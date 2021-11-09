package category.String;

import java.util.*;

/**
 * https://leetcode.com/problems/text-justification/
 *
 * https://www.youtube.com/watch?v=qrZLQmL6fyI
 * linkedin: frequency : 3
 * Created by brianzhang on 11/20/20.
 */
public class TextJustification {

    public static void main(String[] args) {
        for(String s : fullJustify(new String[]{"What","must","be","acknowledgment","shall","be"}, 16)) {
            System.out.println(s);
        }
    }

    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int index = 0, n = words.length;

        while(index < n){
            int totalChars = words[index].length();
            int last = index+1; // "last" exclusive from current line

            while(last < n){
                if(totalChars + words[last].length() + 1 <= maxWidth) {
                    totalChars += words[last++].length() + 1; // "1" means at least one space between two words
                }else{
                    break;
                }
            }

            int gaps = last-index-1; // number of gaps in current line
            StringBuilder sb = new StringBuilder();
            if(last == n || gaps == 0){ // the last line or the line only have one word
                for(int i=index; i<last; i++){
                    sb.append(words[i] + ' ');
                }
                sb.deleteCharAt(sb.length() -1);

                while(sb.length() < maxWidth) sb.append(' ');

            }else{ // the middle lines
                int spaces = (maxWidth-totalChars)/gaps; // evenly distributed spaces need to be added into current gaps
                int rest = (maxWidth-totalChars)%gaps; // the rest spaces need to be added
                for(int i=index; i<last-1; i++) {
                    sb.append(words[i]);
                    sb.append(' ');
                    for(int j=0; j < spaces + (i-index<rest? 1 : 0); j++){  // i-index<rest? 1 : 0 used to determine whether need add one more space there.
                        sb.append(' ');
                    }
                }
                sb.append(words[last-1]); // since the middle line must have end word
            }

            res.add(sb.toString());
            index = last;
        }

        return res;
    }
}
