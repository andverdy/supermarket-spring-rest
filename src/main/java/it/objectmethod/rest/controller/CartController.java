package it.objectmethod.rest.controller;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.rest.controller.beans.Cart;
import it.objectmethod.rest.controller.beans.CartMap;
import it.objectmethod.rest.dao.ArticleDao;
import it.objectmethod.rest.model.Article;
import it.objectmethod.rest.model.ArticleCart;

@RestController
public class CartController {

	@Autowired
	private CartMap carts;
	@Autowired
	private ArticleDao artDao;

	 
	@PostMapping("/api/cart/insert")
	public Long addToCart(@RequestParam("codArt") String codArt,
			@RequestParam(value = "cartKey", required = false) Long cartKey)  {
            // "required = false" lo uso quando voglio rendere facoltativo un parametro, se lo ometto allora lo rendo obblig.
		if (cartKey == null) { // Verifica se mi sono passato una key di un carrello
			Random random = new Random();
			do {
				cartKey = random.nextLong(); // Genero key carrello random finchè non me ne esce una non utilizzata
				if (cartKey < 0) {
					cartKey *= -1;
				}
			} while (carts.getCarrelli().containsKey(cartKey));

		}

		Cart cart = carts.getCarrelli().get(cartKey); // Cerco di recuperare il carrello corrispondente alla key passata
		if (cart == null) { // Se non esiste lo creo
			cart = new Cart();
		}

		Map<String, ArticleCart> cartArt = cart.getArticoli();
		ArticleCart articleCart = cartArt.get(codArt); // Prelevo l'articolo corrispondente al codice passato se
														// presente
		if (articleCart == null) {
			Article article = artDao.getArticleByCode(codArt); // Altrimenti lo aggiungo adesso
			articleCart = new ArticleCart(article);
		}
		articleCart.setQuantita(articleCart.getQuantita() + 1); // Aumento la quantità di 1
		cartArt.put(codArt, articleCart); // aggiungo l'articolo al carrello
		carts.getCarrelli().put(cartKey, cart); // aggiorno mappa dei carrelli

		return cartKey;
	}

	@GetMapping("/api/cart/by-key")
	public Cart getCart(@RequestParam("cartKey") Long cartKey) {

		Map<Long, Cart> map = carts.getCarrelli();
		Cart cart = map.get(cartKey);
		
		return cart;
	}
	
	
	

}
