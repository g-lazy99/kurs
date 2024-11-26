package com.example.kurs.repositories;

import com.example.kurs.models.Currency;
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
class CurrencyRepositoryTest {

    private CurrencyRepository currencyRepository;
    @Autowired
    public CurrencyRepositoryTest(
            CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    private Currency currency;
    @BeforeEach
    public void setup(){
        currency = Currency.builder().currencyFull("Рубль").currencyShort("Руб").build();
    }
    @Test
    public void saveCurrencyTest()
    {
        Currency saved = currencyRepository.save(currency);
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isGreaterThan(0);
    }
    @Test
    public void getCurrencyListTest()
    {
        Currency currency1 = Currency.builder().currencyFull("Доллар").currencyShort("$").build();

        currencyRepository.save(currency);
        currencyRepository.save(currency1);
        Iterable<Currency> list = currencyRepository.findAll();

        assertThat(list).isNotNull();
    }
    @Test
    public void findByIdTest()
    {
        currencyRepository.save(currency);

        Currency currency2 = currencyRepository.findById(currency.getId()).get();

        assertThat(currency2).isNotNull();
    }
}
