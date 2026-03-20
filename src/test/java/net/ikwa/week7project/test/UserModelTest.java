package net.ikwa.week7project.test;

import net.ikwa.week7project.model.UserModel;

import jakarta.validation.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserModelTest {

    private Validator validator;

    @BeforeEach
    void setup() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void validUser_shouldPass() {
        UserModel user = new UserModel("John", "john@mail.com", "USER");

        Set<ConstraintViolation<UserModel>> violations =
                validator.validate(user);

        assertTrue(violations.isEmpty());
    }

    @Test
    void invalidEmail_shouldFail() {
        UserModel user = new UserModel("John", "bad-email", "USER");

        Set<ConstraintViolation<UserModel>> violations =
                validator.validate(user);

        assertFalse(violations.isEmpty());
    }

    @Test
    void blankName_shouldFail() {
        UserModel user = new UserModel("", "john@mail.com", "USER");

        Set<ConstraintViolation<UserModel>> violations =
                validator.validate(user);

        assertFalse(violations.isEmpty());
    }
}