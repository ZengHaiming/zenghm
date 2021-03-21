package com.zenghm.algorithm;

import java.math.BigInteger;

/**
 * @author Airlen
 * @date 2021/3/21
 * @description xxx
 */
public class PerAndComb {
    Integer perm(Integer n) {
        Integer s = 1;
        while (n > 1) {
            s *= n;
            n--;
        }
        return s;
    }

    /**
     * 存在bug   ,存在数据溢出情况
     * @param n
     * @param m
     * @return
     */
    Integer comb(Integer n, Integer m) {
        Long temp_m = Long.valueOf(m);
        Long temp_n = Long.valueOf(n);
        Long s = 1L;
        Long i = 1L;
        Long temp = 1L;
        while (i <= temp_m) {
            s = s * (temp_n - i + 1);
            temp *= i;
            i++;
        }
        s = s / temp;
        return s.intValue();
    }

    public static void main(String[] args) {
        PerAndComb perAndComb = new PerAndComb();
        Integer com = perAndComb.comb(27,17);
        Integer per = perAndComb.perm(5);
        System.out.println(com+" "+per);
    }
}
