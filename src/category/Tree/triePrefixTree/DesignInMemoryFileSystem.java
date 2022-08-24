package category.Tree.triePrefixTree;

import java.util.*;

/**
 * https://leetcode.com/problems/design-in-memory-file-system/
 *
 * Time complexity: O(m): m is the length of input path string,
 * Explanation: if the length of the path string is m, then the max levels of hierarchies (intermediate directories) it can contain is at most m/2,
 * where each directory is a single character. Thus, the levels we must enter is n = m/2 = O(m).
 *
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

    class TNode { // Tri Node, both directory and file are Node
        private TreeMap<String, TNode> children;
        private StringBuilder fileContent;
        private String name; // directory path or file name

        public TNode(String name) {
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

    private TNode root;

    public DesignInMemoryFileSystem() {
        root = new TNode("");
    }

    public List<String> ls(String path) throws DirOrFileNotFound {
        TNode target = findNode(path);
        if(target == null) {
            throw new DirOrFileNotFound("Path Not Found");
        }
        return target.getList();
    }

    public void mkdir(String path) {
        findNode(path);
    }

    public void addContentToFile(String filePath, String content) throws DirOrFileNotFound {
        TNode target = findNode(filePath);
        if(target == null) throw new DirOrFileNotFound("Path Not Found");
        target.addContent(content);
    }

    public String readContentFromFile(String filePath) throws DirOrFileNotFound{
        TNode target = findNode(filePath);
        if(target == null)  throw new DirOrFileNotFound("Path Not Found");

        return target.getContent();
    }

    private TNode findNode(String path) { // find path and file
        String[] paths = path.split("/");
        TNode cur = root;
        for(String p : paths){
            if(p.equals("")) continue; // skip the first "" in paths
            cur.children.putIfAbsent(p, new TNode(p));
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
