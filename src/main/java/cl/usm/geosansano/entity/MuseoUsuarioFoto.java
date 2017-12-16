/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.geosansano.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan Delgado
 */
@Entity
@Table(name = "museo_usuario_foto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MuseoUsuarioFoto.findAll", query = "SELECT m FROM MuseoUsuarioFoto m")
    , @NamedQuery(name = "MuseoUsuarioFoto.findByMususufoId", query = "SELECT m FROM MuseoUsuarioFoto m WHERE m.museoUsuarioFotoPK.mususufoId = :mususufoId")
    , @NamedQuery(name = "MuseoUsuarioFoto.findByMususuId", query = "SELECT m FROM MuseoUsuarioFoto m WHERE m.museoUsuarioFotoPK.mususuId = :mususuId")
    , @NamedQuery(name = "MuseoUsuarioFoto.findByMususuIdUsu", query = "SELECT m FROM MuseoUsuarioFoto m WHERE m.mususuIdUsu = :mususuIdUsu")
    , @NamedQuery(name = "MuseoUsuarioFoto.findByFechaModificacion", query = "SELECT m FROM MuseoUsuarioFoto m WHERE m.fechaModificacion = :fechaModificacion")})
public class MuseoUsuarioFoto implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MuseoUsuarioFotoPK museoUsuarioFotoPK;
    @Lob
    @Column(name = "mususufo_archivo")
    private byte[] mususufoArchivo;
    @Column(name = "mususu_id_usu")
    private BigInteger mususuIdUsu;
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;
    @JoinColumn(name = "mususu_id", referencedColumnName = "mususu_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MuseoUsuario museoUsuario;
    @JoinColumn(name = "cod_vigencia", referencedColumnName = "cod_vigencia")
    @ManyToOne(optional = false)
    private TipoVigencia codVigencia;

    public MuseoUsuarioFoto() {
    }

    public MuseoUsuarioFoto(MuseoUsuarioFotoPK museoUsuarioFotoPK) {
        this.museoUsuarioFotoPK = museoUsuarioFotoPK;
    }

    public MuseoUsuarioFoto(long mususufoId, long mususuId) {
        this.museoUsuarioFotoPK = new MuseoUsuarioFotoPK(mususufoId, mususuId);
    }

    public MuseoUsuarioFotoPK getMuseoUsuarioFotoPK() {
        return museoUsuarioFotoPK;
    }

    public void setMuseoUsuarioFotoPK(MuseoUsuarioFotoPK museoUsuarioFotoPK) {
        this.museoUsuarioFotoPK = museoUsuarioFotoPK;
    }

    public byte[] getMususufoArchivo() {
        return mususufoArchivo;
    }

    public void setMususufoArchivo(byte[] mususufoArchivo) {
        this.mususufoArchivo = mususufoArchivo;
    }

    public BigInteger getMususuIdUsu() {
        return mususuIdUsu;
    }

    public void setMususuIdUsu(BigInteger mususuIdUsu) {
        this.mususuIdUsu = mususuIdUsu;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public MuseoUsuario getMuseoUsuario() {
        return museoUsuario;
    }

    public void setMuseoUsuario(MuseoUsuario museoUsuario) {
        this.museoUsuario = museoUsuario;
    }

    public TipoVigencia getCodVigencia() {
        return codVigencia;
    }

    public void setCodVigencia(TipoVigencia codVigencia) {
        this.codVigencia = codVigencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (museoUsuarioFotoPK != null ? museoUsuarioFotoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuseoUsuarioFoto)) {
            return false;
        }
        MuseoUsuarioFoto other = (MuseoUsuarioFoto) object;
        if ((this.museoUsuarioFotoPK == null && other.museoUsuarioFotoPK != null) || (this.museoUsuarioFotoPK != null && !this.museoUsuarioFotoPK.equals(other.museoUsuarioFotoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.usm.geosansano.entity.MuseoUsuarioFoto[ museoUsuarioFotoPK=" + museoUsuarioFotoPK + " ]";
    }
    
}
