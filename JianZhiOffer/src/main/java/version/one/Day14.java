package version.one;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
 * @TIME 2021/12/29 14:26
 * @DESCRIPTION 
 **/
public class Day14 {
    int ans = 1;

    /**
     * 剑指 Offer 12. 矩阵中的路径
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] used = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                used[i][j] = true;
                if (dfs(i, j, 0, word, used, board)){
                    return true;
                }
                used[i][j] = false;
            }
        }
        return false;
    }
    private boolean dfs(int m, int n, int count, String word, boolean[][] used, char[][] board){
        if (board[m][n] != word.charAt(count))return false;
        if (count == word.length()-1)return true;
        if (m-1 >= 0 && !used[m-1][n]){
            used[m-1][n] = true;
            if (dfs(m-1, n, count+1, word, used, board)) return true;
            used[m-1][n] = false;
        }
        if(m+1 < board.length && !used[m+1][n]){
            used[m+1][n] = true;
            if (dfs(m+1, n, count+1, word, used, board))return true ;
            used[m+1][n] = false;
        }
        if (n-1 >= 0 && !used[m][n-1]){
            used[m][n-1] = true;
            if (dfs(m, n-1, count+1, word, used, board))return true;
            used[m][n-1] = false;
        }
        if (n+1 < board[0].length && !used[m][n+1]){
            used[m][n+1] = true;
            if (dfs(m, n+1, count+1, word, used, board))return true;
            used[m][n+1] = false;
        }
        return false;
    }

    /**
     * 剑指 Offer 13. 机器人的运动范围
     * @param m
     * @param n
     * @param k
     * @return
     */
    public int movingCount(int m, int n, int k) {
        boolean[][] used = new boolean[m][n];
        used[0][0] = true;
        movingCount(m, n, k, 0, 0, used);
        return ans;
    }
    private void movingCount(int m, int n, int k, int x, int y, boolean[][] used){
        if (x-1 >= 0 && !used[x-1][y] && isValid(x-1, y, k)){ // 棋盘内、未访问过、位和小于 k
            ans++;
            used[x-1][y] = true;
            movingCount(m, n, k, x-1, y, used);
        }
        if (x+1 < m && !used[x+1][y] && isValid(x+1, y, k)){ // 棋盘内、未访问过、位和小于 k
            ans++;
            used[x+1][y] = true;
            movingCount(m, n, k, x+1, y, used);
        }
        if (y-1 >= 0 && !used[x][y-1] && isValid(x, y-1, k)){ // 棋盘内、未访问过、位和小于 k
            ans++;
            used[x][y-1] = true;
            movingCount(m, n, k, x, y-1, used);
        }
        if (y+1 < n && !used[x][y+1] && isValid(x, y+1, k)){ // 棋盘内、未访问过、位和小于 k
            ans++;
            used[x][y+1] = true;
            movingCount(m, n, k, x, y+1, used);
        }

    }
    private boolean isValid(int x, int y, int k){
        int sum= 0;
        char[] chars1 = (x+"").toCharArray();
        for (char c : chars1){
            sum += c - '0';
        }
        char[] chars2 = (y+"").toCharArray();
        for (char c : chars2){
            sum += c - '0';
        }
        return sum <= k;
    }


    /**
     * 1995. 统计特殊四元组
     * @param nums
     * @return
     */
    public int countQuadruplets(int[] nums) {
        int n = nums.length;
        // a + b = d - c
        Map<Integer, Integer> map = new HashMap<>(); // 记录所有 d-c 的数量
//        map.put(nums[n-1]-nums[n-2], 1);
        int ans = 0;
        for (int b = n-3; b >=1 ; b--) {
            // 先记录新出现的 d-c
            for (int d = n-1; d > b+1 ; d--) {
                map.put(nums[d]-nums[b+1], map.getOrDefault(nums[d]-nums[b+1], 0)+1);
            }
            for (int a = 0; a < b; a++) {
                ans += map.getOrDefault(nums[a] + nums[b], 0);
            }
        }
        return ans;
    }
}
