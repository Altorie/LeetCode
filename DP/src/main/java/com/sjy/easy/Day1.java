package com.sjy.easy;

public class Day1 {

    /**
     * 509.斐波那契数列
     * @param n
     * @return
     */
    public int fib(int n) {
        // 1.暴力递归
        /*if(n == 0){
            return 0;
        } else if (n == 1){
            return 1;
        } else {
            return fib(n-1) + fib(n-2);
        }*/
        // 2.迭代dp+状态压缩
        if (n==0 || n==1){
            return n;
        } else {
            int a = 0;
            int b = 1;
            int result = 1;
            for (int i = 3; i <= n; i++) {
                a = b;
                b=result;
                result = a+b;
            }
            return result;
        }
    }

    /**
     * 1137.第 N 个泰波那契数
     * @param n
     * @return
     */
    public int tribonacci(int n) {
        if (n==0 || n==1){
            return n;
        } else if (n == 2){
            return 1;
        } else {
            int a = 0;
            int b = 1;
            int c = 1;
            int result = 2;
            for (int i = 4; i <= n; i++) {
                a = b;
                b = c;
                c = result;
                result = a + b + c;
            }
            return result;
        }
    }
}
