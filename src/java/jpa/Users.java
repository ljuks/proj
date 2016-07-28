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
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findById", query = "SELECT u FROM Users u WHERE u.id = :id"),
    @NamedQuery(name = "Users.findByFio", query = "SELECT u FROM Users u WHERE u.fio = :fio"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
    @NamedQuery(name = "Users.findByPid", query = "SELECT u FROM Users u WHERE u.pid = :pid"),
    @NamedQuery(name = "Users.findByTs", query = "SELECT u FROM Users u WHERE u.ts = :ts")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 30)
    @Column(name = "fio")
    private String fio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "password")
    private String password;
    @Column(name = "pid")
    private Integer pid;
    @Basic(optional = false)

    @Column(name = "ts")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ts;

    @OneToMany(mappedBy = "managerid")
    private Collection<Projects> projectsCollection;
    
    @OneToMany(mappedBy = "userid")
    private Collection<Projectpersons> projectpersonsCollection;

    @OneToMany(mappedBy = "userid")
    private Collection<Projects> projectsCollection1;

    @OneToMany(mappedBy = "managerid")
    private Collection<Shedule> sheduleCollection;
    @OneToMany(mappedBy = "userid")
    private Collection<Shedule> sheduleCollection1;
    @OneToMany(mappedBy = "exid")
    private Collection<Shedule> sheduleCollection2;

    @JoinColumn(name = "roleid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Userstype roleid;

    public Users() {
    }

    public Users(Integer id) {
        this.id = id;
    }

    public Users(Integer id, String password, Date ts) {
        this.id = id;
        this.password = password;
        this.ts = ts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Collection<Shedule> getSheduleCollection1() {
        return sheduleCollection1;
    }

    public void setSheduleCollection1(Collection<Shedule> sheduleCollection1) {
        this.sheduleCollection1 = sheduleCollection1;
    }

    public Collection<Shedule> getSheduleCollection2() {
        return sheduleCollection2;
    }

    public void setSheduleCollection2(Collection<Shedule> sheduleCollection2) {
        this.sheduleCollection2 = sheduleCollection2;
    }

    public Collection<Projects> getProjectsCollection1() {
        return projectsCollection1;
    }

    public void setProjectsCollection1(Collection<Projects> projectsCollection1) {
        this.projectsCollection1 = projectsCollection1;
    }

    @XmlTransient
    public Collection<Shedule> getSheduleCollection() {
        return sheduleCollection;
    }

    public void setSheduleCollection(Collection<Shedule> sheduleCollection) {
        this.sheduleCollection = sheduleCollection;
    }

    public Userstype getRoleid() {
        return roleid;
    }

    public void setRoleid(Userstype roleid) {
        this.roleid = roleid;
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
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return fio;
    }

}
