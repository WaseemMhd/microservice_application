package com.crackit.skills.product_service;

import com.crackit.skills.product_service.model.Product;
import com.crackit.skills.product_service.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.Assert;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

	@Container
	static MongoDBContainer container=new MongoDBContainer("mongo:4.0.10");

	@Autowired
	private  MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ProductRepository productRepository;

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
		dynamicPropertyRegistry.add("spring.data.mongodb.uri",container::getReplicaSetUrl);
	}

	@Test
	void contextLoads() throws Exception {
		Product product=getProduct();
		String stringObject=objectMapper.writeValueAsString(product);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(stringObject))
				.andExpect(status().isCreated());

        Assertions.assertEquals(1, productRepository.findAll().size());
	}

	private Product getProduct(){
		Product newProduct=new Product();
		newProduct.setName("iphone16");
		newProduct.setDescription("jdbhsgfsd");
		newProduct.setPrice(BigDecimal.valueOf(763));
		return newProduct;
	}

}
