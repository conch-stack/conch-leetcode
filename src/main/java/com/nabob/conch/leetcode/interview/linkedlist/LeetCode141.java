package com.nabob.conch.leetcode.interview.linkedlist;

import com.nabob.conch.leetcode.core.ListNode;

/**
 * 141. 环形链表
 *
 * 给定一个链表，判断链表中是否有环。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 *
 * @author Adam
 * @date 2020/9/15
 */
public class LeetCode141 {

    // 方式1：hashSet存储<ListNode>
    // 方式2：快慢指针（龟兔赛跑）:两个指针同时跑，一个每次跑一步，一个每次跑两步，当他们相遇时，说明有环，当有next为null时，说明无环
    public static boolean hasCycle(ListNode head) {

        if (head == null || head.next == null) {
            return false;
        }

        ListNode fast = head.next;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            if (fast == slow) {
                return true;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(hasCycle(buildListNode()));
    }

    public static ListNode buildListNode() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        // 环
        head.next.next.next.next.next = head.next;
        return head;
    }
}
