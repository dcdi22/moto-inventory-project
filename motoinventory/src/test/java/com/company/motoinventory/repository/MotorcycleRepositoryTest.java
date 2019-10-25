package com.company.motoinventory.repository;

import com.company.motoinventory.model.Motorcycle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MotorcycleRepositoryTest {
	@Autowired
	private MotorcycleRepository motorcycleRepository;

	@BeforeEach
	void setUp() {
	}

	@Test //index
	void it_can_retrieve_all_motos_from_db() {
		motorcycleRepository.findAll().stream().forEach(motorcycle -> {
			motorcycleRepository.delete(motorcycle);
		});

		motorcycleRepository.save(makeMotorcycle());
		motorcycleRepository.save(makeMotorcycle());
		motorcycleRepository.save(makeMotorcycle());
		motorcycleRepository.save(makeMotorcycle());

		assertEquals(4, motorcycleRepository.findAll().size());
	}

	@Test //show
	void can_retrieve_a_single_moto() throws Exception {
		Motorcycle motorcycle = new Motorcycle(
			new BigDecimal(4000.50),
			"34safds9safsf",
			"Rebel",
			"Suzuki",
			"2017",
			"black"
		);

		motorcycle = motorcycleRepository.save(motorcycle);

		Optional<Motorcycle> result = motorcycleRepository.findById(motorcycle.getId());

		if(result.isPresent()) {
			assertEquals("black", result.get().getColor());
			assertEquals("Suzuki", result.get().getModel());
		} else {
			throw new Exception("expected to get my motorcycle");
		}
	}

	@Test // delete
	void test_that_it_deletes_everything_from_dabase() {
		motorcycleRepository.findAll().stream().forEach(motorcycle -> {
			motorcycleRepository.delete(motorcycle);
		});

		assertEquals(0 , motorcycleRepository.findAll().size());
	}

	@Test //create
	void test_it_can_persist_a_motorcycle() {
		Motorcycle motorcycle = makeMotorcycle();

		motorcycle = motorcycleRepository.save(motorcycle);

		assertNotNull(motorcycle.getId());
	}

	@Test
	void can_update_moto_details() throws Exception {
		Motorcycle motorcycle = makeMotorcycle();

		motorcycle = motorcycleRepository.save(motorcycle);

		// should be red
		assertEquals("red", motorcycle.getColor());

		motorcycle.setColor("turquoise");

		motorcycleRepository.save(motorcycle);

		// lets fetch the same moto
		Optional<Motorcycle> result = motorcycleRepository.findById(motorcycle.getId());

		if(result.isPresent()) {
			assertEquals("turquoise", result.get().getColor());
		} else {
			throw new Exception("expected to get my turquoise motorcycle");
		}

	}

	// -------------- helpers --------------------------//
	private Motorcycle makeMotorcycle() {
		Motorcycle motorcycle = new Motorcycle(
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