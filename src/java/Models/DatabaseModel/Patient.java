/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.DatabaseModel;

import Utils.Helper;
import java.sql.Date;
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
    private boolean removable;

    public Patient() {
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

    public void setRemovable(boolean removable) {
        this.removable = removable;
    }

    public boolean isRemovable() {
        return removable;
    }

    public Patient findPatient(int patientId) {

        String query = "SELECT  `patients`.`ID`"
                +               ",`patients`.`name`"
                + "FROM  	`patients`"
                + "WHERE `patients`.`ID` = '%d';";

        Utils.DBA dba = Helper.getDBA();
        ResultSet rs;
        try {
            rs = dba.executeQuery(String.format(query, patientId));

            while (rs.next()) {
                this.ID =(rs.getInt("id"));
                this.name = (rs.getString("name"));

            }
            rs.close();
            dba.closeConnections();
        } catch (SQLException sqlEx) {
            Helper.logException(sqlEx);
            dba.closeConnections();
        }

        return this;
    }

    public ArrayList<Patient> listAllPatients() {
        String query = "SELECT  `patients`.`ID`"
                + ",`patients`.`name`"
                + "FROM  	`patients`"
                + "LEFT JOIN `deletedPatient` ON `patients`.`ID` = `deletedPatient`.`patientId` "
                + "WHERE `deletedPatient`.`patientId` IS NULL;";

        ArrayList<Patient> allPatients = new ArrayList<>();
        Utils.DBA dba = Helper.getDBA();
        ResultSet rs;
        try {
            rs = dba.executeQuery(query);

            while (rs.next()) {
                Patient newPatient = new Patient();
                newPatient.setID(rs.getInt("id"));
                newPatient.setName(rs.getString("name"));

                allPatients.add(newPatient);
            }
            rs.close();
            dba.closeConnections();
        } catch (SQLException sqlEx) {
            Helper.logException(sqlEx);
            dba.closeConnections();
        }

        return allPatients;
    }

    public void removePatient(int patientID) {

        String query = "insert into `deletedPatient` (`patientId`, `removalDate`) "
                + "values ('%d', '%s');";

        Utils.DBA dba = Helper.getDBA();

        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());

        dba.executeUpdate(String.format(query, patientID, date.toString()));
        dba.closeConnections();

    }

}
