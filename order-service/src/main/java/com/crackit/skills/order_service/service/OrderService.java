package com.crackit.skills.order_service.service;

import com.crackit.skills.order_service.dto.OrderLineItemsRequest;
import com.crackit.skills.order_service.dto.OrderRequest;
import com.crackit.skills.order_service.model.Order;
import com.crackit.skills.order_service.model.OrderLineItem;
import com.crackit.skills.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    public String createOrder(OrderRequest orderRequest){

        Order newOrder=new Order();

        newOrder.setOrderNo(UUID.randomUUID().toString());

        List<OrderLineItem> items=orderRequest.getOrderLineItemsRequest()
                .stream().map(this::mapToOrderLineItems).toList();

        newOrder.setOrderLineItemsList(items);

        orderRepository.save(newOrder);
        return "Order placed successfully";

    }

    private OrderLineItem mapToOrderLineItems(OrderLineItemsRequest orderLineItemsRequest) {
        return OrderLineItem.builder()
                .skuCode(orderLineItemsRequest.getSkuCode())
                .price(orderLineItemsRequest.getPrice())
                .quantity(orderLineItemsRequest.getQuantity())
                .build();
    }


}
