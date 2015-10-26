/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "samples", catalog = "ideam", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Samples.findAll", query = "SELECT s FROM Samples s"),
    @NamedQuery(name = "Samples.findByIdSamples", query = "SELECT s FROM Samples s WHERE s.idSamples = :idSamples"),
    @NamedQuery(name = "Samples.findByUnits", query = "SELECT s FROM Samples s WHERE s.units = :units"),
    @NamedQuery(name = "Samples.findByCode", query = "SELECT s FROM Samples s WHERE s.code = :code"),
    @NamedQuery(name = "Samples.findByStation", query = "SELECT s FROM Samples s WHERE s.station = :station"),
    @NamedQuery(name = "Samples.findByAltitud", query = "SELECT s FROM Samples s WHERE s.altitud = :altitud"),
    @NamedQuery(name = "Samples.findByPlace", query = "SELECT s FROM Samples s WHERE s.place = :place"),
    @NamedQuery(name = "Samples.findByCurrent", query = "SELECT s FROM Samples s WHERE s.current = :current"),
    @NamedQuery(name = "Samples.findByInstitution", query = "SELECT s FROM Samples s WHERE s.institution = :institution"),
    @NamedQuery(name = "Samples.findByType", query = "SELECT s FROM Samples s WHERE s.type = :type"),
    @NamedQuery(name = "Samples.findByStart", query = "SELECT s FROM Samples s WHERE s.start = :start"),
    @NamedQuery(name = "Samples.findByEnd", query = "SELECT s FROM Samples s WHERE s.end = :end")})
public class Samples implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_samples", nullable = false)
    private Integer idSamples;
    @Size(max = 2147483647)
    @Column(name = "units", length = 2147483647)
    private String units;
    @Column(name = "code")
    private Integer code;
    @Size(max = 2147483647)
    @Column(name = "station", length = 2147483647)
    private String station;
    @Column(name = "altitud")
    private Integer altitud;
    @Size(max = 2147483647)
    @Column(name = "place", length = 2147483647)
    private String place;
    @Size(max = 2147483647)
    @Column(name = "current", length = 2147483647)
    private String current;
    @Size(max = 2147483647)
    @Column(name = "institution", length = 2147483647)
    private String institution;
    @Size(max = 2147483647)
    @Column(name = "type", length = 2147483647)
    private String type;
    @Size(max = 2147483647)
    @Column(name = "start", length = 2147483647)
    private String start;
    @Size(max = 2147483647)
    @Column(name = "end", length = 2147483647)
    private String end;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "samples", fetch = FetchType.LAZY)
    private List<Months> monthsList;
    @JoinColumn(name = "station_id", referencedColumnName = "station_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Stations stationId;
    @JoinColumn(name = "variable_id", referencedColumnName = "variable_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Variables variableId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "samples", fetch = FetchType.LAZY)
    private List<Years> yearsList;

    public Samples() {
    }

    public Samples(Integer idSamples) {
        this.idSamples = idSamples;
    }

    public Integer getIdSamples() {
        return idSamples;
    }

    public void setIdSamples(Integer idSamples) {
        this.idSamples = idSamples;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public Integer getAltitud() {
        return altitud;
    }

    public void setAltitud(Integer altitud) {
        this.altitud = altitud;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @XmlTransient
    public List<Months> getMonthsList() {
        return monthsList;
    }

    public void setMonthsList(List<Months> monthsList) {
        this.monthsList = monthsList;
    }

    public Stations getStationId() {
        return stationId;
    }

    public void setStationId(Stations stationId) {
        this.stationId = stationId;
    }

    public Variables getVariableId() {
        return variableId;
    }

    public void setVariableId(Variables variableId) {
        this.variableId = variableId;
    }

    @XmlTransient
    public List<Years> getYearsList() {
        return yearsList;
    }

    public void setYearsList(List<Years> yearsList) {
        this.yearsList = yearsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSamples != null ? idSamples.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Samples)) {
            return false;
        }
        Samples other = (Samples) object;
        if ((this.idSamples == null && other.idSamples != null) || (this.idSamples != null && !this.idSamples.equals(other.idSamples))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.Samples[ idSamples=" + idSamples + " ]";
    }
    
}
