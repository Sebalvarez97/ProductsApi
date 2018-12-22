package com.everis.products;


import java.util.List;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.client.ResourceAccessException;

import com.everis.products.dtos.ProductDTO;
import com.everis.products.entity.Product;
import com.everis.products.repository.IProductRepository;
import com.everis.products.service.ProductService;
import com.everis.products.service.exceptions.ExistingEntityException;


@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class ProductsApplicationTests {
	
	@Autowired
	private IProductRepository repository;
	private static ProductDTO dtotocompare;
	private static ProductDTO dtoforuse;
	private static final long WRONG_ID = new Long(-23);
	private static final long CORRECT_ID = new Long(8);
	private static final long FOREDIT_ID = new Long(10);
	private static final String CORRECT_NAME = "Garden Cart";
	private static final String CORRECT_CODE = "GDN-0023";
	private static final String CORRECT_DATE = "March 18, 2016";
	private static final String CORRECT_DESCRIPTION = "15 gallon capacity rolling garden cart";
	private static final String CORRECT_IMAGE = "https://openclipart.org/image/300px/svg_to_png/58471/garden_cart.png";
	private static final Double CORRECT_PRICE = 32.99;
	private static final Double CORRECT_RATING = 4.2;
	
	@Autowired
	private ProductService service;
	
	@BeforeClass
	public static void antes() {
		dtotocompare = new ProductDTO();
		dtotocompare.setProductId(CORRECT_ID);
		dtotocompare.setProductName(CORRECT_NAME);
		dtotocompare.setProductoCode(CORRECT_CODE);
		dtotocompare.setReleaseDate(CORRECT_DATE);
		dtotocompare.setPrice(CORRECT_PRICE);
		dtotocompare.setDescription(CORRECT_DESCRIPTION);
		dtotocompare.setStarRating(CORRECT_RATING);
		dtotocompare.setImageUrl(CORRECT_IMAGE);
		
		dtoforuse = new ProductDTO();
	}
	
	@Test
	@Transactional(readOnly = true, isolation = Isolation.DEFAULT)
	public void ListAreTheSame() throws Exception {
		List<ProductDTO> dtos = service.getProducts();
		List<Product> products = repository.findAll();
		assertNotNull(dtos);
		assertNotNull(products);
		for (ProductDTO dto : dtos) {
			boolean isin = false;
			for (Product product : products) {
				if(product.getProductId() == dto.getProductId()) {
					isin = true;
					break;
				}
			}
			assertTrue(isin);
		}
		for (Product product : products) {
			boolean isin = false;
			for (ProductDTO dto : dtos) {
				if(product.getProductId() == dto.getProductId()) {
					isin = true;
					break;
				}
			}
			assertTrue(isin);
		}
	}
	
	@Test
	@Transactional(readOnly = true, isolation = Isolation.DEFAULT)
	public void getAllProductsOk() {
		List<ProductDTO> dtos = service.getProducts();
		assertNotNull(dtos);
		for (ProductDTO dto : dtos) {
			assertNotNull(dto);
		}
		assertTrue(isInTheList(dtotocompare, dtos));
	}
	
	private boolean isInTheList(ProductDTO dto, List<ProductDTO> dtos) {
		for (ProductDTO productDTO : dtos) {
			if(dto.getProductId().equals(productDTO.getProductId()) 
					&& dto.getProductName().equals(productDTO.getProductName())
						&& dto.getProductCode().equals(productDTO.getProductCode())
							&& dto.getDescription().equals(productDTO.getDescription())
								&& dto.getPrice().equals(productDTO.getPrice())
									&& dto.getReleaseDate().equals(productDTO.getReleaseDate())
										&& dto.getStarRating().equals(productDTO.getStarRating())
											&& dto.getImageUrl().equals(productDTO.getImageUrl())) {
				return true;
			}
		}
		return false;
	}
	
	@Test
	public void getByIdTestFail() {;
		try {
			ProductDTO dto = service.getProductById(WRONG_ID);
		}catch(ResourceAccessException ex) {
			Assert.hasText("Product not found, id:", ex.getMessage());
		}
	}
	
	@Test
	public void getByIdTestOk() {
		ProductDTO dto = service.getProductById(CORRECT_ID);
		assertNotNull(dto);
		assertEquals(dtotocompare.getProductId(),dto.getProductId());
		compareDTOs(dtotocompare, dto);
	}
	
	@Test
	@Transactional(readOnly = true, isolation = Isolation.DEFAULT)
	@Rollback
	public void createProductTestOk() throws ExistingEntityException {
		dtoforuse.setProductId(null);
		dtoforuse.setProductName("Samsum Galaxy s9");
		dtoforuse.setProductoCode("LKF-0980");
		dtoforuse.setReleaseDate("November 18, 2018");
		dtoforuse.setPrice(10000.89);
		dtoforuse.setDescription("Cell phone with 30mpx, 2gb memory");
		dtoforuse.setStarRating(4.0);
		dtoforuse.setImageUrl("https://openclipart.org/image/300px/svg_to_png/302405/1527145993.png");
		ProductDTO dto = service.createProduct(dtoforuse);
		assertNotNull(dto);
		compareDTOs(dtoforuse,dto);
	}
	
	@Test(expected = ExistingEntityException.class)
	@Transactional(readOnly = true, isolation = Isolation.DEFAULT)
	@Rollback
	public void createProductTestFail() throws ExistingEntityException {
		ProductDTO dto = service.createProduct(dtotocompare);
		assertNotNull(dto);
		assertEquals(dtotocompare.getProductId(),dto.getProductId());
		compareDTOs(dtotocompare, dto);
	}
	
	@Test
	@Transactional(readOnly = true, isolation = Isolation.DEFAULT)
	@Rollback
	public void editProductTestOK() throws ExistingEntityException {
		String nombreantes = dtotocompare.getProductName();
		dtotocompare.setProductName("NUEVO NOMBRE");
		ProductDTO dto = service.editProduct(dtotocompare);
		assertNotNull(dto);
		dtotocompare.setProductName(nombreantes);
	}
	
	
	@Test(expected = ResourceAccessException.class)
	@Transactional(readOnly = true, isolation = Isolation.DEFAULT)
	@Rollback
	public void editProductTestFail() throws ExistingEntityException {
		ProductDTO dto = new ProductDTO();
		dto.setProductId(WRONG_ID);
		dto = service.editProduct(dto);
		assertNotNull(dto);
	}
	
	@Test
	@Transactional(readOnly = true, isolation = Isolation.DEFAULT)
	@Rollback
	public void deleteProductTestOk() {
		ProductDTO dto = service.deleteProduct(CORRECT_ID);
		assertNotNull(dto);
		assertEquals(dtotocompare.getProductId(),dto.getProductId());
		compareDTOs(dtotocompare, dto);
		try {
			service.getProductById(dtotocompare.getProductId());
		}catch(ResourceAccessException ex) {
			Assert.hasText("Product not found, id:", ex.getMessage());
		}
	}
	
	@Test
	@Transactional(readOnly = true, isolation = Isolation.DEFAULT)
	@Rollback
	public void deleteProductTestFail() {
		try {
			service.deleteProduct(WRONG_ID);
		}catch(ResourceAccessException ex) {
			Assert.hasText("Product not found, id:", ex.getMessage());
		}
	}
	
	private void compareDTOs(ProductDTO expected, ProductDTO dto) {
		assertEquals(expected.getProductName(), dto.getProductName());
		assertEquals(expected.getProductCode(),dto.getProductCode());
		assertEquals(expected.getReleaseDate(),dto.getReleaseDate());
		assertEquals(expected.getPrice(),dto.getPrice());
		assertEquals(expected.getDescription(),dto.getDescription());
		assertEquals(expected.getStarRating(),dto.getStarRating());
		assertEquals(expected.getImageUrl(),dto.getImageUrl());
	}
}

