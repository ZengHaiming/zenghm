package com.zenghm.algorithm;

import java.util.ArrayList;

/**
 * @author Airlen
 * @date 2021/2/27
 * @description 二叉树所有路径之和
 */

public class SumOfBinaryTreePaths {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
    }

    /**
     * @param root TreeNode类
     * @return int整型
     */
    public int sumNumbers(TreeNode root) {
        // write code here
        ArrayList<Integer> number = new ArrayList<Integer>();
        if (root != null) {
            groupNumber(root, root.val, number);
        }
        Integer sum = 0;
        for (Integer num : number) {
            sum = sum + num;
        }
        return sum;
    }

    public void groupNumber(TreeNode node, int val, ArrayList number) {
        if (node.left == null && node.right == null) {
            number.add(val);
        } else {
            if (node.left != null) {
                int crrentVal = val * 10 + node.left.val;
                groupNumber(node.left, crrentVal, number);
            }
            if (node.right != null) {
                int crrentVal = val * 10 + node.right.val;
                groupNumber(node.right, crrentVal, number);
            }
        }
    }
}
