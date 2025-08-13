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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
         if(root == null){
            return new ArrayList<>();
        }
        LinkedList<TreeNode> q = new LinkedList<>();
        q.addLast(root);
        int level = 0;
        boolean flag = true; // flag false -><
        List<List<Integer>> main = new LinkedList<>();
        while(q.size() != 0){
            int size = q.size();
            List<Integer> li = new LinkedList<>();
            while(size > 0){
                TreeNode rem = q.removeFirst();
                if(flag == true){
                    li.addLast(rem.val);
                }else{
                    li.addFirst(rem.val);
                }
                if(rem.left != null){
                    q.addLast(rem.left);
                }
                if(rem.right != null){
                    q.addLast(rem.right);
                }
                size--;
            }
            flag = !flag;
            main.add(li);
            level++;
        }
        return main;   
    }
}
