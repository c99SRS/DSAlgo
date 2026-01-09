package TreeBinary.MediumLevel;

import TreeBinary.TreeNode;

public class IdenticalCheck {
    /*
        Check if two trees are identical



0

Problem Statement: Given two Binary Trees, return if true if the two trees are identical, otherwise return false..

Two trees are said to be identical if these three conditions are met for every pair of TreeNodes :

Value of a TreeNode in the first tree is equal to the value of the corresponding TreeNode in the second tree.
Left subtree of this TreeNode is identical to the left subtree of the corresponding TreeNode.
Right subtree of this TreeNode is identical to the right subtree of the corresponding TreeNode.
     */


    public static void main(String[] args) {
        // Creating the first binary tree (TreeNode1)
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);

        // Creating the second binary tree (TreeNode2)
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(4);

        // Creating an instance of the Solution class
        IdenticalCheck solution = new IdenticalCheck();

        // Check if the two binary trees are identical and output the result
        if (solution.isIdentical(root1, root2)) {
            System.out.println("The binary trees are identical.");
        } else {
            System.out.println("The binary trees are not identical.");
        }
    }

    private boolean isIdentical(TreeNode node1, TreeNode node2 ) {

        if (node1 == null && node2 == null)
            return true;

        if (node1 == null || node2 == null)
            return false;

        if (node1.val != node2.val)
            return false;

        return isIdentical(node1.left, node2.left) && isIdentical(node1.right, node2.right) ;
    }

    /*
      Complexity Analysis

Time Complexity: O(N + M) where N is the number of nodes in the first Binary Tree and M is the number of nodes in the second Binary Tree. This complexity arises from visiting each node of both trees during their comparison.

Space Complexity: O(1) as no additional space or data structures are created that are proportional to the input size of the tree.
     */


}
