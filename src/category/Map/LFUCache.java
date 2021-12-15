package category.Map;

import java.util.*;

/**
 * https://leetcode.com/problems/lfu-cache
 * https://www.cnblogs.com/weiyinfu/p/8822876.html
 *
 *
 * LinkedHashMap
 *
 * Created by brianzhang on 6/16/20.
 */
class LFUCache { // leash frequently used
    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);
        String[] op = {"put", "put", "get", "put", "get", "get", "put", "get", "get", "get"};
        int[][] value = {{1, 1}, {2, 2}, {1}, {3, 3}, {2}, {3}, {4, 4}, {1}, {3}, {4}};
        for (int i = 0; i < op.length; i++) {
            System.out.println(op[i] + " " + value[i] + " " + cache.min);
            cache.debug();
            if (op[i].equals("put")) {
                cache.put(value[i][0], value[i][1]);
            } else {
                cache.get(value[i][0]);
            }
        }
    }
    // key (存储element的key的值) : key出现的频率
    HashMap<Integer, Integer> freqMap = new HashMap<>();
    // key出现的频率 : LinkedHashMap (element's key : element's value)
    HashMap<Integer, LinkedHashMap<Integer, Integer>> freqLRU = new HashMap<>();
    // 时刻记住需要更新哪些全局变量
    int min = 0;    //最小频率
    int capacity;   //容器的容量
    int currSize = 0;   //当前容器中元素个数

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        Integer f = freqMap.get(key);
        if (f == null) {
            return -1;
        }
        int value = freqLRU.get(f).get(key);
        active(key);//激活一下key,使其频率+1
        return value;
    }

    void active(int key) {
        int f = freqMap.get(key);
        freqMap.put(key, f + 1);
        LinkedHashMap<Integer, Integer> src = freqLRU.get(f), des = freqLRU.getOrDefault(f + 1, new LinkedHashMap<>());
        des.put(key, src.remove(key));
        tryRemove(f);
        freqLRU.put(f + 1, des);
    }

    void tryRemove(int frequency) {
        if (freqLRU.get(frequency).size() == 0) {
            if (frequency == min) {
                min++;
            }
            freqLRU.remove(frequency);
        }
    }

    void removeLFU() {
        LinkedHashMap<Integer, Integer> ma = freqLRU.get(min);
        int removing = ma.keySet().iterator().next();
        ma.remove(removing);//移除掉最早插入的那个结点
        tryRemove(min);
        freqMap.remove(removing);
        currSize--;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;
        if (freqMap.get(key) == null) {
            if (capacity == currSize) removeLFU();
            currSize++;
            freqMap.put(key, 1);
            LinkedHashMap<Integer, Integer> ff = freqLRU.getOrDefault(1, new LinkedHashMap<>());
            ff.put(key, value);
            freqLRU.put(1, ff);
            min = 1;//新插入结点之后,最低频率必然为1
        } else {
            active(key);
            freqLRU.get(freqMap.get(key)).put(key, value);
        }
    }

    // utility
    public String tos(Map<Integer, Integer> ma) {
        StringBuilder builder = new StringBuilder();
        for (int i : ma.keySet()) {
            builder.append(i + ":" + ma.get(i) + " ");
        }
        return builder.toString();
    }

    public void debug() {
        System.out.println(tos(freqMap));
        for (int i : freqLRU.keySet()) {
            System.out.println(i + " " + tos(freqLRU.get(i)));
        }
        System.out.println("======");
    }
}
