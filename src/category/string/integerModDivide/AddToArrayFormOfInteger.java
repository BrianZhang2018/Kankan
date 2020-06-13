package category.string.integerModDivide;

import java.util.*;

/**
 * https://leetcode.com/problems/add-to-array-form-of-integer/
 *
 * Take K as a carry. Add it to the lowest digit, Update carry K,
 * and keep going to higher digit.
 *
 * Created by brianzhang on 6/7/20.
 */
public class AddToArrayFormOfInteger {

    public static void main(String[] args) {
        addToArrayForm(new int[]{9,9,9,9,9,9,9,9,9,9}, 1);
    }

    public static List<Integer> addToArrayForm(int[] A, int K) {

        List<Integer> res = new ArrayList<>();

        for(int i=A.length-1; i>=0; i--){
            int curr = A[i]+K;
            res.add(curr%10);
            K=curr/10;
        }

        while (K > 0){      // e.g. [10000], 0
            res.add(K%10);
            K /=10;
        }

        Collections.reverse(res);
        return res;
    }
}
