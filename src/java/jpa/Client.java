/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
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
@Table(name = "client")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c"),
    @NamedQuery(name = "Client.findById", query = "SELECT c FROM Client c WHERE c.id = :id"),
    @NamedQuery(name = "Client.findByName", query = "SELECT c FROM Client c WHERE c.name = :name"),
    @NamedQuery(name = "Client.findByOkpo", query = "SELECT c FROM Client c WHERE c.okpo = :okpo"),
    @NamedQuery(name = "Client.findByAddressl", query = "SELECT c FROM Client c WHERE c.addressl = :addressl"),
    @NamedQuery(name = "Client.findByAddressp", query = "SELECT c FROM Client c WHERE c.addressp = :addressp"),
    @NamedQuery(name = "Client.findByPhone", query = "SELECT c FROM Client c WHERE c.phone = :phone"),
    @NamedQuery(name = "Client.findByAddresse", query = "SELECT c FROM Client c WHERE c.addresse = :addresse"),
    @NamedQuery(name = "Client.findByPid", query = "SELECT c FROM Client c WHERE c.pid = :pid"),
    @NamedQuery(name = "Client.findByTs", query = "SELECT c FROM Client c WHERE c.ts = :ts"),
    @NamedQuery(name = "Client.findByTypeid", query = "SELECT c FROM Client c WHERE c.typeid = :typeid"),
    @NamedQuery(name = "Client.findBySite", query = "SELECT c FROM Client c WHERE c.site = :site"),
    @NamedQuery(name = "Client.findByCpid1", query = "SELECT c FROM Client c WHERE c.cpid1 = :cpid1"),
    @NamedQuery(name = "Client.findByCpid2", query = "SELECT c FROM Client c WHERE c.cpid2 = :cpid2"),
    @NamedQuery(name = "Client.findByCpid3", query = "SELECT c FROM Client c WHERE c.cpid3 = :cpid3"),
    @NamedQuery(name = "Client.findByCpid4", query = "SELECT c FROM Client c WHERE c.cpid4 = :cpid4"),
    @NamedQuery(name = "Client.findByCpid5", query = "SELECT c FROM Client c WHERE c.cpid5 = :cpid5"),
    @NamedQuery(name = "Client.findByWaydesc", query = "SELECT c FROM Client c WHERE c.waydesc = :waydesc"),
    @NamedQuery(name = "Client.findByHoldingid", query = "SELECT c FROM Client c WHERE c.holdingid = :holdingid")})
public class Client implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "okpo")
    private String okpo;
    @Size(max = 75)
    @Column(name = "addressl")
    private String addressl;
    @Size(max = 50)
    @Column(name = "addressp")
    private String addressp;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Недопустимый формат номера телефона/факса (должен иметь формат xxx-xxx-xxxx)")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 15)
    @Column(name = "phone")
    private String phone;
    @Size(max = 50)
    @Column(name = "addresse")
    private String addresse;
    @Column(name = "pid")
    private Integer pid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ts")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ts;
    @Column(name = "typeid")
    private Integer typeid;
    @Size(max = 50)
    @Column(name = "site")
    private String site;

    @Lob
    @Size(max = 65535)
    @Column(name = "descr")
    private String descr;
    @Size(max = 100)
    @Column(name = "waydesc")
    private String waydesc;
   @JoinColumn(name = "holdingid", referencedColumnName = "id")
    @ManyToOne
    private Holdings holdingid;
   
   @JoinColumn(name = "cpid1", referencedColumnName = "id")
    @ManyToOne
    private Contactperson cpid1;   
   
   @JoinColumn(name = "cpid2", referencedColumnName = "id")
    @ManyToOne
    private Contactperson cpid2;
   
      @JoinColumn(name = "cpid3", referencedColumnName = "id")
    @ManyToOne
    private Contactperson cpid3;   
      
         @JoinColumn(name = "cpid4", referencedColumnName = "id")
    @ManyToOne
    private Contactperson cpid4;   
         
            @JoinColumn(name = "cpid5", referencedColumnName = "id")
    @ManyToOne
    private Contactperson cpid5;   
   
   
   

    public Client() {
    }

    public Client(Integer id) {
        this.id = id;
    }

    public Client(Integer id, String name, String okpo, Date ts) {
        this.id = id;
        this.name = name;
        this.okpo = okpo;
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

    public String getOkpo() {
        return okpo;
    }

    public void setOkpo(String okpo) {
        this.okpo = okpo;
    }

    public String getAddressl() {
        return addressl;
    }

    public void setAddressl(String addressl) {
        this.addressl = addressl;
    }

    public String getAddressp() {
        return addressp;
    }

    public void setAddressp(String addressp) {
        this.addressp = addressp;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
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

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Contactperson getCpid1() {
        return cpid1;
    }

    public void setCpid1(Contactperson cpid1) {
        this.cpid1 = cpid1;
    }

    public Contactperson getCpid2() {
        return cpid2;
    }

    public void setCpid2(Contactperson cpid2) {
        this.cpid2 = cpid2;
    }

    public Contactperson getCpid3() {
        return cpid3;
    }

    public void setCpid3(Contactperson cpid3) {
        this.cpid3 = cpid3;
    }

    public Contactperson getCpid4() {
        return cpid4;
    }

    public void setCpid4(Contactperson cpid4) {
        this.cpid4 = cpid4;
    }

    public Contactperson getCpid5() {
        return cpid5;
    }

    public void setCpid5(Contactperson cpid5) {
        this.cpid5 = cpid5;
    }


    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getWaydesc() {
        return waydesc;
    }

    public void setWaydesc(String waydesc) {
        this.waydesc = waydesc;
    }

    public Holdings getHoldingid() {
        return holdingid;
    }

    public void setHoldingid(Holdings holdingid) {
        this.holdingid = holdingid;
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
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
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
