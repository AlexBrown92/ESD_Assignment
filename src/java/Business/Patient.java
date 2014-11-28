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

    public static HttpServletRequest listPatients(HttpServletRequest request) {
        Models.DatabaseModel.Patient patient = new Models.DatabaseModel.Patient();

        ArrayList<Models.DatabaseModel.Patient> patients = patient.listAllPatients();

        for (Models.DatabaseModel.Patient thisPatient : patients) {
            thisPatient.setRemovable(canRemove(thisPatient.getID()));
        }

        request.setAttribute("patients", patients);
        request.setAttribute("view", "patientslist.jsp");

        return request;
    }

    public static HttpServletRequest listAPatientBills(HttpServletRequest request) {
        Models.DatabaseModel.Patient patient = new Models.DatabaseModel.Patient();

        int patientID = Integer.parseInt(request.getParameter("patientID"));

        patient = patient.findPatient(patientID);

        ArrayList<Models.DatabaseModel.Bill> bills = Models.DatabaseModel.Bill.findUserAllBill(patientID);

        ArrayList<Models.ViewModel.Bill> viewBills = new ArrayList<>();

        for (Models.DatabaseModel.Bill thisBill : bills) {
            Models.ViewModel.Bill viewBill = new Models.ViewModel.Bill(thisBill,patient,0);
            viewBill.setTotalCost(Models.DatabaseModel.Bill.getBillTotalCost(thisBill.getId()));
            viewBills.add(viewBill);
        }

        request.setAttribute("patient", patient);
        request.setAttribute("bills", viewBills);
        request.setAttribute("view", "patientview.jsp");

        return request;
    }

    public static HttpServletRequest removePatient(HttpServletRequest request) {
        int patientID = Integer.parseInt(request.getParameter("patientID"));

        if (canRemove(patientID)) {
            Models.DatabaseModel.Patient patient = new Models.DatabaseModel.Patient();
            patient.removePatient(patientID);
        }

        return listPatients(request);
    }

    private static boolean canRemove(int patientID) {
        boolean canRemove = false;

        Models.DatabaseModel.Bill bill = new Models.DatabaseModel.Bill();
        ArrayList<Models.DatabaseModel.Bill> patientOutstandingBills = bill.findUserOutstandingBill(patientID);

        if (!patientOutstandingBills.isEmpty()) {
            canRemove = false;
        } else {
            canRemove = true;
        }

        return canRemove;
    }

}
