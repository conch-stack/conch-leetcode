package ltd.beihu.leetcode.interview;

import ltd.beihu.leetcode.utils.GsonUtils;

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



    // main

    public static void main(String[] args) {
        // testReverseList1();
        // testReverseList2();
        testswapPairs1();
        // testswapPairs2();
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

