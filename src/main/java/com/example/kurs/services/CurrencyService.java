package com.example.kurs.services;

import com.example.kurs.models.Currency;
import com.example.kurs.repositories.CurrencyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public List<Currency> findAll() {
        return (List<Currency>) currencyRepository.findAll();
    }

    public Currency findById(Long id) {
        return currencyRepository.findById(id).orElseThrow(() -> new RuntimeException("Currency not found"));
    }

    public Currency save(Currency currency) {
        return currencyRepository.save(currency);
    }

    public void deleteById(Long id) {
        currencyRepository.deleteById(id);
    }
}
