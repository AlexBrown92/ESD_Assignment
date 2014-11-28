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
public class Medicine {

    public static HttpServletRequest medicineList(HttpServletRequest request) {

        ArrayList<Models.DatabaseModel.Medicine> medicines = Models.DatabaseModel.Medicine.listMedicines();

        request.setAttribute("medicines", medicines);
        request.setAttribute("view", "medicinelist.jsp");
        return request;
    }

    public static HttpServletRequest updateMedicine(HttpServletRequest request) {
        int medicineID = Integer.parseInt(request.getParameter("medicineID"));
        int cost = Integer.parseInt(request.getParameter("cost"));
        String name = request.getParameter("name");
        
        Models.DatabaseModel.Medicine.updateMedicine(medicineID, cost, name);

        return request;

    }

}
