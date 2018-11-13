package category.DynamicPlanning.TrianleMiniPath;

import java.util.List;

/**
 * https://leetcode.com/problems/triangle/
 * <p>
 * Created by brianzhang on 11/12/18.
 */
public class OneDimensionalArrayForMemo {

    public int minimumTotal(List<List<Integer>> triangle) {
        int[] arr = new int[triangle.size() + 1];

        for (int i = triangle.size() - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                arr[j] = Math.min(arr[j], arr[j + 1]) + triangle.get(i).get(j);
            }
        }
        return arr[0];
    }
}
