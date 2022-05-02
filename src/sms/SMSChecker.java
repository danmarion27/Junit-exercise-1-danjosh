package sms;

import sms.model.SMS;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.logging.Logger;


public class SMSChecker {

    final private static Logger logger = Logger.getLogger(SMSChecker.class.getName());

    public static boolean check(HashMap<String, String> smsMap, SMS sms) {
        boolean result = false;

        if (smsMap.size() == 1) {
            String message = (smsMap.get("Message")).toLowerCase();

            if (message.equals("register")) {
                result = true;
                sms.setSuccess(true);
            } else {
                sms.setSuccess(false);
            }
        }

        return result;
    }

    public static boolean checkUserInfo(HashMap<String, String> smsMap) {

        //user should send fullName, birthDate, location
        //should separate bt comma (,)

        boolean result = false;

        String message = smsMap.get("Message");
        String[] userInfo = message.split(","); //separated by comma

        //check kung 3 yung info na nilagay
        if (userInfo.length < 3) return result;

        String fullName = userInfo[0].trim();
        String birthDate = userInfo[1].trim();
        String address = "";
        for (int i = 2; i < userInfo.length; i++) {
            address += userInfo[i];
        }

        //fullName checker
        if (fullName.length() == 1) return result;

        //fullName atleast 2 char (1FN, 1LN)
        if (fullName.split(" ").length < 2) return result;

        //if name has number
        for(char fN : fullName.toCharArray()) {
            if (Character.isDigit(fN)) return result;
        }

        //check birthDate
        //yyy-MM-dd format
        boolean birthDateIsValid = false;
        if (birthDate.length() != 10) return result;

        //if birthDate has letter
        for (char bD : birthDate.toCharArray()) {
            if (Character.isLetter(bD)) return result;
        }

        //check if has separator (-)
        if (!birthDate.contains("-")) return result;

        String[] birthDateInfo = birthDate.split("-");
        String year = birthDateInfo[0];
        String month = birthDateInfo[0];
        String day = birthDateInfo[0];

        if (birthDateInfo.length < 3) return result;

        //localDate.parse bt default using yyyy-mm-dd, parse a date using iso local
        LocalDate formattedBirthdate = null;
        try {
            formattedBirthdate = LocalDate.parse(birthDate);
        } catch (DateTimeParseException e) {
            return result;
        }

        if (formattedBirthdate.compareTo(LocalDate.now()) >= 0) return result;


        //checks address
        boolean addressIsValid = false;
        if (address.length() == 1) return result;

        //check address if there's number
        for(char add : address.toCharArray()) {
            if (Character.isDigit(add)) return result;
        }

        result = true;
        return result;
    }
}

