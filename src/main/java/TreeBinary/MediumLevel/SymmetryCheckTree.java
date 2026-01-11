package TreeBinary.MediumLevel;

import TreeBinary.TreeNode;

import java.util.List;

public class SymmetryCheckTree {
    /*
      Problem Statement: Given a Binary Tree, determine whether the given tree is symmetric or not.
      A Binary Tree would be Symmetric, when its mirror image is exactly the same as the original tree.
      If we were to draw a vertical line through the centre of the tree, the nodes on the left and right side
      would be mirror images of each other.
     */

    public static void main(String[] args) {
        // Creating a sample binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);

        SymmetryCheckTree solution = new SymmetryCheckTree();

        // Get the Bottom View traversal
        boolean res = solution.checkSymetry(root);

        // Print the result
        System.out.println("Tree is symmetry or not: " + res);
    }

    private boolean checkSymetry(TreeNode root) {
        if (root == null)
            return true;

        boolean ans = isSymmetry(root.left, root.right);

        return ans;
    }

    private boolean isSymmetry(TreeNode root1, TreeNode root2) {
        // 1. If both are null, they are symmetric (mirror images)
        if (root1 == null && root2 == null)
            return true;

        // 2. If only one is null, or values don't match, they are NOT symmetric
        if (root1 == null || root2 == null || root1.val != root2.val)
            return false;

        // 3. Compare left of one with right of other (mirrored)
        return isSymmetry(root1.left, root2.right) && isSymmetry(root1.right, root2.left);
    }


}
