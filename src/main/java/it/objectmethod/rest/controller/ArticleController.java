package it.objectmethod.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.rest.dao.ArticleDao;
import it.objectmethod.rest.model.Article;

@RestController
public class ArticleController {

	@Autowired
	private ArticleDao articleDao;
	                         // list quando faccio il get di una lista search quando cerco in base ad un filtro
	@GetMapping("/api/articles/list") 
	public List<Article> getArticles() {
		List<Article> listArticle = new ArrayList<Article>();
		listArticle = articleDao.getArticles();

		return listArticle;
	}

	@GetMapping("/api/article/by-code")
	public Article formView(@RequestParam(value = "getCod", required = false) String getCod) {
		Article article = null;

		if (getCod != null) {
			article = articleDao.getArticleByCode(getCod);
		}

		return article;
	}

	@PutMapping("/api/article/save")
	public ResponseEntity<Article> articleIns(@RequestBody Article art) {

		int result = 0;
		ResponseEntity<Article> articleResp = null;

		if (articleDao.getArticleByCode(art.getCodArt()) != null) {
			result = articleDao.update(art);
		} else {
			result = articleDao.insert(art);
		}

		if (result == 1) {
			art = articleDao.getArticleByCode(art.getCodArt());
			articleResp = new ResponseEntity<>(art, HttpStatus.OK);
		} else {
			articleResp = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return articleResp;
	}

}
