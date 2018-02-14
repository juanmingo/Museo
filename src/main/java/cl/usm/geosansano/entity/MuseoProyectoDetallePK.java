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
public class MuseoProyectoDetallePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "musprodet_id")
    private long musprodetId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "muspro_id")
    private long musproId;

    public MuseoProyectoDetallePK() {
    }

    public MuseoProyectoDetallePK(long musprodetId, long musproId) {
        this.musprodetId = musprodetId;
        this.musproId = musproId;
    }

    public long getMusprodetId() {
        return musprodetId;
    }

    public void setMusprodetId(long musprodetId) {
        this.musprodetId = musprodetId;
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
        hash += (int) musprodetId;
        hash += (int) musproId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuseoProyectoDetallePK)) {
            return false;
        }
        MuseoProyectoDetallePK other = (MuseoProyectoDetallePK) object;
        if (this.musprodetId != other.musprodetId) {
            return false;
        }
        if (this.musproId != other.musproId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.usm.geosansano.entity.MuseoProyectoDetallePK[ musprodetId=" + musprodetId + ", musproId=" + musproId + " ]";
    }
    
}
