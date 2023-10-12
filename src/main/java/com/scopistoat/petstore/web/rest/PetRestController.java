package com.scopistoat.petstore.web.rest;

import com.scopistoat.petstore.model.dtos.PetCreationDTO;
import com.scopistoat.petstore.model.dtos.PetDTO;
import com.scopistoat.petstore.service.IPetService;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/pet")
public class PetRestController {
    private final IPetService petService;

    public PetRestController(IPetService petService) {
        this.petService = petService;
    }

    @GetMapping("/list-pets")
    public List<PetDTO> listPets() {
        return petService.findAll();
    }

    @PostMapping("/create-pets")
    public ResponseEntity<String> createPets(@RequestBody @Nullable List<PetCreationDTO> petCreationDTOS) {
        try {
            if (petCreationDTOS == null || petCreationDTOS.isEmpty()) {
                petService.createRandomPets();
            } else {
                petService.createPets(petCreationDTOS);
            }
            return ResponseEntity.ok("Pets created successfully.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Failed to create pets: " + e.getMessage());
        }
    }
}
