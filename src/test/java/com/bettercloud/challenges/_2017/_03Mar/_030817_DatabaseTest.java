package com.bettercloud.challenges._2017._03Mar;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Data;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by davidesposito on 3/8/17.
 */
public class _030817_DatabaseTest {

    public static final String UNDERAGE = "Underage";
    public static final String LEGAL = "Legal";
    private _030817_Database.Database<UUID, TestObj> database;

    @Data
    @Builder
    private static class TestObj {
        private UUID id;
        private String name;
        private int age;
        private boolean suspended;
    }

    @Before
    public void setUp() throws Exception {
        database = new _030817_Database.MyDatabase<>();
    }

    @Test
    public void test_save() {
        UUID id = UUID.randomUUID();
        TestObj expected = TestObj.builder()
                .id(id)
                .name("Tester #4")
                .age(14)
                .suspended(false)
                .build();
        TestObj actual = database.save(id, expected);

        assertEquals(expected, actual);
    }

    @Test
    public void test_findOne() {
        UUID id = UUID.randomUUID();
        TestObj expected = TestObj.builder()
                .id(id)
                .name("Tester #4")
                .age(14)
                .suspended(false)
                .build();
        database.save(id, expected);
        Optional<TestObj> actual = database.findOne(id);

        assertEquals(expected, actual.get());
    }

    @Test
    public void test_findOne_missing() {
        UUID id = UUID.randomUUID();
        Optional<TestObj> expected = Optional.empty();
        Optional<TestObj> actual = database.findOne(id);

        assertEquals(expected, actual);
    }

    @Test
    public void test_findOne_wrongId() {
        UUID id = UUID.randomUUID();
        Optional<TestObj> input = Optional.of(TestObj.builder()
                .id(id)
                .name("Tester #4")
                .age(14)
                .suspended(false)
                .build());
        Optional<TestObj> expected = Optional.empty();

        database.save(id, input.get());
        Optional<TestObj> actual = database.findOne(UUID.randomUUID());

        assertEquals(expected, actual);
    }

    @Test
    public void test_count_empty() {
        long expected = 0L;
        long actual = database.count(val -> true);

        assertEquals(expected, actual);
    }

    @Test
    public void test_count_empty1() {
        long expected = 0L;
        long actual = database.count(null);

        assertEquals(expected, actual);
    }

    @Test
    public void test_count_single() {
        long expected = 1L;
        Lists.newArrayList(
                TestObj.builder().id(UUID.randomUUID()).name("Tester #4").age(14).suspended(false).build()
        ).forEach(o -> database.save(o.getId(), o));

        long actual = database.count(val -> true);

        assertEquals(expected, actual);
    }

    @Test
    public void test_count_singleFiltered() {
        long expected = 0L;
        Lists.newArrayList(
                TestObj.builder().id(UUID.randomUUID()).name("Tester #4").age(14).suspended(false).build()
        ).forEach(o -> database.save(o.getId(), o));

        long actual = database.count(val -> false);

        assertEquals(expected, actual);
    }

    @Test
    public void test_count() {
        long expected = 7L;
        getLargeInput().forEach(o -> database.save(o.getId(), o));

        long actual = database.count(null);

        assertEquals(expected, actual);
    }

    @Test
    public void test_count_filtered() {
        long expected = 3L;
        getLargeInput().forEach(o -> database.save(o.getId(), o));

        long actual = database.count(val -> val.isSuspended());

        assertEquals(expected, actual);
    }

    @Test
    public void test_findAll_single() {
        UUID id = UUID.randomUUID();
        List<TestObj> expected = Lists.newArrayList(
                TestObj.builder().id(id).name("Tester #4").age(14).suspended(false).build()
        );
        expected.forEach(o -> database.save(id, o));

        List<TestObj> actual = database.findAll(_030817_Database.SelectQuery.<TestObj>builder()
                .<TestObj>build());

        assertTrue(!actual.isEmpty());
        assertContains(expected, actual);
    }

    @Test
    public void test_findAll_small() {
        List<TestObj> expected = Lists.newArrayList(
                TestObj.builder().id(UUID.randomUUID()).name("Tester #4").age(14).suspended(false).build(),
                TestObj.builder().id(UUID.randomUUID()).name("Tester #8").age(12).suspended(true).build()
        );
        expected.forEach(o -> database.save(o.getId(), o));

        List<TestObj> actual = database.findAll(_030817_Database.SelectQuery.<TestObj>builder()
                .<TestObj>build());

        assertTrue(!actual.isEmpty());
        assertContains(expected, actual);
    }

    @Test
    public void test_findAll() {
        List<TestObj> expected = getLargeInput();
        expected.forEach(o -> database.save(o.getId(), o));

        List<TestObj> actual = database.findAll(_030817_Database.SelectQuery.<TestObj>builder()
                .<TestObj>build());

        assertTrue(!actual.isEmpty());
        assertContains(expected, actual);
    }

    @Test
    public void test_findAll_limit() {
        List<TestObj> input = getLargeInput();
        input.forEach(o -> database.save(o.getId(), o));

        List<TestObj> expected = Lists.newArrayList(input.get(0), input.get(1), input.get(2));

        List<TestObj> actual = database.findAll(_030817_Database.SelectQuery.<TestObj>builder()
                .limit(3)
                .sort((a, b) -> a.getAge() - b.getAge())
                .<TestObj>build());

        assertEquals(3, actual.size());
        assertEquals(expected, actual);
    }

    @Test
    public void test_findAll_offset() {
        List<TestObj> input = getLargeInput();
        input.forEach(o -> database.save(o.getId(), o));

        List<TestObj> expected = Lists.newArrayList(input.get(6));

        List<TestObj> actual = database.findAll(_030817_Database.SelectQuery.<TestObj>builder()
                .offset(6)
                .sort((a, b) -> a.getAge() - b.getAge())
                .<TestObj>build());

        assertEquals(1, actual.size());
        assertEquals(expected, actual);
    }

    @Test
    public void test_findAll_filter() {
        List<TestObj> input = getLargeInput();
        input.forEach(o -> database.save(o.getId(), o));

        List<TestObj> expected = Lists.newArrayList(input.get(6), input.get(4), input.get(2), input.get(0));

        List<TestObj> actual = database.findAll(_030817_Database.SelectQuery.<TestObj>builder()
                .where(obj -> !obj.isSuspended())
                .sort((a, b) -> b.getAge() - a.getAge())
                .<TestObj>build());

        assertEquals(expected, actual);
    }

    @Test
    public void test_findAll_sort() {
        List<TestObj> expected = getLargeInput();
        expected.forEach(o -> database.save(o.getId(), o));

        List<TestObj> actual = database.findAll(_030817_Database.SelectQuery.<TestObj>builder()
                .sort((a, b) -> b.getAge() - a.getAge())
                .<TestObj>build());

        assertContains(expected, actual);

        int lastAge = Integer.MAX_VALUE;
        for (TestObj curr : actual) {
            assertTrue(String.format("%d > %d", lastAge, curr.getAge()),
                    lastAge > curr.getAge());
            lastAge = curr.getAge();
        }
    }

    @Test
    public void test_findAll_complex() {
        List<TestObj> input = getLargeInput();
        input.forEach(o -> database.save(o.getId(), o));

        List<TestObj> expected = Lists.newArrayList(input.get(4), input.get(3));

        List<TestObj> actual = database.findAll(_030817_Database.SelectQuery.<TestObj>builder()
                .limit(2)
                .offset(1)
                .where(obj -> obj.getAge() > 15 && obj.getAge() < 20)
                .sort((a, b) -> b.getName().compareTo(a.getName()))
                .<TestObj>build());

        assertContains(expected, actual);
    }

    @Test
    public void test_findAll_groupBy_Suspended() {
        List<TestObj> expected = getLargeInput();
        expected.forEach(o -> database.save(o.getId(), o));

        Map<Boolean, List<TestObj>> actual = database.findAll(_030817_Database.GroupByQuery.<Boolean, TestObj>builder()
                .grouping(obj -> obj.isSuspended())
                .<Boolean, TestObj>build());

        assertTrue(actual.containsKey(true));
        assertTrue(actual.containsKey(false));
        assertEquals(4, actual.get(false).size());
        assertEquals(3, actual.get(true).size());

        actual.get(true).forEach(obj -> assertTrue(obj.isSuspended()));
        actual.get(false).forEach(obj -> assertFalse(obj.isSuspended()));
    }

    @Test
    public void test_findAll_groupBy_LegalAge() {
        List<TestObj> expected = getLargeInput();
        expected.forEach(o -> database.save(o.getId(), o));

        Map<String, List<TestObj>> actual = database.findAll(_030817_Database.GroupByQuery.<String, TestObj>builder()
                .grouping(obj -> obj.getAge() < 18 ? UNDERAGE : LEGAL)
                .<String, TestObj>build());

        assertTrue(actual.containsKey(UNDERAGE));
        assertTrue(actual.containsKey(LEGAL));
        assertEquals(4, actual.get(UNDERAGE).size());
        assertEquals(3, actual.get(LEGAL).size());

        actual.get(UNDERAGE).forEach(obj -> assertTrue(obj.getAge() < 18));
        actual.get(LEGAL).forEach(obj -> assertFalse(obj.getAge() < 18));
    }

    @Test
    public void test_findAll_groupBy_complex() {
        List<TestObj> expected = getLargeInput();
        expected.forEach(o -> database.save(o.getId(), o));

        String suspendedLegal = "suspendedLegal";
        String suspendedUnderage = "suspendedUnderage";
        String activeLegal = "activeLegal";
        String activeUnderage = "activeUnderage";

        Map<String, List<TestObj>> actual = database.findAll(_030817_Database.GroupByQuery.<String, TestObj>builder()
                .grouping(obj -> {
                    if (obj.isSuspended()) {
                        return obj.getAge() < 18 ? suspendedUnderage : suspendedLegal;
                    } else {
                        return obj.getAge() < 18 ? activeUnderage : activeLegal;
                    }
                })
                .where(obj -> !obj.getName().endsWith("Tester #5"))
                .<String, TestObj>build());

        assertTrue(actual.containsKey(suspendedLegal));
        assertTrue(actual.containsKey(suspendedUnderage));
        assertTrue(actual.containsKey(activeLegal));
        assertTrue(actual.containsKey(activeUnderage));
        assertEquals(1, actual.get(suspendedLegal).size());
        assertEquals(2, actual.get(suspendedUnderage).size());
        assertEquals(1, actual.get(activeLegal).size());
        assertEquals(2, actual.get(activeUnderage).size());

        assertEquals(
                Lists.newArrayList(expected.get(5)),
                actual.get(suspendedLegal)
        );
        assertContains(
                Lists.newArrayList(expected.get(1), expected.get(3)),
                actual.get(suspendedUnderage)
        );
        assertEquals(
                Lists.newArrayList(expected.get(6)),
                actual.get(activeLegal)
        );
        assertContains(
                Lists.newArrayList(expected.get(0), expected.get(2)),
                actual.get(activeUnderage)
        );
    }

    private ArrayList<TestObj> getLargeInput() {
        return Lists.newArrayList(
                TestObj.builder().id(UUID.randomUUID()).name("Tester #1").age(14).suspended(false).build(),
                TestObj.builder().id(UUID.randomUUID()).name("Tester #2").age(15).suspended(true).build(),
                TestObj.builder().id(UUID.randomUUID()).name("Tester #3").age(16).suspended(false).build(),
                TestObj.builder().id(UUID.randomUUID()).name("Tester #4").age(17).suspended(true).build(),
                TestObj.builder().id(UUID.randomUUID()).name("Tester #5").age(18).suspended(false).build(),
                TestObj.builder().id(UUID.randomUUID()).name("Tester #6").age(19).suspended(true).build(),
                TestObj.builder().id(UUID.randomUUID()).name("Tester #7").age(20).suspended(false).build()
        );
    }

    private <T> void assertContains(Collection<T> expected, Collection<T> actual) {
        assertEquals(expected.size(), actual.size());
        expected.forEach(val -> assertTrue(actual.contains(val)));
        actual.forEach(val -> assertTrue(expected.contains(val)));
    }
}