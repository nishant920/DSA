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
    public TreeNode constructTree(int [] pre, int [] in, int psi, int pei, int isi, int iei){
        // System.out.println(psi + "_" + pei + " : " + isi + "_" + iei);
        if(psi > pei){
            return null;
        }
        if(isi > iei){
            return null;
        }

        if(pei == psi && isi == iei){
            TreeNode root = new TreeNode(pre[psi]);;
            return root;
        }

        TreeNode root = new TreeNode(pre[psi]);
        int count = 0;
        int idx = -1;
        for(int i = isi; i <= iei; i++){
            if(in[i] == pre[psi]){
                idx = i;
                count = i - isi;
                break;
            }
        }

        

        int lpsi = psi + 1;
        int lpei = psi + count;
        int lisi = isi;
        int liei = idx - 1;
    

        root.left = constructTree(pre, in, lpsi, lpei, lisi, liei);

        int rpsi = psi + count + 1;
        int rpei = pei;
        int risi = idx + 1;
        int riei = iei;

        root.right = constructTree(pre, in, rpsi, rpei, risi, riei);

        return root;

    }
    public TreeNode buildTree(int[] pre, int[] in) {
        return constructTree(pre, in, 0, pre.length - 1, 0, in.length - 1);
    }
}
