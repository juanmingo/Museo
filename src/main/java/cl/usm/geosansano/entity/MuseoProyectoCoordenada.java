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
@Table(name = "museo_proyecto_coordenada")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MuseoProyectoCoordenada.findAll", query = "SELECT m FROM MuseoProyectoCoordenada m")
    , @NamedQuery(name = "MuseoProyectoCoordenada.findByMusprocoorId", query = "SELECT m FROM MuseoProyectoCoordenada m WHERE m.museoProyectoCoordenadaPK.musprocoorId = :musprocoorId")
    , @NamedQuery(name = "MuseoProyectoCoordenada.findByMusproId", query = "SELECT m FROM MuseoProyectoCoordenada m WHERE m.museoProyectoCoordenadaPK.musproId = :musproId")
    , @NamedQuery(name = "MuseoProyectoCoordenada.findByMusprocoorCiudad", query = "SELECT m FROM MuseoProyectoCoordenada m WHERE m.musprocoorCiudad = :musprocoorCiudad")
    , @NamedQuery(name = "MuseoProyectoCoordenada.findByMusprocoorNombre", query = "SELECT m FROM MuseoProyectoCoordenada m WHERE m.musprocoorNombre = :musprocoorNombre")
    , @NamedQuery(name = "MuseoProyectoCoordenada.findByMusprocoorLatitud", query = "SELECT m FROM MuseoProyectoCoordenada m WHERE m.musprocoorLatitud = :musprocoorLatitud")
    , @NamedQuery(name = "MuseoProyectoCoordenada.findByMusprocoorLongitud", query = "SELECT m FROM MuseoProyectoCoordenada m WHERE m.musprocoorLongitud = :musprocoorLongitud")
    , @NamedQuery(name = "MuseoProyectoCoordenada.findByMususuIdUsu", query = "SELECT m FROM MuseoProyectoCoordenada m WHERE m.mususuIdUsu = :mususuIdUsu")
    , @NamedQuery(name = "MuseoProyectoCoordenada.findByFechaModificacion", query = "SELECT m FROM MuseoProyectoCoordenada m WHERE m.fechaModificacion = :fechaModificacion")})
public class MuseoProyectoCoordenada implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MuseoProyectoCoordenadaPK museoProyectoCoordenadaPK;
    @Size(max = 1000)
    @Column(name = "musprocoor_ciudad")
    private String musprocoorCiudad;
    @Size(max = 1000)
    @Column(name = "musprocoor_nombre")
    private String musprocoorNombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "musprocoor_latitud")
    private Double musprocoorLatitud;
    @Column(name = "musprocoor_longitud")
    private Double musprocoorLongitud;
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

    public MuseoProyectoCoordenada() {
    }

    public MuseoProyectoCoordenada(MuseoProyectoCoordenadaPK museoProyectoCoordenadaPK) {
        this.museoProyectoCoordenadaPK = museoProyectoCoordenadaPK;
    }

    public MuseoProyectoCoordenada(long musprocoorId, long musproId) {
        this.museoProyectoCoordenadaPK = new MuseoProyectoCoordenadaPK(musprocoorId, musproId);
    }

    public MuseoProyectoCoordenadaPK getMuseoProyectoCoordenadaPK() {
        return museoProyectoCoordenadaPK;
    }

    public void setMuseoProyectoCoordenadaPK(MuseoProyectoCoordenadaPK museoProyectoCoordenadaPK) {
        this.museoProyectoCoordenadaPK = museoProyectoCoordenadaPK;
    }

    public String getMusprocoorCiudad() {
        return musprocoorCiudad;
    }

    public void setMusprocoorCiudad(String musprocoorCiudad) {
        this.musprocoorCiudad = musprocoorCiudad;
    }

    public String getMusprocoorNombre() {
        return musprocoorNombre;
    }

    public void setMusprocoorNombre(String musprocoorNombre) {
        this.musprocoorNombre = musprocoorNombre;
    }

    public Double getMusprocoorLatitud() {
        return musprocoorLatitud;
    }

    public void setMusprocoorLatitud(Double musprocoorLatitud) {
        this.musprocoorLatitud = musprocoorLatitud;
    }

    public Double getMusprocoorLongitud() {
        return musprocoorLongitud;
    }

    public void setMusprocoorLongitud(Double musprocoorLongitud) {
        this.musprocoorLongitud = musprocoorLongitud;
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
        hash += (museoProyectoCoordenadaPK != null ? museoProyectoCoordenadaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuseoProyectoCoordenada)) {
            return false;
        }
        MuseoProyectoCoordenada other = (MuseoProyectoCoordenada) object;
        if ((this.museoProyectoCoordenadaPK == null && other.museoProyectoCoordenadaPK != null) || (this.museoProyectoCoordenadaPK != null && !this.museoProyectoCoordenadaPK.equals(other.museoProyectoCoordenadaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.usm.geosansano.entity.MuseoProyectoCoordenada[ museoProyectoCoordenadaPK=" + museoProyectoCoordenadaPK + " ]";
    }
    
}
