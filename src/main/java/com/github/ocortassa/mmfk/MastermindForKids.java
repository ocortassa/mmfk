package com.github.ocortassa.mmfk;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedHashSet;
import java.util.Set;

public class MastermindForKids {

    private final static int MAX_ATTEMPTS = 12;

    private final static Logger LOGGER = LogManager.getLogger(MastermindForKids.class);

    private int attemptCounter = 0;
    private String secret;
    private String lastAttempt;

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public int getMaxAttempts() {
        return MAX_ATTEMPTS;
    }

    public String hasWin(String attempt) {
        attemptCounter++;
        return isRightSequence(secret, attempt);
    }

    private String isRightSequence(String secret, String attempt) {
        if (secret.equalsIgnoreCase(attempt)) {
            LOGGER.info("Trovata sequenza vincente!");
            return "";
        }

        // Check black
        int blackCounter = 0;
        StringBuilder secretReminder = new StringBuilder();
        StringBuilder attemptReminder = new StringBuilder();
        for (int i = 0; i < attempt.length(); i++) {
            if (secret.toUpperCase().charAt(i) == attempt.toUpperCase().charAt(i)) {
                blackCounter++;
            } else {
                secretReminder.append(secret.toUpperCase().charAt(i));
                attemptReminder.append(attempt.toUpperCase().charAt(i));
            }
        }


        // TODO: SBAGLIATO! Devo poter ottenere 4 bianchi!!! - START

        // Drop duplicates
        String cleanAttempt = dropDuplicates(attemptReminder.toString());
        int whiteCounter = 0;
        for (int i = 0; i < cleanAttempt.length(); i++) {
            String color = "" + cleanAttempt.charAt(i);
            if (secretReminder.toString().toUpperCase().contains(color)) {
                whiteCounter++;
            }
        }

        // TODO: SBAGLIATO! Devo poter ottenere 4 bianchi!!! - END

        //outLn("Hai indovinato: " + rightColor + " colori al posto sbagliato e " + rightPos + " colori nel posto giusto");
        return "Hai indovinato: " + whiteCounter + " bianchi e " + blackCounter + " neri";
    }

    private String dropDuplicates(String attempt) {
        char[] chars = attempt.toCharArray();
        Set<Character> charSet = new LinkedHashSet<>();
        for (char c : chars) {
            charSet.add(c);
        }

        StringBuilder sb = new StringBuilder();
        for (Character character : charSet) {
            sb.append(character);
        }
        return sb.toString();
    }

}
