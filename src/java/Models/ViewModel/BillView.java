package Models.ViewModel;

import Models.DatabaseModel.Medicine;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author david
 */
public class BillView {

    private int billID;
    private int patientID;
    private String patientName;
    private Date dateCreated;
    private Date datePaid;
    private int consultationCost;
    private int totalCost;
    private ArrayList<Models.ViewModel.BillItem> billItems;
    private ArrayList<Models.DatabaseModel.Medicine> medicines; 
    
    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public ArrayList<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(ArrayList<Medicine> medicines) {
        this.medicines = medicines;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(Date datePaid) {
        this.datePaid = datePaid;
    }

    public int getConsultationCost() {
        return consultationCost;
    }

    public void setConsultationCost(int ConsultationCost) {
        this.consultationCost = ConsultationCost;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public ArrayList<Models.ViewModel.BillItem> getBillItems() {
        return billItems;
    }

    public void setBillItems(ArrayList<Models.ViewModel.BillItem> billItems) {
        this.billItems = billItems;
    }

}
