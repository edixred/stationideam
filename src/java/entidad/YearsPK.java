/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author edixred
 */
@Embeddable
public class YearsPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_samples", nullable = false)
    private int idSamples;
    @Basic(optional = false)
    @NotNull
    @Column(name = "year", nullable = false)
    private int year;

    public YearsPK() {
    }

    public YearsPK(int idSamples, int year) {
        this.idSamples = idSamples;
        this.year = year;
    }

    public int getIdSamples() {
        return idSamples;
    }

    public void setIdSamples(int idSamples) {
        this.idSamples = idSamples;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idSamples;
        hash += (int) year;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof YearsPK)) {
            return false;
        }
        YearsPK other = (YearsPK) object;
        if (this.idSamples != other.idSamples) {
            return false;
        }
        if (this.year != other.year) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.YearsPK[ idSamples=" + idSamples + ", year=" + year + " ]";
    }
    
}
