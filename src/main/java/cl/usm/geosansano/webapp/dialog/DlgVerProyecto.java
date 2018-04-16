package cl.usm.geosansano.webapp.dialog;

//<editor-fold defaultstate="collapsed" desc="Imports">
import cl.usm.geosansano.controller.ImagenProyectoControler;
import cl.usm.geosansano.dto.ImagenProyectoDTO;
import cl.usm.geosansano.entity.MuseoProyecto;
import cl.usm.geosansano.entity.MuseoProyectoDetalle;
import cl.usm.geosansano.entity.MuseoUsuario;
import cl.usm.geosansano.functions.FuncionNumero;
import cl.usm.geosansano.sessions.beans.MuseoProyectoDetalleFacadeLocal;
import cl.usm.geosansano.sessions.beans.MuseoProyectoFacadeLocal;
import cl.usm.geosansano.sessions.beans.MuseoUsuarioFacadeLocal;
import cl.usm.geosansano.sessions.beans.PaisFacadeLocal;
import cl.usm.geosansano.sessions.beans.TipoVigenciaFacadeLocal;
import cl.usm.geosansano.sistema.Common;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;
//</editor-fold>

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
    private MuseoUsuario museoUsuario;
    private MuseoProyecto museoProyect;
    private BigInteger mususuId;
    private List<MuseoProyectoDetalle> museoProyectoDetalleList;
    private List<ImagenProyectoDTO> listarImagenProyecto;
//</editor-fold>

    public void cargarProyecto(long musproId) {

        System.out.println("cargarProyecto: " + musproId);

        this.museoUsuario = this.museoUsuarioFacade.findByCorreo(Common.obtenerCuentaUsuario());
        this.mususuId = FuncionNumero.nvlBigInteger(String.valueOf(Common.obtenerMususuId()));

        this.museoProyect = this.museoProyectoFL.find(musproId);

        this.museoProyectoDetalleList = museoProyectoDetalleFL.findByDetalleActivo(this.museoProyect.getMusproId());
        this.listarImagenProyecto = ImagenProyectoControler.listarImagenProyecto(this.museoProyectoDetalleList);

        RequestContext context = RequestContext.getCurrentInstance();

        System.out.println("musproId: " + musproId);

        if (musproId == 0 || musproId == 1 || musproId == 2 || musproId == 3 || musproId == 4 || musproId == 5) {
            context.update("formCampus:dlgVerCampus");
            context.execute("PF('dlgVerCampus').show()");
            System.out.println("dlgVerCampus");
        } else {
            context.update("formSansano:dlgVerProyecto");
            context.execute("PF('dlgVerProyecto').show()");
            System.out.println("dlgVerProyecto");
        }
    }

    public void cargarProyectoGeneral(long musproId) {

        System.out.println("cargarProyecto: " + musproId);

        this.museoProyect = this.museoProyectoFL.find(musproId);

        this.museoProyectoDetalleList = museoProyectoDetalleFL.findByDetalleActivo(musproId);

        System.out.println("this.museoProyectoDetalleList: " + this.museoProyectoDetalleList.size());

        this.listarImagenProyecto = ImagenProyectoControler.listarImagenProyecto(this.museoProyectoDetalleList);

        RequestContext context = RequestContext.getCurrentInstance();

        System.out.println("musproId: " + musproId);

        if (musproId == 0 || musproId == 1 || musproId == 2 || musproId == 3 || musproId == 4 || musproId == 5) {
            context.update("formCampus:dlgVerCampus");
            context.execute("PF('dlgVerCampus').show()");
            System.out.println("dlgVerCampus");
        } else {
            context.update("formGeneral:dlgVerProyecto");
            context.execute("PF('dlgVerProyecto').show()");
            System.out.println("dlgVerProyecto");
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Getter && Setter">
    public MuseoProyecto getMuseoProyect() {
        return museoProyect;
    }

    public void setMuseoProyect(MuseoProyecto museoProyect) {
        this.museoProyect = museoProyect;
    }

    public List<MuseoProyectoDetalle> getMuseoProyectoDetalleList() {
        return museoProyectoDetalleList;
    }

    public void setMuseoProyectoDetalleList(List<MuseoProyectoDetalle> museoProyectoDetalleList) {
        this.museoProyectoDetalleList = museoProyectoDetalleList;
    }

    public List<ImagenProyectoDTO> getListarImagenProyecto() {
        return listarImagenProyecto;
    }

    public void setListarImagenProyecto(List<ImagenProyectoDTO> listarImagenProyecto) {
        this.listarImagenProyecto = listarImagenProyecto;
    }
    //</editor-fold>
}
