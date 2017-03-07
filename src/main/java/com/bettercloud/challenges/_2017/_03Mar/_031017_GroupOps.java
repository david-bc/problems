package com.bettercloud.challenges._2017._03Mar;

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

        List<String> getTags(Collection<Article> articles);

        Map<String, Integer> getTagCounts(Collection<Article> articles);

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

    public static class Article {
        private final List<Tag> tags;

        public Article(String...tags) {
            List<Tag> temp = new ArrayList<>();
            for (String tag : tags) {
                temp.add(new Tag(tag));
            }
            this.tags = Collections.unmodifiableList(temp);
        }

        public List<Tag> getTags() {
            return tags;
        }
    }

    public static class Tag {

        private final String name;

        public Tag(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
