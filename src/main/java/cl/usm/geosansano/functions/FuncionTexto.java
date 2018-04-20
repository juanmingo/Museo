package cl.usm.geosansano.functions;

import java.io.Serializable;

/**
 *
 * @author Juan D. Delgado Robles
 */
public abstract class FuncionTexto implements Serializable {

    public static String nvlTexto(String texto, String nvl) {
        if (texto != null && !"".equals(texto) && !"null".equals(texto.toLowerCase()) && !"0".equals(texto)) {
            return texto;
        }
        return nvl;
    }

    public static int cantidadCaracteres(String cadena, char comparar) {
        int veces = 0;
        char[] caracteres = cadena.toCharArray();
        for (int i = 0; i <= caracteres.length - 1; i++) {
            if (comparar == caracteres[i]) {
                veces++;
            }
        }
        return veces;
    }

    public static String eliminarAcentos(String input) {
        String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
        String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
        String output = input;
        for (int i = 0; i < original.length(); i++) {
            output = output.replace(original.charAt(i), ascii.charAt(i));
        }
        return output;
    }

    public static Boolean validarContrase�a(String contrase�a) {
        char clave;

        byte contNumero = 0, contLetraMay = 0, contLetraMin = 0;
        if (contrase�a.length() > 7) {
            for (byte i = 0; i < contrase�a.length(); i++) {

                clave = contrase�a.charAt(i);

                String passValue = String.valueOf(clave);

                if (passValue.matches("[A-Z]")) {

                    contLetraMay++;

                } else if (passValue.matches("[a-z]")) {

                    contLetraMin++;

                } else if (passValue.matches("[0-9]")) {

                    contNumero++;

                }
            }
        }

        return contrase�a.length() > 7 && contLetraMay > 0 && contLetraMin > 0 && contNumero > 0;
    }
}
