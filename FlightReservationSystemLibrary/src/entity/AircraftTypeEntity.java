/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
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
public class AircraftTypeEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aircraftTypeId;
    @Column(nullable = false)
    private String aircraftTypeName; //Boeing 747
    @Column(nullable = false)
    private Long maxSeatCapacity; //300
    private String description; //wide body, long range
    
    
    //@OneToMany
    //private List<AircraftConfigurationEntity> aircraftConfigs;  

    public AircraftTypeEntity() {
        //aircraftConfigs = new ArrayList<>();
    }

    public AircraftTypeEntity(String aircraftTypeName, Long maxSeatCapacity, String description) {
        
        this();
        
        this.aircraftTypeName = aircraftTypeName;
        this.maxSeatCapacity = maxSeatCapacity;
        this.description = description;
    }
    
    

    public Long getAircraftTypeId() {
        return aircraftTypeId;
    }

    public void setAircraftTypeId(Long aircraftTypeId) {
        this.aircraftTypeId = aircraftTypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aircraftTypeId != null ? aircraftTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the aircraftTypeId fields are not set
        if (!(object instanceof AircraftTypeEntity)) {
            return false;
        }
        AircraftTypeEntity other = (AircraftTypeEntity) object;
        if ((this.aircraftTypeId == null && other.aircraftTypeId != null) || (this.aircraftTypeId != null && !this.aircraftTypeId.equals(other.aircraftTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AircraftTypeEntity[ id=" + aircraftTypeId + " ]";
    }
    
}
