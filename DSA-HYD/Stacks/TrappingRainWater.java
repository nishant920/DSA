class Solution {
    public int trap(int[] height) {
        // 1. Decide your stack what you are going in the stack 
        Stack<Integer> st = new Stack<>();
        // 2. Travel over all the buidlings 
        int totalUnits = 0;
        for(int i = 0; i < height.length; i++){
            // I need to decide the current ith building on which i am on shall i directly put in the stack
            // or i need to check is this ith building is becoming ngb for the stack top building 
            // If on the top of the stack we are having smaller height buidlings in comparison to ith building 
            // The buildings that are present in the stack that are acting as a gap building 
            // I will be poping the gap building till the time  i will not get the building greater then the ith building 
            // And every gap building i pop i will calculating the unit of water getting stored because of that gap building 
            while(st.size() > 0. &&  height[st.peek()] <= height[i]){
                int gabBIdx = st.pop();
                if(st.size() == 0){
                    break;
                }
                int ngbRIdx = i;
                int ngbLIdx = st.peek();
                int h = Math.min(height[ngbRIdx], height[ngbLIdx]) - height[gabBIdx];
                int w = ngbRIdx - ngbLIdx -1;
                totalUnits += h*w;
            }
            st.push(i);
        }
        return totalUnits;
    }
}
