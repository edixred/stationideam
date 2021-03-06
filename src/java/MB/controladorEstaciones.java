/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MB;

import clases.anioestacion;
import clases.variableEstacion;
import dao.StationsFacade;
import java.util.ArrayList;
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
    private List<variableEstacion> listamedidaUnidades,listamedidaUnidadesAnual;
    private List<anioestacion> listaaniosVariableEstacion;
    private List<anioestacion> listamesesVariableEstacion;
    private variableEstacion estacion,valorUnidades;
    private anioestacion aniosestacion,mesesanioestacion;
    private String txtlatitude,txtlongotude;
    private String serieaniosvariable,listaaniosgrafico;
    private String seriemesvariable;
    
    @EJB
    private StationsFacade estacionEjb;
    
    @PostConstruct
    public void inicar() {
        listaEstaciones = new ArrayList<>();
        estacion=new variableEstacion();
        this.aniosestacion=new anioestacion();
        this.mesesanioestacion=new anioestacion();
    }
    public controladorEstaciones() {
    }
    public  void consultarEstacion(){
        listaEstaciones = estacionEjb.getByVariableStations(Integer.parseInt(txtlatitude.trim()), Integer.parseInt(txtlongotude.trim()));
        estacion=new variableEstacion();
    }
    public void consultarVariablesEstacion(){
        listaEstaciones = estacionEjb.getByVariableStations(Integer.parseInt(txtlatitude.trim()), Integer.parseInt(txtlongotude.trim()));
        estacion=new variableEstacion();
    }
    public void ListarAniosVariableEstacion() {
        listaaniosVariableEstacion = estacionEjb.getByYearVariableStations(Integer.parseInt(txtlatitude.trim()), Integer.parseInt(txtlongotude.trim()), Integer.parseInt(estacion.getVariable_id()));
        aniosestacion = new anioestacion();
        listaaniosgrafico="[";
        int listYearsStation[] = new int[listaaniosVariableEstacion.size()];
        for (int i = 0; i < listaaniosVariableEstacion.size(); i++) {
            listYearsStation[i] = Integer.parseInt(listaaniosVariableEstacion.get(i).getAnio());
            if(i==listaaniosVariableEstacion.size()-1){
                    listaaniosgrafico+=listaaniosVariableEstacion.get(i).getAnio().toString()+"]";
                }
                else{
                    listaaniosgrafico+=listaaniosVariableEstacion.get(i).getAnio().toString()+",";
                }
        }
        serieaniosvariable=estacionEjb.getAVGYearsStation(Integer.parseInt(txtlatitude.trim()), Integer.parseInt(txtlongotude.trim()),listYearsStation, Integer.parseInt(estacion.getVariable_id()));
        seriemesvariable=estacionEjb.getAVGMonthsStation(Integer.parseInt(txtlatitude.trim()), Integer.parseInt(txtlongotude.trim()),Integer.parseInt(estacion.getVariable_id()));
        //System.out.println("RESULTADO: "+serieaniosvariable);System.out.println("RESULTADO MESES: "+seriemesvariable);
    }
    
    public void listarMesesAnio(){
        listamesesVariableEstacion = estacionEjb.getByMonthfromYearVariableStations(Integer.parseInt(txtlatitude), Integer.parseInt(txtlongotude),Integer.parseInt(estacion.getVariable_id()),Integer.parseInt(aniosestacion.getAnio()));
        mesesanioestacion=new anioestacion();
    }
    public void consultarDatosEstacion(){
        listamedidaUnidades=estacionEjb.getByUnitMeasureMonthfromYearVariableStations(Integer.parseInt(txtlatitude), Integer.parseInt(txtlongotude),Integer.parseInt(estacion.getVariable_id()),Integer.parseInt(aniosestacion.getAnio()),Integer.parseInt(mesesanioestacion.getAnio()));
    }
    public void consultarDatosAnualEstacion(){
        listamedidaUnidadesAnual=estacionEjb.getByUnitMeasureYearfromYearVariableStations(Integer.parseInt(txtlatitude), Integer.parseInt(txtlongotude),Integer.parseInt(estacion.getVariable_id()),Integer.parseInt(aniosestacion.getAnio()));
    }

    public List<variableEstacion> getListamedidaUnidadesAnual() {
        return listamedidaUnidadesAnual;
    }

    public void setListamedidaUnidadesAnual(List<variableEstacion> listamedidaUnidadesAnual) {
        this.listamedidaUnidadesAnual = listamedidaUnidadesAnual;
    }

    
    public List<variableEstacion> getListamedidaUnidades() {
        return listamedidaUnidades;
    }

    public void setListamedidaUnidades(List<variableEstacion> listamedidaUnidades) {
        this.listamedidaUnidades = listamedidaUnidades;
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

    public String getTxtlatitude() {
        return txtlatitude;
    }

    public void setTxtlatitude(String txtlatitude) {
        this.txtlatitude = txtlatitude;
    }

    public String getTxtlongotude() {
        return txtlongotude;
    }

    public void setTxtlongotude(String txtlongotude) {
        this.txtlongotude = txtlongotude;
    }


    public variableEstacion getValorUnidades() {
        return valorUnidades;
    }

    public void setValorUnidades(variableEstacion valorUnidades) {
        this.valorUnidades = valorUnidades;
    }

    public String getSerieaniosvariable() {
        return serieaniosvariable;
    }

    public void setSerieaniosvariable(String serieaniosvariable) {
        this.serieaniosvariable = serieaniosvariable;
    }

    public String getListaaniosgrafico() {
        return listaaniosgrafico;
    }

    public void setListaaniosgrafico(String listaaniosgrafico) {
        this.listaaniosgrafico = listaaniosgrafico;
    }

    public String getSeriemesvariable() {
        return seriemesvariable;
    }

    public void setSeriemesvariable(String seriemesvariable) {
        this.seriemesvariable = seriemesvariable;
    }
    
    
}
