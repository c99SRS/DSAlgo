package TreeBinary.MediumLevel;

import TreeBinary.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BoundryTraversal {


    /*
The most intuitive way to approach Boundary Traversal in a binary tree is to visualize it as walking around the "outer skin" of the tree in an anti-clockwise direction.
To implement this systematically, you break the problem into three distinct, non-overlapping parts starting from the root.
1. Step-by-Step Approach
Part 1: Left Boundary (Top-Down)
Start from the root.left.
Traverse down the leftmost path.
Rule: If a left child exists, go left; otherwise, go right.
Crucial Tip: Exclude the leaf TreeTreeNode in this step to avoid duplicates when you process the bottom boundary later.

Part 2: Leaf TreeTreeNodes (Left to Right)
Traverse the entire tree (using Pre-order or In-order traversal).
Rule: Only add a TreeTreeNode if it is a leaf (both TreeTreeNode.left and TreeTreeNode.right are null).
This naturally picks up the bottom "edge" of the tree from left to right.

Part 3: Right Boundary (Bottom-Up)
Start from the root.right.
Traverse down the rightmost path.
Rule: If a right child exists, go right; otherwise, go left.
Crucial Tip: Use a stack or recursion to add these TreeTreeNodes in reverse order (bottom-up) and exclude the leaf TreeTreeNode.
     */

    public static void main(String[] args) {
        // Creating a sample binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        BoundryTraversal solution = new BoundryTraversal();

        // Get the boundary traversal
        List<Integer> result = solution.traverseBoundary(root);

        // Print the result
        System.out.print("Boundary Traversal: " + result);
        //solution.printResult(result);
    }



    // Helper to check if a TreeNode is a leaf to avoid duplicate additions
    private boolean isLeaf(TreeNode TreeNode) {
        return TreeNode.left == null && TreeNode.right == null;
    }

    public ArrayList<Integer> traverseBoundary(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) return result;

        // 1. Add root if it's not a leaf (leaves are handled by leaf traversal)
        if (!isLeaf(root)) result.add(root.val);

        // 2. Add Left Boundary (excluding leaf TreeNodes)
        addLeftBoundary(root.left, result);

        // 3. Add all Leaf TreeNodes (left to right)
        addLeaves(root, result);

        // 4. Add Right Boundary (bottom-up, excluding leaf TreeNodes)
        addRightBoundary(root.right, result);

        return result;
    }

    private void addLeftBoundary(TreeNode curr, ArrayList<Integer> res) {
        while (curr != null) {
            if (!isLeaf(curr)) res.add(curr.val);
            // Move left if possible, otherwise move right
            if (curr.left != null) curr = curr.left;
            else curr = curr.right;
        }
    }

    private void addLeaves(TreeNode curr, ArrayList<Integer> res) {
        if (curr == null) return;
        if (isLeaf(curr)) {
            res.add(curr.val);
            return;
        }
        addLeaves(curr.left, res);
        addLeaves(curr.right, res);
    }

    private void addRightBoundary(TreeNode curr, ArrayList<Integer> res) {
        Stack<Integer> stack = new Stack<>();
        while (curr != null) {
            if (!isLeaf(curr)) stack.push(curr.val);
            // Move right if possible, otherwise move left
            if (curr.right != null) curr = curr.right;
            else curr = curr.left;
        }
        // Pop from stack to ensure bottom-up (reverse) order
        while (!stack.isEmpty()) {
            res.add(stack.pop());
        }
    }
    /*
     Complexity
     Time: O(N) since every TreeNode is visited at most a constant number of times.
     Space: O(H) for the recursion stack and auxiliary space for the right boundary, where \(H\) is the height of the tree
     */

}
