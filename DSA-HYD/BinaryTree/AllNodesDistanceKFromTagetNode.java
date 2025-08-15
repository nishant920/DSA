/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    List<Integer> ans;
    public List<TreeNode> getRootToNodePath(TreeNode root, TreeNode target){
        if(root == null){
            return new ArrayList<>();
        }
        if(root == target){
            List<TreeNode> li = new ArrayList<>();
            li.add(root);
            return li;
        }
        List<TreeNode> left = getRootToNodePath(root.left, target);
        if(left.size() > 0 ){
            left.add(root);
            return left;
        }
        List<TreeNode> right = getRootToNodePath(root.right, target);
        if(right.size() > 0 ){
            right.add(root);
            return right;
        }
        return new ArrayList<>();
    }

    public void getAllKDistanceNodes(TreeNode node, int k, TreeNode blocker){
        if(k < 0){
            return;
        }
        if(node == null){
            return;
        }
        if(k == 0){
            ans.add(node.val);
            return;
        }
        if(node.left != blocker){
            getAllKDistanceNodes(node.left, k - 1, blocker);
        }
            
        if(node.right != blocker){
            getAllKDistanceNodes(node.right, k - 1, blocker);
        }
        

    }
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<TreeNode> rootToNodePath = getRootToNodePath(root, target);
        ans = new ArrayList<>();
        for(int i = 0; i < rootToNodePath.size(); i++){
            TreeNode curr = rootToNodePath.get(i);
            TreeNode blocker = i == 0?  null : rootToNodePath.get(i - 1);
            getAllKDistanceNodes(curr, k - i, blocker);
        }
        return ans;
    }
}
