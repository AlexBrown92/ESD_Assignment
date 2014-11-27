/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author david
 */
public class Bill {

    public static HttpServletRequest viewBill(HttpServletRequest request) {

        int billID = Integer.parseInt(request.getParameter("billID"));

        DatabaseModel.Bill bill = new DatabaseModel.Bill();

        bill = bill.findBill(billID);
        bill.setTotalCost(bill.getTotalCost(billID));

        DatabaseModel.Patient patient = new DatabaseModel.Patient();
        patient = patient.findPatient(bill.getPatientId());
        
        ViewModel.BillView billView = new ViewModel.BillView();

        billView.setBillID(billID);
        billView.setConsultationCost(bill.getConsultationCost());
        billView.setDateCreated(new Date(bill.getDateCreated().getTime()));
        
        if(bill.getDatePaid() != null){
            billView.setDatePaid(new Date(bill.getDatePaid().getTime()));
        }
        else {
            billView.setDatePaid(null);
        }
        
        billView.setPatientID(patient.getID());
        billView.setPatientName(patient.getName());
        billView.setTotalCost(bill.getTotalCost(billID));
        
        ArrayList<DatabaseModel.BillItem> billItems = DatabaseModel.BillItem.listBillItems(bill.getId());
        ArrayList<DatabaseModel.Medicine> medicines = DatabaseModel.Medicine.listMedicines();
        billView.setMedicines(medicines);
        billView.setBillItems(getBillItems(billItems, medicines));
        
        request.setAttribute("billView", billView); 
        request.setAttribute("view", "billview.jsp");

        return request;
    }
    
    private static ArrayList<ViewModel.BillItem> getBillItems(ArrayList<DatabaseModel.BillItem> billItems, ArrayList<DatabaseModel.Medicine> medicines){
        
        ArrayList<ViewModel.BillItem> viewmodelbillItemArray = new ArrayList<>();
        for(DatabaseModel.BillItem billItem : billItems){
            for(DatabaseModel.Medicine medicine : medicines){
                if(billItem.getMedicineId() == medicine.getID()){
                    viewmodelbillItemArray.add(new ViewModel.BillItem(billItem,medicine));
                    break;
                }
            }
        }
        
        return viewmodelbillItemArray; 
    }
}
