package com.bettercloud.challenges._2017._02Feb;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by davidesposito on 2/10/17.
 */
public class _021017_Euler_001Test {

    private _021017_Euler_001.SumOfMultiplesService sumService;

    @Before
    public void setUp() throws Exception {
        sumService = new _021017_Euler_001.MySumOfMultiplesService();
    }

    @Test
    public void testExample_emptyList() {
        assertEquals(0, sumService.getSum(Lists.newArrayList(), 10));
    }

    @Test
    public void testExample_zeroMax() {
        assertEquals(0, sumService.getSum(Lists.newArrayList(3, 5), 0));
    }

    @Test
    public void testExample_conflictingMultiples() {
        assertEquals(0, sumService.getSum(Lists.newArrayList(10), 5));
    }

    @Test
    public void testExample_01() {
        assertEquals(23, sumService.getSum(Lists.newArrayList(3, 5), 10));
    }

    @Test
    public void testExample_02() {
        assertEquals(233168, sumService.getSum(Lists.newArrayList(3, 5), 1000));
    }

    @Test
    public void testSimple_01() {
        assertEquals(3, sumService.getSum(Lists.newArrayList(1), 3));
    }

    @Test
    public void testSimple_02() {
        assertEquals(30, sumService.getSum(Lists.newArrayList(10), 30));
    }

    @Test
    public void testMedium_01() {
        assertEquals(4950, sumService.getSum(Lists.newArrayList(1, 2), 100));
    }

    @Test
    public void testMedium_02() {
        assertEquals(435, sumService.getSum(Lists.newArrayList(1, 2, 3), 30));
    }

    @Test
    public void testMedium_03() {
        assertEquals(366832, sumService.getSum(Lists.newArrayList(2, 3, 5), 1000));
    }

    @Test
    public void testLarge_01() {
        assertEquals(294132351, sumService.getSum(Lists.newArrayList(17), 99999));
    }
}