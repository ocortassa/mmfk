package com.github.ocortassa.mmfk.test;

import com.github.ocortassa.mmfk.MastermindForKids;
import org.junit.Assert;
import org.junit.Test;

public class MastermindForKidsTest {

    @Test
    public void doTest() {
        MastermindForKids mmfk = new MastermindForKids();
        Assert.assertEquals(mmfk.getMaxAttempts(), 12);
        mmfk.setSecret("RRBB");
        Assert.assertFalse(mmfk.hasWin("gggg").isEmpty());
        Assert.assertFalse(mmfk.hasWin("rvbg").isEmpty());
        Assert.assertFalse(mmfk.hasWin("vvbb").isEmpty());
        Assert.assertFalse(mmfk.hasWin("rrgg").isEmpty());
        Assert.assertFalse(mmfk.hasWin("bbrr").isEmpty());
        Assert.assertFalse(mmfk.hasWin("RRRB").isEmpty());
        Assert.assertTrue(mmfk.hasWin("RRBB").isEmpty());
    }

}
