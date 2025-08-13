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
    // -Infinity + (-10)
    int max = Integer.MIN_VALUE;
    public int calculateMaxPath(TreeNode root){
        if(root == null){
            return -1001;
        }
        int leftPathSum = calculateMaxPath(root.left);
        int rightPathSum = calculateMaxPath(root.right);
        int leftPath = leftPathSum + root.val;
        int rightPath = rightPathSum + root.val;
        int curvePath = leftPathSum + root.val + rightPathSum;
        int selfPath = root.val;
        int maxPathSumIncRoot = Math.max(Math.max(leftPath, rightPath), Math.max(curvePath, selfPath));
        max = Math.max(max, maxPathSumIncRoot);
        return Math.max(Math.max(leftPath, rightPath), selfPath);
    }
    public int maxPathSum(TreeNode root) {
        calculateMaxPath(root);
        return max;
    }
}
