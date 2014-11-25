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
            thisPatient.setCanRemove(setCanRemove(patient.getID()));
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

                
        ArrayList<DatabaseModel.Bill> patientPaidBills = bill.findUserPaidBill(patientID);
        ArrayList<DatabaseModel.Bill> patientOutstandingBills = bill.findUserOutstandingBill(patientID);
    

        request.setAttribute("patients", patients);
        request.setAttribute("view", "patientview.jsp");

        return request;
    }

    private static boolean setCanRemove(int patientID) {
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

}
