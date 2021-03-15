package category.Array;

import java.util.Arrays;

/**
 * (90 degrees (clockwise))
 * https://leetcode.com/problems/rotate-image/
 *
 * https://leetcode.com/problems/rotate-image/discuss/18872/A-common-method-to-rotate-the-image
 *
 * clockwise rotate
 * first reverse up to down, then swap the symmetry
 * 1 2 3     7 8 9     7 4 1
 * 4 5 6  => 4 5 6  => 8 5 2
 * 7 8 9     1 2 3     9 6 3
 *
 *
 * anticlockwise rotate
 * first reverse left to right, then swap the symmetry
 * 1 2 3     3 2 1     3 6 9
 * 4 5 6  => 6 5 4  => 2 5 8
 * 7 8 9     9 8 7     1 4 7
 *
 * Created by brianzhang on 3/14/21.
 */
public class RotateImageMatrix {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        rotate(matrix); // rotate 90 degree
        for(int[] row : matrix) System.out.println(Arrays.toString(row));

        System.out.println("=========");
        // rotate 180 degree =  2 * 90 degree
        matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        for(int i=0; i<2; i++) rotate(matrix);
        for(int[] row : matrix) System.out.println(Arrays.toString(row));

        System.out.println("=========");
        // rotate 180 degree - one time
        matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        rotate180(matrix);
        for(int[] row : matrix) System.out.println(Arrays.toString(row));

    }

    // rotate 90 degrees (clockwise)
    public static void rotate(int[][] matrix) {
        int s = 0, e = matrix.length - 1;
        while(s < e){ // switch row up down
            int[] temp = matrix[s];
            matrix[s] = matrix[e];
            matrix[e] = temp;
            s++; e--;
        }

        for(int i = 0; i < matrix.length; i++){
            for(int j = i+1; j < matrix[i].length; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    // rotate 180 degree (clockwise)
    public static void rotate180(int[][] matrix) {
        int s = 0, e = matrix[0].length-1;
        // switch column left right
        while(s < e) {
            for(int i=0; i<matrix[0].length; i++){
                int temp = matrix[i][s];
                matrix[i][s] = matrix[i][e];
                matrix[i][e] = temp;
            }
            s++;e--;
        }

        s=0;
        e=matrix.length-1;
        while(s < e){
            int[] temp = matrix[s];
            matrix[s] = matrix[e];
            matrix[e] = temp;
            s++; e--;
        }
    }
}
