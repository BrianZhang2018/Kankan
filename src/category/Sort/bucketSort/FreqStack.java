package category.Sort.bucketSort;

import java.util.*;
/**
 * https://leetcode.com/problems/maximum-frequency-stack/
 *
 * bucket sort
 * use index in the list to represent the "frequency"!!
 *
 * use a stackList with stackList[i] store elements that appears i + 1 times
 * use a map to update the frequency of each element
 *
 * Created by brianzhang on 11/23/21.
 */
public class FreqStack {
    public static void main(String[] args) {
        FreqStack freqStack = new FreqStack();
        freqStack.push(5); // The stack is [5]
        freqStack.push(7); // The stack is [5,7]
        freqStack.push(5); // The stack is [5,7,5]
        freqStack.push(7); // The stack is [5,7,5,7]
        freqStack.push(4); // The stack is [5,7,5,7,4]
        freqStack.push(5); // The stack is [5,7,5,7,4,5]
        freqStack.pop();   // return 5, as 5 is the most frequent. The stack becomes [5,7,5,7,4].
        freqStack.pop();   // return 7, as 5 and 7 is the most frequent, but 7 is closest to the top. The stack becomes [5,7,5,4].
        freqStack.pop();   // return 5, as 5 is the most frequent. The stack becomes [5,7,4].
        freqStack.pop();   // return 4, as 4, 5 and 7 is the most frequent, but 4 is closest to the top. The stack becomes [5,7].

        System.out.println(3/2);
    }

    Map<Integer, Integer> map = new HashMap<>(); // number : freq
    List<Stack<Integer>> stackList = new ArrayList<>(); // index represent freq

    public FreqStack() {}

    public void push(int val) {
        map.put(val, map.getOrDefault(val, 0) +1);
        int freq = map.get(val);
        if(freq > stackList.size()) {
            stackList.add(freq-1, new Stack<>());
        }
        stackList.get(freq-1).push(val);
    }

    public int pop() {
        int highestFreq = stackList.size();
        Stack<Integer> s = stackList.get(highestFreq - 1);
        int val = s.pop();
        map.put(val, map.get(val)-1);
        if(s.isEmpty()) {
            stackList.remove(highestFreq-1);
        }
        return val;
    }
}
