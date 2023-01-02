package com.sportyshoes.restapi.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportyshoes.restapi.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long>{

	Optional<Product> findProductById(Long id);
	
	void deleteProductById(Long id); //SpringBoot is intelligent enough to figure out

}//end interface
