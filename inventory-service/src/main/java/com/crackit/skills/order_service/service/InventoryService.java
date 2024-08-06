package com.crackit.skills.order_service.service;

import com.crackit.skills.order_service.dto.InventoryResponse;
import com.crackit.skills.order_service.repository.InventoryRepository;
import com.crackit.skills.order_service.model.Inventory;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService{

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    @SneakyThrows
    public List<InventoryResponse> checkInStock(List<String> skuCode){

        log.info("Inventory service called");
        List<Inventory> inventoryItems=inventoryRepository.findBySkuCodeIn(skuCode);

        return inventoryItems.stream().map(item->
              InventoryResponse.builder()
                      .skuCode(item.getSkuCode())
                      .isInStock(item.getQuantity()>0)
                      .build()
                ).toList();


    }
}
