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
    HashMap<TreeNode, TreeNode> childParMap;
    HashMap<Integer, TreeNode> valueNodeMap;
    public void createChildVsParentMap(TreeNode root, TreeNode par){
        if(root == null){
            return;
        }
        childParMap.put(root, par);
        valueNodeMap.put(root.val, root);
        createChildVsParentMap(root.left, root);
        createChildVsParentMap(root.right, root);
    }
    public int bfs(TreeNode startNode){
        LinkedList<TreeNode> q = new LinkedList<>();
        q.addLast(startNode);
        int time = 0;
        HashSet<Integer> isInfected = new HashSet<>();

        while(q.size() != 0){
            int size = q.size();
            while(size != 0){
                TreeNode rem = q.removeFirst();
                isInfected.add(rem.val);
                if(rem.left != null && isInfected.contains(rem.left.val) == false){
                    q.addLast(rem.left);
                }
                if(rem.right != null && isInfected.contains(rem.right.val) == false){
                    q.addLast(rem.right);
                }
                TreeNode par = childParMap.get(rem);
                if(par != null && isInfected.contains(par.val) == false){
                    q.addLast(par);
                }
                size--;
            }
            time++;
        }
        return time - 1;
    }
    public int amountOfTime(TreeNode root, int start) {
        childParMap = new HashMap<>();
        valueNodeMap = new HashMap<>();
        createChildVsParentMap(root, null);
        TreeNode startNode = valueNodeMap.get(start);
        return bfs(startNode);
    }
}
