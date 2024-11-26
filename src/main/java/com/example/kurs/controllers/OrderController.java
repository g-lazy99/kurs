package com.example.kurs.controllers;

import com.example.kurs.models.Order;
import com.example.kurs.services.CurrencyService;
import com.example.kurs.services.OrderService;
import com.example.kurs.services.OrderTypeService;
import com.example.kurs.services.OrderVerietyService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderTypeService orderTypeService;
    private final OrderVerietyService orderVerietyService;
    private final CurrencyService currencyService;

    public OrderController(OrderService orderService, OrderTypeService orderTypeService, OrderVerietyService orderVerietyService, CurrencyService currencyService) {
        this.orderService = orderService;
        this.orderTypeService = orderTypeService;
        this.orderVerietyService = orderVerietyService;
        this.currencyService = currencyService;
    }

    @GetMapping
    public String listOrders(Model model) {
        List<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        return "order";
    }


    @GetMapping("/{id}")
    public String viewOrder(@PathVariable Long id, Model model) {
        Order order = orderService.findById(id);
        model.addAttribute("order", order);
        return "order";
    }

    @GetMapping("/new")
    public String createOrderForm(Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("orderTypes", orderTypeService.findAll());
        model.addAttribute("orderVerity", orderVerietyService.findAll());
        model.addAttribute("orderCurrency", currencyService.findAll());
        return "form"; // Ссылаемся на шаблон "form.html"
    }

    @PostMapping
    public String saveOrder(@Valid @ModelAttribute Order order, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("orderTypes", orderTypeService.findAll());
            model.addAttribute("orderVerity", orderVerietyService.findAll());
            model.addAttribute("orderCurrency", currencyService.findAll());
            return "form"; // Возвращаемся к форме с ошибками
        }
        orderService.save(order);
        return "redirect:/orders";
    }

    @GetMapping("/{id}/edit")
    public String editOrderForm(@PathVariable Long id, Model model) {
        Order order = orderService.findById(id);
        if (order == null) {
            throw new RuntimeException("Order not found for ID: " + id);
        }
        model.addAttribute("order", order);
        model.addAttribute("orderTypes", orderTypeService.findAll());
        model.addAttribute("orderVerity", orderVerietyService.findAll());
        model.addAttribute("orderCurrency", currencyService.findAll());
        return "form"; // Используем ту же форму, что и для создания
    }

    @PostMapping("/{id}/edit")
    public String updateOrder(@PathVariable Long id, @Valid @ModelAttribute Order order, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("orderTypes", orderTypeService.findAll());
            model.addAttribute("orderVerity", orderVerietyService.findAll());
            model.addAttribute("orderCurrency", currencyService.findAll());
            return "form"; // Возврат к форме с ошибками
        }
        order.setId(id);
        orderService.save(order);
        return "redirect:/orders";
    }


    @PostMapping("/{id}/delete")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteById(id);
        return "redirect:/orders";
    }


}
