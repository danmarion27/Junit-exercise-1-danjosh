package sms.test;

import org.junit.Test;
import sms.SMSChecker;

import java.util.HashMap;
import java.util.logging.Logger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestUser extends SMSChecker {

    final private static Logger logger = Logger.getLogger(TestSMSChecker.class.getName());

    @Test
    public void test1CorrectFormat() {
        HashMap<String, String> entry = new HashMap<>();
        entry.put("Message", "Marco Valmores, 1973-09-10, Marikina City");
        assertTrue(SMSChecker.checkUserInfo(entry));
        logger.info("Format is correct." +
                "\n ------------------------------------------------------------------------");
    }

    @Test
    public void test2WhiteSpaces() {
        HashMap<String, String> entry = new HashMap<>();
        entry.put("Message", "   Marco Valmores   ,   1973-09-10,    Marikina City");
        assertTrue(SMSChecker.checkUserInfo(entry));
        logger.info("Format is acceptable." +
                "\n ------------------------------------------------------------------------");
    }

    @Test
    public void test3IncompleteInfo() {
        HashMap<String, String> entry = new HashMap<>();
        entry.put("Message", "Marco Valmores, 1973-09-10");
        assertFalse(SMSChecker.checkUserInfo(entry));
        logger.warning("Incomplete information." +
                "\n ------------------------------------------------------------------------");
    }

    @Test
    public void test4TooMuchComma() {
        HashMap<String, String> entry = new HashMap<>();
        entry.put("Message", "Marco Valmores, 1973-09-10, San Fernando, Pampanga");
        assertTrue(SMSChecker.checkUserInfo(entry));
        logger.warning("Incorrect formatting." +
                "\n ------------------------------------------------------------------------");
    }

    @Test
    public void test5IncompleteName() {
        HashMap<String, String> entryOne = new HashMap<>();
        entryOne.put("Message", "M, 1973-09-10, Marikina City");
        assertFalse(SMSChecker.checkUserInfo(entryOne));
        logger.warning("Incomplete information." +
                "\n ------------------------------------------------------------------------");
    }

    @Test
    public void test6NameHasNumbers() {
        HashMap<String, String> entryOne = new HashMap<>();
        entryOne.put("Message", "Marco09 Valmores, 1973-09-10, Marikina City");
        assertFalse(SMSChecker.checkUserInfo(entryOne));
        logger.warning("Invalid information." +
                "\n ------------------------------------------------------------------------");
    }

    @Test
    public void test7IncompleteBirthDate() {
        HashMap<String, String> entryTwo = new HashMap<>();
        entryTwo.put("Message", "Marco Valmores, 197-09-10, Marikina City");
        assertFalse(SMSChecker.checkUserInfo(entryTwo));
        logger.warning("Invalid format." +
                "\n ------------------------------------------------------------------------");
    }

    @Test
    public void test8NotDate() {
        HashMap<String, String> entryOne = new HashMap<>();
        entryOne.put("Message", "Marco Valmores, 1973-09-10-11, Marikina City");
        assertFalse(SMSChecker.checkUserInfo(entryOne));
        logger.warning("Invalid format." +
                "\n ------------------------------------------------------------------------");
    }

    @Test
    public void test9DateHasLetters() {
        HashMap<String, String> entryTwo = new HashMap<>();
        entryTwo.put("Message", "Marco Valmores, 1973-September-10, Marikina City");
        assertFalse(SMSChecker.checkUserInfo(entryTwo));
        logger.warning("Invalid format." +
                "\n ------------------------------------------------------------------------");
    }

    @Test
    public void test10DateWithOtherDelimiter() {
        HashMap<String, String> entry = new HashMap<>();
        entry.put("Message", "Marco Valmores, 1973/09/10, Marikina City");
        assertFalse(SMSChecker.checkUserInfo(entry));
        logger.warning("Invalid format." +
                "\n ------------------------------------------------------------------------");
    }

    @Test
    public void test11BirthDateWrongFormat() {
        HashMap<String, String> entry = new HashMap<>();
        entry.put("Message", "Marco Valmores, 09-10-1973, Marikina City");
        assertFalse(SMSChecker.checkUserInfo(entry));
        logger.warning("Invalid format." +
                "\n ------------------------------------------------------------------------");
    }

    @Test
    public void test12CurrentDateAsBirthDate() {
        HashMap<String, String> entry = new HashMap<>();
        entry.put("Message", "Marco Valmores, 2022-05-04, Marikina City");
        assertFalse(SMSChecker.checkUserInfo(entry));
        logger.warning("Invalid Date." +
                "\n ------------------------------------------------------------------------");
    }

    @Test
    public void test13FutureBirthDate() {
        HashMap<String, String> entry = new HashMap<>();
        entry.put("Message", "Marco Valmores, 2222-09-10, Marikina City");
        assertFalse(SMSChecker.checkUserInfo(entry));
        logger.warning("Invalid Date." +
                "\n ------------------------------------------------------------------------");

    }

    @Test
    public void test14InvalidAddress() {
        HashMap<String, String> entry = new HashMap<>();
        entry.put("Message", "Marco Valmores, 1973-09-10, M4r1k1n4 C1ty");
        assertFalse(SMSChecker.checkUserInfo(entry));
        logger.warning("Invalid address." +
                "\n ------------------------------------------------------------------------");
    }

}
