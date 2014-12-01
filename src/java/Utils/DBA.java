package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

/**
 *
 * @author david
 */
public class DBA {

    private final static String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    private final static String MYSQL_URL = "jdbc:mysql://";

    private String database;
    private String databaseUser;
    private String databasePassword;
    private Connection connection;
    private Statement statement;

    /***
     * DBA constructor
     * @param database
     * @param databaseUser
     * @param databasePassword 
     */
    public DBA(String database, String databaseUser, String databasePassword) {
        this.database = database;
        this.databaseUser = databaseUser;
        this.databasePassword = databasePassword;
    }

    /***
     * given an SQL query will execute and then return the resultset 
     * @param sql
     * @return 
     */
    public ResultSet executeQuery(String sql) {
        ResultSet rs = null;
        closeConnections(); 
        
        try {
            Class.forName(MYSQL_DRIVER);
            String connectionString = String.format("%s?user=%s&password=%s",MYSQL_URL + database, databaseUser, databasePassword);
            //String connectionString = String.format("%s?user=%s&password=%s",MYSQL_URL + database, "root", "");
            connection = DriverManager.getConnection(connectionString);

            statement = connection.createStatement();

            rs = statement.executeQuery(sql);

        } catch (SQLException sqlEx) {
            Helper.logException(sqlEx);

            try {
                connection.close();
            } catch (SQLException sqlEx2) {
                Helper.logException(sqlEx2);
            }
        } catch (ClassNotFoundException cnfEx) {
            Helper.logException(cnfEx);
        } finally {

        }

        return rs;
    }

     public int executeUpdate(String sql) {
        int result = -1;
        closeConnections(); 
        
        try {
            Class.forName(MYSQL_DRIVER);
            String connectionString = String.format("%s?user=%s&password=%s",MYSQL_URL + database, databaseUser, databasePassword);
            //String connectionString = String.format("%s?user=%s&password=%s",MYSQL_URL + database, "root", "");
            connection = DriverManager.getConnection(connectionString);

            statement = connection.createStatement();

            int affectedRows = statement.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
            
            if (affectedRows != 0){
                ResultSet genKeys = statement.getGeneratedKeys();
                if (genKeys.next()){
                    result = genKeys.getInt(1);
                } 
            } else {
                throw new SQLException("Error with Update/Insert.");
            }

        } catch (SQLException sqlEx) {
            Helper.logException(sqlEx);

            try {
                connection.close();
            } catch (SQLException sqlEx2) {
                Helper.logException(sqlEx2);
            }
        } catch (ClassNotFoundException cnfEx) {
            Helper.logException(cnfEx);
        } finally {

        }

        return result;
    }

    
    /***
     * Will try to terminate connections to the database if possible 
     */
    public void closeConnections() {
        try {

            if (statement != null) {
                if (!statement.isClosed()) {
                    statement.close();
                }
            }
        } catch (SQLException sqlEx) {
            Helper.logException(sqlEx);
        }
        try {
            if (connection != null) {
                if (!connection.isClosed()) {

                    connection.close();
                }
            }
        } catch (SQLException sqlEx) {
            Helper.logException(sqlEx);
        }
    }
}
