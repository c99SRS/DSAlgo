package TreeBinary.MediumLevel;

import TreeBinary.TreeNode;

public class DiameterOfBinaryTree {

    static int maxDiameter=0;

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
        calculateHeight(root);

        return maxDiameter;
    }

    private static int calculateHeight(TreeNode root) {
        if (root == null)
            return 0;

        int leftHeight = calculateHeight(root.left);
        int rightHeight = calculateHeight(root.right);

        maxDiameter = Math.max(maxDiameter, leftHeight+rightHeight);

        return 1 + Math.max(leftHeight, rightHeight);
    }

}
