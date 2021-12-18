package version.one;

import java.util.Map;
import java.util.Stack;

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
 * @TIME 2021/12/16 21:11
 * @DESCRIPTION 
 **/
public class Day1 {
    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.push(2147483646);
        stack.push(2147483646);
        stack.push(2147483647);
        System.out.println(stack.top());
        stack.pop();
        System.out.println(stack.min);
        stack.pop();
        System.out.println(stack.min);
        stack.pop();
        stack.push(2147483647);
        System.out.println(stack.top());
        System.out.println(stack.min);
        stack.push(-2147483648);
        System.out.println(stack.top());
        System.out.println(stack.min);
        stack.pop();
        System.out.println(stack.min);
    }
}

/**
 * 剑指 Offer 09. 用两个栈实现队列
 */
class CQueue {

    private Stack<Integer> stack1 = new Stack<>();
    private Stack<Integer> stack2 = new Stack<>();
    int size = 0;

    public CQueue() {

    }

    public void appendTail(int value) {
        for (int i = 0; i < size; i++) {
            stack2.push(stack1.pop());
        }
        stack2.push(value);
        size++;
        for (int i = 0; i < size; i++) {
            stack1.push(stack2.pop());
        }
    }

    public int deleteHead() {
        if (size==0)return -1;
        size--;
        return stack1.pop();
    }
}

/**
 * 剑指 Offer 30. 包含min函数的栈
 */
class MinStack {

    Stack<Integer> stack = new Stack<>();
    int min = Integer.MAX_VALUE;

    /** initialize your data structure here. */
    public MinStack() {

    }

    public void push(int x) {
        stack.push(x);
        min = Math.min(min, x);
        stack.push(min);
    }

    public void pop() {
        stack.pop();
        stack.pop();
        if (stack.size()==0){
            min = Integer.MAX_VALUE;
        } else {
            min = stack.peek();
        }
    }

    public int top() {
        stack.pop();
        int ans = stack.peek();
        stack.push(min);
        return ans;
    }

    public int min() {
        return min;
    }
}
