package com.company.motoedge.controller;

import com.company.motoedge.service.MotorcycleService;
import com.company.motoedge.viewModels.MotorcycleInvoice;
import com.company.motoedge.viewModels.MotorcycleViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motorcycles")
public class MotorCycleController {
	@Autowired
	private MotorcycleService motorcycleService;

	/**
	 * Returns a list of motorcycles
	 * @return
	 */
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<MotorcycleViewModel> index() {
		return motorcycleService.fetchAllMotorcycles();
	}

	/**
	 * Takes in a motorcycle purchase and returns transaction data
	 * @return
	 */
	@PostMapping
	public MotorcycleInvoice handleTransaction() {
		return null;
	}
}
