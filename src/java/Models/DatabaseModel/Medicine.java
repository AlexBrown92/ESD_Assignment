package Models.DatabaseModel;

import Utils.DBA;
import Utils.Helper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author david
 */
public class Medicine {

    private int ID;
    private String name;
    private int cost;

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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Medicine() {
        ID = 0;
        name = "";
        cost = 0;
    }

    /**
     * *
     * given a username and password will return a user object with the values
     * if found
     *
     * @param name
     * @param cost
     * @return
     */
    public static ArrayList<Medicine> listMedicines() {
        String query = "SELECT * FROM medicine";
        DBA dba = Helper.getDBA();

        ArrayList<Medicine> medicines = new ArrayList<>();

        try {
            ResultSet rs = dba.executeQuery(String.format(query));

            while (rs.next()) {
                Medicine newMedicine = new Medicine();
                newMedicine.setID(rs.getInt("ID"));
                newMedicine.setName(rs.getString("name"));
                newMedicine.setCost(rs.getInt("cost"));
                medicines.add(newMedicine);
            }
            rs.close();
            dba.closeConnections();

        } catch (SQLException sqlEx) {
            dba.closeConnections();
            Helper.logException(sqlEx);
        }
        return medicines;
    }

    public Medicine findMedicine(int id) {
        String query = "SELECT * FROM `medicine` WHERE `id` = %d LIMIT 1;";
        Utils.DBA dba = Helper.getDBA();
        Medicine m = new Medicine();
        try {
            ResultSet rs = dba.executeQuery(String.format(query, id));
            rs.next();
            m.setID(rs.getInt("id"));
            m.setName(rs.getString("name"));
            m.setCost(rs.getInt("cost"));
        } catch (SQLException sqlEx) {
            Helper.logException(sqlEx);
        }
        return m;
    }

    public void removeMedicine(int medicineID) {

        String query = "insert into `deletedMedicine` (`medicineId`, `removalDate`) "
                + "values ('%d', '%s');";

        Utils.DBA dba = Helper.getDBA();

        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());

        dba.executeUpdate(String.format(query, medicineID, date.toString()));
        dba.closeConnections();

    }

    public static void updateMedicine(int medicineID, int cost) {
        String query = "UPDATE `medicine` "
                + "SET `medicine`.`cost` = '%d' "
                + "WHERE `medicine`.`id` = '%d'; ";

        Utils.DBA dba = Helper.getDBA();
        dba.executeUpdate(String.format(query, cost, medicineID));
        dba.closeConnections();
    }

}
