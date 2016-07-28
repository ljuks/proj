/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ss
 */
@Entity
@Table(name = "contactperson")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contactperson.findAll", query = "SELECT c FROM Contactperson c"),
    @NamedQuery(name = "Contactperson.findById", query = "SELECT c FROM Contactperson c WHERE c.id = :id"),
    @NamedQuery(name = "Contactperson.findByName", query = "SELECT c FROM Contactperson c WHERE c.name = :name")})
public class Contactperson implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 30)
    @Column(name = "name")
    private String name;
    @Lob
    @Size(max = 65535)
    @Column(name = "descr")
    private String descr;

    @OneToMany(mappedBy = "cpid")
    private Collection<Shedule> sheduleCollection;

    @OneToMany(mappedBy = "cpid1")
    private Collection<Client> clientCollection1;

    @OneToMany(mappedBy = "cpid2")
    private Collection<Client> clientCollection2;

    @OneToMany(mappedBy = "cpid3")
    private Collection<Client> clientCollection3;

    @OneToMany(mappedBy = "cpid4")
    private Collection<Client> clientCollection4;

    @OneToMany(mappedBy = "cpid5")
    private Collection<Client> clientCollection5;
    
    

    public Contactperson() {
    }

    public Contactperson(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

   public Collection<Shedule> getSheduleCollection() {
        return sheduleCollection;
    }

    public void setSheduleCollection(Collection<Shedule> sheduleCollection) {
        this.sheduleCollection = sheduleCollection;
    }

    public Collection<Client> getClientCollection1() {
        return clientCollection1;
    }

    public void setClientCollection1(Collection<Client> clientCollection1) {
        this.clientCollection1 = clientCollection1;
    }

    public Collection<Client> getClientCollection2() {
        return clientCollection2;
    }

    public void setClientCollection2(Collection<Client> clientCollection2) {
        this.clientCollection2 = clientCollection2;
    }

    public Collection<Client> getClientCollection3() {
        return clientCollection3;
    }

    public void setClientCollection3(Collection<Client> clientCollection3) {
        this.clientCollection3 = clientCollection3;
    }

    public Collection<Client> getClientCollection4() {
        return clientCollection4;
    }

    public void setClientCollection4(Collection<Client> clientCollection4) {
        this.clientCollection4 = clientCollection4;
    }

    public Collection<Client> getClientCollection5() {
        return clientCollection5;
    }

    public void setClientCollection5(Collection<Client> clientCollection5) {
        this.clientCollection5 = clientCollection5;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contactperson)) {
            return false;
        }
        Contactperson other = (Contactperson) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }

}
