package cl.usm.geosansano.webapp;

//<editor-fold defaultstate="collapsed" desc="Imports">
import cl.usm.geosansano.entity.MuseoUsuario;
import cl.usm.geosansano.functions.FuncionMD5;
import cl.usm.geosansano.functions.FuncionTexto;
import cl.usm.geosansano.sessions.beans.MuseoUsuarioFacadeLocal;
import cl.usm.geosansano.sistema.Common;
import cl.usm.geosansano.sistema.Pagina;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
//</editor-fold>

/**
 *
 * @author Juan D. Delgado Robles.
 */
@ManagedBean(name = "agregarProyecto", eager = true)
@SessionScoped
public class AgregarProyecto implements Serializable {

    @EJB
    private MuseoUsuarioFacadeLocal museoUsuarioFacade;

    private MapModel emptyModel;
    private String title;
    private double lat;
    private double lng;

    public void cargarAgregarProyecto() {
        Common.redireccionar(Pagina.PAGINA_MENU_AGREGAR_PROYECTO);
    }

    @PostConstruct
    public void init() {
        emptyModel = new DefaultMapModel();
    }

    public MapModel getEmptyModel() {
        return emptyModel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public void addMarker() {
        Marker marker = new Marker(new LatLng(lat, lng), title);
        emptyModel.addOverlay(marker);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Added", "Lat:" + lat + ", Lng:" + lng));
    }

}
