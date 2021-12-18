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
 * @TIME 2021/12/13 16:31
 * @DESCRIPTION 
 **/
public class Solution3 {

    /**
     * 491. 递增子序列
     * @param nums
     * @return
     */
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        findSubsequences_backtrack(nums, 0, new ArrayList<>(), ans);
        return ans;
    }
    private void findSubsequences_backtrack(int[] nums, int index, List<Integer> list, List<List<Integer>> ans){
        if (index >= nums.length)return;
        Set<Integer> set = new HashSet<>();// 用来树层去重
        for (int i = index; i < nums.length; i++) {
            if ((list.size() == 0 || nums[i] >= list.get(list.size()-1)) && !set.contains(nums[i])){ // 满足递增
                list.add(nums[i]);
                set.add(nums[i]);
                if (list.size() > 1){
                    ans.add(new ArrayList<>(list));
                }
                findSubsequences_backtrack(nums, i+1, list, ans);
                list.remove(list.size()-1);
            }
        }
    }


    /**
     * 47. 全排列 II
     * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        boolean[] used = new boolean[nums.length]; // 树枝去重
        permuteUnique_backtrack(nums, used, new ArrayList<>(), ans);
        return ans;
    }
    private void permuteUnique_backtrack(int[] nums, boolean[] used, List<Integer> path, List<List<Integer>> ans){
        if (path.size() == nums.length){
            ans.add(new ArrayList<>(path));
            return;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!used[i] && !set.contains(nums[i])){
                used[i] = true;
                path.add(nums[i]);
                set.add(nums[i]);
                permuteUnique_backtrack(nums, used, path, ans);
                // 回溯
                path.remove(path.size()-1);
                used[i] = false;
            }
        }
    }
}
