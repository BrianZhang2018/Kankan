package category.javabasic;

import java.util.*;

/**
 * Created by brianzhang on 7/26/21.
 */
public class ArrayDequeExample {

    public static void main(String[] args) {
        Deque<Integer> dq = new ArrayDeque<>();
        //Deque<Integer> arrayDeque = new LinkedList<>();

        // Add 3 at the "tail" of this deque
        dq.add(3); // -> [3]
        // Add 4 at the "head" of this deque
        dq.push(4);//  -> [4, 3]
        // Add 6 at the "tail" of this deque
        dq.offer(6); // -> [4, 3, 6]

        System.out.println("Deque:");
        for(Integer i : dq) {
            System.out.println(i);
        }

        Deque<Integer> dq1 = new LinkedList<>();
        int[] nums = {1,2,3,4,5};

        for(int i : nums) {
            dq1.push(i);
           //dq1.offer(i);
        }

        for(int i : dq1) System.out.println(i);

    }
}
