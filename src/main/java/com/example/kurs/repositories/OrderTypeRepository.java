package com.example.kurs.repositories;

import com.example.kurs.models.OrderType;
import org.springframework.data.repository.CrudRepository;

public interface OrderTypeRepository extends CrudRepository<OrderType, Long> {
}
