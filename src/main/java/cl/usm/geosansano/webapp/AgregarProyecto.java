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
    public double central_latitud = Pagina.CENTRAL_LATITUD;
    public double central_longitud = Pagina.CENTRAL_LONGITUD;
    public int central_zoom = Pagina.CENTRAL_ZOOM;
    //
    private String iconoAddMarker;
    private String iconoMarkerAprobado;
    private String iconoMarkerRechazado;
    private String iconoMarkerPendiente;
    private String iconoMarkerUSM;

    @PostConstruct
    public void init() {
    }

    public void cargarAgregarProyecto() {

        this.mapModel = new DefaultMapModel();

        this.mususuId = FuncionNumero.nvlBigInteger(String.valueOf(Common.obtenerMususuId()));
        System.out.println("this.mususuId: " + this.mususuId);

        this.museoProyectoList = museoProyectoFL.findByProyectoUsuario(this.mususuId.longValue());
        System.out.println("museoProyectoList: " + museoProyectoList.size());

        this.iconoAddMarker = Common.obtenereUrlBase() + Pagina.ICON_MARKER_ROZADA;

        this.iconoMarkerAprobado = Common.obtenereUrlBase() + Pagina.ICON_MARKER_VERDE;
        this.iconoMarkerRechazado = Common.obtenereUrlBase() + Pagina.ICON_MARKER_ROZADA;
        this.iconoMarkerPendiente = Common.obtenereUrlBase() + Pagina.ICON_MARKER_CELESTE;
        this.iconoMarkerUSM = Common.obtenereUrlBase() + Pagina.ICON_MARKER_USM;

        for (MuseoProyecto objMP : museoProyectoList) {
            
            Marker marker = new Marker(new LatLng(objMP.getMusproLatitud(), objMP.getMusproLongitud()), objMP.getMusproNombre().toUpperCase());

            if (objMP.getMusproId() == 0 || objMP.getMusproId() == 1 || objMP.getMusproId() == 2 || objMP.getMusproId() == 3
                    || objMP.getMusproId() == 4 || objMP.getMusproId() == 5) {
                marker.setIcon(this.iconoMarkerUSM);
            } else if (objMP.getCodVigencia().getCodVigencia() == 0) {
                marker.setIcon(this.iconoMarkerPendiente);
            } else if (objMP.getCodVigencia().getCodVigencia() == 1) {
                marker.setIcon(this.iconoMarkerAprobado);
            } else if (objMP.getCodVigencia().getCodVigencia() == 2) {
                marker.setIcon(this.iconoMarkerRechazado);
            }

            this.mapModel.addOverlay(marker);
        }

        Common.redireccionar(Pagina.PAGINA_MENU_AGREGAR_PROYECTO);
    }

    public void addMarker() {

        if (!"".equals(this.nombreProyecto)) {

            this.nombreProyecto = this.nombreProyecto.toUpperCase();

            Marker marker = new Marker(new LatLng(latitud, longitud), this.nombreProyecto);
            marker.setIcon(this.iconoAddMarker);

            this.museoProyect = new MuseoProyecto(this.museoProyectoFL.newMusproId());
            this.museoProyect.setMususuId(this.mususuId.longValue());

            this.museoProyect.setMusproNombre(this.nombreProyecto.toUpperCase());
            this.museoProyect.setCodPais(paisFL.find(0));
            this.museoProyect.setCodVigencia(tipoVigenciaFL.find(0));

            this.museoProyect.setMususuIdUsu(this.mususuId);
            this.museoProyect.setFechaModificacion(new Date());

            this.museoProyect.setMusproLatitud(this.latitud);
            this.museoProyect.setMusproLongitud(this.longitud);

            this.museoProyectoFL.create(this.museoProyect);

            this.mapModel.addOverlay(marker);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, this.nombreProyecto.toUpperCase(), "Latitud:" + this.latitud + ", Longitud:" + this.longitud));

            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dlgAgregarProyecto').hide()");
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Getter && Setter">
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

    public String getIconoAddMarker() {
        return iconoAddMarker;
    }

    public void setIconoAddMarker(String iconoAddMarker) {
        this.iconoAddMarker = iconoAddMarker;
    }
//</editor-fold>

    public double getCentral_latitud() {
        return central_latitud;
    }

    public double getCentral_longitud() {
        return central_longitud;
    }

    public int getCentral_zoom() {
        return central_zoom;
    }

}
