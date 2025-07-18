class Solution {
    public int celebrity(int mat[][]) {
        // code here
        Stack<Integer> st = new Stack<>();
        for(int i = 0 ; i < mat.length; i++){
            st.push(i);
        }
        while(st.size() > 1){
            int p1 = st.pop();
            int p2 = st.pop();
            if(mat[p1][p2] == 1){
                st.push(p2);
            }else{
                st.push(p1);
            }
        }
        
        int idx = st.pop();
        
        for(int j = 0; j < mat.length; j++){
            if(mat[idx][j] != 0 && idx != j){
                return -1;
            }
        }
        
        for(int i = 0; i < mat.length; i++){
            if(mat[i][idx] != 1){
                return -1;
            }
        }
        
        return idx;
    }
}
