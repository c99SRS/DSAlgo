package TreeBinarySearch.EasyLevel;

import TreeBinary.TreeNode;

public class FindCeilValue {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(13);
        root.right.left = new TreeNode(11);
        root.right.right = new TreeNode(14);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(2);
        root.left.left.right = new TreeNode(4);
        root.left.right = new TreeNode(6);
        root.left.right.right = new TreeNode(9);

        int target = 7;

        int ceilVal = findCeil(root, target);
        System.out.println("Ceil value: "+ ceilVal);
    }

    private static int findCeil(TreeNode root, int target) {

        int ceil=-1;

        TreeNode curr=root;
        while (curr != null){

            if (curr.val == target) {
                ceil = curr.val;
                return ceil;
            }

            if (target > curr.val)
                curr = curr.right;
            else {
                ceil = curr.val;
                curr = curr.left;
            }

        }
        return ceil;
    }
}
