package com.scopistoat.petstore.constant;

import com.scopistoat.petstore.model.Money;
import com.scopistoat.petstore.model.enums.Currency;

public class PetConstants {
    public static final int MAX_NUMBER_OF_PETS = 20;
    public static final int MAX_RATING = 10;
    public static final int MIN_RATING = 0;
    public static final Money MULTIPLIER_MONEY = new Money(1.0, Currency.USD);
}
