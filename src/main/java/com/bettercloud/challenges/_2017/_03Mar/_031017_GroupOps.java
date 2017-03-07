package com.bettercloud.challenges._2017._03Mar;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by davidesposito on 3/7/17.
 */
public class _031017_GroupOps {

    public interface GroupOps {

        /**
         * Returns a list of DISTINCT tags that appear on the provided articles. If the provided collection
         * enforces order, e.g. java.util.List, then the returned list should provide distinct values in order of
         * first appearance; don't worry, unit tests will tell you if you got it correct :).
         *
         * @param articles
         * @return
         */
        List<String> getTags(Collection<Article> articles);

        /**
         * Returns a map of Tag::name to appearance count across ALL articles. The count is the total number of times
         * the tag appears on any article. Note that tags can appear on the same article multiple times.
         * @param articles
         * @return
         */
        Map<String, Integer> getTagCounts(Collection<Article> articles);

        /**
         * Returns a list, with length `limit`, of tags sorted by tag count. The most popular tags, highest count,
         * should appear at the beginning of the list. `limit` should only truncate the list iff `list.size() > limit`.
         * @param articles
         * @param limit
         * @return
         */
        List<String> getMostPopularTags(Collection<Article> articles, int limit);
    }

    public static class MyGroupOps implements GroupOps {

        @Override
        public List<String> getTags(Collection<Article> articles) {
            return null;
        }

        @Override
        public Map<String, Integer> getTagCounts(Collection<Article> articles) {
            return null;
        }

        @Override
        public List<String> getMostPopularTags(Collection<Article> articles, int limit) {
            return null;
        }
    }

    @Data
    public static class Article {
        private final List<Tag> tags;

        public Article(String...tags) {
            List<Tag> temp = new ArrayList<>();
            for (String tag : tags) {
                temp.add(new Tag(tag));
            }
            this.tags = Collections.unmodifiableList(temp);
        }
    }

    @Data
    public static class Tag {
        private final String name;
    }
}
