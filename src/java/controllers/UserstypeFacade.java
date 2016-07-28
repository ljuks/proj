/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.Userstype;

/**
 *
 * @author administrator
 */
@Stateless
public class UserstypeFacade extends AbstractFacade<Userstype> {

    @PersistenceContext(unitName = "projPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserstypeFacade() {
        super(Userstype.class);
    }
    
}
