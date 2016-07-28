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
@Table(name = "payment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Payment.findAll", query = "SELECT p FROM Payment p"),
    @NamedQuery(name = "Payment.findById", query = "SELECT p FROM Payment p WHERE p.id = :id"),
    @NamedQuery(name = "Payment.findByPaydate", query = "SELECT p FROM Payment p WHERE p.paydate = :paydate"),
    @NamedQuery(name = "Payment.findByClientid", query = "SELECT p FROM Payment p WHERE p.clientid = :clientid"),
    @NamedQuery(name = "Payment.findBySumm", query = "SELECT p FROM Payment p WHERE p.summ = :summ"),
    @NamedQuery(name = "Payment.findByComment", query = "SELECT p FROM Payment p WHERE p.comment = :comment"),
    @NamedQuery(name = "Payment.findByPid", query = "SELECT p FROM Payment p WHERE p.pid = :pid"),
    @NamedQuery(name = "Payment.findByTs", query = "SELECT p FROM Payment p WHERE p.ts = :ts")})
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "paydate")
    @Temporal(TemporalType.DATE)
    private Date paydate;
    @Column(name = "clientid")
    private Integer clientid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "summ")
    private BigDecimal summ;
    @Size(max = 50)
    @Column(name = "comment")
    private String comment;
    @Column(name = "pid")
    private Integer pid;
    @Basic(optional = false)
    
    @Column(name = "ts")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ts;


    public Payment() {
    }

    public Payment(Integer id) {
        this.id = id;
    }

    public Payment(Integer id, Date ts) {
        this.id = id;
        this.ts = ts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getPaydate() {
        return paydate;
    }

    public void setPaydate(Date paydate) {
        this.paydate = paydate;
    }

    public Integer getClientid() {
        return clientid;
    }

    public void setClientid(Integer clientid) {
        this.clientid = clientid;
    }

    public BigDecimal getSumm() {
        return summ;
    }

    public void setSumm(BigDecimal summ) {
        this.summ = summ;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Payment)) {
            return false;
        }
        Payment other = (Payment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Payment[ id=" + id + " ]";
    }
    
}
