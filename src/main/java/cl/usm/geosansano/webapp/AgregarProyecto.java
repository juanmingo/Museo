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
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
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
    //
    private MapModel mapModel;
    private String nombreProyecto;
    private double latitud;
    private double longitud;
    private BigInteger mususuId;

    public void cargarAgregarProyecto() {
        Common.redireccionar(Pagina.PAGINA_MENU_AGREGAR_PROYECTO);
    }

    @PostConstruct
    public void init() {
        this.mapModel = new DefaultMapModel();

        this.mususuId = FuncionNumero.nvlBigInteger(String.valueOf(Common.obtenerMususuId()));
        //System.out.println("mususuId: " + mususuId);
    }

    public void addMarker() {

        if (!"".equals(this.nombreProyecto)) {

            Marker marker = new Marker(new LatLng(latitud, longitud), this.nombreProyecto);

            this.museoProyect = new MuseoProyecto(this.museoProyectoFL.newMusproId());
            this.museoProyect.setMususuId(this.mususuId.longValue());

            this.museoProyect.setMusproNombre(nombreProyecto);
            this.museoProyect.setCodPais(paisFL.find(0));
            this.museoProyect.setCodVigencia(tipoVigenciaFL.find(0));

            this.museoProyect.setMususuIdUsu(this.mususuId);
            this.museoProyect.setFechaModificacion(new Date());

            this.museoProyect.setMusproLatitud(this.latitud);
            this.museoProyect.setMusproLongitud(this.longitud);

            this.museoProyectoFL.create(this.museoProyect);

            this.mapModel.addOverlay(marker);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, this.nombreProyecto, "Latitud:" + this.latitud + ", Longitud:" + this.longitud));

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
//</editor-fold>
}
