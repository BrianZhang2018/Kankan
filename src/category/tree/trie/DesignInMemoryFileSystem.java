package category.tree.trie;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * https://leetcode.com/problems/design-in-memory-file-system/
 * CoinBase
 * Trie
 *
 * 改了dir找不到throw exception, file 找不到throw exception, replace the 內容 問要怎麼scale 如果沒有database
 * Created by brianzhang on 1/28/21.
 */
public class DesignInMemoryFileSystem {

    public static void main(String[] args) throws DirOrFileNotFound {
        DesignInMemoryFileSystem fs = new DesignInMemoryFileSystem();
        System.out.println(fs.ls("/"));
        fs.mkdir("/a/b/c");
        fs.addContentToFile("/a/b/c/d", "hello");
        System.out.println(fs.ls("/"));
        System.out.println(fs.readContentFromFile("/a/b/c/d"));
    }

    private class Node { // directory and file are Node
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

    public DesignInMemoryFileSystem() {
        root = new Node("");
    }

    public List<String> ls(String path) throws DirOrFileNotFound {
        Node target = findNode(path);
        if(target == null) {
            throw new DirOrFileNotFound("Path Not Found");
        }
        return target.getList();
    }

    public void mkdir(String path) {
        findNode(path);
    }

    public void addContentToFile(String filePath, String content) throws DirOrFileNotFound {
        Node target = findNode(filePath);
        if(target == null) {
            throw new DirOrFileNotFound("Path Not Found");
        }
        target.addContent(content);
    }

    public String readContentFromFile(String filePath) throws DirOrFileNotFound{
        Node target = findNode(filePath);
        if(target == null) {
            throw new DirOrFileNotFound("Path Not Found");
        }
        return target.getContent();
    }

    private Node findNode(String path) { // find path and file
        String[] paths = path.split("/");

        Node cur = root;
        for(String p : paths){
            if(p.equals("")) continue;

            cur.children.putIfAbsent(p, new Node(p));
            cur = cur.children.get(p);

            if(cur.isFile()) break; // if it's file, we stop as it's end of this path, we can't go further.
        }

        return cur;
    }
}

class DirOrFileNotFound extends Exception {
    public DirOrFileNotFound(String errorMsg) {
        super(errorMsg);
    }
}
