package com.everis.products.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.everis.products.dtos.ProductDTO;

@Entity
@Table(name = "productos")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "productId")
	private Long productId;
	
	@NotNull
	@Column(name = "productName")
	private String productName;
	
	@NotNull
	@Column(name = "productCode")
	private String productCode;
	
	@Column(name = "releaseDate")
	private String releaseDate;
	
	@Column(name = "price" )
    private Double price;
	
	@Column(name = "description")
    private String description;
	
	@Column(name = "starRating")
    private Double starRating;
	
	@Column(name = "imageUrl")
    private String imageUrl;
	
	public Product() {
		
	}
	
	public Product(Long productId, String productName, String productCode, String releaseDate, Double price,
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

	public void setProductCode(String productCode) {
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
