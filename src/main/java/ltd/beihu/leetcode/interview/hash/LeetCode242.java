package ltd.beihu.leetcode.interview.hash;

import java.util.Arrays;

/**
 * 242. 有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * 示例 1:
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 *
 * 示例 2:
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 说明:
 * 你可以假设字符串只包含小写字母。
 *
 * 进阶:
 * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 *
 * 解决方案：
 *      方案一：
 *          排序：O(nlogn)
 *      方案二：
 *          Map计数：{letter, count}
 *          复杂度：O(n)
 *
 * @author Adam
 * @date 2020/10/13
 */
public class LeetCode242 {

    /**
     * 排序法
     */
    public boolean sort(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        char[] s1 = s.toCharArray();
        char[] t1 = t.toCharArray();
        Arrays.sort(s1);
        Arrays.sort(t1);

        return Arrays.equals(s1, t1);
    }

    /**
     * hash法
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] rs = new int[26];

        for (int i = 0; i < s.length(); i++) {
            rs[s.charAt(i) - 'a']++;
            rs[t.charAt(i) - 'a']--;
        }

        for (int r : rs) {
            if (r != 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        LeetCode242 leetCode242 = new LeetCode242();

        boolean anagram = leetCode242.isAnagram("anagram", "nagaram");
        System.out.println(anagram);
    }
}
