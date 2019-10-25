package com.company.motoinventory.controller;

import com.company.motoinventory.model.Motorcycle;
import com.company.motoinventory.repository.MotorcycleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MotoController.class)
class MotoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    MotorcycleRepository repo;

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        reset(repo);
    }

    @Test
    void fetchAllMotorcycles() throws Exception {
        Motorcycle out = new Motorcycle(1, new BigDecimal("400.00"), "Vin", "Make", "Model", "Year", "Color");

        List<Motorcycle> motorcycleList = new ArrayList<>();
        motorcycleList.add(out);

        String outputJson = mapper.writeValueAsString(motorcycleList);

        when(repo.findAll()).thenReturn(motorcycleList);

        this.mockMvc.perform(get("/moto"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    void fetchMotorcycleByIdReturnWithJson() throws Exception {
        Motorcycle out = new Motorcycle(1, new BigDecimal("400.00"), "Vin", "Make", "Model", "Year", "Color");

        String outputJson = mapper.writeValueAsString(out);

        when(repo.findById(1)).thenReturn(java.util.Optional.of(out));

        this.mockMvc.perform(get("/moto/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));


    }

    @Test
    void fetchMotorcycleByIdReturn404() throws Exception {
        this.mockMvc.perform(get("/moto/1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void saveMotorcycle() throws Exception {
        Motorcycle in = new Motorcycle(null, new BigDecimal("400.00"), "Vin", "Make", "Model", "Year", "Color");

        String inputJson = mapper.writeValueAsString(in);

        Motorcycle out = new Motorcycle(1, new BigDecimal("400.00"), "Vin", "Make", "Model", "Year", "Color");

        String outputJson = mapper.writeValueAsString(out);

        when(repo.save(in)).thenReturn(out);

        this.mockMvc.perform(post("/moto")
        .contentType(MediaType.APPLICATION_JSON)
        .content(inputJson))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    void updateMotorcycle() throws Exception {
        Motorcycle updatedMoto = new Motorcycle(1, new BigDecimal("500.00"), "Vin", "Make", "Model", "Year", "Color");

        String inputJson = mapper.writeValueAsString(updatedMoto);

        this.mockMvc.perform(put("/moto/1")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void deleteMotorcycle() throws Exception {
        Motorcycle moto = new Motorcycle(1, new BigDecimal("500.00"), "Vin", "Make", "Model", "Year", "Color");

        this.mockMvc.perform(delete("/moto/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}