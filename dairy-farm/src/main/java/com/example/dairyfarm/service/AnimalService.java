package com.example.dairyfarm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dairyfarm.entity.Animal;
import com.example.dairyfarm.repository.AnimalRepository;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    // Add Animal
    public Animal saveAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    // Get All Animals
    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    // Get Animal By ID
    public Animal getAnimalById(Long id) {
        Optional<Animal> animal = animalRepository.findById(id);

        if (animal.isPresent()) {
            return animal.get();
        } else {
            return null;
        }
    }

    // Update Animal
    public Animal updateAnimal(Long id, Animal updatedAnimal) {

        Animal animal = animalRepository.findById(id).orElse(null);

        if (animal != null) {

            animal.setTagNumber(updatedAnimal.getTagNumber());
            animal.setAnimalType(updatedAnimal.getAnimalType());
            animal.setBreed(updatedAnimal.getBreed());
            animal.setGender(updatedAnimal.getGender());
            animal.setBirthDate(updatedAnimal.getBirthDate());
            animal.setPurchaseDate(updatedAnimal.getPurchaseDate());
            animal.setPurchasePrice(updatedAnimal.getPurchasePrice());
            animal.setWeight(updatedAnimal.getWeight());
            animal.setHealthStatus(updatedAnimal.getHealthStatus());
            animal.setStatus(updatedAnimal.getStatus());

            return animalRepository.save(animal);
        }

        return null;
    }

    // Delete Animal
    public String deleteAnimal(Long id) {

        if (animalRepository.existsById(id)) {
            animalRepository.deleteById(id);
            return "Animal deleted successfully.";
        }

        return "Animal not found.";
    }

    // Search Animal By Tag Number
    public Animal getAnimalByTagNumber(String tagNumber) {
        return animalRepository.findByTagNumber(tagNumber);
    }

}
