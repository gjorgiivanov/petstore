package com.scopistoat.petstore.service;

import com.scopistoat.petstore.model.dtos.UserCreationDTO;
import com.scopistoat.petstore.model.dtos.UserDTO;

import java.util.List;

public interface IUserService {
    List<UserDTO> findAll();

    void createUsers(List<UserCreationDTO> userDTOs);

    void createRandomUsers();

    void buyPetsForUsers();
}
