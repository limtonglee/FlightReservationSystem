/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author limtonglee
 */
@Entity
public class AirportEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long airportId; 
    @Column(nullable = false, length = 3)
    private String iataCode; //SIN
    @Column(nullable = false)
    private String airportName; //Singapore Changi Airport
    private String city; //Singapore
    private String stateOrProvince; //Singapore
    @Column(nullable = false)
    private String country; //Singapore
    
    @OneToMany(mappedBy = "originAirport")
    private List<FlightRouteEntity> originFlights;

    @OneToMany(mappedBy = "destAirport")
    private List<FlightRouteEntity> destFlights;

    public AirportEntity() {
        originFlights = new ArrayList<>();
        destFlights = new ArrayList<>();
    }

    public AirportEntity(String iataCode, String airportName, String city, String stateOrProvince, String country) {
        
        this();
        
        this.iataCode = iataCode;
        this.airportName = airportName;
        this.city = city;
        this.stateOrProvince = stateOrProvince;
        this.country = country;
    }
    
    

    public Long getAirportId() {
        return airportId;
    }

    public void setAirportId(Long airportId) {
        this.airportId = airportId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (airportId != null ? airportId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the airportId fields are not set
        if (!(object instanceof AirportEntity)) {
            return false;
        }
        AirportEntity other = (AirportEntity) object;
        if ((this.airportId == null && other.airportId != null) || (this.airportId != null && !this.airportId.equals(other.airportId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AirportEntity[ id=" + airportId + " ]";
    }
    
}
