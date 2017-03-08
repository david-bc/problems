package com.bettercloud.challenges._2017._03Mar;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by davidesposito on 3/8/17.
 */
public class _030817_LookSayTest {

    private _030817_LookSay.LookSay lookSay;

    @Before
    public void setup() {
        this.lookSay = new _030817_LookSay.MyLookSay();
    }

    @Test
    public void test_lookSay_01() {
        String expected = "1";
        String actual = lookSay.lookSay("1", 0);
        assertEquals(expected, actual);
    }

    @Test
    public void test_lookSay_02() {
        String expected = "11";
        String actual = lookSay.lookSay("1", 1);
        assertEquals(expected, actual);
    }

    @Test
    public void test_lookSay_03() {
        String expected = "21";
        String actual = lookSay.lookSay("1", 2);
        assertEquals(expected, actual);
    }

    @Test
    public void test_lookSay_04() {
        String expected = "1211";
        String actual = lookSay.lookSay("1", 3);
        assertEquals(expected, actual);
    }

    @Test
    public void test_lookSay_05() {
        String expected = "111221";
        String actual = lookSay.lookSay("1", 4);
        assertEquals(expected, actual);
    }

    @Test
    public void test_lookSay_06() {
        String expected = "312211";
        String actual = lookSay.lookSay("1", 5);
        assertEquals(expected, actual);
    }

    @Test
    public void test_lookSay_07() {
        String expected = "13112221";
        String actual = lookSay.lookSay("1", 6);
        assertEquals(expected, actual);
    }

    @Test
    public void test_lookSay_08() {
        String expected = "1113213211";
        String actual = lookSay.lookSay("1", 7);
        assertEquals(expected, actual);
    }

    @Test
    public void test_lookSay_09() {
        String expected = "31131211131221";
        String actual = lookSay.lookSay("1", 8);
        assertEquals(expected, actual);
    }

    @Test
    public void test_lookSay_10() {
        String expected = "22";
        String actual = lookSay.lookSay("22", 100);
        assertEquals(expected, actual);
    }

    @Test
    public void test_lookSay_11() {
        String expected = "111c111a111t";
        String actual = lookSay.lookSay("cat", 2);
        assertEquals(expected, actual);
    }

    @Test
    public void test_lookSay_12() {
        String expected = "31131211131221";
        String actual = lookSay.lookSay("1113213211", 1);
        assertEquals(expected, actual);
    }

    @Test
    public void test_lookSay_13() {
        String expected = "28";
        String actual = lookSay.lookSay("88888888", 2);
        assertEquals(expected, actual);
    }
}