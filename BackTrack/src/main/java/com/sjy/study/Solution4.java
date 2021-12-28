package com.sjy.study;

import java.util.*;

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
 * @TIME 2021/12/18 13:58
 * @DESCRIPTION 
 **/
public class Solution4 {
    List<String> ans;
    /**
     * 332. 重新安排行程
     * 相当于一个变体的深搜
     *
     * @param tickets
     * @return
     */
    public List<String> findItinerary(List<List<String>> tickets) {
        // 起点 -> （终点，数量）
        // 利用 TreeSet 排序
        // 数量代表有几张 起点 -> 终点 的机票
        Map<String, TreeMap<String, Integer>> map = new HashMap<>();
        for (List<String> ticket : tickets){
            String start = ticket.get(0);
            String end = ticket.get(1);
            if (!map.containsKey(start)){
                map.put(start, new TreeMap<>());
            }
            if (!map.containsKey(end)){
                map.put(end, new TreeMap<>());
            }
            map.get(start).put(end, map.get(start).getOrDefault(end, 0)+1);
        }
        List<String> list = new ArrayList<>();
        list.add("JFK");
        dfs("JFK", map, tickets.size()+1, list);
        return list;
    }
    private boolean dfs(String start, Map<String, TreeMap<String, Integer>> map, int size, List<String> list){
        if (list.size() == size){ // 找到结果
            return true;
        }
        TreeMap<String, Integer> nbrs = map.get(start);
        for (String nbr : nbrs.keySet()){
            int num = nbrs.get(nbr);
            if (num > 0){
                list.add(nbr);
                nbrs.put(nbr, num-1);
                boolean flag = dfs(nbr, map, size, list);
                if (flag)return true;
                // 回溯
                list.remove(list.size()-1);
                nbrs.put(nbr, num);
            }
        }
        return false;
    }


    /**
     * 51. N 皇后
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        char[][] chessboard = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(chessboard[i], '.');
        }
        solveNQueens_backtrack(n, 0, chessboard, ans);
        return ans;
    }
    private void solveNQueens_backtrack(int n, int row, char[][] chessboard, List<List<String>> ans){
        if (row == n){
            List<String> list = new ArrayList<>();
            for (char[] chars : chessboard){
                list.add(new String(chars));
            }
            ans.add(list);
            return;
        }
        // 在第 row 行的 0 ~ n-1 列挑选一个合法位置放皇后
        for (int col = 0; col < n; col++){
            if (isValid(n, row, col, chessboard)){ // 这个位置能放皇后
                chessboard[row][col] = 'Q';
                solveNQueens_backtrack(n, row+1, chessboard, ans);
                chessboard[row][col] = '.';
            }
        }

    }
    private boolean isValid(int n, int row, int col, char[][] chessboard){ // 在 (row, col) 放置皇后是否合法
        // col 这一列是否冲突
        for (int i = 0; i < row; i++) {
            if (chessboard[i][col] == 'Q')return false;
        }
        // 检查左对角线
        for (int i = row-1, j= col-1; i >= 0 && j >= 0 ; i--, j--) {
            if (chessboard[i][j] == 'Q')return false;
        }
        // 检查右对角线
        for (int i = row-1, j = col+1; i >= 0 && j < n; i--, j++){
            if (chessboard[i][j] == 'Q')return false;
        }
        return true;
    }

    /**
     * 37. 解数独
     * @param board
     */
    public void solveSudoku(char[][] board) {
        solveSudoku_backtrack(0, 0, board);
    }
    private boolean solveSudoku_backtrack(int m, int n, char[][] board){
        if (m == board.length){
            return true;
        }
        int nextM = n==8? m+1 : m;
        int nextN = n==8? 0 : n+1;
        if (board[m][n]=='.'){ // 需要填
            for (char c = '1'; c <= '9'; c++){
                if (isValid(m, n, c, board)){
                    board[m][n] = c;
                    if (solveSudoku_backtrack(nextM, nextN, board))return true;
                    board[m][n] = '.';
                }
            }
        } else {
            if (solveSudoku_backtrack(nextM, nextN, board))return true;
        }
        return false;
    }
    private boolean isValid(int m, int n, char c, char[][] board){
        // 检查行
        for (int i = 0; i < 9; i++) {
            if (c == board[m][i])return false;
        }
        // 检查列
        for (int i = 0; i < 9; i++) {
            if (c == board[i][n])return false;
        }
        // 检查 3*3 子矩阵
        int startM = m/3*3;
        int startN = n/3*3;
        for (int i = startM; i < startM+3; i++) {
            for (int j = startN; j < startN+3; j++) {
                if (c == board[i][j])return false;
            }
        }
        return true;
    }

}
