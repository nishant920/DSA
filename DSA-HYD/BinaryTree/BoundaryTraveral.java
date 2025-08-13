/*
class Node
{
    int data;
    Node left, right;

    public Node(int d)
    {
        data = d;
        left = right = null;
    }
}
*/

class Solution {
    
    public void leftSide(Node node, ArrayList<Integer> leftNodes){
        if(node == null){
            return;
        }
        if(node.left != null){
            leftNodes.add(node.data);
            leftSide(node.left, leftNodes);
        }else if(node.right != null){
            leftNodes.add(node.data);
            leftSide(node.right, leftNodes);
        }else{
            return;
        }
    }
    public void leafSide(Node node, ArrayList<Integer> leafNodes){
        if(node == null){
            return;
        }
        leafSide(node.left, leafNodes);
        if(node.left == null && node.right == null){
            leafNodes.add(node.data);
        }
        leafSide(node.right, leafNodes);
    }
    public void rightSide(Node node, Stack<Integer> rightNodes){
        if(node == null){
            return;
        }
         if(node.right != null){
            rightNodes.push(node.data);
            rightSide(node.right, rightNodes);
        }else if(node.left != null){
            rightNodes.push(node.data);
            rightSide(node.left, rightNodes);
        }else{
            return;
        }
    }
    ArrayList<Integer> boundaryTraversal(Node node) {
         ArrayList<Integer> boundary = new ArrayList<>();
        if(node.left == null && node.right == null){
            boundary.add(node.data);
            return boundary;
        }
       
        boundary.add(node.data);
        leftSide(node.left, boundary);
        leafSide(node, boundary);
        Stack<Integer> st = new Stack<>();
        rightSide(node.right, st);
        while(st.size() != 0){
            boundary.add(st.pop());
        }
        return boundary;
    }
}
