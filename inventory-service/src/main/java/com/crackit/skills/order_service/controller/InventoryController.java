package com.crackit.skills.order_service.controller;

import com.crackit.skills.order_service.dto.InventoryResponse;
import com.crackit.skills.order_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> placeOrder(@RequestParam List<String> skuCode){

        return inventoryService.checkInStock(skuCode);

    }

}
