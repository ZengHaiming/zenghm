package com.zenghm.algorithm;

import java.util.ArrayList;

/**
 * @author Airlen
 * @date 2021/3/7
 * @description 十进制数字转换成任意进制数据
 */
public class BaseConvert {
    private static char[] numberLabel = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public String convert(int num, int base) {
        ArrayList<Integer> baseNumber = new ArrayList<>();
        while (num >= base) {
            baseNumber.add(num % base);
            num = num / base;
        }
        baseNumber.add(num);
        StringBuilder result = new StringBuilder(baseNumber.size());
        for (int index = baseNumber.size() - 1; index >= 0; index--) {
            result.append(numberLabel[baseNumber.get(index)]);
        }
        return result.toString();
    }

    public Integer convert(String numStr, int base) {
        int sum = 0;
        for (int index = 0; index < numStr.length(); index++) {
            sum = sum + (numStr.charAt(index) != numberLabel[0] ? (new Double(Math.pow(base, numStr.length() - index - 1))).intValue() * indexOf(numStr.charAt(index)) : 0);
        }
        return sum;
    }

    private int indexOf(char ch) {
        int index = 0;
        for (char c : numberLabel) {
            if (ch == c) {
                break;
            }
            index++;
        }
        return index;
    }

    public static void main(String[] args) {
        BaseConvert baseConvert = new BaseConvert();
        System.out.println(baseConvert.convert(baseConvert.convert(19, 19), 19));
    }
}
