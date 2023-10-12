package com.scopistoat.petstore.service.impl;

import com.scopistoat.petstore.model.BuyHistoryLog;
import com.scopistoat.petstore.model.Money;
import com.scopistoat.petstore.model.Pet;
import com.scopistoat.petstore.model.User;
import com.scopistoat.petstore.model.dtos.UserCreationDTO;
import com.scopistoat.petstore.model.dtos.UserDTO;
import com.scopistoat.petstore.repository.IBuyHistoryLogRepository;
import com.scopistoat.petstore.repository.IPetRepository;
import com.scopistoat.petstore.repository.IUserRepository;
import com.scopistoat.petstore.service.IUserService;
import com.scopistoat.petstore.service.mapper.UserDTOMapper;
import com.scopistoat.petstore.service.mapper.UserMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.scopistoat.petstore.constant.UserConstants.MAX_NUMBER_OF_USERS;
import static com.scopistoat.petstore.data.UserData.userList;

@Service
public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final IPetRepository petRepository;
    private final IBuyHistoryLogRepository buyHistoryLogRepository;

    private final UserMapper userMapper;
    private final UserDTOMapper userDTOMapper;

    public UserService(
            IUserRepository userRepository,
            IPetRepository petRepository,
            IBuyHistoryLogRepository buyHistoryLogRepository,
            UserMapper userMapper,
            UserDTOMapper userDTOMapper) {
        this.userRepository = userRepository;
        this.petRepository = petRepository;
        this.buyHistoryLogRepository = buyHistoryLogRepository;
        this.userMapper = userMapper;
        this.userDTOMapper = userDTOMapper;
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = users.stream().map(userDTOMapper).collect(Collectors.toList());

        return userDTOs;
    }

    @Override
    public void createUsers(List<UserCreationDTO> userCreationDTOS) {
        List<User> users = userRepository.findAll();

        int sizeOfExpectedList = users.size() + userCreationDTOS.size();
        if (sizeOfExpectedList > MAX_NUMBER_OF_USERS) {
            throw new IllegalStateException("You have exceeded the maximum allowed number of users("
                    + MAX_NUMBER_OF_USERS +
                    ") by " + (sizeOfExpectedList - MAX_NUMBER_OF_USERS));
        }

        List<User> usersToAdd = userCreationDTOS.stream()
                .map(userMapper)
                .collect(Collectors.toList());

        userRepository.saveAll(usersToAdd);
    }

    @Override
    public void createRandomUsers() {
        List<User> users = userRepository.findAll();

        if (users.size() >= MAX_NUMBER_OF_USERS) {
            throw new IllegalStateException("The maximum number of users(" + MAX_NUMBER_OF_USERS + ") has been reached");
        }

        Random random = new Random();
        int maxNumberOfRandomUsers = MAX_NUMBER_OF_USERS - users.size();
        int randomNumberOfUsers = random.nextInt(maxNumberOfRandomUsers) + 1; // [1, maxNumberOfRandomUsers]
        List<User> usersToAdd = new ArrayList<>(randomNumberOfUsers);

        for (int i = 0; i < randomNumberOfUsers; i++) {
            int randomIndexOfUserList = random.nextInt(userList.size()); // [0, userList.size() - 1]
            User removedUser = userList.remove(randomIndexOfUserList);
            usersToAdd.add(removedUser);
        }

        userRepository.saveAll(usersToAdd);
    }

    @Override
    public void buyPetsForUsers() {
        List<Pet> pets = petRepository.findAllByOwnerIsNullOrderByPrice();
        if (pets.isEmpty()) {
            throw new IllegalStateException("There are no pets for users to buy");
        }

        List<User> users = userRepository.findAll(Sort.by("budget_amount"));
        if (users.isEmpty()) {
            throw new IllegalStateException("There are no users to buy pets");
        }

        int numberOfSuccessfulBuyers = 0;
        int numberOfUnsuccessfulBuyers = 0;
        int petIndex = 0;

        // buy - this command should go over all the users and try to buy a pet of the store for each user
        // This algorithm ensures that the users with the smallest budget try to buy the pet with the lowest price
        for (User user : users) {
            if (petIndex == pets.size()) {
                numberOfUnsuccessfulBuyers++;
                continue;
            }

            Pet petToBuy = pets.get(petIndex);

            if (user.getBudget().getAmount() < petToBuy.getPrice().getAmount()) {
                numberOfUnsuccessfulBuyers++;
                continue;
            }
            if (!user.getBudget().getCurrency().equals(petToBuy.getPrice().getCurrency())) {
                numberOfUnsuccessfulBuyers++;
                continue;
            }
            Money newUserBudget = user.getBudget().subtract(petToBuy.getPrice());

            user.setBudget(newUserBudget);
            user.getPets().add(petToBuy);

            petToBuy.setOwner(user);

            userRepository.save(user);
            petRepository.save(petToBuy);

            System.out.println(petToBuy.successfullyBoughtMessage());

            numberOfSuccessfulBuyers++;
            petIndex++;
        }

        BuyHistoryLog buyHistoryLog = new BuyHistoryLog(LocalDateTime.now(), numberOfSuccessfulBuyers, numberOfUnsuccessfulBuyers);
        buyHistoryLogRepository.save(buyHistoryLog);
    }
}
