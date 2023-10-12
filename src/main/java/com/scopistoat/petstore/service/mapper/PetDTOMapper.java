package com.scopistoat.petstore.service.mapper;

import com.scopistoat.petstore.model.Dog;
import com.scopistoat.petstore.model.Pet;
import com.scopistoat.petstore.model.User;
import com.scopistoat.petstore.model.dtos.PetDTO;
import com.scopistoat.petstore.model.dtos.UserDTO;
import com.scopistoat.petstore.model.enums.Type;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PetDTOMapper implements Function<Pet, PetDTO> {
    @Override
    public PetDTO apply(Pet pet) {
        UserDTO owner = null;


        User petOwner = pet.getOwner();
        if (petOwner != null) {
            owner = new UserDTO(
                    petOwner.getFirstName(),
                    pet.getOwner().getLastName(),
                    pet.getOwner().getEmailAddress(),
                    pet.getOwner().getBudget()
            );
        }

        if (pet.getType().equals(Type.DOG)) {
            return new PetDTO(
                    pet.getName(),
                    pet.getType(),
                    pet.getDescription(),
                    pet.getDateOfBirth(),
                    ((Dog) pet).getRating(),
                    pet.getPrice(),
                    owner
            );
        } else {
            return new PetDTO(
                    pet.getName(),
                    pet.getType(),
                    pet.getDescription(),
                    pet.getDateOfBirth(),
                    null,
                    pet.getPrice(),
                    owner
            );
        }
    }
}
