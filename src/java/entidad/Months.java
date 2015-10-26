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
@Table(name = "months", catalog = "ideam", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Months.findAll", query = "SELECT m FROM Months m"),
    @NamedQuery(name = "Months.findByIdSamples", query = "SELECT m FROM Months m WHERE m.monthsPK.idSamples = :idSamples"),
    @NamedQuery(name = "Months.findByYear", query = "SELECT m FROM Months m WHERE m.monthsPK.year = :year"),
    @NamedQuery(name = "Months.findByMonth", query = "SELECT m FROM Months m WHERE m.monthsPK.month = :month"),
    @NamedQuery(name = "Months.findByValuePoint", query = "SELECT m FROM Months m WHERE m.valuePoint = :valuePoint"),
    @NamedQuery(name = "Months.findByRemark", query = "SELECT m FROM Months m WHERE m.remark = :remark")})
public class Months implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MonthsPK monthsPK;
    @Column(name = "value_point")
    private BigInteger valuePoint;
    @Size(max = 2147483647)
    @Column(name = "remark", length = 2147483647)
    private String remark;
    @JoinColumn(name = "id_samples", referencedColumnName = "id_samples", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Samples samples;

    public Months() {
    }

    public Months(MonthsPK monthsPK) {
        this.monthsPK = monthsPK;
    }

    public Months(int idSamples, int year, int month) {
        this.monthsPK = new MonthsPK(idSamples, year, month);
    }

    public MonthsPK getMonthsPK() {
        return monthsPK;
    }

    public void setMonthsPK(MonthsPK monthsPK) {
        this.monthsPK = monthsPK;
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
        hash += (monthsPK != null ? monthsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Months)) {
            return false;
        }
        Months other = (Months) object;
        if ((this.monthsPK == null && other.monthsPK != null) || (this.monthsPK != null && !this.monthsPK.equals(other.monthsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.Months[ monthsPK=" + monthsPK + " ]";
    }
    
}
