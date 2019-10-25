package com.company.motoedge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MotoedgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MotoedgeApplication.class, args);
	}

}
