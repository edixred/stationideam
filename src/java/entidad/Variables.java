/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * @author edixred
 */
@Entity
@Table(name = "variables", catalog = "ideam", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Variables.findAll", query = "SELECT v FROM Variables v"),
    @NamedQuery(name = "Variables.findByVariableId", query = "SELECT v FROM Variables v WHERE v.variableId = :variableId"),
    @NamedQuery(name = "Variables.findByNameVariable", query = "SELECT v FROM Variables v WHERE v.nameVariable = :nameVariable")})
public class Variables implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "variable_id", nullable = false)
    private Integer variableId;
    @Size(max = 2147483647)
    @Column(name = "name_variable", length = 2147483647)
    private String nameVariable;
    @OneToMany(mappedBy = "variableId", fetch = FetchType.LAZY)
    private List<Samples> samplesList;

    public Variables() {
    }

    public Variables(Integer variableId) {
        this.variableId = variableId;
    }

    public Integer getVariableId() {
        return variableId;
    }

    public void setVariableId(Integer variableId) {
        this.variableId = variableId;
    }

    public String getNameVariable() {
        return nameVariable;
    }

    public void setNameVariable(String nameVariable) {
        this.nameVariable = nameVariable;
    }

    @XmlTransient
    public List<Samples> getSamplesList() {
        return samplesList;
    }

    public void setSamplesList(List<Samples> samplesList) {
        this.samplesList = samplesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (variableId != null ? variableId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Variables)) {
            return false;
        }
        Variables other = (Variables) object;
        if ((this.variableId == null && other.variableId != null) || (this.variableId != null && !this.variableId.equals(other.variableId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.Variables[ variableId=" + variableId + " ]";
    }
    
}
