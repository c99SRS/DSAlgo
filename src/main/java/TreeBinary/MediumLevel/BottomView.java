package TreeBinary.MediumLevel;

import TreeBinary.TreeNode;

import java.util.*;


class Pair {
    TreeNode node;
    int col;
    Pair(TreeNode n, int h) { node = n; col = h; }
}

public class BottomView {

    public static void main(String[] args) {
        // Creating a sample binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        BottomView solution = new BottomView();

        // Get the Bottom View traversal
        List<Integer> bottomView = solution.bottomView(root);

        // Print the result
        System.out.println("Bottom View Traversal: " + bottomView);
    }

    private List<Integer> bottomView(TreeNode root) {

        List<Integer> resultList = new ArrayList<>();

        if (root == null)
            return resultList;

        Map<Integer, Integer> map = new TreeMap<>();

        Queue<Pair> nodeQueue = new LinkedList<>();
        nodeQueue.offer(new Pair(root, 0));

        while (!nodeQueue.isEmpty()){

            Pair pair = nodeQueue.poll();
            TreeNode  curr = pair.node;
            int col = pair.col;

            map.put(col, curr.val);

            if (curr.left != null)
                nodeQueue.offer(new Pair(curr.left, col -1));

            if (curr.right != null)
                nodeQueue.offer(new Pair(curr.right , col +1 ));

        }
        System.out.println("map : " + map);
        for (int val : map.values())
            resultList.add(val);

        return resultList;
    }
    /*
     Complexity Check:
     Time Complexity: (O(Nlog W)) where (N) is the number of nodes and (W) is the tree width (due to TreeMap insertions).
     Space Complexity: (O(N)) for the queue and map.
     */

}
