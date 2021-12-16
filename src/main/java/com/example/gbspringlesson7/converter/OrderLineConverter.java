package com.example.gbspringlesson7.converter;

import com.example.gbspringlesson7.dto.OrderLineDto;
import com.example.gbspringlesson7.entities.OrderLine;
import org.springframework.stereotype.Component;

@Component
public class OrderLineConverter {
    public OrderLine orderLineFromDto(OrderLineDto orderLineDto){
        return new OrderLine(orderLineDto.getId(), orderLineDto.getTitle(), orderLineDto.getCost(), orderLineDto.getCount());
    }
    public OrderLineDto dtoFromOrderLine(OrderLine orderLine){
        return new OrderLineDto(orderLine.getId(), orderLine.getTitle(), orderLine.getCost(), orderLine.getCount());
    }
}
