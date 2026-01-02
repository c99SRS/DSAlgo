package TreeBinary.traversal;


import TreeBinary.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class LevelOrderTraversal {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(6);
        root.right = new TreeNode(11);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.left.left.left = new TreeNode(1);
        root.left.left.right = new TreeNode(2);

        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(13);
        root.right.right.left = new TreeNode(12);


        //printInorder(root);
        levelOrderTraverse(root);
    }

    private static void levelOrderTraverse(TreeNode root) {
        if (root == null)
            return;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        ArrayList<ArrayList<Integer>> ansList = new ArrayList<>();

        while (!queue.isEmpty()){

            int size = queue.size();
            ArrayList<Integer> list = new ArrayList<>();
            for (int i=0;i<size;i++){
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
            }
            ansList.add(list);
        }
        System.out.println("Final level order traversal "+ ansList);
    }

    private static void printInorder(TreeNode root) {

        if (root == null)
            return;

        printInorder(root.left);
        System.out.print(root.val + " , ");
        printInorder(root.right);
    }


}
