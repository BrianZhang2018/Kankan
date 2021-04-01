package category.String.Math;

/**
 * https://leetcode.com/problems/add-strings/
 *
 * Created by brianzhang on 3/29/21.
 */
public class AddStrings {

    public static void main(String[] args) {
        System.out.println(addStrings("11", "123"));
    }

    public static String addStrings(String num1, String num2) {

        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int m = num1.length()-1,  n = num2.length()-1;

        while(m>=0 || n>=0){
            int sum = carry;
            if(m>=0){
                sum+=num1.charAt(m--) - '0';
            }

            if(n>=0){
                sum+=num2.charAt(n--)- '0';
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
