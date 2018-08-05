package com.zenghm.config.center;

//import org.junit.Test;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;

//import static org.junit.Assert.assertArrayEquals;
//import static org.junit.Assert.assertEquals;

/**
 * @author Airlen
 * @date 2018/08/04
 * Class name:SafeUtil
 * Description:加密与解密工具
 */
public class SafeUtil {
    private static final String KEY="12:!1&=(&*#qDDE~!12@$48=1DE;qw2=3D2*&*&(&@)xxq{|=;}nka9+P+S{?>S}{|}{HIW@:K@)#__JRR$KE):LS";
    /**
     * BASE64解码
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decodeBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    /**
     * BASE64编码
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encodeBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    /**
     * MD5加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encryptMD5(byte[] data) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance(KEY);
        md5.update(data);
        return md5.digest();
    }

    /**
     * SHA加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encryptSHA(byte[] data) throws Exception {
        MessageDigest sha = MessageDigest.getInstance(KEY);
        sha.update(data);
        return sha.digest();

    }


    /**
     * 初始化HMAC密钥
     *
     * @return
     * @throws Exception
     */
    public static String initMacKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY);
        SecretKey secretKey = keyGenerator.generateKey();
        return encodeBASE64(secretKey.getEncoded());
    }

    /**
     * HMAC加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptHMAC(byte[] data, String key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(decodeBASE64(key), KEY);
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        return mac.doFinal(data);

    }

    /*public void test() throws Exception {
        String inputStr = "简单加密";
        System.err.println("原文:\n" + inputStr);

        byte[] inputData = inputStr.getBytes();
        String code = encodeBASE64(inputData);

        System.err.println("BASE64加密后:\n" + code);

        byte[] output = decodeBASE64(code);

        String outputStr = new String(output);

        System.err.println("BASE64解密后:\n" + outputStr);

        // 验证BASE64加密解密一致性
        assertEquals(inputStr, outputStr);

        // 验证MD5对于同一内容加密是否一致
        assertArrayEquals(encryptMD5(inputData),encryptMD5(inputData));

        // 验证SHA对于同一内容加密是否一致
        assertArrayEquals(encryptSHA(inputData),encryptSHA(inputData));

        String key = initMacKey();
        System.err.println("Mac密钥:\n" + key);

        // 验证HMAC对于同一内容，同一密钥加密是否一致
        assertArrayEquals(encryptHMAC(inputData, key), encryptHMAC(inputData, key));

        BigInteger md5 = new BigInteger(encryptMD5(inputData));
        System.err.println("MD5:\n" + md5.toString(16));

        BigInteger sha = new BigInteger(encryptSHA(inputData));
        System.err.println("SHA:\n" + sha.toString(32));

        BigInteger mac = new BigInteger(encryptHMAC(inputData, inputStr));
        System.err.println("HMAC:\n" + mac.toString(16));
    }*/


}
