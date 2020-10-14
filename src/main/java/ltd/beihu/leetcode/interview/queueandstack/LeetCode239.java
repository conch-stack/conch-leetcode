package ltd.beihu.leetcode.interview.queueandstack;

import ltd.beihu.leetcode.utils.GsonUtils;

import java.util.ArrayDeque;

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
 *
 * @author Adam
 * @date 2020/10/13
 */
public class LeetCode239 {

    private ArrayDeque<Integer> deq = new ArrayDeque<>();
    int[] nums;

    /**
     * 清理队列
     *      1. 删除非窗口的元素
     *      2. 删除队列中所有小于当前数据的索引元素
     *
     * @param i 当前索引位置
     * @param k 窗口大小
     */
    public void clean_deque(int i, int k) {
        // 1
        if (!deq.isEmpty() && deq.getFirst() == (i - k)) {
            deq.removeFirst();
        }

        // 2
        while (!deq.isEmpty() && nums[i] > nums[deq.getLast()]) {
            deq.removeLast();
        }

    }

    /**
     * 双向队列
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        // 小优化
        int n = nums.length;
        if (n * k == 0) {
            return new int[0];
        }
        if (k == 1) {
            return nums;
        }

        // 初始化滑动窗口
        this.nums = nums;
        // 初始化时，前k个元素是没有真正进行clean_deque操作的获取最大值的
        int maxIdx = 0;
        for (int i = 0; i < k; i++) {
            clean_deque(i, k);
            deq.addLast(i);
            if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
            }
        }

        int[] output = new int[n - k + 1];
        output[0] = nums[maxIdx];

        // 构建输出
        for (int i = k; i < n; i++) {
            clean_deque(i, k);
            deq.addLast(i);
            output[i - k + 1] = nums[deq.getFirst()];
        }

        return output;
    }

    public static void main(String[] args) {

        LeetCode239 leetCode239 = new LeetCode239();
//        int[] testData = {1,3,-1,-3,5,3,6,7};
        int[] testData = {9, 11};

        int[] result = leetCode239.maxSlidingWindow(testData, 2);
        System.out.println(GsonUtils.gson.toJson(result));
    }

}
