package com.github.ocortassa.mmfk.test;

import com.github.ocortassa.mmfk.MastermindForKids;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MastermindForKidsTest {

    @Test
    public void doTest() {
        MastermindForKids mmfk = new MastermindForKids();
        Assertions.assertEquals(mmfk.getMaxAttempts(), 12);
        mmfk.setSecret("RRBB");
        Assertions.assertFalse(mmfk.hasWin("gggg").isEmpty());
        Assertions.assertFalse(mmfk.hasWin("rvbg").isEmpty());
        Assertions.assertFalse(mmfk.hasWin("vvbb").isEmpty());
        Assertions.assertFalse(mmfk.hasWin("rrgg").isEmpty());
        Assertions.assertFalse(mmfk.hasWin("bbrr").isEmpty());
        Assertions.assertFalse(mmfk.hasWin("RRRB").isEmpty());
        Assertions.assertTrue(mmfk.hasWin("RRBB").isEmpty());
    }

}
