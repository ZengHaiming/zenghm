package com.zenghm.algorithm;

import java.util.*;

/**
 * @author Airlen
 * @date 2021/3/15
 * @description 括号正确性校验
 */
public class Brackets {
    class Node {
        /**
         * 字符
         */
        char ch;
        /**
         * 位置
         */
        int index;

        Node(char c, int index) {
            this.ch = c;
            this.index = index;
        }
    }

    /**
     * @param s string字符串
     * @return int整型
     */
    public int longestValidParentheses(String s) {
        // write code here
        if (s == null || s.length() == 0) {
            return 0;
        }
        Stack<Node> stack = new Stack<Node>();
        for (int i = 0; i < s.length(); i++) {
            if ('(' == s.charAt(i) || stack.empty() || ')' == stack.peek().ch) {
                Node node = new Node(s.charAt(i), i);
                stack.push(node);
            } else {
                stack.pop();
            }
        }
        if (stack.size() == 0) {
            return s.length();
        } else if (stack.size() == 1) {
            Node node = stack.peek();
            return node.index > s.length() - node.index - 1 ? node.index : s.length() - node.index - 1;
        } else {
            Node[] nodes = stack.toArray(new Node[stack.size()]);
            int count = 0;
            for (int i = 0; i < nodes.length - 1; i++) {
                int temp = nodes[i + 1].index - nodes[i].index - 1;
                if (temp > count) {
                    count = temp;
                }
            }
            //计算第一个元素到第一个字符的长度
            int start = nodes[0].index;
            int end = s.length() - nodes[nodes.length - 1].index - 1;
            if (start > count) {
                count = start;
            }
            if (end > count) {
                count = end;
            }
            return count;
        }
    }

    public static void main(String[] args) {
        Brackets brackets = new Brackets();
        int count = brackets.longestValidParentheses(")())");
        System.out.println(count);
    }
}
