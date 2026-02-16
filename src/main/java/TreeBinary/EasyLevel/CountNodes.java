package TreeBinary.EasyLevel;

import TreeBinary.TreeNode;

public class CountNodes {
    // Count total Nodes in a COMPLETE Binary Tree

    public static void main(String[] args) {

        // Create binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        // Create solution object
        CountNodes sol = new CountNodes();

        // Count total nodes
        int totalNodes = sol.countNodes(root);

        // Print result
        System.out.println("Total number of nodes in the Complete Binary Tree: " + totalNodes);
    }

    private int countNodes(TreeNode root) {
        if (root == null) return 0;

        int leftHeight = getLeftHeight(root);
        int rightHeight = getRightHeight(root);

        // If heights are equal, it's a perfect binary tree
        if (leftHeight == rightHeight) {
            return (1 << leftHeight) - 1; // 2^h - 1
        }

        // Otherwise, solve recursively
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    private int getLeftHeight(TreeNode node) {
        int height = 0;
        while (node != null){
            height++;
            node = node.left;
        }
        return height;
    }

    private int getRightHeight(TreeNode node) {
        int height = 0;
        while (node != null){
            height++;
            node = node.right;
        }
        return height;
    }

    /*
     Complexity Analysis
     Time Complexity: (O(log N x log N)).At each level, we spend (O(log N)) to find heights.Since it is a complete binary tree, at least one of the subtrees will be perfect at every level, reducing the search space significantly.
     Space Complexity: (O(log N)) due to the recursive call stack depth in a balanced tree.
     */

}
