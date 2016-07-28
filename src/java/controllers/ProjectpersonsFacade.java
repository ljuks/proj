/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.Projectpersons;

/**
 *
 * @author administrator
 */
@Stateless
public class ProjectpersonsFacade extends AbstractFacade<Projectpersons> {

    @PersistenceContext(unitName = "projPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProjectpersonsFacade() {
        super(Projectpersons.class);
    }
    
}
