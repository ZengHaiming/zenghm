package com.zenghm.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Create date:2018/06/12.
 * Created by: Airlen.
 * Class name:ResultUtil.
 * 结果处理工具类
 */
public class ResultUtil {
    //key constant
    public static String RESULT = "result";
    public static String INFO = "info";
    //value constant
    public static String ERROR = "0";
    public static String SUCCESS = "1";
    public static Map<String,Object> normalResult(){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put(RESULT,SUCCESS);
        return map;
    }
    public static Map<String,Object> errorResult(String info){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put(RESULT,ERROR);
        map.put(INFO,info);
        return map;
    }
    public static boolean isSuccess(Map<String,Object> map) {
        return map.containsKey(RESULT) && SUCCESS.equals(map.get(RESULT));
    }
}
