package category.RightDataStructure.twoStack;

import java.util.Stack;

/**
 * https://leetcode.com/problems/min-stack
 *
 * Created by brianzhang on 1/24/21.
 */
public class MinStack {
    Node head;

    /** initialize your data structure here. */
    public MinStack() {

    }

    public void push(int x) {
        Node temp = new Node(x);
        temp.min = (head == null) ? x : Math.min(x, head.min);
        if(head != null){
            temp.next = head;
        }
        head = temp;
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

class Node{
    int val;
    Node next;
    int min;

    public Node(int val){
        this.val = val;
    }
}

// two stack solution
class MinStack1 {

    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();


    public MinStack1() { }


    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        }
    }


    public void pop() {
        if (stack.peek().equals(minStack.peek())) {
            minStack.pop();
        }
        stack.pop();
    }


    public int top() {
        return stack.peek();
    }


    public int getMin() {
        return minStack.peek();
    }
}



