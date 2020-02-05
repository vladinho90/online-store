package com.sda.grupa11.onlinestore.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.grupa11.onlinestore.dto.order_line.OrderLineMapper;
import com.sda.grupa11.onlinestore.dto.order_line.OrderLineRequest;
import com.sda.grupa11.onlinestore.dto.order_line.OrderLineResponse;
import com.sda.grupa11.onlinestore.model.OrderLine;
import com.sda.grupa11.onlinestore.repository.OrderLineRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderLineService {

    private final static Logger log = LoggerFactory.getLogger(OrderLineService.class);
    private OrderLineRepository orderLineRepository;
    private OrderLineMapper orderLineMapper;
    private ObjectMapper jacksonObjectMapper;

    public OrderLineResponse findById(Long id) {
        OrderLine orderLine = orderLineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("order line not found"));
        return orderLineMapper.toDto(orderLine);
    }

    public List<OrderLineResponse> findAll() {
        List<OrderLine> orderLines = orderLineRepository.findAll();
        return orderLineMapper.toDto(orderLines);
    }

    public OrderLineResponse save(OrderLineRequest orderLineRequest) {
        OrderLine orderLine = orderLineMapper.toEntity(orderLineRequest);
        OrderLine savedOrderLine = orderLineRepository.save(orderLine);
        return orderLineMapper.toDto(savedOrderLine);
    }

    public OrderLineResponse update(Long id, OrderLineRequest orderLineRequest) {
        OrderLine orderLine = orderLineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("order line not found"));

        updateFields(orderLine, orderLineRequest);

        OrderLine savedOrderLine = orderLineRepository.save(orderLine);

        return orderLineMapper.toDto(savedOrderLine);
    }

    private void updateFields(OrderLine orderLine, OrderLineRequest orderLineRequest) {
        orderLine.setProduct(orderLineRequest.getProduct());
        orderLine.setQuantity(orderLineRequest.getQuantity());
        orderLine.setPrice(orderLineRequest.getPrice());
        orderLine.setOrders(orderLineRequest.getOrders());
    }
}
