package TreeBinary.EasyLevel;

public class UniqueBinaryTreeReq {
    /*
    Requirements needed to construct a unique BT
Given a pair of tree traversal, return true if a unique binary tree can be constructed otherwise false. Each traversal is
represented with integer: 1 -> Preorder , 2 -> Inorder , 3 -> Postorder.
     */

    public static void main(String[] args) {

        UniqueBinaryTreeReq uniqueBinaryTreeReq = new UniqueBinaryTreeReq();
        boolean ans = uniqueBinaryTreeReq.uniqueBinaryTree(1,2);
        System.out.println("ans "+ ans);
    }

    public boolean uniqueBinaryTree(int a, int b) {
        //your code goes here
        if(a == 0 || b==0)
            return false;

        if (a<1 || b > 3 || a > 3 || b < 1)
            return false;

        if ((a == 1 && b==2) || (a==2 || b==3))
            return true;

        return false;
    }



}
