package category.BinarySearch;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/koko-eating-bananas/
 *
 * Created by brianzhang on 11/28/21.
 */
public class KokoEatingBananas {

    public static void main(String[] args) {
        System.out.println(minEatingSpeed(new int[]{3,6,7,11}, 8));
    }

    public static int minEatingSpeed(int[] piles, int h) {
        Arrays.sort(piles);
        int l = 0, r = piles[piles.length-1];
        while(l<r) {
            int mid = l + (r-l) / 2;
            int cnt = 0;
            for(int p : piles) {
                cnt += Math.ceil((double)p/mid); // "double casting"
            }
            if(cnt > h) {
                l = mid +1;
            }else{
                r = mid;
            }
        }

        return l;
    }
}
