package DatabaseModel;

import Utils.DBA;
import Utils.Helper;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Alex
 */
public class Bill {

    int id;
    int patientId;
    Timestamp dateCreated;
    Timestamp datePaid;
    int consultationCost;

    public Bill() {
        this.id = 0;
        this.patientId = 0;
        this.dateCreated = null;
        this.datePaid = null;
        this.consultationCost = 0;
    }

    public Bill(int id, int patientId, Timestamp dateCreated, Timestamp datePaid, int consultationCost) {
        this.id = id;
        this.patientId = patientId;
        this.dateCreated = dateCreated;
        this.datePaid = datePaid;
        this.consultationCost = consultationCost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Timestamp getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(Timestamp datePaid) {
        this.datePaid = datePaid;
    }

    public int getConsultationCost() {
        return consultationCost;
    }

    public void setConsultationCost(int consultationCost) {
        this.consultationCost = consultationCost;
    }

    public ArrayList<Bill> findUserPaidBill(int patientId) {
        return findUserBill(patientId, false);
    }

    public ArrayList<Bill> findUserOutstandingBill(int patientId) {
        return findUserBill(patientId, true);
    }

    private ArrayList<Bill> findUserBill(int patientId, boolean outStanding) {
        String query = "SELECT `bill`.`billId`, "
                + "     `bill`.`patientId`, "
                + "     `bill`.`dateCreated`, "
                + "     `bill`.`datePaid`, "
                + "     `bill`.`consulationCost` "
                + " FROM `bill` "
                + " WHERE `bill`.`patientId` = '%d'";
        if (outStanding) {
            query += " AND `bill`.`datePaid` IS NULL;";
        } else {
            query += " AND `bill`.`datePaid` IS NOT NULL;";
        }
        DBA dba = Helper.getDBA();

        ArrayList<Bill> listOfBills = new ArrayList<>();
        try {
            ResultSet rs = dba.executeQuery(String.format(query, patientId));

            if (rs != null) {

                while (rs.next()) {
                    Bill newBill = new Bill();

                    newBill.setId(rs.getInt("billId"));
                    newBill.setPatientId(rs.getInt("patientId"));
                    newBill.setDateCreated(rs.getTimestamp("dateCreated"));
                    newBill.setDatePaid(rs.getTimestamp("datePaid"));
                    newBill.setConsultationCost(rs.getInt("consultationCost"));

                    listOfBills.add(newBill);
                }

                rs.close();
            }
            dba.closeConnections();

        } catch (SQLException sqlEx) {
            dba.closeConnections();
            Helper.logException(sqlEx);
        }
        return listOfBills;
    }

    public Bill findBill(int billId) {
        String query = "SELECT `bill`.`billId`, "
                + "     `bill`.`patientId`, "
                + "     `bill`.`dateCreated`, "
                + "     `bill`.`datePaid`, "
                + "     `bill`.`consulationCost` "
                + " FROM `bill` "
                + " WHERE `bill`.`billId` = '%d'";
        DBA dba = Helper.getDBA();
        try {
            ResultSet rs = dba.executeQuery(String.format(query, billId));

            while (rs.next()) {
                this.id = rs.getInt("billId");
                this.patientId = rs.getInt("patientId");
                this.dateCreated = rs.getTimestamp("dateCreated");
                this.datePaid = rs.getTimestamp("datePaid");
                this.consultationCost = rs.getInt("consultationCost");
            }
            rs.close();
            dba.closeConnections();

        } catch (SQLException sqlEx) {
            dba.closeConnections();
            Helper.logException(sqlEx);
        }
        return this;
    }

}