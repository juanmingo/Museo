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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan Delgado
 */
@Entity
@Table(name = "museo_usuario_proyecto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MuseoUsuarioProyecto.findAll", query = "SELECT m FROM MuseoUsuarioProyecto m")
    , @NamedQuery(name = "MuseoUsuarioProyecto.findByMususuId", query = "SELECT m FROM MuseoUsuarioProyecto m WHERE m.museoUsuarioProyectoPK.mususuId = :mususuId")
    , @NamedQuery(name = "MuseoUsuarioProyecto.findByMusproId", query = "SELECT m FROM MuseoUsuarioProyecto m WHERE m.museoUsuarioProyectoPK.musproId = :musproId")
    , @NamedQuery(name = "MuseoUsuarioProyecto.findByMususuproCargo", query = "SELECT m FROM MuseoUsuarioProyecto m WHERE m.mususuproCargo = :mususuproCargo")
    , @NamedQuery(name = "MuseoUsuarioProyecto.findByFechaModificacion", query = "SELECT m FROM MuseoUsuarioProyecto m WHERE m.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "MuseoUsuarioProyecto.findByRutUsuario", query = "SELECT m FROM MuseoUsuarioProyecto m WHERE m.rutUsuario = :rutUsuario")})
public class MuseoUsuarioProyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MuseoUsuarioProyectoPK museoUsuarioProyectoPK;
    @Size(max = 1000)
    @Column(name = "mususupro_cargo")
    private String mususuproCargo;
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;
    @Column(name = "rut_usuario")
    private BigInteger rutUsuario;
    @JoinColumn(name = "muspro_id", referencedColumnName = "muspro_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MuseoProyecto museoProyecto;
    @JoinColumn(name = "mususu_id", referencedColumnName = "mususu_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MuseoUsuario museoUsuario;
    @JoinColumn(name = "cod_vigencia", referencedColumnName = "cod_vigencia")
    @ManyToOne(optional = false)
    private TipoVigencia codVigencia;

    public MuseoUsuarioProyecto() {
    }

    public MuseoUsuarioProyecto(MuseoUsuarioProyectoPK museoUsuarioProyectoPK) {
        this.museoUsuarioProyectoPK = museoUsuarioProyectoPK;
    }

    public MuseoUsuarioProyecto(long mususuId, long musproId) {
        this.museoUsuarioProyectoPK = new MuseoUsuarioProyectoPK(mususuId, musproId);
    }

    public MuseoUsuarioProyectoPK getMuseoUsuarioProyectoPK() {
        return museoUsuarioProyectoPK;
    }

    public void setMuseoUsuarioProyectoPK(MuseoUsuarioProyectoPK museoUsuarioProyectoPK) {
        this.museoUsuarioProyectoPK = museoUsuarioProyectoPK;
    }

    public String getMususuproCargo() {
        return mususuproCargo;
    }

    public void setMususuproCargo(String mususuproCargo) {
        this.mususuproCargo = mususuproCargo;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public BigInteger getRutUsuario() {
        return rutUsuario;
    }

    public void setRutUsuario(BigInteger rutUsuario) {
        this.rutUsuario = rutUsuario;
    }

    public MuseoProyecto getMuseoProyecto() {
        return museoProyecto;
    }

    public void setMuseoProyecto(MuseoProyecto museoProyecto) {
        this.museoProyecto = museoProyecto;
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
        hash += (museoUsuarioProyectoPK != null ? museoUsuarioProyectoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuseoUsuarioProyecto)) {
            return false;
        }
        MuseoUsuarioProyecto other = (MuseoUsuarioProyecto) object;
        if ((this.museoUsuarioProyectoPK == null && other.museoUsuarioProyectoPK != null) || (this.museoUsuarioProyectoPK != null && !this.museoUsuarioProyectoPK.equals(other.museoUsuarioProyectoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.usm.geosansano.entity.MuseoUsuarioProyecto[ museoUsuarioProyectoPK=" + museoUsuarioProyectoPK + " ]";
    }
    
}
