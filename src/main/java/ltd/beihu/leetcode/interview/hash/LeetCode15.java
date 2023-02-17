package ltd.beihu.leetcode.interview.hash;

import ltd.beihu.leetcode.utils.GsonUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 15. 三数之和
 * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 示例：
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * <p>
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 *
 * @author Adam
 * @date 2020/10/13
 */
public class LeetCode15 {

    public static List<List<Integer>> threeSum(int[] nums) {
        // 小优化
        if (nums == null || nums.length < 3) {
            return null;
        }

        List<List<Integer>> rs = new ArrayList<>();

        int len = nums.length;

        // 1. 排序
        Arrays.sort(nums);

        // 2. 遍历
        for (int i = 0; i < len; i++) {

            // 跳过重复
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int j = i + 1;
            int k = len - 1;

            while (j < k) {

                int sum = nums[i] + nums[j] + nums[k];

                if (sum == 0) {
                    rs.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    // 优化代码
                    // while(j < k && nums[j] == nums[++j]);
                    // while(j < k && nums[k] == nums[--k]);
                    while (j < k && nums[j] == nums[j + 1]) {
                        j++;
                    }
                    while (j < k && nums[k] == nums[k - 1]) {
                        k--;
                    }
                    j++;
                    k--;
                } else if (sum > 0) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return rs;
    }

    public static List<List<Integer>> threeSum1(int[] nums) {
        // 小优化
        if (nums == null || nums.length < 3) {
            return null;
        }

        int len = nums.length;

        // 1. 排序
        Arrays.sort(nums);

        Set<List<Integer>> tempRes = new HashSet<>();
        // 2. 遍历
        for (int i = 0; i < len - 2; i++) {

            // 跳过重复
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int j = i + 1;
            int k = len - 1;

            while (j < k) {

                int sum = nums[i] + nums[j] + nums[k];

                if (sum == 0) {
                    tempRes.add(Arrays.asList(nums[i], nums[j], nums[k]));
                }

                // 精髓
                if (sum > 0) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return new ArrayList<>(tempRes);
    }

    public static void main(String[] args) {
//        List<List<Integer>> lists = threeSum1(new int[]{-1, 0, 1, 2, -1, -4, -2, 1});
//        List<List<Integer>> lists = threeSum1(new int[]{0, 0, 0, 0});
//        List<List<Integer>> lists = threeSum1(new int[]{-2, 0, 0, 2, 2});
        List<List<Integer>> lists = threeSum1(new int[]{-5, 1,2,3,4});
        System.out.println(GsonUtils.gson.toJson(lists));
//
//        int len = 3;
//        for (int i = 0; i < len - 1; ++i) {
//            System.out.println(i);
//        }
//        System.out.println("-----");
//        for (int i = 0; i < len; i++) {
//            System.out.println(i);
//        }
//        System.out.println("-----");
//        int i1 = 1;
//        int i2 = 1;
//        int a = i1++;
//        int b = ++i2;
//        System.out.println(a);
//        System.out.println(i1);
//        System.out.println(b);
//        System.out.println(i2);
    }
}
