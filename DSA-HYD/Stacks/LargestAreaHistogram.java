class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> st = new Stack<>();
        // We want to calculate NSEL for all the elements in the array 
        int [] nsel = new int[heights.length];
        for(int i = 0; i < heights.length; i++){
            while(st.size() > 0 && heights[st.peek()] >= heights[i]){
                st.pop();
            }
            if(st.size() == 0){
                nsel[i] = -1;
            }else{
                nsel[i] = st.peek();
            }
            st.push(i);
        }
        int [] nser = new int[heights.length];
        st = new Stack<>();
        for(int i = heights.length - 1; i >= 0; i--){
            while(st.size() > 0 && heights[st.peek()] >= heights[i]){
                st.pop();
            }
            if(st.size() == 0){
                nser[i] = heights.length;
            }else{
                nser[i] = st.peek();
            }
            st.push(i);
        }
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < heights.length; i++){
            int lIdx = nsel[i];
            int rIdx = nser[i];
            int width = rIdx - lIdx - 1;
            int area = width*heights[i];
            max = Math.max(max, area);
        }
        return max;
    }
}
