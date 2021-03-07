package category.Queue.ArrayDeque;

import javafx.util.Pair;

import java.util.*;

/**
 * https://leetcode.com/problems/min-stack
 *
 * Created by brianzhang on 3/7/21.
 */
public class MinStack {
    public static void main(String[] args) {
        MinStack ms = new MinStack();
        ms.push(-2);
        ms.push(0);
        ms.push(-3);
        System.out.println(ms.getMin());
        ms.pop();
        System.out.println(ms.top());
        System.out.println(ms.getMin());
    }

    Deque<Pair<Integer, Integer>> stack = new ArrayDeque();

    public void push(int x) {
        if(stack.size() == 0){
            stack.push(new Pair(x, x));
        }else{
            int minVal = Math.min(stack.peek().getValue(), x); // got current minimum
            stack.push(new Pair(x, minVal));
        }
    }

    public void pop() {
        if(stack.size() == 0) return;

        stack.pop(); // stack.removeFirst()
    }

    public int top() {
        if(stack.size() == 0) return 0;

        return stack.peek().getKey(); // stack.getFirst().getKey()
    }

    public int getMin() {

        return stack.size() == 0 ? 0 : stack.peek().getValue();
    }
}

// same idea with above one, but use a customized node structure
class MinStack1 {

    static class Node{
        int val;
        int min;
        Node next;

        public Node(int val){
            this.val = val;
        }
    }

    Node head;

    public MinStack1() {}

    public void push(int x) {
        Node newNode = new Node(x);
        newNode.min = (head == null) ? x : Math.min(x, head.min); // find current minimum value
        if(head != null){
            newNode.next = head;
        }
        head = newNode;
    }

    public void pop() {
        if(head != null){
            head = head.next;
        }
    }

    public int top() {
        if(head != null){
            return head.val;
        }else{
            return Integer.MIN_VALUE;
        }
    }

    public int getMin() {
        if(head != null){
            return head.min;
        }else{
            return Integer.MIN_VALUE;
        }
    }
}
