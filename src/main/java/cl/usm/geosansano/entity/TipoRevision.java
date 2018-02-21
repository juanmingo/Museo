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
@Table(name = "tipo_revision")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoRevision.findAll", query = "SELECT t FROM TipoRevision t")
    , @NamedQuery(name = "TipoRevision.findByCodRevision", query = "SELECT t FROM TipoRevision t WHERE t.codRevision = :codRevision")
    , @NamedQuery(name = "TipoRevision.findByNomRevision", query = "SELECT t FROM TipoRevision t WHERE t.nomRevision = :nomRevision")})
public class TipoRevision implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_revision")
    private Integer codRevision;
    @Size(max = 50)
    @Column(name = "nom_revision")
    private String nomRevision;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codRevision")
    private List<MuseoUsuario> museoUsuarioList;

    public TipoRevision() {
    }

    public TipoRevision(Integer codRevision) {
        this.codRevision = codRevision;
    }

    public Integer getCodRevision() {
        return codRevision;
    }

    public void setCodRevision(Integer codRevision) {
        this.codRevision = codRevision;
    }

    public String getNomRevision() {
        return nomRevision;
    }

    public void setNomRevision(String nomRevision) {
        this.nomRevision = nomRevision;
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
        hash += (codRevision != null ? codRevision.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoRevision)) {
            return false;
        }
        TipoRevision other = (TipoRevision) object;
        if ((this.codRevision == null && other.codRevision != null) || (this.codRevision != null && !this.codRevision.equals(other.codRevision))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.usm.geosansano.entity.TipoRevision[ codRevision=" + codRevision + " ]";
    }
    
}
