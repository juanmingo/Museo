package cl.usm.geosansano.webapp;

//<editor-fold defaultstate="collapsed" desc="Imports">
import cl.usm.geosansano.correo.EnviarCorreoGmail;
import cl.usm.geosansano.entity.CarreraImparte;
import cl.usm.geosansano.entity.CarreraSede;
import cl.usm.geosansano.entity.MuseoUsuario;
import cl.usm.geosansano.entity.MuseoUsuarioCarrera;
import cl.usm.geosansano.entity.MuseoUsuarioCarreraPK;
import cl.usm.geosansano.entity.Pais;
import cl.usm.geosansano.entity.Sede;
import cl.usm.geosansano.functions.FuncionCorreo;
import cl.usm.geosansano.functions.FuncionFecha;
import cl.usm.geosansano.functions.FuncionMD5;
import cl.usm.geosansano.functions.FuncionTexto;
import cl.usm.geosansano.sessions.beans.CarreraImparteFacadeLocal;
import cl.usm.geosansano.sessions.beans.MuseoUsuarioCarreraFacadeLocal;
import cl.usm.geosansano.sessions.beans.MuseoUsuarioFacadeLocal;
import cl.usm.geosansano.sessions.beans.PaisFacadeLocal;
import cl.usm.geosansano.sessions.beans.SedeFacadeLocal;
import cl.usm.geosansano.sistema.Common;
import cl.usm.geosansano.sistema.Pagina;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
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
    private MuseoUsuarioCarreraFacadeLocal museoUsuarioCarreraFacade;
    @EJB
    private PaisFacadeLocal paisFacade;
    @EJB
    private CarreraImparteFacadeLocal carreraImparteFacade;
    @EJB
    private SedeFacadeLocal sedeFacade;

    //
    private MuseoUsuario museoUsuario;
    //
    private String cuentaUsuario = "";
    private String cuentaContrase�a = "";
    private String mensajeErrorLogin = "";
    private Boolean editar = false;
    private Boolean addCarrera = false;
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
    private List<Sede> sedes;
    private List<CarreraImparte> carreras;
    private Sede sedeSelect;
    private CarreraImparte carreraSelect;
    private List<Integer> a�os;
    private Integer ingreso;
    private MuseoUsuarioCarrera elimCarr;

    public void limpiarVariables() {
        this.cuentaUsuario = "";
        this.cuentaContrase�a = "";
        this.mensajeErrorLogin = "";
        this.setPaises(this.paisFacade.findAll());
        this.setSedes(sedeFacade.findAll());
        Calendar cal = Calendar.getInstance();

        setIngreso((Integer) cal.get(Calendar.YEAR));
        a�os = new ArrayList<>();
        for (int i = getIngreso(); i > 1925; i--) {
            System.out.println("i: " + i);
            a�os.add(i);
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formCuenta:pnLoginUsuario");
        context.update("formCuenta:msjError");
    }

    public void cargaCarreras() {
        carreras = carreraImparteFacade.findBy("CarreraImparte.findByCodSedeImparte", "codSedeImparte", sedeSelect.getSedCodSede());

    }

    public void eliminar() {

        this.museoUsuario.getMuseoUsuarioCarreraList().remove(elimCarr);
        this.museoUsuarioFacade.edit(museoUsuario);
        museoUsuario = this.museoUsuarioFacade.find(museoUsuario.getMususuId());
        museoUsuarioCarreraFacade.remove(elimCarr);
        

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

    public void addCarrera() {

        if (carreraSelect != null && sedeSelect != null && ingreso != null) {

            MuseoUsuarioCarreraPK mucpk = new MuseoUsuarioCarreraPK(museoUsuario.getMususuId(), carreraSelect.getCarreraImpartePK().getCodSedeImparte(), carreraSelect.getCarreraImpartePK().getCodCarrera());

            MuseoUsuarioCarrera listMuc = museoUsuarioCarreraFacade.find(mucpk);
            if (listMuc != null) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El usuario ya contiene esta carrera.", "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return;
            }
            MuseoUsuarioCarrera muc = new MuseoUsuarioCarrera(mucpk);
            muc.setFechaModificacion(new Date());
            muc.setMususuCarrera(carreraSelect.getCarreraSede().getNomCarrera());
            muc.setSedCodSedeFisica(sedeSelect);
            muc.setMususuIngreso(ingreso);
            museoUsuarioCarreraFacade.create(muc);
            museoUsuario.getMuseoUsuarioCarreraList().add(muc);
            museoUsuarioFacade.edit(museoUsuario);
            museoUsuario = museoUsuarioFacade.find(museoUsuario.getMususuId());
            carreraSelect = null;
            sedeSelect = null;
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar Sede, Carrera e Ingreso.", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
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

    public void mostrarAddCarrera() {

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

    /**
     * @return the sedes
     */
    public List<Sede> getSedes() {
        return sedes;
    }

    /**
     * @param sedes the sedes to set
     */
    public void setSedes(List<Sede> sedes) {
        this.sedes = sedes;
    }

    /**
     * @return the sedeSelect
     */
    public Sede getSedeSelect() {
        return sedeSelect;
    }

    /**
     * @param sedeSelect the sedeSelect to set
     */
    public void setSedeSelect(Sede sedeSelect) {
        this.sedeSelect = sedeSelect;
    }

    /**
     * @return the carreraSelect
     */
    public CarreraImparte getCarreraSelect() {
        return carreraSelect;
    }

    /**
     * @param carreraSelect the carreraSelect to set
     */
    public void setCarreraSelect(CarreraImparte carreraSelect) {
        this.carreraSelect = carreraSelect;
    }

    /**
     * @return the addCarrera
     */
    public Boolean getAddCarrera() {
        return addCarrera;
    }

    /**
     * @param addCarrera the addCarrera to set
     */
    public void setAddCarrera(Boolean addCarrera) {
        this.addCarrera = addCarrera;
    }

    /**
     * @return the carreras
     */
    public List<CarreraImparte> getCarreras() {
        return carreras;
    }

    /**
     * @param carreras the carreras to set
     */
    public void setCarreras(List<CarreraImparte> carreras) {
        this.carreras = carreras;
    }

    /**
     * @return the a�os
     */
    public List<Integer> getA�os() {
        return a�os;
    }

    /**
     * @param a�os the a�os to set
     */
    public void setA�os(List<Integer> a�os) {
        this.a�os = a�os;
    }

    /**
     * @return the ingreso
     */
    public Integer getIngreso() {
        return ingreso;
    }

    /**
     * @param ingreso the ingreso to set
     */
    public void setIngreso(Integer ingreso) {
        this.ingreso = ingreso;
    }

    /**
     * @return the elimCarr
     */
    public MuseoUsuarioCarrera getElimCarr() {
        return elimCarr;
    }

    /**
     * @param elimCarr the elimCarr to set
     */
    public void setElimCarr(MuseoUsuarioCarrera elimCarr) {
        this.elimCarr = elimCarr;
    }
}
