package TreeBinary.MediumLevel;

import TreeBinary.TreeNode;

public class TreePartitionWithNode {
    // This is the extension of tree partition problem with finding out which node or edge from the node to remove
    // means find the node above which it will be 2 equal sum tree
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(7);
        root.left.left =new TreeNode(4);
        root.left.right =new TreeNode(6);
        root.right.left =new TreeNode(5);
        root.right.right =new TreeNode(6);

        TreePartition treePartition = new TreePartition();
        int ans = treePartition.findPartition(root);
        System.out.println("final ans "+ ans);
    }

    public int findTotalSum(TreeNode root){
        if(root == null)
            return 0;

        return  root.val + findTotalSum(root.left)+findTotalSum(root.right);
    }

    // This variable will store the value of the node just above the cutting edge.
    // We use Integer so it can be null if no partition is found.
    private Integer partitionCutNodeValue = null;

    public Integer findPartition(TreeNode root) {
        // Reset the found value for repeated calls (if any)
        partitionCutNodeValue = null;

        int totalSum = findTotalSum(root);
        System.out.println("Total sum: " + totalSum);

        // Call the recursive function. The value returned by this function
        // is the sum of the entire tree, which we ignore in the main method.
        findSumAndCheck(root, totalSum);

        // If cutNodeValue is null, no valid partition was found.
        return partitionCutNodeValue;
    }

    private int findSumAndCheck(TreeNode root, int totalSum) {
        if (root == null)
            return 0;

        int leftSum = findSumAndCheck(root.left, totalSum);
        int rightSum = findSumAndCheck(root.right, totalSum);

        int subTreeSum = root.val + leftSum + rightSum;

        // Check if cutting the edge *just below the current root* creates a valid partition.
        // A valid partition means the remaining tree has a sum equal to this subtree's sum (which is half the total).
        // The condition "subTreeSum != 0" prevents the edge above a 0-sum subtree from being picked incorrectly.
        if (subTreeSum * 2 == totalSum && subTreeSum != totalSum) { // Added 'subTreeSum != totalSum'
            // We found the node to cut beneath! Store its value.
            // We stop here if a valid cut is found.
            if (partitionCutNodeValue == null) {
                // The node to 'remove' is the parent of the subtree that sums to half the total.
                // We don't store "root.val" but usually the problem implies the edge below it.
                // Let's assume the prompt means "which node is at the root of the half-sum subtree?"
                partitionCutNodeValue = root.val;
            }
        }

        return subTreeSum;
    }



}
