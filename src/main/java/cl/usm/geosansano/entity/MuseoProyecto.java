/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.geosansano.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table; 
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Juan Delgado
 */
@Entity
@Table(name = "museo_proyecto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MuseoProyecto.findAll", query = "SELECT m FROM MuseoProyecto m")
    , @NamedQuery(name = "MuseoProyecto.findByMusproId", query = "SELECT m FROM MuseoProyecto m WHERE m.musproId = :musproId")
    , @NamedQuery(name = "MuseoProyecto.findByMusproNombre", query = "SELECT m FROM MuseoProyecto m WHERE m.musproNombre = :musproNombre")
    , @NamedQuery(name = "MuseoProyecto.findByMusproDescripcion", query = "SELECT m FROM MuseoProyecto m WHERE m.musproDescripcion = :musproDescripcion")
    , @NamedQuery(name = "MuseoProyecto.findByMusproA�o", query = "SELECT m FROM MuseoProyecto m WHERE m.musproA�o = :musproA�o")
    , @NamedQuery(name = "MuseoProyecto.findByMususuIdUsu", query = "SELECT m FROM MuseoProyecto m WHERE m.mususuIdUsu = :mususuIdUsu")
    , @NamedQuery(name = "MuseoProyecto.findByFechaModificacion", query = "SELECT m FROM MuseoProyecto m WHERE m.fechaModificacion = :fechaModificacion")})
public class MuseoProyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "muspro_id")
    private Long musproId;
    @Size(max = 1000)
    @Column(name = "muspro_nombre")
    private String musproNombre;
    @Size(max = 4000)
    @Column(name = "muspro_descripcion")
    private String musproDescripcion;
    @Column(name = "muspro_a�o")
    private Integer musproA�o;
    @Column(name = "mususu_id_usu")
    private BigInteger mususuIdUsu;
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;
    @JoinColumn(name = "cod_pais", referencedColumnName = "cod_pais")
    @ManyToOne(optional = false)
    private Pais codPais;
    @JoinColumn(name = "cod_vigencia", referencedColumnName = "cod_vigencia")
    @ManyToOne(optional = false)
    private TipoVigencia codVigencia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "museoProyecto")
    private List<MuseoProyectoDetalle> museoProyectoDetalleList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "museoProyecto")
    private List<MuseoProyectoCoordenada> museoProyectoCoordenadaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "museoProyecto")
    private List<MuseoUsuarioProyecto> museoUsuarioProyectoList;

    public MuseoProyecto() {
    }

    public MuseoProyecto(Long musproId) {
        this.musproId = musproId;
    }

    public Long getMusproId() {
        return musproId;
    }

    public void setMusproId(Long musproId) {
        this.musproId = musproId;
    }

    public String getMusproNombre() {
        return musproNombre;
    }

    public void setMusproNombre(String musproNombre) {
        this.musproNombre = musproNombre;
    }

    public String getMusproDescripcion() {
        return musproDescripcion;
    }

    public void setMusproDescripcion(String musproDescripcion) {
        this.musproDescripcion = musproDescripcion;
    }

    public Integer getMusproA�o() {
        return musproA�o;
    }

    public void setMusproA�o(Integer musproA�o) {
        this.musproA�o = musproA�o;
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

    public Pais getCodPais() {
        return codPais;
    }

    public void setCodPais(Pais codPais) {
        this.codPais = codPais;
    }

    public TipoVigencia getCodVigencia() {
        return codVigencia;
    }

    public void setCodVigencia(TipoVigencia codVigencia) {
        this.codVigencia = codVigencia;
    }

    @XmlTransient
    public List<MuseoProyectoDetalle> getMuseoProyectoDetalleList() {
        return museoProyectoDetalleList;
    }

    public void setMuseoProyectoDetalleList(List<MuseoProyectoDetalle> museoProyectoDetalleList) {
        this.museoProyectoDetalleList = museoProyectoDetalleList;
    }

    @XmlTransient
    public List<MuseoProyectoCoordenada> getMuseoProyectoCoordenadaList() {
        return museoProyectoCoordenadaList;
    }

    public void setMuseoProyectoCoordenadaList(List<MuseoProyectoCoordenada> museoProyectoCoordenadaList) {
        this.museoProyectoCoordenadaList = museoProyectoCoordenadaList;
    }

    @XmlTransient
    public List<MuseoUsuarioProyecto> getMuseoUsuarioProyectoList() {
        return museoUsuarioProyectoList;
    }

    public void setMuseoUsuarioProyectoList(List<MuseoUsuarioProyecto> museoUsuarioProyectoList) {
        this.museoUsuarioProyectoList = museoUsuarioProyectoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (musproId != null ? musproId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuseoProyecto)) {
            return false;
        }
        MuseoProyecto other = (MuseoProyecto) object;
        if ((this.musproId == null && other.musproId != null) || (this.musproId != null && !this.musproId.equals(other.musproId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.usm.geosansano.entity.MuseoProyecto[ musproId=" + musproId + " ]";
    }
    
}
