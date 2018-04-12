package cl.usm.geosansano.webapp.dialog;

//<editor-fold defaultstate="collapsed" desc="Imports">
import cl.usm.geosansano.entity.MuseoProyecto;
import cl.usm.geosansano.entity.MuseoProyectoDetalle;
import cl.usm.geosansano.entity.MuseoUsuario;
import cl.usm.geosansano.functions.FuncionFotoMuseo;
import cl.usm.geosansano.functions.FuncionNumero;
import cl.usm.geosansano.sessions.beans.MuseoProyectoDetalleFacadeLocal;
import cl.usm.geosansano.sessions.beans.MuseoProyectoFacadeLocal;
import cl.usm.geosansano.sessions.beans.MuseoUsuarioFacadeLocal;
import cl.usm.geosansano.sessions.beans.PaisFacadeLocal;
import cl.usm.geosansano.sessions.beans.TipoVigenciaFacadeLocal;
import cl.usm.geosansano.sistema.Common;
import cl.usm.geosansano.sistema.Pagina;
import java.io.File;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Juan D. Delgado Robles.
 */
@ManagedBean(name = "dlgVerProyecto", eager = true)
@SessionScoped
public class DlgVerProyecto implements Serializable {

//<editor-fold defaultstate="collapsed" desc="Atribtos">
    @EJB
    private MuseoUsuarioFacadeLocal museoUsuarioFacade;
    @EJB
    private MuseoProyectoDetalleFacadeLocal museoProyectoDetalleFL;
    @EJB
    private MuseoProyectoFacadeLocal museoProyectoFL;
    @EJB
    private PaisFacadeLocal paisFL;
    @EJB
    private TipoVigenciaFacadeLocal tipoVigenciaFL;
    //
    private MuseoUsuario museoUsuario;
    private MuseoProyecto museoProyect;

    //
    private BigInteger mususuId;

    //
    //
    private List<MuseoProyectoDetalle> museoProyectoDetalleList;
    private List<StreamedContent> fotoProyectoList;
    private StreamedContent fotoProyecto;
    private String rutaCarpetaServer = "";

    //private StreamedContent objStreamedContent;
//</editor-fold>
    public void cargarProyecto(long musproId) {

        this.museoUsuario = this.museoUsuarioFacade.findByCorreo(Common.obtenerCuentaUsuario());
        this.mususuId = FuncionNumero.nvlBigInteger(String.valueOf(Common.obtenerMususuId()));

        this.museoProyect = this.museoProyectoFL.find(musproId);

        System.out.println("musproId: " + musproId);

        this.museoProyectoDetalleList = museoProyectoDetalleFL.findByDetalleActivo(this.museoProyect.getMusproId());

        this.fotoProyectoList = FuncionFotoMuseo.obtenerFotoDetalleList(this.museoProyectoDetalleList);

        for (StreamedContent objFoto : this.fotoProyectoList) {
            this.fotoProyecto = objFoto;
            System.out.println("D:/Servidores/Wildfly-11.0.0.Final/bin/FotosProyectos/" + objFoto.getName());
        }

        System.out.println("2 ******************: " + Common.obtenerAbsolutePath());

        FuncionFotoMuseo.guardarFotosProyectoServidor(this.museoProyectoDetalleList);

        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        this.rutaCarpetaServer = sc.getRealPath(Pagina.CARPETA_IMAGENES_PROYECTO) + "/";
        System.out.println("** rutaCarpetaServer: " + this.rutaCarpetaServer);

        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formVerProyecto:dlgVerProyecto");
    }

    public void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    //<editor-fold defaultstate="collapsed" desc="Getter && Setter">
    public MuseoProyecto getMuseoProyect() {
        return museoProyect;
    }

    public void setMuseoProyect(MuseoProyecto museoProyect) {
        this.museoProyect = museoProyect;
    }

    public List<StreamedContent> getFotoProyectoList() {
        return fotoProyectoList;
    }

    public void setFotoProyectoList(List<StreamedContent> fotoProyectoList) {
        this.fotoProyectoList = fotoProyectoList;
    }

    public StreamedContent getFotoProyecto() {
        return fotoProyecto;
    }

    public void setFotoProyecto(StreamedContent fotoProyecto) {
        this.fotoProyecto = fotoProyecto;
    }

    public List<MuseoProyectoDetalle> getMuseoProyectoDetalleList() {
        return museoProyectoDetalleList;
    }

    public void setMuseoProyectoDetalleList(List<MuseoProyectoDetalle> museoProyectoDetalleList) {
        this.museoProyectoDetalleList = museoProyectoDetalleList;
    }

    public String getRutaCarpetaServer() {
        return rutaCarpetaServer;
    }

    public void setRutaCarpetaServer(String rutaCarpetaServer) {
        this.rutaCarpetaServer = rutaCarpetaServer;
    }
    //</editor-fold>
}
