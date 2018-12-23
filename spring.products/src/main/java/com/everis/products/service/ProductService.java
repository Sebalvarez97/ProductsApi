package com.everis.products.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.everis.products.dtos.ProductDTO;
import com.everis.products.entity.Product;
import com.everis.products.repository.IProductRepository;
import com.everis.products.service.exceptions.ExistingEntityException;

@Service
public class ProductService {
	
	private IProductRepository productRepository;
	
	private final String preExistentMessage = "The product already exists";
	private final String accesFailMessage = "Product not found, id: ";
	
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
	
	public ProductDTO createProduct(ProductDTO product) throws ExistingEntityException {
			Product p = new Product();
		if(validate(product.getProductName(),product.getProductCode(), p.getProductId())) {
			p.setDescription(product.getDescription());
			p.setImageUrl(product.getImageUrl());
			p.setPrice(product.getPrice());
			p.setProductCode(product.getProductCode());
			p.setProductName(product.getProductName());
			p.setReleaseDate(product.getReleaseDate());
			p.setStarRating(product.getStarRating());
		productRepository.save(p);
		return new ProductDTO(p);
		}else {
			throw new ExistingEntityException(preExistentMessage);
		}
	}
	
	public ProductDTO editProduct(ProductDTO product) throws ExistingEntityException {
		if(productRepository.existsById(product.getProductId())) {
				Product antes = productRepository.getOne(product.getProductId());
			if(validate(product.getProductName(),product.getProductCode(), antes.getProductId())) {
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
				throw new ExistingEntityException(preExistentMessage);
			}
		}else {
			throw new ResourceAccessException(accesFailMessage + product.getProductId());
		}
	}
	
	public ProductDTO deleteProduct(Long productId) {
		Product p = productRepository.findById(productId).orElseThrow(() -> new ResourceAccessException("Product not found, id: " + productId));
		productRepository.delete(p);
		return new ProductDTO(p);
	}
	
	private boolean validate (String name, String code, Long id) {
		boolean ret = true;
		for(Product product : productRepository.findAll()) {
			if((id == null || !product.getProductId().equals(id)) && (product.getProductName().equals(name) || product.getProductCode().equals(code))) {
				ret = false;
				break;
			}
		}
		return ret;
	}
}
