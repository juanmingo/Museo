/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.geosansano.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Juan
 */
@Embeddable
public class CarreraSedePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "sed_cod_sede")
    private int sedCodSede;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_carrera")
    private int codCarrera;

    public CarreraSedePK() {
    }

    public CarreraSedePK(int sedCodSede, int codCarrera) {
        this.sedCodSede = sedCodSede;
        this.codCarrera = codCarrera;
    }

    public int getSedCodSede() {
        return sedCodSede;
    }

    public void setSedCodSede(int sedCodSede) {
        this.sedCodSede = sedCodSede;
    }

    public int getCodCarrera() {
        return codCarrera;
    }

    public void setCodCarrera(int codCarrera) {
        this.codCarrera = codCarrera;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) sedCodSede;
        hash += (int) codCarrera;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CarreraSedePK)) {
            return false;
        }
        CarreraSedePK other = (CarreraSedePK) object;
        if (this.sedCodSede != other.sedCodSede) {
            return false;
        }
        if (this.codCarrera != other.codCarrera) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.usm.geosansano.entity.CarreraSedePK[ sedCodSede=" + sedCodSede + ", codCarrera=" + codCarrera + " ]";
    }
    
}
