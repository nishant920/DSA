class StockSpanner {

    Stack<int []> st;
    int day;
    public StockSpanner() {
        this.st = new Stack<>();
        this.day = 0;
    }
    
    public int next(int price) {
        // 0 -> stockPrice, 1 -> day 
        while(st.size() > 0 && st.peek()[0] <= price){
            st.pop();
        }
        int 
        ans = -1;
        if(st.size() == 0){
            ans = day + 1;
        }else{
            ans = day - st.peek()[1];
        }
        int [] arr = {price, day};
        st.push(arr);
        day++;
        return ans;
    }

}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */
