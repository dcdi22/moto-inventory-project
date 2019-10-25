package com.company.motoedge.util;

import com.company.motoedge.viewModels.MotorcycleViewModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("moto-service")
public interface MotorcycleClient {
	@GetMapping("/motorcycles")
	List<MotorcycleViewModel> getMotorcycles();
}
