class Solution {
    public String minWindow(String s, String t) {
        if(s.length() < t.length()){
            return "";
        }
        int st = -1;
        int en = -1;
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < t.length(); i++){
            char ch = t.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        int count = t.length();
        int i = 0;
        int j = 0;
        int min = Integer.MAX_VALUE;
        while(j < s.length()){
            char ch = s.charAt(j);
            if(map.containsKey(ch)){
                map.put(ch, map.get(ch) - 1);
                if(map.get(ch) >= 0){
                    count--;
                }
            }
            while(count == 0){
                if(min > j - i + 1){
                    min = j - i + 1;
                    st = i;
                    en = j;
                }
                char chi = s.charAt(i);
                if(map.containsKey(chi)){
                    map.put(chi, map.get(chi) + 1);
                    if(map.get(chi) > 0){
                        count++;
                    }
                }
                i++;
            }

            j++;

        }

        if(st == -1 && en == -1){
            return "";
        }
        return s.substring(st, en + 1);
    }
}
