package ltd.beihu.leetcode.interview.linkedlist;

import ltd.beihu.leetcode.core.ListNode;
import ltd.beihu.leetcode.utils.GsonUtils;

/**
 * 206. 反转链表
 * <p>
 * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 * 示例:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * <p>
 * 限制：
 * 0 <= 节点个数 <= 5000
 *
 * @author Adam
 * @date 2020/9/14
 */
public class LeetCode206 {

    /**
     * 循环
     */
    public static ListNode reverseList1(ListNode head) {
        if (head == null) {
            return null;
        }

        // 存储反转后的链表
        ListNode pre = null;
        ListNode current = head;

        while (current != null) {
            ListNode temp = current.next;
            current.next = pre;
            // 重置pre
            pre = current;
            current = temp;
        }
        return pre;
    }

    /**
     * 递归
     */
    public static ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode re = reverseList2(head.next);
        // 5的next 换成 4
        head.next.next = head;
        // 4的next 换成 null
        head.next = null;
        // 返回新的链表
        return re;
    }

    /**
     * Test Main
     */
    public static void main(String[] args) {
        ListNode listNode = reverseList1(buildListNode());
        System.out.println(GsonUtils.gson.toJson(listNode));

        ListNode listNode1 = reverseList2(buildListNode());
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
