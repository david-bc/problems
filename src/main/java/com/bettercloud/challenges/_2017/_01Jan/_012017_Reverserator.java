package com.bettercloud.challenges._2017._01Jan;

import com.bettercloud.challenges._2017.util.StreamUtil;
import org.javatuples.Pair;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by davidesposito on 1/20/17.
 */
public class _012017_Reverserator {

    public interface Reverserator {

        /**
         * Reverses the provided integer. e.g.
         *
         *      1 => 1
         *      12 => 21
         *      123 => 321
         *      45737892184 => 48129873754
         *
         * @param num A positive integer
         * @return The reverse of the positive integer
         */
        int process(int num);
    }

    public static class MyReverserator implements Reverserator {

        @Override
        public int process(int num) {
            Stream<Integer> keyStream = IntStream.range(0, Integer.toString(num).length()).boxed();
            Stream<Integer> numStream = Integer.toString(num)
                    .chars()
                    .map(Character::getNumericValue)
                    .boxed();

            String result = StreamUtil.zip(keyStream, numStream, Pair::new)
                    .sorted((pair1, pair2) -> pair2.getValue0() - pair1.getValue0())
                    .map(Pair::getValue1)
                    .map(Object::toString)
                    .collect(Collectors.joining());

            return Integer.parseInt(result);
        }
    }
}
