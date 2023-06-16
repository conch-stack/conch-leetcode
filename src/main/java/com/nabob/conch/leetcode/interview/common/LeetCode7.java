package com.nabob.conch.leetcode.interview.common;

/**
 * 7. 整数反转
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * 注意:
 *
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为[−231, 231− 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 *
 * @author Adam
 * @date 2020/9/16
 */
public class LeetCode7 {

    public static int reverse(int x) {
        int rs = 0;
        while (x != 0) {
            int pop = x % 10;
            // 大于int最大值，或者等于int最大值，但是pop大于（2^32-1）的个位值 - 溢出
            if (rs > Integer.MAX_VALUE/10 || (rs == Integer.MAX_VALUE/10 && pop > 7)) {
                return 0;
            }
            // 小于int最小值，或者等于int最小值，但是pop小于（-2^32）的个位值 - 溢出
            if (rs < Integer.MIN_VALUE/10 || (rs == Integer.MIN_VALUE/10 && pop < -8)) {
                return 0;
            }
            rs = rs * 10 + pop;
            x /= 10;
        }

        return rs;
    }

    public static void main(String[] args) {
        System.out.println(reverse(1534236469));
    }

}
