package category.Array.integerModDivide;

/**
 * https://leetcode.com/problems/add-strings/
 *
 * Created by brianzhang on 6/7/20.
 */
public class AddStrings {

    public static void main(String[] args) {
        System.out.println(addStrings("0", "9"));
    }

    public static String addStrings(String num1, String num2) {

        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int m = num1.length()-1,  n = num2.length()-1;

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
