package com.github.jasjisdo.lemonad.if_;

import java.util.Arrays;
import java.util.List;

public interface If {

    static Then of(Test... tests){
        return new Then(tests);
    };


    class Then {

        private List<Test> tests;

        private Then(Test... tests) {
            this.tests = Arrays.asList(tests);
        }

        public Else then(Runnable runnable) {
            if (tests.stream().allMatch(Test::test)) {
                runnable.run();
            }
            return new Else(tests);
        }

    }

    class Else {

        private List<Test> tests;

        private Else(List<Test> tests) {
            this.tests = tests;
        }

        public void elze(Runnable runnable) {
            if (tests.stream().noneMatch(Test::test)) {
                runnable.run();
            }
        }
    }
}
