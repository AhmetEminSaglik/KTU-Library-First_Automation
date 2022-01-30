package Logic;

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

    String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    String DB_URL = "jdbc:mysql://localhost/LIBRARY?useUnicode=true&characterEncoding=utf8";

    String USER = "root";
    String PASS = "";

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    PreparedStatement preparedStmt = null;

    void getConnection() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ResultSet getResultSet() {

        return rs;
    }

    public void setPrepareStatement(String Query) {

        try {
            getConnection();
            preparedStmt = conn.prepareStatement(Query);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void setResultSet(String Query) {

        try {
            getConnection();
            rs = stmt.executeQuery(Query);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    public PreparedStatement getPreparedStatement() {
        return preparedStmt;
    }

    public void PreparedStatementExecute() {
        getConnection();
        try {
            preparedStmt.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "");
        }

    }

    public int Update(String Query) {
        getConnection();
        try {

            return stmt.executeUpdate(Query);

            //return stmt;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "");

            return 0;
        }
    }

    public void CloseAllConnections() {

        try {
            if (conn != null) {
                conn.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (preparedStmt != null) {
                preparedStmt.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqlConnection.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}
