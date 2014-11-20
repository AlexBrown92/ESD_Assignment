package Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author david
 */
public class Helper implements ServletContextListener {

    private static String database;
    private static String databaseUser;
    private static String databasePassword;
    private static String filePath;

    public static DBA getDBA() {
        return new DBA(database, databaseUser, databasePassword);
    }

    public static void logException(Exception ex) {
        try {
            File exLog = new File(filePath);
            FileOutputStream outputStream = new FileOutputStream(exLog);
            ex.printStackTrace(new PrintStream(outputStream));
            outputStream.flush();
            outputStream.close();

        } catch (IOException ioE) {
            System.exit(1);
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        database = sce.getServletContext().getInitParameter("db");
        databaseUser = sce.getServletContext().getInitParameter("dbUser");
        databasePassword = sce.getServletContext().getInitParameter("dbPassword");
        
        filePath = sce.getServletContext().getRealPath( "/WEB-INF/exceptionLog.log");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Do nothing
    }
}
