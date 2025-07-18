// User function Template for Java

class Solution {
    int countStrings(int n) {
        // code here
        int [][] dp = new int[2][n + 1];
        
        dp[0][1] = 1; // "0"
        dp[1][1] = 1; // "1"
        
        for(int j = 2; j <= n; j++){
            dp[0][j] = dp[0][j-1] + dp[1][j-1];
            dp[1][j] = dp[0][j-1];
        }
        return dp[0][n] + dp[1][n];
    }
}
