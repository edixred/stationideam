/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidad.Variables;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author edixred
 */
@Stateless
public class VariablesFacade extends AbstractFacade<Variables> {
    @PersistenceContext(unitName = "stationideamPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VariablesFacade() {
        super(Variables.class);
    }
    
}
