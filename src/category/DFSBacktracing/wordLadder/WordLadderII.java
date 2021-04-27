package category.DFSBacktracing.wordLadder;

import java.util.*;

/**
 * https://leetcode.com/problems/word-ladder-ii/
 *
 * Step-1: create a map that node -> all neighbors' node
 * Step-2: use dfsHelper to traverse the neighbors' map to get all the paths
 */
public class WordLadderII {
    public static void main(String[] args) {
        WordLadderII wordLadder2 = new WordLadderII();
        System.out.println(wordLadder2.findLadders("hit", "cog", new ArrayList<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"))));
    }

    // record all node's neighbor nodes, then use dfsHelper to figure out the path
    HashMap<String, List<String>> neighborsMap = new HashMap<>();

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (wordList.isEmpty()) return res;

        for (String word : wordList)
            neighborsMap.put(word, new ArrayList<>());

        neighborsMap.put(beginWord, new ArrayList<>());

        boolean found = false;
        HashSet<String> visited = new HashSet<>(), unvisited = new HashSet<>(wordList);
        visited.add(beginWord);
        unvisited.remove(beginWord);

        while (!visited.isEmpty()) {
            if (found) break;

            HashSet<String> temp = new HashSet<>();
            for (String s : visited) {
                for (int i = 0; i < s.length(); i++) {
                    StringBuilder sb = new StringBuilder(s);
                    for (char c = 'a'; c <= 'z'; c++) {
                        sb.setCharAt(i, c);
                        String newWord = sb.toString();
                        if (unvisited.contains(newWord)) {
                            if (newWord.equals(endWord))
                                found = true;

                            temp.add(newWord);
                            neighborsMap.get(s).add(newWord); // building the neighbour map
                        }
                    }
                }
            }
            unvisited.removeAll(temp);
            visited = temp;
        }

        dfs(beginWord, endWord, new ArrayList<>());
        return res;
    }

    List<List<String>> res = new ArrayList<>();

    //dfsHelper backtracking: the exit condition will have the return for backtracking, but the simple dfsHelper don't need return.
    private void dfs(String beginWord, String endWord, List<String> temp) {
        temp.add(beginWord);
        if (beginWord.equals(endWord)) {
            res.add(new ArrayList<>(temp)); //deep copy
            return;
        }

        for (String next : neighborsMap.get(beginWord)) {
            dfs(next, endWord, temp);
            temp.remove(temp.size() - 1);
        }
    }
}