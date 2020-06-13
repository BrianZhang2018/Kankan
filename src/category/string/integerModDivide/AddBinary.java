package category.string.integerModDivide;

/**
 * https://leetcode.com/problems/add-binary/
 *
 * Take K as a carry. Add it to the lowest digit, Update carry K,
 * and keep going to higher digit.
 *
 * Created by brianzhang on 6/7/20.
 */
public class AddBinary {

    public static void main(String[] args) {
        System.out.println(addBinary("1010", "1011"));
    }

    public static String addBinary(String a, String b) {

        StringBuilder sb = new StringBuilder();

        int m = a.length()-1, n = b.length()-1;

        int carry = 0;

        while(m>=0 || n>=0){
            int sum = carry;
            if(m>=0){
                sum += a.charAt(m--) - '0';
            }

            if(n>=0){
                sum += b.charAt(n--) - '0';
            }
            carry = sum/2;
            sb.append(sum%2);
        }

        if(carry != 0)
            sb.append(carry);

        return sb.reverse().toString();
    }
}
