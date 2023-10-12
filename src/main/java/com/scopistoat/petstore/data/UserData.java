package com.scopistoat.petstore.data;

import com.scopistoat.petstore.model.enums.Currency;
import com.scopistoat.petstore.model.Money;
import com.scopistoat.petstore.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserData {
    private static final List<User> list = List.of(
            new User("First", "User", "first.user@mail.com", new Money(24.0, Currency.USD)),
            new User("Second", "User", "second.user@mail.com", new Money(1.0, Currency.USD)),
            new User("Third", "User", "third.user@mail.com", new Money(15.5, Currency.USD)),
            new User("Fourth", "User", "fourth.user@mail.com", new Money(7.25, Currency.USD)),
            new User("Fifth", "User", "fifth.user@mail.com", new Money(42.0, Currency.USD)),
            new User("Sixth", "User", "sixth.user@mail.com", new Money(3.75, Currency.USD)),
            new User("Seventh", "User", "seventh.user@mail.com", new Money(18, Currency.USD)),
            new User("Eighth", "User", "eighth.user@mail.com", new Money(5.5, Currency.USD)),
            new User("Ninth", "User", "ninth.user@mail.com", new Money(10.0, Currency.USD)),
            new User("Tenth", "User", "tenth.user@mail.com", new Money(30.0, Currency.USD))
    );

    public static List<User> userList = new ArrayList<>(list);
}
