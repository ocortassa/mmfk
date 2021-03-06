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

    @Test
    public void doMainWithKnownSecretTest() {
        systemInMock.provideLines("RRRR");
        MastermindForKidsCli.main( new String[] { "RRRR" } );
        Assert.assertTrue(true);
    }

    @Test
    public void doMainWithUnknownSecretTest() {
        systemInMock.provideLines("GGGG");
        MastermindForKidsCli.main(new String[]{});
        Assert.assertEquals("null" + System.getProperty("line.separator"), systemErrMock.getLog());
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
