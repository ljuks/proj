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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author administrator
 */
@Entity
@Table(name = "tasktype1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tasktype1.findAll", query = "SELECT t FROM Tasktype1 t"),
    @NamedQuery(name = "Tasktype1.findById", query = "SELECT t FROM Tasktype1 t WHERE t.id = :id"),
    @NamedQuery(name = "Tasktype1.findByName", query = "SELECT t FROM Tasktype1 t WHERE t.name = :name")})
public class Tasktype1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "task1id")
    private Collection<Shedule> sheduleCollection;
    
     @OneToMany(mappedBy = "tasktype1id")
    private Collection<Tasktype> tasktypeCollection;   

    public Tasktype1() {
    }

    public Tasktype1(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Shedule> getSheduleCollection() {
        return sheduleCollection;
    }

    public void setSheduleCollection(Collection<Shedule> sheduleCollection) {
        this.sheduleCollection = sheduleCollection;
    }

    public Collection<Tasktype> getTasktypeCollection() {
        return tasktypeCollection;
    }

    public void setTasktypeCollection(Collection<Tasktype> tasktypeCollection) {
        this.tasktypeCollection = tasktypeCollection;
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
        if (!(object instanceof Tasktype1)) {
            return false;
        }
        Tasktype1 other = (Tasktype1) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Tasktype1[ id=" + id + " ]";
    }

}
