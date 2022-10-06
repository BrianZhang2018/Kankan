package category.String;

/**
 * https://leetcode.com/problems/integer-to-english-words/
 *
 * Created by brianzhang on 6/1/20.
 */
public class IntegerToEnglishWords {
    public static void main(String[] args) {
        IntegerToEnglishWords test = new IntegerToEnglishWords();
        System.out.println(test.numberToWords(1000000));
    }

    final String[] LESS_THAN_20 = {"", "One", "Two", "Three", "Four", "Five", "Six",
            "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen"
            , "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty",
            "Seventy", "Eighty", "Ninety"};
    final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};

    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        StringBuilder sb = new StringBuilder();
        int i=0; // index of THOUSANDS unit
        // calculate from right to left, e.g. 123,456 - translate the 456, then 123
        while (num > 0) {
            if (num % 1000 != 0)
                sb.insert(0, trans(num % 1000) +THOUSANDS[i] + " ");
            num /= 1000;
            i++;
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
