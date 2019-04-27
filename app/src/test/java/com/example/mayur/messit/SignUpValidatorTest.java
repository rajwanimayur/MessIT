package com.example.mayur.messit;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SignUpValidatorTest {

    //Correct Input
    @Test
    public void signUpValidator_CorrectDetails_ReturnsTrue() {
        assertFalse(SignUpValidator.validate("Vaishali Walia", "2018129", "8727065333"));
    }

    @Test
    public void signUpValidator_InCorrectName_ReturnsFalse() {
        assertFalse(SignUpValidator.validate("123", "MT2018129", "8727065333"));
    }

    @Test
    public void signUpValidator_InCorrectRollNo_ReturnsFalse() {
        assertFalse(SignUpValidator.validate("123", "018129", "8727065333"));
    }

    @Test
    public void signUpValidator_InCorrectContactNo_ReturnsFalse() {
        assertFalse(SignUpValidator.validate("123", "2018129", "4433"));
    }

}
