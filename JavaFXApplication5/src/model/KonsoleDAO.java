/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DBUtil;

/**
 *
 * @author ACER
 */
public class KonsoleDAO {

    public static void wstawkonsole(String nazk, String prod, String rokp, String name, String nk) throws ClassNotFoundException, SQLException {        
        if (name == "Konsola") {
            String sql;
            if (isNumeric(rokp)) {
            int foo = Integer.parseInt(rokp);
             sql = "insert into konsole(Nazwa_konsoli, Producent_konsoli,Rok_produkcji)values('" + nazk + "','" + prod + "','" + rokp + "');";          
            }
            else{
                sql = "insert into konsole(Nazwa_konsoli, Producent_konsoli)values('" + nazk + "','" + prod +"');";
            }
            try {
                DBUtil.dbExcecuteQuery(sql);
            } catch (SQLException e) {
                System.out.println("Błąd podczas wstawiania danych");
                e.printStackTrace();
                throw e;
            }
            
        } else {
            int id = nazwaktoid(nk);
            String sql;
            if (isNumeric(rokp)) {
                
                int foo = Integer.parseInt(rokp);

                 sql = "insert into gry (Nazwa_gry, Producent_gry,Rok_produkcji,ID_konsoli )values('" + nazk + "','" + prod + "','" + rokp + "','" + id + "');";
                
            } else {
                 sql = "insert into gry (Nazwa_gry, Producent_gry,ID_konsoli )values('" + nazk + "','" + prod + "','" + id + "');";
            }
            try {
                    DBUtil.dbExcecuteQuery(sql);
                } catch (SQLException e) {
                    System.out.println("Błąd podczas wstawiania danych");
                    e.printStackTrace();
                    throw e;
                }

        }
    }

    public static int nazwaktoid(String nk) throws SQLException, ClassNotFoundException {
        String sql = "select ID_konsoli from Konsole where Nazwa_konsoli='" + nk + "';";
        ResultSet rsSet = DBUtil.dbExecute(sql);
        try {
            ArrayList<Integer> kon = new ArrayList();
            while (rsSet.next()) {
                kon.add(rsSet.getInt("ID_konsoli"));
            }

            return Integer.parseInt(kon.get(0).toString());
        } catch (SQLException e) {
            System.out.println("Błąd podczas wstawiania danych");
            e.printStackTrace();
            throw e;
        }

    }
        public static String idtonazwa(String id) throws SQLException, ClassNotFoundException {
            
        String sql = "select Nazwa_konsoli from Konsole where ID_konsoli='" + Integer.parseInt(id) + "';";
        ResultSet rsSet = DBUtil.dbExecute(sql);
        try {
            ArrayList<Integer> kon = new ArrayList();
            while (rsSet.next()) {
                kon.add(rsSet.getInt("Nazwa_konsoli"));
            }

            return kon.get(0).toString();
        } catch (SQLException e) {
            System.out.println("Błąd podczas wstawiania danych");
            e.printStackTrace();
            throw e;
        }

    }

    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    public static String polaczenie(String a, String b){
        return a+b;
    }

    public static void updatenazkonsole(int id, String nazwa, String rok,String prod,String kon,String rodz) throws ClassNotFoundException, SQLException {
        String sql;
        sql = "";
        if (rodz.contains("konsola")){
             try {
        if (!"".equals(nazwa)) {
            sql = "update konsole set Nazwa_konsoli= '" + nazwa+"' where ID_konsoli='"+ id + "'" ;
            DBUtil.dbExcecuteQuery(sql);
        }
        if (!"".equals(rok)&&isNumeric(rok)) {
            sql = "update konsole set Rok_produkcji= '" + Integer.parseInt(rok) +"' where ID_konsoli='"+ id + "'" ;
        DBUtil.dbExcecuteQuery(sql);
        }
        if (!"".equals(prod)) {
            sql = "update konsole set Producent_konsoli= '" + prod +"' where ID_konsoli='"+ id + "'" ;
        DBUtil.dbExcecuteQuery(sql);
        }
       
        } catch (SQLException e) {
        System.out.println("Błąd podczas updateowania recordu");
        e.printStackTrace();
        throw e;
        }
        }
        else{
                     try {
        if (!"".equals(nazwa)) {
            sql = "update gry set Nazwa_gry= '" + nazwa+"' where ID_gry='"+ id + "'" ;
            DBUtil.dbExcecuteQuery(sql);
        }
        if (!"".equals(rok)&&isNumeric(rok)) {
            sql = "update gry set Rok_produkcji= '" + Integer.parseInt(rok) +"' where ID_gry='"+ id + "'" ;
        DBUtil.dbExcecuteQuery(sql);
        }
        if (!"".equals(prod)) {
            sql = "update gry set Producent_gry= '" + prod +"' where ID_gry='"+ id + "'" ;
        DBUtil.dbExcecuteQuery(sql);
        }
        if (!"".equals(kon)) 
        {
            
            sql = "update gry set Id_konsoli= '" + nazwaktoid(kon) +"' where ID_gry='"+ id + "'" ;
        DBUtil.dbExcecuteQuery(sql);
        }
       
        } catch (SQLException e) {
        System.out.println("Błąd podczas updateowania recordu");
        e.printStackTrace();
        throw e;
        }
                
                }
            }
       

    
    
    public static void usunkonsolebyID(int id) throws ClassNotFoundException, SQLException {
        String sql = "delete from konsole where ID_konsoli ='" + id + "'";
         String sql2 = "delete from gry where ID_konsoli ='" + id + "'";
        try {
            DBUtil.dbExcecuteQuery(sql);
            DBUtil.dbExcecuteQuery(sql2);
        } catch (SQLException e) {
            System.out.println("Błąd podczas usuwania recordu");
            e.printStackTrace();
            throw e;
        }
    }
    public static void usungrebyID(int id) throws ClassNotFoundException, SQLException {
      
         String sql = "delete from gry where ID_gry ='" + id + "'";
        try {
            DBUtil.dbExcecuteQuery(sql);
        } catch (SQLException e) {
            System.out.println("Błąd podczas usuwania recordu");
            e.printStackTrace();
            throw e;
        }
    }
    
    public static ObservableList<Konsole> getAllRecords() throws ClassNotFoundException, SQLException {
        String sql = "select * from Konsole order by ID_konsoli";
        try {
            ResultSet rsSet = DBUtil.dbExecute(sql);
            ObservableList<Konsole> Konlist = getKonsoleObjects(rsSet);
            return Konlist;
        } catch (SQLException e) {
            System.out.println("Błąd nastąpił podczas pobierania danych z bazy danych" + e);
            e.printStackTrace();
            throw e;
        }
    }

    private static ObservableList<Konsole> getKonsoleObjects(ResultSet rsSet) throws SQLException, ClassNotFoundException {
        try {
            ObservableList<Konsole> konlist = FXCollections.observableArrayList();
            while (rsSet.next()) {
                Konsole kon = new Konsole();
                kon.setIdKonsoli(rsSet.getInt("ID_konsoli"));
                kon.setNazwaKonsoli(rsSet.getString("Nazwa_konsoli"));
                kon.setProducentKonsoli(rsSet.getString("Producent_konsoli"));
                kon.setRokKonsoli(rsSet.getInt("Rok_produkcji"));
                konlist.add(kon);
            }
            return konlist;
        } catch (SQLException e) {
            System.out.println("Błąd nastąpił podczas pobierania danych z bazy danych" + e);
            e.printStackTrace();
            throw e;
        }
    }
       public static ObservableList<Konsole> getAllRecordsG() throws ClassNotFoundException, SQLException {
        String sql = "select gry.ID_gry, gry.Nazwa_gry, gry.Producent_gry, gry.Rok_produkcji, konsole.Nazwa_konsoli, gry.ID_konsoli from Gry, Konsole where konsole.ID_konsoli=gry.ID_konsoli order by ID_gry";
        try {
            ResultSet rsSet = DBUtil.dbExecute(sql);
            ObservableList<Konsole> Konlist = getGameObjects(rsSet);
            return Konlist;
        } catch (SQLException e) {
            System.out.println("Błąd nastąpił podczas pobierania danych z bazy danych" + e);
            e.printStackTrace();
            throw e;
        }
    }

    private static ObservableList<Konsole> getGameObjects(ResultSet rsSet) throws SQLException, ClassNotFoundException {
        try {
            ObservableList<Konsole> konlist = FXCollections.observableArrayList();
            while (rsSet.next()) {
                Konsole kon = new Konsole();
                kon.setIdKonsoli(rsSet.getInt("ID_gry"));
                kon.setNazwaKonsoli(rsSet.getString("Nazwa_gry"));
                kon.setProducentKonsoli(rsSet.getString("Producent_gry"));
                kon.setRokKonsoli(rsSet.getInt("Rok_produkcji"));
               kon.setIdkKonsoli(rsSet.getString("Nazwa_konsoli"));
               kon.setIdkonKonsoli(rsSet.getInt("ID_konsoli"));
                konlist.add(kon);
            }
            
            return konlist;
        } catch (SQLException e) {
            System.out.println("Błąd nastąpił podczas pobierania danych z bazy danych" + e);
            e.printStackTrace();
            throw e;
        }
    }

    public static ObservableList<Konsole> wyszukajKonsole(String IdKonsoli) throws SQLException, ClassNotFoundException {
        String sql = "select * from Konsole where ID_konsoli=" + IdKonsoli;
        try {
            ResultSet rsSet = DBUtil.dbExecute(sql);
            ObservableList<Konsole> list = getKonsoleObjects(rsSet);
            return list;
        } catch (SQLException e) {
            System.out.println("Błąd nastąpił podczas pobierania danych z bazy danych" + e);
            e.printStackTrace();
            throw e;
        }

    }
       public static ObservableList<Konsole> wyszukajgre(String Idgry) throws SQLException, ClassNotFoundException {
        String sql= "select gry.ID_gry, gry.Nazwa_gry, gry.Producent_gry, gry.Rok_produkcji, konsole.Nazwa_konsoli, gry.ID_konsoli from Gry, Konsole where konsole.ID_konsoli=gry.ID_konsoli and gry.ID_gry="+Integer.parseInt(Idgry);
        try {
            ResultSet rsSet = DBUtil.dbExecute(sql);
            ObservableList<Konsole> list = getGameObjects(rsSet);
            return list;
        } catch (SQLException e) {
            System.out.println("Błąd nastąpił podczas pobierania danych z bazy danych" + e);
            e.printStackTrace();
            throw e;
        }

    }

    public static int Getidk(String nazwak) throws SQLException, ClassNotFoundException {
        String sql = "select * from Konsole where Nazwa_Konsoli=" + nazwak;
        try {
            ResultSet rsSet = DBUtil.dbExecute(sql);

            return rsSet.getInt("ID_konsoli");
        } catch (SQLException e) {
            System.out.println("Błąd nastąpił podczas pobierania danych z bazy danych" + e);
            e.printStackTrace();
            throw e;
        }

    }

    public static ArrayList<String> dodKonsole() throws SQLException, ClassNotFoundException {
        String sql = "select Nazwa_konsoli from Konsole";
        try {
            ResultSet rsSet = DBUtil.dbExecute(sql);

            ArrayList<String> kon = new ArrayList();
            while (rsSet.next()) {

                kon.add(rsSet.getString("Nazwa_konsoli"));

            }

            return kon;
        } catch (SQLException e) {
            System.out.println("Błąd nastąpił podczas pobierania danych z bazy danych" + e);
            e.printStackTrace();
            throw e;
        }

    }

}
