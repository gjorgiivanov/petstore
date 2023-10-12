package com.scopistoat.petstore.web.graphql;

import com.scopistoat.petstore.model.dtos.PetCreationDTO;
import com.scopistoat.petstore.model.dtos.PetDTO;
import com.scopistoat.petstore.service.IPetService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PetController {
    private final IPetService petService;

    public PetController(IPetService petService) {
        this.petService = petService;
    }

    @QueryMapping(name = "pets")
    public List<PetDTO> listPets() {
        return petService.findAll();
    }

    @MutationMapping
    public List<PetDTO> createPets(@Argument List<PetCreationDTO> petCreationDTOs) {
        try {
            if (petCreationDTOs == null || petCreationDTOs.isEmpty()) {
                petService.createRandomPets();
            } else {
                petService.createPets(petCreationDTOs);
            }
            return petService.findAll();
        } catch (Exception e) {
            return null;
        }
    }
}
