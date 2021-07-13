package category.String.Math;

/**
 * https://leetcode.com/problems/multiply-strings/
 *
 * 解题思路:
 *
   Smart part is realizing there is a max length of answer and directly adding sums to correct position products[i + j + 1] += d1 * d2

         num1 = "12"
         num2 = "19"

          19  j
         x12  i
         ---
         38
        190
        ---
        228 // max length of (N1+N2+1) here

            idx = 0, 1, 2, 3
     products = [0, 1, 11,18] // after first loop

     products = [0, 2, 2, 8] // after second loop

 * Created by brianzhang on 3/29/21.
 */
public class MultiplyStrings {
    public static void main(String[] args) {
        System.out.println(multiply("123", "45"));
    }

    public static String multiply(String num1, String num2) {
        int n1 = num1.length(), n2 = num2.length();
        int[] products = new int[n1+n2]; // The product of two numbers cannot exceed the sum of the two lengths. (e.g. 99 * 99 cannot be five digit)

        // scan from back to head and store in reverse order in nums
        for(int i=n1-1; i>=0; i--) {
            for(int j=n2-1; j>=0; j--){
                int a = num1.charAt(i) - '0';
                int b = num2.charAt(j) - '0';
                products[i+j+1] += a * b; // 核心公式
            }
        }

        // calculate the carry and move
        int carry = 0;
        for(int i=products.length-1; i>=0; i--) {
            int sum = products[i] + carry;
            carry = sum / 10;
            products[i] = sum % 10;
        }

        StringBuilder sb = new StringBuilder();
        for(int i : products) sb.append(i);
        while(sb.length() >0 && sb.charAt(0) == '0') sb.deleteCharAt(0); // remove leading "0" if has

        return sb.length() == 0? "0" : sb.toString();
    }
}
