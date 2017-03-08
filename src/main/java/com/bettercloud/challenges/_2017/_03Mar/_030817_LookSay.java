package com.bettercloud.challenges._2017._03Mar;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Data;

import java.util.stream.Collectors;

/**
 * Created by davidesposito on 3/8/17.
 */
public class _030817_LookSay {

    public interface LookSay {

        /**
         * The idea is that you look at the string and say what you see.
         *
         *      look: "1" say: "I see one number 1"
         *      look: "11" say: "I see two number 1's"
         *      look: "21" say: "I see one number 2 and one number one"
         *      look: "1211": say: "I see one 1, one 2, and two number 1's"
         *      look: "111221": say: "I see three 1's, two 2's and 1 number 1"
         *
         * The last example is solved by looking at the following:
         *
         *      in: 111221 => out:
         *      in: [111]221 => out: 31 // three 1's
         *      in: 111[22]1 => out: 3122 // two 2's
         *      in: 11122[1] => out: 312211 // one 1
         *
         * When performing this algorithm, the output of the last iteration is the input of the next. Given "1" as input
         * the following is the output for each iteration:
         *
         *      0: "1"
         *      1: "11"  // do you the algorithm once
         *      2: "21"
         *      3: "1211"
         *      4: "111221"
         *      5: "312211"
         *      6: "13112221"
         *
         * So lookSay("1", 3) should return "1211"
         *
         * If you are still confused, check the following link.
         *
         *      [spoiler]
         *          http://www.geeksforgeeks.org/look-and-say-sequence/
         *          This link includes an iterative solution. Don't scroll too far unless you need hints.
         *      [spoiler]
         *
         * @param input
         * @param iters
         * @return
         */
        String lookSay(String input, int iters);
    }

    public static class MyLookSay implements LookSay {

        @Override
        public String lookSay(String input, int iters) {
            return iters == 0
                    ? input
                    : lookSay(
                    Lists.newArrayList(input.split("")).stream()
                            .map(c -> CharCount.builder().character(c).count(1).prefix("").build())
                            .collect(Collectors.reducing(
                                    (a, b) -> a.getCharacter().equals(b.getCharacter())
                                            ? a.inc()
                                            : b.merge(a)
                            ))
                            .map(cc -> cc.getPrefix() + cc.getCount() + cc.getCharacter())
                            .orElse("")
                    , iters - 1);
        }
    }

    @Data
    @Builder
    private static class CharCount {
        private String prefix;
        private String character;
        private int count;

        public CharCount inc() {
            this.count += 1;
            return this;
        }

        public CharCount merge(CharCount other) {
            this.prefix = other.getPrefix() + other.getCount() + other.getCharacter() + this.prefix;
            return this;
        }
    }
}
