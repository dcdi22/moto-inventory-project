package com.company.motoedge.service;

import com.company.motoedge.util.MotorcycleClient;
import com.company.motoedge.viewModels.MotorcycleInvoice;
import com.company.motoedge.viewModels.MotorcycleViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class MotorcycleService {
	@Autowired
	private MotorcycleClient motorcycleClient;

	public List<MotorcycleViewModel> fetchAllMotorcycles() {
		return null;
	}

	public MotorcycleInvoice handleTransaction() {
		return null;
	}
}
