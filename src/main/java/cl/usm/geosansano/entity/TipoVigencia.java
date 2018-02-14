/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.geosansano.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Juan
 */
@Entity
@Table(name = "tipo_vigencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoVigencia.findAll", query = "SELECT t FROM TipoVigencia t")
    , @NamedQuery(name = "TipoVigencia.findByCodVigencia", query = "SELECT t FROM TipoVigencia t WHERE t.codVigencia = :codVigencia")
    , @NamedQuery(name = "TipoVigencia.findByNomVigencia", query = "SELECT t FROM TipoVigencia t WHERE t.nomVigencia = :nomVigencia")})
public class TipoVigencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_vigencia")
    private Integer codVigencia;
    @Size(max = 50)
    @Column(name = "nom_vigencia")
    private String nomVigencia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codVigencia")
    private List<MuseoUsuario> museoUsuarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codVigencia")
    private List<MuseoUsuarioFoto> museoUsuarioFotoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codVigencia")
    private List<MuseoProyecto> museoProyectoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codVigencia")
    private List<MuseoProyectoDetalle> museoProyectoDetalleList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codVigencia")
    private List<MuseoProyectoCoordenada> museoProyectoCoordenadaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codVigencia")
    private List<MuseoUsuarioProyecto> museoUsuarioProyectoList;

    public TipoVigencia() {
    }

    public TipoVigencia(Integer codVigencia) {
        this.codVigencia = codVigencia;
    }

    public Integer getCodVigencia() {
        return codVigencia;
    }

    public void setCodVigencia(Integer codVigencia) {
        this.codVigencia = codVigencia;
    }

    public String getNomVigencia() {
        return nomVigencia;
    }

    public void setNomVigencia(String nomVigencia) {
        this.nomVigencia = nomVigencia;
    }

    @XmlTransient
    public List<MuseoUsuario> getMuseoUsuarioList() {
        return museoUsuarioList;
    }

    public void setMuseoUsuarioList(List<MuseoUsuario> museoUsuarioList) {
        this.museoUsuarioList = museoUsuarioList;
    }

    @XmlTransient
    public List<MuseoUsuarioFoto> getMuseoUsuarioFotoList() {
        return museoUsuarioFotoList;
    }

    public void setMuseoUsuarioFotoList(List<MuseoUsuarioFoto> museoUsuarioFotoList) {
        this.museoUsuarioFotoList = museoUsuarioFotoList;
    }

    @XmlTransient
    public List<MuseoProyecto> getMuseoProyectoList() {
        return museoProyectoList;
    }

    public void setMuseoProyectoList(List<MuseoProyecto> museoProyectoList) {
        this.museoProyectoList = museoProyectoList;
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
        hash += (codVigencia != null ? codVigencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoVigencia)) {
            return false;
        }
        TipoVigencia other = (TipoVigencia) object;
        if ((this.codVigencia == null && other.codVigencia != null) || (this.codVigencia != null && !this.codVigencia.equals(other.codVigencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.usm.geosansano.entity.TipoVigencia[ codVigencia=" + codVigencia + " ]";
    }
    
}
