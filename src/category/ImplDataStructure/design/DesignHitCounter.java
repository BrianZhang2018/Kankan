package category.ImplDataStructure.design;

/**
 * https://leetcode.com/problems/design-hit-counter/discuss/83483/Super-easy-design-O(1)-hit()-O(s)-getHits()-no-fancy-data-structure-is-needed!
 * https://www.geeksforgeeks.org/design-a-hit-counter/
 *
 * Record "Past" 5 mins
 *
 * basic ideal is using buckets. 1 bucket for every second because we only need to keep the recent hits info for 300 seconds.
 * hit[] array is wrapped around by mod operation. Each hit bucket is associated with times[] bucket which record current time.
 * If it is not current time, it means it is 300s or 600s... ago and need to reset to 1.
 *
 * Created by brianzhang on 5/22/20.
 */
public class DesignHitCounter {

    public static void main(String[] args) {
        DesignHitCounter test = new DesignHitCounter();
        test.hit(1);
        System.out.println(test.getHits(2));
        test.hit(300);
        test.hit(300);
        System.out.println(test.getHits(300));
        System.out.println(test.getHits(301));
        test.hit(602);
        System.out.println(test.getHits(602));
        test.hit(302);
        System.out.println(test.getHits(602));
    }

    int[] timestamps = new int[300];
    int[] hits = new int[300];

    public void hit(int timestamp){
        int idx = timestamp % 300;
        if(timestamps[idx] != timestamp){
            if(timestamps[idx] < timestamp){
                timestamps[idx] = timestamp;
                hits[idx] = 1;
            }
        }else{
            hits[idx]++;
        }
    }

    public int getHits(int timestamp){
        int count = 0;
        for(int i = 0; i< timestamps.length; i++){
            if(timestamp - timestamps[i] < 300){
                count += hits[i];
            }
        }
        return count;
    }
}
