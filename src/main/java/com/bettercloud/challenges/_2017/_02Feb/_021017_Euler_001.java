package com.bettercloud.challenges._2017._02Feb;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by davidesposito on 1/20/17.
 */
public class _021017_Euler_001 {

    /**
     * Borrow with love from https://projecteuler.net/problem=1
     *
     * Multiples of 3 and 5
     * Problem 1
     * If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
     *
     * Find the sum of all the multiples of 3 or 5 below 1000.
     *
     *  ======
     *
     * We are going to make it a little more interesting :)
     *
     * The list of multiples and the max will be dynamically passed in as params. So the examples above would look like
     *
     *      <code>service.getSum(Lists.newArrayList(3, 5), 10);</code>
     * and
     *      <code>service.getSum(Lists.newArrayList(3, 5), 1000);</code>
     *
     *
     */
    public interface SumOfMultiplesService {

        int getSum(List<Integer> multiples, int max);
    }

    public static class MySumOfMultiplesService implements SumOfMultiplesService {

        @Override
        public int getSum(List<Integer> multiples, int max) {
            return IntStream.range(0, max)
                    .filter(num -> multiples.stream()
                            .map(multiple -> num % multiple)
                            .anyMatch(remainder -> remainder == 0)
                    )
                    .sum();
        }
    }
}
