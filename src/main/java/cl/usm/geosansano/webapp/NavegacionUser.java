package cl.usm.geosansano.webapp;

//<editor-fold defaultstate="collapsed" desc="Imports">
import cl.usm.geosansano.entity.MuseoUsuario;
import cl.usm.geosansano.functions.FuncionMD5;
import cl.usm.geosansano.sessions.beans.MuseoUsuarioFacadeLocal;
import cl.usm.geosansano.sistema.Common;
import cl.usm.geosansano.sistema.Pagina;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
//</editor-fold>

/**
 *
 * @author Juan D. Delgado Robles.
 */
@ManagedBean(name = "navegacionUser", eager = true)
@SessionScoped
public class NavegacionUser implements Serializable {

    @EJB
    private MuseoUsuarioFacadeLocal museoUsuarioFacade;
    //
    private MuseoUsuario museoUsuario;
    //
    private String cuentaUsuario = "";
    private String cuentaContrase�a = "";
    private String mensajeErrorLogin = "";

    public void validarUsuario() {
        RequestContext context = RequestContext.getCurrentInstance();

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

        System.out.println("INI validarUsuario");

        System.out.println("cuentaUsuario: " + this.cuentaUsuario);
        System.out.println("cuentaContrase�a: " + FuncionMD5.obtenerHash(this.cuentaContrase�a));

        //this.museoUsuario = this.museoUsuarioFacade.findByCuenta("juan.delgado@usm.cl", "A94652AA97C7211BA8954DD15A3CF838");
        this.museoUsuario = this.museoUsuarioFacade.findByCuenta(this.cuentaUsuario, FuncionMD5.obtenerHash(this.cuentaContrase�a));

        System.out.println("this.museoUsuario: " + this.museoUsuario);

        if (this.museoUsuario == null) {
            this.mensajeErrorLogin = "�Usuario y/o Contrase�a Incorrectos!";

            context.update("formCuenta:dlgLoginUsuario");
            context.execute("PF('dlgLoginUsuario').show()");
        } else {
            System.out.println("*cuentaUsuario: " + this.cuentaUsuario);
            
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cuentaUsuario", this.cuentaUsuario);

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cuentaUsuario", this.cuentaUsuario);
            this.mensajeErrorLogin = "";
            Common.redireccionar(Pagina.PAGINA_MENU_CARGAR_DATOS_SANSANO_MAPA);
        }

        this.cuentaUsuario = "";
        this.cuentaContrase�a = "";
    }

    public void salir() {
        String isLoggedIn = Common.obtenerIsLoggedIn();
        if (isLoggedIn.equals("yes")) {
            Common.eliminarSession();
            Common.redireccionar(Pagina.PAGINA_INDEX);
        } else {
            Common.redireccionarExterior(Pagina.PAGINA_INDEX);
        }
    }

    public void validarSession() {
        //System.out.println("validarSession: " + Common.isLoggedIn());
        if (!Common.isLoggedIn()) {
            //System.out.println("validarSession: SI Redireccion");
            Common.redireccionar(Pagina.PAGINA_INDEX);
        } else {
            //System.out.println("validarSession: NO Redireccion");
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Getter && Setter">
    public String getCuentaUsuario() {
        return cuentaUsuario;
    }

    public void setCuentaUsuario(String cuentaUsuario) {
        this.cuentaUsuario = cuentaUsuario;
    }

    public String getCuentaContrase�a() {
        return cuentaContrase�a;
    }

    public void setCuentaContrase�a(String cuentaContrase�a) {
        this.cuentaContrase�a = cuentaContrase�a;
    }

    public String getMensajeErrorLogin() {
        return mensajeErrorLogin;
    }

    public void setMensajeErrorLogin(String mensajeErrorLogin) {
        this.mensajeErrorLogin = mensajeErrorLogin;
    }
//</editor-fold>
}
