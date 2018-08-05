package com.zenghm.config.center;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
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

    //<editor-fold desc="constant var">
    /**
     * 服务器标识
     */
    private static final String SERVER_SIGN = "server";
    /**
     * 客户端标识
     */
    private static final String CLIENT_SIGN = "client";
    /**
     * 参数分割符
     */
    private static final String PARAM_SEPARATOR = ",";
    /**
     * 参数分层符
     */
    private static final String PARAM_HIERARCHY = ".";

    /**
     * 节点分层符
     */
    private static final String NODE_HIERARCHY="/";

    /**
     * 读取配置文件类型
     */
    private static final String READ_FILE_TYPE = ".properties";
    /**
     * 跟配置文件名称
     */
    private static final String ROOT_CONFIG = "root-config";
    /**
     * 标记当前服务是当作客户端还是服务器
     */
    private static final String SIGN_KEY_NAME = "sign";
    /**
     * zookeeper 地址配置键值名称
     */
    private static final String ZOOKEEPER_ADDRESS_KEY_NAME = "zookeeper.address";
    private static final String ZOOKEEPER_TIMEOUT_KEY_NAME = "zookeeper.timeout";
    private static final String CURRENT_NODE_KEY_NAME = "applicationName";
    /**
     * 配置信息根节点名称
     */
    private static final String CONFIG_ROOT_NODE_NAME="ConfigCenterManager";
    //</editor-fold>

    //<editor-fold desc="控制变量">
    /**
     * 提供服务标识
     */
    private static String sign;
    /**
     * 存放基本配置信息
     */
    private static Properties rootConfig;
    /**
     * 运用程序配置信息
     */
    private static Map<String, String> applicationConfig;

    /**
     * 均衡异常参数采集
     */
    private static Map<String, String> equilibriaErrorParams;
    /**
     * 配置信息存放map
     */
    private static Map<String, Properties> map;

    /**
     * 连接zookeeper
     */
    private static ZkClient zkClient;
    //</editor-fold>

    static {
        initRootConfig();
        configInfoUpLoadToZookeeper();
        loadApplicationConfigFromZookeeper();
        startMonitor();
    }


    //<editor-fold desc="提供外部接口">
    /**
     * 获取参数
     *
     * @param keyName 参数键值名称
     * @return
     */
    public static String getConfig(String keyName) {
        if (checkConfigIsLoadSuccess() && checkRequestParameterIsExist(keyName)) {
            return applicationConfig.get(keyName);
        }
        return "";
    }

    /**
     * 获取参数 , 如果传入的租户没有个性配置则 返回 标准配置
     *
     * @param keyName 参数键值名称
     * @param groupId 租户id
     * @return
     */
    public static String getConfig(String keyName, String groupId) {
        String gKeyName = keyName + PARAM_HIERARCHY + groupId;
        String gParamValue = getConfig(gKeyName);
        if (gParamValue != null && !"".equals(gParamValue)) return gParamValue;
        return getConfig(keyName);
    }

    /**
     * 获取参数 ,当配置多个参数值时 ，支持随机返回 ，以实现均衡
     *
     * @param keyName 参数键值名称
     * @return
     */
    public static String getConfigForEquilibria(String keyName) {
        String paramValue = getConfig(keyName);
        return equilibria(keyName, paramValue);
    }

    /**
     * 获取参数 ,当配置多个参数值时 ，支持随机返回 ，以实现均衡
     * 获取参数 , 如果传入的租户没有个性配置则 返回 标准配置
     *
     * @param keyName 参数键值名称
     * @param groupId 租户id
     * @return
     */
    public static String getConfigForEquilibria(String keyName, String groupId) {
        String paramValue = getConfig(keyName, groupId);
        return equilibria(keyName, paramValue);
    }

    /**
     * 均衡异常参数采集
     *
     * @param keyName    参数键值名称
     * @param paramValue 参数值
     */
    public synchronized static void equilibriaExceptionParamValue(String keyName, String paramValue) {
        if (equilibriaErrorParams == null) {
            equilibriaErrorParams = new HashMap<>();
        }
        if (equilibriaErrorParams.containsKey(keyName)) {
            equilibriaErrorParams.put(keyName,equilibriaErrorParams.get(keyName)+PARAM_SEPARATOR+paramValue);
        } else {
            equilibriaErrorParams.put(keyName, paramValue);
        }
    }
    //</editor-fold>


    /**
     * 均衡
     *
     * @param paramValue 参数值
     * @return
     */
    private static String equilibria(String keyName, String paramValue) {
        if (paramValue != null && !paramValue.equals("")) {
            String[] paramValues = paramValue.split(PARAM_SEPARATOR);
            Random rand = new Random();
            String value;
            int equilibriaCount = 0;
            do {
                int paramIndex = rand.nextInt(paramValues.length);
                value = paramValues[paramIndex];
                equilibriaCount ++ ;
            }while (checkParamIsEquilibriaException(keyName,value)&&equilibriaCount<paramValues.length*2);
            return value;
        } else {
            return "";
        }
    }

    /**
     * 判断参数值是否均衡异常
     *
     * @param keyName    参数名称
     * @param paramValue 参数值
     * @return
     */
    private static boolean checkParamIsEquilibriaException(String keyName, String paramValue) {
        if(equilibriaErrorParams==null||equilibriaErrorParams.isEmpty()
                ||!equilibriaErrorParams.containsKey(keyName)) return false;
        String errorParamValue  = equilibriaErrorParams.get(keyName);
        return errorParamValue.contains(paramValue);
    }

    /**
     * 检查配置文件是否加载成功
     *
     * @return
     */
    private static boolean checkConfigIsLoadSuccess() {
        if (applicationConfig == null || applicationConfig.isEmpty()) {
            logger.error("Configuration information loading failure !");
            return false;
        }
        return true;
    }

    /**
     * 检测当前请求参数是否存在
     *
     * @param keyName 参数键值名称
     * @return
     */
    private static boolean checkRequestParameterIsExist(String keyName) {
        if (applicationConfig != null && !applicationConfig.isEmpty() && applicationConfig.containsKey(keyName)) {
            return true;
        } else {
            logger.error("The current request parameters do not exist !");
            return false;
        }
    }


    /**
     * 开启zookeeper 监听
     */
    private static void startMonitor() {
        if(rootConfig==null||rootConfig.isEmpty()||!CLIENT_SIGN.equals(sign)) return;
        createZkClientObj();
        if(null==zkClient) return;
        String currentClientNode = NODE_HIERARCHY+CONFIG_ROOT_NODE_NAME+rootConfig.get(CURRENT_NODE_KEY_NAME);
        zkClient.subscribeDataChanges(currentClientNode,new IZkDataListener(){
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                if(o instanceof Properties){
                    Properties changedConfig = (Properties) o ;
                    if(applicationConfig!=null){
                        applicationConfig.clear();
                    }else {
                        applicationConfig = new ConcurrentHashMap<>();
                    }
                    for (Object keyName:changedConfig.keySet()){
                        applicationConfig.put(keyName.toString(),changedConfig.getProperty(keyName.toString()));
                    }
                }
            }
            @Override
            public void handleDataDeleted(String s) throws Exception {
                // not to do
            }
        });


    }

    /**
     * 如果是客户端
     * 从 Zookeeper 中加载 运用程序配置
     */
    private static void loadApplicationConfigFromZookeeper() {
        if(rootConfig==null||rootConfig.isEmpty()||!CLIENT_SIGN.equals(sign)) return;
        createZkClientObj();
        if(null==zkClient) return;
        String currentClientNode = NODE_HIERARCHY+CONFIG_ROOT_NODE_NAME+rootConfig.get(CURRENT_NODE_KEY_NAME);
        Properties readConfig = null;
        try {
            if(zkClient.exists(currentClientNode)){
                readConfig = zkClient.readData(currentClientNode);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        if(readConfig==null||readConfig.isEmpty()) return;
        if(applicationConfig!=null){
            applicationConfig.clear();
        }else {
            applicationConfig = new ConcurrentHashMap<>();
        }
        for (Object keyName:readConfig.keySet()){
            applicationConfig.put(keyName.toString(),readConfig.getProperty(keyName.toString()));
        }
        logger.info("Read the node configuration information to complete  ! ");
    }



    /**
     * 如果是服务器
     * 配置信息上传至 Zookeeper
     */
    private static void configInfoUpLoadToZookeeper() {
        if(rootConfig==null||rootConfig.isEmpty()||!SERVER_SIGN.equals(sign)) return;
        map = loadConfigInfoFromFile();
        if(map==null||map.isEmpty()){
            logger.warn("No configuration file information exists !");
            return;
        }
        createZkClientObj();
        if(null==zkClient) return;
        String rootNode = NODE_HIERARCHY+CONFIG_ROOT_NODE_NAME;
        for (String configFileName:map.keySet()){
            String nodeName = rootNode+NODE_HIERARCHY+configFileName;
            try {
                if(!zkClient.exists(nodeName)){
                    if(!zkClient.exists(rootNode)){
                        zkClient.create(rootNode,System.currentTimeMillis(),CreateMode.PERSISTENT);
                    }
                    zkClient.create(nodeName,map.get(configFileName), CreateMode.PERSISTENT);
                }else {
                    zkClient.writeData(nodeName,map.get(configFileName));
                }
            } catch (Exception e) {
                logger.error(e.getMessage(),e);
            }
        }
    }

    /**
     * 创建 ZkClient 对象
     */
    private static void createZkClientObj(){
        if(zkClient==null){
            synchronized (ConfigManager.class){
                if(zkClient==null){
                    try {
                        Integer timeout = Integer.valueOf(rootConfig.getProperty(ZOOKEEPER_TIMEOUT_KEY_NAME));
                        zkClient = new ZkClient(rootConfig.getProperty(ZOOKEEPER_ADDRESS_KEY_NAME),timeout);
                    } catch (Exception e) {
                        logger.error(e.getMessage(),e);
                    }
                }
            }
        }
    }


    private static void initRootConfig() {
        /*
        1、加载配置文件
         */
        String rootConfigFileName = ROOT_CONFIG+READ_FILE_TYPE;
        Map<String,Properties> tmpMap = loadConfigInfoFromFile(new String[]{rootConfigFileName});
        /*
        2、查找跟配置文件
         */
        if (tmpMap != null && !tmpMap.isEmpty()) {
            for (String fileName : tmpMap.keySet()) {
                if (ROOT_CONFIG.equals(fileName)) {
                    rootConfig = tmpMap.get(fileName);
                }
            }
        }
        if (rootConfig == null || rootConfig.isEmpty()) {
            logger.warn("No configuration of root configuration information !");
        }
        /*
        3、初始化配置数据
         */
        if (rootConfig != null && !rootConfig.isEmpty()) {
            sign = rootConfig.getProperty(SIGN_KEY_NAME);
            if (!SERVER_SIGN.equals(sign) && !CLIENT_SIGN.equals(sign)) {
                logger.warn("Service identity configuration error !");
            }
        }
        if (sign == null || sign.equals("")) {
            logger.warn("No configuration of service identities !");
        }
    }

    /**
     * 从文件中加载配置信息
     * 无参数，默认在当前classes 文件夹下加载所有配置文件信息
     *
     * @return
     */
    public static Map<String, Properties> loadConfigInfoFromFile() {
        String[] allFileUrl = getClassPathAllFile();
        return loadConfigInfoFromFile(allFileUrl);
    }

    /**
     * 从文件中加载配置信息
     *
     * @param allFileUrl 指定文件名称加载配置信息
     * @return
     */
    public static Map<String, Properties> loadConfigInfoFromFile(String[] allFileUrl) {
        Map<String, Properties> allConfigInfo = new ConcurrentHashMap<>();
        /*
          读取所有 properties 文件
         */
        if (allFileUrl != null && allFileUrl.length > 0) {
            for (String fileUrl : allFileUrl) {
                if (!fileUrl.endsWith(READ_FILE_TYPE)) continue;
                Properties properties = new Properties();
                try (InputStream inputStream = ClassLoader.getSystemResourceAsStream(fileUrl)) {
                    properties.load(inputStream);
                    String key = fileUrl.substring(0, fileUrl.length() - READ_FILE_TYPE.length());
                    allConfigInfo.put(key, properties);
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        return allConfigInfo;
    }

    /**
     * 获取classes路径下所有的文件夹及文件名称
     *
     * @return 文件夹及文件名称
     */
    private static String[] getClassPathAllFile() {
        /*
          获取classes路径
         */
        URL classesPath = ConfigManager.class.getResource("/");
        File rootFile = new File(classesPath.getPath());
        /*
          获取classes路径下的所有文件名称
         */
        return rootFile.list();
    }


    public static void init(){
        logger.info("Start loading configuration information !");
    }
    /*public static void main(String[] args) {
        logger.info("Start loading configuration information !");
    }*/

}
