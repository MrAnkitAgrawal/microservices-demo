package com.spe.demo.catalogservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spe.demo.catalogservice.domain.model.Catalog;
import com.spe.demo.catalogservice.domain.repo.CatalogRepo;

@Service
public class CatalogService {
	CatalogRepo catalogRepo;

	@Autowired
	public CatalogService(CatalogRepo catalogRepo) {
		this.catalogRepo = catalogRepo;
	}

	public int getInventoryByItemCode(int itemCode) {
		Catalog catalog = catalogRepo.findById(itemCode).get();
		if (catalog == null) {
			return -1;
		}
		return catalog.getInventory();
	}

	public int getInventoryByItemName(String itemName) {
		Catalog catalog = catalogRepo.findByItemName(itemName);
		if (catalog == null) {
			return -1;
		}
		return catalog.getInventory();
	}

	public void saveCatalogs(List<Catalog> catalogList) {
		catalogRepo.saveAll(catalogList);
	}
}
