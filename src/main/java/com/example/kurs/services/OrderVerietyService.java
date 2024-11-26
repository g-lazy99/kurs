package com.example.kurs.services;

import com.example.kurs.models.OrderVeriety;
import com.example.kurs.repositories.OrderVerietyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderVerietyService {

    private final OrderVerietyRepository orderVerietyRepository;

    public OrderVerietyService(OrderVerietyRepository orderVerietyRepository) {
        this.orderVerietyRepository = orderVerietyRepository;
    }

    public List<OrderVeriety> findAll() {
        return (List<OrderVeriety>) orderVerietyRepository.findAll();
    }

    public OrderVeriety findById(Long id) {
        return orderVerietyRepository.findById(id).orElseThrow(() -> new RuntimeException("OrderVeriety not found"));
    }

    public OrderVeriety save(OrderVeriety orderVeriety) {
        return orderVerietyRepository.save(orderVeriety);
    }

    public void deleteById(Long id) {
        orderVerietyRepository.deleteById(id);
    }
}