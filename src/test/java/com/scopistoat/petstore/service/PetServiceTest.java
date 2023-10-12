package com.scopistoat.petstore.service;

import com.scopistoat.petstore.model.*;
import com.scopistoat.petstore.model.dtos.PetCreationDTO;
import com.scopistoat.petstore.model.dtos.PetDTO;
import com.scopistoat.petstore.model.enums.Currency;
import com.scopistoat.petstore.model.enums.Type;
import com.scopistoat.petstore.repository.IPetRepository;
import com.scopistoat.petstore.service.impl.PetService;
import com.scopistoat.petstore.service.mapper.PetDTOMapper;
import com.scopistoat.petstore.service.mapper.PetMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.scopistoat.petstore.constant.PetConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {
    @Mock
    private IPetRepository petRepository;

    private final PetDTOMapper petDTOMapper = new PetDTOMapper();
    private final PetMapper petMapper = new PetMapper();

    private IPetService underTest;

    @BeforeEach
    void setUp() {
        underTest = new PetService(petRepository, petDTOMapper, petMapper);
    }

    @Test
    void canFindAllPets() {
        // when
        underTest.findAll();

        //then
        verify(petRepository).findAll();
    }

    @Test
    public void canFindPets() {
        // given
        Pet p1 = new Dog(
                "Pet1",
                Type.DOG,
                "Description1",
                LocalDate.of(2019, 10, 7),
                5
        );
        Pet p2 = new Cat(
                "Pet2",
                Type.CAT,
                "Description2",
                LocalDate.of(2018, 6, 25)
        );
        User u1 = new User(
                "First",
                "User",
                "first.user@mail.com",
                new Money(24.0, Currency.USD)
        );
        p2.setOwner(u1);

        List<Pet> petList = new ArrayList<>();
        petList.add(p1);
        petList.add(p2);

        List<PetDTO> expectedList = petList.stream().map(petDTOMapper).collect(Collectors.toList());

        // when
        when(petRepository.findAll()).thenReturn(petList);
        List<PetDTO> result = underTest.findAll();

        // then
        assertEquals(result, expectedList);
    }

    @Test
    void canCreatePets() {
        // given
        List<PetCreationDTO> petCreationDTOS = new ArrayList<>();
        petCreationDTOS.add(new PetCreationDTO(
                "Pet1", Type.DOG,
                "Description1",
                LocalDate.of(2019, 10, 7),
                5
        ));
        petCreationDTOS.add(new PetCreationDTO(
                "Pet2", Type.CAT,
                "Description2",
                LocalDate.of(2018, 6, 25)
        ));

        // when
        underTest.createPets(petCreationDTOS);

        // then
        verify(petRepository).saveAll(anyList());
    }

    @Test
    public void willThrowExceedingLimitWhenCreatingPets() {
        // given
        List<PetCreationDTO> petDTOList = new ArrayList<>();
        for (int i = 0; i < MAX_NUMBER_OF_PETS + 1; i++) {
            petDTOList.add(new PetCreationDTO(
                    "Pet" + i,
                    Type.DOG,
                    "Description" + i,
                    LocalDate.now(),
                    5
            ));
        }

        // when
        // then
        assertThrows(IllegalStateException.class, () -> underTest.createPets(petDTOList));
        verify(petRepository, never()).saveAll(anyList());
    }

    @Test
    void canCreateRandomPets() {
        // when
        underTest.createRandomPets();

        // then
        verify(petRepository).saveAll(anyList());
    }

    @Test
    public void willThrowExceedingLimitWhenCreatingRandomPets() {
        // given
        List<Pet> petList = new ArrayList<>();
        for (int i = 0; i < MAX_NUMBER_OF_PETS; i++) {
            petList.add(new Cat(
                    "Pet" + i,
                    Type.CAT,
                    "Description" + i,
                    LocalDate.now()
            ));
        }

        // when
        when(petRepository.findAll()).thenReturn(petList);

        // then
        assertThrows(IllegalStateException.class, () -> underTest.createRandomPets());
        verify(petRepository, never()).saveAll(anyList());
    }

    @Test
    public void willThrowTypeNull() {
        // given
        List<PetCreationDTO> petCreationDTOS = new ArrayList<>();
        petCreationDTOS.add(new PetCreationDTO(
                "Pet",
                null,
                "Description",
                LocalDate.now(),
                5
        ));

        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> underTest.createPets(petCreationDTOS));
        verify(petRepository, never()).saveAll(anyList());
    }

    @Test
    public void willThrowRatingOutsideBounds() {
        // given
        List<PetCreationDTO> petCreationDTOListAbove = new ArrayList<>();
        List<PetCreationDTO> petCreationDTOListBelow = new ArrayList<>();
        petCreationDTOListAbove.add(new PetCreationDTO(
                "Pet",
                Type.DOG,
                "Description",
                LocalDate.now(),
                MAX_RATING + 1
        ));
        petCreationDTOListBelow.add(new PetCreationDTO(
                "Pet", Type.DOG,
                "Description",
                LocalDate.now(),
                MIN_RATING - 1
        ));

        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> underTest.createPets(petCreationDTOListAbove));
        assertThrows(IllegalArgumentException.class, () -> underTest.createPets(petCreationDTOListBelow));
        verify(petRepository, never()).saveAll(anyList());
    }
}