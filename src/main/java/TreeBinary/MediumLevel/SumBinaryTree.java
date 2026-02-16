package TreeBinary.MediumLevel;

import TreeBinary.TreeNode;

public class SumBinaryTree {
    /*
     Given a binary tree. Check whether the given tree is a Sum-binary Tree or not.
    Sum-binary Tree is a Binary Tree where the value of a every node is equal to sum of the nodes present in its left subtree and right subtree.
    An empty tree is Sum-binary Tree and sum of an empty tree can be considered as 0. A leaf node is also considered as SumTree.
    Return 1 if it sum-binary tree else return 0.
     */

    public static void main(String[] args) {
        SumBinaryTree binaryTree = new SumBinaryTree();
        TreeNode root = new TreeNode(26);
        root.left = new TreeNode(10);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(6);
        root.right.right = new TreeNode(3);

        int check = binaryTree.checkSum(root);
        System.out.println("check " + check);
    }

    private int checkSum(TreeNode node) {
        // Base case: Empty tree has sum 0
        if (node == null) return 0;

        // Base case: Leaf node is a Sum Tree; return its value
        if (node.left == null && node.right == null)
            return node.val;

        // Recursive calls for left and right subtrees
        int leftSum = checkSum(node.left);
        int rightSum = checkSum(node.right);

        // If either subtree is not a Sum Tree, propagate -1 upwards
        if (leftSum == -1 || rightSum == -1) return -1;

        // Check if current node satisfies the Sum Tree property
        if (node.val == leftSum + rightSum) {
            // Total sum of this Sum Tree is (root value + sum of subtrees)
            // or simply 2 * node.data
            return node.val + leftSum + rightSum;
        }
        return -1; // Violates property
    }
    /*
    Time Complexity: (O(n)), as it visits each node exactly once.
    Space Complexity: (O(h)), where \(h\) is the height of the tree, due to the recursion stack
     */


}
