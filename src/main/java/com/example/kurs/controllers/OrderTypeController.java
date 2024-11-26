package com.example.kurs.controllers;

import com.example.kurs.models.OrderType;
import com.example.kurs.services.OrderTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order-types")
public class OrderTypeController {

    private final OrderTypeService orderTypeService;

    public OrderTypeController(OrderTypeService orderTypeService) {
        this.orderTypeService = orderTypeService;
    }

    @GetMapping
    public String listOrderTypes(Model model) {
        model.addAttribute("orderTypes", orderTypeService.findAll());
        return "orderTypes";
    }

    @GetMapping("/{id}")
    public String viewOrderType(@PathVariable Long id, Model model) {
        model.addAttribute("orderType", orderTypeService.findById(id));
        return "viewOrderType";
    }

    @PostMapping
    public String createOrderType(OrderType orderType) {
        orderTypeService.save(orderType);
        return "redirect:/order-types";
    }

    @PostMapping("/{id}/delete")
    public String deleteOrderType(@PathVariable Long id) {
        orderTypeService.deleteById(id);
        return "redirect:/order-types";
    }
}

