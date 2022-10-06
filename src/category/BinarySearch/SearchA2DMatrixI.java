package category.BinarySearch;

public class SearchA2DMatrixI {
    public static void main(String[] args) {
        System.out.println(searchMatrix(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}}, 3));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int left=0, right = n*m -1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            int val = matrix[mid/n][mid%n];

            if(val == target) return true;

            if(val < target) {
                left = mid+1;
            }else{
                right = mid-1;
            }
        }

        return false;
    }
}
