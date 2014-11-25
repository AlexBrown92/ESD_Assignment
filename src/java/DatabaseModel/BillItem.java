/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseModel;

import Utils.DBA;
import Utils.Helper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Alex
 */
public class BillItem {

    int billId;
    int medicineId;
    int quanitity;

    public BillItem() {
        this.billId = 0;
        this.medicineId = 0;
        this.quanitity = 0;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public int getQuanitity() {
        return quanitity;
    }

    public void setQuanitity(int quanitity) {
        this.quanitity = quanitity;
    }

    public BillItem findBillItem(int billId, int medicineId) {
        String query = "SELECT `billItem`.`billId`, "
                + "         `billItem`.`medicineId`, "
                + "         `billItem`.`quantity` "
                + "     FROM `billItem` "
                + "     WHERE `billItem`.`billId` = '%d'"
                + "     AND `billItem`.`medicineId` = '%d';";
        DBA dba = Helper.getDBA();
        try {
            ResultSet rs = dba.executeQuery(String.format(query, billId, medicineId));

            while (rs.next()) {
                this.billId = rs.getInt("billId");
                this.medicineId = rs.getInt("medicineId");
                this.quanitity = rs.getInt("quantity");
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
