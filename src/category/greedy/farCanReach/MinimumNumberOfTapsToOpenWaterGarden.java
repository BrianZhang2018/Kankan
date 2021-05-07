package category.greedy.farCanReach;

/**
 * https://leetcode.com/problems/minimum-number-of-taps-to-open-to-water-a-garden/
 *
 * https://leetcode.com/problems/minimum-number-of-taps-to-open-to-water-a-garden/discuss/506853/Java-A-general-greedy-solution-to-process-similar-problems
 * Created by brianzhang on 5/6/21.
 */
public class MinimumNumberOfTapsToOpenWaterGarden {

    public static void main(String[] args) {
        System.out.println(minTaps(5, new int[]{3,4,1,1,0,0}));
    }

    public static int minTaps(int n, int[] ranges) {
        int l = ranges.length;
        int[] arr = new int[l+1];
        for(int i=0; i<l; i++){
            if(ranges[i] == 0) continue;

            int left = Math.max(0, i-ranges[i]);
            arr[left] = Math.max(arr[left], i+ranges[i]);
        }

        int cnt=0, farCanReach = 0, end = 0;
        for(int i=0; i<arr.length && end <n; end = farCanReach){
            cnt++;
            while(i<arr.length && i<=end){
                farCanReach = Math.max(farCanReach, arr[i++]);
            }

            if(end == farCanReach) return -1;
        }

        return cnt;
    }
}
