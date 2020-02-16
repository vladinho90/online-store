package com.sda.group11.onlinestore.dto.order_line;

import com.sda.group11.onlinestore.model.OrderLine;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderLineMapper {

    public OrderLineResponse toDto(OrderLine orderLine) {
        OrderLineResponse dto = new OrderLineResponse();
        dto.setId(orderLine.getId());
        dto.setProduct(orderLine.getProduct());
        dto.setQuantity(orderLine.getQuantity());
        dto.setPrice(orderLine.getPrice());
        return dto;
    }

    public List<OrderLineResponse> toDto(List<OrderLine> orderLines) {
        return orderLines.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public OrderLine toEntity(OrderLineRequest orderLineRequest) {
        OrderLine entity = new OrderLine();
        entity.setProduct(orderLineRequest.getProduct());
        entity.setQuantity(orderLineRequest.getQuantity());
        entity.setPrice(orderLineRequest.getPrice());
        entity.setOrder(orderLineRequest.getOrder());
        return entity;
    }

    public List<OrderLine> toEntity(List<OrderLineRequest> orderLineRequestList) {
        List<OrderLine> orderLines = new ArrayList<>();
        for (OrderLineRequest orderLineRequest : orderLineRequestList)
            orderLines.add(toEntity(orderLineRequest));
        return orderLines;
    }
}
