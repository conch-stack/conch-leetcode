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
     * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
     * 示例:
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     * <p>
     * 限制：
     * 0 <= 节点个数 <= 5000
     */
    public static ListNode reverseList(ListNode head) {

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

    public static void main(String[] args) {
        testReverseList();

    }

    // test reverseList
    
    public static void testReverseList() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        ListNode listNode = reverseList(head);
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

