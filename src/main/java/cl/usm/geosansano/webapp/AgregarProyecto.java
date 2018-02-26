package cl.usm.geosansano.webapp;

//<editor-fold defaultstate="collapsed" desc="Imports">
import cl.usm.geosansano.entity.MuseoProyecto;
import cl.usm.geosansano.functions.FuncionNumero;
import cl.usm.geosansano.sessions.beans.MuseoProyectoFacadeLocal;
import cl.usm.geosansano.sessions.beans.PaisFacadeLocal;
import cl.usm.geosansano.sessions.beans.TipoVigenciaFacadeLocal;
import cl.usm.geosansano.sistema.Common;
import cl.usm.geosansano.sistema.Pagina;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.map.OverlaySelectEvent;
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
@ManagedBean(name = "agregarProyecto", eager = true)
@SessionScoped
public class AgregarProyecto implements Serializable {

    @EJB
    private MuseoProyectoFacadeLocal museoProyectoFL;
    @EJB
    private TipoVigenciaFacadeLocal tipoVigenciaFL;
    @EJB
    private PaisFacadeLocal paisFL;
    //
    private MuseoProyecto museoProyect;
    private List<MuseoProyecto> museoProyectoList;
    //
    private MapModel mapModel;
    //
    private String nombreProyecto;
    private double latitud;
    private double longitud;
    private BigInteger mususuId;
    //
    private double central_latitud;
    private double central_longitud;
    private int central_zoom;
    //
    private String iconoAddMarker;
    private String iconoMarkerAprobado;
    private String iconoMarkerRechazado;
    private String iconoMarkerEnviadoRevision;
    private String iconoMarkerPendiente;
    private String iconoMarkerUSM;
    //
    private boolean isCampusSede;

    @PostConstruct
    public void init() {
    }

    public void cargarAgregarProyecto() {

        this.mapModel = new DefaultMapModel();

        this.mususuId = FuncionNumero.nvlBigInteger(String.valueOf(Common.obtenerMususuId()));
        System.out.println("this.mususuId: " + this.mususuId);

        this.museoProyectoList = museoProyectoFL.findByProyectoUsuario(this.mususuId.longValue());
        System.out.println("museoProyectoList: " + museoProyectoList.size());

        this.iconoAddMarker = Common.obtenereUrlBase() + Pagina.ICON_MARKER_PURPLE;

        this.iconoMarkerAprobado = Common.obtenereUrlBase() + Pagina.ICON_MARKER_GREEN;
        this.iconoMarkerRechazado = Common.obtenereUrlBase() + Pagina.ICON_MARKER_RED;
        this.iconoMarkerEnviadoRevision = Common.obtenereUrlBase() + Pagina.ICON_MARKER_YELLOW;
        this.iconoMarkerPendiente = Common.obtenereUrlBase() + Pagina.ICON_MARKER_PURPLE;

        this.iconoMarkerUSM = Common.obtenereUrlBase() + Pagina.ICON_MARKER_USM;

        this.central_latitud = Pagina.CENTRAL_LATITUD;
        this.central_longitud = Pagina.CENTRAL_LONGITUD;
        this.central_zoom = Pagina.CENTRAL_ZOOM;

        for (MuseoProyecto objMP : museoProyectoList) {

            Marker marker = new Marker(new LatLng(objMP.getMusproLatitud(), objMP.getMusproLongitud()), objMP.getMusproNombre().toUpperCase(), objMP.getMusproId());

            if (objMP.getMusproId() == 0 || objMP.getMusproId() == 1 || objMP.getMusproId() == 2 || objMP.getMusproId() == 3
                    || objMP.getMusproId() == 4 || objMP.getMusproId() == 5) {
                marker.setIcon(this.iconoMarkerUSM);
            } else if (objMP.getCodVigencia().getCodVigencia() == 0) {
                marker.setIcon(this.iconoMarkerPendiente);
            } else if (objMP.getCodVigencia().getCodVigencia() == 1) {
                marker.setIcon(this.iconoMarkerEnviadoRevision);
            } else if (objMP.getCodVigencia().getCodVigencia() == 2) {
                marker.setIcon(this.iconoMarkerAprobado);
            } else if (objMP.getCodVigencia().getCodVigencia() == 3) {
                marker.setIcon(this.iconoMarkerRechazado);
            }

            this.mapModel.addOverlay(marker);
        }

        Common.redireccionar(Pagina.PAGINA_MENU_AGREGAR_PROYECTO);
    }

    public void addMarker() {

        if (!"".equals(this.nombreProyecto)) {

            this.nombreProyecto = this.nombreProyecto.toUpperCase();

            this.museoProyect = new MuseoProyecto(this.museoProyectoFL.newMusproId());
            this.museoProyect.setMususuId(this.mususuId.longValue());

            this.museoProyect.setMusproNombre(this.nombreProyecto.toUpperCase());
            this.museoProyect.setCodPais(this.paisFL.find(0));
            this.museoProyect.setCodVigencia(this.tipoVigenciaFL.find(0));

            this.museoProyect.setMususuIdUsu(this.mususuId);
            this.museoProyect.setFechaModificacion(new Date());

            this.museoProyect.setMusproLatitud(this.latitud);
            this.museoProyect.setMusproLongitud(this.longitud);

            this.museoProyectoFL.create(this.museoProyect);

            Marker marker = new Marker(new LatLng(this.latitud, this.longitud), this.nombreProyecto, this.museoProyect.getMusproId());
            marker.setIcon(this.iconoAddMarker);

            this.mapModel.addOverlay(marker);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, this.nombreProyecto.toUpperCase(), "Latitud:" + this.latitud + ", Longitud:" + this.longitud));

            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dlgAgregarProyecto').hide()");
        }
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

    public void onStateChange(StateChangeEvent event) {
        
          this.central_latitud = event.getCenter().getLat();
        this.central_longitud = event.getCenter().getLng();

        if (event.getZoomLevel() < 3) {
            this.central_zoom = 3;
        } else {
            this.central_zoom = event.getZoomLevel();
        }

        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formSansanoAdd:mapGeoSansanoAdd");
    }

    //<editor-fold defaultstate="collapsed" desc="Getter && Setter">
    public MuseoProyecto getMuseoProyect() {
        return museoProyect;
    }

    public void setMuseoProyect(MuseoProyecto museoProyect) {
        this.museoProyect = museoProyect;
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

    public double getCentral_latitud() {
        return central_latitud;
    }

    public void setCentral_latitud(double central_latitud) {
        this.central_latitud = central_latitud;
    }

    public double getCentral_longitud() {
        return central_longitud;
    }

    public void setCentral_longitud(double central_longitud) {
        this.central_longitud = central_longitud;
    }

    public int getCentral_zoom() {
        return central_zoom;
    }

    public void setCentral_zoom(int central_zoom) {
        this.central_zoom = central_zoom;
    }

    public String getIconoAddMarker() {
        return iconoAddMarker;
    }

    public void setIconoAddMarker(String iconoAddMarker) {
        this.iconoAddMarker = iconoAddMarker;
    }

    public boolean isIsCampusSede() {
        return isCampusSede;
    }

    public void setIsCampusSede(boolean isCampusSede) {
        this.isCampusSede = isCampusSede;
    }
    //</editor-fold>
}
