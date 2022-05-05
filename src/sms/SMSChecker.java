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

        boolean result = false;

        String message = smsMap.get("Message");
        String[] userInfo = message.split(","); //separated by comma

        if (userInfo.length < 3) return result;

        String fullName = userInfo[0].trim();
        String birthDate = userInfo[1].trim();
        String address = "";
        for (int i = 2; i < userInfo.length; i++) {
            address += userInfo[i];
        }

        if (fullName.length() == 1) return result;

        if (fullName.split(" ").length < 2) return result;

        for(char fN : fullName.toCharArray()) {
            if (Character.isDigit(fN)) return result;
        }

        boolean birthDateIsValid = false;
        if (birthDate.length() != 10) return result;

        for (char bD : birthDate.toCharArray()) {
            if (Character.isLetter(bD)) return result;
        }

        if (!birthDate.contains("-")) return result;

        String[] birthDateInfo = birthDate.split("-");
        String year = birthDateInfo[0];
        String month = birthDateInfo[0];
        String day = birthDateInfo[0];

        if (birthDateInfo.length < 3) return result;

        LocalDate formattedBirthdate = null;
        try {
            formattedBirthdate = LocalDate.parse(birthDate);
        } catch (DateTimeParseException e) {
            return result;
        }

        if (formattedBirthdate.compareTo(LocalDate.now()) >= 0) return result;


        boolean addressIsValid = false;
        if (address.length() == 1) return result;

        for(char add : address.toCharArray()) {
            if (Character.isDigit(add)) return result;
        }

        result = true;
        return result;
    }
}

