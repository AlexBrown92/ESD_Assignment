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
public class Patient {

    public static HttpServletRequest ListPatients(HttpServletRequest request) {
        DatabaseModel.Patient patient = new DatabaseModel.Patient();

        ArrayList<DatabaseModel.Patient> patients = patient.listAllPatients();

        for (DatabaseModel.Patient thisPatient : patients) {
            thisPatient.setRemovable(canRemove(thisPatient.getID()));
        }

        request.setAttribute("patients", patients);
        request.setAttribute("view", "patientslist.jsp");

        return request;
    }

    public static HttpServletRequest ListPatientBill(HttpServletRequest request) {
        DatabaseModel.Patient patient = new DatabaseModel.Patient();

        int patientID = Integer.parseInt(request.getParameter("patient"));

        ArrayList<DatabaseModel.Patient> patients = patient.listAllPatients();

        DatabaseModel.Bill bill = new DatabaseModel.Bill();

        ArrayList<DatabaseModel.Bill> bills = bill.findUserAllBill(patientID);

        for (DatabaseModel.Bill thisBill : bills) {
            thisBill.setCost(setBillCost(thisBill.getId()));
        }

        request.setAttribute("patient", patients.get(0));
        request.setAttribute("bills", bills);
        request.setAttribute("view", "patientview.jsp");

        return request;
    }

    public static HttpServletRequest RemovePatient(HttpServletRequest request) {
        int patientID = Integer.parseInt(request.getParameter("patient"));

        if (canRemove(patientID)) {
            DatabaseModel.Patient patient = new DatabaseModel.Patient();
            patient.removePatient(patientID);
        }
        
        return ListPatients(request);
    }

    private static boolean canRemove(int patientID) {
        boolean canRemove = false;

        DatabaseModel.Bill bill = new DatabaseModel.Bill();
        ArrayList<DatabaseModel.Bill> patientOutstandingBills = bill.findUserOutstandingBill(patientID);

        if (!patientOutstandingBills.isEmpty()) {
            canRemove = false;
        } else {
            canRemove = true;
        }

        return canRemove;
    }

    private static int setBillCost(int billID) {
        DatabaseModel.Bill bill = new DatabaseModel.Bill();

        return bill.getBillCost(billID);
    }

}
