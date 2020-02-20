package it.objectmethod.rest.controller.beans;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class CartMap {

	Map<Long, Cart> carts;

	public Map<Long, Cart> getCarrelli() {
		if(this.carts == null) {
			this.carts = new HashMap<Long, Cart>();
		}
		return carts;
	}

}
