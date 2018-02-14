package cl.usm.geosansano.functions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.model.SelectItem;

/**
 *
 * @author Juan D. Delgado Robles.
 */
public abstract class FuncionPrueba implements Serializable {

    public static List<String> getColors(ArrayList<SelectItem> listarSelectItem) {
        String[] item = new String[listarSelectItem.size()];
        for (int i = 0; i < listarSelectItem.size(); i++) {
            item[i] = listarSelectItem.get(i).getLabel();
        }
        return Arrays.asList(item);
    }

}
