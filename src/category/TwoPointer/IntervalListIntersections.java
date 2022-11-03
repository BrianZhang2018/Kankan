package category.TwoPointer;

import java.util.*;

/**
 * https://leetcode.com/problems/interval-list-intersections/
 * Created by brianzhang on 9/12/20.
 */
public class IntervalListIntersections {
    public static void main(String[] args) {
        int[][] res = intervalIntersection(new int[][]{{0, 2}, {5, 10}, {13, 23}, {24, 25}}, new int[][]{{1, 5}, {8, 12}, {15, 24}, {25, 26}});
        for(int[] i : res) System.out.println(Arrays.toString(i));
   }

    public static int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> res = new ArrayList<>();
        int i = 0, j = 0;

        while (i < A.length && j < B.length) {

            int lo = Math.max(A[i][0], B[j][0]);
            int hi = Math.min(A[i][1], B[j][1]);

            if (lo <= hi) {
                res.add(new int[]{lo, hi});
            }

            if (A[i][1] < B[j][1]) {
                i++;
            } else {
                j++;
            }
        }

        return res.toArray(new int[res.size()][]);
    }
}
