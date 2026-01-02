package TreeBinary.traversal;

import TreeBinary.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class PostOrderTraversal {
    /*
    Implementing post-order traversal iteratively is generally more complex than pre-order or in-order because the root is visited last.
There are two main intuitive approaches:
Using Two Stacks: Simulates a "reverse pre-order" to build the correct sequence in a second stack. This is often easier to conceptualize.
Using One Stack and a peek check: More efficient in space, but the logic is slightly trickier as you must constantly track the last visited node to decide whether to visit the parent.
     */

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        System.out.println(postOrderTraversalWithTwoStack(root) ) ;
        System.out.println(postOrderTraversalWithOneStack(root) ) ;
    }

    //Option 1: The Two-Stack Intuitive Approach
    //This method is very intuitive because it reuses the logic of pre-order traversal (Root -> Left -> Right) to generate the reverse of post-order (Root -> Right -> Left), and then reverses it one last time with a second stack.

    public static List<Integer> postOrderTraversalWithTwoStack(TreeNode root){

        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Deque<TreeNode> stack1 = new ArrayDeque<>();
        Deque<TreeNode> stack2 = new ArrayDeque<>();

        stack1.push(root);

        while (!stack1.isEmpty()) {
            TreeNode node = stack1.pop();
            stack2.push(node); // The 'visit' step for the reverse order

            // Push left child first so it ends up BELOW the right child
            // in stack1 (mimics Root -> Right -> Left logic for stack2)
            if (node.left != null) {
                stack1.push(node.left);
            }
            if (node.right != null) {
                stack1.push(node.right);
            }
        }

        // Pop from the second stack to get the correct L->R->Root order
        while (!stack2.isEmpty()) {
            result.add(stack2.pop().val);
        }

        return result;
    }

    // Option 2: The Single-Stack Space-Optimized Approach
    //This method uses a peek operation to decide if it's time to process the current node or if we still need to explore its right subtree.
    public static List<Integer> postOrderTraversalWithOneStack(TreeNode root){

        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        // Keeps track of the last node successfully processed
        TreeNode lastVisitedNode = null;

        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                // Drill down the left side, pushing potential parents to the stack
                stack.push(curr);
                curr = curr.left;
            } else {
                // Peek at the top node to make a decision without popping yet
                TreeNode peekNode = stack.peek();

                // If the right child exists AND hasn't been visited yet, go right
                if (peekNode.right != null && lastVisitedNode != peekNode.right) {
                    curr = peekNode.right;
                } else {
                    // Otherwise, both children are processed/null. Process the parent.
                    stack.pop();
                    result.add(peekNode.val);
                    lastVisitedNode = peekNode; // Mark as visited
                }
            }
        }
        return result;
    }

}
