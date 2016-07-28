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
 * @author administrator
 */
@Entity
@Table(name = "shedule")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Shedule.findAll", query = "SELECT s FROM Shedule s"),
    @NamedQuery(name = "Shedule.findById", query = "SELECT s FROM Shedule s WHERE s.id = :id"),
    @NamedQuery(name = "Shedule.findByTdate", query = "SELECT s FROM Shedule s WHERE s.tdate = :tdate"),
    @NamedQuery(name = "Shedule.findByStarttime", query = "SELECT s FROM Shedule s WHERE s.starttime = :starttime"),
    @NamedQuery(name = "Shedule.findByEndtime", query = "SELECT s FROM Shedule s WHERE s.endtime = :endtime"),
    @NamedQuery(name = "Shedule.findByTask", query = "SELECT s FROM Shedule s WHERE s.task = :task"),
    @NamedQuery(name = "Shedule.findByClientid", query = "SELECT s FROM Shedule s WHERE s.clientid = :clientid"),
    @NamedQuery(name = "Shedule.findByCpid", query = "SELECT s FROM Shedule s WHERE s.cpid = :cpid"),
    @NamedQuery(name = "Shedule.findByPid", query = "SELECT s FROM Shedule s WHERE s.pid = :pid"),
    @NamedQuery(name = "Shedule.findByTs", query = "SELECT s FROM Shedule s WHERE s.ts = :ts")})
public class Shedule implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "tdate")
    @Temporal(TemporalType.DATE)
    private Date tdate;
    @Column(name = "starttime")
    @Temporal(TemporalType.TIME)
    private Date starttime;
    @Column(name = "endtime")
    @Temporal(TemporalType.TIME)
    private Date endtime;
    @Size(max = 100)
    @Column(name = "task")
    private String task;

    @Column(name = "pid")
    private Integer pid;

    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;

    @Basic(optional = false)

    @Column(name = "ts")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ts;

    @JoinColumn(name = "projectid", referencedColumnName = "id")
    @ManyToOne
    private Projects projectid;
    @JoinColumn(name = "taskid", referencedColumnName = "id")
    @ManyToOne
    private Tasktype taskid;
    
    
    @JoinColumn(name = "task1id", referencedColumnName = "id")
    @ManyToOne
    private Tasktype1 task1id;
    @JoinColumn(name = "managerid", referencedColumnName = "id")
    @ManyToOne
    private Users managerid;

    @JoinColumn(name = "userid", referencedColumnName = "id")
    @ManyToOne
    private Users userid;

    @JoinColumn(name = "controlid", referencedColumnName = "id")
    @ManyToOne
    private Users controlid;

    @JoinColumn(name = "clientid", referencedColumnName = "id")
    @ManyToOne
    private Client clientid;

    @JoinColumn(name = "cpid", referencedColumnName = "id")
    @ManyToOne
    private Contactperson cpid;

    @JoinColumn(name = "exid", referencedColumnName = "id")
    @ManyToOne
    private Users exid;

    @JoinColumn(name = "statusid", referencedColumnName = "id")
    @ManyToOne
    private Taskstatus statusid;
    
  

    public Shedule() {
    }

    public Contactperson getCpid() {
        return cpid;
    }

    public void setCpid(Contactperson cpid) {
        this.cpid = cpid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Tasktype1 getTask1id() {
        return task1id;
    }

    public void setTask1id(Tasktype1 task1id) {
        this.task1id = task1id;
    }

    public Users getUserid() {
        return userid;
    }

    public void setUserid(Users userid) {
        this.userid = userid;
    }

    public Users getControlid() {
        return controlid;
    }

    public void setControlid(Users controlid) {
        this.controlid = controlid;
    }

    public Shedule(Integer id) {
        this.id = id;
    }

    public Shedule(Integer id, Date ts) {
        this.id = id;
        this.ts = ts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTdate() {
        return tdate;
    }

    public void setTdate(Date tdate) {
        this.tdate = tdate;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Client getClientid() {
        return clientid;
    }

    public void setClientid(Client clientid) {
        this.clientid = clientid;
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

    public Projects getProjectid() {
        return projectid;
    }

    public void setProjectid(Projects projectid) {
        this.projectid = projectid;
    }

    public Tasktype getTaskid() {
        return taskid;
    }

    public void setTaskid(Tasktype taskid) {
        this.taskid = taskid;
    }

    public Users getManagerid() {
        return managerid;
    }

    public void setManagerid(Users managerid) {
        this.managerid = managerid;
    }

    public Users getExid() {
        return exid;
    }

    public void setExid(Users exid) {
        this.exid = exid;
    }

    public Taskstatus getStatusid() {
        return statusid;
    }

    public void setStatusid(Taskstatus statusid) {
        this.statusid = statusid;
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
        if (!(object instanceof Shedule)) {
            return false;
        }
        Shedule other = (Shedule) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Shedule[ id=" + id + " ]";
    }

}
