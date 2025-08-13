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
    public class Pair implements Comparable<Pair>{
        TreeNode node;
        int hl;
        int vl;
        public Pair(TreeNode node, int hl, int vl){
            this.node = node;
            this.hl = hl;
            this.vl = vl;
        }
        public int compareTo(Pair o){
            if(this.hl == o.hl){
                return this.node.val - o.node.val;
            }
            return this.hl - o.hl;
        }
    }

    // [{10, 0, 0}, {6, 2, 0}, {5, 2, 0}]
    // [{10, 0, 0}, {5, 2, 0}, {6, 2, 0}]
    public List<List<Integer>> verticalTraversal(TreeNode root) {
       LinkedList<Pair> q = new LinkedList<>(); 
       Pair p = new Pair(root,0, 0);
       q.addLast(p);
       HashMap<Integer, ArrayList<Pair>> map = new HashMap<>();
       int leftVtxIdx = Integer.MAX_VALUE;
       while(q.size() != 0){
        int size = q.size();
        
        while(size != 0){
            Pair rem = q.removeFirst();
            int vl = rem.vl;
            leftVtxIdx = Math.min(vl, leftVtxIdx);
            if(map.containsKey(vl)){
                map.get(vl).add(rem);
            }else{
                ArrayList<Pair> li = new ArrayList<>();
                li.add(rem);
                map.put(rem.vl, li);
            }
            if(rem.node.left != null){
                q.addLast(new Pair(rem.node.left, rem.hl + 1, rem.vl - 1));
            }
            if(rem.node.right != null){
                q.addLast(new Pair(rem.node.right, rem.hl + 1, rem.vl + 1));
            }
            size--;
        }
       }

       int rightVtxIdx = leftVtxIdx + map.size() - 1;
       List<List<Integer>> ans = new ArrayList<>();
       for(int i = leftVtxIdx; i <= rightVtxIdx; i++){
            ArrayList<Pair> li = map.get(i);
            Collections.sort(li);
            List<Integer> li1 = new ArrayList<>();
            for(int j = 0; j < li.size(); j++){
                int val = li.get(j).node.val;
                li1.add(val);
            }
            ans.add(li1);
       }

       return ans;

    }
}
