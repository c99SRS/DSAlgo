package TreeNodeBinary.EasyLevel;

import TreeBinary.TreeNode;

import java.util.ArrayList;

public class SerializeTreeNode {

    public static void main(String[] args) {
        //create a new binary TreeNode
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);

        SerializeTreeNode m = new SerializeTreeNode();
        ArrayList<Integer> Preorderseries = new ArrayList<>();

        m.PreorderSerialize(root, Preorderseries);

        System.out.println("Preorderseries follows as");
        for(int i=0;i<Preorderseries.size();i++){
            System.out.print(Preorderseries.get(i)+" ");
        }

    }

    private void PreorderSerialize(TreeNode root, ArrayList<Integer> preorderseries) {

        if (root == null){
            preorderseries.add(Integer.MIN_VALUE);
            return ;
        }

        preorderseries.add(root.val);
        PreorderSerialize(root.left, preorderseries);
        PreorderSerialize(root.right, preorderseries);
    }


}
