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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ss
 */
@Entity
@Table(name = "taskstatus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Taskstatus.findAll", query = "SELECT t FROM Taskstatus t"),
    @NamedQuery(name = "Taskstatus.findById", query = "SELECT t FROM Taskstatus t WHERE t.id = :id"),
    @NamedQuery(name = "Taskstatus.findByName", query = "SELECT t FROM Taskstatus t WHERE t.name = :name"),
    @NamedQuery(name = "Taskstatus.findByTs", query = "SELECT t FROM Taskstatus t WHERE t.ts = :ts"),
    @NamedQuery(name = "Taskstatus.findByPid", query = "SELECT t FROM Taskstatus t WHERE t.pid = :pid")})
public class Taskstatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ts")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ts;
    @Column(name = "pid")
    private Integer pid;

    @OneToMany(mappedBy = "statusid")
    private Collection<Shedule> sheduleCollection;

    public Taskstatus() {
    }

    public Taskstatus(Integer id) {
        this.id = id;
    }

    public Taskstatus(Integer id, String name, Date ts) {
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

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
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
        if (!(object instanceof Taskstatus)) {
            return false;
        }
        Taskstatus other = (Taskstatus) object;
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
