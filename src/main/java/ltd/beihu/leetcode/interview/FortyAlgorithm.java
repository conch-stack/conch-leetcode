package ltd.beihu.leetcode.interview;

import ltd.beihu.leetcode.utils.GsonUtils;

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
     * 206. 反转链表
     *
     * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
     * 示例:
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     * <p>
     * 限制：
     * 0 <= 节点个数 <= 5000
     */
    public static ListNode reverseList1(ListNode head) {

        ListNode pre = null;
        ListNode current = head;

        while (current != null) {
            ListNode temp = current.next;
            current.next = pre;
            pre = current;
            current = temp;
        }
        return pre;
    }
    public static ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode a = reverseList2(head.next);
        head.next.next = head;  // 5的next 换成 4∂
        head.next = null;  // 4的next 换成 null
        return a;  // 返回当前节点 5
    }

    /**
     * 24. 两两交换链表中的节点
     *
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     * 示例:
     * 给定 1->2->3->4, 你应该返回 2->1->4->3.
     *
     * 复杂度分析
     * 时间复杂度：O(N)，其中 N 指的是链表的节点数量。
     * 空间复杂度：O(N)，递归过程使用的堆栈空间。
     */
    public static ListNode swapPairs1(ListNode head) {

        // 假节点 核心 - 所有转换指针都会修改dummy的地址
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode preNode = dummy;
        while (head != null && head.next != null) {
            ListNode firstNode = head;
            ListNode secondNode = head.next;

            // 交换两个节点：
            // 1. 把3放到1的next 得到  1->3->4
            // 2. 把1->3挂到2的next
            // 3. 将交换后的节点 放到后续节点的头部
            firstNode.next = secondNode.next;
            secondNode.next = firstNode;
            preNode.next = secondNode;

            // 下一次迭代 准备
            preNode = firstNode;  // preNode 只是用来记录处理节点的前一个node
            head = firstNode.next;
        }

        return dummy.next;
    }
    public static ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode firstNode = head;  // 第一个节点
        ListNode secondNod = head.next;  // 第二个节点
        firstNode.next = swapPairs2(secondNod.next);   // 递归 交换两个节点指针 将交换后的链表给到 第一个节点的next
        secondNod.next = firstNode;   // 将第二个节点的next 设置为第一个节点
        return secondNod;  // 交换后 返回第二个节点
    }

    /**
     * 141. 环形链表
     *
     * 给定一个链表，判断链表中是否有环。
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
     */
    public boolean hasCycle(ListNode head) {
        // 方式1：hashSet存储<ListNode>
        // 方式2：快慢指针（龟兔赛跑）:两个指针同时跑，一个每次跑一步，一个每次跑两步，当他们相遇时，说明有环，当有next为null时，说明无环
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode faster = head.next;

        while (faster != null && faster.next != null){
            if (faster == slow) {
                return true;
            }
            faster = faster.next.next;
            slow = slow.next;
        }
        return false;
    }

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
        // testReverseList1();
        // testReverseList2();
        // testswapPairs1();
        // testswapPairs2();

        testMyQueue();
    }

    // test reverseList

    public static ListNode buildListNode() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        return head;
    }

    public static void testReverseList1() {
        ListNode listNode = reverseList1(buildListNode());
        System.out.println(GsonUtils.gson.toJson(listNode));
    }
    public static void testReverseList2() {
        ListNode listNode = reverseList2(buildListNode());
        System.out.println(GsonUtils.gson.toJson(listNode));
    }

    public static void testswapPairs1() {
        ListNode listNode = swapPairs1(buildListNode());
        System.out.println(GsonUtils.gson.toJson(listNode));
    } 
    public static void testswapPairs2() {
        ListNode listNode = swapPairs2(buildListNode());
        System.out.println(GsonUtils.gson.toJson(listNode));
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
 * 伪链表
 */
class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
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