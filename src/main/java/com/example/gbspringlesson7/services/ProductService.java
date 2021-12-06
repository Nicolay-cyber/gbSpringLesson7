package com.example.gbspringlesson7.services;

import com.example.gbspringlesson7.entities.Product;
import com.example.gbspringlesson7.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }

    public List<Product> findAllByCostBetween(Integer min, Integer max) {
        return productRepository.findAllByCostBetween(min,max);
    }

    public List<Product> findChipperThen(Integer border){
        return productRepository.findChipperThen(border);
    }

    public List<Product> findMoreExpensiveThen(Integer border){
        return productRepository.findMoreExpensiveThen(border);
    }

    public void changeCost(Integer delta, Long id){
        productRepository.changeCost(delta, id);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }
}
