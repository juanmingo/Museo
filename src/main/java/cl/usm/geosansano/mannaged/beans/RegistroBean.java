/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.geosansano.mannaged.beans;

import cl.usm.geosansano.comparators.SedeNameComparator;
import cl.usm.geosansano.correo.EnviarCorreoGmail;
import cl.usm.geosansano.entity.CarreraImparte;
import cl.usm.geosansano.entity.MuseoUsuario;
import cl.usm.geosansano.entity.Sede;
import cl.usm.geosansano.entity.TipoPerfil;
import cl.usm.geosansano.entity.TipoRevision;
import cl.usm.geosansano.entity.TipoVigencia;
import cl.usm.geosansano.functions.FuncionEncriptado;
import cl.usm.geosansano.functions.FuncionFecha;
import cl.usm.geosansano.functions.FuncionMD5;
import cl.usm.geosansano.sessions.beans.CarreraImparteFacadeLocal;
import cl.usm.geosansano.sessions.beans.MuseoUsuarioFacadeLocal;
import cl.usm.geosansano.sessions.beans.SedeFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Alex
 */
@Named(value = "registroBean")
@ViewScoped
public class RegistroBean implements Serializable {

    @EJB
    private SedeFacadeLocal sedeFacade;
    @EJB
    private CarreraImparteFacadeLocal carreraImparteFacade;
    @EJB
    private MuseoUsuarioFacadeLocal museoUsuarioFacade;
    private String nombres;
    private String paterno;
    private String materno;
    private CarreraImparte carrera;
    private String pass;
    private String confirmPass;
    private String numDocu;
    private String correo;
    private String correoConfirm;
    private boolean rut;
    private List<Sede> sedes;
    private Sede sedeSelected;
    private List<CarreraImparte> carrerasImparte;
    private CarreraImparte carreraImparteSelected;
    private List<Integer> anios;
    private Integer anioSelected;
    private String rutPasaporte;

    @PostConstruct
    public void init() {
        System.out.println("init registroBean");

        setSedes(sedeFacade.findAll());
        setSedes(getSedes().stream().filter((sede) -> sede.getSedCodSede() >= 1 && sede.getSedCodSede() <= 5 || sede.getSedCodSede() == 15).collect(Collectors.toList()));
        Collections.sort(getSedes(), new SedeNameComparator());
        anios = new ArrayList<>();
        anioSelected = FuncionFecha.anioIntFormat(FuncionFecha.hoy());
        for (int i = anioSelected; i >= 1960; i--) {
            anios.add(i);
        }
    }

    public void changueSede() {
        System.out.println("changueSede");
        carrerasImparte = carreraImparteFacade.findBy("CarreraImparte.findByCodSedeImparte", "codSedeImparte", sedeSelected.getSedCodSede());
        System.out.println("changueSede carrerasImparte.size(): " + carrerasImparte.size());
    }

    public void registrar() {
        System.out.println("****** registrar");
        Long id = museoUsuarioFacade.querySimple("MuseoUsuario.maxMususuId") + 1;
        MuseoUsuario nuevoUser = new MuseoUsuario(id);
        nuevoUser.setMususuNombres(nombres);
        nuevoUser.setMususuPaterno(paterno);
        nuevoUser.setMususuMaterno(materno);
        nuevoUser.setContraseña(FuncionMD5.obtenerHash(pass));
        nuevoUser.setCorreo(correo);
        nuevoUser.setCodVigencia(new TipoVigencia(0));
        nuevoUser.setCodRevision(new TipoRevision(0));
        nuevoUser.setCodPerfil(new TipoPerfil(2));
        nuevoUser.setFechaModificacion(FuncionFecha.hoy());

        museoUsuarioFacade.create(nuevoUser);
        enviarCorreoConfirm(correo);
        pass = null;
        confirmPass = null;
        nombres = null;
        paterno = null;
        materno = null;
        correo = null;
        correoConfirm = null;

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Registrado con éxito.", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void enviarCorreoConfirm(String destinatario) {
        String nombrePersonalFrom = "Registro Geo UTFSM - Sansanos por el Mundo";
        destinatario = "r.alexander.riquelme@gmail.com";
        //String copia = "juan.delgado@usm.cl";
        String asunto = "Prueba Desarrollo USM";
        HttpServletRequest origRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = origRequest.getRequestURL().substring(0, origRequest.getRequestURL().indexOf(FacesContext.getCurrentInstance().getExternalContext().getRequestServletPath()));
        FuncionEncriptado f = new FuncionEncriptado();
        try {
            f.makeKey();
            url = url + "/validacionUsuario.jsf?c=" + f.encrypt("textoEncriptado");
        } catch (Exception ex) {
            Logger.getLogger(RegistroBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        String mensaje = "Alo!! Probando!! D: <br/>"
                + "<a href=\"" + url + "\">Validar Cuenta</a>";

        EnviarCorreoGmail objEnviarCorreoGmail = new EnviarCorreoGmail();
        objEnviarCorreoGmail.correo(nombrePersonalFrom, destinatario, null, asunto, mensaje);
    }

    /**
     * @return the nombres
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * @param nombres the nombres to set
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
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
     * @return the carrera
     */
    public CarreraImparte getCarrera() {
        return carrera;
    }

    /**
     * @param carrera the carrera to set
     */
    public void setCarrera(CarreraImparte carrera) {
        this.carrera = carrera;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * @return the confirmPass
     */
    public String getConfirmPass() {
        return confirmPass;
    }

    /**
     * @param confirmPass the confirmPass to set
     */
    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }

    /**
     * @return the numDocu
     */
    public String getNumDocu() {
        return numDocu;
    }

    /**
     * @param numDocu the numDocu to set
     */
    public void setNumDocu(String numDocu) {
        this.numDocu = numDocu;
    }

    /**
     * @return the rut
     */
    public boolean isRut() {
        return rut;
    }

    /**
     * @param rut the rut to set
     */
    public void setRut(boolean rut) {
        this.rut = rut;
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
     * @return the sedeSelected
     */
    public Sede getSedeSelected() {
        return sedeSelected;
    }

    /**
     * @param sedeSelected the sedeSelected to set
     */
    public void setSedeSelected(Sede sedeSelected) {
        this.sedeSelected = sedeSelected;
    }

    /**
     * @return the carrerasImparte
     */
    public List<CarreraImparte> getCarrerasImparte() {
        return carrerasImparte;
    }

    /**
     * @param carrerasImparte the carrerasImparte to set
     */
    public void setCarrerasImparte(List<CarreraImparte> carrerasImparte) {
        this.carrerasImparte = carrerasImparte;
    }

    /**
     * @return the carreraImparteSelected
     */
    public CarreraImparte getCarreraImparteSelected() {
        return carreraImparteSelected;
    }

    /**
     * @param carreraImparteSelected the carreraImparteSelected to set
     */
    public void setCarreraImparteSelected(CarreraImparte carreraImparteSelected) {
        this.carreraImparteSelected = carreraImparteSelected;
    }

    /**
     * @return the anios
     */
    public List<Integer> getAnios() {
        return anios;
    }

    /**
     * @param anios the anios to set
     */
    public void setAnios(List<Integer> anios) {
        this.anios = anios;
    }

    /**
     * @return the anioSelected
     */
    public Integer getAnioSelected() {
        return anioSelected;
    }

    /**
     * @param anioSelected the anioSelected to set
     */
    public void setAnioSelected(Integer anioSelected) {
        this.anioSelected = anioSelected;
    }

    /**
     * @return the rutPasaporte
     */
    public String getRutPasaporte() {
        return rutPasaporte;
    }

    /**
     * @param rutPasaporte the rutPasaporte to set
     */
    public void setRutPasaporte(String rutPasaporte) {
        this.rutPasaporte = rutPasaporte;
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
     * @return the correoConfirm
     */
    public String getCorreoConfirm() {
        return correoConfirm;
    }

    /**
     * @param correoConfirm the correoConfirm to set
     */
    public void setCorreoConfirm(String correoConfirm) {
        this.correoConfirm = correoConfirm;
    }

}
