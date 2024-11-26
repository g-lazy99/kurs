package com.example.kurs.controllers;

import com.example.kurs.models.OrderVeriety;
import com.example.kurs.services.OrderVerietyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order-verieties")
public class OrderVerietyController {

    private final OrderVerietyService orderVerietyService;

    public OrderVerietyController(OrderVerietyService orderVerietyService) {
        this.orderVerietyService = orderVerietyService;
    }

    @GetMapping
    public String listOrderVerieties(Model model) {
        model.addAttribute("orderVerieties", orderVerietyService.findAll());
        return "orderVerieties";
    }

    @GetMapping("/{id}")
    public String viewOrderVeriety(@PathVariable Long id, Model model) {
        model.addAttribute("orderVeriety", orderVerietyService.findById(id));
        return "viewOrderVeriety";
    }

    @PostMapping
    public String createOrderVeriety(OrderVeriety orderVeriety) {
        orderVerietyService.save(orderVeriety);
        return "redirect:/order-verieties";
    }

    @PostMapping("/{id}/delete")
    public String deleteOrderVeriety(@PathVariable Long id) {
        orderVerietyService.deleteById(id);
        return "redirect:/order-verieties";
    }
}
