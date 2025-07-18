class MinStack {

    Stack<Integer> st1; // Normal Stack
    Stack<Integer> st2; 

    public MinStack() {
        this.st1 = new Stack<>();
        this.st2 = new Stack<>();
    }
    
    public void push(int val) {
       st1.push(val);
       if(st2.size() == 0){
            st2.push(val);
       }else{
        int min = Math.min(val, st2.peek());
        st2.push(min);
       }
    }
    
    public void pop() {
        if(st1.size() == 0){
            return;
        }
        st1.pop();
        st2.pop();
    }
    
    public int top() {
        return st1.peek();
    }
    
    public int getMin() {
        return st2.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
