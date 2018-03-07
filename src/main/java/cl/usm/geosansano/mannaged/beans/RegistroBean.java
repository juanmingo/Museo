/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.geosansano.mannaged.beans;

import cl.usm.geosansano.comparators.SedeNameComparator;
import cl.usm.geosansano.entity.CarreraImparte;
import cl.usm.geosansano.entity.MuseoUsuario;
import cl.usm.geosansano.entity.MuseoUsuarioCarrera;
import cl.usm.geosansano.entity.MuseoUsuarioCarreraPK;
import cl.usm.geosansano.entity.Sede;
import cl.usm.geosansano.functions.FuncionFecha;
import cl.usm.geosansano.sessions.beans.CarreraImparteFacadeLocal;
import cl.usm.geosansano.sessions.beans.SedeFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

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
    private String nombres;
    private String paterno;
    private String materno;
    private CarreraImparte carrera;
    private String pass;
    private String confirmPass;
    private String numDocu;
    private String correo;
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
        Long id = (long) 0;
        MuseoUsuario nuevoUser = new MuseoUsuario(id);
        nuevoUser.setMususuNombres(nombres);
        nuevoUser.setMususuPaterno(paterno);
        nuevoUser.setMususuMaterno(materno);
        nuevoUser.setContraseña(pass);
        MuseoUsuarioCarrera carreraUser = new MuseoUsuarioCarrera(
                new MuseoUsuarioCarreraPK(id,
                        carreraImparteSelected.getCarreraImpartePK().getCodSedeImparte(),
                        carreraImparteSelected.getCarreraImpartePK().getCodCarrera()
                ));
        
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

}
