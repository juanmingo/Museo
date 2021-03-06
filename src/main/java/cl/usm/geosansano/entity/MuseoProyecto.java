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
    , @NamedQuery(name = "MuseoProyecto.findByMaxMusproId", query = "SELECT m FROM MuseoProyecto m WHERE m.musproId = (SELECT MAX(m2.musproId) FROM MuseoProyecto m2)")
    , @NamedQuery(name = "MuseoProyecto.findByMusproId", query = "SELECT m FROM MuseoProyecto m WHERE m.musproId = :musproId")
    , @NamedQuery(name = "MuseoProyecto.findByMususuId", query = "SELECT m FROM MuseoProyecto m WHERE m.mususuId = :mususuId")
    , @NamedQuery(name = "MuseoProyecto.findByMusproNombre", query = "SELECT m FROM MuseoProyecto m WHERE m.musproNombre = :musproNombre")
    , @NamedQuery(name = "MuseoProyecto.findByMusproDescripcion", query = "SELECT m FROM MuseoProyecto m WHERE m.musproDescripcion = :musproDescripcion")
    , @NamedQuery(name = "MuseoProyecto.findByMusproCiudad", query = "SELECT m FROM MuseoProyecto m WHERE m.musproCiudad = :musproCiudad")
    , @NamedQuery(name = "MuseoProyecto.findByMusproLatitud", query = "SELECT m FROM MuseoProyecto m WHERE m.musproLatitud = :musproLatitud")
    , @NamedQuery(name = "MuseoProyecto.findByMusproLongitud", query = "SELECT m FROM MuseoProyecto m WHERE m.musproLongitud = :musproLongitud")
    , @NamedQuery(name = "MuseoProyecto.findByMusproA\u00f1o", query = "SELECT m FROM MuseoProyecto m WHERE m.musproA\u00f1o = :musproA\u00f1o")
    , @NamedQuery(name = "MuseoProyecto.findByMususuIdUsu", query = "SELECT m FROM MuseoProyecto m WHERE m.mususuIdUsu = :mususuIdUsu")
    , @NamedQuery(name = "MuseoProyecto.findByFechaModificacion", query = "SELECT m FROM MuseoProyecto m WHERE m.fechaModificacion = :fechaModificacion")

    , @NamedQuery(name = "MuseoProyecto.findByProyectoUsuario", query = "SELECT m FROM MuseoProyecto m WHERE (m.mususuId = :mususuId) "
            + " OR m.musproId IN (0,1,2,3,4,5) ")

    , @NamedQuery(name = "MuseoProyecto.findByProyectosUsuarioPendienteRechazado", query = "SELECT m FROM MuseoProyecto m WHERE m.mususuId = :mususuId AND (m.musproLatitud <= :norteLatitud AND m.musproLatitud >= :surteLatitud) and (m.musproLongitud <= :norteLongitud and m.musproLongitud >= :surLongitud) "
            + " AND m.codVigencia.codVigencia IN (0,1,3) ")

    , @NamedQuery(name = "MuseoProyecto.findByProyectosGeo", query = "SELECT m FROM MuseoProyecto m "
            + " WHERE (m.musproLatitud <= :norteLatitud AND m.musproLatitud >= :surteLatitud) "
            + " AND (m.musproLongitud <= :norteLongitud AND m.musproLongitud >= :surLongitud) "
            + " AND m.codVigencia.codVigencia = 2 ")

    , @NamedQuery(name = "MuseoProyecto.findByProyectosGeo2", query = "SELECT m FROM MuseoProyecto m "
            + " WHERE ((m.musproLatitud <= :norteLatitud AND m.musproLatitud >= :surteLatitud) "
            + " OR (m.musproLongitud >= :norteLongitud AND m.musproLongitud <= :surLongitud) ) "
            + " AND m.codVigencia.codVigencia = 2 ")

    , @NamedQuery(name = "MuseoProyecto.findByProyectosGeo3", query = "SELECT m FROM MuseoProyecto m "
            + " WHERE ((m.musproLatitud >= :norteLatitud1 AND m.musproLatitud >= :surteLatitud2) "
            + " OR (m.musproLatitud >= :norteLatitud2 AND m.musproLatitud >= :surteLatitud3)) "
            + " AND ((m.musproLongitud >= :norteLongitud1 AND m.musproLongitud >= :surLongitud2) "
            + " OR (m.musproLongitud >= :norteLongitud3 AND m.musproLongitud >= :surLongitud4))")

})

/*

SELECT *
FROM museo_proyecto
WHERE ((30.234375 >= muspro_longitud  AND muspro_longitud >= -88.41796875)
OR (152.9296875 >= muspro_longitud  AND muspro_longitud >= -88.41796875))
AND 
((-16.896498660939496 >= muspro_latitud  AND muspro_latitud >= -60.61156692242454)
OR(26.818569600545544 >= muspro_latitud  AND muspro_latitud >= -16.896498660939496))

NorthEast
Lat:20.81933027601954, Lng:33.771358848328646
SouthWest
Lat:-63.45956894832444, Lng:156.81823384832865

NorthEast
Lat:3.1295050881201534, Lng:-21.072391151671354
SouthWest
Lat:-43.858326480235675, Lng:-139.54895365167135

 */
public class MuseoProyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "muspro_id")
    private Long musproId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mususu_id")
    private long mususuId;
    @Size(max = 1000)
    @Column(name = "muspro_nombre")
    private String musproNombre;
    @Size(max = 4000)
    @Column(name = "muspro_descripcion")
    private String musproDescripcion;
    @Size(max = 1000)
    @Column(name = "muspro_ciudad")
    private String musproCiudad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "muspro_latitud")
    private Double musproLatitud;
    @Column(name = "muspro_longitud")
    private Double musproLongitud;
    @Column(name = "muspro_a\u00f1o")
    private Integer musproAņo;
    @Column(name = "mususu_id_usu")
    private BigInteger mususuIdUsu;
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "museoProyecto")
    private List<MuseoProyectoDetalle> museoProyectoDetalleList;
    @JoinColumn(name = "cod_pais", referencedColumnName = "cod_pais")
    @ManyToOne(optional = false)
    private Pais codPais;
    @JoinColumn(name = "cod_vigencia", referencedColumnName = "cod_vigencia")
    @ManyToOne(optional = false)
    private TipoVigencia codVigencia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "museoProyecto")
    private List<MuseoUsuarioProyecto> museoUsuarioProyectoList;

    public MuseoProyecto() {
    }

    public MuseoProyecto(Long musproId) {
        this.musproId = musproId;
    }

    public MuseoProyecto(Long musproId, long mususuId) {
        this.musproId = musproId;
        this.mususuId = mususuId;
    }

    public Long getMusproId() {
        return musproId;
    }

    public void setMusproId(Long musproId) {
        this.musproId = musproId;
    }

    public long getMususuId() {
        return mususuId;
    }

    public void setMususuId(long mususuId) {
        this.mususuId = mususuId;
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

    public String getMusproCiudad() {
        return musproCiudad;
    }

    public void setMusproCiudad(String musproCiudad) {
        this.musproCiudad = musproCiudad;
    }

    public Double getMusproLatitud() {
        return musproLatitud;
    }

    public void setMusproLatitud(Double musproLatitud) {
        this.musproLatitud = musproLatitud;
    }

    public Double getMusproLongitud() {
        return musproLongitud;
    }

    public void setMusproLongitud(Double musproLongitud) {
        this.musproLongitud = musproLongitud;
    }

    public Integer getMusproAņo() {
        return musproAņo;
    }

    public void setMusproAņo(Integer musproAņo) {
        this.musproAņo = musproAņo;
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

    @XmlTransient
    public List<MuseoProyectoDetalle> getMuseoProyectoDetalleList() {
        return museoProyectoDetalleList;
    }

    public void setMuseoProyectoDetalleList(List<MuseoProyectoDetalle> museoProyectoDetalleList) {
        this.museoProyectoDetalleList = museoProyectoDetalleList;
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
