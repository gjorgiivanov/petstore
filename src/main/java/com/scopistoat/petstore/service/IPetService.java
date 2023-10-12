package com.scopistoat.petstore.service;

import com.scopistoat.petstore.model.dtos.PetCreationDTO;
import com.scopistoat.petstore.model.dtos.PetDTO;

import java.util.List;

public interface IPetService {
    List<PetDTO> findAll();

    void createPets(List<PetCreationDTO> petDTOs);

    void createRandomPets();
}
