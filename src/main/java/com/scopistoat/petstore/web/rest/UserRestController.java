package com.scopistoat.petstore.web.rest;

import com.scopistoat.petstore.model.dtos.UserCreationDTO;
import com.scopistoat.petstore.model.dtos.UserDTO;
import com.scopistoat.petstore.service.IUserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/user")
public class UserRestController {
    private final IUserService userService;

    public UserRestController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list-users")
    public List<UserDTO> listUsers() {
        return userService.findAll();
    }

    @PostMapping("/create-users")
    public ResponseEntity<String> createUsers(@RequestBody @Nullable List<UserCreationDTO> userCreationDTOS) {
        try {
            if (userCreationDTOS == null || userCreationDTOS.isEmpty()) {
                userService.createRandomUsers();
            } else {
                userService.createUsers(userCreationDTOS);
            }
            return ResponseEntity.ok("Users created successfully.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Failed to create users: " + e.getMessage());
        }
    }

    @GetMapping(path = "/buy")
    public ResponseEntity<String> buyPetsForUsers(HttpServletResponse response) {
        try {
            userService.buyPetsForUsers();
            response.sendRedirect("/api/history-log");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Failed to buy pets: " + e.getMessage());
        }
        return null;
    }
}
