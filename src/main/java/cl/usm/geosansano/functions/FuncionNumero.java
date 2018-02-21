package cl.usm.geosansano.functions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan D. Delgado Robles.
 */
public abstract class FuncionNumero {

    private static Logger logger = Logger.getLogger("[FuncionNumero]");

    public static BigDecimal nvlBigDecimal(String texto) {
        BigDecimal numero = new BigDecimal(0);
        if (texto == null || "".equals(texto.trim()) || "null".equals(texto.trim().toLowerCase()) || "0".equals(texto.trim())) {
            return numero;
        }
        try {
            numero = new BigDecimal(texto);
        } catch (Exception e) {
            System.out.println("[Error][FuncionNumero][nvlBigDecimal][Exception]: " + e.getMessage());
            logger.log(Level.SEVERE, "[Error][FuncionNumero][nvlBigDecimal][Exception]: {0}", e.getMessage());
            return new BigDecimal(0);
        }
        return numero;
    }

    public static BigInteger nvlBigInteger(String texto) {
        BigInteger numero = new BigInteger("0");
        if (texto == null || "".equals(texto.trim()) || "null".equals(texto.trim().toLowerCase()) || "0".equals(texto.trim())) {
            return numero;
        }
        try {
            numero = new BigInteger(texto);
        } catch (Exception e) {
            System.out.println("[Error][FuncionNumero][nvlBigDecimal][Exception]: " + e.getMessage());
            logger.log(Level.SEVERE, "[Error][FuncionNumero][nvlBigDecimal][Exception]: {0}", e.getMessage());
            return new BigInteger("0");
        }
        return numero;
    }

    public static int nvlInteger(String texto) {
        int numero = 0;
        if (texto == null || "".equals(texto.trim()) || "null".equals(texto.trim().toLowerCase()) || "0".equals(texto.trim())) {
            return numero;
        }
        try {
            numero = Integer.parseInt(texto);
        } catch (Exception e) {
            System.out.println("[Error][FuncionNumero][nvlInteger][Exception]: " + e.getMessage());
            logger.log(Level.SEVERE, "[Error][FuncionNumero][nvlInteger][Exception]: {0}", e.getMessage());
            return 0;
        }
        return numero;
    }

    public static float nvlFloat(String texto) {
        float numero = 0;
        if (texto == null || "".equals(texto.trim()) || "null".equals(texto.trim().toLowerCase()) || "0".equals(texto.trim())) {
            return numero;
        }
        try {
            numero = Float.parseFloat(texto);
        } catch (Exception e) {
            System.out.println("[Error][FuncionNumero][nvlInteger][Exception]: " + e.getMessage());
            logger.log(Level.SEVERE, "[Error][FuncionNumero][nvlInteger][Exception]: {0}", e.getMessage());
            return 0;
        }
        return numero;
    }

    public static double nvlDouble(String texto) {
        double numero = 0;
        if (texto == null || "".equals(texto.trim()) || "null".equals(texto.trim().toLowerCase()) || "0".equals(texto.trim())) {
            return numero;
        }
        try {
            numero = Double.parseDouble(texto);
        } catch (Exception e) {
            System.out.println("[Error][FuncionNumero][nvlInteger][Exception]: " + e.getMessage());
            logger.log(Level.SEVERE, "[Error][FuncionNumero][nvlInteger][Exception]: {0}", e.getMessage());
            return 0;
        }
        return numero;
    }

    public static Short nvlShort(String texto) {
        Short numero = 0;
        if (texto == null || "".equals(texto.trim()) || "null".equals(texto.trim().toLowerCase()) || "0".equals(texto.trim())) {
            return numero;
        }
        try {
            numero = Short.parseShort(texto);
        } catch (Exception e) {
            System.out.println("[Error][FuncionNumero][nvlShort][Exception]: " + e.getMessage());
            logger.log(Level.SEVERE, "[Error][FuncionNumero][nvlShort][Exception]: {0}", e.getMessage());
            return 0;
        }
        return numero;
    }

    public static long nvlLong(String texto) {
        long numero = ((Long) (long) 0);
        if (texto == null || "".equals(texto.trim()) || "null".equals(texto.trim().toLowerCase()) || "0".equals(texto.trim())) {
            return numero;
        }
        try {
            numero = Long.parseLong(texto);
        } catch (Exception e) {
            System.out.println("[Error][FuncionNumero][parseLong][Exception]: " + e.getMessage());
            logger.log(Level.SEVERE, "[Error][FuncionNumero][parseLong][Exception]: {0}", e.getMessage());
            return ((Long) (long) 0);
        }
        return numero;
    }

    public static boolean validarInteger(String texto) {
        try {
            int numero = Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            //System.out.println("[Error][FuncionNumero][validarLong][Exception]: " + e.getMessage());
            //logger.log(Level.SEVERE, "[Error][FuncionNumero][validarLong][Exception]: {0}", e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean validarLong(String texto) {
        try {
            Long numero = Long.parseLong(texto);
        } catch (NumberFormatException e) {
            //System.out.println("[Error][FuncionNumero][validarLong][Exception]: " + e.getMessage());
            //logger.log(Level.SEVERE, "[Error][FuncionNumero][validarLong][Exception]: {0}", e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean validarBigDecimal(String texto) {
        try {
            BigDecimal numero = new BigDecimal(texto);
        } catch (NumberFormatException e) {
            //System.out.println("[Error][FuncionNumero][validarLong][Exception]: " + e.getMessage());
            //logger.log(Level.SEVERE, "[Error][FuncionNumero][validarLong][Exception]: {0}", e.getMessage());
            return false;
        }
        return true;
    }

    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}
