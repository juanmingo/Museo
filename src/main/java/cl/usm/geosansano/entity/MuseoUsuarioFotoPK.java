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
public class MuseoUsuarioFotoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "mususufo_id")
    private long mususufoId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mususu_id")
    private long mususuId;

    public MuseoUsuarioFotoPK() {
    }

    public MuseoUsuarioFotoPK(long mususufoId, long mususuId) {
        this.mususufoId = mususufoId;
        this.mususuId = mususuId;
    }

    public long getMususufoId() {
        return mususufoId;
    }

    public void setMususufoId(long mususufoId) {
        this.mususufoId = mususufoId;
    }

    public long getMususuId() {
        return mususuId;
    }

    public void setMususuId(long mususuId) {
        this.mususuId = mususuId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) mususufoId;
        hash += (int) mususuId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuseoUsuarioFotoPK)) {
            return false;
        }
        MuseoUsuarioFotoPK other = (MuseoUsuarioFotoPK) object;
        if (this.mususufoId != other.mususufoId) {
            return false;
        }
        if (this.mususuId != other.mususuId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.usm.geosansano.entity.MuseoUsuarioFotoPK[ mususufoId=" + mususufoId + ", mususuId=" + mususuId + " ]";
    }
    
}
