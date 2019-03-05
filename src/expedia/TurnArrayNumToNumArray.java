package expedia;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by brianzhang on 3/3/19.
 */
public class TurnArrayNumToNumArray {

    public static void main(String[] args) {
       int[] res = solutionWithHashMap(new int[]{40, 29, 29, 29, 8,8,8});
       for(int i : res){
           System.out.println(i);
       }
    }

    public static int[] solutionWithHashMap(int[] input){

        HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();
        for(int i : input){
            hmap.put(i, hmap.getOrDefault(i, 0) + 1);
        }

        int[] res = new int[hmap.size()*2];

        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> b.getKey() - a.getKey());
        for(Map.Entry<Integer, Integer> x : hmap.entrySet()){
            pq.add(x);
        }

        int index = 0;
        while(pq.size()>0){
            Map.Entry<Integer, Integer> temp =  pq.poll();
            res[index++] = temp.getValue();
            res[index++] = temp.getKey();
        }
        return res;
    }
}
