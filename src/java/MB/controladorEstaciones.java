/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MB;

import clases.anioestacion;
import clases.variableEstacion;
import dao.StationsFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author edixred
 */
@ManagedBean
@ViewScoped
public class controladorEstaciones {

    /**
     * Creates a new instance of controladorEstaciones
     */
    private List<variableEstacion> listaEstaciones;
    private List<anioestacion> listaaniosVariableEstacion;
    private List<anioestacion> listamesesVariableEstacion;
    private variableEstacion estacion;
    private anioestacion aniosestacion,mesesanioestacion;
    @EJB
    private StationsFacade estacionEjb;
    
    @PostConstruct
    public void inicar() {
        listaEstaciones = estacionEjb.getByVariableStations(-8572050, 155700);
        estacion=new variableEstacion();
        System.out.println("variable "+listaEstaciones.size());
    }
    public controladorEstaciones() {
    }
    
    public void ListarAniosVariableEstacion()
    {
        listaaniosVariableEstacion = estacionEjb.getByYearVariableStations(-8572050, 155700,1);
        aniosestacion=new anioestacion();
        System.out.println("anios "+listaaniosVariableEstacion.size());
    }
    
    public void ListarMesesAniosVariableEstacion()
    {
        listamesesVariableEstacion = estacionEjb.getByMonthfromYearVariableStations(-8572050, 155700,1,1965);
        mesesanioestacion=new anioestacion();
        System.out.println("meses "+listamesesVariableEstacion.size());
    }

    public List<variableEstacion> getListaEstaciones() {
        return listaEstaciones;
    }

    public void setListaEstaciones(List<variableEstacion> listaEstaciones) {
        this.listaEstaciones = listaEstaciones;
    }

    public List<anioestacion> getListaaniosVariableEstacion() {
        return listaaniosVariableEstacion;
    }

    public void setListaaniosVariableEstacion(List<anioestacion> listaaniosVariableEstacion) {
        this.listaaniosVariableEstacion = listaaniosVariableEstacion;
    }

    public List<anioestacion> getListamesesVariableEstacion() {
        return listamesesVariableEstacion;
    }

    public void setListamesesVariableEstacion(List<anioestacion> listamesesVariableEstacion) {
        this.listamesesVariableEstacion = listamesesVariableEstacion;
    }

   

    public variableEstacion getEstacion() {
        return estacion;
    }

    public void setEstacion(variableEstacion estacion) {
        this.estacion = estacion;
    }

    public anioestacion getAniosestacion() {
        return aniosestacion;
    }

    public void setAniosestacion(anioestacion aniosestacion) {
        this.aniosestacion = aniosestacion;
    }

    public anioestacion getMesesanioestacion() {
        return mesesanioestacion;
    }

    public void setMesesanioestacion(anioestacion mesesanioestacion) {
        this.mesesanioestacion = mesesanioestacion;
    }
    
    
}
