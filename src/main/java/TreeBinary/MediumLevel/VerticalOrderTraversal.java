package TreeBinary.MediumLevel;

import TreeBinary.TreeNode;

import java.util.*;

public class VerticalOrderTraversal {
    /*
      Vertical Order Traversal of Binary Tree
Problem Statement:
Given a Binary Tree, return the Vertical Order Traversal of it starting from the Leftmost level to the Rightmost level.
If there are multiple TreeNodes passing through a vertical line, then they should be printed as they appear in level order
traversal of the tree.
     */

    public static void main(String[] args) {
        // Create sample binary tree
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(7);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(6);
        root.left.right.left = new TreeNode(5);
        root.left.right.right = new TreeNode(11);
        root.right.right = new TreeNode(9);
        root.right.right.left = new TreeNode(4);


        // Create solution object
        VerticalOrderTraversal solution = new VerticalOrderTraversal();

        // Call function
        List<List<Integer>> verticalTraversal = solution.verticalOrder(root);

        // Print result
        System.out.println("Vertical Traversal: " +  verticalTraversal);
    }

    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        // TreeMap keeps horizontal distance keys sorted
        Map<Integer, List<Integer>> map = new TreeMap<>();

        // Two queues for BFS to track nodes and their vertical levels
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> colQueue = new LinkedList<>();

        nodeQueue.offer(root);
        colQueue.offer(0);

        while (!nodeQueue.isEmpty()) {
            TreeNode curr = nodeQueue.poll();
            int col = colQueue.poll();

            // Add node to its corresponding column list
            map.putIfAbsent(col, new ArrayList<>());
            map.get(col).add(curr.val);

            // Left child: horizontal distance decreases by 1
            if (curr.left != null) {
                nodeQueue.offer(curr.left);
                colQueue.offer(col - 1);
            }
            // Right child: horizontal distance increases by 1
            if (curr.right != null) {
                nodeQueue.offer(curr.right);
                colQueue.offer(col + 1);
            }
        }
        // TreeMap values are already in leftmost-to-rightmost order
        result.addAll(map.values());
        return result;
    }
    /*
      Time Complexity: (O(Nlog W)), where (N) is the number of nodes and (W) is the width of the tree (for TreeMap insertions).
      Space Complexity: (O(N)) to store nodes in the map and queue
     */

}
