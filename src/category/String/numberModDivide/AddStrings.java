package category.String.numberModDivide;

/**
 * https://leetcode.com/problems/add-strings/
 *
 * Created by brianzhang on 6/7/20.
 */
public class AddStrings {
    public static void main(String[] args) {
        System.out.println(addStrings("0", "9"));
    }

    public static int maxVowels(String s, int k) {
        String vowels = "aeiou";
        int max = 0;
        for(int i=0; i<s.length(); i++){
            int count = 0;
            for(int j=i; j<s.length() && j<i+k; j++){
                if(i == 3)
                    System.out.println(111);
                if(vowels.indexOf(s.charAt(j)) >= 0){
                    count++;
                    System.out.println("hahah");
                }

            }
            max = Math.max(count, max);
        }
        return max;
    }

    public static String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int m = num1.length()-1, n = num2.length()-1;

        while(m>=0 || n>=0){
            int sum = carry;
            if(m>=0){
                sum+=Character.getNumericValue(num1.charAt(m--));
            }
            if(n>=0){
                sum+=Character.getNumericValue(num2.charAt(n--));
            }

            sb.append(sum%10);
            carry = sum/10;
        }

        if(carry > 0){
            sb.append(carry);
        }
        return sb.reverse().toString();
    }
}
