package com.spe.demo.catalogservice.domain.repo;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.spe.demo.catalogservice.domain.model.Catalog;

@RepositoryRestResource(path="catalog")
public interface CatalogRepo extends PagingAndSortingRepository<@Valid Catalog, Integer> {
	Catalog findByItemName(@Param("name") String itemName);
	
	@Query("Select c.inventory from Catalog c where c.itemName = ?1")
	int getInventoryByItemName(@Param("name") String itemName);

	@Override
	@RestResource(exported=false)
	void delete(Catalog entity);

	@Override
	@RestResource(exported=false)
	void deleteAll(); 
}
