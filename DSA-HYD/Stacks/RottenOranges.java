class Solution {
    public int orangesRotting(int[][] grid) {
        // i, j -> i, j - 1
        int [][] dir = {{-1, 0}, 
                        {0, 1}, 
                        {1, 0}, 
                        {0, -1}};
        LinkedList<int []> q = new LinkedList<>();
        int totalCount = 0;
        

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1 || grid[i][j] == 2){
                    totalCount++;
                }
                if(grid[i][j] == 2){
                    int [] arr = {i, j};
                    q.addLast(arr);
                 }
            }
        }

        int time = 0;
        int totalMarked = 0;
        while(q.size() != 0){
            int count = q.size();
            while(count != 0){
                int [] rem = q.removeFirst();
                int i = rem[0];
                int j = rem[1];
                totalMarked++;
                if(totalMarked == totalCount){
                    return time;
                }
                for(int d = 0; d < dir.length; d++){
                   int nr = i + dir[d][0];
                    int nc = j + dir[d][1];
                    if(nr >= 0 && nr < grid.length && nc >= 0 && nc < grid[0].length && grid[nr][nc] != 0 && grid[nr][nc] != 2){
                        int [] arr = {nr, nc};
                        grid[nr][nc] = 2;
                        q.addLast(arr);
                    }
                }
                count--;
            }
            time++;
        }

        if(totalMarked != totalCount){
            return -1;
        }

        return time;

    }
}
