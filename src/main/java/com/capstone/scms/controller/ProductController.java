package com.capstone.scms.controller;

import com.capstone.scms.dto.ProductDto;
import com.capstone.scms.model.Product;
import com.capstone.scms.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/{categoryId}/create")
    private ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto, @PathVariable Long categoryId) {
        Product product = productService.createProduct(productDto, categoryId);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/list")
    private ResponseEntity<List<Product>> getListProduct() {
        List<Product> products = productService.getListProducts();
        return ResponseEntity.ok(products);
    }

    @PutMapping("/update/{productId}")
    private ResponseEntity<Product> updateProduct(@PathVariable("productId") Long productId, @RequestBody ProductDto productDto) {
        Product product = productService.updateProduct(productDto, productId);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/delete/{productId}")
    private ResponseEntity<String> deleteProduct(@PathVariable("productId") Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok("deleted product ID: " + productId);
    }

    @GetMapping("/list/{productId}")
    private Product getProduct(@PathVariable("productId") Long productId) {
        return productService.getProductById(productId);
    }

}
