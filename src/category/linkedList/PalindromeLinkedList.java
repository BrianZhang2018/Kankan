package category.linkedList;

/**
 * Explanation
 * Example :
   1-> 2-> 3-> 4-> 2-> 1

    ref points 1 initially.
    Make recursive calls until you reach the last element - 1.
    On returning from each recursion, check if it is equal to ref values.
    ref values are updated to on each recursion.
    So first check is ref 1 -  end 1
    Second ref 2 - second last 2 ...and so on.
 * 
 */
public class PalindromeLinkedList{

    ListNode ref;
    public boolean isPalindrome(ListNode head) {
        if(head == null) return true;

        ref = head;
        return dfs(head);
    }
    
    public boolean dfs(ListNode node) {
        if(node == null) return true;

        boolean res = dfs(node.next);

        if(!res) return false;

        if(ref.val == node.val){
            ref = ref.next;
            return true;
        }

        return false;
    }
}