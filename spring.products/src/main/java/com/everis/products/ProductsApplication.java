package com.everis.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.everis.products.dtos.ProductDTO;
import com.everis.products.entity.Product;
import com.everis.products.service.ProductService;

@SpringBootApplication
public class ProductsApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(ProductsApplication.class, args);
		
		ProductService ps = ctx.getBean(ProductService.class);
		/*
		Product p = new Product();
		p.setProductName("Video Game Controller");
		p.setDescription("Standard two-button video game controller");
		p.setPrice(35.95);
		p.setImageUrl("https://openclipart.org/image/300px/svg_to_png/120337/xbox-controller_01.png");
		p.setProductCode("GMG-0042");
		p.setReleaseDate("October 15, 2015");
		p.setStarRating(4.6);
		
		//ps.create(p);
		*/
	}

}

