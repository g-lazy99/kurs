package com.example.kurs.services;

import com.example.kurs.models.OrderType;
import com.example.kurs.repositories.OrderTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderTypeService {

    private final OrderTypeRepository orderTypeRepository;

    public OrderTypeService(OrderTypeRepository orderTypeRepository) {
        this.orderTypeRepository = orderTypeRepository;
    }

    public List<OrderType> findAll() {
        return (List<OrderType>) orderTypeRepository.findAll();
    }

    public OrderType findById(Long id) {
        return orderTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("OrderType not found"));
    }

    public OrderType save(OrderType orderType) {
        return orderTypeRepository.save(orderType);
    }

    public void deleteById(Long id) {
        orderTypeRepository.deleteById(id);
    }
}
