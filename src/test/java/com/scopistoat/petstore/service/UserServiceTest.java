package com.scopistoat.petstore.service;

import com.scopistoat.petstore.model.*;
import com.scopistoat.petstore.model.dtos.UserCreationDTO;
import com.scopistoat.petstore.model.dtos.UserDTO;
import com.scopistoat.petstore.model.enums.Currency;
import com.scopistoat.petstore.model.enums.Type;
import com.scopistoat.petstore.repository.IBuyHistoryLogRepository;
import com.scopistoat.petstore.repository.IPetRepository;
import com.scopistoat.petstore.repository.IUserRepository;
import com.scopistoat.petstore.service.impl.UserService;
import com.scopistoat.petstore.service.mapper.UserDTOMapper;
import com.scopistoat.petstore.service.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.scopistoat.petstore.constant.UserConstants.MAX_NUMBER_OF_USERS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private IUserRepository userRepository;
    @Mock
    private IPetRepository petRepository;
    @Mock
    private IBuyHistoryLogRepository buyHistoryLogRepository;

    private final UserDTOMapper userDTOMapper = new UserDTOMapper();
    private final UserMapper userMapper = new UserMapper();

    private IUserService underTest;


    @BeforeEach
    void setUp() {
        underTest = new UserService(
                userRepository,
                petRepository,
                buyHistoryLogRepository,
                userMapper,
                userDTOMapper
        );
    }

    @Test
    void canFindAllUsers() {
        // when
        underTest.findAll();

        //then
        verify(userRepository).findAll();
    }

    @Test
    public void canFindUsers() {
        // given
        List<User> userList = new ArrayList<>();
        userList.add(new User(
                "First",
                "User",
                "first.user@mail.com",
                new Money(24.0, Currency.USD)
        ));
        userList.add(new User(
                "Second",
                "User",
                "second.user@mail.com",
                new Money(1.0, Currency.USD)
        ));

        List<UserDTO> expectedList = userList.stream().map(userDTOMapper).collect(Collectors.toList());

        // when
        when(userRepository.findAll()).thenReturn(userList);
        List<UserDTO> result = underTest.findAll();

        // then
        assertEquals(result, expectedList);
    }

    @Test
    void canCreateUsers() {
        // given
        List<UserCreationDTO> userCreationDTOS = new ArrayList<>();
        userCreationDTOS.add(new UserCreationDTO(
                "First",
                "User",
                "first.user@mail.com",
                new Money(24.0, Currency.USD)
        ));
        userCreationDTOS.add(new UserCreationDTO(
                "Second",
                "User",
                "second.user@mail.com",
                new Money(1.0, Currency.USD)
        ));

        // when
        underTest.createUsers(userCreationDTOS);

        // then
        verify(userRepository).saveAll(anyList());
    }

    @Test
    public void willThrowExceedingLimitWhenCreatingUsers() {
        // given
        List<UserCreationDTO> userCreationDTOS = new ArrayList<>();
        for (int i = 0; i < MAX_NUMBER_OF_USERS + 1; i++) {
            userCreationDTOS.add(new UserCreationDTO(
                    "User" + i,
                    "Name" + i,
                    "user" + i + "@mail.com",
                    new Money(i * 2.4, Currency.USD)
            ));
        }

        // when
        // then
        assertThrows(IllegalStateException.class, () -> underTest.createUsers(userCreationDTOS));
        verify(userRepository, never()).saveAll(anyList());
    }

    @Test
    void canCreateRandomUsers() {
        // when
        underTest.createRandomUsers();

        // then
        verify(userRepository).saveAll(anyList());
    }

    @Test
    public void willThrowExceedingLimitWhenCreatingRandomUsers() {
        // given
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < MAX_NUMBER_OF_USERS; i++) {
            userList.add(new User(
                    "User" + i,
                    "Name" + i,
                    "user" + i + "@mail.com",
                    new Money(i * 2.4, Currency.USD)
            ));
        }

        // when
        when(userRepository.findAll()).thenReturn(userList);

        // then
        assertThrows(IllegalStateException.class, () -> underTest.createRandomUsers());
        verify(userRepository, never()).saveAll(anyList());
    }

    @Test
    public void canSuccessfullyBuyPetsForUsers() {
        // given
        User u1 = new User(
                "First",
                "User",
                "first.user@mail.com",
                new Money(10.0, Currency.USD)
        );
        User u2 = new User(
                "Second",
                "User",
                "second.user@mail.com",
                new Money(25.0, Currency.USD)
        );
        List<User> userList = new ArrayList<>();
        userList.add(u1);
        userList.add(u2);

        Dog p1 = new Dog(
                "Pet1",
                Type.DOG,
                "Description1",
                LocalDate.of(2019, 10, 7),
                7
        );
        Cat p2 = new Cat(
                "Pet2",
                Type.CAT,
                "Description2",
                LocalDate.of(2018, 6, 25)
        );
        List<Pet> petList = new ArrayList<>();
        petList.add(p2);
        petList.add(p1);

        // when
        when(userRepository.findAll(Sort.by("budget_amount"))).thenReturn(userList);
        when(petRepository.findAllByOwnerIsNullOrderByPrice()).thenReturn(petList);

        // then
        underTest.buyPetsForUsers();

        assertEquals(1, u1.getPets().size());
        assertEquals(1, u2.getPets().size());
        assertNotNull(p1.getOwner());
        assertNotNull(p2.getOwner());
        verify(buyHistoryLogRepository).save(any(BuyHistoryLog.class));
    }

    @Test
    public void canNotSuccessfullyBuyPetsForUsers() {
        // given
        User u1 = new User(
                "First",
                "User",
                "first.user@mail.com",
                new Money(2.0, Currency.USD)
        );
        User u2 = new User(
                "Second",
                "User",
                "second.user@mail.com",
                new Money(1.0, Currency.USD)
        );
        List<User> userList = new ArrayList<>();
        userList.add(u1);
        userList.add(u2);

        Dog p1 = new Dog(
                "Pet1",
                Type.DOG,
                "Description1",
                LocalDate.of(2019, 10, 7),
                5
        );
        Cat p2 = new Cat(
                "Pet2",
                Type.CAT,
                "Description2",
                LocalDate.of(2018, 6, 25)
        );
        List<Pet> petList = new ArrayList<>();
        petList.add(p1);
        petList.add(p2);

        // when
        when(userRepository.findAll(Sort.by("budget_amount"))).thenReturn(userList);
        when(petRepository.findAllByOwnerIsNullOrderByPrice()).thenReturn(petList);

        // then
        underTest.buyPetsForUsers();

        assertEquals(0, u1.getPets().size());
        assertEquals(0, u2.getPets().size());
        assertNull(p1.getOwner());
        assertNull(p2.getOwner());
        verify(buyHistoryLogRepository).save(any(BuyHistoryLog.class));
    }

    @Test
    public void willThrowNoUsers() {
        // given
        List<User> userList = new ArrayList<>();

        Cat p1 = new Cat(
                "Pet2",
                Type.CAT,
                "Description2",
                LocalDate.of(2018, 6, 25)
        );
        List<Pet> petList = new ArrayList<>();
        petList.add(p1);


        // when
        when(userRepository.findAll(Sort.by("budget_amount"))).thenReturn(userList);
        when(petRepository.findAllByOwnerIsNullOrderByPrice()).thenReturn(petList);

        // then
        assertThrows(IllegalStateException.class, () -> underTest.buyPetsForUsers());
        verify(buyHistoryLogRepository, never()).save(any(BuyHistoryLog.class));
    }

    @Test
    public void willThrowNoPets() {
        // given
        List<Pet> petList = new ArrayList<>();

        // when
        when(petRepository.findAllByOwnerIsNullOrderByPrice()).thenReturn(petList);

        // then
        assertThrows(IllegalStateException.class, () -> underTest.buyPetsForUsers());
        verify(buyHistoryLogRepository, never()).save(any(BuyHistoryLog.class));
    }

    @Test
    public void canSuccessfullyBuyPetsForUsersAvoidingDifferentCurrenciesAndMoreUsersThenPetsToBuy() {
        // given
        User u1 = new User(
                "First",
                "User",
                "first.user@mail.com",
                new Money(10.0, Currency.USD)
        );
        User u2 = new User(
                "Third",
                "User",
                "third.user@mail.com",
                new Money(25.0, Currency.EUR)
        );
        User u3 = new User(
                "Second",
                "User",
                "second.user@mail.com",
                new Money(25.0, Currency.USD)
        );
        User u4 = new User(
                "Forth",
                "User",
                "forth.user@mail.com",
                new Money(25.0, Currency.USD)
        );
        List<User> userList = new ArrayList<>();
        userList.add(u1);
        userList.add(u2);
        userList.add(u3);
        userList.add(u4);

        Dog p1 = new Dog(
                "Pet1",
                Type.DOG,
                "Description1",
                LocalDate.of(2019, 10, 7),
                7
        );
        Cat p2 = new Cat(
                "Pet2",
                Type.CAT,
                "Description2",
                LocalDate.of(2018, 6, 25)
        );
        List<Pet> petList = new ArrayList<>();
        petList.add(p2);
        petList.add(p1);

        // when
        when(userRepository.findAll(Sort.by("budget_amount"))).thenReturn(userList);
        when(petRepository.findAllByOwnerIsNullOrderByPrice()).thenReturn(petList);

        // then
        underTest.buyPetsForUsers();

        assertEquals(1, u1.getPets().size());
        assertEquals(0, u2.getPets().size());
        assertEquals(1, u3.getPets().size());
        assertEquals(0, u4.getPets().size());
        assertNotNull(p1.getOwner());
        assertNotNull(p2.getOwner());
        verify(buyHistoryLogRepository).save(any(BuyHistoryLog.class));
    }
}