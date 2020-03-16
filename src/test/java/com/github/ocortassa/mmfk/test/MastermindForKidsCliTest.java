package com.github.ocortassa.mmfk.test;

import com.github.ocortassa.mmfk.cli.MastermindForKidsCli;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemErrRule;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

public class MastermindForKidsCliTest {

    @Rule
    public final TextFromStandardInputStream systemInMock = TextFromStandardInputStream.emptyStandardInputStream();

    @Rule
    public final SystemErrRule systemErrMock = new SystemErrRule().enableLog();

    @Rule
    public final SystemOutRule systemOutMock = new SystemOutRule().enableLog();

    /*
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
    */

    @Test
    public void doMainTest() {
        systemInMock.provideLines("RRRR");
        systemInMock.provideLines("RRRR");
        MastermindForKidsCli.main(new String[] {});
        Assert.assertTrue(true);
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

    @Test
    public void doOutTest() {
        MastermindForKidsCli cli = new MastermindForKidsCli();
        cli.out("test");
        Assert.assertEquals("test", systemOutMock.getLog());
    }

    @Test
    public void doOutLnTest() {
        MastermindForKidsCli cli = new MastermindForKidsCli();
        cli.outLn("test");
        Assert.assertEquals("test" + System.getProperty("line.separator"), systemOutMock.getLog());
    }

    @Test
    public void doOutErrorTest() {
        MastermindForKidsCli cli = new MastermindForKidsCli();
        cli.outError("test");
        Assert.assertEquals("test" + System.getProperty("line.separator"), systemErrMock.getLog());
    }

}
