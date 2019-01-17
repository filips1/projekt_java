/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import static javafx.application.ConditionalFeature.FXML;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import model.Konsole;
import model.KonsoleDAO;
import static model.KonsoleDAO.isNumeric;

/**
 *
 * @author ACER
 */
public class Konsole_controller {

    @FXML
    private TextField txtnazwa_k;
    @FXML
    private TextField txtprod;
    @FXML
    private TextField txtrok_p;
    @FXML
    private TextArea resultConsole;
    @FXML
    private TextField searchIDkonsoli;
    @FXML
    private TextField searchRokP;
    @FXML
    private TextField searchNazwaK;
     @FXML
    private TextField searchprod;
    @FXML
    private TableColumn<Konsole, Integer> colKonId;
    @FXML
    private TableColumn<Konsole, Integer> colKonRok;
    @FXML
    private TableColumn<Konsole, String> colKonNazwa;
    @FXML
    private TableColumn<Konsole, String> colKonProd;
    @FXML
    private TableView KonsoleTable;
    @FXML
    private Button button;

@FXML
    private void wstawkonsole(ActionEvent event) throws SQLException, ClassNotFoundException, Exception {
        if("".equals(txtnazwa_k.getText())){
                    resultConsole.setText("Podaj nazwę konsoli");
                }
                else if(!isNumeric(txtrok_p.getText())&&!txtrok_p.getText().equals("")){
                    resultConsole.setText("Zostały użyte nieodpowiednie znaki w 'roku produkcji'");
                }
                else{       
        try {
            KonsoleDAO.wstawkonsole(txtnazwa_k.getText(), txtprod.getText(), txtrok_p.getText(),"Konsola","");
            resultConsole.setText("Udało Się :)))))))))))) Wartości zostały wprowadzone do bazy");
            ObservableList<Konsole> konlist = KonsoleDAO.getAllRecords();
            populateTable(konlist);
        } catch (SQLException e) {
            System.out.println("Błąd nastąpił podczas wstawiania");
            e.printStackTrace();
            throw e;
        }
        

    }
    }

    @FXML
    private void updateKonsole(ActionEvent event) throws SQLException, ClassNotFoundException, Exception {
  
            if(!isNumeric(searchIDkonsoli.getText())){
                        resultConsole.setText("Podaj id w postaci liczbowej");     
            }
                else{
        try {
            KonsoleDAO.updatenazkonsole(Integer.parseInt(searchIDkonsoli.getText()), searchNazwaK.getText(), searchRokP.getText(),searchprod.getText(),"","konsola");
            resultConsole.setText("Udało się :) update się powiódł");
            ObservableList<Konsole> konlist = KonsoleDAO.wyszukajKonsole(searchIDkonsoli.getText());
            populateTable(konlist);
        } catch (SQLException e) {
            System.out.println("błąd podczas updateowania");
        }
    }
    }
    @FXML
    private void usunkonsole(ActionEvent event) throws SQLException, ClassNotFoundException, Exception {
         if(!isNumeric(searchIDkonsoli.getText())){
                        resultConsole.setText("Podaj id w postaci liczbowej");     
            }
                else{
        try {
            KonsoleDAO.usunkonsolebyID(Integer.parseInt(searchIDkonsoli.getText()));
            resultConsole.setText("Udało się :) usunięto rekord");
            ObservableList<Konsole> konlist = KonsoleDAO.getAllRecords();
            populateTable(konlist);
        } catch (SQLException e) {
            System.out.println("Błąd podczas usuwania");

        }
    }
    }
@FXML
    private void inicjalizacja() throws Exception {
        colKonId.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asObject());
        colKonNazwa.setCellValueFactory(cellData -> cellData.getValue().getNazwaProperty());
        colKonProd.setCellValueFactory(cellData -> cellData.getValue().getProducentProperty());
        colKonRok.setCellValueFactory(cellData -> cellData.getValue().getRokProperty().asObject());
        ObservableList<Konsole> konlist = KonsoleDAO.getAllRecords();
        populateTable(konlist);
    }
    
    private void populateTable(ObservableList<Konsole> konlist) {
        KonsoleTable.setItems(konlist);
      
    }
    @FXML
       private void PrzeszukajKonsole(ActionEvent event) throws SQLException, ClassNotFoundException {
             colKonId.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asObject());
        colKonNazwa.setCellValueFactory(cellData -> cellData.getValue().getNazwaProperty());
        colKonProd.setCellValueFactory(cellData -> cellData.getValue().getProducentProperty());
        colKonRok.setCellValueFactory(cellData -> cellData.getValue().getRokProperty().asObject());
         if(!isNumeric(searchIDkonsoli.getText())){
                        resultConsole.setText("Podaj id w postaci liczbowej");     
            }
         else{
        ObservableList<Konsole>list=KonsoleDAO.wyszukajKonsole(searchIDkonsoli.getText());
      
        if(list.size()>0){
            populateTable(list);
            resultConsole.setText("Rekord znaleziony");
        }
        else{
             populateTable(list);
            resultConsole.setText("Nie znaleziono");
        }
        
    }
       }
@FXML
private AnchorPane rootPane;

    @FXML 
    private void btnConnect() throws Exception {
        AnchorPane pane=FXMLLoader.load(getClass().getResource("GryView.fxml"));
        rootPane.getChildren().setAll(pane);
       
    }
        public void clear(){
        txtnazwa_k.clear();

    txtprod.clear();
    
  txtrok_p.clear();
  
    }
         public void clearsear(){
         searchIDkonsoli.clear();
                 searchNazwaK.clear();
                 searchRokP.clear();
                         searchprod.clear();
     }
}
