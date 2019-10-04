import java.util.*;

class Test {
    public static void main(String[] args){

       System.out.println(solution(new long[]{1,4,3,4,2,5}, 3));
    }

    public static long solution(long[] numbers, long k) {
        // Type your solution here
        if(numbers == null || numbers.length == 0)
            return 0;
        
        List<Long> list = new ArrayList<>();
        for(long num : numbers)
            list.add(num);
        
        boolean existingDup = false;
        int m = numbers.length;
        HashSet<Long> set = new HashSet<>();
        
        for(long num : numbers){
            if(set.add(num) == false){
                existingDup = true;
            }
        }
        
       /* if(existingDup){
            Arrays.sort(list, Collections.reverseOrder());
            return numbers[m-k+1];
        }else{
              Arrays.sort(list);
            return numbers[k-1];
        }*/
        return 0;
    }
}