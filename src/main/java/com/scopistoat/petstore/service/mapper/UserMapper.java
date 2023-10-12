package com.scopistoat.petstore.service.mapper;

import com.scopistoat.petstore.model.User;
import com.scopistoat.petstore.model.dtos.UserCreationDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserMapper implements Function<UserCreationDTO, User> {
    @Override
    public User apply(UserCreationDTO userCreationDTO) {
        return new User(
                userCreationDTO.getFirstName(),
                userCreationDTO.getLastName(),
                userCreationDTO.getEmailAddress(),
                userCreationDTO.getBudget()
        );
    }
}
