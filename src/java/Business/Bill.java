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

        Models.DatabaseModel.Bill bill = new Models.DatabaseModel.Bill();

        bill = bill.findBill(billID);
        bill.setTotalCost(bill.getBillTotalCost(billID));

        
        Models.DatabaseModel.Patient patient = new Models.DatabaseModel.Patient();
        patient = patient.findPatient(bill.getPatientId());
        
        Models.ViewModel.BillView billView = new Models.ViewModel.BillView();

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
        billView.setTotalCost(bill.getBillTotalCost(billID));
        
        ArrayList<Models.DatabaseModel.BillItem> billItems = Models.DatabaseModel.BillItem.listBillItems(bill.getId());
        ArrayList<Models.DatabaseModel.Medicine> medicines = Models.DatabaseModel.Medicine.listMedicines();
        billView.setMedicines(medicines);
        billView.setBillItems(getBillItems(billItems, medicines));
        
        request.setAttribute("billView", billView); 
        request.setAttribute("view", "billview.jsp");

        return request;
    }
    
    private static ArrayList<Models.ViewModel.BillItem> getBillItems(ArrayList<Models.DatabaseModel.BillItem> billItems, ArrayList<Models.DatabaseModel.Medicine> medicines){
        
        ArrayList<Models.ViewModel.BillItem> viewmodelbillItemArray = new ArrayList<>();
        for(Models.DatabaseModel.BillItem billItem : billItems){
            for(Models.DatabaseModel.Medicine medicine : medicines){
                if(billItem.getMedicineId() == medicine.getID()){
                    viewmodelbillItemArray.add(new Models.ViewModel.BillItem(billItem,medicine));
                    break;
                }
            }
        }
        
        return viewmodelbillItemArray; 
    }
}
