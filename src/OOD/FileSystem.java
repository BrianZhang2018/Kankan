package OOD;

import java.util.*;

/**
 * https://leetcode.com/articles/design-in-memory-file-system/
 */
public class FileSystem {
    public static void main(String[] args){
        FileSystem fs = new FileSystem();
        System.out.println(fs.ls("/"));
        fs.mkdir("/a/b/c");
        fs.addContentToFile("/a/b/c/d", "hello"); //'d' is the fileName, 'hello' is the content
        System.out.println(fs.ls("/"));
        System.out.println(fs.readContentFromFile("/a/b/c/d"));
    }
    //data structure, the key of this problem
    class Dir {
        HashMap <String, Dir> dirs = new HashMap <> ();
        HashMap <String, String> files = new HashMap <> ();
    }

    Dir root;
    public FileSystem() {
        root = new Dir();
    }

    public List <String> ls(String path) {
        Dir t = root;
        List <String> files = new ArrayList < > ();
        if (!path.equals("/")) {
            String[] d = path.split("/");
            //loop to the second last subpath as the last subpath may dir or file, so will check in the below
            for (int i = 1; i < d.length - 1; i++) {
                t = t.dirs.get(d[i]);
            }
            //if path is a file path
            if (t.files.containsKey(d[d.length - 1])) {
                files.add(d[d.length - 1]);
                return files;
            } else { // the path is a dir path
                t = t.dirs.get(d[d.length - 1]);
            }
        }

        files.addAll(new ArrayList <> (t.dirs.keySet()));
        files.addAll(new ArrayList <> (t.files.keySet()));
        Collections.sort(files);
        return files;
    }

    public void mkdir(String path) {
        Dir t = root;
        String[] d = path.split("/");
        for (int i = 1; i < d.length; i++) {
            if (!t.dirs.containsKey(d[i]))
                t.dirs.put(d[i], new Dir());
            t = t.dirs.get(d[i]);
        }
    }

    public void addContentToFile(String filePath, String content) {
        Dir t = root;
        String[] d = filePath.split("/");
        for (int i = 1; i < d.length - 1; i++) {
            if(!t.dirs.containsKey(d[i]))
                t.dirs.put(d[i], new Dir());

            t = t.dirs.get(d[i]);
        }
        t.files.put(d[d.length - 1], t.files.getOrDefault(d[d.length - 1], "") + content);
    }

    public String readContentFromFile(String filePath) {
        Dir t = root;
        String[] d = filePath.split("/");
        for (int i = 1; i < d.length - 1; i++) {
            t = t.dirs.get(d[i]);
        }
        return t.files.get(d[d.length - 1]);
    }
}