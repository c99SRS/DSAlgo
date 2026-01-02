package TreeBinary.traversal;

import TreeBinary.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class PreOrderTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        System.out.println(getPreOrderNodes(root) ) ;
    }

    private static List<Integer> getPreOrderNodes(TreeNode root) {
        return preOrderTraversal(root);
    }

    private static List<Integer> preOrderTraversal(TreeNode root) {

        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        // Use ArrayDeque as a Stack (recommended over java.util.Stack)
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            // 1. Process the current "Root" node
            TreeNode node = stack.pop();
            result.add(node.val);

            // 2. Push RIGHT child first (so it's processed LATER)
            if (node.right != null) {
                stack.push(node.right);
            }

            // 3. Push LEFT child last (so it's processed NEXT)
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return result;

    }


}
