package TreeBinarySearch.EasyLevel;

import TreeBinary.TreeNode;

public class SearchTarget {
    /*
    Search in a Binary Search Tree
Problem Statement: Given a Binary Search Tree and a key value return the node in the BST having data equal to ‘key’ otherwise return nullptr.
     */
    public static void main(String[] args) {

        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(5);
        root.right = new TreeNode(12);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(7);
        root.left.right.left = new TreeNode(6);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(14);
        root.right.right.left = new TreeNode(13);

        int key = 10;
        boolean isPresent = findTarget(root,key);
        System.out.println("Is target present: "+ isPresent);
    }

    static boolean findTarget(TreeNode root, int target){

        if (root == null)
            return false;

        while ( root != null){

            if (root.val == target)
                return true;

            if (target > root.val)
                root = root.right;
            else
                root = root.left;
        }

        return false;
    }


}
