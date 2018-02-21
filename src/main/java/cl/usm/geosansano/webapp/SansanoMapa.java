package cl.usm.geosansano.webapp;

//<editor-fold defaultstate="collapsed" desc="Imports">
import cl.usm.geosansano.entity.MuseoUsuario;
import cl.usm.geosansano.functions.FuncionMD5;
import cl.usm.geosansano.functions.FuncionTexto;
import cl.usm.geosansano.sessions.beans.MuseoUsuarioFacadeLocal;
import cl.usm.geosansano.sistema.Common;
import cl.usm.geosansano.sistema.Pagina;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;
//</editor-fold>

/**
 *
 * @author Juan D. Delgado Robles.
 */
@ManagedBean(name = "sansanoMapa", eager = true)
@SessionScoped
public class SansanoMapa implements Serializable {

    @EJB
    private MuseoUsuarioFacadeLocal museoUsuarioFacade;
    //
    private MuseoUsuario museoUsuario;
    //
    private String nombreUsuario = "";

    public void cargarCuentaUsuario() {
        RequestContext context = RequestContext.getCurrentInstance();
        System.out.println("INI cargarCuentaUsuario");

        System.out.println("obtenerCuentaUsuario: " + Common.obtenerCuentaUsuario());

        //this.museoUsuario = this.museoUsuarioFacade.findByCuenta("juan.delgado@usm.cl", "A94652AA97C7211BA8954DD15A3CF838");
        this.museoUsuario = this.museoUsuarioFacade.findByCorreo(Common.obtenerCuentaUsuario());

        if (this.museoUsuario != null) {
            this.nombreUsuario = FuncionTexto.nvlTexto(this.museoUsuario.getMususuNombres(), "") + " " + FuncionTexto.nvlTexto(this.museoUsuario.getMususuPaterno(), "") + " " + FuncionTexto.nvlTexto(this.museoUsuario.getMususuMaterno(), "");
            this.nombreUsuario = this.nombreUsuario.trim();
            this.nombreUsuario = this.nombreUsuario.toUpperCase();
        }
        //

        System.out.println("this.museoUsuario: " + this.museoUsuario);

        Common.redireccionar(Pagina.PAGINA_MENU_SANSANO_MAPA);
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

}
