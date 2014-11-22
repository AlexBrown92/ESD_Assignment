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
          
          request.setAttribute("patients", patients);
          request.setAttribute("view", "patientslist");
          
          return request;
      }
    
}
