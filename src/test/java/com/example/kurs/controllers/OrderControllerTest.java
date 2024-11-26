package com.example.kurs.controllers;

import com.example.kurs.models.Currency;
import com.example.kurs.models.Order;
import com.example.kurs.models.OrderType;
import com.example.kurs.models.OrderVeriety;
import com.example.kurs.services.CurrencyService;
import com.example.kurs.services.OrderService;
import com.example.kurs.services.OrderTypeService;
import com.example.kurs.services.OrderVerietyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @MockBean
    private OrderTypeService orderTypeService;

    @MockBean
    private OrderVerietyService orderVerietyService;

    @MockBean
    private CurrencyService currencyService;

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void listOrders_ShouldReturnOrdersView() throws Exception {
        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(view().name("order"))
                .andExpect(model().attributeExists("orders"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void editOrderForm_ShouldReturnFormView() throws Exception {
        Order order = new Order();
        order.setId(1L);
        order.setTiker("AAPL");
        order.setNumber(1001);

        when(orderService.findById(1L)).thenReturn(order);
        when(orderTypeService.findAll()).thenReturn(List.of(new OrderType(1L, "Брокерская")));
        when(orderVerietyService.findAll()).thenReturn(List.of(new OrderVeriety(1L, "Покупка")));
        when(currencyService.findAll()).thenReturn(List.of(new Currency(1L, "Рубль", "RUB")));

        mockMvc.perform(get("/orders/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("form"))
                .andExpect(model().attributeExists("order"))
                .andExpect(model().attributeExists("orderTypes"))
                .andExpect(model().attributeExists("orderVerity"))
                .andExpect(model().attributeExists("orderCurrency"));
    }




}

