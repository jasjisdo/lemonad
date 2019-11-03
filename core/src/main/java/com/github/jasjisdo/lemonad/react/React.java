package com.github.jasjisdo.lemonad.react;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * Created by domann on 25.03.17.
 */
public interface React<T> {

    static <T> React<T> on(T t) {
        return new Basic<>(t);
    }

    default Reflex<T> when(Predicate<T> predicate) {
        Objects.requireNonNull(getSense(), "React has no sensing object");
        if(predicate.test(getSense())) {
            return new Reflex.Active<>(getSense());
        } else {
            return new Reflex.Idle<>(getSense());
        }
    }

    T getSense();

    class Basic<T> implements React<T> {

        private static final long serialVersionUID = 1L;

        private final T t;

        /**
         * Constructs a Basic (React).
         *
         * @param t The value of this React.
         */
        public Basic(T t) {
            this.t = t;
        }

        @Override
        public T getSense() {
            return t;
        }

    }
}
