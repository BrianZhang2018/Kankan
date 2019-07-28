package companies.amazon;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LinkedHashMap from java API
 * Created by brianzhang on 7/14/18.
 */
public class LRU2<K, V> extends LinkedHashMap<K, V> {

    private static int MAX_CACHE_SIZE = 10;

    public LRU2(int maxSize) {
        super(MAX_CACHE_SIZE, 1.0f);

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