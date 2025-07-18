class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // 1 + 1 + "Hello";
        // "Hello" + 1 + 1
        HashMap<String, List<String>> map = new HashMap<>();
        for(int i = 0; i < strs.length; i++){
            String str = strs[i];
            // Create frequency array 
            int [] freq = new int[26];
            for(int j = 0; j < str.length(); j++){
                char ch = str.charAt(j);
                int idx = ch - 'a';
                freq[idx]++;
            }
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < freq.length; j++){
                if(freq[j] != 0){
                    char ch = (char)(j + 'a');
                    sb.append(ch + "" + freq[j]);
                }
            }
            String hash = sb.toString();
            if(map.containsKey(hash)){
                map.get(hash).add(str);
            }else{
                ArrayList<String> li = new ArrayList<>();
                li.add(str);
                map.put(hash, li);
            }
        }

        List<List<String>> ans = new ArrayList<>();
        for(String key : map.keySet()){
            List<String> li = map.get(key);
            ans.add(li);
        }

        return ans;

    }
}
