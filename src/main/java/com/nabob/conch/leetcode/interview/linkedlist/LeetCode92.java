package com.nabob.conch.leetcode.interview.linkedlist;

import com.nabob.conch.leetcode.core.ListNode;
import com.nabob.conch.leetcode.utils.GsonUtils;

/**
 * 92. 反转链表 II
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 *
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 *
 * @author Adam
 * @date 2020/9/16
 */
public class LeetCode92 {

    public static ListNode reverseBetween(ListNode head, int m, int n) {
        int index = 1;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode pre = dummy;
        ListNode cur = head;

        ListNode start = null;

        ListNode reverse = null;

        while(index <= n) {
            if (index < m) {
                pre = pre.next;
                cur = cur.next;
            } else {
                // 保存开始反转位置的指针
                if (start == null) {
                    start = cur;
                }
                // 反转
                ListNode temp = cur.next;
                cur.next = reverse;
                reverse = cur;
                cur = temp;
            }
            index++;
        }
        // 拼接上 反转的部分
        pre.next = reverse;
        // 拼接上 剩下的部分
        start.next = cur;

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode listNode1 = reverseBetween(buildListNode(), 1, 7);
        System.out.println(GsonUtils.gson.toJson(listNode1));
    }

    public static ListNode buildListNode() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        return head;
    }
}
