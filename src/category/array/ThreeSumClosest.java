package category.array;

import java.util.Arrays;
import java.util.*;

/**
 * Similar to 3 Sum problem, use 3 pointers to point current element, next element and the last element. 
 * If the sum is less than target, it means we have to add a larger element so next element move to the next. 
 * If the sum is greater, it means we have to add a smaller element so last element move to the second last element.
 * doing this until the end. Each time compare the difference between sum and target, if it is less than minimum difference so far, then replace result with it, otherwise keep iterating.
 * 
 * https://leetcode.com/problems/3sum-closest/discuss/7872/Java-solution-with-O(n2)-for-reference
 */

public class ThreeSumClosest{

    public static void main(String[] args) {

        Map<String, Integer> people = new HashMap<>();
        people.put("Jim", 25);
        people.put("Scott", 28);
        people.put("Anna", 23);

        Map<String, Integer> test = new LinkedHashMap<>();
        test.put("Anna", 23);
        test.put("Jim", 25);
        test.put("Scott", 28);
       
        for(Map.Entry<String, Integer> entry : test.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        List<Map.Entry<String, Integer>> lists = new LinkedList<Map.Entry<String, Integer>>(people.entrySet());
        Collections.sort(lists, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
               return  o1.getValue().compareTo(o2.getValue());
            }
       });

       StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, Integer> entry : lists){
            sb.append(entry.getKey());
        }

        System.out.println(threeSumClosest(new int[]{1,1,-1,-1,3}, -1));
    }
    public static int threeSumClosest(int[] num, int target) {
        if(num == null || num.length <3)
            return 0;
        //give arbitary result
        int result = num[0] + num[1] + num[num.length-1];
        Arrays.sort(num);
        //'i' is the left
        for (int i = 0; i < num.length - 2; i++) {
            int right = i + 1, end = num.length - 1;
            while (right < end) {
                int sum = num[i] + num[right] + num[end];
                if (sum > target) {
                    end--;
                } else {
                    right++;
                }
                if (Math.abs(sum - target) < Math.abs(result - target)) {
                    result = sum;
                }
            }
        }
        return result;
    }
}