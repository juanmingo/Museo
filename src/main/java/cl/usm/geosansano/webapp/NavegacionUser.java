package cl.usm.geosansano.webapp;

//<editor-fold defaultstate="collapsed" desc="Imports">
import cl.usm.geosansano.entity.MuseoUsuario;
import cl.usm.geosansano.functions.FuncionCorreo;
import cl.usm.geosansano.functions.FuncionMD5;
import cl.usm.geosansano.functions.FuncionTexto;
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

    public void limpiarVariables() {
        this.cuentaUsuario = "";
        this.cuentaContrase�a = "";
        this.mensajeErrorLogin = "";
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formCuenta:pnLoginUsuario");
        context.update("formCuenta:msjError");
    }

    public void validarUsuario() {
        RequestContext context = RequestContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

        this.mensajeErrorLogin = "";

        this.cuentaUsuario = "juan.delgado@usm.cl";
        this.cuentaContrase�a = "juan";

        if (!"".equals(FuncionTexto.nvlTexto(this.cuentaUsuario, "")) && !"".equals(FuncionTexto.nvlTexto(this.cuentaContrase�a, ""))) {

            if (FuncionCorreo.validarCorreo(this.cuentaUsuario)) {

                this.museoUsuario = this.museoUsuarioFacade.findByCuenta(this.cuentaUsuario, FuncionMD5.obtenerHash(this.cuentaContrase�a));

                if (this.museoUsuario == null) {
                    this.mensajeErrorLogin = "�Usuario y/o Contrase�a Incorrectos!";
                } else {
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cuentaUsuario", this.cuentaUsuario);
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("codPerfil", this.museoUsuario.getCodPerfil());
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("isLoggedIn", "yes");
                    this.mensajeErrorLogin = "";
                    Common.redireccionar(Pagina.PAGINA_MENU_CARGAR_DATOS_SANSANO_MAPA);
                }

            } else {
                this.mensajeErrorLogin = "�Formato de Correo Incorrecto!";
            }

        } else {
            this.mensajeErrorLogin = "�Ingrese Usuario y Contrase�a!";
        }

        this.cuentaUsuario = "";
        this.cuentaContrase�a = "";
        context.update("formCuenta:pnLoginUsuario");
        context.update("formCuenta:msjError");
        context.execute("PF('dlgLoginUsuario').show()");
    }

    public void salir() {
        if (Common.isLoggedIn()) {
            Common.eliminarSession();
        }
        Common.redireccionar(Pagina.PAGINA_INDEX);
    }

    public void validarSession() {
        if (!Common.isLoggedIn()) {
            Common.redireccionar(Pagina.PAGINA_INDEX);
        }
    }

    public void redirecionarPagina(int codPagina) {
        System.out.println("codPagina: " + codPagina);
        switch (codPagina) {
            case 1://AGREGAR MARKER
                Common.redireccionar(Pagina.PAGINA_MENU_CARGAR_DATOS_AGREGAR_PROYECTO);
                break;
            case 2://PROYECTOS SANSANOS
                Common.redireccionar(Pagina.PAGINA_MENU_CARGAR_DATOS_SANSANO_MAPA);
                break;
            default:
                Common.redireccionar(Pagina.PAGINA_INDEX);
                break;
        }

        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlgLoading').hide()");
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
