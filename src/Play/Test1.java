package Play;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class Test1{
    public static void main(String[] args){
        
        List<Integer> list = new ArrayList<Integer>();
        list.add(8);
        list.add(4);
        list.add(6);
        list.add(12);
        Collections.sort(list);
        
        System.out.println(minimumTime(4, list));
    }

   static int minimumTime(int numOfParts, List<Integer> parts){
        
        int prevTime = parts.get(0) + parts.get(1);
        int result = prevTime;

        for(int i= 2; i< numOfParts; i++){
            prevTime += parts.get(i);
            result += prevTime;
        }

        return result;
    }
}