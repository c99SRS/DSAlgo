package TreeBinary.MediumLevel;

import TreeBinary.TreeNode;

public class TreePartition {

    // Problem Description
    //
    //Given a binary tree A. Check whether it is possible to partition the tree to two trees which have equal sum of values after
    // removing exactly one edge on the original tree.

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


    boolean found =false;
    public int findPartition(TreeNode root) {

        int totalSum = findTotalSum(root);
        System.out.println("total sum "+ totalSum);
        int subtreesum = findSumAndCheck(root, totalSum);
        System.out.println("sub sum"+ subtreesum);

        return found ? 1 : 0;
    }

    private int findSumAndCheck(TreeNode root, int totalSum) {

        if (root == null)
            return 0;

        int leftSum = findSumAndCheck(root.left, totalSum);
        int rightSum = findSumAndCheck(root.right, totalSum);

        int subTreeSum =  root.val + leftSum + rightSum;

        if (subTreeSum == totalSum - subTreeSum && subTreeSum != 0)
            found = true;

        return subTreeSum;
    }

    public int findTotalSum(TreeNode root){
        if(root == null)
            return 0;

        return  root.val + findTotalSum(root.left)+findTotalSum(root.right);
    }


}
