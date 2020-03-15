package com.github.ocortassa.mmfk.test;

import com.github.ocortassa.mmfk.cli.MastermindForKidsCli;
import org.junit.*;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MastermindForKidsCliTest {

    @Rule
    public final TextFromStandardInputStream systemInMock = TextFromStandardInputStream.emptyStandardInputStream();

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    private void resetBuffers() {
        outContent.reset();
        errContent.reset();
    }

    @Test
    public void doPlayTest() {
        MastermindForKidsCli cli = new MastermindForKidsCli();
        Assert.assertEquals(12, cli.getMaxAttempts());
        cli.setSecret("RRRR");
        Assert.assertNotEquals(cli.play("RRRG"), "");
        Assert.assertEquals(cli.play("RRRR"), "");
    }

    @Test
    public void doReadSecretTest() {
        MastermindForKidsCli cli = new MastermindForKidsCli();
        systemInMock.provideLines("RRRR");
        Assert.assertNotEquals("RRRG".trim(), cli.readSecret());
        systemInMock.provideLines("RRRR");
        Assert.assertEquals("RRRR".trim(), cli.readSecret());
    }

    @Test
    public void doReadAttemptTest() {
        MastermindForKidsCli cli = new MastermindForKidsCli();
        systemInMock.provideLines("RRRR");
        Assert.assertNotEquals("RRRG".trim(), cli.readAttempt());
        systemInMock.provideLines("RRRR");
        Assert.assertEquals("RRRR".trim(), cli.readAttempt());
    }

    //@Test
    public void doOutTest() {
        MastermindForKidsCli cli = new MastermindForKidsCli();
        resetBuffers();
        cli.out("test");
        Assert.assertEquals("test", outContent.toString());
    }

    //@Test
    public void doOutLnTest() {
        MastermindForKidsCli cli = new MastermindForKidsCli();
        resetBuffers();
        cli.outLn("test");
        Assert.assertEquals("test\r\n", outContent.toString());
    }

    @Test
    public void doOutErrorTest() {
        MastermindForKidsCli cli = new MastermindForKidsCli();
        resetBuffers();
        cli.outError("test");
        Assert.assertEquals("test\r\n", errContent.toString());
    }

}
