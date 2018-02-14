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
 * @author Juan
 */
@Entity
@Table(name = "museo_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MuseoUsuario.findAll", query = "SELECT m FROM MuseoUsuario m")
    , @NamedQuery(name = "MuseoUsuario.findByMususuId", query = "SELECT m FROM MuseoUsuario m WHERE m.mususuId = :mususuId")
    , @NamedQuery(name = "MuseoUsuario.findByMususuNombres", query = "SELECT m FROM MuseoUsuario m WHERE m.mususuNombres = :mususuNombres")
    , @NamedQuery(name = "MuseoUsuario.findByMususuPaterno", query = "SELECT m FROM MuseoUsuario m WHERE m.mususuPaterno = :mususuPaterno")
    , @NamedQuery(name = "MuseoUsuario.findByMususuMaterno", query = "SELECT m FROM MuseoUsuario m WHERE m.mususuMaterno = :mususuMaterno")
    , @NamedQuery(name = "MuseoUsuario.findByMususuFechaNac", query = "SELECT m FROM MuseoUsuario m WHERE m.mususuFechaNac = :mususuFechaNac")
    , @NamedQuery(name = "MuseoUsuario.findByCorreo", query = "SELECT m FROM MuseoUsuario m WHERE m.correo = :correo")
    , @NamedQuery(name = "MuseoUsuario.findByMususuFono", query = "SELECT m FROM MuseoUsuario m WHERE m.mususuFono = :mususuFono")
    , @NamedQuery(name = "MuseoUsuario.findByMususuIngreso", query = "SELECT m FROM MuseoUsuario m WHERE m.mususuIngreso = :mususuIngreso")
    , @NamedQuery(name = "MuseoUsuario.findByMususuRol", query = "SELECT m FROM MuseoUsuario m WHERE m.mususuRol = :mususuRol")
    , @NamedQuery(name = "MuseoUsuario.findByMususuRolDv", query = "SELECT m FROM MuseoUsuario m WHERE m.mususuRolDv = :mususuRolDv")
    , @NamedQuery(name = "MuseoUsuario.findByMususuIdUsu", query = "SELECT m FROM MuseoUsuario m WHERE m.mususuIdUsu = :mususuIdUsu")
    , @NamedQuery(name = "MuseoUsuario.findByFechaModificacion", query = "SELECT m FROM MuseoUsuario m WHERE m.fechaModificacion = :fechaModificacion")

    //Busqueda Cuenta
    , @NamedQuery(name = "MuseoUsuario.findByCuenta", query = "SELECT m FROM MuseoUsuario m WHERE m.correo = :correo AND m.contrase\u00f1a = :contrase\u00f1a")
})
public class MuseoUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "mususu_id")
    private Long mususuId;
    @Size(max = 100)
    @Column(name = "mususu_nombres")
    private String mususuNombres;
    @Size(max = 50)
    @Column(name = "mususu_paterno")
    private String mususuPaterno;
    @Size(max = 50)
    @Column(name = "mususu_materno")
    private String mususuMaterno;
    @Column(name = "mususu_fecha_nac")
    @Temporal(TemporalType.TIMESTAMP)
    private Date mususuFechaNac;
    @Size(max = 200)
    @Column(name = "correo")
    private String correo;
    @Size(max = 200)
    @Column(name = "contrase\u00f1a")
    private String contraseña;
    @Size(max = 20)
    @Column(name = "mususu_fono")
    private String mususuFono;
    @Column(name = "mususu_ingreso")
    private Integer mususuIngreso;
    @Column(name = "mususu_rol")
    private BigInteger mususuRol;
    @Column(name = "mususu_rol_dv")
    private Short mususuRolDv;
    @Column(name = "mususu_id_usu")
    private BigInteger mususuIdUsu;
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;
    @JoinColumn(name = "cod_pais", referencedColumnName = "cod_pais")
    @ManyToOne
    private Pais codPais;
    @JoinColumn(name = "cod_perfil", referencedColumnName = "cod_perfil")
    @ManyToOne(optional = false)
    private TipoPerfil codPerfil;
    @JoinColumn(name = "cod_revision", referencedColumnName = "cod_revision")
    @ManyToOne(optional = false)
    private TipoRevision codRevision;
    @JoinColumn(name = "cod_vigencia", referencedColumnName = "cod_vigencia")
    @ManyToOne(optional = false)
    private TipoVigencia codVigencia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "museoUsuario")
    private List<MuseoUsuarioFoto> museoUsuarioFotoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "museoUsuario")
    private List<MuseoUsuarioCarrera> museoUsuarioCarreraList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "museoUsuario")
    private List<MuseoUsuarioProyecto> museoUsuarioProyectoList;

    public MuseoUsuario() {
    }

    public MuseoUsuario(Long mususuId) {
        this.mususuId = mususuId;
    }

    public Long getMususuId() {
        return mususuId;
    }

    public void setMususuId(Long mususuId) {
        this.mususuId = mususuId;
    }

    public String getMususuNombres() {
        return mususuNombres;
    }

    public void setMususuNombres(String mususuNombres) {
        this.mususuNombres = mususuNombres;
    }

    public String getMususuPaterno() {
        return mususuPaterno;
    }

    public void setMususuPaterno(String mususuPaterno) {
        this.mususuPaterno = mususuPaterno;
    }

    public String getMususuMaterno() {
        return mususuMaterno;
    }

    public void setMususuMaterno(String mususuMaterno) {
        this.mususuMaterno = mususuMaterno;
    }

    public Date getMususuFechaNac() {
        return mususuFechaNac;
    }

    public void setMususuFechaNac(Date mususuFechaNac) {
        this.mususuFechaNac = mususuFechaNac;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getMususuFono() {
        return mususuFono;
    }

    public void setMususuFono(String mususuFono) {
        this.mususuFono = mususuFono;
    }

    public Integer getMususuIngreso() {
        return mususuIngreso;
    }

    public void setMususuIngreso(Integer mususuIngreso) {
        this.mususuIngreso = mususuIngreso;
    }

    public BigInteger getMususuRol() {
        return mususuRol;
    }

    public void setMususuRol(BigInteger mususuRol) {
        this.mususuRol = mususuRol;
    }

    public Short getMususuRolDv() {
        return mususuRolDv;
    }

    public void setMususuRolDv(Short mususuRolDv) {
        this.mususuRolDv = mususuRolDv;
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

    public TipoPerfil getCodPerfil() {
        return codPerfil;
    }

    public void setCodPerfil(TipoPerfil codPerfil) {
        this.codPerfil = codPerfil;
    }

    public TipoRevision getCodRevision() {
        return codRevision;
    }

    public void setCodRevision(TipoRevision codRevision) {
        this.codRevision = codRevision;
    }

    public TipoVigencia getCodVigencia() {
        return codVigencia;
    }

    public void setCodVigencia(TipoVigencia codVigencia) {
        this.codVigencia = codVigencia;
    }

    @XmlTransient
    public List<MuseoUsuarioFoto> getMuseoUsuarioFotoList() {
        return museoUsuarioFotoList;
    }

    public void setMuseoUsuarioFotoList(List<MuseoUsuarioFoto> museoUsuarioFotoList) {
        this.museoUsuarioFotoList = museoUsuarioFotoList;
    }

    @XmlTransient
    public List<MuseoUsuarioCarrera> getMuseoUsuarioCarreraList() {
        return museoUsuarioCarreraList;
    }

    public void setMuseoUsuarioCarreraList(List<MuseoUsuarioCarrera> museoUsuarioCarreraList) {
        this.museoUsuarioCarreraList = museoUsuarioCarreraList;
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
        hash += (mususuId != null ? mususuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MuseoUsuario)) {
            return false;
        }
        MuseoUsuario other = (MuseoUsuario) object;
        if ((this.mususuId == null && other.mususuId != null) || (this.mususuId != null && !this.mususuId.equals(other.mususuId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.usm.geosansano.entity.MuseoUsuario[ mususuId=" + mususuId + " ]";
    }

}
