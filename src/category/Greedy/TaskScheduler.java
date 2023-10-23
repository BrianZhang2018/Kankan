package category.Greedy;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/task-scheduler/
 */
public class TaskScheduler {
    public static void main(String[] args) {
        System.out.println(leastInterval(new char[]{'A','A','A','B','B','B'}, 2));
    }

    public static int leastInterval(char[] tasks, int n) {
        int[] frequency = new int[26];
        for(char c : tasks) {
            frequency[c - 'A']++;
        }

        Arrays.sort(frequency);
        int maxF = frequency[frequency.length-1];
        int maxIdleTime = (maxF - 1) * n;
        for(int i = frequency.length -2; i >=0; i--) {
            if(frequency[i] == 0 || maxIdleTime == 0) break;

            maxIdleTime -= Math.min(maxF-1, frequency[i]); // maxF-1 is to handle that has dup max frequency
        }

        maxIdleTime = Math.max(0, maxIdleTime);
        return maxIdleTime + tasks.length;
    }
}
