package cl.usm.geosansano.webapp;

//<editor-fold defaultstate="collapsed" desc="Imports">
import cl.usm.geosansano.entity.MuseoProyecto;
import cl.usm.geosansano.entity.MuseoUsuario;
import cl.usm.geosansano.functions.FuncionMD5;
import cl.usm.geosansano.functions.FuncionNumero;
import cl.usm.geosansano.functions.FuncionTexto;
import cl.usm.geosansano.sessions.beans.MuseoProyectoFacadeLocal;
import cl.usm.geosansano.sessions.beans.MuseoUsuarioFacadeLocal;
import cl.usm.geosansano.sistema.Common;
import cl.usm.geosansano.sistema.Pagina;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.map.StateChangeEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.LatLngBounds;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
//</editor-fold>

/**
 *
 * @author Juan D. Delgado Robles.
 */
@ManagedBean(name = "generalMapa", eager = true)
@SessionScoped
public class GeneralMapa implements Serializable {

    @EJB
    private MuseoProyectoFacadeLocal museoProyectoFL;
    //
    private MapModel mapModel;
    //
    private MuseoProyecto museoProyect;
    private List<MuseoProyecto> museoProyectoList;
    //
    private String iconoMarkerUsuario;
    private String iconoMarkerSansano;
    private String iconoMarkerUSM;
    //
    public double central_latitud;
    public double central_longitud;
    public int central_zoom;

    public void cargarMapaGeneral() {

        this.mapModel = new DefaultMapModel();

        this.central_latitud = Pagina.CENTRAL_LATITUD;
        this.central_longitud = Pagina.CENTRAL_LONGITUD;
        this.central_zoom = Pagina.CENTRAL_ZOOM;

        this.iconoMarkerUsuario = Common.obtenereUrlBase() + Pagina.ICON_MARKER_CELESTE;

        this.iconoMarkerSansano = Common.obtenereUrlBase() + Pagina.ICON_MARKER_VERDE;

        this.iconoMarkerUSM = Common.obtenereUrlBase() + Pagina.ICON_MARKER_USM;

        this.museoProyectoList = museoProyectoFL.findByProyectosGeo(Pagina.CENTRAL_NORTE_LATITUD, Pagina.CENTRAL_NORTE_LONGITUD, Pagina.CENTRAL_SUR_LATITUD, Pagina.CENTRAL_SUR_LONGITUD);

        for (MuseoProyecto objMP : museoProyectoList) {
            Marker marker = new Marker(new LatLng(objMP.getMusproLatitud(), objMP.getMusproLongitud()), objMP.getMusproNombre().toUpperCase());
            if (objMP.getMusproId() == 0 || objMP.getMusproId() == 1 || objMP.getMusproId() == 2 || objMP.getMusproId() == 3
                    || objMP.getMusproId() == 4 || objMP.getMusproId() == 5) {
                marker.setIcon(this.iconoMarkerUSM);
            } else {
                marker.setIcon(this.iconoMarkerSansano);
            }
            this.mapModel.addOverlay(marker);
        }

        Common.redireccionar(Pagina.PAGINA_MENU_CARGAR_DATOS_GENERAL_MAPA);

    }

    public void onStateChange(StateChangeEvent event) {

        /*
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Zoom Level", String.valueOf(zoomLevel)));
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Center", event.getCenter().toString()));
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "NorthEast", bounds.getNorthEast().toString()));
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "SouthWest", bounds.getSouthWest().toString()));
         */
        this.central_latitud = event.getCenter().getLat();
        this.central_longitud = event.getCenter().getLng();
        this.central_zoom = event.getZoomLevel();
        LatLngBounds coordenadas = event.getBounds();

        this.mapModel = new DefaultMapModel();

        this.museoProyectoList = museoProyectoFL.findByProyectosGeo(coordenadas.getNorthEast().getLat(), coordenadas.getNorthEast().getLng(), coordenadas.getSouthWest().getLat(), coordenadas.getSouthWest().getLng());

        for (MuseoProyecto objMP : museoProyectoList) {
            Marker marker = new Marker(new LatLng(objMP.getMusproLatitud(), objMP.getMusproLongitud()), objMP.getMusproNombre().toUpperCase());
            if (objMP.getMusproId() == 0 || objMP.getMusproId() == 1 || objMP.getMusproId() == 2 || objMP.getMusproId() == 3
                    || objMP.getMusproId() == 4 || objMP.getMusproId() == 5) {
                marker.setIcon(this.iconoMarkerUSM);
            } else {
                marker.setIcon(this.iconoMarkerSansano);
            }
            this.mapModel.addOverlay(marker);
        }
    }

    public void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    //<editor-fold defaultstate="collapsed" desc="Getter && Setter">
    public MapModel getMapModel() {
        return mapModel;
    }

    public void setMapModel(MapModel mapModel) {
        this.mapModel = mapModel;
    }

    public double getCentral_latitud() {
        return central_latitud;
    }

    public double getCentral_longitud() {
        return central_longitud;
    }

    public int getCentral_zoom() {
        return central_zoom;
    }
//</editor-fold>
}
