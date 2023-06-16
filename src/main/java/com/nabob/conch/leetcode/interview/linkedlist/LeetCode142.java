package com.nabob.conch.leetcode.interview.linkedlist;

import com.nabob.conch.leetcode.core.ListNode;
import com.nabob.conch.leetcode.utils.GsonUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * 142. 环形链表 II
 * 给定一个链表，返回链表开始入环的第一个节点。如果链表无环，则返回null。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * 说明：不允许修改给定的链表。
 *
 * @author Adam
 * @date 2020/9/16
 */
public class LeetCode142 {

    /**
     * 方法 1：哈希表
     */
    public static ListNode detectCycle1(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        Set<ListNode> rs = new HashSet<>();
        while (head != null && head.next != null) {
            if (rs.contains(head)) {
                return head;
            } else {
                rs.add(head);
                head = head.next;
            }
        }
        return null;
    }

    /**
     * 方法 2：Floyd 算法  （弗洛伊德）
     */
    public static ListNode detectCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode fast = head.next;
        ListNode slow = head;

        ListNode start = head;

        while (fast != null && fast.next != null) {
            if (fast == slow) {
                return help(start, fast);
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return null;
    }

    public static ListNode help(ListNode start, ListNode end) {
        while (start != end) {
            start = start.next;
            end = end.next;
        }
        return start;
    }


    private static ListNode getIntersect(ListNode head) {
        ListNode tortoise = head;
        ListNode hare = head;

        // A fast pointer will either loop around a cycle and meet the slow
        // pointer or reach the `null` at the end of a non-cyclic list.
        while (hare != null && hare.next != null) {
            tortoise = tortoise.next;
            hare = hare.next.next;
            if (tortoise == hare) {
                return tortoise;
            }
        }

        return null;
    }

    public static ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }

        // If there is a cycle, the fast/slow pointers will intersect at some
        // node. Otherwise, there is no cycle, so we cannot find an e***ance to
        // a cycle.
        ListNode intersect = getIntersect(head);
        if (intersect == null) {
            return null;
        }

        // To find the e***ance to the cycle, we have two pointers traverse at
        // the same speed -- one from the front of the list, and the other from
        // the point of intersection.
        ListNode ptr1 = head;
        ListNode ptr2 = intersect;
        while (ptr1 != ptr2) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }

        return ptr1;
    }

    public static void main(String[] args) {
        ListNode listNode = detectCycle(buildListNode2());
        System.out.println(GsonUtils.gson.toJson(listNode));
    }

    public static ListNode buildListNode1() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        // 环
        head.next.next.next.next = head.next.next.next;
        return head;
    }

    // todo 这种场景龟兔赛跑方法失败
    public static ListNode buildListNode2() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        // 环
        head.next.next.next.next.next = head.next.next;
        return head;
    }
}
