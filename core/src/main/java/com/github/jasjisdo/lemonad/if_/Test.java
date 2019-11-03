package com.github.jasjisdo.lemonad.if_;

import java.util.function.Predicate;

public class Test<T> {

    private T t;
    private Predicate<T> predicate;

    private Test(T t, Predicate<T> predicate) {
        this.t = t;
        this.predicate = predicate;
    }

    public static <T> Test<T> of(T t, Predicate<T> predicate) {
        return new Test<>(t, predicate);
    }

    boolean test() {
        return predicate.test(t);
    }

}
