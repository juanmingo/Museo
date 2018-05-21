package cl.usm.geosansano.webapp;

//<editor-fold defaultstate="collapsed" desc="Imports">
import cl.usm.geosansano.correo.EnviarCorreoGmail;
import cl.usm.geosansano.entity.MuseoUsuario;
import cl.usm.geosansano.entity.Pais;
import cl.usm.geosansano.functions.FuncionCorreo;
import cl.usm.geosansano.functions.FuncionFecha;
import cl.usm.geosansano.functions.FuncionMD5;
import cl.usm.geosansano.functions.FuncionTexto;
import cl.usm.geosansano.sessions.beans.MuseoUsuarioFacadeLocal;
import cl.usm.geosansano.sessions.beans.PaisFacadeLocal;
import cl.usm.geosansano.sistema.Common;
import cl.usm.geosansano.sistema.Pagina;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
    @EJB
    private PaisFacadeLocal paisFacade;
    //
    private MuseoUsuario museoUsuario;
    //
    private String cuentaUsuario = "";
    private String cuentaContrase�a = "";
    private String mensajeErrorLogin = "";
    private Boolean editar = false;
    private List<Pais> paises;
    private String correo;
    private Integer pais;
    private Date nacimiento;
    private String fono;
    private String nombre;
    private String paterno;
    private String materno;
    private String contrase�a;
    private String newContrase�a;
    private String confirmNewContrase�a;

    public void limpiarVariables() {
        this.cuentaUsuario = "";
        this.cuentaContrase�a = "";
        this.mensajeErrorLogin = "";
        this.setPaises(this.paisFacade.findAll());
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
        this.cuentaContrase�a = "juan";

        if (!"".equals(FuncionTexto.nvlTexto(this.cuentaUsuario, "")) && !"".equals(FuncionTexto.nvlTexto(this.cuentaContrase�a, ""))) {

            if (FuncionCorreo.validarCorreo(this.cuentaUsuario)) {

                this.setMuseoUsuario(this.museoUsuarioFacade.findByCuenta(this.cuentaUsuario, FuncionMD5.obtenerHash(this.cuentaContrase�a)));

                if (this.getMuseoUsuario() == null) {

                    this.mensajeErrorLogin = "�Usuario y/o Contrase�a Incorrectos!";

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
                this.mensajeErrorLogin = "�Formato de Correo Incorrecto!";

                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Acceso Usuario", this.mensajeErrorLogin);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }

        } else {
            this.mensajeErrorLogin = "�Ingrese Usuario y Contrase�a!";

            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Acceso Usuario", this.mensajeErrorLogin);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

        this.cuentaUsuario = "";
        this.cuentaContrase�a = "";
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

    public void recuperarContrase�a() {
        Common.redireccionar(Pagina.PAGINA_RECUPERAR_CONTRASENA);
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
        this.correo = this.museoUsuario.getCorreo();
        this.pais = this.museoUsuario.getCodPais().getCodPais();
        this.nacimiento = this.museoUsuario.getMususuFechaNac();
        this.fono = this.museoUsuario.getMususuFono();
        this.nombre = this.museoUsuario.getMususuNombres();
        this.paterno = this.museoUsuario.getMususuPaterno();
        this.materno = this.museoUsuario.getMususuMaterno();
        this.contrase�a = "";
        this.newContrase�a = "";
        this.confirmNewContrase�a = "";

    }

    public void preGuardar() {
        System.out.println("newContrase�a: " + newContrase�a);
        if (this.newContrase�a == null || this.newContrase�a.trim().equals("")) {
            guardar();
        } else {

            RequestContext context = RequestContext.getCurrentInstance();

            context.execute("PF('dlgPass').show();");

        }
    }

    public void guardar() {

        if (this.newContrase�a != null && !this.newContrase�a.trim().equals("")
                && !FuncionMD5.obtenerHash(this.contrase�a).equals(this.museoUsuario.getContrase�a())) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Contrase�a actual Incorrecta", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }
        if (this.newContrase�a != null && !this.newContrase�a.trim().equals("") && !this.newContrase�a.equals(confirmNewContrase�a)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "las contrase�as nuevas no coinciden", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }
        if (this.newContrase�a != null && !this.newContrase�a.trim().equals("")) {
            this.museoUsuario.setContrase�a(FuncionMD5.obtenerHash(this.newContrase�a));
        }
        this.museoUsuario.setCorreo(correo);
        if (this.pais != null) {
            this.museoUsuario.setCodPais(paisFacade.find(this.pais));
        }
        this.museoUsuario.setMususuFechaNac(nacimiento);
        this.museoUsuario.setMususuFono(fono);
        this.museoUsuario.setMususuNombres(nombre);
        this.museoUsuario.setMususuPaterno(paterno);
        this.museoUsuario.setMususuMaterno(materno);
        this.museoUsuarioFacade.edit(museoUsuario);
        this.museoUsuario = this.museoUsuarioFacade.find(this.museoUsuario.getMususuId());
        this.openSidebar();

    }

    public void openSidebar() {
        this.editar = false;
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

    /**
     * @return the paises
     */
    public List<Pais> getPaises() {
        return paises;
    }

    /**
     * @param paises the paises to set
     */
    public void setPaises(List<Pais> paises) {
        this.paises = paises;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the pais
     */
    public Integer getPais() {
        return pais;
    }

    /**
     * @param pais the pais to set
     */
    public void setPais(Integer pais) {
        this.pais = pais;
    }

    /**
     * @return the nacimiento
     */
    public Date getNacimiento() {
        return nacimiento;
    }

    /**
     * @param nacimiento the nacimiento to set
     */
    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    /**
     * @return the fono
     */
    public String getFono() {
        return fono;
    }

    /**
     * @param fono the fono to set
     */
    public void setFono(String fono) {
        this.fono = fono;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the paterno
     */
    public String getPaterno() {
        return paterno;
    }

    /**
     * @param paterno the paterno to set
     */
    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    /**
     * @return the materno
     */
    public String getMaterno() {
        return materno;
    }

    /**
     * @param materno the materno to set
     */
    public void setMaterno(String materno) {
        this.materno = materno;
    }

    /**
     * @return the contrase�a
     */
    public String getContrase�a() {
        return contrase�a;
    }

    /**
     * @param contrase�a the contrase�a to set
     */
    public void setContrase�a(String contrase�a) {
        this.contrase�a = contrase�a;
    }

    /**
     * @return the newContrase�a
     */
    public String getNewContrase�a() {
        return newContrase�a;
    }

    /**
     * @param newContrase�a the newContrase�a to set
     */
    public void setNewContrase�a(String newContrase�a) {
        this.newContrase�a = newContrase�a;
    }

    /**
     * @return the confirmNewContrase�a
     */
    public String getConfirmNewContrase�a() {
        return confirmNewContrase�a;
    }

    /**
     * @param confirmNewContrase�a the confirmNewContrase�a to set
     */
    public void setConfirmNewContrase�a(String confirmNewContrase�a) {
        this.confirmNewContrase�a = confirmNewContrase�a;
    }
}
