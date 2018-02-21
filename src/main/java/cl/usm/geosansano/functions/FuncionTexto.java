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

}
