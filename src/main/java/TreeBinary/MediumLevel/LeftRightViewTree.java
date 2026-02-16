package TreeBinary.MediumLevel;

import TreeBinary.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class LeftRightViewTree {
    /*
      Problem Statement: Assuming standing on the right side of a binary tree and given its root, return the values of the TreeNodes visible, arranged from top to bottom.
     */
    // To get the left and right views of a binary tree, we use level order traversal (BFS). We queue TreeNodes level by level. For each level, we record all TreeNode values in order. The left view is formed by picking the first TreeNode of each level, and the right view by picking the last TreeNode of each level.

    public static void main(String[] args) {
        // Build sample binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        // Create solution object
        LeftRightViewTree sol = new LeftRightViewTree();

        // Get and print level order
        List<List<Integer>> levelOrder = sol.levelOrder(root);
        System.out.println("Level Order Traversal: " + levelOrder);

        // Print left view
        System.out.println("Left View: " + sol.leftView(levelOrder));

        // Print right view
        System.out.println("Right View: " + sol.rightView(levelOrder));
    }

    private List<Integer> rightView(List<List<Integer>> levelOrder) {

        List<Integer> rightView = levelOrder.stream().map(innerList -> innerList.get(innerList.size() -1)).collect(Collectors.toList());

//        List<Integer> rightView = new ArrayList<>();
//        for (List<Integer> list : levelOrder)
//            rightView.add(list.get(list.size()-1));

        return rightView;
    }

    // left view is 0th index element of each level
    private List<Integer> leftView(List<List<Integer>> levelOrder) {


        List<Integer> leftView = levelOrder.stream().map(innerList -> innerList.get(0)).collect(Collectors.toList());

//        List<Integer> leftView = new ArrayList<>();
//        for (List<Integer> list : levelOrder)
//            leftView.add(list.get(0));

        return leftView;
    }

    private List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> resultList = new ArrayList<>();
        if (root == null)
            return resultList;

        Queue<TreeNode> queue =new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i=0;i<size;i++){
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
            }
            resultList.add(list);
        }

        return resultList;
    }


}
