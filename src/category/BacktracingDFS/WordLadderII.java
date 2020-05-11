package category.BacktracingDFS;

import java.util.*;

/**
 * Step-1: create a map that key-value is node -> all neighbors' node
 * Step-2: use dfs to traverse the neighbors' map to get all the paths
 */
public class WordLadderII {
    public static void main(String[] args) {
        WordLadderII wordLadder2 = new WordLadderII();
        System.out.println(wordLadder2.findLadders("hit", "cog", new ArrayList<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"))));
    }

    //record all node's neighbors' node, then use dfs to figure out the path
    HashMap<String, List<String>> neighborsMap = new HashMap<>();
    List<List<String>> res = new ArrayList<>();
    List<String> list = new ArrayList<>();

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (wordList.isEmpty())
            return res;

        for (String word : wordList) {
            neighborsMap.put(word, new ArrayList<>());
        }
        neighborsMap.put(beginWord, new ArrayList<>());

        boolean found = false;
        HashSet<String> visited = new HashSet<>();
        HashSet<String> unvisited = new HashSet<>(wordList);
        visited.add(beginWord);
        unvisited.remove(beginWord);

        while (!visited.isEmpty()) {
            if (found)
                break;
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

                            //building the neighbour map
                            neighborsMap.get(s).add(newWord);
                        }
                    }
                }
            }
            unvisited.removeAll(temp);
            visited = temp;
        }

        dfs(beginWord, endWord);
        return res;
    }

    //dfs + backtracking: the exit condition will have the return for backtracking, but the simply dfs don't need return.
    private void dfs(String beginWord, String endWord) {
        list.add(beginWord);
        if (beginWord.equals(endWord)) {
            //deep copy -> new ArrayList<String>(list), otherwise the value will be changed
            res.add(new ArrayList<String>(list));
        }

        for (String next : neighborsMap.get(beginWord)) {
            dfs(next, endWord);
            list.remove(list.size() - 1);
        }
    }
}