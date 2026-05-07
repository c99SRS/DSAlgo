package TreeBinarySearch.mediumLevel;

import TreeBinary.TreeNode;
import java.util.*;

public class BSTCheck {
    /*
        Check if a Binary Tree is a Binary Search Tree
        Problem Statement: Given the root of a binary tree, determine if it is a valid binary search tree (BST).

        A valid BST is defined as follows:
            The left subtree of a node contains only nodes with keys less than the node's key.
            The right subtree of a node contains only nodes with keys greater than the node's key.
            Both the left and right subtrees must also be binary search trees.
     */

    // Approach 1: In-order Traversal → Array → Check if Sorted
    // Time: O(n), Space: O(n)
    public static boolean isBSTApproach1(TreeNode root) {
        List<Integer> values = new ArrayList<>();
        inorderTraversal(root, values);
        System.out.println("list of values: "+ values);
        
        for (int i = 1; i < values.size(); i++) {
            if (values.get(i) <= values.get(i - 1)) {
                return false;
            }
        }
        return true;
    }
    
    private static void inorderTraversal(TreeNode node, List<Integer> values) {
        if (node == null) return;
        inorderTraversal(node.left, values);
        values.add(node.val);
        inorderTraversal(node.right, values);
    }

    // Best for Interviews: Avoids extra space by checking values during in-order traversal
    // Approach 2: In-order Traversal with Value Checking (Most Efficient)
    // Time: O(n), Space: O(h) - only stack depth
    private static long previousValue = Long.MIN_VALUE;
    
    public static boolean isBSTApproach2(TreeNode root) {
        previousValue = Long.MIN_VALUE;
        return inorderCheck(root);
    }
    
    private static boolean inorderCheck(TreeNode node) {
        if (node == null) return true;
        
        // Check left subtree
        if (!inorderCheck(node.left))
            return false;
        
        // Check current node
        if (node.val <= previousValue)
            return false;
        previousValue = node.val;
        
        // Check right subtree
        return inorderCheck(node.right);
    }

    // Approach 3: Min-Max Range Validation
    // Time: O(n), Space: O(h)
    public static boolean isBSTApproach3(TreeNode root) {
        return validateBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    private static boolean validateBST(TreeNode node, long min, long max) {
        if (node == null) return true;
        
        // Current node must be within range
        if (node.val <= min || node.val >= max) {
            return false;
        }
        
        // Left subtree: all values must be < node.val
        // Right subtree: all values must be > node.val
        return validateBST(node.left, min, node.val) &&
               validateBST(node.right, node.val, max);
    }

    // Approach 4: Iterative In-order Traversal (Stack-based)
    // Time: O(n), Space: O(h)
    public static boolean isBSTApproach4(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        long previousValue = Long.MIN_VALUE;
        
        while (current != null || !stack.isEmpty()) {
            // Go to left-most node
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            
            // Current is null, pop from stack
            current = stack.pop();
            
            // Check if current value is greater than previous
            if (current.val <= previousValue) {
                return false;
            }
            previousValue = current.val;
            
            // Visit right subtree
            current = current.right;
        }
        return true;
    }

    public static void main(String[] args) {
        // Test case 1: Valid BST
        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(6);
        root1.left.left = new TreeNode(2);
        root1.left.right = new TreeNode(4);
        root1.right.right = new TreeNode(7);
        
        System.out.println("Test Case 1 (Valid BST):");
        System.out.println("Approach 1: " + isBSTApproach1(root1));
        System.out.println("Approach 2: " + isBSTApproach2(root1));
        System.out.println("Approach 3: " + isBSTApproach3(root1));
        System.out.println("Approach 4: " + isBSTApproach4(root1));
        
        // Test case 2: Invalid BST
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(3);
        root2.right = new TreeNode(4);  // Invalid: 4 < 5 but on right
        
        System.out.println("\nTest Case 2 (Invalid BST):");
        System.out.println("Approach 1: " + isBSTApproach1(root2));
        System.out.println("Approach 2: " + isBSTApproach2(root2));
        System.out.println("Approach 3: " + isBSTApproach3(root2));
        System.out.println("Approach 4: " + isBSTApproach4(root2));
    }

}
