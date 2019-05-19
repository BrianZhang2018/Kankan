package category.PriorityQueue;

public class SlidingWindowMedianBST {
    private class Node {
        int lcnt;
        int dup;
        long val;
        Node left;
        Node right;
        
        public Node(long val) {
            this.val = val;
            this.dup = 1;
        }
    }
    
    private Node add(Node root, long val) {
        if (root == null) {
            return new Node(val);
        }
        
        if (val == root.val) {
            root.dup++;
        } else if (val > root.val) {
            root.right = add(root.right, val);
        } else if (val < root.val) {
            root.lcnt++;
            root.left = add(root.left, val);
        }
        
        return root;
    }
    
    private void remove(Node root, long val) {
        if (root.val == val) {
            root.dup--;
        } else if (val < root.val) {
            root.lcnt--;
            remove(root.left, val);
        } else {
            remove(root.right, val);
        }
    }
    
    private long findKth(Node root, int k) {
        if (k > root.lcnt && k <= root.lcnt + root.dup) {
            return root.val;
        } else if (k <= root.lcnt) {
            return findKth(root.left, k);
        } else {
            return findKth(root.right, k - root.lcnt - root.dup);
        }
    }
    
    public double[] medianSlidingWindow(int[] nums, int k) {
        Node root = null;
        double[] ret = new double[nums.length - k + 1];
        
        for (int i = 0; i < nums.length; i++) {
            root = add(root, nums[i]);
            
            if (i >= k) {
                remove(root, nums[i - k]);
            }
            
            if (i - k + 1 >= 0) {
                if (k % 2 == 1) {
                    ret[i - k + 1] = findKth(root, k / 2 + 1);
                } else {
                    ret[i - k + 1] = (findKth(root, k / 2) + findKth(root, k / 2 + 1)) / 2.0;
                }
            }
        }
        
        return ret;
    }

}