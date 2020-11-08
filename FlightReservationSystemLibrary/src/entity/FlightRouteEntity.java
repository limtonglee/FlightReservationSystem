/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author limtonglee
 */
@Entity
public class FlightRouteEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightRouteId;
    @Column(nullable = false)
    private String origin;
    @Column(nullable = false)
    private String dest;
    
    //@OneToOne(fetch = FetchType.LAZY)
    //private FlightRouteEntity destination;
    @OneToOne
    private FlightRouteEntity complementaryRoute;

    //@OneToMany
    //private List<FlightEntity> flightEntities;
       
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private AirportEntity originAirport;
    
    
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private AirportEntity destAirport;
    

    public FlightRouteEntity() {
       //flightEntities = new ArrayList<>(); 
    }

    public FlightRouteEntity(String origin, String dest, AirportEntity originAirport, AirportEntity destAirport) {
        
        this();
        
        this.origin = origin;
        this.dest = dest;
        this.originAirport = originAirport;
        this.destAirport = destAirport;
    }
    
    
    
    
    

    public Long getFlightRouteId() {
        return flightRouteId;
    }

    public void setFlightRouteId(Long flightRouteId) {
        this.flightRouteId = flightRouteId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (flightRouteId != null ? flightRouteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the flightRouteId fields are not set
        if (!(object instanceof FlightRouteEntity)) {
            return false;
        }
        FlightRouteEntity other = (FlightRouteEntity) object;
        if ((this.flightRouteId == null && other.flightRouteId != null) || (this.flightRouteId != null && !this.flightRouteId.equals(other.flightRouteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FlightRouteEntity[ id=" + flightRouteId + " ]";
    }
    
}
