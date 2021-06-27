package category.String.Math;

/**
 * https://leetcode.com/problems/multiply-strings/
 *
 * `num1[i] * num2[j]` will be placed at indices `[i + j`, `i + j + 1]`
 *
 * Created by brianzhang on 3/29/21.
 */
public class MultiplyStrings {
    public static void main(String[] args) {
        System.out.println(multiply("123", "45"));
    }

    public static String multiply(String num1, String num2) {
        int n1 = num1.length(), n2 = num2.length();
        int[] products = new int[n1+n2];

        //scan from back to head and store in reverse order in nums
        for(int i=n1-1; i>=0; i--) {
            for(int j=n2-1; j>=0; j--){
                int a = num1.charAt(i) - '0';
                int b = num2.charAt(j) - '0';
                products[i+j+1] += a * b;
            }
        }

        int carry = 0;
        for(int i=products.length-1; i>=0; i--) {
            int sum = products[i] + carry;
            carry = sum / 10;
            products[i] = sum % 10;
        }

        StringBuilder sb = new StringBuilder();
        for(int i : products) sb.append(i);
        while(sb.length() >0 && sb.charAt(0) == '0') sb.deleteCharAt(0); // remove leading 0s if has

        return sb.length() == 0? "0" : sb.toString();
    }
}
