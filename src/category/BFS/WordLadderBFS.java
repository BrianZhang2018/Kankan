package category.BFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by brianzhang on 8/20/18.
 * <p>
 * right solution, but Time Limit Exceeded in Leetcode, (30/39 test cases passed)
 */
class WordNode {
    String word;
    int numSteps;

    public WordNode(String word, int numSteps) {
        this.word = word;
        this.numSteps = numSteps;
    }
}

public class WordLadderBFS {
    public static void main(String[] args) {
        
        System.out.println(ladderLength("hit", "cog", new ArrayList<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"))));
        //System.out.println(System.currentTimeMillis() - start);
    }

    public static int ladderLength(String beginWord, String endWord, List<String> wordDict) {

        if (!wordDict.contains(endWord))
            return 0;

        LinkedList<WordNode> queue = new LinkedList<>();
        queue.add(new WordNode(beginWord, 1));

        while (!queue.isEmpty()) {
            WordNode top = queue.remove();
            String word = top.word;
            //System.out.println("top word:  " + word);

            char[] arr = word.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    char temp = arr[i];
                    if (arr[i] != c) {
                        arr[i] = c;
                    }

                    String newWord = new String(arr);
                    if (wordDict.contains(newWord)) {

                        if (newWord.equals(endWord)) {
                            return top.numSteps + 1;
                        } else {
                            System.out.println("newWord: " + newWord);
                            queue.add(new WordNode(newWord, top.numSteps + 1));
                            wordDict.remove(newWord);
                        }
                    }

                    arr[i] = temp;
                }
            }
        }
        return 0;
    }
}