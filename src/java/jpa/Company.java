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
 * @author administrator
 */
@Entity
@Table(name = "company")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Company.findAll", query = "SELECT c FROM Company c"),
    @NamedQuery(name = "Company.findById", query = "SELECT c FROM Company c WHERE c.id = :id"),
    @NamedQuery(name = "Company.findByName", query = "SELECT c FROM Company c WHERE c.name = :name"),
    @NamedQuery(name = "Company.findByPid", query = "SELECT c FROM Company c WHERE c.pid = :pid"),
    @NamedQuery(name = "Company.findByTs", query = "SELECT c FROM Company c WHERE c.ts = :ts")})
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    @Column(name = "pid")
    private Integer pid;
    @Basic(optional = false)
    
    @Column(name = "ts")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ts;
    
       @OneToMany(mappedBy = "companyid")
    private Collection<Projects> projectsCollection;

    public Company() {
    }

    public Company(Integer id) {
        this.id = id;
    }

    public Company(Integer id, String name, Date ts) {
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
        if (!(object instanceof Company)) {
            return false;
        }
        Company other = (Company) object;
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
