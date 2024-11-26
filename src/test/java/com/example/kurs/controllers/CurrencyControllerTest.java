package com.example.kurs.controllers;

import com.example.kurs.services.CurrencyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(CurrencyController.class)
class CurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private CurrencyService currencyService;


    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void testPage() throws Exception {
        mockMvc.perform(get("/currencies"))
                .andExpect(status().isOk());
    }

    
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testShowList() throws  Exception{

        mockMvc.perform(get("/currencies")).andExpect(view().name("currencies")).andExpect(model().attributeExists("currencies"));
    }
}
