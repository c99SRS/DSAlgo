package TreeBinary.hard;

import TreeBinary.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class FlattenBinaryTree {
    /*
      Flatten Binary Tree to Linked List
Problem Statement: Given a Binary Tree, convert it to a Linked List where the linked list nodes follow the same order as the pre-order traversal of the binary tree.
Use the right pointer of the Binary Tree as the ‘next’ pointer for the linked list and set the left pointer to null. Do this in place and do not create extra nodes
     */


    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(10);
        root.left.right = new TreeNode(9);

        FlattenBinaryTree binaryTree= new FlattenBinaryTree();
        binaryTree.flattenBinaryTreeByStack(root);

        TreeNode prev[] = new TreeNode[1];  // Taking this as to avoid global variable
        binaryTree.flattenBinaryTreeByPreorderRecursion(root, prev);

        binaryTree.flattenByMorrisTraversal(root);
    }

    /*
      How it Works (Intuition)
1.Find the Bridge: For any node with a left child, you find its inorder predecessor (the rightmost node in the left subtree).
2. Relink: You take the entire original right subtree and attach it to that predecessor's right pointer.
3. Flatten: You move the left subtree to the right position and set left to null.
4. Repeat: By moving to curr.right, you eventually process every node until the tree is a single right-skewed line.
     */
    private void flattenByMorrisTraversal(TreeNode root) {

        TreeNode curr = root;

        while (curr != null) {
            // If there is a left child, we need to move the right
            // subtree to the end of this left child's rightmost path.
            if (curr.left != null) {
                // Find the rightmost node in the left subtree (the predecessor)
                TreeNode prev = curr.left;
                while (prev.right != null) {
                    prev = prev.right;
                }

                // Bridge: Connect predecessor's right to current's right child
                prev.right = curr.right;

                // Shift: Current's right becomes the left subtree
                curr.right = curr.left;
                curr.left = null; // Clean up original left pointer
            }

            // Move to the next node on the right path
            curr = curr.right;
        }

        /*
        Complexity Analysis
        Time Complexity: (O(N)). Although there is a nested while loop to find the predecessor, each edge in the tree is visited at most twice (once to find it and once to process it).
        Space Complexity: (O(1)). The algorithm only uses a few auxiliary pointers regardless of the tree's height or size.
         */

    }

    // This is reverse Preorder recursive approach i:e Right -> Left -> Root
    private void flattenBinaryTreeByPreorderRecursion(TreeNode root, TreeNode[] prev) {
        if (root == null)
            return;

        //Traverse in reverse: right -> Left -> root
        flattenBinaryTreeByPreorderRecursion(root.right, prev);
        flattenBinaryTreeByPreorderRecursion(root.left, prev);

        //Now process Root: set current node's right to previous
        root.right = prev[0];
        root.left = null;
        prev[0] = root;
    }

    //Implementing by reverse of PreorderTraversal i:e  right -> left -> root
    private void flattenBinaryTreeByStack(TreeNode root) {
        if (root == null)
            return;

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()){

            TreeNode curr = stack.pop();
            //first put right then left bcoz while popping out it will be in left -> right order
            if (curr.right != null)
                stack.push(curr.right);
            if (curr.left != null)
                stack.push( curr.left);


            // now link  the left of curr node to the right
            if (!stack.isEmpty())
                curr.right = stack.peek();   // stack.peek() having left node on stack

            // now make curr of left to null
            curr.left = null;
        }

        /*
         Summary of Complexity Time Complexity: (O(N)), where (N) is the number of nodes, as each node is visited once.
         Space Complexity: (O(H)), where (H) is the height of the tree, due to the recursion stack or explicit stack.
         */
    }


}
