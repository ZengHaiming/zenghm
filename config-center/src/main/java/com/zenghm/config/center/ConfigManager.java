package com.zenghm.config.center;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Airlen
 * @date 2018/07/29
 * Class name:ConfigManager
 */
public class ConfigManager {
    /**
     * 参数分割符
     */
    private static  final  String paramSeparator =",";
    /**
     * 配置信息存放map
     */
    private ConcurrentHashMap map;

    /**
     * 从文件中加载配置信息
     * 无参数，默认在当前classes 文件夹下加载所有配置文件信息
     * @return
     */
    public static Map<String,String> loadConfigInFromFile(){
        return null;
    }

    /**
     * 从文件中加载配置信息
     * @param fileNames 指定文件名称加载配置信息
     * @return
     */
    public static Map<String,String> loadConfigInFromFile(List<String> fileNames){
        return null;
    }


}
