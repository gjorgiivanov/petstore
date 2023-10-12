package com.scopistoat.petstore.web.graphql;

import com.scopistoat.petstore.model.dtos.BuyHistoryLogDTO;
import com.scopistoat.petstore.model.dtos.UserCreationDTO;
import com.scopistoat.petstore.model.dtos.UserDTO;
import com.scopistoat.petstore.service.IBuyHistoryLogService;
import com.scopistoat.petstore.service.IUserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserController {
    private final IUserService userService;
    private final IBuyHistoryLogService buyHistoryLogService;

    public UserController(IUserService userService, IBuyHistoryLogService buyHistoryLogService) {
        this.userService = userService;
        this.buyHistoryLogService = buyHistoryLogService;
    }

    @QueryMapping(name = "users")
    public List<UserDTO> listUsers() {
        return userService.findAll();
    }

    @MutationMapping
    public List<UserDTO> createUsers(@Argument List<UserCreationDTO> userCreationDTOs) {
        try {
            if (userCreationDTOs == null || userCreationDTOs.isEmpty()) {
                userService.createRandomUsers();
            } else {
                userService.createUsers(userCreationDTOs);
            }
            return userService.findAll();
        } catch (Exception e) {
            return null;
        }
    }

    @MutationMapping(name = "buy")
    public List<BuyHistoryLogDTO> buyPetsForUsers() {
        try {
            userService.buyPetsForUsers();
            return buyHistoryLogService.findAll();
        } catch (Exception e) {
            return null;
        }
    }
}
