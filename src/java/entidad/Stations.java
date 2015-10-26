/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author edixred
 */
@Entity
@Table(name = "stations", catalog = "ideam", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stations.findAll", query = "SELECT s FROM Stations s"),
    @NamedQuery(name = "Stations.findByStationId", query = "SELECT s FROM Stations s WHERE s.stationId = :stationId"),
    @NamedQuery(name = "Stations.findByLatitude3857", query = "SELECT s FROM Stations s WHERE s.latitude3857 = :latitude3857"),
    @NamedQuery(name = "Stations.findByLongitude3857", query = "SELECT s FROM Stations s WHERE s.longitude3857 = :longitude3857"),
    @NamedQuery(name = "Stations.findByLatitude4326", query = "SELECT s FROM Stations s WHERE s.latitude4326 = :latitude4326"),
    @NamedQuery(name = "Stations.findByLongitude4326", query = "SELECT s FROM Stations s WHERE s.longitude4326 = :longitude4326")})
public class Stations implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "station_id", nullable = false)
    private Integer stationId;
    @Column(name = "latitude_3857")
    private Integer latitude3857;
    @Column(name = "longitude_3857")
    private Integer longitude3857;
    @Column(name = "latitude_4326")
    private BigInteger latitude4326;
    @Column(name = "longitude_4326")
    private BigInteger longitude4326;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "stations1", fetch = FetchType.LAZY)
    private Stations stations;
    @JoinColumn(name = "station_id", referencedColumnName = "station_id", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Stations stations1;
    @OneToMany(mappedBy = "stationId", fetch = FetchType.LAZY)
    private List<Samples> samplesList;

    public Stations() {
    }

    public Stations(Integer stationId) {
        this.stationId = stationId;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public Integer getLatitude3857() {
        return latitude3857;
    }

    public void setLatitude3857(Integer latitude3857) {
        this.latitude3857 = latitude3857;
    }

    public Integer getLongitude3857() {
        return longitude3857;
    }

    public void setLongitude3857(Integer longitude3857) {
        this.longitude3857 = longitude3857;
    }

    public BigInteger getLatitude4326() {
        return latitude4326;
    }

    public void setLatitude4326(BigInteger latitude4326) {
        this.latitude4326 = latitude4326;
    }

    public BigInteger getLongitude4326() {
        return longitude4326;
    }

    public void setLongitude4326(BigInteger longitude4326) {
        this.longitude4326 = longitude4326;
    }

    public Stations getStations() {
        return stations;
    }

    public void setStations(Stations stations) {
        this.stations = stations;
    }

    public Stations getStations1() {
        return stations1;
    }

    public void setStations1(Stations stations1) {
        this.stations1 = stations1;
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
        hash += (stationId != null ? stationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stations)) {
            return false;
        }
        Stations other = (Stations) object;
        if ((this.stationId == null && other.stationId != null) || (this.stationId != null && !this.stationId.equals(other.stationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.Stations[ stationId=" + stationId + " ]";
    }
    
}
