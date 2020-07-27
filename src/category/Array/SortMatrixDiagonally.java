package category.Array;

import java.util.*;

/**
 * https://leetcode.com/problems/sort-the-matrix-diagonally/
 *
 * Created by brianzhang on 7/19/20.
 */
public class SortMatrixDiagonally {

    public static void main(String[] args) {
        int a[][] = {
                {1, 2, 3, 9},
                {4, 5, 6, 1},
                {7, 8, 9, 8},
                {1, 3, 9, 1},
        };

        //diagonalSort(a);
        antiDiagonalSort(a);
        for (int[] i : a) System.out.println(Arrays.toString(i));
    }

    // if the elements on same diagonal, the index of "row-col" is same for elements
    public static int[][] diagonalSort(int[][] mat) {
        // beautiful structure - use to PriorityQueue to sort
        Map<Integer, PriorityQueue<Integer>> map = new HashMap();
        int m = mat.length, n = mat[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map.putIfAbsent(i - j, new PriorityQueue<>());
                map.get(i - j).add(mat[i][j]);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = map.get(i - j).poll();
            }
        }

        return mat;
    }

    // if element on same anti-diagonal, the index of "row+col" is same for elements
    public static int[][] antiDiagonalSort(int[][] mat) {
        // beautiful structure - use to PriorityQueue to sort
        Map<Integer, PriorityQueue<Integer>> map = new HashMap();
        int m = mat.length, n = mat[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map.putIfAbsent(i + j, new PriorityQueue<>());
                map.get(i + j).add(mat[i][j]);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = map.get(i + j).poll();
            }
        }

        return mat;
    }
}
