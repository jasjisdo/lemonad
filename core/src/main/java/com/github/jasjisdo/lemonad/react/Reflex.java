package com.github.jasjisdo.lemonad.react;

import java.io.Serializable;
import java.util.function.Consumer;

/**
 *
 */
public interface Reflex<T> {

    default Reflex<T> thenRun(Runnable runnable) {
        if(this.isActive()) {
            runnable.run();
        }
        return this;
    }

    default Reflex<T> orElseRun(Runnable runnable) {
        if(this.isIdle()) {
            runnable.run();
        }
        return this;
    }

    default Reflex<T> then(Consumer<T> consumer) {
        if(this.isActive()) {
            consumer.accept(getSense());
        }
        return this;
    }

    default Reflex<T> orElse(Consumer<T> consumer) {
        if(this.isIdle()) {
            consumer.accept(getSense());
        }
        return this;
    }

    T getSense();

    boolean isActive();

    boolean isIdle();

    class Basic<T> implements Reflex<T>, Serializable {

        private static final long serialVersionUID = 1L;

        private final T value;

        /**
         * Constructs a Success.
         *
         * @param value The value of this Success.
         */
        public Basic(T value) {
            this.value = value;
        }

        @Override
        public T getSense() {
            return value;
        }

        @Override
        public boolean isActive() {
            return false;
        }

        @Override
        public boolean isIdle() {
            return false;
        }

    }

    final class Active<T> extends Basic<T> implements Serializable {

        private static final long serialVersionUID = 1L;

        /**
         * Constructs a Success.
         *
         * @param value The value of this Success.
         */
        Active(T value) {
            super(value);
        }

        @Override
        public boolean isActive() {
            return true;
        }

        @Override
        public boolean isIdle() {
            return false;
        }

    }

    final class Idle<T> extends Basic<T> implements Serializable {

        private static final long serialVersionUID = 1L;

        /**
         * Constructs a Success.
         *
         * @param value The value of this Success.
         */
        Idle(T value) {
            super(value);
        }

        @Override
        public boolean isActive() {
            return false;
        }

        @Override
        public boolean isIdle() {
            return true;
        }

    }

}
