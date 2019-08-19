package category.DataStructure;

import java.util.*;

/**
 * https://www.lintcode.com/problem/meeting-rooms/description
 * 
 * Created by brianzhang on 10/24/18.
 */
public class MeetingRoomII {
    public static void main(String[] args) {
        Interval source = new Interval(9, 77);
        Interval source1 = new Interval(9, 10);
        Interval source2 = new Interval(10, 12);

        List<Interval> tests = new ArrayList<>();
        tests.add(source);
        tests.add(source1);
        tests.add(source2);

        System.out.println(minMeetingRooms(tests));
    }

    public static int minMeetingRooms(List<Interval> intervals) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (Interval i : intervals) {
            map.put(i.start, map.getOrDefault(i.start, 0) + 1);
            map.put(i.end, map.getOrDefault(i.end, 0) - 1);
        }
        int room = 0;
        int max = 0;
        for (int num : map.keySet()) {
            room += num;
            max = Math.max(max, room);
        }
        return max;
    }

//https://www.youtube.com/watch?v=jUpuIio_oYo
    public int minMeetingRooms1(List<Interval> intervals) {
        // Write your code here
        
        int n = intervals.size();
        int[] starts = new int[n];
        int[] ends = new int[n];
        
        for(int i=0; i<n; i++){
            starts[i] = intervals.get(i).start;
            ends[i] = intervals.get(i).end;
        }
        
        Arrays.sort(starts);
        Arrays.sort(ends);
        
        int room = 0;
        int endIndex = 0;
        
        for(int i = 0; i<n; i++){
            if(starts[i] < ends[endIndex]){
                room++;
            }else{
                endIndex++;
            }
        }
        
        return room;
    }
}

class Interval {
    int start, end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
