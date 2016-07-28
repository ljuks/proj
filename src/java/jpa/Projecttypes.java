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
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author administrator
 */
@Entity
@Table(name = "projecttypes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Projecttypes.findAll", query = "SELECT p FROM Projecttypes p"),
    @NamedQuery(name = "Projecttypes.findById", query = "SELECT p FROM Projecttypes p WHERE p.id = :id"),
    @NamedQuery(name = "Projecttypes.findByName", query = "SELECT p FROM Projecttypes p WHERE p.name = :name"),
    @NamedQuery(name = "Projecttypes.findByPid", query = "SELECT p FROM Projecttypes p WHERE p.pid = :pid"),
    @NamedQuery(name = "Projecttypes.findByTs", query = "SELECT p FROM Projecttypes p WHERE p.ts = :ts")})
public class Projecttypes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "name")
    private String name;
    @Column(name = "pid")
    private Integer pid;
    @Basic(optional = false)
    
    @Column(name = "ts")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ts;
    @OneToMany(mappedBy = "typeid")
    private Collection<Projects> projectsCollection;

    public Projecttypes() {
    }

    public Projecttypes(Integer id) {
        this.id = id;
    }

    public Projecttypes(Integer id, Date ts) {
        this.id = id;
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
    public Collection<Projects> getProjectsCollection() {
        return projectsCollection;
    }

    public void setProjectsCollection(Collection<Projects> projectsCollection) {
        this.projectsCollection = projectsCollection;
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
        if (!(object instanceof Projecttypes)) {
            return false;
        }
        Projecttypes other = (Projecttypes) object;
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
