package com.salmon.test.page_objects.gui;


import com.salmon.test.enums.PermittedCharacters;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public final class RandomGenerator {

    private Logger log = LogManager.getLogger(RandomGenerator.class.getName());
    public static String random(Integer length, PermittedCharacters permittedCharacters) {
        String randomString = null;
        if (PermittedCharacters.ALPHABETS.equals(permittedCharacters)) {
            randomString = randomString(length);
        } else if (PermittedCharacters.NUMERIC.equals(permittedCharacters)) {
            randomString = randomInteger(length);
        } else if (PermittedCharacters.ALPHANUMERIC.equals(permittedCharacters)) {
            randomString = randomAlphanumeric(length);
        } else if (PermittedCharacters.ANY_CHARACTERS.equals(permittedCharacters)) {
            randomString = randomAsciiCharacters(length);
        } else if (PermittedCharacters.ANY_CHARACTERS_SUPPORTS_MULTILINGUAL.equals(permittedCharacters)) {
            randomString = randomAsciiCharacters(length);
        }
        return randomString;
    }

    /**
     * Generates random Number.
     *
     * @param length length of random number to be generated
     */
    public static String randomInteger(Integer length) {
        return RandomStringUtils.randomNumeric(length);
    }

    /**
     * Generates random String.
     *
     * @param length length of random characters to be generated
     */
    private static String randomString(Integer length) {
        return RandomStringUtils.random(length, true, false);
    }

    /**
     * Generates random alphanumeric String.
     *
     * @param length length of random alphanumeric characters to be generated
     */
    private static String randomAlphanumeric(Integer length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    /**
     * Generates random alphabetic String.
     *
     * @param length length of random alphabetic characters to be generated
     */
    public static String randomAlphabetic(Integer length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    /**
     * Generates random emailaddress for @example.com domain  in lower case
     *
     * @param length length of random alphanumeric characters to be generated for the local part of email address
     */
    public static String randomEmailAddress(Integer length) {
        String email = randomAlphanumeric(length) + "@example.com";
        return email.toLowerCase();
    }

    /**
     * Generates random gender in short text form "M" , "F" , "U"
     * M = Male , F = Female , U = Unspecified
     */
    public static String randomGenderShortText() {
        List<String> gender = new LinkedList<>();
        gender.add("M");
        gender.add("F");
        gender.add("U");
        Random rand = new Random();
        int choice = rand.nextInt(gender.size());
        return gender.get(choice);
    }

    /**
     * Generates random gender in full text form
     * Male , Female , Unspecified
     */
    public static String randomGenderFullText() {
        List<String> gender = new LinkedList<>();
        gender.add("Male");
        gender.add("Female");
        gender.add("Unspecified");
        Random rand = new Random();
        int choice = rand.nextInt(gender.size());
        return gender.get(choice);
    }

    /**
     * Generates random plus or minus
     * "-" , "+"
     */

    public static String randomPlusOrMinus() {
        List<String> item = new LinkedList<>();
        item.add("-");
        item.add("+");

        Random rand = new Random();
        int choice = rand.nextInt(item.size());
        return item.get(choice);
    }


    private static String randomAsciiCharacters(Integer characterAmount) {
        return RandomStringUtils.random(characterAmount, 32, 127, false, false);
    }

    //Gets current time in the format below. Ex - 010220170205
    public static String currentTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
        return sdf.format(date);
    }
}