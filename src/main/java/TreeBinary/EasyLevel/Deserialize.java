package TreeNodeNodeBinary.EasyLevel;

import TreeBinary.TreeNode;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;

public class Deserialize {


    int index;
    //method to deserialize a binary TreeNode using preorder traversal series
    public TreeNode PreorderDeserialize(ArrayList<Integer> series){
        if(series.size() == index){ //base case
            return null;
        }

        //adds null at relevant positions
        if(series.get(index) == Integer.MIN_VALUE){
            System.out.println("index at return "+ index);
            index++;
            return null;
        }

        System.out.println("index "+ index);
        //add the nodes in a preorder manner current, left, right
        TreeNode root = new TreeNode(series.get(index));
        index++;
        root.left = PreorderDeserialize(series);
        root.right = PreorderDeserialize(series);

        return root;
    }

    //function to print in order traversal of a TreeNode
    public void inOrder(TreeNode root){
        if(root == null)
            return;

        inOrder(root.left);
        System.out.print(root.val+" ");
        inOrder(root.right);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 4, -2147483648, -2147483648, -2147483648, 3, 5, -2147483648, -2147483648, -2147483648};
        Deserialize m = new Deserialize();
        ArrayList<Integer> PreorderSeries = new ArrayList<Integer>();
        for(int i:arr)
            PreorderSeries.add(i);
        m.index=0;
        TreeNode root = m.PreorderDeserialize(PreorderSeries);
        System.out.println("inorder traversal of deserialized TreeNode follows as");
        m.inOrder(root);
    }
    


}
