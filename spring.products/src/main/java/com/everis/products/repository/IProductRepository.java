package com.everis.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.everis.products.entity.Product;

@Repository("productRepository")
public interface IProductRepository extends JpaRepository <Product, Long>{

}
