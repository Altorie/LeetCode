package com.sjy.competition.singleweek263;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.countMaxOrSubsets(new int[]{1,1,2}));
    }
    public int countMaxOrSubsets(int[] nums) {
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        a(nums, map, 0, 0);
        int max = 0;
        for(int key: map.keySet()){
            max = Integer.max(max, key);
        }
        return map.get(max);

    }
    private void a(int[] nums, Map<Integer, Integer> map, int cur, int value){
        if (cur == nums.length){
            map.put(value, map.getOrDefault(value, 0)+1);
            return;
        }
        a(nums, map, cur+1, value);
        a(nums, map, cur+1, value|nums[cur]);

    }
}
