package com.example.gbspringlesson7.controllers;
import com.example.gbspringlesson7.dto.ProductDto;
import com.example.gbspringlesson7.entities.Product;
import com.example.gbspringlesson7.exceptions.ResourceNotFoundException;
import com.example.gbspringlesson7.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public Page<ProductDto> getAllProducts(
        @RequestParam(name = "p", defaultValue = "1") Integer page,
        @RequestParam(name = "min_cost", required = false) Integer minCost,
        @RequestParam(name = "max_cost", required = false) Integer maxCost,
        @RequestParam(name = "title_part", required = false) String titlePart
    ) {
            if (page < 1) {
                page = 1;
            }
            return productService.find(minCost, maxCost, titlePart, page).map(
                    p -> new ProductDto(p)
            );
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id));
    }

    @GetMapping("/change_cost")
    public void changeCost(@RequestParam Integer delta, @RequestParam Long id){
        productService.changeCost(delta, id);
    }

    @PostMapping
    public Product saveNewProduct(@RequestBody Product product) {
        product.setId(null);
        return productService.save(product);
    }
    @PutMapping
    public Product updateProduct(@RequestBody Product product){
        return productService.save(product);
    }
}
