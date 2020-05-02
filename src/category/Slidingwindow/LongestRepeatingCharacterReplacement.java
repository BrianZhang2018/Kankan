package category.Slidingwindow;

public class LongestRepeatingCharacterReplacement{

    public static void main(String[] args){
        System.out.println(characterReplacement("AABABBA", 1));
    }

    public static int characterReplacement(String s, int k) {
        
        if(s == null || s.length() == 0){
            return 0;
        }
        int[] count = new int[26];
        int start = 0, maxCount= 0, maxLength = 0;
        
        for(int end=0; end < s.length(); end++){
            maxCount = Math.max(maxCount, ++count[s.charAt(end) - 'A']);
            while(end - start + 1 - maxCount > k){
                count[s.charAt(start) - 'A']--;
                start++;
            }
            maxLength = Math.max(end - start + 1, maxLength);
        }
        return maxLength;
    }
}