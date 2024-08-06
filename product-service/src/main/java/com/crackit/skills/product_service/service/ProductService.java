package com.crackit.skills.product_service.service;

import com.crackit.skills.product_service.model.Product;
import com.crackit.skills.product_service.repository.ProductRepository;
import com.crackit.skills.product_service.dto.ProductRequest;
import com.crackit.skills.product_service.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void createNewProduct(ProductRequest productRequest) {


        Product product= Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        System.out.println("Product added successfully");
    }

    public List<ProductResponse> getAllProduct() {
        List<Product> products=productRepository.findAll();

        return products.stream().map(this::mapToProductResponse).toList();

    }

    private ProductResponse mapToProductResponse(Product product) {

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
