package com.everis.products;


import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.ResourceAccessException;

import com.everis.products.dtos.ProductDTO;
import com.everis.products.entity.Product;
import com.everis.products.repository.IProductRepository;
import com.everis.products.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductsApplicationTests {

	@Autowired
	private IProductRepository repository;
	
	@Autowired
	private ProductService service;
	
	@BeforeClass
	public static void antes() {
	}
	
	@Test
	public void contextLoads() {
	}
	
	@Test(expected = ResourceAccessException.class)
	public void getByNegIdTest() {
		long number = (long) -23;
		service.getProductById(number);
	}
	
	@Test
	public void getByIdTest() {
		long number = (long) 8;
		service.getProductById(number);
	}
	
	@Test
	public void getAll() throws Exception {
		List<ProductDTO> dtos = service.getProducts();
		List<Product> products = repository.findAll();
		for (ProductDTO dto : dtos) {
			boolean isin = false;
			for (Product product : products) {
				if(product.getProductId() == dto.getProductId()) {
					isin = true;
					break;
				}
			}
			if(!isin) {
				throw new Exception("El producto " + dto.getProductId() + " no esta en base de datos");
			}
		}
		for (Product product : products) {
			boolean isin = false;
			for (ProductDTO dto : dtos) {
				if(product.getProductId() == dto.getProductId()) {
					isin = true;
					break;
				}
			}
			if(!isin) {
				throw new Exception("El producto " + product.getProductId() + " no lo esta leyendo de la base de datos");
			}
		}
	}
}

