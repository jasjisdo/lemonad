package com.github.jasjisdo.lemonad.loop;

import java.util.List;

public interface Each<T> {

    default Where<T> in(List<T> list) {
        return new Where.WhereList<>(list);
    }

    class EachImpl<T> implements Each<T> {}

}
