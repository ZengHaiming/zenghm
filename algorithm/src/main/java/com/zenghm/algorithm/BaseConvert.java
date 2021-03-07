package com.zenghm.algorithm;

import java.util.ArrayList;

/**
 * @author Airlen
 * @date 2021/3/7
 * @description 十进制数字转换成任意进制数据
 */
public class BaseConvert {
    public String convert(int num, int base) {
        ArrayList<Integer> baseNumber = new ArrayList<>();
        while (true) {
            baseNumber.add(num % base);
            num = num / base;
            if (num < base) {
                break;
            }
        }
        baseNumber.add(num);
        StringBuilder result = new StringBuilder(baseNumber.size());
        for (int index = baseNumber.size() - 1; index >= 0; index--) {
            result.append(baseNumber.get(index));
        }
        return result.toString();
    }

    public static void main(String[] args) {
        BaseConvert baseConvert = new BaseConvert();
        System.out.println(baseConvert.convert(23, 16));
    }
}
