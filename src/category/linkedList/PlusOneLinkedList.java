package category.linkedList;

/**
 * https://leetcode.com/problems/plus-one-linked-list/
 *
 * [9, 9]
 * Output: [1, 0, 0]
 *
 * Created by brianzhang on 2/24/21.
 */
public class PlusOneLinkedList {

    public static void main(String[] args) {}

    public ListNode plusOne(ListNode head) {
        ListNode curr = head;
        if(dfs(curr) > 0) {
            ListNode newHead = new ListNode(1);
            newHead.next = head;
            return newHead;
        }
        return head;
    }

    public int dfs(ListNode node){
        if(node == null) return 1;

        int carry = dfs(node.next);
        if(carry > 0) {
            if(node.val + carry >=10){
                node.val = 0;
                return 1;
            }else{
                node.val +=1;
            }
        }
        return 0;
    }
}
