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
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.event.map.PointSelectEvent;
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
@ManagedBean(name = "sansanoMapa", eager = true)
@SessionScoped
public class SansanoMapa implements Serializable {

//<editor-fold defaultstate="collapsed" desc="Atribtos">
    @EJB
    private MuseoUsuarioFacadeLocal museoUsuarioFacade;
    @EJB
    private MuseoProyectoFacadeLocal museoProyectoFL;
    //
    private MuseoUsuario museoUsuario;
    //
    private MapModel mapModel;
    //
    private MuseoProyecto museoProyect;
    private List<MuseoProyecto> museoProyectoList;
    //
    private String nombreUsuario = "";
    private BigInteger mususuId;
    private String iconoMarkerUsuario;
    private String iconoMarkerSansano;
    private String iconoMarkerUSM;

    private String iconoMarkerRechazado;
    private String iconoMarkerPendiente;
    //
    public double central_latitud;
    public double central_longitud;
    public int central_zoom;
    //
    private boolean isCampusSede;
//</editor-fold>

    public void cargarSansanoMapa() {

        this.central_latitud = Pagina.CENTRAL_LATITUD;
        this.central_longitud = Pagina.CENTRAL_LONGITUD;
        this.central_zoom = Pagina.CENTRAL_ZOOM;

        this.mapModel = new DefaultMapModel();
        this.museoUsuario = this.museoUsuarioFacade.findByCorreo(Common.obtenerCuentaUsuario());
        this.mususuId = FuncionNumero.nvlBigInteger(String.valueOf(Common.obtenerMususuId()));

        this.iconoMarkerUsuario = Common.obtenereUrlBase() + Pagina.ICON_MARKER_GREEN;
        this.iconoMarkerSansano = Common.obtenereUrlBase() + Pagina.ICON_MARKER_BLUE;
        this.iconoMarkerUSM = Common.obtenereUrlBase() + Pagina.ICON_MARKER_USM;

        this.iconoMarkerRechazado = Common.obtenereUrlBase() + Pagina.ICON_MARKER_RED;
        this.iconoMarkerPendiente = Common.obtenereUrlBase() + Pagina.ICON_MARKER_YELLOW;

        if (this.museoUsuario != null) {
            this.nombreUsuario = FuncionTexto.nvlTexto(this.museoUsuario.getMususuNombres(), "") + " " + FuncionTexto.nvlTexto(this.museoUsuario.getMususuPaterno(), "") + " " + FuncionTexto.nvlTexto(this.museoUsuario.getMususuMaterno(), "");
            this.nombreUsuario = this.nombreUsuario.trim();
            this.nombreUsuario = this.nombreUsuario.toUpperCase();
        }

        this.museoProyectoList = museoProyectoFL.findByProyectosGeo(Pagina.CENTRAL_NORTE_LATITUD, Pagina.CENTRAL_NORTE_LONGITUD, Pagina.CENTRAL_SUR_LATITUD, Pagina.CENTRAL_SUR_LONGITUD);

        for (MuseoProyecto objMP : museoProyectoList) {
            Marker marker = new Marker(new LatLng(objMP.getMusproLatitud(), objMP.getMusproLongitud()), objMP.getMusproNombre().toUpperCase(), objMP.getMusproId());
            if (objMP.getMusproId() == 0 || objMP.getMusproId() == 1 || objMP.getMusproId() == 2 || objMP.getMusproId() == 3
                    || objMP.getMusproId() == 4 || objMP.getMusproId() == 5) {
                marker.setIcon(this.iconoMarkerUSM);
            } else if (objMP.getMususuId() == this.mususuId.longValue()) {
                marker.setIcon(this.iconoMarkerUsuario);
            } else {
                marker.setIcon(this.iconoMarkerSansano);
            }
            this.mapModel.addOverlay(marker);
        }

        this.museoProyectoList = museoProyectoFL.findByProyectosUsuarioPendienteRechazado(this.mususuId.longValue(), Pagina.CENTRAL_NORTE_LATITUD, Pagina.CENTRAL_NORTE_LONGITUD, Pagina.CENTRAL_SUR_LATITUD, Pagina.CENTRAL_SUR_LONGITUD);

        for (MuseoProyecto objMP : museoProyectoList) {

            Marker marker = new Marker(new LatLng(objMP.getMusproLatitud(), objMP.getMusproLongitud()), objMP.getMusproNombre().toUpperCase(), objMP.getMusproId());

            if (objMP.getCodVigencia().getCodVigencia() == 0) {
                marker.setIcon(this.iconoMarkerPendiente);
            } else if (objMP.getCodVigencia().getCodVigencia() == 2) {
                marker.setIcon(this.iconoMarkerRechazado);
            }

            this.mapModel.addOverlay(marker);
        }

        Common.redireccionar(Pagina.PAGINA_MENU_SANSANO_MAPA);
    }

    public void onStateChange(StateChangeEvent event) {

        this.central_latitud = event.getCenter().getLat();
        this.central_longitud = event.getCenter().getLng();

        if (event.getZoomLevel() < 3) {
            this.central_zoom = 3;
        } else {
            this.central_zoom = event.getZoomLevel();
        }

        LatLngBounds coordenadas = event.getBounds();

        this.mapModel = new DefaultMapModel();

        int cont = 0;
        if (coordenadas.getNorthEast().getLng() > coordenadas.getSouthWest().getLng()) {
            this.museoProyectoList = museoProyectoFL.findByProyectosGeo(coordenadas.getNorthEast().getLat(), coordenadas.getNorthEast().getLng(), coordenadas.getSouthWest().getLat(), coordenadas.getSouthWest().getLng());
        } else {
            this.museoProyectoList = museoProyectoFL.findByProyectosGeo(coordenadas.getNorthEast().getLat(), coordenadas.getSouthWest().getLng(), coordenadas.getSouthWest().getLat(), coordenadas.getNorthEast().getLng());
        }

        cont += museoProyectoList.size();

        for (MuseoProyecto objMP : museoProyectoList) {
            Marker marker = new Marker(new LatLng(objMP.getMusproLatitud(), objMP.getMusproLongitud()), objMP.getMusproNombre().toUpperCase(), objMP.getMusproId());
            if (objMP.getMusproId() == 0 || objMP.getMusproId() == 1 || objMP.getMusproId() == 2 || objMP.getMusproId() == 3
                    || objMP.getMusproId() == 4 || objMP.getMusproId() == 5) {
                marker.setIcon(this.iconoMarkerUSM);
            } else if (objMP.getMususuId() == this.mususuId.longValue()) {
                marker.setIcon(this.iconoMarkerUsuario);
            } else {
                marker.setIcon(this.iconoMarkerSansano);
            }
            this.mapModel.addOverlay(marker);
        }

        if (coordenadas.getNorthEast().getLng() > coordenadas.getSouthWest().getLng()) {
            this.museoProyectoList = museoProyectoFL.findByProyectosUsuarioPendienteRechazado(this.mususuId.longValue(), coordenadas.getNorthEast().getLat(), coordenadas.getNorthEast().getLng(), coordenadas.getSouthWest().getLat(), coordenadas.getSouthWest().getLng());
        } else {
            this.museoProyectoList = museoProyectoFL.findByProyectosUsuarioPendienteRechazado(this.mususuId.longValue(), coordenadas.getNorthEast().getLat(), coordenadas.getSouthWest().getLng(), coordenadas.getSouthWest().getLat(), coordenadas.getNorthEast().getLng());
        }

        cont += museoProyectoList.size();

        for (MuseoProyecto objMP : museoProyectoList) {

            Marker marker = new Marker(new LatLng(objMP.getMusproLatitud(), objMP.getMusproLongitud()), objMP.getMusproNombre().toUpperCase(), objMP.getMusproId());

            if (objMP.getCodVigencia().getCodVigencia() == 0) {
                marker.setIcon(this.iconoMarkerPendiente);
            } else if (objMP.getCodVigencia().getCodVigencia() == 2) {
                marker.setIcon(this.iconoMarkerRechazado);
            }

            this.mapModel.addOverlay(marker);
        }

        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Zoom Level", String.valueOf(central_zoom)));
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Center", event.getCenter().toString()));
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "NorthEast", coordenadas.getNorthEast().toString()));
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "SouthWest", coordenadas.getSouthWest().toString()));

        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "LIST", String.valueOf(cont)));

        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formSansano:mapGeoSansano");
        context.update("formSansano:msjProyectos");
    }

    public void onPointSelect(PointSelectEvent event) {
        LatLng latlng = event.getLatLng();

        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Point Selected", "Lat:" + latlng.getLat() + ", Lng:" + latlng.getLng()));

        RequestContext context = RequestContext.getCurrentInstance();
        //context.update("formSansano:mapGeoSansano");
        context.update("formSansano:msjProyectos");
    }

    public void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onMarkerSelect(OverlaySelectEvent event) {

        System.out.println("OverlaySelectEvent event: " + event);

        Marker marker = (Marker) event.getOverlay();
        long musproId = FuncionNumero.nvlLong(marker.getData().toString());
        this.museoProyect = this.museoProyectoFL.find(musproId);

        this.isCampusSede = musproId == 0 || musproId == 1 || musproId == 2 || musproId == 3 || musproId == 4 || musproId == 5;
        System.out.println("linkUSM: " + isCampusSede);
        RequestContext context = RequestContext.getCurrentInstance();
        //context.update("formSansano:mapGeoSansano");
        context.update("formSansano:linkUSM");

    }

    //<editor-fold defaultstate="collapsed" desc="Getter && Setter">
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public MapModel getMapModel() {
        return mapModel;
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

    public MuseoProyecto getMuseoProyect() {
        return museoProyect;
    }

    public boolean isIsCampusSede() {
        return isCampusSede;
    }

//</editor-fold>
}
