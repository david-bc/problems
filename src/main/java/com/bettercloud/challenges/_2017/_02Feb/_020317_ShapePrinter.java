package com.bettercloud.challenges._2017._02Feb;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by davidesposito on 1/20/17.
 */
public class _020317_ShapePrinter {

    public interface ShapePrinter {
        /**
         * ex. size = 5, c = '#'
         *
         *      |# # # # #
         *      |# # # # #
         *      |# # # # #
         *      |# # # # #
         *      |# # # # #
         *
         * @param size
         * @param c
         * @return
         */
        String square(int size, String c);

        /**
         * ex. width = 4, height = 2, c = '#'
         *
         *      |# # # #
         *      |# # # #
         *
         * @param width
         * @param height
         * @param c
         * @return
         */
        String rectangle(int width, int height, String c);

        /**
         * ex. size = 5, c = '#'
         *
         *      |#
         *      |# #
         *      |# # #
         *      |# # # #
         *      |# # # # #
         *
         * @param size
         * @param c
         * @return
         */
        String triangle(int size, String c);

        /**
         * ex. size = 5, c = '#'
         *
         *      |        #
         *      |      # # #
         *      |    # # # # #
         *      |  # # # # # # #
         *      |# # # # # # # # #
         *
         * @param size
         * @param c
         * @return
         */
        String pyramid(int size, String c);

    }

    public static class MyShapePrinter implements ShapePrinter {

        @Override
        public String square(int size, String c) {
            return IntStream.range(0, size).boxed()
                    .map(val -> line(size, c))
                    .collect(Collectors.joining("\n"));
        }

        @Override
        public String rectangle(int width, int height, String c) {
            return IntStream.range(0, height).boxed()
                    .map(val -> line(width, c))
                    .collect(Collectors.joining("\n"));
        }

        @Override
        public String triangle(int size, String c) {
            return IntStream.range(0, size).boxed()
                    .map(val -> val + 1)
                    .map(val -> line(val, c))
                    .collect(Collectors.joining("\n"));
        }

        @Override
        public String pyramid(int size, String c) {
            return IntStream.range(0, size).boxed()
                    .map(val -> val + 1)
                    .map(val -> line(size - val + 1, " ") + line(val, c) + " " + line(val - 1, c))
                    .map(str -> str.replaceFirst("\\s++$", ""))
                    .map(str -> str.substring(1))
                    .collect(Collectors.joining("\n"));
        }

        private String line(int size, String c) {
            return IntStream.range(0, size).boxed()
                    .map(val -> c)
                    .collect(Collectors.joining(" "));
        }
    }
}
