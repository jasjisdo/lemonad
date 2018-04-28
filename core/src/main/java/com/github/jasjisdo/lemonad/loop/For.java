package com.github.jasjisdo.lemonad.loop;

public interface For<T> {

    static <T> Each<T> each(Class<T> t) { return new Each.EachImpl<T>(); }

}
