package Models.DatabaseModel;

import Utils.DBA;
import Utils.Helper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author david
 */
public class User {

    private int ID;
    private String userName;
    private String password;
    
    public User() {
        ID = 0;
        userName = "";
        password = "";
    }

    public int getID() {
        return ID;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setID(int id) {
        this.ID = id;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * *
     * given a username and password will return a user object with the values
     * if found
     *
     * @param username
     * @param password
     * @return
     */
    public User findUser(String username, String password) {
        String query = "SELECT * FROM user WHERE username = '%s' AND password = '%s'";
        DBA dba = Helper.getDBA();

        try {

            ResultSet rs = dba.executeQuery(String.format(query, username, password));

            while (rs.next()) {
                this.ID = rs.getInt("userId");
                this.userName = rs.getString("username");
                this.password = rs.getString("password");
            }
            rs.close();
            dba.closeConnections();

        } catch (SQLException sqlEx) {
            dba.closeConnections();
            Helper.logException(sqlEx);
        }

        return this;
    }

}
