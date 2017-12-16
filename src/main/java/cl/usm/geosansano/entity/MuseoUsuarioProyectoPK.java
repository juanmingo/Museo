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
public class MuseoUsuarioProyectoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "mususu_id")
    private long mususuId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "muspro_id")
    private long musproId;

    public MuseoUsuarioProyectoPK() {
    }

    public MuseoUsuarioProyectoPK(long mususuId, long musproId) {
        this.mususuId = mususuId;
        this.musproId = musproId;
    }

    public long getMususuId() {
        return mususuId;
    }

    public void setMususuId(long mususuId) {
        this.mususuId = mususuId;
    }

    public long getMusproId() {
        return musproId;
    }

    public void setMusproId(long musproId) {
        this.musproId = musproId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) mususuId;
        hash += (int) musproId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuseoUsuarioProyectoPK)) {
            return false;
        }
        MuseoUsuarioProyectoPK other = (MuseoUsuarioProyectoPK) object;
        if (this.mususuId != other.mususuId) {
            return false;
        }
        if (this.musproId != other.musproId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.usm.geosansano.entity.MuseoUsuarioProyectoPK[ mususuId=" + mususuId + ", musproId=" + musproId + " ]";
    }
    
}
