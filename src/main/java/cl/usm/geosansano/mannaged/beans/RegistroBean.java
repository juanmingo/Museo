/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.geosansano.mannaged.beans;

import cl.usm.geosansano.entity.CarreraSede;
import cl.usm.geosansano.entity.MuseoUsuarioCarrera;
import cl.usm.geosansano.entity.Pais;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Alex
 */
@Named(value = "registroBean")
@ViewScoped
public class RegistroBean implements Serializable {

    private String nombre;
    private String paterno;
    private String materno;
    private Pais pais;
    private Date fechaNacimiento;
    private String correo;
    private String fono;
    private Integer rol;
    private String dvRol;
    private List<CarreraSede> carreras;
    private List<MuseoUsuarioCarrera> carreraSeleted;

    /**
     * Creates a new instance of RegistroBean
     */
    public RegistroBean() {
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
     * @return the pais
     */
    public Pais getPais() {
        return pais;
    }

    /**
     * @param pais the pais to set
     */
    public void setPais(Pais pais) {
        this.pais = pais;
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
     * @return the carreras
     */
    public List<CarreraSede> getCarreras() {
        return carreras;
    }

    /**
     * @param carreras the carreras to set
     */
    public void setCarreras(List<CarreraSede> carreras) {
        this.carreras = carreras;
    }

    /**
     * @return the carreraSeleted
     */
    public List<MuseoUsuarioCarrera> getCarreraSeleted() {
        return carreraSeleted;
    }

    /**
     * @param carreraSeleted the carreraSeleted to set
     */
    public void setCarreraSeleted(List<MuseoUsuarioCarrera> carreraSeleted) {
        this.carreraSeleted = carreraSeleted;
    }

}
