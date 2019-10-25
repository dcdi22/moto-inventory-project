package com.company.motoedge.controller;

import com.company.motoedge.service.MotorcycleService;
import com.company.motoedge.viewModels.MotorcycleViewModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MotorCycleController.class)
class MotorCycleControllerTest {
	@Autowired
	MockMvc mockMvc;

	@MockBean
	MotorcycleService motorcycleService;

	@Autowired
	private ObjectMapper objectMapper;
	/**
	 * Test that all motorcycles can be fetched
	 */
	@Test
	void fetches_all_motorcycles() throws Exception {
		List<MotorcycleViewModel> motos = new ArrayList<>();
		motos.add(makeMotorcycle());
		motos.add(makeMotorcycle());
		motos.add(makeMotorcycle());
		motos.add(makeMotorcycle());

		when(motorcycleService.fetchAllMotorcycles()).thenReturn(motos);

		mockMvc.perform(
			get("/motorcycles").contentType(MediaType.APPLICATION_JSON)
		).andExpect(status().isOk());

		Mockito.verify(motorcycleService, times(1)).fetchAllMotorcycles();
	}

 	// helpers
	private MotorcycleViewModel makeMotorcycle() {
		Random random = new Random();

		MotorcycleViewModel motorcycle = new MotorcycleViewModel(
			random.nextInt(49) + 1,
			new BigDecimal(4000.50),
			"34safds9safsf",
			"Rebel",
			"Honda",
			"2017",
			"red"
		);

		return motorcycle;
	}
}