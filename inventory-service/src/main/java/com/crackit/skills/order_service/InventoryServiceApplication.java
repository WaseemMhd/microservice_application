package com.crackit.skills.order_service;

import com.crackit.skills.order_service.model.Inventory;
import com.crackit.skills.order_service.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(InventoryRepository inventoryRepository){

		return args -> {
			Inventory inventory1=new Inventory();
			inventory1.setSkuCode("MB-112");
			inventory1.setQuantity(300);

			Inventory inventory2=new Inventory();
			inventory2.setSkuCode("MB-213");
			inventory2.setQuantity(0);

			inventoryRepository.save(inventory1);
			inventoryRepository.save(inventory2);
		};
	}
}
