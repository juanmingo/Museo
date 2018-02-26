package cl.usm.geosansano.converter;

import cl.usm.geosansano.entity.Pais;
import cl.usm.geosansano.entity.Sede;
import cl.usm.geosansano.sessions.beans.SedeFacade;
import cl.usm.geosansano.sessions.beans.SedeFacadeLocal;
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
@FacesConverter(value = "sedeConverter")
public class SedeConverter implements Converter {

    private final SedeFacadeLocal sedeFacade = FuncionEJB.lookup(SedeFacade.class);

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        String ip = ((ServletRequest) fc.getExternalContext().getRequest()).getRemoteAddr();
        if (value != null && !value.trim().equals("")) {
            return sedeFacade.find(Integer.parseInt(value));
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        String str = "";

        if (object instanceof Sede) {
            str = ((Sede) object).getSedCodSede().toString();
        }
        if (object instanceof Integer) {
            str = object + "";
        }
        return str;
    }

}
