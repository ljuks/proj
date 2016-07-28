/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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

/**
 *
 * @author administrator
 */
@Entity
@Table(name = "projects")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Projects.findAll", query = "SELECT p FROM Projects p"),
    @NamedQuery(name = "Projects.findById", query = "SELECT p FROM Projects p WHERE p.id = :id"),
    @NamedQuery(name = "Projects.findByPdate", query = "SELECT p FROM Projects p WHERE p.pdate = :pdate"),
    @NamedQuery(name = "Projects.findByName", query = "SELECT p FROM Projects p WHERE p.name = :name"),
    @NamedQuery(name = "Projects.findByContract", query = "SELECT p FROM Projects p WHERE p.contract = :contract"),
    @NamedQuery(name = "Projects.findByPid", query = "SELECT p FROM Projects p WHERE p.pid = :pid"),
    @NamedQuery(name = "Projects.findByTs", query = "SELECT p FROM Projects p WHERE p.ts = :ts"),
    @NamedQuery(name = "Projects.findByCompanyid", query = "SELECT p FROM Projects p WHERE p.companyid = :companyid"),
    @NamedQuery(name = "Projects.findByCost", query = "SELECT p FROM Projects p WHERE p.cost = :cost"),
    @NamedQuery(name = "Projects.findByPrepaid", query = "SELECT p FROM Projects p WHERE p.prepaid = :prepaid"),
    @NamedQuery(name = "Projects.findByNz", query = "SELECT p FROM Projects p WHERE p.nz = :nz")})
public class Projects implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "pdate")
    @Temporal(TemporalType.DATE)
    private Date pdate;
    @Size(max = 100)
    @Column(name = "name")
    private String name;
    @Size(max = 50)
    @Column(name = "contract")
    private String contract;
    @Lob
    @Size(max = 65535)
    @Column(name = "pdesc")
    private String pdesc;
    @Column(name = "pid")
    private Integer pid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ts")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ts;
//    @Column(name = "companyid")
//    private Integer companyid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cost")
    private BigDecimal cost;
    @Column(name = "prepaid")
    private BigDecimal prepaid;
    @Size(max = 30)
    @Column(name = "nz")
    private String nz;
    
//    @OneToMany(mappedBy = "projectid")
//    private Collection<Projectpersons> projectpersonsCollection;

    @JoinColumn(name = "companyid", referencedColumnName = "id")
    @ManyToOne
    private Company companyid;

    @JoinColumn(name = "clientid", referencedColumnName = "id")
    @ManyToOne
    private Client clientid;

    @JoinColumn(name = "statusid", referencedColumnName = "id")
    @ManyToOne
    private Projectstatus statusid;

    @JoinColumn(name = "typeid", referencedColumnName = "id")
    @ManyToOne
    private Projecttypes typeid;

    @JoinColumn(name = "catid", referencedColumnName = "id")
    @ManyToOne
    private Cats catid;

    @JoinColumn(name = "managerid", referencedColumnName = "id")
    @ManyToOne
    private Users managerid;

    @JoinColumn(name = "userid", referencedColumnName = "id")
    @ManyToOne
    
    
    
    private Users userid;

    public Projects() {
    }

    public Projects(Integer id) {
        this.id = id;
    }

    public Projects(Integer id, Date ts) {
        this.id = id;
        this.ts = ts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getPdate() {
        return pdate;
    }

    public void setPdate(Date pdate) {
        this.pdate = pdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getPdesc() {
        return pdesc;
    }

    public void setPdesc(String pdesc) {
        this.pdesc = pdesc;
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

    public Company getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Company companyid) {
        this.companyid = companyid;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getPrepaid() {
        return prepaid;
    }

    public void setPrepaid(BigDecimal prepaid) {
        this.prepaid = prepaid;
    }

    public String getNz() {
        return nz;
    }

    public void setNz(String nz) {
        this.nz = nz;
    }

    public Client getClientid() {
        return clientid;
    }

    public void setClientid(Client clientid) {
        this.clientid = clientid;
    }

    public Projectstatus getStatusid() {
        return statusid;
    }

    public void setStatusid(Projectstatus statusid) {
        this.statusid = statusid;
    }

    public Projecttypes getTypeid() {
        return typeid;
    }

    public void setTypeid(Projecttypes typeid) {
        this.typeid = typeid;
    }

    public Cats getCatid() {
        return catid;
    }

    public void setCatid(Cats catid) {
        this.catid = catid;
    }

    public Users getManagerid() {
        return managerid;
    }

    public void setManagerid(Users managerid) {
        this.managerid = managerid;
    }

    public Users getUserid() {
        return userid;
    }

    public void setUserid(Users userid) {
        this.userid = userid;
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
        if (!(object instanceof Projects)) {
            return false;
        }
        Projects other = (Projects) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Projects[ id=" + id + " ]";
    }

}
