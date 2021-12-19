package com.github.jasjisdo.lemonad;


import com.github.jasjisdo.lemonad.loop.For;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class ForTest {

    private static final Logger logger = LoggerFactory.getLogger(ForTest.class);

    private final List<String> stringList = new ArrayList<>();
    private final Predicate<String> isNotEmpty = s -> !s.isEmpty();
    private final Predicate<String> isNotLabelEmpty = s -> !s.equals("EMPTY");

    @Before
    public void setUp() throws Exception {

        stringList.clear();
        stringList.add("");
        stringList.add("");
        stringList.add("EMPTY");
        stringList.add("EMPTY");
        stringList.add("Joe1");
        stringList.add("Joe2");
        stringList.add("Doe1");
        stringList.add("Doe2");

        logger.info("created stringList = {}", stringList);

    }

    @Test
    public void whenStringListIsAppliedOnForEachWhereMonad_thenStringListIsFilteredByWhereClauseCorrectly() {

        Stream<String> stream = For.each(String.class)
                                   .in(stringList)
                                   .where(isNotEmpty.and(isNotLabelEmpty)).get();

        assertThat(stream.collect(toList()), contains("Joe1", "Joe2", "Doe1", "Doe2"));

    }

    @Test
    public void whenStringListIsAppliedOnForEachWhereMonad_thenStringListIsFilteredByWhereClauseAndSortedByOrderByClauseCorrectly() {

        Stream<String> stream = For.each(String.class)
                                   .in(stringList)
                                   .where(isNotEmpty.and(isNotLabelEmpty))
                                   .orderby(new AlphanumComparator());

        assertThat(stream.collect(toList()), contains("Doe1", "Doe2", "Joe1", "Joe2"));

    }
}
