package version.two;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 *                             _ooOoo_
 *                            o8888888o
 *                            88" . "88
 *                            (| -_- |)
 *                            O\  =  /O
 *                         ____/`---'\____
 *                       .'  \\|     |//  `.
 *                      /  \\|||  :  |||//  \
 *                     /  _||||| -:- |||||-  \
 *                     |   | \\\  -  /// |   |
 *                     | \_|  ''\---/''  |   |
 *                     \  .-\__  `-`  ___/-. /
 *                   ___`. .'  /--.--\  `. . __
 *                ."" '<  `.___\_<|>_/___.'  >'"".
 *               | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 *               \  \ `-.   \_ __\ /__ _/   .-` /  /
 *          ======`-.____`-.___\_____/___.-`____.-'======
 *                             `=---='
 *          ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 *                     佛祖保佑        永无BUG
 * @AUTHOR zuo-zhenjun
 * @TIME 2021/12/12 10:46
 * @DESCRIPTION 
 **/
public class Day36 {

    public static void main(String[] args) {
        Day36 d = new Day36();
        d.maxAreaOfIsland(new int[][]{
                new int[]{0,0,1,0,0,0,0,1,0,0,0,0,0},
                new int[]{0,0,0,0,0,0,0,1,1,1,0,0,0},
                new int[]{0,1,1,0,1,0,0,0,0,0,0,0,0},
                new int[]{0,1,0,0,1,1,0,0,1,0,1,0,0},
                new int[]{0,1,0,0,1,1,0,0,1,1,1,0,0},
                new int[]{0,0,0,0,0,0,0,0,0,0,1,0,0},
                new int[]{0,0,0,0,0,0,0,1,1,1,0,0,0},
                new int[]{0,0,0,0,0,0,0,1,1,0,0,0,0}
        });
    }

    /**
     * 剑指 Offer II 105. 岛屿的最大面积
     * @param grid
     * @return
     */
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j]==1){ // 是陆地
                    int temp = 1;
                    Deque<Land> stack = new LinkedList<>();
                    stack.push(new Land(i, j));
                    grid[i][j] = 2; // 标记为已访问
                    while (!stack.isEmpty()){
                        Land land = stack.pop();
                        int up = land.m-1;
                        int down = land.m+1;
                        int left = land.n-1;
                        int right = land.n+1;
                        if (up>=0 &&  grid[up][land.n]==1){
                            stack.push(new Land(up, land.n));
                            grid[up][land.n] = 2;
                            temp++;
                        }
                        if (down < m && grid[down][land.n] == 1){
                            stack.push(new Land(down, land.n));
                            grid[down][land.n] = 2;
                            temp++;
                        }
                        if (left >= 0 && grid[land.m][left] == 1){
                            stack.push(new Land(land.m, left));
                            grid[land.m][left] = 2;
                            temp++;
                        }
                        if (right < n && grid[land.m][right] == 1){
                            stack.push(new Land(land.m, right));
                            grid[land.m][right] = 2;
                            temp++;
                        }
                    }
                    ans = Math.max(ans, temp);
                }
            }
        }
        return ans;
    }

    /**
     * 剑指 Offer II 106. 二分图
     * @param graph
     * @return
     */
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        // 0:没访问过
        // 1:属于第一个子集
        // 2:属于第二个子集
        int[] flag = new int[n];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (flag[i]==0){// 没访问过
                stack.push(i);
                flag[i] = 1;
                while (!stack.isEmpty()){
                    Integer node = stack.pop();
                    int correct_flag = flag[node]==1 ? 2 : 1;
                    for (int j : graph[node]){
                        if (flag[j] == 0){
                            flag[j] = correct_flag;
                            stack.push(j);
                        } else {
                            if (correct_flag != flag[j]) return false;
                        }
                    }
                }
            }
        }
        return true;
    }


    /**
     * 剑指 Offer II 107. 矩阵中的距离
     * @param mat
     * @return
     */
    public int[][] updateMatrix(int[][] mat) {
        int[][] ans = new int[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 0){
                    ans[i][j]=0;
                } else {
                    Deque<Land> queue = new ArrayDeque<>();
                    boolean[][] visited = new boolean[ans.length][ans[0].length];
                    queue.addLast(new Land(i, j, 0));
                    visited[i][j] = true;
                    while (!queue.isEmpty()){
                        Land land = queue.pop();
                        int up = land.m-1;
                        int down = land.m+1;
                        int left = land.n-1;
                        int right = land.n+1;
                        if (up >=0 && !visited[up][land.n]){
                            if (mat[up][land.n]==0){
                                ans[i][j] = land.l+1;
                                break;
                            }
                            queue.addLast(new Land(up, land.n, land.l+1));
                            visited[up][land.n] = true;
                        }
                        if (down < mat.length && !visited[down][land.n]){
                            if (mat[down][land.n]==0){
                                ans[i][j] = land.l+1;
                                break;
                            }
                            queue.addLast(new Land(down, land.n, land.l+1));
                            visited[down][land.n] = true;
                        }
                        if (left>=0 && !visited[land.m][left]){
                            if (mat[land.m][left] == 0){
                                ans[i][j] = land.l+1;
                                break;
                            }
                            queue.addLast(new Land(land.m, left, land.l+1));
                            visited[land.m][left]=true;
                        }
                        if (right < mat[0].length && !visited[land.m][right]){
                            if (mat[land.m][right] == 0){
                                ans[i][j] = land.l+1;
                                break;
                            }
                            queue.addLast(new Land(land.m, right, land.l+1));
                            visited[land.m][right]=true;
                        }
                    }
                }

            }

        }

        return ans;
    }
}
class Land{
    int m;
    int n;
    int l;
    public Land(int m, int n){
        this.m = m;
        this.n = n;
    }
    public Land(int m, int n, int l){
        this.m = m;
        this.n = n;
        this.l = l;
    }
}
