/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.Shedule;

/**
 *
 * @author administrator
 */
@Stateless
public class SheduleFacade extends AbstractFacade<Shedule> {

    @PersistenceContext(unitName = "projPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SheduleFacade() {
        super(Shedule.class);
    }
    
}
