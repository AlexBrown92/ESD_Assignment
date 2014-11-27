/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.DatabaseModel;

import Utils.DBA;
import Utils.Helper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public static ArrayList<BillItem> listBillItems(int billID) {

        String query = "SELECT *"
                + "FROM `billItem`"
                + "WHERE `billItem`.`billId` = '%d';";

        ArrayList<BillItem> listOfBillItems = new ArrayList<>();
        DBA dba = Helper.getDBA();
        try {
            ResultSet rs = dba.executeQuery(String.format(query, billID));

            if (rs != null) {

                while (rs.next()) {
                    BillItem newBillItem = new BillItem();

                    newBillItem.setBillId(rs.getInt("billId"));
                    newBillItem.setMedicineId(rs.getInt("medicineId"));
                    newBillItem.setQuanitity(rs.getInt("quantity"));
                
                    listOfBillItems.add(newBillItem);
                }
                rs.close();
                dba.closeConnections();
            }
        } catch (SQLException sqlEx) {
            dba.closeConnections();
            Helper.logException(sqlEx);
        }

        return listOfBillItems;
    }
}
