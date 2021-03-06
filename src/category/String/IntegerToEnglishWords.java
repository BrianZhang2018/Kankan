package category.String;

/**
 * https://leetcode.com/problems/integer-to-english-words/
 *
 * Created by brianzhang on 6/1/20.
 */
public class IntegerToEnglishWords {

    public static void main(String[] args) {
        IntegerToEnglishWords test = new IntegerToEnglishWords();
        System.out.println(test.numberToWords(123456));
    }

    final String[] LESS_THAN_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    final String[] THOUSANDS = {"Billion", "Million", "Thousand", ""};
    final int[] radix = {1000000000, 1000000, 1000, 1}; // 重点

    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < radix.length; i++) {
            if (num / radix[i] == 0) continue; // until find right radix

            sb.append(trans(num / radix[i])).append(THOUSANDS[i]).append(' ');
            num %= radix[i];
        }
        return sb.toString().trim();
    }

    private String trans(int num) {
        if (num == 0) return "";
        if (num < 20) return LESS_THAN_20[num] + " ";
        if (num < 100) return TENS[num / 10] + " " + trans(num % 10);

        return LESS_THAN_20[num / 100] + " Hundred " + trans(num % 100);
    }
}
