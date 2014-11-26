/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.util.ArrayList;
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
        bill.setTotalCost(bill.getBillCost(billID));

        DatabaseModel.Patient patient = new DatabaseModel.Patient();
        patient = patient.findPatient(bill.getPatientId());

        ArrayList<DatabaseModel.BillItem> billItems = DatabaseModel.BillItem.listBillItems(bill.getId());
        ArrayList<DatabaseModel.Medicine> medicines = DatabaseModel.Medicine.listMedicines();
        request.setAttribute("patient", patient);
        request.setAttribute("bill", bill);
        request.setAttribute("billItems", billItems);
        request.setAttribute("medicines", medicines);
        request.setAttribute("view", "billview.jsp");

        return request;
    }
}
