package com.yjx.demo.quizzes.arithmetic.lru;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class LruTest {

    public static void main(String[] args) {
        LruCache lruCache = new LruCache(3);
        lruCache.put("1", "abc");
        lruCache.put("2", "def");
        lruCache.put("3", "ghi");
        lruCache.put("4", "kl;");
        System.out.println(lruCache.toString());
        lruCache.get("2");
        System.out.println(lruCache.toString());
    }


    static class LruCache<K, V> extends LinkedHashMap<K, V> {

        private int maxEntries = 10;

        @Override
        protected boolean removeEldestEntry(Entry<K, V> eldest) {
            return size() > this.maxEntries;
        }
        public LruCache(int maxEntries) {
            super(maxEntries, 0.75f, true);
            this.maxEntries = maxEntries;
        }
    }

}
