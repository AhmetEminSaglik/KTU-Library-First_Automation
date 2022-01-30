package Gui;

import Logic.ActionsLogin;
import Logic.JavaMailUtil;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JPanel {

    JFrame jf = null;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final double jframeWidth = screenSize.getWidth() / 1.6070588235294119;
    final double jframeHeight = screenSize.getHeight() / 1.3963636363636365;
    //final int scaleWidth = (injat) (screenSize.getWidth() / 1.6070588235294119);
    //final int scaleHeight = (int) (screenSize.getHeight() / 1.3963636363636365);
    final int screenSizeWidth = (int) screenSize.getWidth();
    final int screenSizeHeight = (int) screenSize.getHeight();
    final int jframeX = (int) ((screenSize.getWidth() - jframeWidth) / 2);
    final int jframeY = (int) ((screenSize.getHeight() - jframeHeight) / 2);
    JLabel lblImageKtu;
    JLabel lblImageBook;
    JLabel lblusername;
    JLabel lblPass;
    JLabel lblTitle;

    JTextField txtusername;
    JPasswordField jPass;

    JButton signIn;
    Font lblFont = new Font("monospaced", Font.BOLD, screenSizeWidth / 80);
    Font txtFont = new Font("", Font.BOLD, screenSizeWidth / 91);
    ActionsLogin action = new ActionsLogin(this);

    public Login() {

        this.setLayout(null);
        this.setBackground(new Color(246, 229, 141));
        this.add(getLblImageKtu());
        this.add(getLblImageBook());
        this.add(getLblusername());
        this.add(getLblPass());
        this.add(getLblTitle());
        this.add(getTxtusername());
        this.add(getjPass());
        this.add(getSignIn());

        getJf().add(this);
        getJf().setVisible(true);
        getJf().setCursor(new Cursor(3));

        CreateDatabase();
        CreateTable();

        getJf().setCursor(null);
        JavaMailUtil jmu = new JavaMailUtil();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean DontSendMail = false;
                jmu.FindStudentAndMailThem(0, DontSendMail);

            }
        });
        t1.start();
        try {
            t1.join();

        } catch (InterruptedException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public JFrame getJf() {
        if (jf == null) {
            jf = new JFrame("GİRİŞ SAYFASI");

         //   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

            jf.setBounds(jframeX, jframeY, (int) jframeWidth, (int) jframeHeight);
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jf.setResizable(false);

        }
        return jf;
    }

    public void setJf(JFrame jf) {
        this.jf = jf;
    }

    public JLabel getLblusername() {
        if (lblusername == null) {
            lblusername = new JLabel("Kullanıcı adı  : ");
            lblusername.setBounds(screenSizeWidth / 10, (int) (screenSizeHeight / 3.2), screenSizeWidth / 6, screenSizeHeight / 19);

            lblusername.setFont(lblFont);

        }
        return lblusername;
    }

    public void setLblusername(JLabel lblusername) {
        this.lblusername = lblusername;
    }

    public JLabel getLblPass() {
        if (lblPass == null) {
            lblPass = new JLabel("Parola         : ");
            lblPass.setBounds(screenSizeWidth / 10, (int) (screenSizeHeight / 2.7), screenSizeWidth / 6, screenSizeHeight / 19);

            lblPass.setFont(lblFont);

        }
        return lblPass;
    }

    public void setLblPass(JLabel lblPass) {
        this.lblPass = lblPass;
    }

    public JLabel getLblTitle() {
        if (lblTitle == null) {
            lblTitle = new JLabel("OF TEKNOLOJİ FAKÜLTESİ KÜTÜPHANESİ");
            lblTitle.setBounds((int) (screenSizeWidth / 6), (int) (screenSizeHeight / 7.5), screenSizeWidth / 3, screenSizeHeight / 19);

            lblTitle.setFont(new Font("", Font.BOLD, (int) (screenSizeWidth / 70)));

        }
        return lblTitle;
    }

    public void setLblTitle(JLabel lblTitle) {
        this.lblTitle = lblTitle;
    }

    public JTextField getTxtusername() {
        if (txtusername == null) {
            txtusername = new JTextField("");

            txtusername.setBounds((screenSizeWidth * 3 / 12), (int) (screenSizeHeight / 3.2), ((screenSizeWidth / 5)), (screenSizeHeight / 19));
            txtusername.setFont(txtFont);
            txtusername.addActionListener(action);
        }
        return txtusername;
    }

    public void setTxtusername(JTextField txtusername) {
        this.txtusername = txtusername;
    }

    public JPasswordField getjPass() {
        if (jPass == null) {
            jPass = new JPasswordField("");
            jPass.setBounds((screenSizeWidth * 3 / 12), (int) (screenSizeHeight / 2.7), (screenSizeWidth / 5), (screenSizeHeight / 19));
            jPass.setFont(txtFont);
            jPass.addActionListener(action);

        }
        return jPass;
    }

    public void setjPass(JPasswordField jPass) {
        this.jPass = jPass;
    }

    public JButton getSignIn() {
        if (signIn == null) {
            signIn = new JButton("Oturum aç");
            signIn.setBounds((screenSizeWidth / 3) - (screenSizeWidth / 70), (int) (screenSizeHeight / 2.2),
                    screenSizeWidth / 9 + (screenSizeWidth / 50), screenSizeHeight / 19);

            signIn.setFont(txtFont);
            signIn.addActionListener(action);
            signIn.setCursor(new Cursor(12));
        }
        return signIn;
    }

    public void setSignIn(JButton signIn) {
        this.signIn = signIn;
    }

    public JLabel getLblImageKtu() {
        if (lblImageKtu == null) {
            lblImageKtu = new JLabel();

            lblImageKtu.setBounds((int) (screenSizeWidth / 2.134375), (int) (screenSizeHeight / 153.6), (int) (screenSizeWidth / 6.83), (int) (screenSizeHeight / 3.84));
            ImageIcon imageIconKtu = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("ktuLogo.png")));
            Image imageKtu = imageIconKtu.getImage();
            Image imageKtu2 = imageKtu.getScaledInstance(lblImageKtu.getWidth(), lblImageKtu.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon i = new ImageIcon(imageKtu2);
            lblImageKtu.setIcon(i);

        }

        return lblImageKtu;
    }

    public void setLblImageKtu(JLabel lblImageKtu) {
        this.lblImageKtu = lblImageKtu;
    }

    public JLabel getLblImageBook() {
        if (lblImageBook == null) {
            lblImageBook = new JLabel();
            lblImageBook.setBounds((int) (screenSizeWidth / 136.6), (int) (screenSizeHeight / 76.8),
                    (int) (screenSizeWidth / 5.464), (int) (screenSizeHeight / 3.84));
            ImageIcon imageIconbook = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("5.gif")));
            Image imageBook = imageIconbook.getImage();
            Image imageBook2 = imageBook.getScaledInstance(lblImageKtu.getWidth(), lblImageKtu.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon i = new ImageIcon(imageBook2);
            lblImageBook.setIcon(i);

        }
        return lblImageBook;
    }

    public void setLblImageBook(JLabel lblImageBook) {
        this.lblImageBook = lblImageBook;
    }

    public void CreateDatabase() {

        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/";
        String USER = "root";
        String PASS = "";

        Connection conn = null;
        Statement stmt = null;

        while (true) {
            try {

                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URL, USER, PASS);

                stmt = conn.createStatement();

                String sqlControl = "SHOW DATABASES LIKE 'library' ";

                stmt.executeUpdate(sqlControl);

                if (stmt.executeUpdate(sqlControl) == -1) {

                    return;
                }

                String sql = "CREATE DATABASE library";

                stmt.executeUpdate(sql);

            } catch (SQLException se) {
                JOptionPane.showMessageDialog(null, se + "\n\n   Çözüm Önerileri: \n"
                        + "1-) Lütfen xampp portunu açınız");

            } catch (ClassNotFoundException ex) {

                JOptionPane.showMessageDialog(null, "catch (mistake): ClassNotFoundException " + ex);

            } finally {
                closeConnections(conn, stmt, null, null);
            }
        }
    }

    public void CreateTable() {
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/LIBRARY?useUnicode=true&characterEncoding=utf8";

        String USER = "root";
        String PASS = "";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String tableControl = "SHOW TABLES LIKE 'student' ";
            String tableYoneticiControl = "SHOW TABLES LIKE 'admin' ";
            String tableKitapControl = "SHOW TABLES LIKE 'book'";
            if (stmt.executeUpdate(tableControl) == -1 || stmt.executeUpdate(tableYoneticiControl) == -1 || stmt.executeUpdate(tableKitapControl) == -1) {

                // yukarıda || yerine && yapacam
                LocalDate today = LocalDate.now();
                String ControlQueryOver30Days0rOver27 = "SELECT * FROM book WHERE NOW() >  BorrowedDate + INTERVAL 30 DAY ";
                int Friday = 5;

                if (today.getDayOfWeek().getValue() == Friday) {
                    ControlQueryOver30Days0rOver27 = "SELECT * FROM book WHERE NOW() >  BorrowedDate + INTERVAL 28 DAY ";

                }

                int over30Days = 0;

                rs = stmt.executeQuery(ControlQueryOver30Days0rOver27);

                while (rs.next()) {

                    over30Days++;

                }
                ControlQueryOver30Days0rOver27 = "SELECT * FROM book WHERE NOW() BETWEEN BorrowedDate + INTERVAL 27 DAY and BorrowedDate+ INTERVAL 30 DAY ";

                rs = stmt.executeQuery(ControlQueryOver30Days0rOver27);
                int inLast3Days = 0;
                while (rs.next()) {

                    inLast3Days++;

                }

                if (over30Days > 0 || inLast3Days > 0) {

                    JOptionPane.showMessageDialog(null, "30 günü aşmış ödünç verilen kitapların sayısı : " + over30Days
                            + "\n\n Son üç gün  içinde iade edilmesi gereken kitap sayısı : " + inLast3Days);

                }

                closeConnections(conn, stmt, rs, null);

                return;
            }
            String createStudentTable = "CREATE TABLE `library`.`student` "
                    + "( `Id` INT NOT NULL AUTO_INCREMENT ,"
                    + " `No`  VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL , "
                    + "`Name` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL , "
                    + "`Surname` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL , "
                    + "`Email` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL , "
                    + "`Phone` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL ,"
                    + " `Debt` DOUBLE NOT NULL , PRIMARY KEY (`Id`));";

            String createAdminTable = "CREATE TABLE `library`.`admin` "
                    + "( `Username` VARCHAR(50) NOT NULL  , "
                    + "`Password` VARCHAR(50) NOT NULL);";

            String createBookTable = "CREATE TABLE `library`.`book`"
                    + " ( `Id` INT NOT NULL AUTO_INCREMENT ,"
                    + " `BarcodeNo` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL ,"
                    + " `Name` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL ,"
                    + " `AuthorName` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL ,"
                    + " `CategoryName` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL ,"
                    + " `StudentNo` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci  ,"
                    + " `BorrowedDate` DATETIME   ,"
                    + " `Condition` INT NULL  ,"
                    + " PRIMARY KEY (`Id`));";

            stmt.executeUpdate(createStudentTable);
            stmt.executeUpdate(createAdminTable);
            stmt.executeUpdate(createBookTable);
        } catch (SQLException se) {

            JOptionPane.showMessageDialog(null, "Beklenmeyen Hata : \n" + se);
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Beklenmeyen Hata : \n" + e);

        } finally {

            closeConnections(conn, stmt, rs, null);

        }

        Initializer();
    }

    public void Initializer() {

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

            String sql2 = "INSERT INTO `admin` (`Username`, `Password`) "
                    + "VALUES ('1', '1'),"
                    + "('2','2')";

            stmt.executeUpdate(sql2);
        } catch (SQLException se) {

            JOptionPane.showMessageDialog(null, se);
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        } finally {

            closeConnections(conn, stmt, null, null);
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
