package category.string.numberModDivide;

import java.util.Random;

/**
 * Created by brianzhang on 6/7/20.
 */
public class PlayStringCharToInteger {

    public static void main(String[] args) {
        // get digit from string
        String nums = "123";
        Integer num1 = nums.charAt(2) - '0';
        Integer num2 = Integer.parseInt(String.valueOf(nums.charAt(2)));
        Integer num3 = Character.getNumericValue(nums.charAt(2));
        System.out.println(num1);  System.out.println(num2); System.out.println(num3);

        //
        Random rand = new Random();
        for (int i=0; i<10; i++)
            System.out.println( (int)(Math.random() * (0+1)));

    }
}