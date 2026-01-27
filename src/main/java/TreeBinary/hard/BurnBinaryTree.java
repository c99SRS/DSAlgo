package TreeBinary.hard;

import TreeBinary.TreeNode;

import java.util.*;

public class BurnBinaryTree {

    /*
      Minimum time taken to BURN the Binary Tree from a Node
        Problem Statement: Given a target node data and a root of binary tree. If the target is set on fire,
         determine the shortest amount of time needed to burn the entire binary tree. It is known that in 1 second all nodes connected to a given node get burned. That is its left child, right child, and parent.
     */
    /*
    To implement your approach intuitively, think of the binary tree as a network of rooms in a house. To burn the whole house starting from the middle, you need to know how to reach every adjacent room—not just those below you (children), but also the one you came from (parent).
Here is the intuitive 3-step implementation:

1. The "Back Door" Phase (Parent Mapping)
Standard binary trees only have "one-way" doors to children. You must first create "back doors" so fire can travel back up to the parent.
Intuition: Traverse the tree once and for every node, write down who its parent is.
Data Structure: Use a Map (Hash Table) where Map[node] = parent_node.

2. The "Spark" Phase (Finding Target)
You need to know exactly which room to set on fire first.
Intuition: If you are given only the target's value, search the tree to get the actual Node* pointer.

3. The "Radial Spread" Phase (BFS)
Now, start the fire. In each "second," the fire spreads in all possible directions.
Intuition: Use a Queue to hold the nodes currently on fire.
Rules for Each Second:
Look at all nodes in the queue (the current fire front).
For each node, "ignite" its Left Child, Right Child, and Parent.

Crucial: Use a Visited Set to mark burned nodes. This prevents the fire from looping back and forth between a parent and child forever.
If at least one new node was ignited this second, increment your time counter.

     */


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(4);

        int target = 4;
       int time =  findMinTime(root, target);
        System.out.println("time: "+ time);
    }

    private static int findMinTime(TreeNode root, int target) {

        HashMap<TreeNode, TreeNode> parentMap = new HashMap<>();

        TreeNode targetNode = findParentMapping(root, parentMap, target);
        if (targetNode == null)
            return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();

        queue.offer(targetNode);
        visited.add(targetNode);

        int time=0;

        while (!queue.isEmpty()){
            int size = queue.size();

            boolean spread = false;

            // iterate and traverse the neightbours
            for (int i=0;i<size;i++){
                TreeNode curr = queue.poll();

                // check left, right child and parent
                TreeNode[] neighbours = {curr.left, curr.right, parentMap.get(curr)};
                // now traverse these neighbours
                for (TreeNode node:neighbours){

                    // check if it not visited and if not null
                    if (node != null && !visited.contains(node)){
                        visited.add(node);
                        queue.offer(node);
                        spread = true;     // Fire successfully spread this second
                    }
                }
            }
            if (spread)
                time++;
        }


        return time;
    }

    private static TreeNode findParentMapping(TreeNode root, HashMap<TreeNode, TreeNode> parentMap, int target) {

        if (root == null)
            return null;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        TreeNode targetNode= null;

        while (!queue.isEmpty()){

            TreeNode curr = queue.poll();

            if (curr.val == target)
                targetNode = curr;

            // Here left check, if child is there add it to queue and map the parent
            if (curr.left != null) {
                parentMap.put(curr.left, curr);
                queue.offer(curr.left);
            }
            // for right node also same
            if (curr.right != null){
                parentMap.put(curr.right, curr);
                queue.offer(curr.right);
            }
        }

        return targetNode;
    }
    /*
    Time Complexity: (O(N)) because you visit each node a constant number of times (once to map parents and once during the burn).
    Space Complexity: (O(N)) for the HashMap and the Visited set.
     */

}
