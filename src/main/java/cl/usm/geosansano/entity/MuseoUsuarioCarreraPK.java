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
public class MuseoUsuarioCarreraPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "mususu_id")
    private long mususuId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sed_cod_sede")
    private int sedCodSede;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_carrera")
    private int codCarrera;

    public MuseoUsuarioCarreraPK() {
    }

    public MuseoUsuarioCarreraPK(long mususuId, int sedCodSede, int codCarrera) {
        this.mususuId = mususuId;
        this.sedCodSede = sedCodSede;
        this.codCarrera = codCarrera;
    }

    public long getMususuId() {
        return mususuId;
    }

    public void setMususuId(long mususuId) {
        this.mususuId = mususuId;
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
        hash += (int) mususuId;
        hash += (int) sedCodSede;
        hash += (int) codCarrera;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuseoUsuarioCarreraPK)) {
            return false;
        }
        MuseoUsuarioCarreraPK other = (MuseoUsuarioCarreraPK) object;
        if (this.mususuId != other.mususuId) {
            return false;
        }
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
        return "cl.usm.geosansano.entity.MuseoUsuarioCarreraPK[ mususuId=" + mususuId + ", sedCodSede=" + sedCodSede + ", codCarrera=" + codCarrera + " ]";
    }
    
}
