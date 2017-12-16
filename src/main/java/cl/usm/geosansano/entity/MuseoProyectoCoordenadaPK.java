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
public class MuseoProyectoCoordenadaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "musprocoor_id")
    private long musprocoorId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "muspro_id")
    private long musproId;

    public MuseoProyectoCoordenadaPK() {
    }

    public MuseoProyectoCoordenadaPK(long musprocoorId, long musproId) {
        this.musprocoorId = musprocoorId;
        this.musproId = musproId;
    }

    public long getMusprocoorId() {
        return musprocoorId;
    }

    public void setMusprocoorId(long musprocoorId) {
        this.musprocoorId = musprocoorId;
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
        hash += (int) musprocoorId;
        hash += (int) musproId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuseoProyectoCoordenadaPK)) {
            return false;
        }
        MuseoProyectoCoordenadaPK other = (MuseoProyectoCoordenadaPK) object;
        if (this.musprocoorId != other.musprocoorId) {
            return false;
        }
        if (this.musproId != other.musproId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.usm.geosansano.entity.MuseoProyectoCoordenadaPK[ musprocoorId=" + musprocoorId + ", musproId=" + musproId + " ]";
    }
    
}
