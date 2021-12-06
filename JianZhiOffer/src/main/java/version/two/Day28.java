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
 * @TIME 2021/12/4 11:00
 * @DESCRIPTION 
 **/
public class Day28 {

    public static void main(String[] args) {
        Day28 d = new Day28();
        d.combinationSum2(new int[]{10,1,2,7,6,1,5}, 8);
    }

    /**
     * 剑指 Offer II 082. 含有重复元素集合的组合
     * 给定一个可能有重复数字的整数数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
     *
     * candidates 中的每个数字在每个组合中只能使用一次，解集不能包含重复的组合
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Set<String> set = new HashSet<>(); // 去重
        Arrays.sort(candidates);
        combinationSum2_backtrack(candidates, 0, 0, target,
                new ArrayList<>(), ans, set);
        return ans;
    }
    private void combinationSum2_backtrack(int[] candidates, int index, int sum, int target,
                                           List<Integer> list, List<List<Integer>> ans, Set<String> set){
        if (index >= candidates.length){
            if (sum == target){
                List<Integer> newList = new ArrayList<>(list);
                Collections.sort(newList);
                StringBuffer sb = new StringBuffer();
                for (int i : newList){
                    sb.append(i).append('_');
                }
                if (!set.contains(sb.toString())){
                    ans.add(newList);
                    set.add(sb.toString());
                }
                return;
            }
            return;
        }

        // 选择
        if (sum+candidates[index] <= target){
            list.add(candidates[index]);
            combinationSum2_backtrack(candidates, index+1, sum+candidates[index], target,
                    list, ans, set);
            list.remove(list.size()-1);
            // 不选择
            combinationSum2_backtrack(candidates, index+1, sum, target,
                    list, ans, set);
        } else {
            combinationSum2_backtrack(candidates, candidates.length, sum, target,
                    list, ans, set);
        }
    }


    /**
     * 剑指 Offer II 083. 没有重复元素集合的全排列
     * 给定一个不含重复数字的整数数组 nums ，返回其 所有可能的全排列 。可以 按任意顺序 返回答案。
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums){
            list.add(num);
        }
        List<List<Integer>> ans = new ArrayList<>();
        permute_backtrack(list, new ArrayList<>(), ans);
        return ans;
    }
    private void permute_backtrack(List<Integer> list, List<Integer> l, List<List<Integer>> ans){
        if (list.size() == 0 ){
            ans.add(new ArrayList<>(l));
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            int value = list.get(i);
            l.add(value);
            list.remove(i);
            permute_backtrack(list, l, ans);
            list.add(i, value);
            l.remove(l.size()-1);
        }
    }

    /**
     * 剑指 Offer II 084. 含有重复元素集合的全排列
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums){
            list.add(num);
        }
        List<List<Integer>> ans = new ArrayList<>();
        Set<String> set = new HashSet<>();
        permuteUnique_backtrack(list, new ArrayList<>(), ans, set);
        return ans;
    }
    private void permuteUnique_backtrack(List<Integer> list, List<Integer> l, List<List<Integer>> ans, Set<String> set){
        if (list.size() == 0 ){
            StringBuffer sb = new StringBuffer();
            for (int i : l){
                sb.append(l).append('_');
            }
            String key = sb.toString();
            if (!set.contains(key)){
                ans.add(new ArrayList<>(l));
                set.add(key);
            }
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            int value = list.get(i);
            l.add(value);
            list.remove(i);
            permuteUnique_backtrack(list, l, ans, set);
            list.add(i, value);
            l.remove(l.size()-1);
        }
    }
}
