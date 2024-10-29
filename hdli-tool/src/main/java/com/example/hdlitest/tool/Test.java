package com.example.hdlitest.tool;

import com.alibaba.fastjson.JSON;
import org.springframework.util.StopWatch;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author luyi
 * @date 2024/10/15 14:02
 */
public class Test {


    /**
     * 加密
     * @param data
     * @param key 要求key的长度为 16、24、32
     * @return
     * @throws Exception
     */
    public static String encrypt(String data, String key) throws Exception {
        Key secretKey = generateKey(key);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    /**
     * 解密
     * @param encryptedData
     * @param key  要求key的长度为 16、24、32
     * @return
     * @throws Exception
     */
    public static String decrypt(String encryptedData, String key) throws Exception {
        Key secretKey = generateKey(key);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decryptedData);
    }

    private static Key generateKey(String key) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();
        return new SecretKeySpec(key.getBytes(), "AES");
    }


    public static Date getExpireTime(Date date){
        date.setHours(23);
        date.setMinutes(59);
        date.setSeconds(59);
        return date;
    }


    public static void main(String[] args) {
        String url = "jdbc:mysql://rm-2zehd26c7rq92mknt2o.mysql.rds.aliyuncs.com:3306/test";
        String test = getConnectionUrl(url, "test");
        System.out.println(test);
    }

    private static String getConnectionUrl(String url,String schema){
        String[] split = url.split("/");
        if (split.length <= 3){
            if (url.endsWith("/")){
                return url+schema;
            }
            return url+"/"+schema;
        }
        return url;
    }
}
