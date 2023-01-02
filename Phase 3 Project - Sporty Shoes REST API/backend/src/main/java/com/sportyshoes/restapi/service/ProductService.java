package com.sportyshoes.restapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sportyshoes.restapi.exception.ProductNotFoundException;
import com.sportyshoes.restapi.model.Product;
import com.sportyshoes.restapi.repo.ProductRepo;

@Service
@Transactional
public class ProductService {
	//Properties
	private final ProductRepo productRepo;	

	//Constructor with Dependency Injection of the Product Repository	
	@Autowired
	public ProductService(ProductRepo productRepo) {
		this.productRepo = productRepo;
	}
	
	//CRUD Methods
	public Product addProduct(Product product) {        
        return productRepo.save(product);
    }

	public Product getProduct(Long id) {		
		return productRepo.findById(id).get();
	}
	
	public List<Product> findAllProducts(){
		return productRepo.findAll();
	}
	
	public Product updateProduct(Product product) {		
			return productRepo.save(product);		
	}
	
	public Product updateProduct(Product product, Long id) {
		if(product.getId() == id)
			return productRepo.save(product);
		return product;
	}
	
	public Product findProductById(Long id) {
		return productRepo.findProductById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product by id" + id + " was not found"));
	}
	
	//must have @Transactional annotation for this to work properly
	public void deleteProduct(Long id) {
		productRepo.deleteProductById(id);
	}

}//end class
