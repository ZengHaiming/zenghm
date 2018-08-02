package com.zenghm.config.center;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Airlen
 * @date 2018/07/29
 * Class name:ConfigManager
 */
public class ConfigManager {
    private static Logger logger = LoggerFactory.getLogger(ConfigManager.class);
    /**
     * 参数分割符
     */
    private static final String PARAM_SEPARATOR = ",";
    /**
     * 读取配置文件类型
     */
    private static final String READ_FILE_TYPE = ".properties";
    /**
     * 配置信息存放map
     */
    private ConcurrentHashMap map;

    /**
     * 从文件中加载配置信息
     * 无参数，默认在当前classes 文件夹下加载所有配置文件信息
     *
     * @return
     */
    public static Map<String, String> loadConfigInFromFile() {
        return null;
    }

    /**
     * 从文件中加载配置信息
     *
     * @param fileNames 指定文件名称加载配置信息
     * @return
     */
    public static Map<String, String> loadConfigInFromFile(List<String> fileNames) {
        return null;
    }

    private static Map<String, Properties> getClassPathAllFile() {
        Map<String, Properties> allConfigInfo = new ConcurrentHashMap<String, Properties>();
        /*
          获取classes路径
         */
        URL classesPath = ConfigManager.class.getResource("/");
        File rootFile = new File(classesPath.getPath());
        /*
          获取classes路径下的所有文件名称
         */
        String[] allFileUrl = rootFile.list();
        /*
          读取所有 properties 文件
         */
        if (allFileUrl != null && allFileUrl.length > 0) {
            for (String fileUrl : allFileUrl) {
                Properties properties = new Properties();
                InputStream inputStream = ClassLoader.getSystemResourceAsStream(fileUrl);
                if (!fileUrl.endsWith(READ_FILE_TYPE)) continue;
                try {
                    properties.load(inputStream);
                    inputStream.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(),e);
                    continue;
                }
                String key = fileUrl.substring(0, fileUrl.length() - READ_FILE_TYPE.length());
                allConfigInfo.put(key, properties);
            }
        }
        return allConfigInfo;
    }

    public static void main(String[] args) {
        getClassPathAllFile();
    }


}
