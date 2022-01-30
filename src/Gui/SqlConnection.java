/*package Gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class SqlConnection {

    public final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    //"com.mysql.jdbc.Driver"
    public final String DB_URL = "jdbc:mysql://localhost/LIBRARY?useUnicode=true&characterEncoding=utf8"; //"jdbc:mysql://localhost/"
    public final String DB_URL_CreateDatabase = "jdbc:mysql://localhost/";
    //  Database credentials
    private String USER; // "root"
    private String PASS; //""

    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement preparedStmt = null;

    public SqlConnection() {
    }

    public void StartSqlConnection(String JDBC_DRIVER, String DB_URL) {
        try {
               conn = null;
            stmt = null;

            Class.forName(JDBC_DRIVER);
            getStmt(DB_URL);

            //  String sqlControl = "SHOW DATABASES LIKE 'library' ";
            // getStmt().executeUpdate(sqlControl);
        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(SqlConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex + " 1", "CLASS NOT FOUND EXCEPTION", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void CloseSqlConnection() {

        try {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + " 2 : SQL Bağlantı kapatma hatası");
        }

    }

    public String getUSER() {
        if (USER == null) {
            USER = "root";
        }
        return USER;
    }

    public void setUSER(String USER) {
        this.USER = USER;
    }

    public String getPASS() {
        if (PASS == null) {
            PASS = "";
        }
        return PASS;
    }

    public void setPASS(String PASS) {
        this.PASS = PASS;
    }

    public Connection getConn(String DB_URL) {

        try {
            conn = DriverManager.getConnection(DB_URL, getUSER(), getPASS());
        } catch (SQLException ex) {
            //Logger.getLogger(SqlConnection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex + "\n\n\n    İhtimal Çözümler:\n "
                    + "1-)Xampp portu kapalı ise açınız", "SQL EXCEPTION ERROR", JOptionPane.ERROR_MESSAGE);
        }

        return conn;
    }

    public void setConn(String DB_URL, String USER, String PASS) {
        try {
            this.conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + " 4", "SQL EXCEPTION ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }

    public Statement getStmt(String DB_URL) {

        try {
            stmt = getConn(DB_URL).createStatement();
            //createStatement();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + " 5", "SQL EXCEPTION ERROR", JOptionPane.ERROR_MESSAGE);
        }

        return stmt;
    }

    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(String TxtStringQuery, String DB_URL) {
        try {
            //ResultSet rs = sqlConnection.getStmt().executeQuery(detectWhoHasBookOverThirtyDays);
            rs = getStmt(DB_URL).executeQuery(TxtStringQuery);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + " 6", "SQL EXCEPTION ERROR", JOptionPane.ERROR_MESSAGE);
        }
        this.rs = rs;
    }

    public PreparedStatement getPreparedStmt() {
        return preparedStmt;
    }

    public void setPreparedStmt(PreparedStatement preparedStmt) {
        this.preparedStmt = preparedStmt;
    }

}
*/
