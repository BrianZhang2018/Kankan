package category.TwoPointer;

/**
 * https://leetcode.com/problems/container-with-most-water/
 *
 * Created by brianzhang on 2/27/21.
 */
public class ContainerWithMostWater {

    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }

    public static int maxArea(int[] height) {

        if(height == null || height.length ==0) return 0;

        int i=0;
        int j=height.length-1;
        int max = Integer.MIN_VALUE;

        while(i<=j){
            if(height[i] < height[j]){
                max = Math.max((j-i) * height[i], max);
                i++;
            }else{
                max = Math.max((j-i) * height[j], max);
                j--;
            }
        }

        return max;
    }
}
