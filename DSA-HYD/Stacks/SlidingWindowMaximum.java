class Solution {
    public int[] maxSlidingWindow(int[] arr, int k) {
        Stack<Integer> st = new Stack<>();
        int [] a = new int[arr.length];
        
        for(int i = arr.length - 1; i >= 0; i--){
            int val = arr[i];
            while(st.size() >0 && arr[st.peek()] <= val){
                st.pop();
            }
            if(st.size() == 0){
                a[i] = arr.length;
            }else{
                a[i] = st.peek();
            }
            st.push(i);
        }

        int [] nge = a;
        int n = arr.length;
        
        int tw = n - k + 1;
        int [] ans = new int[tw]

        for(int i = 0; i < tw; i++){
            int j = i;
            while(nge[j] <= i + k - 1){
                j = nge[j];
            }
            ans[i] = arr[j]
        }
        return ans;
    }
}
