package com.bettercloud.challenges._2017._03Mar;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by davidesposito on 3/7/17.
 */
public class _031017_GroupOpsTest {

    private _031017_GroupOps.GroupOps ops;

    @Before
    public void setUp() throws Exception {
        this.ops = new _031017_GroupOps.MyGroupOps();
    }

    private Collection<_031017_GroupOps.Article> ac(_031017_GroupOps.Article...articles) {
        return Lists.newArrayList(articles);
    }

    private _031017_GroupOps.Article a(String...tags) {
        return new _031017_GroupOps.Article(tags);
    }

    private void assertContainsEquals(Collection<String> expected, Collection<String> actual) {
        assertEquals(expected.size(), actual.size());
        for (String tag : expected) {
            assertTrue(actual.contains(tag));
        }
        for (String tag : expected) {
            assertTrue(actual.contains(tag));
        }
    }

    @Test
    public void test_getTags_empty() {
        Collection<_031017_GroupOps.Article> input = ac();
        List<String> expected = Lists.newArrayList();

        List<String> actual = ops.getTags(input);

        assertContainsEquals(expected, actual);
    }

    @Test
    public void test_getTags_single() {
        Collection<_031017_GroupOps.Article> input = ac(a("mySupahTag"));
        List<String> expected = Lists.newArrayList("mySupahTag");

        List<String> actual = ops.getTags(input);

        assertContainsEquals(expected, actual);
    }

    @Test
    public void test_getTags_multi_0() {
        Collection<_031017_GroupOps.Article> input = ac(a("mySupahTag", "myOtherTag"));
        List<String> expected = Lists.newArrayList("mySupahTag", "myOtherTag");

        List<String> actual = ops.getTags(input);

        assertContainsEquals(expected, actual);
    }

    @Test
    public void test_getTags_multi_1() {
        Collection<_031017_GroupOps.Article> input = ac(a("mySupahTag"), a("myOtherTag"));
        List<String> expected = Lists.newArrayList("mySupahTag", "myOtherTag");

        List<String> actual = ops.getTags(input);

        assertContainsEquals(expected, actual);
    }

    @Test
    public void test_getTags_distinct_0() {
        Collection<_031017_GroupOps.Article> input = ac(a("dupeTag", "dupeTag"));
        List<String> expected = Lists.newArrayList("dupeTag");

        List<String> actual = ops.getTags(input);

        assertContainsEquals(expected, actual);
    }

    @Test
    public void test_getTags_distinct_1() {
        Collection<_031017_GroupOps.Article> input = ac(a("dupeTag"), a("dupeTag"));
        List<String> expected = Lists.newArrayList("dupeTag");

        List<String> actual = ops.getTags(input);

        assertContainsEquals(expected, actual);
    }

    @Test
    public void test_getTags_complex() {
        Collection<_031017_GroupOps.Article> input = ac(a("tag01", "#yolo", "ship it!"), a("tag01"), a("#yolo", "#b3"));
        List<String> expected = Lists.newArrayList("tag01", "#yolo", "ship it!", "#b3");

        List<String> actual = ops.getTags(input);

        assertContainsEquals(expected, actual);
    }

    @Test
    public void test_getTagCounts_empty() {
        Collection<_031017_GroupOps.Article> input = ac();
        Map<String, Integer> expected = Maps.newHashMap();

        Map<String, Integer> actual = ops.getTagCounts(input);

        assertEquals(expected, actual);
    }

    @Test
    public void test_getTagCounts_multi_0() {
        Collection<_031017_GroupOps.Article> input = ac(a("mySupahTag", "myOtherTag"));
        Map<String, Integer> expected = Maps.newHashMap();
        expected.put("mySupahTag", 1);
        expected.put("myOtherTag", 1);

        Map<String, Integer> actual = ops.getTagCounts(input);

        assertEquals(expected, actual);
    }

    @Test
    public void test_getTagCounts_multi_1() {
        Collection<_031017_GroupOps.Article> input = ac(a("mySupahTag"), a("myOtherTag"));
        Map<String, Integer> expected = Maps.newHashMap();
        expected.put("mySupahTag", 1);
        expected.put("myOtherTag", 1);

        Map<String, Integer> actual = ops.getTagCounts(input);

        assertEquals(expected, actual);
    }

    @Test
    public void test_getTagCounts_dupe_0() {
        Collection<_031017_GroupOps.Article> input = ac(a("myDupeTag", "myDupeTag"));
        Map<String, Integer> expected = Maps.newHashMap();
        expected.put("myDupeTag", 2);

        Map<String, Integer> actual = ops.getTagCounts(input);

        assertEquals(expected, actual);
    }

    @Test
    public void test_getTagCounts_dupe_1() {
        Collection<_031017_GroupOps.Article> input = ac(a("myDupeTag"), a("myDupeTag"));
        Map<String, Integer> expected = Maps.newHashMap();
        expected.put("myDupeTag", 2);

        Map<String, Integer> actual = ops.getTagCounts(input);

        assertEquals(expected, actual);
    }

    @Test
    public void test_getTagCounts_complex() {
        Collection<_031017_GroupOps.Article> input = ac(a("tag01", "#yolo", "ship it!"), a("tag01"), a("#yolo", "#b3"));
        Map<String, Integer> expected = Maps.newHashMap();
        expected.put("tag01", 2);
        expected.put("#yolo", 2);
        expected.put("ship it!", 1);
        expected.put("#b3", 1);

        Map<String, Integer> actual = ops.getTagCounts(input);

        assertEquals(expected, actual);
    }

    @Test
    public void test_getMostPopularTag_empty() {
        Collection<_031017_GroupOps.Article> input = ac();
        List<String> expected = Lists.newArrayList();

        List<String> actual = ops.getMostPopularTags(input, 5);

        assertEquals(expected, actual);
    }

    @Test
    public void test_getMostPopularTag_single() {
        Collection<_031017_GroupOps.Article> input = ac(a("mySupahTag"));
        List<String> expected = Lists.newArrayList("mySupahTag");

        List<String> actual = ops.getMostPopularTags(input, 5);

        assertEquals(expected, actual);
    }

    @Test
    public void test_getMostPopularTag_multi_0() {
        Collection<_031017_GroupOps.Article> input = ac(a("mySupahTag", "myOtherTag"));
        List<String> expected = Lists.newArrayList("mySupahTag", "myOtherTag");

        List<String> actual = ops.getMostPopularTags(input, 5);

        assertEquals(expected, actual);
    }

    @Test
    public void test_getMostPopularTag_multi_1() {
        Collection<_031017_GroupOps.Article> input = ac(a("mySupahTag"), a("myOtherTag"));
        List<String> expected = Lists.newArrayList("mySupahTag", "myOtherTag");

        List<String> actual = ops.getMostPopularTags(input, 5);

        assertEquals(expected, actual);
    }

    @Test
    public void test_getTagCounts_complex_0() {
        Collection<_031017_GroupOps.Article> input = ac(a("#yolo", "ship it!"), a("#yolo"));
        List<String> expected = Lists.newArrayList("#yolo", "ship it!");

        List<String> actual = ops.getMostPopularTags(input, 5);

        assertEquals(expected, actual);
    }

    @Test
    public void test_getTagCounts_complex_0_limit() {
        Collection<_031017_GroupOps.Article> input = ac(a("#yolo", "ship it!"), a("#yolo"));
        List<String> expected = Lists.newArrayList("#yolo");

        List<String> actual = ops.getMostPopularTags(input, 1);

        assertEquals(expected, actual);
    }

    @Test
    public void test_getTagCounts_complex_1() {
        Collection<_031017_GroupOps.Article> input = ac(a("times3", "times2", "times1"), a("times3", "times2"), a("times3"));
        List<String> expected = Lists.newArrayList("times3", "times2", "times1");

        List<String> actual = ops.getMostPopularTags(input, 5);

        assertEquals(expected, actual);
    }

    @Test
    public void test_getTagCounts_complex_1_limit() {
        Collection<_031017_GroupOps.Article> input = ac(a("times3", "times2", "times1"), a("times3", "times2"), a("times3"));
        List<String> expected = Lists.newArrayList("times3", "times2");

        List<String> actual = ops.getMostPopularTags(input, 2);

        assertEquals(expected, actual);
    }

    @Test
    public void test_getTagCounts_complex_2() {
        Collection<_031017_GroupOps.Article> input = ac(a("times3", "times3", "times3"), a("times2", "times2"), a("times1"));
        List<String> expected = Lists.newArrayList("times3", "times2", "times1");

        List<String> actual = ops.getMostPopularTags(input, 5);

        assertEquals(expected, actual);
    }

    @Test(timeout = 10_000)
    public void loadTest_getTagCounts() {
        ArrayList<_031017_GroupOps.Article> input = Lists.newArrayList();
        String evenVal = "Winner, winner, chicken dinner!";
        String oddVal = "NOT IN MY HOUSE!";
        String hundredsVal = "OVER 9000!!!";
        int total = 1_000_000;
        int expectedOdd = total / 2;
        int expectedHundreds = total / 100;
        int expectedEven = expectedOdd - expectedHundreds;
        for (int i=0;i<total;i++) {
            input.add(a((i & 1) > 0 ? oddVal : i % 100 == 0 ? hundredsVal : evenVal));
        }
        Map<String, Integer> expected = Maps.newHashMap();
        expected.put(oddVal, expectedOdd);
        expected.put(evenVal, expectedEven);
        expected.put(hundredsVal, expectedHundreds);

        Map<String, Integer> actual = ops.getTagCounts(input);

        assertEquals(expected, actual);
    }

    @Test(timeout = 10_000)
    public void loadTest_getMostPopularTags() {
        ArrayList<_031017_GroupOps.Article> input = Lists.newArrayList();
        String evenVal = "Winner, winner, chicken dinner!";
        String oddVal = "NOT IN MY HOUSE!";
        String hundredsVal = "OVER 9000!!!";
        int total = 1_000_000;
        for (int i=0;i<total;i++) {
            input.add(a((i & 1) > 0 ? oddVal : i % 100 == 0 ? hundredsVal : evenVal));
        }
        List<String> expected = Lists.newArrayList(oddVal, evenVal, hundredsVal);

        List<String> actual = ops.getMostPopularTags(input, 5);

        assertEquals(expected, actual);
    }
}