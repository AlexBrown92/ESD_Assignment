/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author david
 */
public class User {

    public static HttpServletRequest Login(HttpServletRequest request) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        DatabaseModel.User user = new DatabaseModel.User();

        user.findUser(username, password);

        if (user.getID() > 0) {
            request.getSession().setAttribute("user", user);
            request.setAttribute("include", "homepage.jsp");
        } else {
            request.getSession().invalidate();
            request.setAttribute("include", "error.jsp");
        }

        return request;
    }

    public static HttpServletRequest Logout(HttpServletRequest request) {

        request.getSession().invalidate();
        request.setAttribute("include", "login.jsp");

        return request;
    }

}
