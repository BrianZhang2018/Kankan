package category.ImplDataStructure;

// single linked list which contains limited size array
public class ImplementQueueByLimitedSizeOfArray {
    public static void main(String[] args) {
        ImplementQueueByLimitedSizeOfArray queue = new ImplementQueueByLimitedSizeOfArray();
        queue.offer(1);
        queue.offer(2);
        System.out.println(queue.poll()); // 1
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        queue.offer(6);
        queue.offer(7);
        queue.offer(8);
        queue.offer(9);
        System.out.println(queue.poll()); // 2
        System.out.println(queue.poll()); // 3
    }

    ListNode head, tail;
    public ImplementQueueByLimitedSizeOfArray() {
        head = new ListNode();
        tail = head;
    }
    public boolean offer(int val) {
        tail = tail.add(val);
        return true;
    }
    public int poll() {
        if(head.isEmpty() && head != tail) {
            head = head.next;
        }
        return head.removeFirst();
    }
}

class ListNode {
    int MAX = 5;
    int[] array = new int[MAX];
    int currSize;
    ListNode next;
    public ListNode(){}

    public ListNode add(int val) {
        if (currSize == MAX) {
            this.next = new ListNode();
            next.array[next.currSize++] = val;
            return next;
        }
        this.array[this.currSize++] = val;
        return this;
    }

    public int removeFirst() {
        if(isEmpty()) return -1;

        int val = this.array[0];
        currSize--;
        for(int i=1; i<=currSize; i++)
            array[i-1] = array[i];
        return val;
    }

    public boolean isEmpty() {return currSize == 0;}

}
