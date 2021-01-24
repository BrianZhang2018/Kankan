package category.RightDataStructure.design;

/**
 * https://leetcode.jp/problemdetail.php?id=362
 *
 * https://www.geeksforgeeks.org/design-a-hit-counter/
 * Created by brianzhang on 5/22/20.
 */
public class DesignHitCounter {

    int[] timestamps = new int[300];
    int[] hits = new int[300];

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
