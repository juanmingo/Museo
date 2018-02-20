package cl.usm.geosansano.functions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Juan D. Delgado Robles.
 */
public abstract class FuncionCorreo {

    private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static boolean validarCorreo(String email) {
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
