package com.scopistoat.petstore.service.mapper;

import com.scopistoat.petstore.model.Cat;
import com.scopistoat.petstore.model.Dog;
import com.scopistoat.petstore.model.Pet;
import com.scopistoat.petstore.model.dtos.PetCreationDTO;
import com.scopistoat.petstore.model.enums.Type;
import org.springframework.stereotype.Service;

import java.util.function.Function;

import static com.scopistoat.petstore.constant.PetConstants.MAX_RATING;
import static com.scopistoat.petstore.constant.PetConstants.MIN_RATING;

@Service
public class PetMapper implements Function<PetCreationDTO, Pet> {
    @Override
    public Pet apply(PetCreationDTO petCreationDTO) {
        if (petCreationDTO.getType() == null) {
            throw new IllegalArgumentException("Type field should not be null");
        }

        if (petCreationDTO.getType().equals(Type.CAT)) {
            return new Cat(
                    petCreationDTO.getName(),
                    petCreationDTO.getType(),
                    petCreationDTO.getDescription(),
                    petCreationDTO.getDateOfBirth()
            );
        } else {
            if (petCreationDTO.getRating() < MIN_RATING || petCreationDTO.getRating() > MAX_RATING) {
                throw new IllegalArgumentException("Rating needs to be between " + MIN_RATING + " and " + MAX_RATING);
            }
            return new Dog(
                    petCreationDTO.getName(),
                    petCreationDTO.getType(),
                    petCreationDTO.getDescription(),
                    petCreationDTO.getDateOfBirth(),
                    petCreationDTO.getRating()
            );
        }
    }
}
