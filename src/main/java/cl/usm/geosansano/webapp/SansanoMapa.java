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
    private long musproId;
    private String iconoMarkerUsuario;
    private String iconoMarkerSansano;
    private String iconoMarkerUSM;

    private String iconoMarkerRechazado;
    private String iconoMarkerPendiente;
    private String iconoMarkerEnviadoRevision;

    //
    public double central_latitud;
    public double central_longitud;
    public int central_zoom;
    //
    private boolean isCampusSede;
    private boolean isEditarPublicacion;
    private boolean isAdministrador;
    private boolean isVerMas;
    private boolean isVerEstadoPublicacion;
    //
    private String colorEstadoPublicacion = "";

//</editor-fold>
    public void cargarSansanoMapa() {

        this.central_latitud = Pagina.CENTRAL_LATITUD;
        this.central_longitud = Pagina.CENTRAL_LONGITUD;
        this.central_zoom = Pagina.CENTRAL_ZOOM;

        this.mapModel = new DefaultMapModel();
        this.museoUsuario = this.museoUsuarioFacade.findByCorreo(Common.obtenerCuentaUsuario());
        this.mususuId = FuncionNumero.nvlBigInteger(String.valueOf(Common.obtenerMususuId()));

        this.iconoMarkerUsuario = Common.obtenereUrlBase() + Pagina.ICON_MARKER_LIGHT_GREEN;
        this.iconoMarkerSansano = Common.obtenereUrlBase() + Pagina.ICON_MARKER_GREEN;
        this.iconoMarkerUSM = Common.obtenereUrlBase() + Pagina.ICON_MARKER_USM;

        this.iconoMarkerRechazado = Common.obtenereUrlBase() + Pagina.ICON_MARKER_RED;
        this.iconoMarkerEnviadoRevision = Common.obtenereUrlBase() + Pagina.ICON_MARKER_YELLOW;
        this.iconoMarkerPendiente = Common.obtenereUrlBase() + Pagina.ICON_MARKER_PURPLE;

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

            if (null != objMP.getCodVigencia().getCodVigencia()) {
                switch (objMP.getCodVigencia().getCodVigencia()) {
                    case 0:
                        marker.setIcon(this.iconoMarkerPendiente);
                        break;
                    case 1:
                        marker.setIcon(this.iconoMarkerEnviadoRevision);
                        break;
                    case 3:
                        marker.setIcon(this.iconoMarkerRechazado);
                        break;
                    default:
                        break;
                }
            }
            this.mapModel.addOverlay(marker);
        }

        Common.redireccionar(Pagina.PAGINA_MENU_SANSANO_MAPA);
    }

    public void onStateChange(StateChangeEvent event) {

        this.central_latitud = event.getCenter().getLat();
        this.central_longitud = event.getCenter().getLng();
        LatLngBounds coordenadas = event.getBounds();

        if (event.getZoomLevel() < 4) {
            this.central_zoom = 4;
        } else {
            this.central_zoom = event.getZoomLevel();
        }

        this.museoProyectoList = museoProyectoFL.findByProyectosGeo(coordenadas.getNorthEast().getLat(), coordenadas.getNorthEast().getLng(), coordenadas.getSouthWest().getLat(), coordenadas.getSouthWest().getLng());

        this.mapModel = new DefaultMapModel();

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

        this.museoProyectoList = museoProyectoFL.findByProyectosUsuarioPendienteRechazado(this.mususuId.longValue(), coordenadas.getNorthEast().getLat(), coordenadas.getNorthEast().getLng(), coordenadas.getSouthWest().getLat(), coordenadas.getSouthWest().getLng());

        for (MuseoProyecto objMP : museoProyectoList) {

            Marker marker = new Marker(new LatLng(objMP.getMusproLatitud(), objMP.getMusproLongitud()), objMP.getMusproNombre().toUpperCase(), objMP.getMusproId());

            if (null != objMP.getCodVigencia().getCodVigencia()) {
                switch (objMP.getCodVigencia().getCodVigencia()) {
                    case 0:
                        marker.setIcon(this.iconoMarkerPendiente);
                        break;
                    case 1:
                        marker.setIcon(this.iconoMarkerEnviadoRevision);
                        break;
                    case 3:
                        marker.setIcon(this.iconoMarkerRechazado);
                        break;
                    default:
                        break;
                }
            }

            this.mapModel.addOverlay(marker);
        }

        //addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Zoom Level", String.valueOf(central_zoom)));
        //addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Center", event.getCenter().toString()));
        //addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "NorthEast", coordenadas.getNorthEast().toString()));
        //addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "SouthWest", coordenadas.getSouthWest().toString()));
        //addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "LIST", String.valueOf(cont)));
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

        Marker marker = (Marker) event.getOverlay();
        this.musproId = FuncionNumero.nvlLong(marker.getData().toString());
        this.museoProyect = this.museoProyectoFL.find(this.musproId);

        this.isCampusSede = musproId == 0 || musproId == 1 || musproId == 2 || musproId == 3 || musproId == 4 || musproId == 5;

        if (this.museoProyect.getCodVigencia().getCodVigencia() == 0 && this.museoProyect.getMususuId() == this.mususuId.longValue()) {
            this.isEditarPublicacion = true;
            this.isVerMas = false;
        } else {
            this.isEditarPublicacion = false;
            this.isVerMas = true;
        }

        if (this.museoUsuario.getCodPerfil().getCodPerfil() == 1) {
            this.isAdministrador = true;
            this.isEditarPublicacion = false;
            this.isVerMas = false;
        }

        if (this.museoUsuario.getCodPerfil().getCodPerfil() == 1 || this.museoProyect.getMususuId() == this.mususuId.longValue()) {
            this.isVerEstadoPublicacion = true;
        } else {
            this.isVerEstadoPublicacion = false;
        }

        if (null != this.museoProyect.getCodVigencia().getCodVigencia()) {
            switch (this.museoProyect.getCodVigencia().getCodVigencia()) {
                case 0:
                    this.colorEstadoPublicacion = Pagina.COLOR_ESTADO_PENDIENTE;
                    break;
                case 1:
                    this.colorEstadoPublicacion = Pagina.COLOR_ESTADO_ENVIADA;
                    break;
                case 2:
                    this.colorEstadoPublicacion = Pagina.COLOR_ESTADO_APROBADA;
                    break;
                case 3:
                    this.colorEstadoPublicacion = Pagina.COLOR_ESTADO_RECHAZADA;
                    break;
                default:
                    this.colorEstadoPublicacion = "";
                    break;
            }
        }

        RequestContext context = RequestContext.getCurrentInstance();
        //context.update("formSansano:mapGeoSansano");
        context.update("formSansano:infoWindow");

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

    public boolean isIsEditarPublicacion() {
        return isEditarPublicacion;
    }

    public void setIsEditarPublicacion(boolean isEditarPublicacion) {
        this.isEditarPublicacion = isEditarPublicacion;
    }

    public boolean isIsVerMas() {
        return isVerMas;
    }

    public void setIsVerMas(boolean isVerMas) {
        this.isVerMas = isVerMas;
    }

    public boolean isIsAdministrador() {
        return isAdministrador;
    }

    public void setIsAdministrador(boolean isAdministrador) {
        this.isAdministrador = isAdministrador;
    }

    public boolean isIsVerEstadoPublicacion() {
        return isVerEstadoPublicacion;
    }

    public void setIsVerEstadoPublicacion(boolean isVerEstadoPublicacion) {
        this.isVerEstadoPublicacion = isVerEstadoPublicacion;
    }

    public String getColorEstadoPublicacion() {
        return colorEstadoPublicacion;
    }

    public void setColorEstadoPublicacion(String colorEstadoPublicacion) {
        this.colorEstadoPublicacion = colorEstadoPublicacion;
    }

    public long getMusproId() {
        return musproId;
    }

    public void setMusproId(long musproId) {
        this.musproId = musproId;
    }
//</editor-fold>
}
