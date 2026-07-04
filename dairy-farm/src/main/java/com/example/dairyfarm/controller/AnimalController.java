package com.example.dairyfarm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.dairyfarm.entity.Animal;
import com.example.dairyfarm.service.AnimalService;

@RestController
@RequestMapping("/api/animals")
@CrossOrigin(origins = "*")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    // Add Animal
    @PostMapping
    public Animal addAnimal(@RequestBody Animal animal) {
        return animalService.saveAnimal(animal);
    }

    // Get All Animals
    @GetMapping
    public List<Animal> getAllAnimals() {
        return animalService.getAllAnimals();
    }

    // Get Animal By ID
    @GetMapping("/{id}")
    public Animal getAnimalById(@PathVariable Long id) {
        return animalService.getAnimalById(id);
    }

    // Update Animal
    @PutMapping("/{id}")
    public Animal updateAnimal(@PathVariable Long id,
                               @RequestBody Animal animal) {

        return animalService.updateAnimal(id, animal);
    }

    // Delete Animal
    @DeleteMapping("/{id}")
    public String deleteAnimal(@PathVariable Long id) {

        return animalService.deleteAnimal(id);
    }

    // Search Animal By Tag Number
    @GetMapping("/tag/{tagNumber}")
    public Animal getAnimalByTagNumber(@PathVariable String tagNumber) {

        return animalService.getAnimalByTagNumber(tagNumber);
    }

}