package Business;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author david
 */
public class User {

    /***
     * Business functionality for logging in 
     * @param request
     * @return request with a set session and the attribute "include" set to the correct page
     */
    public static HttpServletRequest Login(HttpServletRequest request) {
        DatabaseModel.User user  = new DatabaseModel.User();
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(validLoginParameters(username, password)){
            user.findUser(username, password);
        }
        
        if (user.getID() > 0) {
            request.getSession().setAttribute("user", user);
            request.setAttribute("view", "homepage");
        } else {
            request.getSession().invalidate();
            request.setAttribute("view", "error");
        }

        return request;
    }

    /***
     * Checks to see if the login parameters length > 0
     * @param username
     * @param password
     * @return true both username and password length > 0
     *         false one failed
     */
    private static boolean validLoginParameters(String username, String password){
        boolean isValid = false;
        
        if(validateStringLength(username) & validateStringLength(password)){
            isValid = true; 
        }
        
        return isValid;
    }
    
    /***
     * Checks the string length > 0
     * @param param
     * @return  true string length > 0
     *          false string length == 0 
     */
    private static boolean validateStringLength(String param){
        boolean isValid = false;
        
        if(param.length() > 0){
            isValid = true;
        }
        
        return isValid;
    }
    
    /***
     * Destroys session and sets request attribute "include" to the "login.jsp"
     * @param request
     * @return 
     */
    public static HttpServletRequest Logout(HttpServletRequest request) {

        request.getSession().invalidate();
        request.setAttribute("include", "login.jsp");

        return request;
    }

}
