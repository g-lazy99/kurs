package com.example.kurs.repositories;

import com.example.kurs.models.OrderVeriety;
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
class OrderVerietyRepositoryTest {

    private OrderVerietyRepository orderVerietyRepository;

    @Autowired
    public OrderVerietyRepositoryTest(
            OrderVerietyRepository orderVerietyRepository) {
        this.orderVerietyRepository = orderVerietyRepository;
    }

    private OrderVeriety orderVeriety;

    @BeforeEach
    public void setup() {
        orderVeriety = OrderVeriety.builder().veriety("1").build();
    }

    @Test
    public void saveTypeTest() {
        OrderVeriety saved = orderVerietyRepository.save(orderVeriety);
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isGreaterThan(0);
    }

    @Test
    public void getTypeListTest() {
        OrderVeriety orderVeriety1 = OrderVeriety.builder().veriety("2").build();

        orderVerietyRepository.save(orderVeriety);
        orderVerietyRepository.save(orderVeriety1);
        Iterable<OrderVeriety> list = orderVerietyRepository.findAll();

        assertThat(list).isNotNull();
    }

    @Test
    public void findByIdTest() {
        orderVerietyRepository.save(orderVeriety);
        OrderVeriety orderVeriety2 = orderVerietyRepository.findById(orderVeriety.getId()).get();
        assertThat(orderVeriety2).isNotNull();
    }
}
