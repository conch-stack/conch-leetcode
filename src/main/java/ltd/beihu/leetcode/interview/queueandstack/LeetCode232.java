package ltd.beihu.leetcode.interview.queueandstack;

import java.util.Stack;

/**
 * 232. 用栈实现队列
 *      官方实现不需要考虑多线程操作
 *
 * @author Adam
 * @date 2020/9/26
 */
public class LeetCode232 {

    private static Stack<Integer> inStack;
    private static Stack<Integer> outStack;

    /** Initialize your data structure here. */
    public LeetCode232() {
        inStack = new Stack<>();
        outStack = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        inStack.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
        return outStack.pop();
    }

    /** Get the front element. */
    public int peek() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
        return outStack.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        if (inStack.isEmpty() && outStack.isEmpty()) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        LeetCode232 myQueue = new LeetCode232();
        myQueue.push(1);
        myQueue.push(2);
        myQueue.push(3);
        System.out.println(myQueue.pop());
        myQueue.push(4);
        System.out.println(myQueue.peek());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.empty());

        while (!myQueue.empty()) {
            System.out.println(myQueue.pop());
        }
    }
}
