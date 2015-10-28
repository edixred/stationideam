/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import clases.anioestacion;
import clases.variableEstacion;
import entidad.Stations;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author edixred
 */
@Stateless
public class StationsFacade extends AbstractFacade<Stations> {
    @PersistenceContext(unitName = "stationideamPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StationsFacade() {
        super(Stations.class);
    }
    
    public List<variableEstacion> getByVariableStations(int latitude, int longitude) {
        try {
            List<variableEstacion> listaEstaciones=new ArrayList<variableEstacion>();
            Query q = getEntityManager().createNativeQuery("SELECT \n"
                    + "	variable_id, name_variable\n"
                    + "FROM\n"
                    + "	stations\n"
                    + "NATURAL JOIN\n"
                    + "	samples\n"
                    + "NATURAL JOIN\n"
                    + "	variables\n"
                    + "WHERE\n"
                    + "	latitude_3857=? and longitude_3857=?");
            q.setParameter(1, latitude);
            q.setParameter(2, longitude);
            List<Object[]>lista=q.getResultList();
           
            for ( Object[] obj :lista) {
              
                variableEstacion est=new variableEstacion();
                est.setName_variable(obj[1].toString());
                est.setVariable_id(obj[0].toString());
                listaEstaciones.add(est);
            }
            return listaEstaciones ;
        } catch (Exception e) {
            return null;
        }
    }
    public List<anioestacion> getByYearVariableStations(int latitude, int longitude, int variable) {
        try {
            List<anioestacion> listaAnioEstaciones=new ArrayList<anioestacion>();
            Query qy = getEntityManager().createNativeQuery("SELECT \n"
                    + "	distinct year \n"
                    + "FROM\n"
                    + "	stations\n"
                    + "NATURAL JOIN\n"
                    + "	samples\n"
                    + "NATURAL JOIN\n"
                    + "	variables\n"
                    + "NATURAL JOIN\n"
                    + "	months\n"
                    + "WHERE\n"
                    + "	latitude_3857=? and longitude_3857=?\n"
                    + "	AND\n"
                    + "	variable_id=? order by year");
            qy.setParameter(1, latitude);
            qy.setParameter(2, longitude);
            qy.setParameter(3, variable);
            List<Object>lista=qy.getResultList();
           
            for ( Object obj :lista) {
              
                anioestacion anioest=new anioestacion();
                anioest.setAnio(obj.toString());
                listaAnioEstaciones.add(anioest);
            }
            return listaAnioEstaciones;
        } catch (Exception e) {
            return null;
        }
    }

    public List<anioestacion> getByMonthfromYearVariableStations(int latitude, int longitude, int variable, int year) {
        try {
            List<anioestacion> listaMesesAnioEstaciones = new ArrayList<anioestacion>();
            Query qm = getEntityManager().createNativeQuery("SELECT \n"
                    + "	month\n"
                    + " FROM\n"
                    + "	stations\n"
                    + " NATURAL JOIN\n"
                    + "	samples\n"
                    + " NATURAL JOIN\n"
                    + "	variables\n"
                    + " NATURAL JOIN\n"
                    + "	months\n"
                    + " WHERE\n"
                    + "	latitude_3857=?\n"
                    + "	AND longitude_3857=?\n"
                    + "	AND variable_id=? \n"
                    + "	AND year=? order by month");
            qm.setParameter(1, latitude);
            qm.setParameter(2, longitude);
            qm.setParameter(3, variable);
            qm.setParameter(4, year);
            List<Object> lista = qm.getResultList();

            for (Object obj : lista) {

                anioestacion mesesest = new anioestacion();
                mesesest.setAnio(obj.toString());
                listaMesesAnioEstaciones.add(mesesest);
            }
            System.out.println("MESES- " + listaMesesAnioEstaciones.size());
            return listaMesesAnioEstaciones;
        } catch (Exception e) {
            return null;
        }
    }

    public List<variableEstacion> getByUnitMeasureMonthfromYearVariableStations(int latitude, int longitude, int variable, int year, int month) {
        try {
            List<variableEstacion> listaEstaciones = new ArrayList<variableEstacion>();
            Query q = getEntityManager().createNativeQuery("SELECT \n"
                    + "	value_point, units\n"
                    + "FROM\n"
                    + "	stations\n"
                    + "NATURAL JOIN\n"
                    + "	samples\n"
                    + "NATURAL JOIN\n"
                    + "	variables\n"
                    + "NATURAL JOIN\n"
                    + "	months\n"
                    + "WHERE\n"
                    + "    latitude_3857=?\n"
                    + "    AND longitude_3857=?\n"
                    + "    AND variable_id=?\n"
                    + "    AND year=?\n"
                    + "    AND month=?");
            q.setParameter(1, latitude);
            q.setParameter(2, longitude);
            q.setParameter(3, variable);
            q.setParameter(4, year);
            q.setParameter(5, month);
            List<Object[]>lista=q.getResultList();
           
            for ( Object[] obj :lista) {
              
                variableEstacion est=new variableEstacion();
                est.setName_variable(obj[1].toString());
                est.setVariable_id(obj[0].toString());
                listaEstaciones.add(est);
            }
            return listaEstaciones ;
        } catch (Exception e) {
            return null;
        }
    }
}
