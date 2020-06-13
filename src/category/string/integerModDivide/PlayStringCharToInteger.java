package category.string.integerModDivide;

/**
 * Created by brianzhang on 6/7/20.
 */
public class PlayStringCharToInteger {

    public static void main(String[] args) {
        // number string
        String nums = "123";
        Integer num1 = nums.charAt(2) - '0';
        Integer num2 = Character.getNumericValue(nums.charAt(2));
        System.out.println(num1);  System.out.println(num2);

        //
    }
}
