package it.objectmethod.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.rest.dao.FamAssortDao;
import it.objectmethod.rest.model.FamAssort;

@RestController
public class FamAssController {

	@Autowired
	private FamAssortDao famAssDao;

	@GetMapping("/api/famassort/list")
	public List<FamAssort> getFamAss() {

		List<FamAssort> listFamAss = famAssDao.getFamAssort();
		return listFamAss;
	}

}
