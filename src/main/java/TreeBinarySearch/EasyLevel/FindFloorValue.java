package TreeBinarySearch.EasyLevel;

import TreeBinary.TreeNode;

public class FindFloorValue {
    /*
    Floor in a Binary Search Tree
        Problem Statement: Given a Binary Search Tree and a key, return the floor of the given key in the Binary Search Tree.
        Floor of a value refers to the value of the largest node in the Binary Search Tree that is smaller than or equal to the given key. If the floor node does not exist, return nullptr.
     */

    public static void main(String[] args) {

        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(6);

        int target = 7;
        int floorVal = findFloorValue(root, target);
        System.out.println("floorval "+ floorVal);
    }

    private static int findFloorValue(TreeNode root, int target) {

        if (root == null)
            return target;

        int floorVal=-1;

        TreeNode curr = root;
        while (curr != null){

            if (curr.val == target) {
                floorVal = curr.val;
                return floorVal;
            }

            if (target > curr.val) {
                floorVal = curr.val;
                curr = curr.right;
            }
            else
                curr = curr.left;

        }

        return floorVal;
    }
    /*
     Complexity Analysis
        Time Complexity: O(log2N), where N is the number of nodes in the Binary Search Tree. The complexity is equivalent to the height of the tree.
        Space Complexity: O(1), since the algorithm does not use any additional space or data structures.
    */


}
