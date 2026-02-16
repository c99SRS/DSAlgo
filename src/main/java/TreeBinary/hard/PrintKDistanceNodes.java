package TreeBinary.hard;

import TreeBinary.TreeNode;

import java.util.*;

public class PrintKDistanceNodes {

    /*
       Print all the Nodes at a distance of K in a Binary Tree

       Problem Statement:
       Given the root of a binary tree, the value of a target node target, and an integer k.
       Return an array of the values of all nodes that have a distance k from the target node. The answer can be returned in any order (N represents null).
     */

    /*
     essentially a subset of the "Burning Tree" logic.In the Burning Tree problem, you find the total time (max distance).
     In this Distance K problem, you stop exactly at the \(K^{th}\) second and return all nodes that are "on fire" at that specific moment.

     Why the logic is identical:
     ---------------------------
     Radial BFS: Just like fire, distance spreads in all directions: left child, right child, and parent.
     Graph Transformation: Since standard binary trees don't have parent pointers, you must first create a parent map to allow the "fire" to travel upward.
     Level Tracking: Instead of running the BFS until the whole tree is burned, you track the current "level" (distance). When the level equals \(K\), the nodes in your queue are your answer. 
     */

    /*
      The Step-by-Step Approach:
      Step 1: Map Parents. Perform a BFS starting from the root to build a Map<Node, Node> that stores the parent of every node.
      Step 2: Find Target. Locate the actual target node if only its value is given.
      Step 3: Radial BFS. Start a new BFS from the target node:Maintain a visited set to avoid going in circles.For each node, add its left, right, and parent to the queue.Stop once you have completed (K) levels of expansion.
      Step 4: Collect Results. The remaining nodes in the queue are exactly distance (K) away. Add their values to your list.
     */

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        int target = 5;
        int k =2;

        List<Integer> nodeList =  findNodes(root, target,k);
        System.out.println("all nodes: "+ nodeList);
    }

    private static List<Integer> findNodes(TreeNode root, int target, int distance) {

        List<Integer> ansList = new ArrayList<>();
        HashMap<TreeNode, TreeNode> parentMap = new HashMap<>();
        TreeNode targetNode = findTarget(root, parentMap,target);
        if (targetNode == null)
            return ansList;

        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();

        queue.add(targetNode);
        visited.add(targetNode);

        int currDist = 0;
        while (!queue.isEmpty()){
            // check if we have reached the distance
            if (currDist == distance){
                while(!queue.isEmpty())
                    ansList.add(queue.poll().val);

                return ansList;
            }

            int size = queue.size();
            for (int i=0;i<size;i++){
                TreeNode curr = queue.poll();
                TreeNode[] neighbours = {curr.left,curr.right,parentMap.get(curr)};
                for (TreeNode neighbour : neighbours){
                    if (neighbour != null && !visited.contains(neighbour)){
                        visited.add(neighbour);
                        queue.add(neighbour);
                    }
                }
            }
            currDist++;
        }
        return ansList;
    }

    private static TreeNode findTarget(TreeNode root, HashMap<TreeNode, TreeNode> parentMap, int target) {

        TreeNode targetNode = null;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()){

            TreeNode curr = queue.poll();

            if (curr.val == target)
                targetNode = curr;

            if (curr.left != null){
                parentMap.put(curr.left, curr);
                queue.add(curr.left);
            }

            if (curr.right != null){
                parentMap.put(curr.right, curr);
                queue.add(curr.right);
            }
        }

        return targetNode;
    }

}
