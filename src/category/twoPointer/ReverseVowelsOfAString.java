package category.twoPointer;

/**
 * https://leetcode.com/problems/reverse-vowels-of-a-string/
 * 
 * To solve the problem which left and right pointer can meet at any position in a array
 */
public class ReverseVowelsOfAString{

    public String reverseVowels(String s) {
        if(s == null || s.length() == 0)
            return "";
        
        String vowels = "aeiouAEIOU";
        char[] arr = s.toCharArray();
        int left=0, right = s.length()-1;
        
        while(left < right){
            
            while(left < right && vowels.indexOf(arr[left]) == -1){
                left++;
            }
            
            while(left < right && vowels.indexOf(arr[right]) == -1){
                right--;
            }
            
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            
            left++;
            right--;
        }
                      
        return String.valueOf(arr);
    }
}