package sms.test;

import org.junit.Test;
import sms.SMSChecker;

import java.util.HashMap;
import java.util.logging.Logger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestUser {

    final private static Logger logger = Logger.getLogger(TestSMSChecker.class.getName());

    @Test
    public void test1CorrectFormat() {
        HashMap<String, String> entryOne = new HashMap<>();
        entryOne.put("Message", "Marco Valmores, 1973-09-10, Marikina City");
        assertTrue(SMSChecker.checkUserInfo(entryOne));
        logger.info("Format is correct." +
                "\n ------------------------------------------------------------------------");
    }

    @Test
    public void test2WhiteSpaces() {
        HashMap<String, String> entryTwo = new HashMap<>();
        entryTwo.put("Message", "   Marco Valmores   ,   1973-09-10,    Marikina City");
        assertTrue(SMSChecker.checkUserInfo(entryTwo));
        logger.info("Format is acceptable." +
                "\n ------------------------------------------------------------------------");
    }

    @Test
    public void test3IncompleteInfo() {
        HashMap<String, String> entryThree = new HashMap<>();
        entryThree.put("Message", "Marco Valmores, 1973-09-10");
        assertFalse(SMSChecker.checkUserInfo(entryThree));
        logger.warning("Incomplete information." +
                "\n ------------------------------------------------------------------------");
    }

    @Test
    public void test4TooMuchComma() {
        HashMap<String, String> entryFour = new HashMap<>();
        entryFour.put("Message", "Marco Valmores, 1973-09-10, San Fernando, Pampanga");
        assertTrue(SMSChecker.checkUserInfo(entryFour));
        logger.warning("Incorrect formatting." +
                "\n ------------------------------------------------------------------------");
    }

    @Test
    public void test5IncompleteName() {
        HashMap<String, String> entryFive = new HashMap<>();
        entryFive.put("Message", "M, 1973-09-10, Marikina City");
        assertFalse(SMSChecker.checkUserInfo(entryFive));
        logger.warning("Incomplete information." +
                "\n ------------------------------------------------------------------------");
    }

    @Test
    public void test6NameHasNumbers() {
        HashMap<String, String> entrySix = new HashMap<>();
        entrySix.put("Message", "Marco09 Valmores, 1973-09-10, Marikina City");
        assertFalse(SMSChecker.checkUserInfo(entrySix));
        logger.warning("Invalid information." +
                "\n ------------------------------------------------------------------------");
    }

    @Test
    public void test7IncompleteBirthDate() {
        HashMap<String, String> entrySeven = new HashMap<>();
        entrySeven.put("Message", "Marco Valmores, 197-09-10, Marikina City");
        assertFalse(SMSChecker.checkUserInfo(entrySeven));
        logger.warning("Invalid format." +
                "\n ------------------------------------------------------------------------");
    }

    @Test
    public void test8NotDate() {
        HashMap<String, String> entryEight = new HashMap<>();
        entryEight.put("Message", "Marco Valmores, 1973-09-10-11, Marikina City");
        assertFalse(SMSChecker.checkUserInfo(entryEight));
        logger.warning("Invalid format." +
                "\n ------------------------------------------------------------------------");
    }

    @Test
    public void test9DateHasLetters() {
        HashMap<String, String> entryNine = new HashMap<>();
        entryNine.put("Message", "Marco Valmores, 1973-September-10, Marikina City");
        assertFalse(SMSChecker.checkUserInfo(entryNine));
        logger.warning("Invalid format." +
                "\n ------------------------------------------------------------------------");
    }

    @Test
    public void test10DateWithOtherDelimiter() {
        HashMap<String, String> entryTen = new HashMap<>();
        entryTen.put("Message", "Marco Valmores, 1973/09/10, Marikina City");
        assertFalse(SMSChecker.checkUserInfo(entryTen));
        logger.warning("Invalid format." +
                "\n ------------------------------------------------------------------------");
    }

    @Test
    public void test11BirthDateWrongFormat() {
        HashMap<String, String> entryEleven = new HashMap<>();
        entryEleven.put("Message", "Marco Valmores, 09-10-1973, Marikina City");
        assertFalse(SMSChecker.checkUserInfo(entryEleven));
        logger.warning("Invalid format." +
                "\n ------------------------------------------------------------------------");
    }

    @Test
    public void test12CurrentDateAsBirthDate() {
        HashMap<String, String> entryTwelve = new HashMap<>();
        entryTwelve.put("Message", "Marco Valmores, 2022-05-05, Marikina City");
        assertFalse(SMSChecker.checkUserInfo(entryTwelve));
        logger.warning("Invalid Date." +
                "\n ------------------------------------------------------------------------");
    }

    @Test
    public void test13FutureBirthDate() {
        HashMap<String, String> entryThirteen = new HashMap<>();
        entryThirteen.put("Message", "Marco Valmores, 2222-09-10, Marikina City");
        assertFalse(SMSChecker.checkUserInfo(entryThirteen));
        logger.warning("Invalid Date." +
                "\n ------------------------------------------------------------------------");

    }

    @Test
    public void test14InvalidAddress() {
        HashMap<String, String> entryFourteen = new HashMap<>();
        entryFourteen.put("Message", "Marco Valmores, 1973-09-10, M4r1k1n4 C1ty");
        assertFalse(SMSChecker.checkUserInfo(entryFourteen));
        logger.warning("Invalid address." +
                "\n ------------------------------------------------------------------------");
    }

}
