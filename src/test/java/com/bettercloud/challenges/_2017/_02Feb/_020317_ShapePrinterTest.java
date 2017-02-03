package com.bettercloud.challenges._2017._02Feb;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by davidesposito on 1/30/17.
 */
public class _020317_ShapePrinterTest {

    private _020317_ShapePrinter.ShapePrinter printer;

    @Before
    public void setUp() throws Exception {
        printer = new _020317_ShapePrinter.MyShapePrinter();
    }

    @Test
    public void testSquare_1_hash() {
        assertEquals("#", printer.square(1, "#"));
    }

    @Test
    public void testSquare_3_hash() {
        assertEquals("# # #\n# # #\n# # #", printer.square(3, "#"));
    }

    @Test
    public void testSquare_4_eq() {
        assertEquals("= = = =\n= = = =\n= = = =\n= = = =", printer.square(4, "="));
    }

    @Test
    public void testRectangle_1_1_hash() {
        assertEquals("#", printer.rectangle(1, 1, "#"));
    }

    @Test
    public void testRectangle_3_2_hash() {
        assertEquals("# # #\n# # #", printer.rectangle(3, 2, "#"));
    }

    @Test
    public void testRectangle_3_5_eq() {
        assertEquals("= = =\n= = =\n= = =\n= = =\n= = =", printer.rectangle(3, 5, "="));
    }

    @Test
    public void testTriangle_1_hash() {
        assertEquals("#", printer.triangle(1, "#"));
    }

    @Test
    public void testTriangle_3_hash() {
        assertEquals("#\n# #\n# # #", printer.triangle(3, "#"));
    }

    @Test
    public void testTriangle_5_eq() {
        assertEquals("=\n= =\n= = =\n= = = =\n= = = = =", printer.triangle(5, "="));
    }

    @Test
    public void testPyramid_1_hash() {
        assertEquals("#", printer.pyramid(1, "#"));
    }

    @Test
    public void testPyramid_2_hash() {
        assertEquals("  #\n# # #", printer.pyramid(2, "#"));
    }

    @Test
    public void testPyramid_3_hash() {
        assertEquals("    #\n  # # #\n# # # # #", printer.pyramid(3, "#"));
    }

    @Test
    public void testPyramid_5_eq() {
        assertEquals("        =\n      = = =\n    = = = = =\n  = = = = = = =\n= = = = = = = = =", printer.pyramid(5, "="));
    }
}