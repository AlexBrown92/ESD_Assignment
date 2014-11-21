/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseModel;

import Utils.Helper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author david
 */
public class Patient {
    private int ID;
    private String name;
    
    public Patient(){
        ID = 0;
        name = ""; 
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
   
    public ArrayList<Patient> listAllPatients(){
        String query = "SELECT * FROM patients";
        ArrayList<Patient>  allPatients = new ArrayList<>(); 
        Utils.DBA dba = Helper.getDBA();
        ResultSet rs;
        try{
            rs = Helper.getDBA().executeQuery(query);
            
            while(rs.next()){
                Patient newPatient = new Patient();
                newPatient.setID(rs.getInt("id"));
                newPatient.setName(rs.getString("name"));
            }
            
        } catch (SQLException sqlEx){
            Helper.logException(sqlEx);
            
        }
         
        return allPatients;
    }
}
