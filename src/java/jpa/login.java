/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import beans.UsersController;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author administrator
 */
@Named(value = "login")
@RequestScoped
public class login {

    @Inject
    private jpa.pro p;
    @Inject
    private  UsersController usersController;

    /**
     * Creates a new instance of login
     */
    public login() {
    }

    private String currentuserPassword;

    private List<SelectItem> ff = new ArrayList<SelectItem>();
    private List<SelectItem> projlist = new ArrayList<SelectItem>();
    private String oldpass;
    private String newpass1;
    private String newpass2;
            

    public List<SelectItem> fff() {
        List<SelectItem> temp = new ArrayList<SelectItem>();
        temp.clear();
        // ff = null;
        EntityManager em = Persistence.createEntityManagerFactory("projPU").createEntityManager();
        TypedQuery<Users> query = em.createNamedQuery("Users.findAll", Users.class);
        List<Users> res1;
        res1 = query.getResultList();

        //       if (res1.size() != 0) {
        //           Iterator stIterator = res1.iterator();
        int i = 0;
        for (i = 0; i < res1.size(); i++) {

            temp.add(new SelectItem(res1.get(i).getId(), res1.get(i).getFio()));
        }

        return temp;

    }

        public String enter1() {

        if (p.getCurrentuserId() == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка", "Не выбран пользователь"));
            return "";
        }
        
        Users users = usersController.getUsers(p.getCurrentuserId());
        
       
        if ( !users.getPassword().equals(currentuserPassword)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка", "Неправильный старый пароль"));
        return "";
        }
        
        if ( newpass2.equals(newpass1)){
            
            usersController.setSelected(users);
            usersController.getSelected().setPassword(newpass1);
            usersController.update();
            return "/" + p.getDpass() + "/index";
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка", "Неверно введен новый пароль"));
        };
return "";
    }
    
    
    public String enter() {

        if (p.getCurrentuserId() == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка", "Не выбран пользователь"));
            return "";
        }
        EntityManager em = Persistence.createEntityManagerFactory("projPU").createEntityManager();

        TypedQuery<Users> query = em.createNamedQuery("Users.findById", Users.class);
        query.setParameter("id", p.getCurrentuserId());
        Users res1;
        res1 = query.getSingleResult();
        if (res1.getPassword() == null ? currentuserPassword == null : res1.getPassword().equals(currentuserPassword)) {
            p.setCurrentuserFio(res1.getFio());
            p.setCurrentuserRole(res1.getRoleid().getId());
            p.setDpass(Integer.toString(p.getCurrentuserRole()));
            return "/" + p.getDpass() + "/index";
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка", "Неправильный пароль"));
        return "";

    }

    public boolean f() {
        return true;
    }

    public String getCurrentuserPassword() {
        return currentuserPassword;
    }

    public void setCurrentuserPassword(String currentuserPassword) {
        this.currentuserPassword = currentuserPassword;
    }

    public List<SelectItem> getFf() {
        return fff();
    }

    public void setFf(List<SelectItem> ff) {

        this.ff = ff;
    }

    public pro getP() {
        return p;
    }

    public void setP(pro p) {
        this.p = p;
    }

    public String getOldpass() {
        return oldpass;
    }

    public void setOldpass(String oldpass) {
        this.oldpass = oldpass;
    }

    public String getNewpass1() {
        return newpass1;
    }

    public void setNewpass1(String newpass1) {
        this.newpass1 = newpass1;
    }

    public String getNewpass2() {
        return newpass2;
    }

    public void setNewpass2(String newpass2) {
        this.newpass2 = newpass2;
    }
    
    

    public List<SelectItem> getProjlist() {
        List<SelectItem> temp = new ArrayList<SelectItem>();
        temp.clear();
        // ff = null;
        EntityManager em = Persistence.createEntityManagerFactory("projPU").createEntityManager();
        TypedQuery<Projects> query = em.createNamedQuery("Projects.findAll", Projects.class);
        List<Projects> res1;
        res1 = query.getResultList();

        //       if (res1.size() != 0) {
        //           Iterator stIterator = res1.iterator();
     
        for (int i = 0; i < res1.size(); i++) {

            temp.add(new SelectItem(res1.get(i).getId(), res1.get(i).getName()));
        }

        return temp;
    }

    public void setProjlist(List<SelectItem> projlist) {
        this.projlist = projlist;
    }

}
