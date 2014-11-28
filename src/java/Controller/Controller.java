package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author david
 */
public class Controller extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Finds which page made the request 
        String url = request.getRequestURI().substring(request.getContextPath().length()).toLowerCase();
        String mainPage = "/index.jsp";
        //Chooses which business object is responsible for the interaction
        switch (url) {
            case "/login":
                request = Business.User.login(request);
                break;
            case "/logout":
                request = Business.User.logout(request);
                break;
            case "/patientslist":
                request = Business.Patient.listPatients(request);
                break;
            case "/patientview":
                request = Business.Patient.listAPatientBills(request);
                break;
            case "/patientremove":
                request = Business.Patient.removePatient(request);
                break;
            case "/patientviewpaybill":
                request = Business.Bill.payBill(request);
                request = Business.Patient.listAPatientBills(request);
                break;
            case "/billview":
                request = Business.Bill.viewBill(request);
                break;
            case "/billviewpaybill":
                request = Business.Bill.payBill(request);
                request = Business.Bill.viewBill(request);
                break;
            case "/billviewaddmedicine":
                request = Business.Bill.addMedicine(request);
                request = Business.Bill.viewBill(request);
                break;
            case "/billviewremove":
                request = Business.Bill.removeMedicine(request);
                request = Business.Bill.viewBill(request);
                break;
            case "/billviewconsultationcost":
                request = Business.Bill.updateConsultationCost(request);
                request = Business.Bill.viewBill(request);
                break;
            case "/billvieweditmedicine":
                request = Business.Bill.updateMedicine(request);
                request = Business.Bill.viewBill(request);
                break;
            default:
                response.setStatus(401);
                request.setAttribute("view", "");
        }

        request.getRequestDispatcher(mainPage).forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
