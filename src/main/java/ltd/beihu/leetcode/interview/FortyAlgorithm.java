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
     * 反转链表
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

        ListNode current = head;
        ListNode pre = null;

        while (current != null) {
            ListNode temp = current.next;
            current.next = pre;
            pre = current;
            current = temp;
        }
        return pre;
    }

    /**
     * 两两交换链表中的节点
     *
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     * 示例:
     * 给定 1->2->3->4, 你应该返回 2->1->4->3.
     */
    public static ListNode swapPairs1(ListNode head) {
        // todo 迭代
        return null;
    }

    public static ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        head.next = swapPairs2(next.next);
        next.next = head;
        return next;
    }



    // main

    public static void main(String[] args) {
        // testReverseList1();
        testswapPairs2();
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

