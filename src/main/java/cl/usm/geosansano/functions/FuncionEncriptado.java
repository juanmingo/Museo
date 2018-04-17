/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.geosansano.functions;

import java.security.*;

import javax.crypto.*;
import javax.crypto.spec.*;

import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Alexander Riquelme
 */
public class FuncionEncriptado {

    int AES_Key_Size = 128;
    byte[] aesKey;
    SecretKeySpec aeskeySpec;

    Cipher aesCipher;

    public void makeKey() throws NoSuchAlgorithmException, NoSuchPaddingException {
        aesCipher = Cipher.getInstance("AES");
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(AES_Key_Size);
        SecretKey key = kgen.generateKey();
        aesKey = key.getEncoded();
        aeskeySpec = new SecretKeySpec(aesKey, "AES");
    }

    /**
     * Encrypts AES String.
     */
    public  String encrypt(String Data) throws Exception {
        aesCipher.init(Cipher.ENCRYPT_MODE, aeskeySpec);
        byte[] encVal = aesCipher.doFinal(Data.getBytes());
        String encryptedValue = DatatypeConverter.printBase64Binary(encVal);
        return encryptedValue;
    }

    /**
     * Decrypts AES String.
     */
    public String decrypt(String encryptedData) throws Exception {
        aesCipher.init(Cipher.DECRYPT_MODE, aeskeySpec);
        byte[] decordedValue = DatatypeConverter.parseBase64Binary(encryptedData);
        byte[] decValue = aesCipher.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }

}
