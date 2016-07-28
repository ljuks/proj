/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author administrator
 */
@Named(value = "pro")
@SessionScoped

public class pro implements Serializable {

    /**
     * Creates a new instance of pro
     */
    public pro() {

    }

    private int currentuserId;
    private int currentuserRole;
    private String dpass;
    private String currentuserFio;

  ///////////////////////
    

    
    
    
    
    
    
    
    
    
    
    
   ////////////////////////////// 
    




    public int getCurrentuserId() {
        return currentuserId;
    }

    public void setCurrentuserId(int currentuserId) {
        this.currentuserId = currentuserId;
    }

    public int getCurrentuserRole() {
        return currentuserRole;
    }

    public void setCurrentuserRole(int currentuserRole) {
        this.currentuserRole = currentuserRole;
    }

    public String getDpass() {
        return dpass;
    }

    public void setDpass(String dpass) {
        this.dpass = dpass;
    }



    public String getCurrentuserFio() {
        return currentuserFio;
    }

    public void setCurrentuserFio(String currentuserFio) {
        this.currentuserFio = currentuserFio;
    }

}
