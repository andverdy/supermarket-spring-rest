package it.objectmethod.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.rest.dao.IvaDao;
import it.objectmethod.rest.model.Iva;

@RestController
public class IvaController {

	@Autowired
	private IvaDao ivaDao;

	@GetMapping("/api/iva/list")
	public List<Iva> getIvaList() {

		List<Iva> listIva = ivaDao.getIva();

		return listIva;
	}

}
