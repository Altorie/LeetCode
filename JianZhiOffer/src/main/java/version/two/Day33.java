package version.two;
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
 * @TIME 2021/12/9 10:35
 * @DESCRIPTION 
 **/
public class Day33 {

    public static void main(String[] args) {
        Day33 d = new Day33();
        d.numDistinct("rabbbit", "rabbit");
    }
    /**
     * 剑指 Offer II 097. 子序列的数目
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        if (m == 0 || m < n)return 0;
        if (n == 0)return 1;
        // dp[i][j]：s[0, j) 的子序列中 t[0, i)的数目
        int[][] dp = new int[n+1][m+1];
        // 初始化: i = 0 代表一个空字符串，对于 s 来说把字符全删除即可
        for (int j = 0; j <= m ; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= m; j++) {
                if (s.charAt(j-1) == t.charAt(i-1)){
                    dp[i][j] += dp[i][j-1]; // 从 s 选择子序列时不选择 第 j-1 个
                    dp[i][j] += dp[i-1][j-1]; // 从 s 选择子序列时选择 第 j-1 个
                } else {
                    // 只能不选择
                    dp[i][j] += dp[i][j-1];
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 剑指 Offer II 098. 路径的数目
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        // 1. 使用dp
        // 到 [i,j] 去的路径数
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

    /**
     * 剑指 Offer II 099. 最小路径之和
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        int temp = 0;
        for (int i = 0; i < m; i++) {
            dp[i][0] = grid[i][0] + temp;
            temp = dp[i][0];
        }
        temp = 0;
        for (int j = 0; j < n; j++) {
            dp[0][j] = grid[0][j] + temp;
            temp = dp[0][j];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i-1][j], dp[i][j-1]);
            }
        }
        return dp[m-1][n-1];
    }
}
