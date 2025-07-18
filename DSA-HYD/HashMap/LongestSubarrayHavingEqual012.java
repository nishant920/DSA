import java.util.*;

class Solution{
    public static int longestSubarray(int[] arr){
        // write code here
        HashMap<String, Integer> map = new HashMap<>();
        int [] freqArr = new int[3];
        int max = 0;
        map.put("0#0", - 1);
        for(int i = 0; i < arr.length; i++){
            int val = arr[i];
            freqArr[val]++;
            int delta10 = freqArr[1] - freqArr[0];
            int delta21 = freqArr[2] - freqArr[1];
            String hash = delta10 + "#" + delta21;
            if(map.containsKey(hash)){
                int len = i - map.get(hash);
                max = Math.max(max, len);
            }else{
                map.put(hash, i);
            }
        }

        return max;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        Solution Obj =  new Solution();
        System.out.println(Obj.longestSubarray(arr));
    }
}
