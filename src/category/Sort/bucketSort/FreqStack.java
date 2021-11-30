package category.Sort.bucketSort;

import java.util.*;

/**
 * https://leetcode.com/problems/maximum-frequency-stack/
 *
 * bucket sort
 * use index in list to represent the "frequency"!!
 *
 * use a stackList with stackList[i] store elements that appears i + 1 times
 * use a map to update the frequency of each element
 *
 * Created by brianzhang on 11/23/21.
 */
public class FreqStack {
    public static void main(String[] args) {

    }

    Map<Integer, Integer> map = new HashMap<>();
    List<Stack<Integer>> stackList = new ArrayList<>();

    public FreqStack() {}

    public void push(int val) {
        map.put(val, map.getOrDefault(val, 0) +1);
        int freq = map.get(val);

        if(freq > stackList.size()) stackList.add(freq-1, new Stack<>());

        stackList.get(freq-1).push(val);
    }

    public int pop() {

        Stack<Integer> s = stackList.get(stackList.size()-1);
        int val = s.pop();
        map.put(val, map.get(val)-1);

        if(s.isEmpty()) {
            stackList.remove(stackList.size()-1);
        }

        return val;
    }
}
