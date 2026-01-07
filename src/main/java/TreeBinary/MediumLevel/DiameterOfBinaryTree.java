package TreeBinary.MediumLevel;

import TreeBinary.TreeNode;

public class DiameterOfBinaryTree {

    //static int maxDiameter=0;

    public static void main(String[] args) {
        // Creating a sample binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.right.right = new TreeNode(6);
        root.left.right.right.right = new TreeNode(7);


        // Calculate the diameter of the binary tree
        int diameter = diameterOfBinaryTree(root);

        System.out.println("The diameter of the binary tree is: " + diameter);
    }

    private static int diameterOfBinaryTree(TreeNode root) {

        int[] maxDiameter = new int[1];
        calculateHeight(root, maxDiameter);

        return maxDiameter[0];
    }

    private static int calculateHeight(TreeNode node, int[] maxDiameter) {

        if (node == null)
            return 0;

        // Recursively get heights of left and right subtrees
        int leftHeight = calculateHeight(node.left, maxDiameter);
        int rightHeight = calculateHeight(node.right, maxDiameter);

        // Update global max diameter: length of path through the current node
        // (Sum of edges from left and right subtrees)
        maxDiameter[0] = Math.max(maxDiameter[0], leftHeight + rightHeight);

        // Return height of current subtree to the parent
        return 1 + Math.max(leftHeight, rightHeight);
    }

}
