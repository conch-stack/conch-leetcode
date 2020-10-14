package ltd.beihu.leetcode.interview.queueandstack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 20. 有效的括号
 * <p>
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * @author Adam
 * @date 2020/9/22
 */
public class LeetCode20 {

    /**
     * 压入栈
     */
    public static boolean isValid(String s) {
        Map<Character, Character> valid = new HashMap<>();
        valid.put(')', '(');
        valid.put('}', '{');
        valid.put(']', '[');
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char cell = s.charAt(i);

            // 需要验证
            if (valid.containsKey(cell)) {
                if (stack.isEmpty()) {
                    return false;
                }
                Character pop = stack.pop();
                if (!pop.equals(valid.get(cell))) {
                    return false;
                }
            } else {
                stack.push(cell);
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isValid("[[]]"));
    }

}
