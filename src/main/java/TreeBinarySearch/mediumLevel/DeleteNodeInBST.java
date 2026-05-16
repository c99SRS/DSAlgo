package TreeBinarySearch.mediumLevel;

import TreeBinary.TreeNode;

public class DeleteNodeInBST {

    /*
        Delete a given node in BST
        Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.

        Basically, the deletion can be divided into two stages:

        Search for a node to remove.
        If the node is found, delete the node.
     */

        public static void main(String[] args) {
            // Test Case 1: Delete node with TWO children (3)
            System.out.println("=== Test Case 1: Delete node with TWO children ===");
            TreeNode root1 = new TreeNode(5);
            root1.left = new TreeNode(3);
            root1.right = new TreeNode(6);
            root1.left.left = new TreeNode(2);
            root1.left.right = new TreeNode(4);
            root1.right.right = new TreeNode(7);
            
            System.out.print("Before: ");
            printInOrder(root1);
            System.out.println();
            
            root1 = deleteNodeBST(root1, 3);  // Delete 3 (has both children)
            
            System.out.print("After deleting 3: ");
            printInOrder(root1);
            System.out.println("\n");
            
            // Test Case 2: Delete node with ONE child (6)
            System.out.println("=== Test Case 2: Delete node with ONE child ===");
            TreeNode root2 = new TreeNode(5);
            root2.left = new TreeNode(3);
            root2.right = new TreeNode(6);
            root2.left.left = new TreeNode(2);
            root2.left.right = new TreeNode(4);
            root2.right.right = new TreeNode(7);
            
            System.out.print("Before: ");
            printInOrder(root2);
            System.out.println();
            
            root2 = deleteNodeBST(root2, 6);  // Delete 6 (has only right child 7)
            
            System.out.print("After deleting 6: ");
            printInOrder(root2);
            System.out.println("\n");
            
            // Test Case 3: Delete LEAF node (2)
            System.out.println("=== Test Case 3: Delete LEAF node ===");
            TreeNode root3 = new TreeNode(5);
            root3.left = new TreeNode(3);
            root3.right = new TreeNode(6);
            root3.left.left = new TreeNode(2);
            root3.left.right = new TreeNode(4);
            root3.right.right = new TreeNode(7);
            
            System.out.print("Before: ");
            printInOrder(root3);
            System.out.println();
            
            root3 = deleteNodeBST(root3, 2);  // Delete 2 (leaf)
            
            System.out.print("After deleting 2: ");
            printInOrder(root3);
            System.out.println();
        }

    private static TreeNode deleteNodeBST(TreeNode root, int key) {
        if (root == null)
            return null;
        
        if (key < root.val) {
            // Key is in left subtree
            root.left = deleteNodeBST(root.left, key);
        } else if (key > root.val) {
            // Key is in right subtree
            root.right = deleteNodeBST(root.right, key);
        } else {
            // KEY FOUND! Now handle deletion in 3 cases:
            
            // CASE 1: Node is a leaf (no children)
            if (root.left == null && root.right == null) {
                return null;
            }
            
            // CASE 2: Node has only RIGHT child
            if (root.left == null) {
                return root.right;
            }
            
            // CASE 2b: Node has only LEFT child
            if (root.right == null) {
                return root.left;
            }
            
            // CASE 3: Node has BOTH children
            // Solution: Replace with in-order successor (or predecessor):
            //In-order Successor: Smallest value in right subtree (next larger)
            //In-order Predecessor: Largest value in left subtree (next smaller)
            // Strategy: Find in-order successor (smallest in right subtree)
            // Replace current node's value with successor's value
            // Delete the successor from right subtree
            TreeNode successor = findMin(root.right);  // Find smallest in right subtree
            root.val = successor.val;                  // Copy successor value to current
            root.right = deleteNodeBST(root.right, successor.val);  // Delete successor
        }
        
        return root;
    }
    
    // Helper: Find node with minimum value
    private static TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    
    // Helper: Print tree (in-order) to verify deletion
    private static void printInOrder(TreeNode node) {
        if (node == null) return;
        printInOrder(node.left);
        System.out.print(node.val + " ");
        printInOrder(node.right);
    }


}
