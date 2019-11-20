import java.util.*;

class Test {
    public static void main(String[] args){
       //System.out.println(solution(new long[]{1,4,3,4,2,5}, 3));
       //System.out.println(longestSubSequence(new int[]{1,5,7,8,5,3,4,2,1}, -2));
       System.out.println(ls(new int[]{1,5,7,8,5,3,4,2,1}, -2));
       //System.out.println(ls(new int[]{1,2,3,4}, 1));
       System.out.println("aeiout".indexOf('p'));
    }

    public static int ls(int[] arr, int d){
        int m = arr.length;
        int[] dp = new int[m];
        HashMap<Integer, Integer> map = new HashMap<>();
        int best = 0;
        for(int i=m-1; i>=0; --i){ //start from the end of array
            dp[i] = 1;
            if(map.containsKey(arr[i] + d)){
                dp[i] = Math.max(dp[i], map.get(arr[i] + d) +1);
            }

            map.put(arr[i], Math.max(dp[i], map.getOrDefault(arr[i], 0)));
            best = Math.max(best, dp[i]);
        }
        return best;
    }

    public static int longestSubsequence(int[] arr, int difference) {
        if(arr == null || arr.length <=1)
            return 0;
        int m = arr.length;
        int left=0, right=m-1;
        HashMap<Integer, Integer> map = new HashMap<>();
        int counter = 0;
        while(left<m){
            while(right>=0){
                if(arr[right] - arr[left] == difference){
                    if(map.containsKey(arr[right]) && map.get(arr[right]) == arr[left]){
                        right++;
                        continue;
                    }

                    map.put(arr[right], arr[left]);
                    counter++;
                    
                }
                right++;
            }
            left++;
            right=left+1;
        }
        
        return counter;
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