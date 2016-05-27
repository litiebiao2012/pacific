package com.pacific.common.utils;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by panwang.chengpw on 6/29/15.
 */
public class CryptoUtil {

    private static Logger logger          = LoggerFactory.getLogger(CryptoUtil.class);

    private static String DEFAULT_CHARSET = "UTF-8";
    private static String AES = "AES";
    private static String SECURE_TYPE = "SHA1PRNG";

    public static String encryptAES(String content, String encryptKey) {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance(AES);
            SecureRandom secureRandom = SecureRandom.getInstance(SECURE_TYPE);
            secureRandom.setSeed(encryptKey.getBytes(DEFAULT_CHARSET));
            keyGen.init(128, secureRandom);

            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyGen.generateKey().getEncoded(), AES));
            byte[] data = cipher.doFinal(content.getBytes(DEFAULT_CHARSET));

            String result = new String(Base64.encodeBase64(data), DEFAULT_CHARSET);
            return result;
        } catch (Exception e) {
            logger.error("AES encrypt error", e);
            throw new RuntimeException("AES encrypt error", e);
        }
    }

    public static String decryptAES(String content, String decryptKey) {
        try {
            byte[] encryptBytes = Base64.decodeBase64(content.getBytes(DEFAULT_CHARSET));
            KeyGenerator keyGen = KeyGenerator.getInstance(AES);
            SecureRandom secureRandom = SecureRandom.getInstance(SECURE_TYPE);
            secureRandom.setSeed(decryptKey.getBytes(DEFAULT_CHARSET));
            keyGen.init(128, secureRandom);

            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyGen.generateKey().getEncoded(), AES));
            byte[] data = cipher.doFinal(encryptBytes);
            String result = new String(data, DEFAULT_CHARSET);
            return result;
        } catch (Exception e) {
            logger.error("AES decrypt error", e);
            throw new RuntimeException("AES decrypt error", e);
        }
    }


    public static void main(String[] args) {
        String content = "你好hello";
        String key = "242423423434(&(^*#*$(";

        String en = encryptAES(content, key);
        System.out.println(en);
        String de = decryptAES(en, key);
        System.out.println(de);
    }

}
