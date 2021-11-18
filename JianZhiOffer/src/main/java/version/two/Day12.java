package version.two;

import sun.awt.image.ImageWatched;

import java.util.Deque;
import java.util.LinkedList;

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
 * @TIME 2021/11/18 10:09
 * @DESCRIPTION 
 **/
public class Day12 {
    public static void main(String[] args) {
        Day12 d = new Day12();
        d.asteroidCollision(new int[]{5,10,-5});
    }
    /**
     * 剑指 Offer II 036. 后缀表达式
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new LinkedList<>();
        for (String str : tokens){
            try {
                int i = Integer.parseInt(str);
                stack.push(i);
            }catch (Exception e){
                int second = stack.pop();
                int first = stack.pop();
                if (str.equals("+"))stack.push(first+second);
                if (str.equals("-"))stack.push(first-second);
                if (str.equals("/"))stack.push(first/second);
                if (str.equals("*"))stack.push(first*second);
            }
        }
        return stack.pop();
    }

    /**
     * 剑指 Offer II 037. 小行星碰撞
     * @param asteroids
     * @return
     */
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new LinkedList<>();
        stack.push(asteroids[0]);
        for (int i = 1; i < asteroids.length; i++) {
            if (asteroids[i] > 0 || stack.isEmpty() || stack.getFirst() * asteroids[i] > 0){
                stack.push(asteroids[i]);
                continue;
            }
            boolean add = true;
            while (!stack.isEmpty() && stack.getFirst()>0){
                int top = Math.abs(stack.getFirst());
                int cur = Math.abs(asteroids[i]);
                if (top > cur){ // 当前元素消失
                    add = false;
                    break;
                } else if (top == cur){ // 两个都消失
                    add = false;
                    stack.pop();
                    break;
                } else { // 栈顶元素消失
                    stack.pop();
                }
            }
            if (add) stack.push(asteroids[i]);
        }
        int[] ans = new int[stack.size()];
        for (int i = ans.length-1; i >= 0; i--) {
            ans[i] = stack.pop();
        }
        return ans;
    }

    /**
     * 剑指 Offer II 038. 每日温度
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length;
        int[] ans = new int[len];
        for (int i = len-2; i >= 0 ; i--) {
            int index = i+1;
            while (true){
                if (temperatures[i]<temperatures[index]){
                    ans[i] = index-i;
                    break;
                }
                if (ans[index] == 0){
                    ans[i] = 0;
                    break;
                }else {
                    index += ans[index];
                }
            }
        }
        return ans;
    }
}
