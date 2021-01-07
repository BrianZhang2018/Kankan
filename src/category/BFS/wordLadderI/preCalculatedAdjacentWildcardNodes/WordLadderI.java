package category.BFS.wordLadderI.preCalculatedAdjacentWildcardNodes;

import javafx.util.Pair;
import java.util.*;

/**
 * https://leetcode.com/problems/word-ladder/solution/
 * Return the length of the shortest transformation sequence
 *
 * We will essentially be working with an undirected and unweighted graph with words as nodes and edges between words which differ by just one letter.
 * The problem boils down to finding the shortest path from a start node to a destination node, if there exists one. Hence it can be solved using Breadth First Search approach.
 *
 * Time Complexity: O(N*L), where L is the length of each word and N is the total number of words in the input word list.
 * check the detailed analysis from below code for time complexity
 *
 * Created by brianzhang on 7/19/20.
 */
public class WordLadderI {

    public static void main(String[] args) {
        WordLadderI test = new WordLadderI();
        System.out.println(test.ladderLength("hit", "cog", new ArrayList<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"))));
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int wordLength = beginWord.length(); // Since all words are of same length.

        // Pre-processing: get all adjacent wildcard words by changing one letter to "*"
        Map<String, List<String>> adjacentDict = new HashMap<>();
        // Pre calculate the wildcard word map, Time Complexity: O(N*L), e.g. h*t -> hot, hat
        for(String word : wordList){
            for (int i = 0; i < wordLength; i++) {
                // Key is the generic word (wildcard word)
                // Value is a list of words which have the same intermediate generic word.
                String wildcardWord = word.substring(0, i) + '*' + word.substring(i + 1, wordLength);
                adjacentDict.putIfAbsent(wildcardWord, new ArrayList<>());
                adjacentDict.get(wildcardWord).add(word);
            }
        }
        // Queue for BFS
        Queue<Pair<String, Integer>> queue = new LinkedList<>();
        queue.add(new Pair(beginWord, 1));

        // visited to make sure we don't repeat processing same word.
        Map<String, Boolean> visited = new HashMap<>();
        visited.put(beginWord, true);

        // tc: O(N*L*M) = O(N*L), N is the number of word in wordList, L is the length of word, M=(N*L) is constant complexity since it's pre-calculated in above
        while (!queue.isEmpty()) {  // TC: O(N)
            Pair<String, Integer> node = queue.poll();
            String word = node.getKey();
            int level = node.getValue();

            for (int i = 0; i < wordLength; i++) {   // TC: O(L*M)

                String wildCardWord = word.substring(0, i) + '*' + word.substring(i + 1, wordLength);

                // get all the words which map to the same wildcard word.
                for (String adjacentWord : adjacentDict.getOrDefault(wildCardWord, new ArrayList<>())) {  // TC: O(M), M=N*L, pre-processing in above
                    // reached the end word
                    if (adjacentWord.equals(endWord)) {
                        return level + 1;
                    }
                    // Otherwise, add it to the BFS Queue. Also mark it visited
                    if (!visited.containsKey(adjacentWord)) {
                        visited.put(adjacentWord, true);
                        queue.add(new Pair(adjacentWord, level + 1));
                    }
                }
            }
        }
        return 0;
    }
}
