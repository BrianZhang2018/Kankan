package companies.amazon;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LinkedHashMap from java API
 * Created by brianzhang on 7/14/18.
 */
public class LRU2<K, V> extends LinkedHashMap<K, V> {

    public static void main(String[] args) {
        LRU2 test = new LRU2(3);
    }

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