package TreeBinarySearch.mediumLevel;

import TreeBinary.TreeNode;
import sun.reflect.generics.tree.Tree;

public class InsertNodeInBST {
    /*
        Insert a given node in BST
        Given the root node of a binary search tree (BST) and a value val to insert into the tree. Return the root node of the BST after the insertion.
        It is guaranteed that the new value does not exist in the original BST. Note that the compiler output shows true if the node is added correctly, else false.

        Initial Thinking:
        Your thinking is partially correct in that it produces a valid BST, but it is inefficient compared to standard BST insertion methods.
        Converting the tree into an array (Inorder traversal) and then rebuilding it is an "overkill" that doesn't leverage the actual properties of a Binary Search Tree.

        Why your approach is suboptimal:
        Time Complexity: Your approach is O(N) because you visit every node twice (once to create the array, once to find the insertion point).
        Space Complexity: It is O(N) because you must store every node's value in an array.
        Redundancy: You are treating the BST like a simple sorted list, ignoring the fact that you can "search" for the insertion point without touching every node.

        The Better Approach: Direct Insertion
            Standard BST insertion works just like a binary search. You traverse only the relevant branch from root to leaf, which is much faster.
            Average Time Complexity: O(log N) (proportional to the height of the tree).
            Space Complexity: O(1) if done iteratively, or O(h) if done recursively.

     */

    public static void main(String[] args) {

        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        int val = 5;
       TreeNode node =  insertValInTree(root, val);
        System.out.println("tree values: "+ root);
        node = insertIntoBST(root,val);
    }

    // Recursive approach
    // Time Complexity: O(log N)
    // Space Complexity: O(H), where H is height of the tree, due to recursion stack
    private static TreeNode insertValInTree(TreeNode root, int val) {

        //Base case: if we reach null spot, this is where new node goes in
        if (root == null)
            return new TreeNode(val);

        // decide whether to go left or right
        if (val > root.val)
            root.right = insertValInTree(root.right,val);
        else
            root.left = insertValInTree(root.left, val);

        // return updated root
        return root;
    }


    // Iterative Approach (Space efficient)
    // Time Complexity: O(log N)
    // Space Complexity: O(1)

   static TreeNode insertIntoBST(TreeNode root, int val){

        if (root == null)
            return new TreeNode(val);


        TreeNode curr = root;

        while (true){
            // Move left if value is smaller
            if (val < curr.val){
                if (curr.left != null)
                    curr = curr.left;
                else{
                    curr.left = new TreeNode(val);
                    break;
                }
            }else{
                // Move right if value is larger
                if (curr.right != null)
                    curr = curr.right;
                else {
                    curr.right = new TreeNode(val);
                    break;
                }
            }

        }
        return root;
    }

}
