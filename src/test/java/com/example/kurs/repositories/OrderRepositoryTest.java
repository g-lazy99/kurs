package com.example.kurs.repositories;

import com.example.kurs.models.Currency;
import com.example.kurs.models.Order;
import com.example.kurs.models.OrderType;
import com.example.kurs.models.OrderVeriety;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class OrderRepositoryTest {

    @BeforeEach
    void cleanDatabase() {
        orderRepository.deleteAll();
        orderTypeRepository.deleteAll();
        orderVerietyRepository.deleteAll();
        currencyRepository.deleteAll();
    }

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderTypeRepository orderTypeRepository;

    @Autowired
    private OrderVerietyRepository orderVerietyRepository;

    @Autowired
    private CurrencyRepository currencyRepository;



    @Test
    void findAll_ShouldReturnEmptyList_WhenNoOrdersExist() {
        List<Order> orders = (List<Order>) orderRepository.findAll();
        System.out.println("Количество записей в таблице: " + orders.size());
        orders.forEach(order -> System.out.println("Запись: " + order));
        assertTrue(orders.isEmpty());
    }

    @Test
    void save_ShouldPersistOrder() {

        OrderType orderType = new OrderType(null, "Брокерская");
        OrderVeriety orderVeriety = new OrderVeriety(null, "Покупка");
        Currency currency = new Currency(null, "Российский рубль", "RUB");

        OrderType savedOrderType = orderTypeRepository.save(orderType);
        OrderVeriety savedOrderVeriety = orderVerietyRepository.save(orderVeriety);
        Currency savedCurrency = currencyRepository.save(currency);


        Order order = new Order();
        order.setTiker("NVDA");
        order.setNumber(1001);
        order.setOrderType(savedOrderType);
        order.setOrderVeriety(savedOrderVeriety);
        order.setCurrency(savedCurrency);
        order.setData(LocalDateTime.of(2021, 4, 24, 14, 33, 48, 123456789));
        order.setCount(10);
        order.setDuration("10 days");


        // Сохраняем Order
        Order savedOrder = orderRepository.save(order);

        assertNotNull(savedOrder.getId());
        assertEquals("NVDA", savedOrder.getTiker());
    }
}

