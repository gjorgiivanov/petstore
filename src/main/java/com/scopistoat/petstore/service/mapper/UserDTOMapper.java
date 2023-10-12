package com.scopistoat.petstore.service.mapper;

import com.scopistoat.petstore.model.User;
import com.scopistoat.petstore.model.dtos.UserDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDTOMapper implements Function<User, UserDTO> {
    @Override
    public UserDTO apply(User user) {
        return new UserDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getEmailAddress(),
                user.getBudget()
        );
    }
}
