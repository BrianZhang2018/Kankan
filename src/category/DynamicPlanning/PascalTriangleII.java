package category.DynamicPlanning;

import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/pascals-triangle-ii/
 *
 * Created by brianzhang on 2/23/19.
 */
public class PascalTriangleII {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(getRow(3).toArray()));
    }

    public static List<Integer> getRow(int rowIndex) {
        Integer[] pascal = new Integer[rowIndex + 1];
        Arrays.fill(pascal, 0);
        pascal[0] = 1;

        for (int i = 1; i <= rowIndex; i++) {
            for (int j = i; j > 0; j--) {
                pascal[j] = pascal[j] + pascal[j - 1];
            }
        }
        return Arrays.asList(pascal);
    }
}
