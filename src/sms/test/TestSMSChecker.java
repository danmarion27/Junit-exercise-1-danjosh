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
        HashMap<String, String> entry = new HashMap<>();
        SMS sms = new SMS();
        entry.put("Message", "register");
        assertTrue(SMSChecker.check(entry,sms));
        //assertFalse(SMSChecker.check(entry, sms));
        logger.info(" True, and considered as correct Message :) " +
                "\n ------------------------------------------------------------------------");
    }

    @Test
    public void test2RegisterMixedCase() {
        HashMap<String, String> entry = new HashMap<>();
        SMS sms = new SMS();
        entry.put("Message", "ReGiStEr");
        assertTrue(SMSChecker.check(entry, sms));
        //assertFalse(SMSChecker.check(entry, sms));
        logger.info(" True, and considered as correct Message :) " +
                "\n ------------------------------------------------------------------------");
    }

    @Test
    public void test3RegisterSentenceCase() {
        HashMap<String, String> entry = new HashMap<>();
        SMS sms = new SMS();
        entry.put("Message", "Register");
        assertTrue(SMSChecker.check(entry, sms));
        //assertFalse(SMSChecker.check(entry, sms));
        logger.info(" True, and considered as correct Message :) " +
                "\n ------------------------------------------------------------------------");
    }

    @Test
    public void test4RegisterAllCaps() {
        HashMap<String, String> entry = new HashMap<>();
        SMS sms = new SMS();
        entry.put("Message", "REGISTER");
        assertTrue(SMSChecker.check(entry, sms));
        //assertFalse(SMSChecker.check(entry, sms));
        logger.info(" True, and considered as correct Message :) " +
                "\n ------------------------------------------------------------------------");
    }

    @Test
    public void test5RegisterNull() {
        HashMap<String, String> entry = new HashMap<>();
        SMS sms = new SMS();
        entry.put("Message", "");
        assertFalse(SMSChecker.check(entry, sms));
        //assertTrue(SMSChecker.check(entry, sms));
        logger.info(" False, Null." +
                "\n ------------------------------------------------------------------------");
    }

    @Test
    public void test6RegisterWhiteSpaceFront() {
        HashMap<String, String> entry = new HashMap<>();
        SMS sms = new SMS();
        entry.put("Message", " register");
        assertFalse(SMSChecker.check(entry, sms));
        //assertTrue(SMSChecker.check(entry, sms));
        logger.warning(" False, Incorrect usage due to whitespace at the front" +
                "\n ------------------------------------------------------------------------");
    }

    @Test
    public void test7RegisterWhiteSpaceBack() {
        HashMap<String, String> entry = new HashMap<>();
        SMS sms = new SMS();
        entry.put("Message", "register ");
        assertFalse(SMSChecker.check(entry, sms));
        //assertTrue(SMSChecker.check(entry, sms));
        logger.warning(" False, Incorrect usage due to whitespace at the back" +
                "\n ------------------------------------------------------------------------");
    }

    @Test
    public void test8RegisterAddedLetters() {
        HashMap<String, String> entry = new HashMap<>();
        SMS sms = new SMS();
        entry.put("Message", "registers");
        assertFalse(SMSChecker.check(entry, sms));
        //assertTrue(SMSChecker.check(entry, sms));
        logger.warning(" False, Incorrect usage due to misspelled" +
                "\n ------------------------------------------------------------------------");
    }

    @Test
    public void test9RegisterJejemon() {
        HashMap<String, String> entry = new HashMap<>();
        SMS sms = new SMS();
        entry.put("Message", "r3G!sT3r");
        assertFalse(SMSChecker.check(entry, sms));
        //assertTrue(SMSChecker.check(entry, sms));
        logger.warning(" False, Incorrect usage due to misspelled" +
                "\n ------------------------------------------------------------------------");
    }

}
