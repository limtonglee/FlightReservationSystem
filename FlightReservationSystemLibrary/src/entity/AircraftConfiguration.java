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

/**
 *
 * @author zhiliangwang
 */
@Entity
public class AircraftConfiguration implements Serializable
{

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aircraftConfigurationId;
    @Column(nullable = false, length = 32)
    private String name;
    @Column(nullable = false)
    private int numberOfCabinClass;

    public AircraftConfiguration()
    {
    }

    public AircraftConfiguration(String name, int numberOfCabinClass)
    {
        this.name = name;
        this.numberOfCabinClass = numberOfCabinClass;
    }
    

    public Long getAircraftConfigurationId()
    {
        return aircraftConfigurationId;
    }

    public void setAircraftConfigurationId(Long aircraftConfigurationId)
    {
        this.aircraftConfigurationId = aircraftConfigurationId;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (aircraftConfigurationId != null ? aircraftConfigurationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the aircraftConfigurationId fields are not set
        if (!(object instanceof AircraftConfiguration))
        {
            return false;
        }
        AircraftConfiguration other = (AircraftConfiguration) object;
        if ((this.aircraftConfigurationId == null && other.aircraftConfigurationId != null) || (this.aircraftConfigurationId != null && !this.aircraftConfigurationId.equals(other.aircraftConfigurationId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.AircraftConfiguration[ id=" + aircraftConfigurationId + " ]";
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getNumberOfCabinClass()
    {
        return numberOfCabinClass;
    }

    public void setNumberOfCabinClass(int numberOfCabinClass)
    {
        this.numberOfCabinClass = numberOfCabinClass;
    }
    
}
