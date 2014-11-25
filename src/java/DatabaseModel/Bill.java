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
