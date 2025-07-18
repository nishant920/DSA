class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);
        Stack<int[]> st = new Stack<>();
        st.push(intervals[0]);
        for(int i = 1; i < intervals.length; i++){
            int [] i1 = st.peek();
            int [] i2 = intervals[i];
            if(i2[0] <= i1[1]){
                i1[1] = Math.max(i1[1], i2[1]);
            }else{
                st.push(i2);
            }
        }

        int[][] ans = new int[st.size()][2];

        for(int i = st.size() - 1; i >= 0; i--){
            ans[i] = st.pop();
        }
        return ans;

    }
}
