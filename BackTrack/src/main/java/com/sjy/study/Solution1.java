package com.sjy.study;

import java.util.ArrayList;
import java.util.List;

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
 * @TIME 2021/11/24 16:12
 * @DESCRIPTION 
 **/
public class Solution1 {

    public static void main(String[] args) {
        Solution1 s = new Solution1();
        s.combinationSum3(3, 7);
    }

    /**
     * 77. 组合
     * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
     *
     * 你可以按 任何顺序 返回答案。
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        combine_backtrack(n, k, 1, new ArrayList<>(), ans);
        return ans;
    }
    private void combine_backtrack(int n, int k, int start, List<Integer> list, List<List<Integer>> ans){
        if (list.size() == k){
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; k-list.size() <= n-i+1; i++) { // 剪枝：如果把 start及之后的所有元素都选择，最后总数量小于 k，就没必要遍历
            list.add(i);
            // 下一层直接从 i+1 开始，相当于去重
            combine_backtrack(n, k, i+1, list, ans);
            list.remove(list.size()-1);
        }
    }

    /**
     * 216. 组合总和 III
     * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
     * 说明：
     *      所有数字都是正整数。
     *      解集不能包含重复的组合
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        if (k <= 9){
            combinationSum3_backtrack(k, n, 1, new ArrayList<>(), ans);
        }
        return ans;
    }
    private void combinationSum3_backtrack(int k, int n, int start, List<Integer> list, List<List<Integer>> ans){
        if (list.size()==k){
            if (n == 0)ans.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i <= 9 ; i++) {
            // 剪枝：如果剩下的元素从 start 开始连续取，结果比 n 大，不需要遍历
            if ((k - list.size()) * i <= n){
                list.add(i);
                combinationSum3_backtrack(k, n-i, i+1, list, ans);
                list.remove(list.size()-1);
            }
        }
    }
}
