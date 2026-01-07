package TreeBinary.MediumLevel;

import TreeBinary.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class DepthOfBinaryTree {
    public static void main(String[] args) {
        // Representation of the input tree:
        //     12
        //    /  \
        //   8   18
        //  / \
        // 5   11
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(8);
        root.right = new TreeNode(18);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(11);

        System.out.println(findHeight(root));

        System.out.println(findHeightUsingLevelOrder(root));
    }

    private static int findHeightUsingLevelOrder(TreeNode root) {

        if (root == null)
             return 0;

        int level=0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()){
            int size = queue.size();

            for (int i=0;i<size; i++){
                TreeNode node = queue.poll();

                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);

            }
            level++;
        }
        return level;
    }

    private static int findHeight(TreeNode root) {
        if (root == null)
            return 0;

        return 1 + Math.max(findHeight(root.left), findHeight(root.right));
    }

    // Time Complexity: O(n)
    //Space Complexity: O(n), Recursive stack space

}
