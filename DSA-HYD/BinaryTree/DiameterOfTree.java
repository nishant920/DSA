/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int longestPath = -1;
    public int calculateHeight(TreeNode root){
        if(root == null){
            return -1;
        }
        int leftH = calculateHeight(root.left);
        int rightH = calculateHeight(root.right);
        longestPath = Math.max(longestPath, (leftH + rightH + 2));
        return Math.max(leftH, rightH) + 1;
    }
    /*
    Expectation from this function is to calculate maximum path passing through all the nodes in the tree;
    */
    public void traversal(TreeNode root){ 
        if(root == null){
            return;
        }
        traversal(root.left);
        traversal(root.right);
        int lh = calculateHeight(root.left);
        int rh = calculateHeight(root.right);
        int path = lh + rh + 2;
        longestPath = Math.max(longestPath, path);
    }
    public int diameterOfBinaryTree(TreeNode root) {
        //traversal(root);
        calculateHeight(root);
        return longestPath;
    }
}
