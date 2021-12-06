package com.example.gbspringlesson7.controllers;

import com.example.gbspringlesson7.entities.Product;
import com.example.gbspringlesson7.exceptions.ResourceNotFoundException;
import com.example.gbspringlesson7.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id));
    }

    @GetMapping("/products/cost_between")
    public List<Product> findProductBetween(@RequestParam(defaultValue = "0") Integer min, @RequestParam(defaultValue = "100000") Integer max) {
        return productService.findAllByCostBetween(min, max);
    }

    @GetMapping("/products/chipper_then")
    public List<Product> findChipperThen(@RequestParam(defaultValue = "0") Integer border) {
        return productService.findChipperThen(border);
    }

    @GetMapping("/products/more_expensive_then")
    public List<Product> findMoreExpensiveThen(@RequestParam(defaultValue = "0") Integer border) {
        return productService.findMoreExpensiveThen(border);
    }
    @GetMapping("/products/change_cost")
    public void changeCost(@RequestParam Integer delta, @RequestParam Long id){
        productService.changeCost(delta, id);
    }
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public Product saveNewProduct(@RequestBody Product product) {
        return productService.save(product);

    }
}
