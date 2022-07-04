package category.Sort.quickSelect;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/k-closest-points-to-origin
 * Quick Select base on Quick sort
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
            int mid = partition(points, l, r);
            if (mid == K) break;
            if (mid < K) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return Arrays.copyOfRange(points, 0, K);
    }

    // my solution for quick select - easily understood
    private int partition(int[][] points, int l, int r) {
        int[] pivot = points[r]; // set rightmost as pivot
        int pivotIdx = l;
        for (int i = l; i <= r; i++)
        {
            // place small element to the left of the pivot element
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

    // another swap way - quick select
    private int partition1(int[][] num, int lo, int hi) {
        int[] pivot = num[lo];
        while (lo < hi) {
            while (lo < hi && compare(num[hi], pivot) >= 0) hi--;
            num[lo] = num[hi];
            while (lo < hi && compare(num[lo], pivot) <= 0) lo++;
            num[hi] = num[lo];
        }
        num[lo] = pivot;
        return lo;
    }

    private int compare(int[] p1, int[] p2) {
        return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1];
    }
}
