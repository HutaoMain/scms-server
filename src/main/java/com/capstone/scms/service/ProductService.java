package com.capstone.scms.service;

import com.capstone.scms.dto.ProductDto;
import com.capstone.scms.model.Category;
import com.capstone.scms.model.Product;
import com.capstone.scms.repository.CategoryRepository;
import com.capstone.scms.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public Product createProduct(ProductDto productDto, Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        Product product = new Product();
        if (category != null) {
            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());
            product.setQuantity(productDto.getQuantity());
            product.setDescription(productDto.getDescription());
            product.setCategory(category);

            category.getProducts().add(product); // Add the product to the category's list of products
            categoryRepository.save(category);

            productRepository.save(product);
        } else {
            log.info("There is no category that has an ID of: " + categoryId);
        }

        return product;
    }

    public List<Product> getListProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    public Product updateProduct(ProductDto productDto, Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setPrice(productDto.getPrice());
            product.setQuantity(productDto.getQuantity());
            productRepository.save(product);
        } else {
            log.info("product not exist");
        }
        return product;
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

}
