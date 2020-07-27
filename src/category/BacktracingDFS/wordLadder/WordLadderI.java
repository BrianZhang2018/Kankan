package category.BacktracingDFS.wordLadder;

import java.util.*;

/**
 * https://leetcode.com/problems/word-ladder/
 *
 * Created by brianzhang on 7/19/20.
 */
public class WordLadderI {

    public static void main(String[] args) {
        WordLadderI test = new WordLadderI();
        System.out.println(test.ladderLength("hit", "cog", new ArrayList<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"))));
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        // COUNT NUMBER OF WORDS TRANSFORMED
        int count = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            // FOR ALL WORDS THIS ROUND
            for (int i = 0; i < size; i++) {
                char[] current = queue.poll().toCharArray();
                // TRAVERSE CURRENT WORD
                for (int j = 0; j < current.length; j++) {
                    char tmp = current[j];
                    // CHANGE ONE LETTER AT A TIME
                    for (char c = 'a'; c <= 'z'; c++) {
                        current[j] = c;
                        String next = new String(current);
                        // WHEN NEXT WORD IS IN THE SET
                        if (set.contains(next)) {
                            if (next.equals(endWord)) return count + 1;
                            queue.add(next);
                            set.remove(next);
                        }
                    }
                    // HAVE TO reset FOR NEXT CHANGE OF LETTER
                    current[j] = tmp;
                }
            }
            // THIS ROUND ENDS WITH ONE LETTER CHANGED
            count++;
        }
        return 0;
    }

/*    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.size() == 0) {
            return 0;
        }
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) {
            return 0;
        }
        Set<String> bSet = new HashSet<>(Arrays.asList(beginWord)), eSet = new HashSet<>(Arrays.asList(endWord));
        return bfs(bSet, eSet, dict, 1);
    }

    public int bfs(Set<String> bSet, Set<String> eSet, Set<String> dict, int level) {
        if (bSet.size() == 0 || eSet.size() == 0) return 0;

        dict.removeAll(bSet);
        Set<String> temp = new HashSet<>();
        for (String s : bSet) {
            char[] ch = s.toCharArray();
            for (int i = 0; i < ch.length; i++) {
                char old = ch[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    ch[i] = c;
                    String word = new String(ch);
                    if (dict.contains(word)) {
                        if (eSet.contains(word))
                            return level + 1;
                        else
                            temp.add(word);
                    }
                }
                ch[i] = old;
            }
        }
        return temp.size() != 0 && temp.size() > eSet.size() ? bfs(eSet, temp, dict, level + 1) : bfs(temp, eSet, dict, level + 1);
    }*/
}
