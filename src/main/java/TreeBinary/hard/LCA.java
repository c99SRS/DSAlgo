package TreeBinary.hard;

import TreeBinary.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LCA {
    /*
     Lowest Common Ancestor for two given Nodes

Problem Statement: Given a root of binary tree, find the lowest common ancestor (LCA) of two given nodes (p, q) in the tree.
The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).
     */

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.left.left = new TreeNode(8);
        root.right.right = new TreeNode(7);

        TreeNode n1 = root.right.right;
        TreeNode n2 = root.right.left.left;

        TreeNode ansNode = findLCA(root,n1, n2);
        System.out.println("LCA : "+ansNode.val);

        ansNode = findLCAOptimizeApproach(root,n1,n2);
        System.out.println("Optimize LCA : "+ansNode.val);
    }

    /*
     Time Complexity: O(n)
     Auxiliary Space: O(h), where h is the height of the tree.
     */
    private static TreeNode findLCAOptimizeApproach(TreeNode root, TreeNode n1, TreeNode n2) {

        if (root == null)
            return null;

        // if either target matches, return root
        if (root == n1 || root == n2)
            return root;

        TreeNode leftLca = findLCAOptimizeApproach(root.left,n1,n2);
        TreeNode rightLca = findLCAOptimizeApproach(root.right,n1,n2);

        // if both return non null values, that means either one of value
        // is present in left side & other is in right side.
        // so common is the root one
        if (leftLca != null && rightLca != null)
            return root;


        // otherwise check left or right return accordingly
        if (leftLca != null)
            return leftLca;
        else
            return rightLca;
    }


    // This is bruteforce approach, finding path for n1 & n2 , then find the lowest common point for them
    // In this Time Complexity: O(N),  spac complexity: O(N)
    private static TreeNode findLCA(TreeNode root, TreeNode n1, TreeNode n2) {

        // Take 2 path list
        List<TreeNode> path1 = new ArrayList<>();
        List<TreeNode> path2 = new ArrayList<>();

        // find path from root to n1 & path from root n2
        // if no path return null
        if (!findPath(root,path1,n1) || !findPath(root,path2, n2))
            return null;

        // now we have both the paths , compare to find the different value
        int i=0;
        for (i=0; i<path1.size() && i<path2.size() ; i++){
            if (path1.get(i) != path2.get(i))
                return path1.get(i-1);
        }
        return path1.get(i-1);
    }

    private static boolean findPath(TreeNode root, List<TreeNode> path, TreeNode targetNode) {

        if (root == null)
            return false;

        path.add(root);

        if (root == targetNode)
            return true;

        boolean leftExist  =  findPath(root.left,path,targetNode);
        boolean rightExist = findPath(root.right,path, targetNode);

        // if any path exist, return true
        if (leftExist || rightExist)
            return true;

        // if not finding, then backtrack
        path.remove(path.size()-1);
        return false;
    }


}
