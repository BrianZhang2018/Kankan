package category.tree.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/add-and-search-word-data-structure-design/
 *
 * Created by brianzhang on 5/12/20.
 */
public class AddSearchWordDataStructureDesign {

    public static void main(String[] args) {
        AddSearchWordDataStructureDesign test = new AddSearchWordDataStructureDesign();
        test.addWord("bad");test.addWord("dad");test.addWord("mad");
        System.out.println(test.search("pad"));
        System.out.println(test.search("bad"));
        System.out.println(test.search(".ad"));
        System.out.println(test.search("b.."));
    }
    TrieNode root;
    /** Initialize your data structure here. */
    public AddSearchWordDataStructureDesign() {
        root = new TrieNode(' ');
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        if (word == null)
            return;
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char temp = word.charAt(i);
            if (curr.children.get(temp) == null) {
                TrieNode child = new TrieNode(temp);
                curr.children.put(temp, child);
            }
            curr = curr.children.get(temp);
        }

        curr.isWord = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return dfs(root, word, 0);
    }

    public boolean dfs(TrieNode node, String word, int pos){
        if(pos == word.length()) return node.isWord;

        if(!(word.charAt(pos) == '.')){
            return node.children.containsKey(word.charAt(pos)) && dfs(node.children.get(word.charAt(pos)), word, pos+1);
        }else{
            for(Map.Entry<Character, TrieNode> child : node.children.entrySet()){
                if(dfs(child.getValue(), word, pos+1)){
                    return true;
                }
            }
        }
        return false;
    }
}


class TrieNode{
    HashMap<Character, TrieNode> children = new HashMap<>();
    char val;
    boolean isWord = false;

    public TrieNode(char val){
        this.val = val;
    }
}