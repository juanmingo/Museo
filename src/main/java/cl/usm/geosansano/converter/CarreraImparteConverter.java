package cl.usm.geosansano.converter;

import cl.usm.geosansano.entity.CarreraImparte;
import cl.usm.geosansano.entity.CarreraImpartePK;
import cl.usm.geosansano.sessions.beans.CarreraImparteFacade;
import cl.usm.geosansano.sessions.beans.CarreraImparteFacadeLocal;
import cl.usm.geosansano.functions.FuncionEJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.ServletRequest;

/**
 *
 * @author Alexander Riquelme
 */
@FacesConverter(value = "carreraImparteConverter")
public class CarreraImparteConverter implements Converter {

    private final CarreraImparteFacadeLocal carreraImparteFacade = FuncionEJB.lookup(CarreraImparteFacade.class);

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.trim().equals("")) {
            String[] pks = value.split("@");
            CarreraImpartePK carrPk = new CarreraImpartePK(Integer.parseInt(pks[2]), Integer.parseInt(pks[0]), Integer.parseInt(pks[3]), Integer.parseInt(pks[1]));
            return carreraImparteFacade.find(carrPk);
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        String str = "";

        if (object instanceof CarreraImparte) {
            str = ((CarreraImparte) object).getCarreraImpartePK().getCodCarrera()
                    + "@" + ((CarreraImparte) object).getCarreraImpartePK().getCodMencion()
                    + "@" + ((CarreraImparte) object).getCarreraImpartePK().getCodSedeCarrera()
                    + "@" + ((CarreraImparte) object).getCarreraImpartePK().getCodSedeImparte();

        }

        return str;
    }

}
