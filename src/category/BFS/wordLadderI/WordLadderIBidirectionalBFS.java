package category.BFS.wordLadderI;

import java.util.*;
/**
 * Pre-processed adjacent nodes + bidirectional bfs
 *
 * Time complexity:
 * Created by brianzhang on 12/3/20.
 */
public class WordLadderIBidirectionalBFS {
    public static void main(String[] args) {
        System.out.println(ladderLength("hit", "cog", new ArrayList<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"))));
    }

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int wl = beginWord.length();  // since all words are of same length.

        // Pre-processing: get all adjacent wildcard words by changing one letter to "*"
        Map<String, List<String>> allComboDict = new HashMap<>();
        wordList.forEach(word -> {
            for (int i = 0; i < wl; i++) {
                // Key: wildcard word, Value: a list of words which match the wildcard word
                String newWord = word.substring(0, i) + '*' + word.substring(i + 1, wl);
                allComboDict.computeIfAbsent(newWord, k -> new ArrayList<>()).add(word);
            }
        });

        // Queue for BFS
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);
        // Visited to make sure we don't repeat processing same word.
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        int steps=1;
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            if (beginSet.size() > endSet.size()) {
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
            }
            Set<String> temp = new HashSet<>(); // temp set to store the next level words
            for(String word : beginSet){
                for (int i = 0; i < wl; i++) {
                    // Intermediate words for current word
                    String wildCardWord = word.substring(0, i) + '*' + word.substring(i + 1, wl);
                    // get all the words which match the wildcard word, e.g. h*t.
                    for (String adjacentWord : allComboDict.getOrDefault(wildCardWord, new ArrayList<>())) {
                        if (adjacentWord.equals(endWord)) {
                            return steps + 1;
                        }
                        // Otherwise, add it to the BFS Queue. Also mark it visited
                        if (visited.add(adjacentWord)) {
                            temp.add(adjacentWord);
                        }
                    }
                }
            }
            beginSet = temp; // update the beginSet to the next level
            steps++;
        }
        return 0;
    }
}
