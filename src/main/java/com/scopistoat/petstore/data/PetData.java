package com.scopistoat.petstore.data;

import com.scopistoat.petstore.model.*;
import com.scopistoat.petstore.model.enums.Type;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PetData {
    private static final List<Pet> list = List.of(
            new Dog("Max", Type.DOG, "Loyal and playful dog", LocalDate.of(2019, 10, 7), 3),
            new Cat("Bella", Type.CAT, "Independent and curious cat", LocalDate.of(2018, 6, 25)),
            new Dog("Charlie", Type.DOG, "Friendly and energetic dog", LocalDate.of(2020, 3, 15), 2),
            new Cat("Luna", Type.CAT, "Graceful and mysterious cat", LocalDate.of(2017, 8, 10)),
            new Dog("Cooper", Type.DOG, "Adventurous and outgoing dog", LocalDate.of(2016, 5, 12), 4),
            new Cat("Simba", Type.CAT, "Regal and confident cat", LocalDate.of(2021, 9, 5)),
            new Dog("Lucy", Type.DOG, "Affectionate and smart dog", LocalDate.of(2020, 7, 22), 5),
            new Cat("Milo", Type.CAT, "Playful and mischievous cat", LocalDate.of(2019, 4, 18)),
            new Dog("Rocky", Type.DOG, "Strong and agile dog", LocalDate.of(2018, 2, 9), 2),
            new Cat("Zoe", Type.CAT, "Gentle and friendly cat", LocalDate.of(2020, 11, 30)),
            new Dog("Daisy", Type.DOG, "Energetic and loyal dog", LocalDate.of(2017, 12, 3), 3),
            new Cat("Leo", Type.CAT, "Calm and observant cat", LocalDate.of(2019, 8, 14)),
            new Dog("Molly", Type.DOG, "Energetic and loving dog", LocalDate.of(2016, 9, 19), 6),
            new Cat("Nola", Type.CAT, "Adventurous and agile cat", LocalDate.of(2021, 5, 7)),
            new Dog("Bailey", Type.DOG, "Playful and friendly pup", LocalDate.of(2018, 4, 11), 1),
            new Cat("Oliver", Type.CAT, "Sly and mischievous cat", LocalDate.of(2020, 1, 28)),
            new Dog("Lily", Type.DOG, "Sweet and affectionate dog", LocalDate.of(2019, 3, 8), 4),
            new Cat("Lily", Type.CAT, "Sweet and gentle cat", LocalDate.of(2018, 7, 17)),
            new Dog("Ruby", Type.DOG, "Loving and cuddly dog", LocalDate.of(2017, 11, 21), 5),
            new Cat("Chloe", Type.CAT, "Curious and playful cat", LocalDate.of(2019, 2, 3))
    );

    public static List<Pet> petList = new ArrayList<>(list);
}
