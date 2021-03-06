package com.everis.products.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import com.everis.products.dtos.ProductDTO;
import com.everis.products.entity.Product;
import com.everis.products.repository.IProductRepository;
import com.everis.products.service.ProductService;
import com.everis.products.service.exceptions.ExistingEntityException;

@Controller
@RestController
@RequestMapping(path = "/api")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping(path="/products")
	public @ResponseBody ResponseEntity<List<ProductDTO>> getAllProducts() {
		// This returns a JSON or XML with the users
			return ResponseEntity
					.ok()
					.body(productService.getProducts());
	}
	
	@GetMapping("/products/{productId}")
	public @ResponseBody ResponseEntity<ProductDTO> getProduct(@PathVariable Long productId) {
		try {
			ProductDTO product = productService.getProductById(productId);
			return ResponseEntity
					.ok()
					.body(product);
		}catch(ResourceAccessException ex) {
			return ResponseEntity.status(204).build();
		}
	}
	
	@PostMapping("/products/add")
	public @ResponseBody ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody ProductDTO product) {
		try {
			return ResponseEntity
					.status(201)
					.body(productService.createProduct(product));
		} catch (ExistingEntityException e) {
			return ResponseEntity.status(204).build();
		}
	}
	@PutMapping("/products/edit")
	public @ResponseBody ResponseEntity<ProductDTO> editProduct(@Valid @RequestBody ProductDTO product){
		try {
			return ResponseEntity.status(201).body(productService.editProduct(product));
		} catch (ExistingEntityException e) {
			return ResponseEntity.status(204).build();
		}
	}
	
	@DeleteMapping("/products/{productId}")
	public @ResponseBody ResponseEntity<ProductDTO> deleteProduct(@PathVariable Long productId) {
		try {
			ProductDTO product = productService.deleteProduct(productId);
			return ResponseEntity
					.ok()
					.body(product);
		}catch(ResourceAccessException ex) {
			return ResponseEntity.status(204).build();
		}
	}
} 
