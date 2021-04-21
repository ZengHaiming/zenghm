package com.zenghm.experiment.reflect;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Airlen
 * @date 2021/4/21
 * @description xxx
 */
public class MethodClass {
    public List<Integer> getNumbers(ENClass enClass ,List<Map<String,Object>> obj, List<Integer> list , List<ParamTestDto> paramTestDtoList) {
        System.out.println(list);
        System.out.println(paramTestDtoList);
        return new ArrayList<>();
    }
}
