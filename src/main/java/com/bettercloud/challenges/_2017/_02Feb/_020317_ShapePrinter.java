package com.bettercloud.challenges._2017._02Feb;

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
            return c;
        }

        @Override
        public String rectangle(int width, int height, String c) {
            return c;
        }

        @Override
        public String triangle(int size, String c) {
            return c;
        }

        @Override
        public String pyramid(int size, String c) {
            return c;
        }
    }
}
