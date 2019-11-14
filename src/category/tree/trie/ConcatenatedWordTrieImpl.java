package category.tree.trie;

import java.util.*;

/**
 * intuitive from
 * https://leetcode.com/problems/concatenated-words/discuss/95644/102ms-java-Trie-%2B-DFS-solution.-With-explanation-easy-to-understand.
 * Created by brianzhang on 3/12/19.
 */
public class ConcatenatedWordTrieImpl {

    public static void main(String[] args) {
        ConcatenatedWordTrieImpl test = new ConcatenatedWordTrieImpl();
        List<String> res = test.findAllConcatenatedWordsInADict(new String[] {"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"});
        if(res != null){
            for(String str : res)
                System.out.println(str);
        }
    }

    public List<String> findAllConcatenatedWordsInADict(String[] words) {

        List<String> res = new ArrayList<String>();
        if(words == null || words.length ==0)
            return res;
        TrieNode root = new TrieNode(' ');
        HashSet<String> set = new HashSet<String>();
        for(String word : words){
            if (word.length() == 0) {
                continue;
            }
            set.add(word);
        }

        for(String word : set){
            insertTrie(word, root);
        }

        for(String word : set){
            if(isConcatenated(word, root, 0))
                res.add(word);
        }

        return res;
    }

    public boolean isConcatenated(String word, TrieNode root, int count){

        if(word == null || word.length() == 0)
            return false;

        TrieNode curr = root;
        for(int i=0; i<word.length(); i++){
            HashMap<Character, TrieNode> children = curr.children;
            char key = word.charAt(i);
            TrieNode node = children.get(key);

            if(node == null){
                return false;
            }

            if(node.isLastWord){
                if(i == word.length()-1) {
                    return count >= 1;
                }
                if(isConcatenated(word.substring(i+1), root, count+1)) {
                    return true;
                }
            }
            curr = children.get(key);
        }
        return false;
    }

    public void insertTrie(String word, TrieNode root){
        if(word == null || word.length() == 0)
            return;

        TrieNode curr = root;
        for(int i=0; i<word.length(); i++){
            HashMap<Character, TrieNode> children = curr.children;
            char key = word.charAt(i);
            if(!children.containsKey(key)){
                TrieNode newNode = new TrieNode(key);
                children.put(key, newNode);
            }
            curr = children.get(key);
        }

        curr.word = word;
        curr.isLastWord = true;
    }

    class TrieNode{
        HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
        boolean isLastWord = false;
        Character val = ' ';
        String word = "";

        public TrieNode(Character c){
            this.val = c;
        }
    }

}
