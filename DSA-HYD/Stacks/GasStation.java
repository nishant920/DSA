class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas= 0;
        int totalCost = 0;
        for(int i = 0; i < gas.length; i++){
            totalGas += gas[i];
            totalCost += cost[i];
        }
        if(totalGas < totalCost){
            return -1;
        }
        int currGas = 0;
        int idx = -1;
        for(int i = 0; i < gas.length; i++){
            if(currGas + gas[i] >= cost[i]){
                currGas = currGas + gas[i] - cost[i];
                if(idx == -1){
                    idx = i;
                }
            }else{
                idx = -1;
                currGas = 0;
            }
        }

        return idx;
    }
}
