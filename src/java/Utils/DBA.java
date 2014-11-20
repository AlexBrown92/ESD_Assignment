/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.Instant;
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

    public DBA(String database, String databaseUser, String databasePassword) {
        this.database = database;
        this.databaseUser = databaseUser;
        this.databasePassword = databasePassword;
    }

    public ResultSet executeQuery(String sql) {
        ResultSet rs = null;
        closeConnections(); 
        
        try {
            Class.forName(MYSQL_DRIVER);
            connection = DriverManager.getConnection(MYSQL_URL + database, databaseUser, databasePassword);

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
