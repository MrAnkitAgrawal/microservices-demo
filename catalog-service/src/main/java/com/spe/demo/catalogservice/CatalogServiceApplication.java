package com.spe.demo.catalogservice;

//import java.util.ArrayList;
//import java.util.List;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

//import com.spe.demo.catalogservice.domain.model.Catalog;
//import com.spe.demo.catalogservice.services.CatalogService;

@SpringBootApplication
//public class CatalogServiceApplication implements CommandLineRunner{
public class CatalogServiceApplication {
	//@Autowired
	//CatalogService catalogService;
	
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(CatalogServiceApplication.class, args);
		String[] beanDefinitions = ctx.getBeanDefinitionNames();
	}

	/*@Override
	public void run(String... args) throws Exception {
		List<Catalog> catalogList = new ArrayList<>();
		catalogList.add(new Catalog(1, "Laptop", "Laptop", (float) 80000.00, 10));
		catalogList.add(new Catalog(2, "Desktop", "Desktop", (float) 50000.00, 20));
		catalogList.add(new Catalog(3, "Tablet", "Tablet", (float) 50000.00, 30));
		catalogList.add(new Catalog(4, "SP", "Smart Phone", (float) 100000.00, 40));
		
		catalogService.saveCatalogs(catalogList);
	}*/
}
