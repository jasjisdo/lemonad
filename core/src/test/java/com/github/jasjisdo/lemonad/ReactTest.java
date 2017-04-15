package com.github.jasjisdo.lemonad;

import org.junit.Test;

import java.util.function.Predicate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by domann on 15.04.17.
 */
public class ReactTest {

    @Test
    public void whenReactOnIsSet_thenIsSenseTheGivenObject() throws Exception {
        String A = "A";
        React<String> react = React.on(A);
        assertThat(react.getSense(), is("A"));
    }

    @Test
    public void whenReactPredicateIsFulfilled_thenReturnAnActiveReflex() throws Exception {
        String A = "A";
        Predicate<String> isCapsA = s -> s.equals("A");
        Reflex<String> reflex = React.on(A).when(isCapsA);
        assertThat(reflex.isActive(), is(true));
        assertThat(reflex.isIdle(), is(false));
    }

    @Test
    public void whenReactPredicateIsNotFulfilled_thenReturnAnIdleReflex() throws Exception {
        String a = "a";
        Predicate<String> isCapsA = s -> s.equals("A");
        Reflex<String> reflex = React.on(a).when(isCapsA);
        assertThat(reflex.isActive(), is(false));
        assertThat(reflex.isIdle(), is(true));
    }

}
