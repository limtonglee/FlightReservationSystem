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
public class Partner implements Serializable
{

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partnerId;
    @Column(nullable = false, length = 32)
    private String name;
    @Column(nullable = false, length = 32, unique = true)
    private String unsername;
    @Column(nullable = false, length = 32)
    private String password;

    public Partner()
    {
    }

    public Partner(String name, String unsername, String password)
    {
        this.name = name;
        this.unsername = unsername;
        this.password = password;
    }
            
    public Long getPartnerId()
    {
        return partnerId;
    }

    public void setPartnerId(Long partnerId)
    {
        this.partnerId = partnerId;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (partnerId != null ? partnerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the partnerId fields are not set
        if (!(object instanceof Partner))
        {
            return false;
        }
        Partner other = (Partner) object;
        if ((this.partnerId == null && other.partnerId != null) || (this.partnerId != null && !this.partnerId.equals(other.partnerId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.Partner[ id=" + partnerId + " ]";
    }
    
        public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getUnsername()
    {
        return unsername;
    }

    public void setUnsername(String unsername)
    {
        this.unsername = unsername;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
