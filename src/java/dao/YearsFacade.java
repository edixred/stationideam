/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidad.Years;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author edixred
 */
@Stateless
public class YearsFacade extends AbstractFacade<Years> {
    @PersistenceContext(unitName = "stationideamPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public YearsFacade() {
        super(Years.class);
    }
    
}
