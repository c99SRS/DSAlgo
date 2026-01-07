package TreeBinary.MediumLevel;

import TreeBinary.TreeNode;

public class HeightBalancedTree {
    // Problem Statement: Given a Binary Tree, return true if it is a Balanced Binary Tree else return false. A Binary Tree is balanced if,
    // for all nodes in the tree, the difference between left and right subtree height is not more than 1..
    public static void main(String[] args) {
        // Creating a sample binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(8);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        root.right.left.left = new TreeNode(6);
        //root.left.right.right.right = new TreeNode(7);

        // Creating an instance of the Solution class
        HeightBalancedTree solution = new HeightBalancedTree();

        // Checking if the tree is balanced
        if (solution.isBalanced(root)) {
            System.out.println("The tree is balanced.");
        } else {
            System.out.println("The tree is not balanced.");
        }
    }

    // Optimal Approach
    /*
      Algorithm
The O(N²) time complexity of the previous approach can be optimized by checking the balance condition while traversing the tree in a bottom-up manner. Instead of repeatedly calculating the height at each node, we compute subtree heights during postorder traversal and evaluate the balance condition at the same time.

This avoids redundant height calculations and allows early detection of unbalanced nodes, thereby preventing unnecessary recursive calls. Postorder traversal helps ensure that we already have the height information of both subtrees when we assess the balance condition at any node.

Traverse the Binary Tree in post-order using recursion: visit the left subtree, then the right subtree, and finally the current node.
At each node, compute the height of its left and right subtrees using recursive calls.
Check the absolute difference between the heights of the left and right subtrees. If the difference is greater than 1, or if either subtree is already unbalanced (returns -1), return -1 to indicate an unbalanced state.
If the subtree is balanced, return the height of the current node as max(left height, right height) + 1.
Continue the traversal until all nodes are visited. If no -1 is returned, the tree is balanced.
     */
    private boolean isBalanced(TreeNode root) {

        return dfsHeight(root) != -1;
    }

    // Recursive function to calculate the height of the tree
    public int dfsHeight(TreeNode root) {
        // Base case: if the current node is NULL, return 0 (height of an empty tree)
        if (root == null) return 0;

        // Recursively calculate the height of the left subtree
        int leftHeight = dfsHeight(root.left);

        // If the left subtree is unbalanced, propagate the unbalance status
        if (leftHeight == -1)
            return -1;

        // Recursively calculate the height of the right subtree
        int rightHeight = dfsHeight(root.right);

        // If the right subtree is unbalanced, propagate the unbalance status
        if (rightHeight == -1)
            return -1;

        // Check if the difference in height between left and right subtrees is greater than 1
        // If it's greater, the tree is unbalanced, return -1 to propagate the unbalance status
        if (Math.abs(leftHeight - rightHeight) > 1)
            return -1;

        // Return the maximum height of left and right subtrees plus 1 (for the current node)
        return Math.max(leftHeight, rightHeight) + 1;
    }





    // Below is Bruteforce Approach bcoz here Time complexity is O(N*N)
    /*
      Complexity Analysis

    Time Complexity: O(N2), where N is the number of nodes in the binary tree. For each node, we calculate the height of its left and right subtrees,
    and height calculation takes O(N) in the worst case, leading to an overall O(N × N) = O(N²).
    Space Complexity: O(H), where H is the height of the tree. This space is used by the recursive call stack of the getHeight function.
    In the worst case (skewed tree), H = N, and in the best case (balanced tree), H = log N. No additional data structures are used, so auxiliary space remains constant.
     */
    /*
    private boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;

        int leftHeight = findHeight(root.left);
        int rightHeight = findHeight(root.right);

        if (Math.abs(rightHeight - leftHeight) <= 1 && isBalanced(root.left) && isBalanced(root.right))
            return true;

        return false;
    }
    private int findHeight(TreeNode node){

        if (node == null)
            return 0;

        return 1 + Math.max(findHeight(node.left), findHeight(node.right));
    }
*/
}
