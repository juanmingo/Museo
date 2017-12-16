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
 * @author Juan Delgado
 */
@Embeddable
public class CarreraImpartePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_sede_carrera")
    private int codSedeCarrera;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_carrera")
    private int codCarrera;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_sede_imparte")
    private int codSedeImparte;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_mencion")
    private int codMencion;

    public CarreraImpartePK() {
    }

    public CarreraImpartePK(int codSedeCarrera, int codCarrera, int codSedeImparte, int codMencion) {
        this.codSedeCarrera = codSedeCarrera;
        this.codCarrera = codCarrera;
        this.codSedeImparte = codSedeImparte;
        this.codMencion = codMencion;
    }

    public int getCodSedeCarrera() {
        return codSedeCarrera;
    }

    public void setCodSedeCarrera(int codSedeCarrera) {
        this.codSedeCarrera = codSedeCarrera;
    }

    public int getCodCarrera() {
        return codCarrera;
    }

    public void setCodCarrera(int codCarrera) {
        this.codCarrera = codCarrera;
    }

    public int getCodSedeImparte() {
        return codSedeImparte;
    }

    public void setCodSedeImparte(int codSedeImparte) {
        this.codSedeImparte = codSedeImparte;
    }

    public int getCodMencion() {
        return codMencion;
    }

    public void setCodMencion(int codMencion) {
        this.codMencion = codMencion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codSedeCarrera;
        hash += (int) codCarrera;
        hash += (int) codSedeImparte;
        hash += (int) codMencion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CarreraImpartePK)) {
            return false;
        }
        CarreraImpartePK other = (CarreraImpartePK) object;
        if (this.codSedeCarrera != other.codSedeCarrera) {
            return false;
        }
        if (this.codCarrera != other.codCarrera) {
            return false;
        }
        if (this.codSedeImparte != other.codSedeImparte) {
            return false;
        }
        if (this.codMencion != other.codMencion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.usm.geosansano.entity.CarreraImpartePK[ codSedeCarrera=" + codSedeCarrera + ", codCarrera=" + codCarrera + ", codSedeImparte=" + codSedeImparte + ", codMencion=" + codMencion + " ]";
    }
    
}
