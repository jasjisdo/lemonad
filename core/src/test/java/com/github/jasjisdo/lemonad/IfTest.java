package com.github.jasjisdo.lemonad;

import com.github.jasjisdo.lemonad.if_.If;
import com.github.jasjisdo.lemonad.if_.Test;
import org.junit.Before;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IfTest {

    @Before
    public void setUp() throws Exception {

    }

    @org.junit.Test
    public void whenAllTestAreTrue_thenResultIsTrue() {

        final boolean[] result = new boolean[1];
        If.of(
                Test.of("A", s -> !s.isEmpty()),
                Test.of('B', s -> !s.equals(' '))
        ).then(() -> {result[0] = true;}
        ).elze(() -> {result[0] = false;});

        assertTrue(result[0]);

    }

    @org.junit.Test
    public void whenAllTestAreFalse_thenResultIsFalse() {

        final boolean[] result = new boolean[1];
        If.of(
                Test.of("A", s -> s.isEmpty()),
                Test.of('B', s -> s.equals(' '))
        ).then(() -> {result[0] = true;}
        ).elze(() -> {result[0] = false;});

        assertFalse(result[0]);

    }

}
