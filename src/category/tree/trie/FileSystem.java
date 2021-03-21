package category.tree.trie;

import java.util.*;

/**
 * https://leetcode.com/problems/design-in-memory-file-system/
 * Trie
 * Created by brianzhang on 1/28/21.
 */
public class FileSystem {

    public static void main(String[] args) {
        FileSystem fs = new FileSystem();
        System.out.println(fs.ls("/"));
        fs.mkdir("/a/b/c");
        fs.addContentToFile("/a/b/c/d", "hello");
        System.out.println(fs.ls("/"));
        System.out.println(fs.readContentFromFile("/a/b/c/d"));

        new TreeMap<String, Node>().values();
    }

    private class Node { // directory and file both are Node
        private TreeMap<String, Node> children;
        private StringBuilder fileContent;
        private String name; // directory path or file name

        public Node(String name) {
            children = new TreeMap<>();
            fileContent = new StringBuilder();
            this.name = name;
        }

        public String getContent(){
            return fileContent.toString();
        }

        public String getName(){
            return name;
        }

        public void addContent(String content){
            fileContent.append(content);
        }

        public boolean isFile(){
            return fileContent.length() > 0;
        }

        public List<String> getList() {
            List<String> list = new ArrayList<>();
            if(isFile()){
                list.add(getName());
            }else{
                list.addAll(children.keySet());
            }

            return list;
        }
    }

    private Node root;

    public FileSystem() {
        root = new Node("");
    }

    public List<String> ls(String path) {
        return findNode(path).getList();
    }

    public void mkdir(String path) {
        findNode(path);
    }

    public void addContentToFile(String filePath, String content) {
        findNode(filePath).addContent(content);
    }

    public String readContentFromFile(String filePath) {
        return findNode(filePath).getContent();
    }

    private Node findNode(String path) { // find path and file
        String[] paths = path.split("/");

        Node cur = root;
        for(String p : paths){
            if(p.length() == 0) continue;

            cur.children.putIfAbsent(p, new Node(p));
            cur = cur.children.get(p);

            if(cur.isFile()) break; // if it's file, we stop as it's end of this path, we can't go further.
        }

        return cur;
    }
}
