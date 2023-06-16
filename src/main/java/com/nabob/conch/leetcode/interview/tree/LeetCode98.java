package com.nabob.conch.leetcode.interview.tree;

import com.nabob.conch.leetcode.core.TreeNode;

/**
 * 98. 验证二叉搜索树
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * 假设一个二叉搜索树具有如下特征：
 *      节点的左子树只包含小于当前节点的数。
 *      节点的右子树只包含大于当前节点的数。
 *      所有左子树和右子树自身必须也是二叉搜索树。
 * 示例1:
 *
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 * 示例2:
 *
 * 输入:
 *     5
 *    / \
 *   1   4
 *     / \
 *    3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *     根节点的值为 5 ，但是其右子节点值为 4 。
 *
 * 解决方案：
 *      方案一：中序遍历，遍历后的数组是否为升序的序列，只需要保留前继节点 O(n)
 *      方案二：递归 左右节点
 *          复杂度：每个节点访问且只访问一次 O(n)
 *
 * @author Adam
 * @date 2020/10/14
 */
public class LeetCode98 {

    public boolean isValidBST(TreeNode root) {

        return false;
    }

    /**
     * 递归
     */
    public boolean helper1() {
        return false;
    }
}
