package com.example.kurs.controllers;

import com.example.kurs.models.Currency;
import com.example.kurs.services.CurrencyService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/currencies")
public class CurrencyController {

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping
    public String listCurrencies(Model model) {
        model.addAttribute("currencies", currencyService.findAll());
        return "currencies";
    }

    @GetMapping("/{id}")
    public String viewCurrency(@PathVariable Long id, Model model) {
        model.addAttribute("currency", currencyService.findById(id));
        return "viewCurrency";
    }

    @GetMapping("/new")
    public String createCurrencyForm(Model model) {
        model.addAttribute("currency", new Currency());
        return "currencyform";
    }

    @PostMapping
    public String saveCurrency(@Valid @ModelAttribute Currency currency, Errors errors, Model model) {
        if (errors.hasErrors()) {

            model.addAttribute("orderCurrency", currencyService.findAll());
            return "form";
        }
        currencyService.save(currency);
        return "redirect:/currencies";
    }

    @GetMapping("/{id}/edit")
    public String editCurrencyForm(@PathVariable Long id, Model model) {
        Currency currency = currencyService.findById(id);

        model.addAttribute("currency", currency);

        return "currencyform";
    }

    @PostMapping("/{id}/edit")
    public String updateCurrency(@PathVariable Long id, @Valid @ModelAttribute Currency currency, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("orderCurrency", currencyService.findAll());
            return "form";
        }
        currency.setId(id);
        currencyService.save(currency);
        return "redirect:/currencies";
    }

    @PostMapping("/{id}/delete")
    public String deleteCurrency(@PathVariable Long id) {
        currencyService.deleteById(id);
        return "redirect:/currencies";
    }
}

