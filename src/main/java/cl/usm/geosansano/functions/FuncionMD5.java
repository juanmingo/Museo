package cl.usm.geosansano.functions;

import java.io.Serializable;
import java.security.*;
import java.math.*;

/**
 *
 * @author Juan D. Delgado Robles.
 */
public abstract class FuncionMD5 implements Serializable {

    public static String obtenerHash(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("[Error][FuncionMD5][obtenerHash]:" + e.getMessage());
        }
        md.update(password.getBytes(), 0, password.length());
        return new BigInteger(1, md.digest()).toString(16).toUpperCase();
    }
    
    

}
