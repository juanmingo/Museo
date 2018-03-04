/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.geosansano.mannaged.beans;

import cl.usm.geosansano.comparators.PaisNameComparator;
import cl.usm.geosansano.comparators.SedeNameComparator;
import cl.usm.geosansano.entity.CarreraImparte;
import cl.usm.geosansano.entity.MuseoUsuarioCarrera;
import cl.usm.geosansano.entity.Pais;
import cl.usm.geosansano.entity.Sede;
import cl.usm.geosansano.functions.FuncionFecha;
import cl.usm.geosansano.sessions.beans.CarreraImparteFacadeLocal;
import cl.usm.geosansano.sessions.beans.PaisFacadeLocal;
import cl.usm.geosansano.sessions.beans.SedeFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Alex
 */
@Named(value = "actualizaDatosBean")
@ViewScoped
public class ActualizaDatosBean implements Serializable {

    @EJB
    private PaisFacadeLocal paisFacade;
    @EJB
    private SedeFacadeLocal sedeFacade;
    @EJB
    private CarreraImparteFacadeLocal carreraImparteFacade;

    private String nombre;
    private String paterno;
    private String materno;
    private Date fechaNacimiento;
    private String correo;
    private String fono;
    private Integer rol;
    private String dvRol;

    private List<Pais> paises;
    private Pais paisSelected;
    private List<Sede> sedes;
    private Sede sedeSelected;
    private List<CarreraImparte> carrerasImparte;
    private CarreraImparte carreraImparteSelected;
    private List<MuseoUsuarioCarrera> museoUsuarioCarreras;
    private List<Integer> anios;
    private Integer anioSelected;

    /**
     * Creates a new instance of RegistroBean
     */
    public ActualizaDatosBean() {
    }

    @PostConstruct
    public void init() {
        System.out.println("init registroBean");
        paises = paisFacade.findAll();
        Collections.sort(paises, new PaisNameComparator());
        paises.remove(new Pais(0));
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

    public void addCarrera() {
        System.out.println("addCarrera");

        museoUsuarioCarreras = museoUsuarioCarreras == null ? new ArrayList<>() : museoUsuarioCarreras;
        if (carreraImparteSelected == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar una carrera.", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }
        MuseoUsuarioCarrera muc = new MuseoUsuarioCarrera(0, carreraImparteSelected.getCarreraSede().getCarreraSedePK().getCodCarrera(),
                carreraImparteSelected.getCarreraSede().getCarreraSedePK().getSedCodSede());
        muc.setFechaModificacion(new Date());
        muc.setMususuIngreso(anioSelected);
        muc.setSedCodSedeFisica(carreraImparteSelected.getSedCodSede());
        muc.setCarreraSede(carreraImparteSelected.getCarreraSede());
        if (museoUsuarioCarreras.contains(muc)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ya ha sido agregada esta carrera.", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        } else {
            museoUsuarioCarreras.add(muc);
        }
    }

    public void fechaStrFormat(Date fecha) {
        FuncionFecha.fechaStrFormat(fecha);
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
     * @return the fechaNacimiento
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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
     * @return the rol
     */
    public Integer getRol() {
        return rol;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(Integer rol) {
        this.rol = rol;
    }

    /**
     * @return the dvRol
     */
    public String getDvRol() {
        return dvRol;
    }

    /**
     * @param dvRol the dvRol to set
     */
    public void setDvRol(String dvRol) {
        this.dvRol = dvRol;
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
     * @return the paisSelected
     */
    public Pais getPaisSelected() {
        return paisSelected;
    }

    /**
     * @param paisSelected the paisSelected to set
     */
    public void setPaisSelected(Pais paisSelected) {
        this.paisSelected = paisSelected;
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
     * @return the museoUsuarioCarreras
     */
    public List<MuseoUsuarioCarrera> getMuseoUsuarioCarreras() {
        return museoUsuarioCarreras;
    }

    /**
     * @param museoUsuarioCarreras the museoUsuarioCarreras to set
     */
    public void setMuseoUsuarioCarreras(List<MuseoUsuarioCarrera> museoUsuarioCarreras) {
        this.museoUsuarioCarreras = museoUsuarioCarreras;
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

}
