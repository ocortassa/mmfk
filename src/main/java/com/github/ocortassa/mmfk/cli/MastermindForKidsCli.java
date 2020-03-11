package com.github.ocortassa.mmfk.cli;

import com.github.ocortassa.mmfk.MastermindForKids;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Console;
import java.util.Arrays;
import java.util.Scanner;

public class MastermindForKidsCli {

    private final static Logger LOGGER = LogManager.getLogger(MastermindForKidsCli.class);

    private MastermindForKids mmfk = new MastermindForKids();

    public static void main(String[] args) {
        MastermindForKidsCli cli = new MastermindForKidsCli();
        cli.play();
    }

    public void play() {
        outLn("");
        boolean gameCompleted = false;
        try {

            String secret = readSecret();
            for (int j = 0; j < 50; j++) {
                outLn("");
            }
            mmfk.setSecret(secret);
            outLn("Sequenza impostata, hai " + mmfk.getMaxAttempts() + " tentativi!\nSi comincia!");

            for (int i = 0; i < mmfk.getMaxAttempts(); i++) {
                out("Tentativo " + (i + 1) + ", ");
                String attempt = readAttempt();
                String result = mmfk.hasWin(attempt);
                if (result != null && result.isEmpty()) {
                    gameCompleted = true;
                    break;
                } else {
                    outLn(result);
                    //outLn("Peccato, non hai indovinato. Riprova!");
                }
            }   //  end game

            if (gameCompleted) {
                outLn("HAI VINTO!!!!");
            } else {
                outLn("Peccato, non hai indovinato entro i " + mmfk.getMaxAttempts() + " tentativi previsti.\nLa sequenza giusta era: " + secret);
            }
            outLn("");

        } catch (Exception e) {
            logError(e.getMessage());
        }
    }

    private String readSecret() {
        String secret;
        out("Inserisci sequenza segreta (R = Rosso, V = Verde, B = Blu, G = Giallo): ");
        Console console = System.console();
        if (console != null) {
            secret = "" + Arrays.toString(console.readPassword());
        } else {
            Scanner in = new Scanner(System.in);
            secret = in.next().trim();
        }
        return secret;
    }

    private String readAttempt() {
        Scanner in = new Scanner(System.in);
        out("vai: ");
        return in.next().trim();
    }

    private void out(String msg) {
        System.out.print(msg);
    }

    private void outLn(String msg) {
        System.out.println(msg);
    }

    /*private void logInfo(String msg) {
        System.out.println(msg);
        LOGGER.info(msg);
    }*/

    private void logError(String msg) {
        System.err.println(msg);
        LOGGER.error(msg);
    }

}
