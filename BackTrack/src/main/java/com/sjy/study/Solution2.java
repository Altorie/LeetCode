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
 * @TIME 2021/12/7 12:22
 * @DESCRIPTION 
 **/
public class Solution2 {

    public static void main(String[] args) {

    }

    /**
     * 39. 组合总和
     * 找出和等于 target 的所有不重复组合，每个元素可以重复选取
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        combinationSum_backtrack(candidates, target, 0, new ArrayList<>(), ans);
        return ans;
    }
    private void combinationSum_backtrack(int[] candidates, int target, int start, List<Integer> path, List<List<Integer>> ans){
        if (target == 0){
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < candidates.length ; i++) {
            if (target - candidates[i] >=0){
                path.add(candidates[i]);
                combinationSum_backtrack(candidates, target-candidates[i], i, path, ans);
                path.remove(path.size()-1);
            }
        }
    }

    /**
     * 40. 组合总和 II
     * candidates 中有重复元素，每个元素只能被使用一次，组合不能重复
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList<>();
        combinationSum2_backtrack(candidates, target, 0, new ArrayList<>(), ans);
        return ans;
    }
    private void combinationSum2_backtrack(int[] candidates, int target, int start, List<Integer> path, List<List<Integer>> ans){
        if (target == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }
        while (start < candidates.length){
            if (target - candidates[start] >= 0){ // 剪枝
                path.add(candidates[start]);
                // start+1 实现在同一条路径上去重
                combinationSum2_backtrack(candidates, target-candidates[start], start+1, path, ans);
                path.remove(path.size()-1);
                // 回溯树同一层进行去重
                int value = candidates[start];
                while (start < candidates.length && candidates[start] == value)start++;
            } else {
                break;
            }
        }
    }

    /**
     * 131. 分割回文串
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        partition_backtrack(s, 0, new ArrayList<>(), ans);
        return ans;
    }
    private void partition_backtrack(String s, int start, List<String> list, List<List<String>> ans){
        if (start == s.length()){
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            int l = start;
            int r = i;
            boolean flag = true;
            while (l < r){
                if (s.charAt(l) == s.charAt(r)){
                    l++;
                    r--;
                } else {
                    flag = false;
                    break;
                }
            }
            if (flag){ // 是回文串
                list.add(s.substring(start, i+1));
                partition_backtrack(s, i+1, list, ans);
                list.remove(list.size()-1);
            }
        }
    }

}
