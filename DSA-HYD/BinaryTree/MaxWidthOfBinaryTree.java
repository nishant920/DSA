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
    public class Pair{
        TreeNode node;
        int idx;
        Pair(TreeNode node, int idx){
            this.node = node;
            this.idx = idx;
        }
    }
    public int widthOfBinaryTree(TreeNode root) {
        LinkedList<Pair> q = new LinkedList<>();
        q.addLast(new Pair(root, 0));
        int max = Integer.MIN_VALUE;
        while(q.size() != 0){
            int size = q.size();
            int st = -1;
            int en = -1;

            for(int i = 1; i <= size; i++){
                Pair rem = q.removeFirst();
                if(i == 1){ // This is the element of this level 
                    st = rem.idx;
                }
                if(i == size){// this is the last element of this level
                    en = rem.idx;
                }
                if(rem.node.left != null){
                    int lIdx = 2*rem.idx + 1;
                    q.addLast(new Pair(rem.node.left, lIdx));
                }
                if(rem.node.right != null){
                    int rIdx = 2*rem.idx + 2;
                    q.addLast(new Pair(rem.node.right, rIdx));
                }
            }

            int len = en - st + 1;
            max = Math.max(len, max);
        }

        return max;
    }
}
