package cl.usm.geosansano.functions;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.model.SelectItem;

/**
 *
 * @author Juan D. Delgado Robles
 */
public abstract class FuncionSelectItem implements Serializable {

    public static ArrayList<SelectItem> listarA�os() {
        ArrayList<SelectItem> listarA�os = new ArrayList<>();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy");
        Date a�oActual = new Date();
        int a�o = FuncionNumero.nvlInteger(formatoFecha.format(a�oActual)) + 1;
        for (int i = a�o; i >= 1900; i--) {
            listarA�os.add(new SelectItem(i, String.valueOf(i)));
        }
        return listarA�os;
    }

}
