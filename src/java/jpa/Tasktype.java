/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author administrator
 */
@Entity
@Table(name = "tasktype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tasktype.findAll", query = "SELECT t FROM Tasktype t"),
    @NamedQuery(name = "Tasktype.findById", query = "SELECT t FROM Tasktype t WHERE t.id = :id"),
    @NamedQuery(name = "Tasktype.findByName", query = "SELECT t FROM Tasktype t WHERE t.name = :name"),
    @NamedQuery(name = "Tasktype.findByPid", query = "SELECT t FROM Tasktype t WHERE t.pid = :pid"),
    @NamedQuery(name = "Tasktype.findByTs", query = "SELECT t FROM Tasktype t WHERE t.ts = :ts")})
public class Tasktype implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "name")
    private String name;
    @Column(name = "pid")
    private Integer pid;
    @Basic(optional = false)
  
    @Column(name = "ts")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ts;
    @OneToMany(mappedBy = "taskid")
    private Collection<Shedule> sheduleCollection;
    
    @JoinColumn(name = "tasktype1id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tasktype1 tasktype1id;

    public Tasktype() {
    }

    public Tasktype(Integer id) {
        this.id = id;
    }

    public Tasktype(Integer id, String name, Date ts) {
        this.id = id;
        this.name = name;
        this.ts = ts;
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

    public Tasktype1 getTasktype1id() {
        return tasktype1id;
    }

    public void setTasktype1id(Tasktype1 tasktype1id) {
        this.tasktype1id = tasktype1id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    @XmlTransient
    public Collection<Shedule> getSheduleCollection() {
        return sheduleCollection;
    }

    public void setSheduleCollection(Collection<Shedule> sheduleCollection) {
        this.sheduleCollection = sheduleCollection;
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
        if (!(object instanceof Tasktype)) {
            return false;
        }
        Tasktype other = (Tasktype) object;
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
