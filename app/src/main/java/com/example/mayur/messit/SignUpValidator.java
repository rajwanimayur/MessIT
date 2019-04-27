package com.example.mayur.messit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpValidator {

    //rollno,clgemailid,foodpreference, contactno, password

    private static Pattern name_pattern;
    private static Pattern roll_no_pattern;
    private static Pattern contact_no_pattern;
    //private Matcher matcher;

    private static final String NAME_PATTERN = "^[a-zA-Z\\s]*$"; //allow alphabets and space
    private static final String ROLL_NO_PATTERN = "[0-9]{7}";  //check it
    private static final String CONTACT_NO_PATTERN = "^(?:(?:\\+|0{0,2})91(\\S*[\\ -]\\S*)?|[0]?)?[789]\\d{9}|(\\d[ -]?){10}\\d$";

    public SignUpValidator() {
        name_pattern = Pattern.compile(NAME_PATTERN);
        roll_no_pattern = Pattern.compile(ROLL_NO_PATTERN);
        contact_no_pattern = Pattern.compile(CONTACT_NO_PATTERN);
    }

    public static boolean validate(final String name, final String roll_no, final String contact_no) {

        return (name_pattern!=null && name_pattern.matcher(name).matches())
                && (roll_no_pattern!=null && roll_no_pattern.matcher(roll_no).matches())
                && (contact_no_pattern!=null && contact_no_pattern.matcher(contact_no).matches());
    }
}
