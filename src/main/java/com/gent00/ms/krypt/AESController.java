package com.gent00.ms.krypt;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
public class AESController {

    @PostMapping(value = "/encrypt1")
    @ResponseBody
    public Map<String, String> encryptOneTime(InputStream inputStream) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Map<String, String> retVals = new HashMap<>();
        byte toEncryptBites[] = StreamUtils.copyToByteArray(inputStream);
        retVals.put("source", Base64.encodeBase64String(toEncryptBites));

        Random rd = new Random();
        byte[] randomBites = new byte[32];
        rd.nextBytes(randomBites);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        IvParameterSpec iv = new IvParameterSpec("0000000000000000".getBytes(StandardCharsets.UTF_8));
        SecretKeySpec keySpec = new SecretKeySpec(randomBites, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);
        byte encryptedBites[] = cipher.doFinal(toEncryptBites);
        retVals.put("IV", Base64.encodeBase64String("0000000000000000".getBytes(StandardCharsets.UTF_8)));
        retVals.put("Key", Base64.encodeBase64String(randomBites));
        retVals.put("encrypted", Base64.encodeBase64String(encryptedBites));
        return retVals;
    }
}
