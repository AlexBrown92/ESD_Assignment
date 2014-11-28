/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.util.ArrayList;
import java.util.Date;
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

        Models.DatabaseModel.Patient patient = new Models.DatabaseModel.Patient();
        patient = patient.findPatient(bill.getPatientId());

        Models.ViewModel.Bill billView = new Models.ViewModel.Bill(bill, patient, bill.getBillTotalCost(billID));

        ArrayList<Models.DatabaseModel.BillItem> billItems = Models.DatabaseModel.BillItem.listBillItems(bill.getId());
        ArrayList<Models.DatabaseModel.Medicine> medicines = Models.DatabaseModel.Medicine.listMedicines();

        billView.setMedicines(medicines);
        billView.setBillItems(getBillItems(billItems, medicines));

        request.setAttribute("billView", billView);
        request.setAttribute("view", "billview.jsp");

        return request;
    }

    private static ArrayList<Models.ViewModel.BillItem> getBillItems(ArrayList<Models.DatabaseModel.BillItem> billItems, ArrayList<Models.DatabaseModel.Medicine> medicines) {

        ArrayList<Models.ViewModel.BillItem> viewmodelbillItemArray = new ArrayList<>();
        for (Models.DatabaseModel.BillItem billItem : billItems) {
            for (Models.DatabaseModel.Medicine medicine : medicines) {
                if (billItem.getMedicineId() == medicine.getID()) {
                    viewmodelbillItemArray.add(new Models.ViewModel.BillItem(billItem, medicine));
                    break;
                }
            }
        }

        return viewmodelbillItemArray;
    }

    public static HttpServletRequest addMedicine(HttpServletRequest request) {
        int billID = Integer.parseInt(request.getParameter("billID"));
        int medicineID = Integer.parseInt(request.getParameter("medicineID"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        Models.DatabaseModel.BillItem.addBillItem(billID, medicineID, quantity);

        return request;
    }

    public static HttpServletRequest removeMedicine(HttpServletRequest request) {
        int billID = Integer.parseInt(request.getParameter("billID"));
        int medicineID = Integer.parseInt(request.getParameter("medicineID"));

        Models.DatabaseModel.BillItem.removeBillItem(billID, medicineID);

        return request;

    }

    public static HttpServletRequest updateMedicine(HttpServletRequest request) {
        int billID = Integer.parseInt(request.getParameter("billID"));
        int medicineID = Integer.parseInt(request.getParameter("medicineID"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        Models.DatabaseModel.BillItem.updateBillItem(billID, medicineID, quantity);

        return request;

    }

    public static HttpServletRequest payBill(HttpServletRequest request) {
        int billID = Integer.parseInt(request.getParameter("billID"));
        Models.DatabaseModel.Bill bill = new Models.DatabaseModel.Bill();

        bill.payBill(billID);

        return request;
    }

    public static HttpServletRequest updateConsultationCost(HttpServletRequest request) {
        int billID = Integer.parseInt(request.getParameter("billID"));
        int consultationCost = Integer.parseInt(request.getParameter("consultationCost"));

        Models.DatabaseModel.Bill.updateConsultationCost(billID, consultationCost);

        return request;

    }

    public static HttpServletRequest listBills(HttpServletRequest request) {
        ArrayList<Models.ViewModel.Bill> viewBills = new ArrayList<>();
        ArrayList<Models.DatabaseModel.Bill> bills = Models.DatabaseModel.Bill.listBills();

        for (Models.DatabaseModel.Bill bill : bills) {
            Models.DatabaseModel.Patient patient = new Models.DatabaseModel.Patient();

            int totalCost = Models.DatabaseModel.Bill.getBillTotalCost(bill.getId());

            patient = patient.findPatient(bill.getPatientId());

            Models.ViewModel.Bill viewBill = new Models.ViewModel.Bill(bill, patient, totalCost);

            viewBills.add(viewBill);
        }
        request.setAttribute("bills", viewBills);
        request.setAttribute("view", "billlist.jsp");

        return request;
    }
}
