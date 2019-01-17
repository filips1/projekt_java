/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author ACER
 */
public class Konsole {                                                         
    private IntegerProperty idProperty;
    private StringProperty nazwaProperty;
    private IntegerProperty rokProperty;
    private StringProperty producentProperty;
    private StringProperty idkProperty;
    private IntegerProperty idkonProperty;
    
    public Konsole(){
        this.idProperty= new SimpleIntegerProperty();
        this.nazwaProperty= new SimpleStringProperty();
        this.producentProperty = new SimpleStringProperty();
        this.rokProperty= new SimpleIntegerProperty();
         this.idkProperty= new SimpleStringProperty();
         this.idkonProperty= new SimpleIntegerProperty();
    }

    public IntegerProperty getRokProperty() {                                    //settery i gettery 
        return rokProperty;
    }
    public int getRokKonsoli() {
        return rokProperty.get();
    }

    public void setRokKonsoli(int rok) {
        this.rokProperty.set(rok);
    }


    public int getIdKonsoli() {
        return idProperty.get();
    }

    public void setIdKonsoli(int id) {
        this.idProperty.set(id);
    }
    public IntegerProperty getIdProperty() {
        return idProperty;
    }
     public int getIdkonKonsoli() {
        return idkonProperty.get();
    }

    public void setIdkonKonsoli(int id) {
        this.idkonProperty.set(id);
    }
    public IntegerProperty getIdkonProperty() {
        return idkonProperty;
    }
     public String getIdkKonsoli() {
        return idkProperty.get();
    }

    public void setIdkKonsoli(String id) {
        this.idkProperty.set(id);
    }
    public StringProperty getIdkProperty() {
        return idkProperty;
    }
    
    public StringProperty getNazwaProperty() {
        return nazwaProperty;
    }

       public String getNazwaKonsoli() {
        return nazwaProperty.get();
    }

    public void setNazwaKonsoli(String nazwa) {
        this.nazwaProperty.set(nazwa);
    }

    public StringProperty getProducentProperty() {
        return producentProperty;
    }

       public String getProducentKonsoli() {
        return producentProperty.get();
    }

    public void setProducentKonsoli(String prod) {
        this.producentProperty.set(prod);
    }


}
