/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author edixred
 */
@Entity
@Table(name = "years", catalog = "ideam", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Years.findAll", query = "SELECT y FROM Years y"),
    @NamedQuery(name = "Years.findByIdSamples", query = "SELECT y FROM Years y WHERE y.yearsPK.idSamples = :idSamples"),
    @NamedQuery(name = "Years.findByYear", query = "SELECT y FROM Years y WHERE y.yearsPK.year = :year"),
    @NamedQuery(name = "Years.findByValuePoint", query = "SELECT y FROM Years y WHERE y.valuePoint = :valuePoint"),
    @NamedQuery(name = "Years.findByRemark", query = "SELECT y FROM Years y WHERE y.remark = :remark")})
public class Years implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected YearsPK yearsPK;
    @Column(name = "value_point")
    private BigInteger valuePoint;
    @Size(max = 2147483647)
    @Column(name = "remark", length = 2147483647)
    private String remark;
    @JoinColumn(name = "id_samples", referencedColumnName = "id_samples", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Samples samples;

    public Years() {
    }

    public Years(YearsPK yearsPK) {
        this.yearsPK = yearsPK;
    }

    public Years(int idSamples, int year) {
        this.yearsPK = new YearsPK(idSamples, year);
    }

    public YearsPK getYearsPK() {
        return yearsPK;
    }

    public void setYearsPK(YearsPK yearsPK) {
        this.yearsPK = yearsPK;
    }

    public BigInteger getValuePoint() {
        return valuePoint;
    }

    public void setValuePoint(BigInteger valuePoint) {
        this.valuePoint = valuePoint;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Samples getSamples() {
        return samples;
    }

    public void setSamples(Samples samples) {
        this.samples = samples;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (yearsPK != null ? yearsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Years)) {
            return false;
        }
        Years other = (Years) object;
        if ((this.yearsPK == null && other.yearsPK != null) || (this.yearsPK != null && !this.yearsPK.equals(other.yearsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.Years[ yearsPK=" + yearsPK + " ]";
    }
    
}
