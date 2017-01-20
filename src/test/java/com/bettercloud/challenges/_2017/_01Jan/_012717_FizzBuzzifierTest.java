package com.bettercloud.challenges._2017._01Jan;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

/**
 * Created by davidesposito on 1/20/17.
 */
@RunWith(Parameterized.class)
public class _012717_FizzBuzzifierTest {

    private _012717_FizzBuzz.FizzBuzzifier fizzBuzzifier = new _012717_FizzBuzz.MyFizzBuzzifier();

    private int min, max;
    private String expected;

    public _012717_FizzBuzzifierTest(int min, int max, String expected) {
        this.min = min;
        this.max = max;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        List<Object[]> data = Lists.newArrayList();
        data.add(new Object[]{ -11, -3, ""});
        data.add(new Object[]{ -11, 0, ""});
        String base = "1\n2\nFizz\n4\nBuzz";
        data.add(new Object[]{ 1, 5, base});
        String medium = base + "\nFizz\n7\n8\nFizz\nBuzz\n11\nFizz";
        data.add(new Object[]{ 1, 12, medium});
        String allCases = medium + "\n13\n14\nFizzBuzz\n16";
        data.add(new Object[]{ 1, 16, allCases});
        data.add(new Object[]{ 0, 16, allCases});
        data.add(new Object[]{ -13, 16, allCases});
        data.add(new Object[]{ 59, 61, "59\nFizzBuzz\n61"});
        return data;
    }

    @Test
    public void processSmall() throws Exception {
        Assert.assertEquals(expected, fizzBuzzifier.process(min, max));
    }
}