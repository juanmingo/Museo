package cl.usm.geosansano.functions;

import cl.usm.geosansano.entity.Pais;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.model.SelectItem;

/**
 *
 * @author Juan D. Delgado Robles
 */
public abstract class FuncionSelectItem implements Serializable {

    public static ArrayList<SelectItem> listarAños() {
        ArrayList<SelectItem> listarAños = new ArrayList<>();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy");
        Date añoActual = new Date();
        int año = FuncionNumero.nvlInteger(formatoFecha.format(añoActual));
        for (int i = año; i >= 1900; i--) {
            listarAños.add(new SelectItem(i, String.valueOf(i)));
        }
        return listarAños;
    }

    public static ArrayList<SelectItem> listarPais(List<Pais> paisList) {
        ArrayList<SelectItem> listarPais = new ArrayList<>();
        for (Pais pais : paisList) {
            listarPais.add(new SelectItem(pais.getCodPais(), pais.getNomPais()));
        }
        return listarPais;
    }

}
