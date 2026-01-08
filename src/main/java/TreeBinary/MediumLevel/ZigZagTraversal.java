package TreeBinary.MediumLevel;

import TreeBinary.TreeNode;

import java.util.*;

public class ZigZagTraversal {
    /*
        Problem Statement: Given a Binary Tree, print the zigzag traversal of the Binary Tree.
        Zigzag traversal of a binary tree is a way of visiting the nodes of the tree in a zigzag pattern,
        alternating between left-to-right and right-to-left at each level.
     */

    public static void main(String[] args) {
        // Create binary tree:
        //        1
        //      /   \
        //     2     3
        //    / \     \
        //   4   5     6
        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(2);
//        root.right = new TreeNode(3);
//        root.left.left = new TreeNode(4);
//        root.left.right = new TreeNode(5);
//        root.right.right = new TreeNode(6);


        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(8);

        // Create solution object
        ZigZagTraversal sol = new ZigZagTraversal();

        // Get zigzag traversal
        List<List<Integer>> ans = sol.zigzagLevelOrder(root);

        // Print result
        System.out.println(ans);
    }

    private List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> resultList = new ArrayList<>();

        if (root == null)
            return resultList;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int level=0;
        while ( !queue.isEmpty()){

            int size = queue.size();
            level++;
            List<Integer> list = new ArrayList<>();

            for (int i=0;i<size;i++){
                TreeNode node = queue.poll();
                list.add(node.val);

                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }

            if (level % 2 == 0)
                Collections.reverse(list);

            resultList.add(list);
        }

        return resultList;
    }
    /*
      Complexity Analysis

Time Complexity: O(N) where N is the number of nodes in the binary tree. Each node of the binary tree is enqueued and dequeued exactly once,
hence all nodes need to be processed and visited. Processing each node takes constant time operations which contributes to the overall linear time complexity.

Space Complexity: O(N) where N is the number of nodes in the binary tree. In the worst case, the queue has to hold all the nodes of the last level of the binary tree,
the last level could at most hold N/2 nodes hence the space complexity of the queue is proportional to O(N). The resultant vector answer also stores the values of the nodes level by level and hence contains all the nodes of the tree contributing to O(N) space as well.
     */

}
