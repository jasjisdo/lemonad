package com.github.jasjisdo.lemonad.loop;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;


public interface Where<T> {

    default Where<T> where(Predicate<T> predicate) {
        return new WhereImpl<>(get().filter(predicate));
    }

    default Stream<T> orderby(Comparator<? super T> comparator) {
        return get().sorted(comparator);
    }

    Stream<T> get();

    class WhereImpl<T> implements Where<T> {

        private static final long serialVersionUID = 1L;

        private Stream<T> stream;

        WhereImpl(Stream<T> stream) { this.stream = stream; }

        @Override
        public Stream<T> get() { return stream; }

    }

    class WhereList<T> implements Where<T> {

        private static final long serialVersionUID = 1L;

        private Stream<T> stream;

        WhereList(List<T> list) { this.stream = list.stream(); }

        @Override
        public Stream<T> get() { return stream; }

    }

    class WhereSet<T> implements Where<T> {

        private static final long serialVersionUID = 1L;

        private Stream<T> stream;

        WhereSet(Set<T> set) { this.stream = set.stream(); }

        @Override
        public Stream<T> get() { return stream; }

    }

}
