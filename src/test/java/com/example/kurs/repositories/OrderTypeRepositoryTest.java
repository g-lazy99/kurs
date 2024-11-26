package com.example.kurs.repositories;

import com.example.kurs.models.OrderType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class OrderTypeRepositoryTest {

    private OrderTypeRepository orderTypeRepository;
    @Autowired
    public OrderTypeRepositoryTest(
            OrderTypeRepository orderTypeRepository) {
        this.orderTypeRepository = orderTypeRepository;
    }

    private OrderType orderType;
    @BeforeEach
    public void setup(){
        orderType = OrderType.builder().type("1").build();
    }
    @Test
    public void saveTypeTest()
    {
        OrderType saved = orderTypeRepository.save(orderType);
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isGreaterThan(0);
    }
    @Test
    public void getTypeListTest()
    {
        OrderType orderType1 = OrderType.builder().type("2").build();

        orderTypeRepository.save(orderType);
        orderTypeRepository.save(orderType1);
        Iterable<OrderType> list = orderTypeRepository.findAll();

        assertThat(list).isNotNull();
    }
    @Test
    public void findByIdTest()
    {
        orderTypeRepository.save(orderType);
        OrderType orderType2 = orderTypeRepository.findById(orderType.getId()).get();
        assertThat(orderType2).isNotNull();
    }

}