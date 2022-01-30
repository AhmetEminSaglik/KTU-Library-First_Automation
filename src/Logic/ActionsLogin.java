package Logic;

import Gui.Login;
import Gui.MainGui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JOptionPane;

public class ActionsLogin implements ActionListener {

    Login login;

    public ActionsLogin(Login login) {
        this.login = login;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (login != null) {
            if (e.getSource() == login.getSignIn()
                    || e.getSource() == login.getTxtusername()
                    || e.getSource() == login.getjPass()) {
                String JDBC_DRIVER = "com.mysql.jdbc.Driver";
                String DB_URL = "jdbc:mysql://localhost/library";

                String USER = "root";
                String PASS = "";

                Connection conn = null;
                Statement stmt = null;
                try {
                    Class.forName(JDBC_DRIVER);
                    conn = DriverManager.getConnection(DB_URL, USER, PASS);

                    stmt = conn.createStatement();

                    String sqlYoneticiKontrol = "SELECT * FROM admin WHERE Username='" + login.getTxtusername().getText() + "' AND Password ='" + String.valueOf(login.getjPass().getPassword()) + "'";
                    ResultSet rs = stmt.executeQuery(sqlYoneticiKontrol);
                    boolean girisDogru = false;
                    while (rs.next()) {
                        String kullaniciAdi = rs.getString("Username");
                        String Sifre = rs.getString("Password");

                        if (kullaniciAdi.equals(login.getTxtusername().getText()) && Sifre.equals(String.valueOf(login.getjPass().getPassword()))) {
                            login.setVisible(false);
                            MainGui mg = new MainGui(login);
                            mg.action.NumbersOfBooks();
                            girisDogru = true;

                        }

                    }
                    if (!girisDogru) {
                        java.awt.Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(null, "KULLANICI ADI VEYA PAROLA HATALI");
                        login.getTxtusername().setText("");
                        login.getjPass().setText("");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                } finally {
                    closeConnections(conn, stmt, null, null);
                }

            }
        }
    }

    public void closeConnections(Connection conn, Statement stmt, ResultSet rs, PreparedStatement preparedStmt) {

        try {
            if (stmt != null) {

                stmt.close();

            }
            if (conn != null) {

                conn.close();

            }
            if (rs != null) {

                rs.close();
            }
            if (preparedStmt != null) {
                preparedStmt.close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sql bağlantısı kapatılırken hata meydana geldi");
        }
    }

}
