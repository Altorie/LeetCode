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
 * @TIME 2021/11/27 9:53
 * @DESCRIPTION 
 **/
public class Day20 {

    public static void main(String[] args) {
        System.out.println(1+2+"-"+2+1);
    }

    /**
     * 剑指 Offer II 060. 出现频率最高的 k 个数字
     * 给定一个整数数组 nums 和一个整数 k ，请返回其中出现频率前 k 高的元素。可以按 任意顺序 返回答案。
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        /**
         * 基于小顶堆的的优先级队列
         */
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int num : nums) {
//            map.put(num, map.getOrDefault(num, 0)+1);
//        }
//        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(k, Comparator.comparingInt(Map.Entry::getValue));
//        for (Map.Entry<Integer, Integer> entry : map.entrySet()){
//            if (queue.size() < k){
//                queue.offer(entry);
//            } else {
//                if (entry.getValue() > queue.element().getValue()){
//                    queue.poll();
//                    queue.offer(entry);
//                }
//            }
//        }
//        int[] ans = new int[k];
//        for (int i = 0; i < k; i++) {
//            ans[i] = queue.poll().getKey();
//        }
//        return ans;
        /**
         * 使用快速排序
         */
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        Object[] entries = map.entrySet().toArray();
        quickSort(entries, 0, entries.length-1, k);
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = ((Map.Entry<Integer, Integer>)entries[i]).getKey();
        }
        return ans;

    }
    private void quickSort(Object[] entries, int start, int end, int k){
        if (start >= end)return;
        int l = start;
        int r = end;
        int base = ((Map.Entry<Integer, Integer>)entries[start]).getValue();
        for (int i = start; i <= r; i++) {

            while (i <= r && ((Map.Entry<Integer, Integer>)entries[i]).getValue() < base){
                Object temp = entries[i];
                entries[i] = entries[r];
                entries[r] = temp;
                r--;
            }
            if (((Map.Entry<Integer, Integer>)entries[i]).getValue() > base){
                Object temp = entries[i];
                entries[i] = entries[l];
                entries[l] = temp;
                l++;
            }
        }
        if (k-1 < l){
            quickSort(entries, start, l-1, k);
        } else if (k-1 > r){
            quickSort(entries, r+1, end, k);
        }
    }

    /**
     * 剑指 Offer II 061. 和最小的 k 个数对
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        /**
         * 利用小顶堆
         * 假设已经选出了 n 个结果: (u1,v1) (u2,v2) ... (un,vn)
         * 那么第 n+1 个 结果一定是上述 n 个结果的所有邻居中的下一个
         * (u1,v1)的邻居指的是 (u1 + 1,v1) 或者 (u1, v1 +1)
         * 从一堆数据中获取最小值，可以使用小顶堆
         *
         * 由于一个数对可以是两个数对的邻居，因此要去重，避免重复添加
         */
//        List<List<Integer>> ans = new ArrayList<>();
//        PriorityQueue<List<Integer>> queue = new PriorityQueue<>((a, b)-> nums1[a.get(0)] + nums2[a.get(1)] - nums1[b.get(0)] - nums2[b.get(1)]);
//        Set<String> set = new HashSet<>();
//        queue.offer(Arrays.asList(0, 0));
//        while (ans.size() < k && !queue.isEmpty()){
//            // 最小堆堆顶是答案之一
//            List<Integer> list = queue.poll();
//            ans.add(Arrays.asList(nums1[list.get(0)], nums2[list.get(1)]));
//            // 将可能的下一个答案放入堆中
//            int i = list.get(0)+1;
//            if (i < nums1.length && !set.contains(i+"-"+list.get(1))){ // 保证在 nums1 的范围内且没进过堆
//                set.add(i+"-"+list.get(1));
//                queue.offer(Arrays.asList(i, list.get(1)));
//            }
//            i = list.get(1)+1;
//            if (i < nums2.length && !set.contains(list.get(0)+"-"+i)){
//                set.add(list.get(0)+"-"+i);
//                queue.offer(Arrays.asList(list.get(0), i));
//            }
//        }
//        return ans;

        /**
         * 小顶堆的进一步优化，不用去重
         */
        List<List<Integer>> ans = new ArrayList<>();
        PriorityQueue<List<Integer>> queue = new PriorityQueue<>((a, b)-> nums1[a.get(0)] + nums2[a.get(1)] - nums1[b.get(0)] - nums2[b.get(1)]);
        for (int i = 0; i < nums1.length; i++) {
            // 把所有的 (u,0) 入队列
            queue.offer(Arrays.asList(i, 0));
        }
        while (ans.size() < k && !queue.isEmpty()){
            // 最小堆堆顶是答案之一
            List<Integer> list = queue.poll();
            ans.add(Arrays.asList(nums1[list.get(0)], nums2[list.get(1)]));
            int i = list.get(1)+1;
            if (i < nums2.length){
                queue.offer(Arrays.asList(list.get(0), i));
            }
        }
        return ans;
    }
}

