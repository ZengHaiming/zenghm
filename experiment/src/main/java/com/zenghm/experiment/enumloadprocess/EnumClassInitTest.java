package com.zenghm.experiment.enumloadprocess;

import java.util.Arrays;

/**
 * @author Airlen
 * @date 2021/3/25
 * @description 枚举类的初始化的过程测试
 * 测试结果：枚举值 > 静态变量 > 静态代码块
 */
public enum  EnumClassInitTest {
    A(1),
    B(2),
    C(3);
    EnumClassInitTest(Integer val){
        System.out.println("初始化："+val);
        this.value= val;
    }
    private Integer value;
    private static Inner inner = new Inner();
    static class Inner{
        Inner(){
            System.out.println("Inner初始化");
        }
    }
    static {
        System.out.println("static 代码块执行");
    }

    public static void main(String[] args) {
        System.out.println(EnumClassInitTest.class);
        Arrays.stream(EnumClassInitTest.values()).forEach(System.out::println);
        EnumClassInitTest a = EnumClassInitTest.valueOf("A");
        System.out.println(a);
    }
}
