package category.DynamicPlanning;

import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/pascals-triangle-ii/
 * <p>
 * Created by brianzhang on 11/29/18.
 */
public class PascalTriangle {

    public static void main(String[] args) {
        for (Integer i : getRow(3)) {
            System.out.println(i);
        }
    }

    public static List<Integer> getRow(int k) {
        Integer[] arr = new Integer[k + 1];
        Arrays.fill(arr, 0);
        arr[0] = 1;

        for (int i = 1; i <= k; i++) {
            for (int j = i; j > 0; j--) {   //从end of row向前推(j=i), i 代表处理到了第几行
                arr[j] = arr[j] + arr[j - 1];
            }
        }
        // i=1, arr[0], arr[1]
        // i=2, arr[0], arr[1], arr[2]
        // i=3, arr[0], arr[1], arr[2], arr[3]

        return Arrays.asList(arr);
    }
}
