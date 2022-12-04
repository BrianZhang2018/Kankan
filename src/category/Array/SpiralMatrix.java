package category.Array;

import java.util.*;

/**
 * https://leetcode.com/problems/spiral-matrix/
 * 1. edge boudary - shift the boudary inwardly after each edge traverse
 * 2. direction
 */
public class SpiralMatrix {
    public static void main(String[] args) {
        int[][] matrix = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        System.out.println(Arrays.toString(spiralOrder(matrix).toArray()));
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        // four sides' boundary
        int row = 0, lastRow = matrix.length - 1;
        int col = 0, lastCol = matrix[0].length - 1;
        int dir = 0;
        while (row <= lastRow && col <= lastCol) {
            switch (dir) {
                case 0: // right
                    for (int i = col; i <= lastCol; i++) {
                        list.add(matrix[row][i]);
                    }
                    row++; // shift the boudary inwardly by 1
                    break;
                case 1: // down
                    for (int i = row; i <= lastRow; i++) {
                        list.add(matrix[i][lastCol]);

                    }
                    lastCol--;
                    break;
                case 2: // left
                    for (int i = lastCol; i >= col; i--) {
                        list.add(matrix[lastRow][i]);
                    }
                    lastRow--;
                    break;
                case 3: // up
                    for (int i = lastRow; i >= row; i--) {
                        list.add(matrix[i][col]);
                    }
                    col++;
                    break;
            }

            dir = (dir + 1) % 4;

        }

        return list;

    }
}
