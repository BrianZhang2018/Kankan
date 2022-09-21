package category.ImplDataStructure.LRU;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LinkedHashMap from java SDK
 * Created by brianzhang on 7/14/18.
 */
public class LRULinkedHashMap {
    public static void main(String[] args) {
        LRU2<Integer, Double> lhm = new LRU2<>(3);
        lhm.put(1, 1.0);
        lhm.put(2, 1.0);
        lhm.put(3, 1.0);
        lhm.put(4, 1.0);

        System.out.println(lhm.size());
    }

    static class LRU2<K, V> extends LinkedHashMap<K, V> {
        private int MAX_CACHE_SIZE;

        public LRU2(int maxCacheSize) {
            super(maxCacheSize);
            this.MAX_CACHE_SIZE = maxCacheSize;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            if (size() > MAX_CACHE_SIZE) {
                return true;
            } else {
                return false;
            }
        }
    }

}

