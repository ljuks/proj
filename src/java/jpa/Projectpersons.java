/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author administrator
 */
@Entity
@Table(name = "projectpersons")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Projectpersons.findAll", query = "SELECT p FROM Projectpersons p"),
    @NamedQuery(name = "Projectpersons.findById", query = "SELECT p FROM Projectpersons p WHERE p.id = :id"),
    @NamedQuery(name = "Projectpersons.findByUserid", query = "SELECT p FROM Projectpersons p WHERE p.userid = :userid"),
    @NamedQuery(name = "Projectpersons.findByUsersptypeid", query = "SELECT p FROM Projectpersons p WHERE p.usersptypeid = :usersptypeid"),
    @NamedQuery(name = "Projectpersons.findByUdate", query = "SELECT p FROM Projectpersons p WHERE p.udate = :udate"),
    @NamedQuery(name = "Projectpersons.findByUdateend", query = "SELECT p FROM Projectpersons p WHERE p.udateend = :udateend"),
    @NamedQuery(name = "Projectpersons.findByProjectid", query = "SELECT p FROM Projectpersons p WHERE p.projectid = :projectid")})
public class Projectpersons implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "udate")
    @Temporal(TemporalType.DATE)
    private Date udate;
    @Column(name = "udateend")
    @Temporal(TemporalType.DATE)
    private Date udateend;

    @Column(name = "kpi")
    private BigDecimal kpi;

    @Column(name = "projectid")
    private Integer projectid;

    @JoinColumn(name = "userid", referencedColumnName = "id")
    @ManyToOne
    private Users userid;

    @JoinColumn(name = "usersptypeid", referencedColumnName = "id")
    @ManyToOne
    private Usersptype usersptypeid;

//    @JoinColumn(name = "projectid", referencedColumnName = "id")
//    @ManyToOne
//    private Projects projectid;
    public Projectpersons() {
    }

    public Projectpersons(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getKpi() {
        return kpi;
    }

    public void setKpi(BigDecimal kpi) {
        this.kpi = kpi;
    }

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public Users getUserid() {
        return userid;
    }

    public void setUserid(Users userid) {
        this.userid = userid;
    }

    public Usersptype getUsersptypeid() {
        return usersptypeid;
    }

    public void setUsersptypeid(Usersptype usersptypeid) {
        this.usersptypeid = usersptypeid;
    }

    public Date getUdate() {
        return udate;
    }

    public void setUdate(Date udate) {
        this.udate = udate;
    }

    public Date getUdateend() {
        return udateend;
    }

    public void setUdateend(Date udateend) {
        this.udateend = udateend;
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
        if (!(object instanceof Projectpersons)) {
            return false;
        }
        Projectpersons other = (Projectpersons) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Projectpersons[ id=" + id + " ]";
    }

}
