package TreeBinary.MediumLevel;

import TreeBinary.TreeNode;

import java.util.*;

public class TopViewOfTree {

    /*
     Top view of a Binary Tree
        Problem Statement:
        Given a Binary Tree, return its Top View. The Top View of a Binary Tree is the set of nodes visible when we see the tree
        from the top.
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
        TopViewOfTree solution = new TopViewOfTree();

        // Call function
        List<Integer> topViewOfTree = solution.topViewOfTree(root);

        // Print result
        System.out.println("Top view nodes: " +  topViewOfTree);
    }

    // It is same as vertical order traversal. Only pick 0th index of each column data, so that means we have to only pick 0th index on map value which we were storing for list of nodes
    private List<Integer> topViewOfTree(TreeNode root) {

        List<Integer> resultList = new ArrayList<>();
        if (root == null)
             return resultList;


        Map<Integer, Integer> map = new TreeMap<>();

        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> colQueue = new LinkedList<>();

        nodeQueue.offer(root);
        colQueue.offer(0);

        while (!nodeQueue.isEmpty()){

            TreeNode curr = nodeQueue.poll();
            int col = colQueue.poll();

            map.putIfAbsent(col, curr.val);

            if (curr.left != null){
                nodeQueue.offer(curr.left);
                colQueue.offer(col -1);
            }

            if (curr.right != null){
                nodeQueue.offer(curr.right);
                colQueue.offer(col +1);
            }
        }

        for (Integer val : map.values())
            resultList.add(val);

        return resultList;
    }

    /*
    Time Complexity:
    Space Complexity: Still (O(N)) in the worst case (for a skewed tree), but for a dense/balanced tree, the map size is reduced from (N) nodes to roughly the width of the tree ((W)). You only store one value per column instead of every node in that column.
    Time Complexity: Still (O(N)) because you must visit every node to discover its children. However, you save the time previously spent on list operations (appending nodes to a list) and subsequent retrieval (fetching the 0th index).

     */


}
