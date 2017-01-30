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
            return rectangle(size, size, c);
        }

        @Override
        public String rectangle(int width, int height, String c) {
            return IntStream.range(0, height).boxed()
                    .map(i -> IntStream.range(0, width))
                    .map(wStream -> wStream
                            .boxed()
                            .map(i -> c)
                            .collect(Collectors.joining(" "))
                    )
                    .collect(Collectors.joining("\n"));
        }

        @Override
        public String triangle(int size, String c) {
            return IntStream.rangeClosed(1, size)
                    .boxed()
                    .map(i -> IntStream.range(0, i))
                    .map(rowStream -> rowStream.boxed()
                            .map(i -> c)
                            .collect(Collectors.joining(" "))
                    )
                    .collect(Collectors.joining("\n"));
        }

        @Override
        public String pyramid(int size, String c) {
            return IntStream.rangeClosed(1, size)
                    .boxed()
                    .map(i -> IntStream.range(0, size - i)
                            .boxed()
                            .map(j -> "  ")
                            .collect(Collectors.joining())
                    )
                    .map(whiteSpace -> whiteSpace + IntStream.range(0, ((size - (whiteSpace.length() / 2)) * 2) - 1)
                            .boxed()
                            .map(i -> c)
                            .collect(Collectors.joining(" "))
                    )
                    .collect(Collectors.joining("\n"));
        }
    }
}
