package category.Sort;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/k-closest-points-to-origin
 *
 * Created by brianzhang on 5/27/20.
 */
public class KClosestPointsToOriginQuickSelect {

    public static void main(String[] args) {
        KClosestPointsToOriginQuickSelect test = new KClosestPointsToOriginQuickSelect();
        for(int[] res : test.kClosest(new int[][]{{3,3},{5,-1},{-2,4}}, 2))
            System.out.println(Arrays.toString(res));
    }

    public int[][] kClosest(int[][] points, int K) {
        int len =  points.length, l = 0, r = len - 1;
        while (l <= r) {
            int mid = helper(points, l, r);
            if (mid == K) break;
            if (mid < K) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return Arrays.copyOfRange(points, 0, K);
    }

    // my solution - easily understood
    private int helper(int[][] points, int l, int r) {

        int[] pivot = points[r]; int pivotIdx = l;
        for (int i = l; i <= r; i++)
        {
            // inserting elements of less value to the left of the pivot location
            if(compare(points[i], pivot) < 0)
            {
                int[] temp = points[i];
                points[i] = points[pivotIdx];
                points[pivotIdx] = temp;
                pivotIdx++;
            }
        }

        // swapping pivot to the pivot location
        int[] temp = points[r];
        points[r] = points[pivotIdx];
        points[pivotIdx] = temp;

        return pivotIdx;
    }

    // leetcode solution
    private int helper1(int[][] A, int l, int r) {
        int[] pivot = A[l];
        while (l < r) {
            while (l < r && compare(A[r], pivot) >= 0) r--;
            A[l] = A[r];
            while (l < r && compare(A[l], pivot) <= 0) l++;
            A[r] = A[l];
        }
        A[l] = pivot;
        return l;
    }

    private int compare(int[] p1, int[] p2) {
        return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1];
    }
}
