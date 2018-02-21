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
 * @author Juan Delgado
 */
@Entity
@Table(name = "tipo_perfil")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoPerfil.findAll", query = "SELECT t FROM TipoPerfil t")
    , @NamedQuery(name = "TipoPerfil.findByCodPerfil", query = "SELECT t FROM TipoPerfil t WHERE t.codPerfil = :codPerfil")
    , @NamedQuery(name = "TipoPerfil.findByNomPerfil", query = "SELECT t FROM TipoPerfil t WHERE t.nomPerfil = :nomPerfil")})
public class TipoPerfil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_perfil")
    private Integer codPerfil;
    @Size(max = 50)
    @Column(name = "nom_perfil")
    private String nomPerfil;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codPerfil")
    private List<MuseoUsuario> museoUsuarioList;

    public TipoPerfil() {
    }

    public TipoPerfil(Integer codPerfil) {
        this.codPerfil = codPerfil;
    }

    public Integer getCodPerfil() {
        return codPerfil;
    }

    public void setCodPerfil(Integer codPerfil) {
        this.codPerfil = codPerfil;
    }

    public String getNomPerfil() {
        return nomPerfil;
    }

    public void setNomPerfil(String nomPerfil) {
        this.nomPerfil = nomPerfil;
    }

    @XmlTransient
    public List<MuseoUsuario> getMuseoUsuarioList() {
        return museoUsuarioList;
    }

    public void setMuseoUsuarioList(List<MuseoUsuario> museoUsuarioList) {
        this.museoUsuarioList = museoUsuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codPerfil != null ? codPerfil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPerfil)) {
            return false;
        }
        TipoPerfil other = (TipoPerfil) object;
        if ((this.codPerfil == null && other.codPerfil != null) || (this.codPerfil != null && !this.codPerfil.equals(other.codPerfil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.usm.geosansano.entity.TipoPerfil[ codPerfil=" + codPerfil + " ]";
    }
    
}
