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
        String secret = "";
        try {
            if (args.length == 0) {
                secret = cli.readSecret();
                for (int j = 0; j < 50; j++) {
                    cli.outLn("");
                }
            } else {
                secret = args[0];
            }
            cli.setSecret(secret);
            cli.outLn("Sequenza impostata, hai " + cli.getMaxAttempts() + " tentativi!\nSi comincia!");
            cli.outLn("");

            for (int i = 0; i < cli.getMaxAttempts(); i++) {
                cli.outLn("");
                cli.out("Tentativo " + (i + 1) + ", ");
                String attempt = cli.readAttempt();
                String result = cli.play(attempt);
                if (result == null || result.isEmpty()) {
                    cli.outLn("HAI VINTO!!!!");
                    break;
                } else {
                    cli.outLn("Peccato, non hai indovinato entro i " + cli.getMaxAttempts() + " tentativi previsti.\nLa sequenza giusta era: " + secret);
                }
            }   //  end game
        } catch (Exception e) {
            cli.outError(e.getMessage());
            LOGGER.error(e.getMessage(), e);
        }
    }

    public void setSecret(String secret) {
        mmfk.setSecret(secret);
    }

    public int getMaxAttempts() {
        return mmfk.getMaxAttempts();
    }

    public String play(String attempt) {
        return mmfk.hasWin(attempt);
    }

    public String readSecret() {
        String secret;
        out("Inserisci sequenza segreta (R = Rosso, V = Verde, B = Blu, G = Giallo): ");
        Console console = System.console();
        if (console != null) {
            secret = "" + Arrays.toString(console.readPassword());
        } else {
            Scanner in = new Scanner(System.in);
            try {
                secret = in.next().trim();
            } finally {
                in.close();
            }
        }
        return secret;
    }

    public String readAttempt() {
        Scanner in = new Scanner(System.in);
        String value;
        try {
            out("vai: ");
            value = in.next().trim();
        } finally {
            in.close();
        }
        return value;
    }

    public void out(String msg) {
        System.out.print(msg);
    }

    public void outLn(String msg) {
        System.out.println(msg);
    }

    public void outError(String msg) {
        System.err.println(msg);
        LOGGER.error(msg);
    }

}
