package com.zenghm.algorithm;

import java.util.*;

/**
 * @author Airlen
 * @date 2021/3/17
 * @description xxx
 * // 字符串的排列
 * // 2. 输入一个字符串，打印出该字符串中字符的所有排列。
 * // 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 *
 * // 示例:
 * // 输入：s = "abc"
 * // 输出：["abc","acb","bac","bca","cab","cba"]
 *
 * // 限制：
 * // 1 <= s 的长度 <= 8
 * 排列组合算法
 */
public class CharSort {

    public Set<StringBuffer> toCharSort(String s){
        Set<StringBuffer> sort = new LinkedHashSet<>();
        if(s==null||s.length()==0){
            return sort;
        }
        sort.add(new StringBuffer(s));
        int strLength = s.length();
        for (int i=0;i<strLength-1;i++){
            group(i,strLength-1,sort);
        }
        return sort;
    }
    void group(int i,int max,Set<StringBuffer> sort){
        Set<StringBuffer> newBuffer = new LinkedHashSet<>();
        for (StringBuffer s:sort){
            char temp = s.charAt(i);
            for (int rep = i+1;rep<=max;rep++){
                StringBuffer buffer = new StringBuffer(s);
                char temp_rep = buffer.charAt(rep);
                buffer.setCharAt(i,temp_rep);
                buffer.setCharAt(rep,temp);
                newBuffer.add(buffer);
            }
        }
        sort.addAll(newBuffer);
    }

    public static void main(String[] args) {
        CharSort charSort = new CharSort();
        Set<StringBuffer> sort = charSort.toCharSort("abcd");
        for (StringBuffer stringBuffer:sort){
            System.out.println(stringBuffer);
        }
    }
}
