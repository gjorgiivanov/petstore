package com.scopistoat.petstore.service.impl;

import com.scopistoat.petstore.model.*;
import com.scopistoat.petstore.model.dtos.PetCreationDTO;
import com.scopistoat.petstore.model.dtos.PetDTO;
import com.scopistoat.petstore.repository.IPetRepository;
import com.scopistoat.petstore.service.IPetService;
import com.scopistoat.petstore.service.mapper.PetDTOMapper;
import com.scopistoat.petstore.service.mapper.PetMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.scopistoat.petstore.constant.PetConstants.*;
import static com.scopistoat.petstore.data.PetData.petList;

@Service
public class PetService implements IPetService {
    private final IPetRepository petRepository;
    private final PetDTOMapper petDTOMapper;
    private final PetMapper petMapper;

    public PetService(IPetRepository petRepository, PetDTOMapper petDTOMapper, PetMapper petMapper) {
        this.petRepository = petRepository;
        this.petDTOMapper = petDTOMapper;
        this.petMapper = petMapper;
    }

    @Override
    public List<PetDTO> findAll() {
        List<Pet> pets = petRepository.findAll();
        List<PetDTO> petDTOs = pets.stream().map(petDTOMapper).collect(Collectors.toList());

        return petDTOs;
    }

    @Override
    public void createPets(List<PetCreationDTO> petCreationDTOS) {
        List<Pet> pets = petRepository.findAll();

        int sizeOfExpectedList = pets.size() + petCreationDTOS.size();
        if (sizeOfExpectedList > MAX_NUMBER_OF_PETS) {
            throw new IllegalStateException("You have exceeded the maximum allowed number of pets("
                    + MAX_NUMBER_OF_PETS +
                    ") by " + (sizeOfExpectedList - MAX_NUMBER_OF_PETS));
        }

        List<Pet> petsToAdd = petCreationDTOS.stream()
                .map(petMapper)
                .collect(Collectors.toList());

        petRepository.saveAll(petsToAdd);
    }

    @Override
    public void createRandomPets() {
        List<Pet> pets = petRepository.findAll();

        if (pets.size() >= MAX_NUMBER_OF_PETS) {
            throw new IllegalStateException("The maximum number of pets(" + MAX_NUMBER_OF_PETS + ") has been reached");
        }

        Random random = new Random();
        int maxNumberOfRandomPets = MAX_NUMBER_OF_PETS - pets.size();
        int randomNumberOfPets = random.nextInt(maxNumberOfRandomPets) + 1; // [1, maxNumberOfRandomPets]
        List<Pet> petsToAdd = new ArrayList<>(randomNumberOfPets);

        for (int i = 0; i < randomNumberOfPets; i++) {
            int randomIndexOfPetList = random.nextInt(petList.size()); // [0, petList.size() - 1]
            Pet removedPet = petList.remove(randomIndexOfPetList);
            petsToAdd.add(removedPet);
        }

        petRepository.saveAll(petsToAdd);
    }
}
