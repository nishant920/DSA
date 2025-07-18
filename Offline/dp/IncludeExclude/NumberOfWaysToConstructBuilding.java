// User function Template for Java

class Solution {
    public int TotalWays(int N) {
        // Code here
        // Road ke ek side ka hi ans bana raha hun 
        int mod = 1_000_000_007;
        int bw = 1;
        int sw = 1;
        
        
        for(int i = 2; i <= N; i++){
            // ith plot -> bw 
            int cbw = sw;
            int csw = bw + sw;
            bw = cbw;
            sw = csw;
        }
        int totalWays = bw + sw;
        
        int ans =  (totalWays*totalWays)%mod;
       
        return ans;
    }
}
