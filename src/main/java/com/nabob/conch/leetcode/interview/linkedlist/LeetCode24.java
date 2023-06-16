package com.nabob.conch.leetcode.interview.linkedlist;

import com.nabob.conch.leetcode.core.ListNode;
import com.nabob.conch.leetcode.utils.GsonUtils;

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
 *
 * @author Adam
 * @date 2020/9/15
 */
public class LeetCode24 {

    /**
     * 循环
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
            // preNode 只是用来记录处理节点的前一个node
            preNode = firstNode;
            head = firstNode.next;
        }

        return dummy.next;
    }

    /**
     * 递归
     */
    public static ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode first = head;
        ListNode second = head.next;

        ListNode rs = swapPairs2(second.next);

        second.next = first;
        first.next = rs;

        return second;
    }

    /**
     * Test Main
     */
    public static void main(String[] args) {
        ListNode listNode = swapPairs1(buildListNode());
        System.out.println(GsonUtils.gson.toJson(listNode));

        ListNode listNode1 = swapPairs2(buildListNode());
        System.out.println(GsonUtils.gson.toJson(listNode1));
    }

    public static ListNode buildListNode() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        return head;
    }
}
