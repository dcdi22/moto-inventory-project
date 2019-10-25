package com.company.motoinventory.controller;

import com.company.motoinventory.model.Motorcycle;
import com.company.motoinventory.repository.MotorcycleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
    void fetchMotorcycleById() throws Exception {
        Motorcycle out = new Motorcycle(1, new BigDecimal("400.00"), "Vin", "Make", "Model", "Year", "Color");

        String outputJson = mapper.writeValueAsString(out);

        when(repo.findById(1)).thenReturn(java.util.Optional.of(out));

        this.mockMvc.perform(get("/moto/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));


    }

    @Test
    void saveMotorcycle() {
        /*
        	  id int not null auto_increment primary key,
    price decimal(7,2) not null,
    vin varchar(20) not null,
    make varchar(20) not null,
    model varchar(20) not null,
    year varchar(4) not null,
    color varchar(20) not null
         */
        Motorcycle in = new Motorcycle(null, new BigDecimal("400.00"), "Vin", "Make", "Model", "Year", "Color");

//        String inputJson =
    }

    @Test
    void updateMotorcycle() {
    }

    @Test
    void deleteMotorcycle() {
    }
}