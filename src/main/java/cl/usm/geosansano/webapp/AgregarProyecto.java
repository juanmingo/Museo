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

    private MapModel mapModel;
    private String nombreProyecto;
    private double latitud;
    private double longitud;

    public void cargarAgregarProyecto() {
        Common.redireccionar(Pagina.PAGINA_MENU_AGREGAR_PROYECTO);
    }

    @PostConstruct
    public void init() {
        this.mapModel = new DefaultMapModel();
    }

    public void addMarker() {

        if (!"".equals(this.nombreProyecto)) {
            Marker marker = new Marker(new LatLng(latitud, longitud), this.nombreProyecto);
            this.mapModel.addOverlay(marker);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, this.nombreProyecto, "Latitud:" + this.latitud + ", Longitud:" + this.longitud));

            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dlgAgregarProyecto').hide()");

            //Common.redireccionar(Pagina.PAGINA_MENU_CARGAR_DATOS_SANSANO_MAPA);
        }
    }

    public MapModel getMapModel() {
        return mapModel;
    }

    public void setMapModel(MapModel mapModel) {
        this.mapModel = mapModel;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

}
