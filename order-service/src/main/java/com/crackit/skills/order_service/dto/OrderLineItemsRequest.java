package com.crackit.skills.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemsRequest {

    private Integer id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
