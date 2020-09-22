package ltd.beihu.leetcode.interview;

import javax.swing.tree.TreeNode;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 40个算法题
 *
 * @author Adam
 * @date 2020/7/11
 */
public class FortyAlgorithm {

    /**
     * 20. 有效的括号
     *
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。
     */
    public boolean isValid(String s) {
        Map<Character, Character> mapping = new HashMap<>();
        mapping.put(')', '(');
        mapping.put('}', '{');
        mapping.put(']', '[');
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!mapping.containsKey(c)) {
                stack.push(c);
            } else {
                if (stack.empty()) {
                    return false;
                }
                if (stack.pop().charValue() != mapping.get(c).charValue()) {
                    return false;
                }
            }
        }
        return stack.empty();
    }

    /**
     * 239. 滑动窗口最大值
     * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
     * 返回滑动窗口中的最大值。
     * 进阶：
     * 你能在线性时间复杂度内解决此题吗？
     * 示例:
     * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
     * 输出: [3,3,5,5,6,7]
     * 解释:
     *   滑动窗口的位置                最大值
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     *  1 [3  -1  -3] 5  3  6  7       3
     *  1  3 [-1  -3  5] 3  6  7       5
     *  1  3  -1 [-3  5  3] 6  7       5
     *  1  3  -1  -3 [5  3  6] 7       6
     *  1  3  -1  -3  5 [3  6  7]      7
     * 提示：
     * 1. 1 <= nums.length <= 10^5
     * 2. -10^4 <= nums[i] <= 10^4
     * 3. 1 <= k <= nums.length
     *
     * 方案一：
     *      优先队列，维护一个k窗口大小的大顶堆
     *      复杂度：O(nlogk)
     * 方案二：
     *      双端队列：两端都可以进出元素，建设右进左出，那么维护左边永远是当前最大的一个元素
     *      复杂度：O(n * 1)
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        // 滑动窗口

        return null;
    }

    /**
     * 242. 有效的字母异位词
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     * 示例 1:
     * 输入: s = "anagram", t = "nagaram"
     * 输出: true
     *
     * 示例 2:
     * 输入: s = "rat", t = "car"
     * 输出: false
     * 说明:
     * 你可以假设字符串只包含小写字母。
     *
     * 进阶:
     * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
     *
     * 解决方案：
     *      方案一：
     *          排序：O(nlogn)
     *      方案二：
     *          Map计数：{letter, count}
     *          复杂度：O(n)
     */
    public boolean isAnagram(String s, String t) {

        return false;
    }

    /**
     * 15. 三个数之和
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
     *
     * 注意：答案中不可以包含重复的三元组。
     *
     * 解决方案：
     *      方案一：暴力O(n3)
     *      方案二：用一个set存储所有数，两层循环，看 -(a+b) 是否在set中 O(n2)
     *      方案三：先排序，然后一层循环a，在每次循环里面对后续元素进行双端压缩查找和为-a的原始，指针转换  （忽略）
     */


    /**
     * 树：左小中右大
     * 二叉搜索树：平均复杂度 O(logn)，最坏复杂度 O(n) - 即只有小元素或只有大元素，一直排成一链
     * 二叉平衡数：
     *      红黑树：
     *      Splay Tree：
     *      AVL Tree：
     * KD Tree：
     *
     * 前中后续遍历主要看跟节点的位置
     *      前：根左右
     *      中：左根右
     *      后：左右根
     */

    /**
     * 98. 验证二叉搜索树
     * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
     * 假设一个二叉搜索树具有如下特征：
     *      节点的左子树只包含小于当前节点的数。
     *      节点的右子树只包含大于当前节点的数。
     *      所有左子树和右子树自身必须也是二叉搜索树。
     * 示例 1:
     *
     * 输入:
     *     2
     *    / \
     *   1   3
     * 输出: true
     * 示例 2:
     *
     * 输入:
     *     5
     *    / \
     *   1   4
     *      / \
     *     3   6
     * 输出: false
     * 解释: 输入为: [5,1,4,null,null,3,6]。
     *      根节点的值为 5 ，但是其右子节点值为 4 。
     *
     * 解决方案：
     *      方案一：中序遍历，遍历后的数组是否为升序的序列，只需要保留前继节点 O(n)
     *      方案二：递归 左右节点
     *          复杂度：每个节点访问且只访问一次 O(n)
     *
     */
    public boolean isValidBST(TreeNode root) {
        return false;
    }

    /**
     * 235. 二叉搜索树的最近公共祖先 - 可更块
     * 236. 二叉树的最近公共祖先
     *
     * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     *
     * 解决方案：
     *      方案一：路径path寻找，从上往下，标记路径 ，寻找重合原始 O(n)
     *      方案二：递归
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return null;
    }

    // main

    public static void main(String[] args) {
        testMyQueue();
    }

    public static void testMyQueue() {
        MyQueue myQueue = new MyQueue();
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



/**
 * 232. 用栈实现队列
 *      官方实现不需要考虑多线程操作
 */
class MyQueue {

    private static Stack<Integer> inStack;
    private static Stack<Integer> outStack;
    private static ReentrantLock lock;

    /** Initialize your data structure here. */
    public MyQueue() {
        inStack = new Stack<>();
        outStack = new Stack<>();
        lock = new ReentrantLock();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        lock.lock();
        try {
            inStack.push(x);
        } finally {
            lock.unlock();
        }
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        lock.lock();
        try {
            if (outStack.isEmpty()) {
                while (!inStack.isEmpty()) {
                    outStack.push(inStack.pop());
                }
            }
            return outStack.pop();
        } finally {
            lock.unlock();
        }
    }

    /** Get the front element. */
    public int peek() {
        lock.lock();
        try {
            if (outStack.isEmpty()) {
                while (!inStack.isEmpty()) {
                    outStack.push(inStack.pop());
                }
            }
            return outStack.peek();
        } finally {
            lock.unlock();
        }
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        if (inStack.isEmpty() && outStack.isEmpty()) {
            return true;
        }
        return false;
    }
}


/**
 * 703. 数据流中的第K大元素
 * 设计一个找到数据流中第K大元素的类（class）。注意是排序后的第K大元素，不是第K个不同的元素。
 * 你的 KthLargest 类需要一个同时接收整数 k 和整数数组nums 的构造器，它包含数据流中的初始元素。每次调用 KthLargest.add，返回当前数据流中第K大的元素。
 * 示例:
 * int k = 3;
 * int[] arr = [4,5,8,2];
 * KthLargest kthLargest = new KthLargest(3, arr);
 * kthLargest.add(3);   // returns 4
 * kthLargest.add(5);   // returns 5
 * kthLargest.add(10);  // returns 5
 * kthLargest.add(9);   // returns 8
 * kthLargest.add(4);   // returns 8
 * 说明:
 * 你可以假设 nums 的长度≥ k-1 且k ≥ 1。
 *
 * 方案一：
 *      维护前一个k个最大值的数据，进行排序，每进去一个元素，比当前最小的一个元素还小则抛弃，大于最小元素则剔除最小元素，再次进行排序
 *      复杂度分析：n个元素，每次需要对前k个元素进行排序，排序最好的算法是快排，复杂度是klogk；所以总体的复杂度是 O(n * klogk)
 * 方案二：
 *      优先队列（小顶堆）；维护一个前k个元素的小顶堆，最小的元素在顶部，获取的复杂度是O(1)，每进入一个元素，和堆顶元素比较，如果小于则抛弃，
 *      如果大于则将当前堆顶元素抛弃，将新元素放入堆中，进行重新排列（logk）
 *      复杂度：O(nlogk)
 */
class KthLargest {

    public KthLargest(int k, int[] nums) {

    }

    public int add(int val) {
        return 0;
    }
}