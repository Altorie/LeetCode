package version.two;

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
 * @TIME 2021/12/3 21:58
 * @DESCRIPTION 
 **/
public class Day27 {

    public static void main(String[] args) {
        Day27 d = new Day27();
        d.combinationSum(new int[]{2,3,5}, 8);
    }
    /**
     * 剑指 Offer II 079. 所有子集
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        subsets_backtrack(nums, 0, new ArrayList<>(), ans);
        return ans;
    }
    private void subsets_backtrack(int[] nums, int index, List<Integer> subset, List<List<Integer>> ans){
        if (index == nums.length){
            List<Integer> list = new ArrayList<>(subset);
            ans.add(list);
            return;
        }
        subsets_backtrack(nums, index+1, subset, ans);
        subset.add(nums[index]);
        subsets_backtrack(nums, index+1, subset, ans);
        subset.remove(subset.size()-1);
    }

    /**
     * 剑指 Offer II 080. 含有 k 个元素的组合
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        combine_backtrack(n, 1, k, new ArrayList<>(), ans);
        return ans;
    }
    private void combine_backtrack(int n, int i, int k, List<Integer> list, List<List<Integer>> ans){
        if (list.size() == k){
            ans.add(new ArrayList<>(list));
            return;
        }
        if (n-i+1+list.size() < k) return;
        // 加入数字 i
        list.add(i);
        combine_backtrack(n, i+1, k, list, ans);
        list.remove(list.size()-1);
        // 不加入数字 i
        combine_backtrack(n, i+1, k, list, ans);
    }

    /**
     * 剑指 Offer II 081. 允许重复选择元素的组合
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Set<String> set = new HashSet<>(); // 去重
        combinationSum_backtrack(candidates, 0, target, new ArrayList<>(), ans, set);
        return ans;
    }
    private void combinationSum_backtrack(int[] candidates, int sum, int target, List<Integer>list, List<List<Integer>> ans, Set<String> set){
        if (sum == target){
            List<Integer> newList = new ArrayList<>(list);
            Collections.sort(newList);
            StringBuffer sb = new StringBuffer();
            for (int i : newList){
                sb.append(i);
            }
            if (!set.contains(sb.toString())){
                ans.add(newList);
                set.add(sb.toString());
            }
            return;
        }
        if (sum > target)return;
        for (int candidate : candidates) {
            list.add(candidate);
            combinationSum_backtrack(candidates, sum+candidate, target, list, ans, set);
            list.remove(list.size()-1);
        }
    }
}
