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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan Delgado
 */
@Entity
@Table(name = "museo_proyecto_detalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MuseoProyectoDetalle.findAll", query = "SELECT m FROM MuseoProyectoDetalle m")
    , @NamedQuery(name = "MuseoProyectoDetalle.findByMusprodetId", query = "SELECT m FROM MuseoProyectoDetalle m WHERE m.museoProyectoDetallePK.musprodetId = :musprodetId")
    , @NamedQuery(name = "MuseoProyectoDetalle.findByMusproId", query = "SELECT m FROM MuseoProyectoDetalle m WHERE m.museoProyectoDetallePK.musproId = :musproId")
    , @NamedQuery(name = "MuseoProyectoDetalle.findByMusprodetNombre", query = "SELECT m FROM MuseoProyectoDetalle m WHERE m.musprodetNombre = :musprodetNombre")
    , @NamedQuery(name = "MuseoProyectoDetalle.findByMusprodetDescripcion", query = "SELECT m FROM MuseoProyectoDetalle m WHERE m.musprodetDescripcion = :musprodetDescripcion")
    , @NamedQuery(name = "MuseoProyectoDetalle.findByMususuIdUsu", query = "SELECT m FROM MuseoProyectoDetalle m WHERE m.mususuIdUsu = :mususuIdUsu")
    , @NamedQuery(name = "MuseoProyectoDetalle.findByFechaModificacion", query = "SELECT m FROM MuseoProyectoDetalle m WHERE m.fechaModificacion = :fechaModificacion")

    , @NamedQuery(name = "MuseoProyectoDetalle.findByDetalleActivo", query = "SELECT m FROM MuseoProyectoDetalle m  WHERE m.museoProyectoDetallePK.musproId = :musproId AND m.codVigencia.codVigencia IN (2) ")
})
public class MuseoProyectoDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MuseoProyectoDetallePK museoProyectoDetallePK;
    @Lob
    @Column(name = "musprodet_archivo")
    private byte[] musprodetArchivo;
    @Size(max = 1000)
    @Column(name = "musprodet_nombre")
    private String musprodetNombre;
    @Size(max = 4000)
    @Column(name = "musprodet_descripcion")
    private String musprodetDescripcion;
    @Column(name = "mususu_id_usu")
    private BigInteger mususuIdUsu;
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;
    @JoinColumn(name = "muspro_id", referencedColumnName = "muspro_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MuseoProyecto museoProyecto;
    @JoinColumn(name = "cod_vigencia", referencedColumnName = "cod_vigencia")
    @ManyToOne(optional = false)
    private TipoVigencia codVigencia;

    public MuseoProyectoDetalle() {
    }

    public MuseoProyectoDetalle(MuseoProyectoDetallePK museoProyectoDetallePK) {
        this.museoProyectoDetallePK = museoProyectoDetallePK;
    }

    public MuseoProyectoDetalle(long musprodetId, long musproId) {
        this.museoProyectoDetallePK = new MuseoProyectoDetallePK(musprodetId, musproId);
    }

    public MuseoProyectoDetallePK getMuseoProyectoDetallePK() {
        return museoProyectoDetallePK;
    }

    public void setMuseoProyectoDetallePK(MuseoProyectoDetallePK museoProyectoDetallePK) {
        this.museoProyectoDetallePK = museoProyectoDetallePK;
    }

    public byte[] getMusprodetArchivo() {
        return musprodetArchivo;
    }

    public void setMusprodetArchivo(byte[] musprodetArchivo) {
        this.musprodetArchivo = musprodetArchivo;
    }

    public String getMusprodetNombre() {
        return musprodetNombre;
    }

    public void setMusprodetNombre(String musprodetNombre) {
        this.musprodetNombre = musprodetNombre;
    }

    public String getMusprodetDescripcion() {
        return musprodetDescripcion;
    }

    public void setMusprodetDescripcion(String musprodetDescripcion) {
        this.musprodetDescripcion = musprodetDescripcion;
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

    public MuseoProyecto getMuseoProyecto() {
        return museoProyecto;
    }

    public void setMuseoProyecto(MuseoProyecto museoProyecto) {
        this.museoProyecto = museoProyecto;
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
        hash += (museoProyectoDetallePK != null ? museoProyectoDetallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuseoProyectoDetalle)) {
            return false;
        }
        MuseoProyectoDetalle other = (MuseoProyectoDetalle) object;
        if ((this.museoProyectoDetallePK == null && other.museoProyectoDetallePK != null) || (this.museoProyectoDetallePK != null && !this.museoProyectoDetallePK.equals(other.museoProyectoDetallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.usm.geosansano.entity.MuseoProyectoDetalle[ museoProyectoDetallePK=" + museoProyectoDetallePK + " ]";
    }

}
