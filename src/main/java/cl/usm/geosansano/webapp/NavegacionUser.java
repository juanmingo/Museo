package cl.usm.geosansano.webapp;

//<editor-fold defaultstate="collapsed" desc="Imports">
import cl.usm.geosansano.correo.EnviarCorreoGmail;
import cl.usm.geosansano.entity.MuseoUsuario;
import cl.usm.geosansano.functions.FuncionCorreo;
import cl.usm.geosansano.functions.FuncionFecha;
import cl.usm.geosansano.functions.FuncionMD5;
import cl.usm.geosansano.functions.FuncionTexto;
import cl.usm.geosansano.sessions.beans.MuseoUsuarioFacadeLocal;
import cl.usm.geosansano.sistema.Common;
import cl.usm.geosansano.sistema.Pagina;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
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
    private String cuentaContraseña = "";
    private String mensajeErrorLogin = "";
    private Boolean editar = false;
    

    public void limpiarVariables() {
        this.cuentaUsuario = "";
        this.cuentaContraseña = "";
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

        //this.cuentaUsuario = "juan.delgado2@usm.cl";
        this.cuentaUsuario = "juan.delgado@usm.cl";
        this.cuentaContraseña = "juan";

        if (!"".equals(FuncionTexto.nvlTexto(this.cuentaUsuario, "")) && !"".equals(FuncionTexto.nvlTexto(this.cuentaContraseña, ""))) {

            if (FuncionCorreo.validarCorreo(this.cuentaUsuario)) {

                this.setMuseoUsuario(this.museoUsuarioFacade.findByCuenta(this.cuentaUsuario, FuncionMD5.obtenerHash(this.cuentaContraseña)));

                if (this.getMuseoUsuario() == null) {

                    this.mensajeErrorLogin = "¡Usuario y/o Contraseña Incorrectos!";

                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Acceso Usuario", this.mensajeErrorLogin);
                    FacesContext.getCurrentInstance().addMessage(null, msg);

                } else {

                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cuentaUsuario", this.cuentaUsuario);
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("codPerfil", this.getMuseoUsuario().getCodPerfil());
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("mususuId", this.getMuseoUsuario().getMususuId());
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("isLoggedIn", "yes");
                    this.mensajeErrorLogin = "";
                    Common.redireccionar(Pagina.PAGINA_MENU_CARGAR_DATOS_SANSANO_MAPA);

                }

            } else {
                this.mensajeErrorLogin = "¡Formato de Correo Incorrecto!";

                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Acceso Usuario", this.mensajeErrorLogin);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }

        } else {
            this.mensajeErrorLogin = "¡Ingrese Usuario y Contraseña!";

            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Acceso Usuario", this.mensajeErrorLogin);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

        this.cuentaUsuario = "";
        this.cuentaContraseña = "";
        context.update("formLogin:msjLogin");
        context.update("formLogin:msjError");
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

    public void registrar() {
        Common.redireccionar(Pagina.PAGINA_REGISTRO);
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

    public void mostrarEditPerfil() {
        editar = !editar;
    }

    public String getFechaFormat(Date fecha) {
        return FuncionFecha.fechaStrFormat(fecha);
    }

    //<editor-fold defaultstate="collapsed" desc="Getter && Setter">
    public String getCuentaUsuario() {
        return cuentaUsuario;
    }

    public void setCuentaUsuario(String cuentaUsuario) {
        this.cuentaUsuario = cuentaUsuario;
    }

    public String getCuentaContraseña() {
        return cuentaContraseña;
    }

    public void setCuentaContraseña(String cuentaContraseña) {
        this.cuentaContraseña = cuentaContraseña;
    }

    public String getMensajeErrorLogin() {
        return mensajeErrorLogin;
    }

    public void setMensajeErrorLogin(String mensajeErrorLogin) {
        this.mensajeErrorLogin = mensajeErrorLogin;
    }
//</editor-fold>

    /**
     * @return the museoUsuario
     */
    public MuseoUsuario getMuseoUsuario() {
        return museoUsuario;
    }

    /**
     * @param museoUsuario the museoUsuario to set
     */
    public void setMuseoUsuario(MuseoUsuario museoUsuario) {
        this.museoUsuario = museoUsuario;
    }

    /**
     * @return the editar
     */
    public Boolean getEditar() {
        return editar;
    }

    /**
     * @param editar the editar to set
     */
    public void setEditar(Boolean editar) {
        this.editar = editar;
    }
}
