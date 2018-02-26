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
import javax.persistence.JoinColumns;
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
@Table(name = "museo_usuario_carrera")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MuseoUsuarioCarrera.findAll", query = "SELECT m FROM MuseoUsuarioCarrera m")
    , @NamedQuery(name = "MuseoUsuarioCarrera.findByMususuId", query = "SELECT m FROM MuseoUsuarioCarrera m WHERE m.museoUsuarioCarreraPK.mususuId = :mususuId")
    , @NamedQuery(name = "MuseoUsuarioCarrera.findBySedCodSede", query = "SELECT m FROM MuseoUsuarioCarrera m WHERE m.museoUsuarioCarreraPK.sedCodSede = :sedCodSede")
    , @NamedQuery(name = "MuseoUsuarioCarrera.findByCodCarrera", query = "SELECT m FROM MuseoUsuarioCarrera m WHERE m.museoUsuarioCarreraPK.codCarrera = :codCarrera")
    , @NamedQuery(name = "MuseoUsuarioCarrera.findByMususuCarrera", query = "SELECT m FROM MuseoUsuarioCarrera m WHERE m.mususuCarrera = :mususuCarrera")
    , @NamedQuery(name = "MuseoUsuarioCarrera.findByMususuIngreso", query = "SELECT m FROM MuseoUsuarioCarrera m WHERE m.mususuIngreso = :mususuIngreso")
    , @NamedQuery(name = "MuseoUsuarioCarrera.findByMususuIdUsu", query = "SELECT m FROM MuseoUsuarioCarrera m WHERE m.mususuIdUsu = :mususuIdUsu")
    , @NamedQuery(name = "MuseoUsuarioCarrera.findByFechaModificacion", query = "SELECT m FROM MuseoUsuarioCarrera m WHERE m.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "MuseoUsuarioCarrera.findMaxIdForUser", query = "SELECT  NULLIF(MAX(m.mususuIdUsu),0) FROM MuseoUsuarioCarrera m ")
})
public class MuseoUsuarioCarrera implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MuseoUsuarioCarreraPK museoUsuarioCarreraPK;
    @Size(max = 200)
    @Column(name = "mususu_carrera")
    private String mususuCarrera;
    @Column(name = "mususu_ingreso")
    private Integer mususuIngreso;
    @Column(name = "mususu_id_usu")
    private BigInteger mususuIdUsu;
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @JoinColumns({
        @JoinColumn(name = "sed_cod_sede", referencedColumnName = "sed_cod_sede", insertable = false, updatable = false)
        , @JoinColumn(name = "cod_carrera", referencedColumnName = "cod_carrera", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private CarreraSede carreraSede;
    @JoinColumn(name = "mususu_id", referencedColumnName = "mususu_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MuseoUsuario museoUsuario;
    @JoinColumn(name = "sed_cod_sede_fisica", referencedColumnName = "sed_cod_sede")
    @ManyToOne
    private Sede sedCodSedeFisica;

    public MuseoUsuarioCarrera() {
    }

    public MuseoUsuarioCarrera(MuseoUsuarioCarreraPK museoUsuarioCarreraPK) {
        this.museoUsuarioCarreraPK = museoUsuarioCarreraPK;
    }

    public MuseoUsuarioCarrera(long mususuId, int sedCodSede, int codCarrera) {
        this.museoUsuarioCarreraPK = new MuseoUsuarioCarreraPK(mususuId, sedCodSede, codCarrera);
    }

    public MuseoUsuarioCarreraPK getMuseoUsuarioCarreraPK() {
        return museoUsuarioCarreraPK;
    }

    public void setMuseoUsuarioCarreraPK(MuseoUsuarioCarreraPK museoUsuarioCarreraPK) {
        this.museoUsuarioCarreraPK = museoUsuarioCarreraPK;
    }

    public String getMususuCarrera() {
        return mususuCarrera;
    }

    public void setMususuCarrera(String mususuCarrera) {
        this.mususuCarrera = mususuCarrera;
    }

    public Integer getMususuIngreso() {
        return mususuIngreso;
    }

    public void setMususuIngreso(Integer mususuIngreso) {
        this.mususuIngreso = mususuIngreso;
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

    public CarreraSede getCarreraSede() {
        return carreraSede;
    }

    public void setCarreraSede(CarreraSede carreraSede) {
        this.carreraSede = carreraSede;
    }

    public MuseoUsuario getMuseoUsuario() {
        return museoUsuario;
    }

    public void setMuseoUsuario(MuseoUsuario museoUsuario) {
        this.museoUsuario = museoUsuario;
    }

    public Sede getSedCodSedeFisica() {
        return sedCodSedeFisica;
    }

    public void setSedCodSedeFisica(Sede sedCodSedeFisica) {
        this.sedCodSedeFisica = sedCodSedeFisica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (museoUsuarioCarreraPK != null ? museoUsuarioCarreraPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuseoUsuarioCarrera)) {
            return false;
        }
        MuseoUsuarioCarrera other = (MuseoUsuarioCarrera) object;
        if ((this.museoUsuarioCarreraPK == null && other.museoUsuarioCarreraPK != null) || (this.museoUsuarioCarreraPK != null && !this.museoUsuarioCarreraPK.equals(other.museoUsuarioCarreraPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.usm.geosansano.entity.MuseoUsuarioCarrera[ museoUsuarioCarreraPK=" + museoUsuarioCarreraPK + " ]";
    }

}
