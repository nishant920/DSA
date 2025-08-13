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
    public List<Integer> rightSideView(TreeNode root) {
        LinkedList<TreeNode> q = new LinkedList<>();
        q.addLast(root);
        List<Integer> ans = new ArrayList<>();
        if(root == null){
            return ans;
        }
        while(q.size() != 0){
            int size = q.size();
            for(int i = 1; i <= size; i++){
                TreeNode rem = q.removeFirst();
                if(i == size){
                    ans.add(rem.val);
                }
                if(rem.left != null){
                    q.addLast(rem.left);
                }
                if(rem.right != null){
                    q.addLast(rem.right);
                }
            }
        }
        return ans;
    }
}
