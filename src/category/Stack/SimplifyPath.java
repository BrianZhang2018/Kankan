package category.Stack;
import java.util.Stack;

/**
 * https://leetcode.com/problems/simplify-path/
 * */
class SimplifyPath {
    public static void main(String[] args) {
        System.out.println(simplifyPath("/.../a/../b/c/../d/./"));
        Stack<String> stack = new Stack<>();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        for(String str : stack) {
            System.out.println(str);
        }
    }

    public static String simplifyPath(String path) {
        if(path == null || path.length() == 0) return path;
        
        Stack<String> stack = new Stack();
        String[] strs = path.split("/");
        
        for(String str : strs) {
            if(str.equals("") || str.equals(".")) continue;
            
            if(str.equals("..")) {
                if(!stack.isEmpty())
                    stack.pop();
            }else{
                stack.push(str);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(String str : stack) { 
            sb.append("/" + str);
        }
        
        return stack.isEmpty() ? "/" : sb.toString();
    }
}
