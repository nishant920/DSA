class Solution {
    public ArrayList<Integer> nextLargerElement(int[] arr) {
        // code here
        ArrayList<Integer> ans = new ArrayList<>();
        Stack<Integer> st = new Stack<>();
        int [] a = new int[arr.length];
        
        for(int i = arr.length - 1; i >= 0; i--){
            int val = arr[i];
            while(st.size() >0 && st.peek() <= val){
                st.pop();
            }
            if(st.size() == 0){
                a[i] = -1;
            }else{
                a[i] = st.peek();
            }
            st.push(val);
        }
        
        for(int i = 0; i < a.length; i++){
            ans.add(a[i]);
        }
        
        return ans;
        
        
    }
}
