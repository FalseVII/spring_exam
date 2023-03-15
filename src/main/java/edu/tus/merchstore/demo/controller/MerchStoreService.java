package edu.tus.merchstore.demo.controller;

import edu.tus.merchstore.demo.dao.ProductRepository;
import edu.tus.merchstore.demo.dto.Product;
import edu.tus.merchstore.demo.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class MerchStoreService {

    private ProductRepository productRepo;

    public MerchStoreService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    @GetMapping("/products")
    public Iterable<Product> getallProducts() {
        return productRepo.findAll();

    }

    @GetMapping("/products/search/brand/{queryBrand}")
    public ResponseEntity<List<Product>> getProductByBrande(@PathVariable("queryBrand") String queryBrand){
        List<Product> productsByBrand = new ArrayList<>();
        productsByBrand = productRepo.findByBrandContaining(queryBrand);
        if(productsByBrand.size()>0) {
            return new ResponseEntity(productsByBrand, HttpStatus.OK);
        }
        else {
            return new ResponseEntity(productsByBrand, HttpStatus.NO_CONTENT);
        }

    }
    @GetMapping("/products/{id}")
    public Optional<Product> getProductById(@PathVariable("id")Long id){
        Optional<Product> product = productRepo.findById(id);
        if(!product.isPresent()) {
            throw new ProductNotFoundException("No product found with ID:  " +id);
        }
        else {
            return product;
        }
    }

    @GetMapping("/products/search/item/{queryType}")
    public ResponseEntity<List<Product>> getProductByItemType(@PathVariable("queryType") String queryType){
        List<Product> productsByType = new ArrayList<>();
        productsByType = productRepo.findByTypeContaining(queryType);
        if(productsByType.size()>0) {
            return new ResponseEntity(productsByType, HttpStatus.OK);
        }
        else {
            return new ResponseEntity(productsByType, HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping("/products/search/deal/{price}")
    public ResponseEntity<List<Product>> getBooksByPriceLessThan(@PathVariable("price") double price){
        List<Product> productsByPrice = new ArrayList<>();
        productsByPrice = productRepo.findByDealsMoreThan(price);
        if(productsByPrice.size()>0) {
            return new ResponseEntity(productsByPrice, HttpStatus.OK);
        }
        else {
            return new ResponseEntity(productsByPrice, HttpStatus.NO_CONTENT);
        }
    }
}
