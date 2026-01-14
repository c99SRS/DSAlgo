package TreeBinary.MediumLevel;

import TreeBinary.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class NodePath {
    /*
       Print Root to Node Path in a Binary Tree
Problem Statement: Given a Binary Tree and a reference to a root belonging to it. Return the path from the root node to the given leaf node.
Note: No two nodes in the tree have the same data value and it is assured that the given node is present and a path always exists.
     */

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.right.left = new TreeNode(6);
        root.left.right.right = new TreeNode(7);

        findNodePath(root, 7);
    }

    private static void findNodePath(TreeNode root, int keyNode) {

        List<Integer> pathList = new ArrayList<>();

        findPath(root, pathList, keyNode);

        System.out.println("final path list: "+ pathList);
    }

    private static boolean findPath(TreeNode root, List<Integer> pathList, int keyNode) {
        // 1. Base case
        if (root == null) {
            return false;
        }

        // 2. Add current node to path
        pathList.add(root.val);

        // 3. Check if current node is the target
        if (root.val == keyNode) {
            return true;
        }

        // 4. Recursively check left and right subtrees
        if (findPath(root.left, pathList, keyNode) || findPath(root.right, pathList, keyNode)) {
            return true;
        }

        // 5. Backtrack: If not found in this branch, remove the node
        pathList.remove(pathList.size() - 1);
        return false;
    }
    // Time Complexity: (O(N)) where (N) is the number of nodes, as we visit each node at most once.

}
