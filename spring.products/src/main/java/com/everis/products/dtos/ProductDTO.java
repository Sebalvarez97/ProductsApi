package com.everis.products.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.everis.products.entity.Product;

public class ProductDTO implements Serializable {

	
	private Long productId;
	
	@NotNull
	private String productName;
	
	@NotNull
	private String productCode;
	
	private String releaseDate;
    private Double price;
    private String description;
    private Double starRating;
    private String imageUrl;

    public ProductDTO() {}
    
    public ProductDTO(Product p) {
    	this.productId = p.getProductId();
		this.productName = p.getProductName();
		this.productCode = p.getProductCode();
		this.releaseDate = p.getReleaseDate();
		this.price = p.getPrice();
		this.description = p.getDescription();
		this.starRating = p.getStarRating();
		this.imageUrl = p.getImageUrl();
    }
    
    public ProductDTO(Long productId, String productName, String productCode, String releaseDate, Double price,
			String description, Double starRating, String imageUrl) {
    	this.productId = productId;
		this.productName = productName;
		this.productCode = productCode;
		this.releaseDate = releaseDate;
		this.price = price;
		this.description = description;
		this.starRating = starRating;
		this.imageUrl = imageUrl;
    }
    
    public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductoCode(String productCode) {
		this.productCode = productCode;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getStarRating() {
		return starRating;
	}
	public void setStarRating(Double starRating) {
		this.starRating = starRating;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
    
	
}
