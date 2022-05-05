package sms.test;

import org.junit.Test;
import sms.SMSChecker;
import sms.model.SMS;

import java.util.HashMap;
import java.util.logging.Logger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestSMSChecker{

    final private static Logger logger = Logger.getLogger(TestSMSChecker.class.getName());

    @Test
    public void test1RegisterAllLowerCase() {
        HashMap<String, String> entryOne = new HashMap<>();
        SMS sms = new SMS();
        entryOne.put("Message", "register");
        assertTrue(SMSChecker.check(entryOne,sms));
        logger.info(" True, and considered as correct Message :) " +
                "\n ------------------------------------------------------------------------");
    }

    @Test
    public void test2RegisterMixedCase() {
        HashMap<String, String> entryTwo = new HashMap<>();
        SMS sms = new SMS();
        entryTwo.put("Message", "ReGiStEr");
        assertTrue(SMSChecker.check(entryTwo, sms));
        logger.info(" True, and considered as correct Message :) " +
                "\n ------------------------------------------------------------------------");
    }

    @Test
    public void test3RegisterSentenceCase() {
        HashMap<String, String> entryThree = new HashMap<>();
        SMS sms = new SMS();
        entryThree.put("Message", "Register");
        assertTrue(SMSChecker.check(entryThree, sms));
        logger.info(" True, and considered as correct Message :) " +
                "\n ------------------------------------------------------------------------");
    }

    @Test
    public void test4RegisterAllCaps() {
        HashMap<String, String> entryFour = new HashMap<>();
        SMS sms = new SMS();
        entryFour.put("Message", "REGISTER");
        assertTrue(SMSChecker.check(entryFour, sms));
        logger.info(" True, and considered as correct Message :) " +
                "\n ------------------------------------------------------------------------");
    }

    @Test
    public void test5RegisterNull() {
        HashMap<String, String> entryFive = new HashMap<>();
        SMS sms = new SMS();
        entryFive.put("Message", "");
        assertFalse(SMSChecker.check(entryFive, sms));
        logger.info(" False, Null." +
                "\n ------------------------------------------------------------------------");
    }

    @Test
    public void test6RegisterWhiteSpaceFront() {
        HashMap<String, String> entrySix = new HashMap<>();
        SMS sms = new SMS();
        entrySix.put("Message", " register");
        assertFalse(SMSChecker.check(entrySix, sms));
        logger.warning(" False, Incorrect usage due to whitespace at the front" +
                "\n ------------------------------------------------------------------------");
    }

    @Test
    public void test7RegisterWhiteSpaceBack() {
        HashMap<String, String> entrySeven = new HashMap<>();
        SMS sms = new SMS();
        entrySeven.put("Message", "register ");
        assertFalse(SMSChecker.check(entrySeven, sms));
        logger.warning(" False, Incorrect usage due to whitespace at the back" +
                "\n ------------------------------------------------------------------------");
    }

    @Test
    public void test8RegisterAddedLetters() {
        HashMap<String, String> entryEight = new HashMap<>();
        SMS sms = new SMS();
        entryEight.put("Message", "registers");
        assertFalse(SMSChecker.check(entryEight, sms));
        logger.warning(" False, Incorrect usage due to misspelled" +
                "\n ------------------------------------------------------------------------");
    }

    @Test
    public void test9RegisterJejemon() {
        HashMap<String, String> entryNine = new HashMap<>();
        SMS sms = new SMS();
        entryNine.put("Message", "r3G!sT3r");
        assertFalse(SMSChecker.check(entryNine, sms));
        logger.warning(" False, Incorrect usage due to misspelled" +
                "\n ------------------------------------------------------------------------");
    }

}
