package com.github.jasjisdo.lemonad;

import com.github.jasjisdo.lemonad.react.React;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Predicate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Created by domann on 15.04.17.
 */
public class ReflexTest {

    private ByteArrayOutputStream baos;

    @Before
    public void setUp() throws Exception {
        baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
    }

    @Test
    public void whenPredicateIsFulfilled_thenPrintStringToOut() throws Exception {
        String A = "A";
        Predicate<String> isCapsA = s -> s.equals("A");
        React.on(A).when(isCapsA).then(System.out::print);
        assertThat(baos.toString(), is("A"));
    }

    @Test
    public void whenPredicateIsNotFulfilled_thenPrintNothingToOut() throws Exception {
        String a = "a";
        Predicate<String> isCapsA = s -> s.equals("A");
        React.on(a).when(isCapsA).then(System.out::print);
        assertThat(baos.toString(), is(""));
    }
}
