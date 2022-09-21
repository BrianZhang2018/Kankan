package category.ImplDataStructure;

/**
 * https://www.geeksforgeeks.org/priority-queue-using-linked-list/
 *
 * Created by brianzhang on 3/9/20.
 */

class ImplementPriorityQueue {
    public static void main(String args[]){
        ImplementPriorityQueue test = new ImplementPriorityQueue();
        Node pq = test.newNode(5, 1);
        pq =test.push(pq, 6, 2);
        pq =test.push(pq, 7, 3);
        pq =test.push(pq, 4, 0);

        while (test.isEmpty(pq)==0) {
            System.out.printf("%d ", test.peek(pq));
            pq=test.pop(pq);
        }
    }

    class Node {
        int data;
        int priority;// Lower values indicate higher priority

        Node next;
    }

    // Function to Create A New Node
     Node newNode(int d, int p) {
        Node temp = new Node();
        temp.data = d;
        temp.priority = p;
        temp.next = null;

        return temp;
    }

    // Function to push according to priority
     Node push(Node head, int d, int p) {
        Node start = head;

        // Create new Node
        Node temp = newNode(d, p);

        // Special Case: The head of list has lesser priority than new node.
         // So insert new node before head node and change head node.
        if ((head).priority > p) {
            // Insert New Node before head
            temp.next = head;
            (head) = temp;
        }
        else {
            // Traverse the list and find a position to insert new node
            while (start.next != null && start.next.priority < p) {
                start = start.next;
            }
            // Either at the ends of the list or at required position
            temp.next = start.next;
            start.next = temp;
        }
        return head;
    }

    // Return the value at head
     int peek(Node head)
    {
        return (head).data;
    }

    // Removes the element with the highest priority form the list
     Node pop(Node head) {
        head = head.next;
        return head;
    }

    // Function to check is list is empty
     int isEmpty(Node head)
    {
        return ((head) == null)?1:0;
    }
}