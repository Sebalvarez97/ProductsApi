package com.everis.products.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.everis.products.dtos.ProductDTO;
import com.everis.products.entity.Product;
import com.everis.products.repository.IProductRepository;

@Service
public class ProductService {
	
	private IProductRepository productRepository;
	
	public ProductService(IProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public List<ProductDTO> getProducts(){
		List<ProductDTO> all = new ArrayList<ProductDTO>();
		for(Product product : productRepository.findAll()) {
			ProductDTO p = new ProductDTO(product);
			all.add(p);
		}
		return all;
	}
	
	public ProductDTO getProductById(Long productId) {
		Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceAccessException("Product not found, id: " + productId));
		return new ProductDTO(product);
	}
	public ProductDTO createProduct(ProductDTO product) {
		Product p = new Product();
			p.setDescription(product.getDescription());
			p.setImageUrl(product.getImageUrl());
			p.setPrice(product.getPrice());
			p.setProductCode(product.getProductCode());
			p.setProductName(product.getProductName());
			p.setReleaseDate(product.getReleaseDate());
			p.setStarRating(product.getStarRating());
		productRepository.save(p);
		return new ProductDTO(p);
	}
	public ProductDTO editProduct(ProductDTO product) {
		if(productRepository.existsById(product.getProductId())) {
			Product antes = productRepository.getOne(product.getProductId());
			antes.setDescription(product.getDescription());
			antes.setImageUrl(product.getImageUrl());
			antes.setPrice(product.getPrice());
			antes.setProductCode(product.getProductCode());
			antes.setProductName(product.getProductName());
			antes.setReleaseDate(product.getReleaseDate());
			antes.setStarRating(product.getStarRating());
		productRepository.save(antes);
		return new ProductDTO(antes);
		}else {
			throw new ResourceAccessException("Product not found, id: " + product.getProductId());
		}
	}
	public void deleteProduct(ProductDTO product) {
		Product p = productRepository.findById(product.getProductId()).orElseThrow(() -> new ResourceAccessException("Product not found, id: " + product.getProductId()));
		productRepository.delete(p);
	}
}
