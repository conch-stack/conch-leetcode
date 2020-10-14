package ltd.beihu.leetcode.interview.queueandstack;

import java.util.PriorityQueue;

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
 *
 * @author Adam
 * @date 2020/9/26
 */
public class LeetCode703 {

    /**
     * 关于 Java 的 PriorityQueue 优先级队列
     * 1 是线程不安全的队列
     * 2 存储使用数组实现
     * 3 利用比较器做优先级比较
     */
    private PriorityQueue<Integer> priorityQueue;
    private int limit;

    /**
     * 考察优先队列 小顶堆
     */
    public LeetCode703(int k, int[] nums) {
        priorityQueue = new PriorityQueue<>(k);
        limit = k;

        for (int i = 0; i < nums.length; i++) {
            add(nums[i]);
        }
    }

    public int add(int val) {
        if (priorityQueue.size() < limit) {
            priorityQueue.add(val);
        } else if (val > priorityQueue.peek()) {
            priorityQueue.poll();
            priorityQueue.add(val);
        }
        return priorityQueue.peek();
    }

    public static void main(String[] args) {
        int k = 3;
        int arr[] = {4,5,8,2};

        LeetCode703 obj = new LeetCode703(k, arr);
        System.out.println(obj.add(3));
        System.out.println(obj.add(5));
        System.out.println(obj.add(10));
        System.out.println(obj.add(9));
        System.out.println(obj.add(4));
    }
}
