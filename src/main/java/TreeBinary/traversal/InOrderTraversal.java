package TreeBinary.traversal;


import TreeBinary.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class InOrderTraversal {
    // This is Inorder Iterative traversal, bcoz recursion is easy
    // Logic - Left -> Root -> Right
    /*
    1. The Intuition
In a recursive in-order traversal, the function calls itself on the left child repeatedly until it hits null. Only then does it "backtrack" to the parent, print it, and move to the right.
Iteratively, we simulate this with two distinct phases in a loop:
Drilling Down: Keep pushing the current node to the stack and moving current = current.left until you hit null.
Processing & Pivoting: Pop the stack (the "Root"), record its value, and then pivot to its Right child to repeat the process.
2. Java Implementation (Iterative)
This uses ArrayDeque as a faster, non-synchronized stack.
     */

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        System.out.println(getInorderNodes(root) ) ;
    }

    private static List<Integer> getInorderNodes(TreeNode root) {

        return traverseInorder(root);
    }

    private static List<Integer> traverseInorder(TreeNode root) {

        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;

        // Continue if there's a node to visit OR nodes waiting on the stack
        while (curr != null || !stack.isEmpty()) {

            // 1. Drill down to the leftmost leaf
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            // 2. Backtrack: curr is now null, so pop the last 'parent'
            curr = stack.pop();
            result.add(curr.val); // Process Root

            // 3. Pivot to the right subtree
            curr = curr.right;
        }
        return result;
    }


}
