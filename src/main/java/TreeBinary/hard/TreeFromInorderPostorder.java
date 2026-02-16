package TreeBinary.hard;

import TreeBinary.TreeNode;

import java.util.HashMap;

public class TreeFromInorderPostorder {


    public static void main(String[] args) {

        int inorder[] = new int[]{40, 20, 50, 10, 60, 30};
        int postorder[] = new int[]{40, 50, 20, 60, 30, 10};

        buildTree(inorder, postorder);
    }

    private static void buildTree(int[] inorder, int[] postorder) {

        TreeNode root = buildTree(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
        printTree(root);

        // Map will store value as key and index as the value
        HashMap<Integer, Integer> inorderIndexMap = new HashMap<>();

        // Fill the map with current index of inorder traversal
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        root = optimizedBuildTree(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1, inorderIndexMap);
        printTree(root);
    }


    private static void printTree(TreeNode root) {
        if (root == null)
            return;

        printTree(root.left);
        System.out.print(root.val + " ");
        printTree(root.right);
    }


    // Bruteforce Approach
    /*
     Complexity Analysis
        Time Complexity: O(n^2) – We might end up searching through the inorder list for the root multiple times.
        Space Complexity: O(n) – for the recursion stack.
     */
    private static TreeNode buildTree(int[] inorder, int[] postorder, int inStart, int inEnd, int postStart, int postEnd) {

        // base case
        if (inStart > inEnd || postStart > postEnd)
            return null;

        // for tree post order
        TreeNode root = new TreeNode(postorder[postEnd]);

        // now find the index of root index from inorder array
        int inRootIndex = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.val) {
                inRootIndex = i;
                break;
            }
        }

        int leftTreeSize = inRootIndex - inStart;

        root.left = buildTree(inorder, postorder, inStart, inRootIndex - 1, postStart, postStart + leftTreeSize - 1);
        root.right = buildTree(inorder, postorder, inRootIndex + 1, inEnd, postStart + leftTreeSize, postEnd - 1);

        return root;
    }


    private static TreeNode optimizedBuildTree(int[] inorder, int[] postorder, int inStart, int inEnd, int postStart, int postEnd, HashMap<Integer, Integer> inorderIndexMap) {
        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }

        // The last element in postorder is the root node
        int rootValue = postorder[postEnd];
        TreeNode root = new TreeNode(rootValue);

        // Retrieve root index from map directly
        int rootIndex = inorderIndexMap.get(rootValue);

        // Calculate the size of left subtree
        int leftTreeSize = rootIndex - inStart;

        // Recursively construct left and right subtrees
        root.left = optimizedBuildTree(inorder, postorder, inStart, rootIndex - 1, postStart, postStart + leftTreeSize - 1, inorderIndexMap);
        root.right = optimizedBuildTree(inorder, postorder, rootIndex + 1, inEnd, postStart + leftTreeSize, postEnd - 1, inorderIndexMap);

        return root;
    }
}
