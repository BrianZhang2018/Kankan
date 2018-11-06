package category.BFS;

import java.util.*;

import static category.BFS.Constant.largeTestData;

/**
 * Created by brian Zhang on 8/21/18.
 * <p>
 * Bidirectional BreadthFirstSearch search
 */
public class WordLadderTwoEndBFS {

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || !wordList.contains(endWord))
            return 0;

        Set<String> beginSet = new HashSet<String>(), endSet = new HashSet<String>();

        int steps = 1;

        beginSet.add(beginWord);
        endSet.add(endWord);

        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            System.out.println("beginSet-start: " + beginSet);
            System.out.println("steps: " + steps);
            //Optimal way, 选小的queue去扩展
            if (beginSet.size() > endSet.size()) {
                System.out.println("beginSet: " + beginSet);
                System.out.println("endSet: " + endSet);
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
            }

            Set<String> temp = new HashSet<String>();
            for (String word : beginSet) {
                char[] chs = word.toCharArray();

                for (int i = 0; i < chs.length; i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        char old = chs[i];
                        chs[i] = c;
                        String target = String.valueOf(chs);

                        if (endSet.contains(target)) {
                            return steps + 1;
                        }

                        if (wordList.contains(target)) {
                            temp.add(target);
                            wordList.remove(target);
                        }
                        chs[i] = old;
                    }
                }
            }

            beginSet = temp;
            steps++;
        }

        return 0;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        System.out.println(ladderLength("hit", "log",
                new ArrayList<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"))));

        //System.out.println(ladderLength("nanny", "aloud", new ArrayList(largeTestData)));
    }
}