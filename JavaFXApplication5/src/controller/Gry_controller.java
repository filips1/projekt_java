/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.PrintWriter;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import static javafx.application.ConditionalFeature.FXML;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Konsole;


;
import model.KonsoleDAO;
import static model.KonsoleDAO.isNumeric;
import util.DBUtil;

/**
 *
 * @author ACER
 */
public class Gry_controller {

    @FXML
    private TextField txtnazwa_k;
    @FXML
    private TextField txtprod;
    @FXML
    private TextField txtrok_p;
    @FXML
    private TextArea resultConsole;
    @FXML
    private TextField searchIDGry;
    @FXML
    private TextField searchRokP;
    @FXML
        private TextField searchNazwaG;
     @FXML
        private TextField searchProd;
   
    @FXML
    private TableColumn<Konsole, Integer> colGraId;
    @FXML
    private TableColumn<Konsole, Integer> colGraRok;
    @FXML
    private TableColumn<Konsole, String> colKonNazwa;
    @FXML
    private TableColumn<Konsole, String> colGraProd;
    @FXML
    private TableColumn<Konsole, String> colGNazwa;
    @FXML
    private TableColumn<Konsole, Integer> colKonID;
    @FXML
    

    private TableView GraTable;
    @FXML
    private ChoiceBox choicebox;
     @FXML
    private ChoiceBox choiceboxes;
    
    

@FXML
    private void wstawgre(ActionEvent event) throws SQLException, ClassNotFoundException, Exception {
              
                if("".equals(txtnazwa_k.getText())){
                    resultConsole.setText("Podaj nazwę gry");
                }
                 else if(choicebox.getValue()==null){
                        resultConsole.setText("Podaj konsolę");     
            }
                else if(!isNumeric(txtrok_p.getText())&&!txtrok_p.getText().equals("")){
                    resultConsole.setText("Zostały użyte nieodpowiednie znaki w 'roku produkcji'");
                }
                else{       
        try {
            KonsoleDAO.wstawkonsole(txtnazwa_k.getText(), txtprod.getText(), txtrok_p.getText(),"Gra",choicebox.getValue().toString());
            resultConsole.setText("Udało Się :))))))))))))  Wartości zostały wprowadzone do bazy");
           ObservableList<Konsole> konlist = KonsoleDAO.getAllRecordsG();
            populateTable(konlist);
          
        } catch (SQLException e) {
        //    System.out.println(kon.get(0));
            System.out.println("Błąd nastąpił podczas wstawiania");
            e.printStackTrace();
            throw e;
        }
    }
    }
    @FXML
    private void updateGre(ActionEvent event) throws SQLException, ClassNotFoundException, Exception {
                if(!isNumeric(searchIDGry.getText())){
                        resultConsole.setText("Podaj id w postaci liczbowej");     
            }
                else{
        try {
            KonsoleDAO.updatenazkonsole(Integer.parseInt(searchIDGry.getText()), searchNazwaG.getText(), searchRokP.getText(),searchProd.getText(), choiceboxes.getValue().toString(),"gra");
            resultConsole.setText("Udało się :) update się powiódł");
       ObservableList<Konsole> konlist = KonsoleDAO.wyszukajgre(searchIDGry.getText());
     populateTable(konlist);
        } catch (SQLException e) {
            System.out.println("błąd podczas updateowania");
        }
                }
    }

    @FXML
    private void usunGre(ActionEvent event) throws SQLException, ClassNotFoundException, Exception {
        if(!isNumeric(searchIDGry.getText())){
                        resultConsole.setText("Podaj id w postaci liczbowej");}
        else{
        try {
             
            
            KonsoleDAO.usungrebyID(Integer.parseInt(searchIDGry.getText()));
            resultConsole.setText("Udało się :) usunięto rekord");
            ObservableList<Konsole> konlist = KonsoleDAO.getAllRecordsG();
            populateTable(konlist);}
         catch (SQLException e) {
            System.out.println("Błąd podczas usuwania");

                 }}
    }
    
@FXML
    private void inicjalizacja() throws Exception {
        colGraId.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asObject());
        colKonNazwa.setCellValueFactory(cellData -> cellData.getValue().getNazwaProperty());
        colGraProd.setCellValueFactory(cellData -> cellData.getValue().getProducentProperty());
        colGraRok.setCellValueFactory(cellData -> cellData.getValue().getRokProperty().asObject());
        colGNazwa.setCellValueFactory(cellData -> cellData.getValue().getIdkProperty());
        colKonID.setCellValueFactory(cellData -> cellData.getValue().getIdkonProperty().asObject());
        ObservableList<Konsole> konlist = KonsoleDAO.getAllRecordsG();
        populateTable(konlist);
    }
    
    private void populateTable(ObservableList<Konsole> konlist) {
       GraTable.setItems(konlist);
    }
    
    @FXML
       private void PrzeszukajGry(ActionEvent event) throws SQLException, ClassNotFoundException {
           
             if(!isNumeric(searchIDGry.getText())){
                        resultConsole.setText("Podaj id w postaci liczbowej");     
            }
             else{
             colGraId.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asObject());
        colKonNazwa.setCellValueFactory(cellData -> cellData.getValue().getNazwaProperty());
        colGraProd.setCellValueFactory(cellData -> cellData.getValue().getProducentProperty());
        colGraRok.setCellValueFactory(cellData -> cellData.getValue().getRokProperty().asObject());
         colGNazwa.setCellValueFactory(cellData -> cellData.getValue().getIdkProperty());
           colKonID.setCellValueFactory(cellData -> cellData.getValue().getIdkonProperty().asObject());
        ObservableList<Konsole>list=KonsoleDAO.wyszukajgre(searchIDGry.getText());
      
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
private AnchorPane newPane;

@FXML
public void initialize() throws SQLException, ClassNotFoundException{
    konsole();
}

public void konsole()throws SQLException, ClassNotFoundException {
     
            try {
       ArrayList<String>list= KonsoleDAO.dodKonsole();
            choicebox.getItems().addAll(list);
            choiceboxes.getItems().addAll(list);
        } catch (SQLException e) {
            System.out.println("Błąd ");

        }
  
}

    @FXML 
    public void btnConnect() throws Exception {
        AnchorPane pane=FXMLLoader.load(getClass().getResource("KonsoleView.fxml"));
        
        newPane.getChildren().setAll(pane);
    }

    public void clear(){
        txtnazwa_k.clear();

    txtprod.clear();
    
  txtrok_p.clear();
  choicebox.setValue(null);
    }
     public void clearsear(){
         searchIDGry.clear();
                 searchNazwaG.clear();
                 searchRokP.clear();
                         searchProd.clear();
                          choiceboxes.setValue(null);
     }
}

