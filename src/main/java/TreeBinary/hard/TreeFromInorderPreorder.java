package TreeBinary.hard;

import TreeBinary.TreeNode;

import java.util.HashMap;

public class TreeFromInorderPreorder {

    public static void main(String[] args) {

       int preorder[] = new int[] {3, 9, 20, 15, 7} ;
       int inorder[]  = new int [] { 9, 3, 15, 20, 7} ;

       buildTree(preorder, inorder);
    }

    private static TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode root =  buildTree(preorder, inorder, 0,preorder.length-1, 0, inorder.length-1);
        printTree(root);



        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i=0;i<inorder.length;i++){
            map.put(inorder[i], i);
        }

        root = optimizedBuildTree(preorder,inorder,0,preorder.length-1,0,inorder.length-1,map);

        System.out.println();
        printTree(root);

        return root;
    }

    private static void printTree(TreeNode root) {
        if (root == null)
            return;

        printTree(root.left);
        System.out.print(root.val + " ");
        printTree(root.right);
    }

    /*
      This is Bruteforce Approach

      Time Complexity: O(N^2), where N is the number of nodes. This is because for each node, we potentially scan the entire inorder array.
      Space Complexity: O(N), for the recursion call stack.
     */
    private static TreeNode buildTree(int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd) {
        // base case
        if (preStart > preEnd || inStart > inEnd)
            return null;

        // now for root node
        TreeNode root = new TreeNode(preorder[preStart]);

        // find the index of root node value in inorder array
        int inRootIndex=0;
        for (int i=inStart;i<=inEnd;i++){
            if (inorder[i] == root.val){
                inRootIndex = i;
                break;
            }
        }

        // now find the legth of left tree
        int leftTreeSize = inRootIndex - inStart;

        // now recursive call left and right to find out remaining

        root.left = buildTree(preorder, inorder,preStart+1, preStart+leftTreeSize,inStart, inRootIndex-1);
        root.right = buildTree(preorder,inorder,preStart+leftTreeSize+1, preEnd, inRootIndex+1, inEnd);

        return root;
    }

    // Optimized Approach
    /*
     Time Complexity: O(N), where N is the number of nodes. We avoid repeated scanning of inorder by using HashMap.
     Space Complexity: O(N), for the recursion call stack and the HashMap.
     */
    private static TreeNode optimizedBuildTree(int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd, HashMap<Integer, Integer> map) {

        // Base case: no elements left to construct the tree
        if (preStart > preEnd || inStart > inEnd)
            return null;


        TreeNode root = new TreeNode(preorder[preStart]);

        // Find the index of the root in inorder array using the map
        int inRootIndex = map.get(root.val);
        int leftTreeSize = inRootIndex - inStart;

        // Recursive construction of left and right subtrees
        root.left = optimizedBuildTree(preorder,inorder,preStart+1, preStart+leftTreeSize, inStart, inRootIndex-1, map);
        root.right = optimizedBuildTree(preorder,inorder, preStart+leftTreeSize+1, preEnd, inRootIndex+1, inEnd, map);

        return root;
    }


}
