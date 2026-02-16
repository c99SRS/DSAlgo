package TreeBinary.hard;

import TreeBinary.TreeNode;

public class ChildSumBinaryTree {
    /*
      Check for Children Sum Property in a Binary Tree

        Problem Statement:
        Given a Binary Tree, convert the value of its nodes to follow the Children Sum Property.
        The Children Sum Property in a binary tree states that for every node, the sum of its children's values (if they exist)
        should be equal to the node's value. If a child is missing, it is considered as having a value of 0.

        Note:
        The node values can be increased by any positive integer any number of times, but decrementing any node value is not allowed.
        A value for a NULL node can be assumed as 0.
        We cannot change the structure of the given binary tree.
     */

    // Step-by-Step Thinking Process
    /*
    1. Check Current "Budget" (Top-Down):
            1. As you move from the root down to the leaves, compare the parent's value to the sum of its immediate children.
            2. Case A: Parent > Children Sum. To fix this without decreasing the parent, update the children's values to match the parent's value (e.g., if parent is 50, make both children 50).
            3. Case B: Parent ≤ Children Sum. Do nothing yet; the children already have "enough" to support the parent's current value.
    2. Propagate the Change (Recursion):
            1. Recursively call the function for the left and right children. This ensures that if you increased a child's value in Step 1, its own subtrees are updated to maintain the property.
    3. Final Update (Bottom-Up):
            1. Once you've reached the leaves and are returning back up the tree, update each parent's value to be the exact sum of its children.
            2. Since you ensured the children were at least as large as the parent during the downward pass, you are guaranteed that the parent's value will either stay the same or increase.

    Summary of the Flow
        1. Downwards (Pre-order logic): Ensure children are large enough so we never have to decrease a parent later.
        2. Upwards (Post-order logic): Set each parent's value to the final calculated sum of its children to perfectly satisfy the property.

     */

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(35);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(2);

        System.out.println("Before changing Tree structure");
        printTree(root);

        changeTree(root);

        System.out.println("\nAfter changing Tree structure");
        // now print tree to check the update values
        printTree(root);
    }

    private static void printTree(TreeNode root) {
        if (root == null)
            return;
        printTree(root.left);
        System.out.print(root.val + " ");
        printTree(root.right);
    }

    private static void changeTree(TreeNode root) {

        if (root == null) return;

        // --- STEP 1: TOP-DOWN (Ensure kids are big enough) ---
        int childSum = 0;
        if (root.left != null) childSum += root.left.val;
        if (root.right != null) childSum += root.right.val;

        if (childSum >= root.val) {
            // Children have enough "budget" to support parent
            root.val = childSum;
        } else {
            // Parent is too large; update children to match parent's value
            if (root.left != null) root.left.val = root.val;
            if (root.right != null) root.right.val = root.val;
        }

        // --- STEP 2: RECURSE ---
        changeTree(root.left);
        changeTree(root.right);

        // --- STEP 3: BOTTOM-UP (Finalize parent value) ---
        int total = 0;
        if (root.left != null) total += root.left.val;
        if (root.right != null) total += root.right.val;

        // If it's not a leaf, update parent with final sum of children
        if (root.left != null || root.right != null) {
            root.val = total;
        }
    }

    /*
     Complexity: This approach visits each node exactly twice (once going down, once coming up),
     resulting in (O(N)) time complexity.
     */

}
