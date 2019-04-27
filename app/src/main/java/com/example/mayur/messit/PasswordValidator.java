package com.example.mayur.messit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
    Should not be null

    (?=.*[0-9]) a digit must occur at least once
    (?=.*[a-z]) a lower case letter must occur at least once
    (?=.*[A-Z]) an upper case letter must occur at least once
    (?=.*[@#$%^&+=]) a special character must occur at least once
    (?=\\S+$) no whitespace allowed in the entire string
    .{8, 40} at least 8 characters and at most 40 characters long

*/

public class PasswordValidator {

    private Pattern pattern;
    private Matcher matcher;

    private static final String PASSWORD_PATTERN = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,40}";

    public PasswordValidator() {
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }

    public boolean validate(final String password) {

        matcher = pattern.matcher(password);
        return password != null && matcher.matches();

    }
}
