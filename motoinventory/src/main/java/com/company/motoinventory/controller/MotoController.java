package com.company.motoinventory.controller;

import com.company.motoinventory.model.Motorcycle;
import com.company.motoinventory.repository.MotorcycleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/moto")
public class MotoController {

    @Autowired
    MotorcycleRepository repo;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Motorcycle> fetchAllMotorcycles() {
        return repo.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Motorcycle fetchMotorcycleById(@PathVariable Integer id) throws EntityNotFoundException {
        Optional<Motorcycle> motorcycle = repo.findById(id);
        if (motorcycle.isPresent()) {
            return motorcycle.get();
        } else {
            throw new EntityNotFoundException("expected to get back a motorcycle");
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Motorcycle saveMotorcycle(@RequestBody Motorcycle motorcycle) {
        return repo.save(motorcycle);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateMotorcycle(@RequestBody Motorcycle motorcycle, @PathVariable Integer id) {
        motorcycle.setId(id);
        repo.save(motorcycle);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMotorcycle(@PathVariable Integer id) {
        repo.deleteById(id);
    }
	
}
