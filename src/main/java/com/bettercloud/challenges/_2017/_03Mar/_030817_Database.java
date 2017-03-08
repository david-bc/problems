package com.bettercloud.challenges._2017._03Mar;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by davidesposito on 3/8/17.
 */
public class _030817_Database {

    public interface Database<I, T> {

        /**
         * Find the entry that was saved with the corresponding ID.
         * @param id Not Null.
         * @return Optional wrapper around the saved value if present. Otherwise, returns an empty optional.
         */
        Optional<T> findOne(I id);

        /**
         * Returns all values that meet the select criteria.
         *
         *      - limit: Optional. No more than this number of results that should be returne.
         *      - offset: Optional. Skip this number of results before starting the return set.
         *      - where: Optional. Predicate to decide which results should be included in the result set.
         *      - sort: Optional. How to order the results.
         *
         * NOTE: that you will need to decide which order to apply these.
         *
         * @param select
         * @return
         */
        List<T> findAll(SelectQuery<T> select);

        /**
         * This should provides MySQL "GROUP BY" like functionality. The provided select.grouping function
         * will map each value to a "key" and then store all values with the same key in a list.
         *
         * Given the results
         *      [
         *          { name: 'bob', age: 21 },
         *          { name: 'tim', age: 32 },
         *          { name: "Kate", age: 21 }
         *      ]
         *
         * and we want to group by age i.e. (obj) => obj.age
         *
         * the returned map should be:
         *      {
         *          21: [ { name: 'bob', age: 21 }, { name: "Kate", age: 21 } ],
         *          32: [ { name: 'tim', age: 32 } ]
         *      }
         *
         * Returns all values that meet the select criteria.
         *
         *      - limit: Optional. No more than this number of results that should be returned.
         *      - offset: Optional. Skip this number of results before starting the return set.
         *      - where: predicate to decide which results should be included in the result set.
         *      - grouping: Optional. Maps each element to a key to use for grouping.
         *
         * NOTE: that you will need to decide which order to apply these.
         *
         * @param select
         * @return
         */
        <K> Map<K, List<T>> findAll(GroupByQuery<K, T> select);

        /**
         *
         * @param where Optional. May be null. If present, should filter the results before counting.
         * @return Total number of saved objects that were not filtered out.
         */
        long count(Predicate<T> where);

        /**
         * Saves the value with the associated id and returns the saved item.
         * @param id Not Null.
         * @param item Not Null.
         * @return item being saved.
         */
        T save(I id, T item);
    }

    public static class MyDatabase<I, T> implements Database<I, T> {

        @Override
        public Optional<T> findOne(I id) {
            return null;
        }

        @Override
        public List<T> findAll(SelectQuery<T> select) {
            return null;
        }

        @Override
        public <K> Map<K, List<T>> findAll(GroupByQuery<K, T> select) {
            return null;
        }

        @Override
        public long count(Predicate<T> where) {
            return 0;
        }

        @Override
        public T save(I id, T item) {
            return null;
        }
    }

    @Data
    @Builder
    public static class SelectQuery<T> {
        private final Integer limit;
        private final Integer offset;
        private final Predicate<T> where;
        private final Comparator<T> sort;
    }

    @Data
    @Builder
    public static class GroupByQuery<K, T> {
        private final Integer limit;
        private final Integer offset;
        private final Predicate<T> where;
        @NonNull
        private final Function<T, K> grouping;
    }
}
