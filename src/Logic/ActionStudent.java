package Logic;

import Gui.RegisteredStudentGui;
import Gui.StudentAddGui;
import Gui.StudentStateGui;
import Gui.StudentUpdateGui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class ActionStudent implements ActionListener, FocusListener {

    StudentAddGui sag;
    StudentStateGui ssg;
    StudentUpdateGui sug;
    RegisteredStudentGui rsg;

    boolean StudentCanAdd;
    boolean StudentBringCame;
    boolean StudentCanUpdate;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final double screenSizeWidth = screenSize.getWidth();
    final double screenSizeHeight = screenSize.getHeight();

    Color rsgPlaceHolder = Color.GRAY;
    Font fontTxtPlaceHolder = new Font("", Font.ITALIC, (int) screenSizeWidth / 91);
    boolean firstValueChangedActionWillPerform = true;

    public ActionStudent(StudentAddGui sag) {
        this.sag = sag;
    }

    public ActionStudent(StudentStateGui ssg) {
        this.ssg = ssg;
    }

    public ActionStudent(StudentUpdateGui sug) {
        this.sug = sug;
    }

    public ActionStudent(RegisteredStudentGui rsg) {
        this.rsg = rsg;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (sag != null) {
            if (e.getSource() == sag.getBtnAdd()
                    || e.getSource() == sag.getTxtNo()
                    || e.getSource() == sag.getTxtName()
                    || e.getSource() == sag.getTxtSurname()
                    || e.getSource() == sag.getTxtEmail()
                    || e.getSource() == sag.getTxtPhoneNo()) {
                if (!sag.getTxtNo().getText().trim().equals("")
                        && !sag.getTxtName().getText().trim().equals("")
                        && !sag.getTxtSurname().getText().trim().equals("")
                        && !sag.getTxtEmail().getText().trim().equals("")
                        && !sag.getTxtPhoneNo().getText().trim().equals("")) {

                    DbStudentAdd();
                } else {
                    java.awt.Toolkit.getDefaultToolkit().beep();
                    sag.getTxtResult().setText("Please fill all of them");
                    sag.getTxtResult().setForeground(Color.red);
                    sag.getTxtResult().setBackground(Color.BLACK);
                }
            } else if (e.getSource() == sag.getBtnClear()) {
                int answer = JOptionPane.showConfirmDialog(null, "All data will be deleted. Are you sure?", "DATA DELETE WARNING", 0);
                if (answer == JOptionPane.YES_OPTION) {

                    SuccessVoice();
                    sag.getTxtEmail().setText("");
                    sag.getTxtName().setText("");
                    sag.getTxtNo().setText("");
                    sag.getTxtSurname().setText("");
                    sag.getTxtPhoneNo().setText("");
                    sag.getTxtResult().setText("");
                    sag.getTxtResult().setBackground(new Color(206, 214, 224));

                }
            } else if (e.getSource() == sag.getBtnComeBack()) {
                sag.getJp().setVisible(false);
                sag.getMg().getJf().setTitle("MAIN PAGE");
                sag.getMg().getJp().setVisible(true);
                clearAllTxtMainGui();
            }
        } else if (sug != null) {
            if (e.getSource() == sug.getBtnComeBack()) {
                sug.setVisible(false);
                sug.getMg().getJf().setTitle("MAIN PAGE");
                clearAllTxtMainGui();
                sug.getMg().getJp().setVisible(true);

            } else if (e.getSource() == sug.getTxtno()) {
                if (!sug.getTxtno().getText().trim().equals("")) {
                    DBStudentBringData();
                } else {
                    java.awt.Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "Must be filf Student No",
                            "SEARCH ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } else if (e.getSource() == sug.getBtnUpdate()
                    || e.getSource() == sug.getTxtResult()
                    || e.getSource() == sug.getTxtNewSurname()
                    || e.getSource() == sug.getTxtNewNo()
                    || e.getSource() == sug.getTxtNewName()
                    || e.getSource() == sug.getTxtNewEmail()
                    || e.getSource() == sug.getTxtPhoneNo()) {

                DBStudentUpdate();

            } else if (e.getSource() == sug.getBtnDelete()) {
                if (ControlBeforeRemoveStudent() == true) {
                    DBStudentDelete();
                }
            }
        } else if (ssg != null) {
            if (e.getSource() == ssg.getBtnComeBack()) {
                ssg.setVisible(false);
                ssg.getMg().getJf().setTitle("MAIN PAGE");
                ssg.getMg().getJp().setVisible(true);
                clearAllTxtMainGui();
            } else if (e.getSource() == ssg.getTxtStudentNo()) {
                ResetStudentState();

                BringStudentState();
            }
        } else if (rsg != null) {
            if (e.getSource() == rsg.getBtnComeBack()) {
                rsg.getJp().setVisible(false);
                rsg.getMg().getJf().setTitle("MAIN PAGE");
                rsg.getMg().getJp().setVisible(true);
                clearAllTxtMainGui();
            } else if (e.getSource() == rsg.getTxtName()) {
                SearchRegisteredStudent(1);

            } else if (e.getSource() == rsg.getTxtSurname()) {
                SearchRegisteredStudent(2);
            } else if (e.getSource() == rsg.getTxtNo()) {
                SearchRegisteredStudent(3);

            }
        }
    }

    public void DBStudentDelete() {

        SqlConnection SqlConnection = new SqlConnection();

        StudentCanUpdate = true;

        //  boolean StudentDeleted = true;
        boolean AlreadyCame = true;
        boolean StudentHasDebt = false;

        try {

            String SqlStudentControlQuery = "SELECT * FROM `student` WHERE  No LIKE '" + sug.getTxtno().getText().trim() + "'";
            SqlConnection.setResultSet(SqlStudentControlQuery);

            if (SqlConnection.getResultSet().next()) {
                if (!sug.getTxtNewNo().getText().trim().equals((SqlConnection.getResultSet().getString("No")))
                        || !sug.getTxtNewName().getText().trim().equals(SqlConnection.getResultSet().getString("Name"))
                        || !sug.getTxtNewSurname().getText().trim().equals(SqlConnection.getResultSet().getString("Surname"))
                        || !sug.getTxtNewEmail().getText().trim().equals(SqlConnection.getResultSet().getString("Email"))
                        || !sug.getTxtPhoneNo().getText().trim().equals(SqlConnection.getResultSet().getString("Phone"))) {
                    AlreadyCame = false;
                    throw new Exception();
                }
                if (Double.parseDouble(SqlConnection.getResultSet().getString("Debt")) > 0.0) {
                    StudentHasDebt = true;
                    throw new Exception();
                }
            } else {
                throw new Exception("Not found registered student.");
            }

            String SqlStudentdDeleteQuery = "DELETE FROM `student` WHERE No LIKE '" + sug.getTxtNewNo().getText().trim() + "'";

            SqlConnection.setPrepareStatement(SqlStudentdDeleteQuery);

            int answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete student?", "DELETE STUDENT WARNING",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (answer == JOptionPane.YES_OPTION) {
                SuccessVoice();

                Thread SendEmail = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        new JavaMailUtil().MailStudentWhoDeleted(sug.getTxtno().getText().trim(),
                                sug.getTxtNewName().getText().trim(), sug.getTxtNewSurname().getText().trim(),
                                sug.getTxtNewEmail().getText().trim());
                    }
                }
                );
                SendEmail.start();
                sug.getTxtNewNo().setText("");
                sug.getTxtNewName().setText("");
                sug.getTxtNewSurname().setText("");
                sug.getTxtPhoneNo().setText("");
                sug.getTxtNewEmail().setText("");
                sug.getTxtResult().setText("STUDENT IS DELETED SUCCESSFULLY");
                sug.getTxtResult().setBackground(new Color(255, 121, 63));
                SqlConnection.getPreparedStatement().execute();
            } else {
                sug.getTxtResult().setText("STUDENT DELETION IS CANCELED");
                sug.getTxtResult().setBackground(Color.PINK);
                java.awt.Toolkit.getDefaultToolkit().beep();
            }

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "ClASS NOT FOUND");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "\n\n\nStudent data must be retrived before delete student");
        } catch (Exception ex) {
            if (AlreadyCame == false) {
                java.awt.Toolkit.getDefaultToolkit().beep();
                int answer = JOptionPane.showConfirmDialog(null, "Student data must be retrived before delete student\n"
                        + "                                        Do you want to retrive data?", "MATCH EXCEPTION", JOptionPane.YES_NO_OPTION);
                if (answer == JOptionPane.YES_OPTION) {
                    DBStudentBringData();
                }
            } else if (StudentHasDebt == true) {
                JOptionPane.showMessageDialog(null, "Student is not allowed to delete because of having debt.");

                java.awt.Toolkit.getDefaultToolkit().beep();
                sug.getTxtResult().setBackground(new Color(255, 82, 82));
                sug.getTxtResult().setText("DELETION IS FAILED");
            } else {
                JOptionPane.showConfirmDialog(null, "Student is not found with number :  "+sug.getTxtno().getText().trim() , "DELETION ERROR", JOptionPane.ERROR_MESSAGE);
                java.awt.Toolkit.getDefaultToolkit().beep();
                sug.getTxtResult().setBackground(new Color(255, 82, 82));
                sug.getTxtResult().setText("DELETION IS FAILED");
            }
        } finally {

            SqlConnection.CloseAllConnections();

        }

    }

    public void DBStudentUpdate() {
        SqlConnection sqlConnection = new SqlConnection();

        try {

            if (sug.getTxtNewNo().getText().trim().equals("")
                    || sug.getTxtNewName().getText().trim().equals("")
                    || sug.getTxtNewSurname().getText().trim().equals("")
                    || sug.getTxtNewEmail().getText().trim().trim().equals("")
                    || sug.getTxtPhoneNo().getText().trim().equals("")
                    || sug.getTxtno().getText().trim().equals("")) {
                java.awt.Toolkit.getDefaultToolkit().beep();
                sug.getTxtResult().setText("MISSING INFORMATION/ UPDATION CANCELLED");
                sug.getTxtResult().setBackground(new Color(250, 130, 49));
                JOptionPane.showMessageDialog(null, "Please fill all textfields.", "UPDATE MISSING INFORMATION", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String SqlStudentUpdateQuery = "UPDATE `student` SET \n"
                    + "No='" + sug.getTxtNewNo().getText().trim() + "',\n"
                    + "Name='" + sug.getTxtNewName().getText().trim() + "',\n "
                    + "Surname='" + sug.getTxtNewSurname().getText().trim() + "', \n"
                    + "Email = '" + sug.getTxtNewEmail().getText().trim() + "',\n "
                    + "Phone ='" + sug.getTxtPhoneNo().getText().trim() + "'\n "
                    + "WHERE No LIKE  '" + sug.getTxtno().getText().trim() + "' ";

            DBStudentControlToUpdate();
            if (!StudentCanUpdate) {
                throw new Exception("New student no is allready in use. Updation is cancelled.");

            }
            int answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to update?", "UPDATE CONFIRMATION", JOptionPane.YES_NO_OPTION);
            if (answer != JOptionPane.YES_OPTION) {
                return;
            }

            sqlConnection.Update(SqlStudentUpdateQuery);
            String SqlBookStudentNoUpdateQuery = "UPDATE `book` SET \n"
                    + "StudentNo = '" + sug.getTxtNewNo().getText().trim() + "' "
                    + "WHERE StudentNo LIKE '" + sug.getTxtno().getText().trim() + "'";

            sqlConnection.Update(SqlBookStudentNoUpdateQuery);
            sug.getTxtResult().setBackground(Color.GREEN);
            sug.getTxtResult().setText("UPDATED SUCCESSFULLY");

            SuccessVoice();
        } catch (ClassNotFoundException ex) {
//            JOptionPane.showMessageDialog(null, ex + "aaaaaaaaaaaaaaaaa");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "\n\n\n1-) Must be retrived student data before update."
                    + "2-) Must type only numbers to student number.", "UPDATE ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {

        } finally {
            sqlConnection.CloseAllConnections();

        }

    }

    public void DBStudentBringData() {
        SqlConnection sqlConnection = new SqlConnection();

        StudentBringCame = false;
        Boolean AlreadyCame = false;
        try {

            String SqlStudentUpdateQuery = "SELECT * FROM `student` WHERE  No LIKE '" + sug.getTxtno().getText().trim() + "' ";
            sqlConnection.setResultSet(SqlStudentUpdateQuery);

            while (sqlConnection.getResultSet().next()) {

                if (sug.getTxtNewNo().getText().trim().equals(sqlConnection.getResultSet().getString("No"))
                        && sug.getTxtNewName().getText().trim().equals(sqlConnection.getResultSet().getString("Name"))
                        && sug.getTxtNewSurname().getText().trim().equals(sqlConnection.getResultSet().getString("Surname"))
                        && sug.getTxtNewEmail().getText().trim().equals(sqlConnection.getResultSet().getString("Email"))
                        && sug.getTxtPhoneNo().getText().trim().equals(sqlConnection.getResultSet().getString("Phone"))) {
                    AlreadyCame = true;
                    throw new Exception();
                }

                sug.getTxtNewNo().setText(sqlConnection.getResultSet().getString("No"));
                sug.getTxtNewName().setText(sqlConnection.getResultSet().getString("Name"));
                sug.getTxtNewSurname().setText(sqlConnection.getResultSet().getString("SurName"));
                sug.getTxtNewEmail().setText(sqlConnection.getResultSet().getString("Email"));
                sug.getTxtPhoneNo().setText(sqlConnection.getResultSet().getString("Phone"));
                StudentBringCame = true;
                sug.getTxtResult().setBackground(new Color(24, 220, 255));
                sug.getTxtResult().setText("DATA IS RETRIVED");

                SuccessVoice();
            }
            if (!StudentBringCame) {
                java.awt.Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Student is not found with number :  "+sug.getTxtno().getText().trim() , "EKSİK KAYIT HATASI", JOptionPane.ERROR_MESSAGE);
                sug.getTxtNewNo().setText("");
                sug.getTxtNewName().setText("");
                sug.getTxtNewSurname().setText("");
                sug.getTxtNewEmail().setText("");
                sug.getTxtPhoneNo().setText("");
                sug.getTxtResult().setBackground(new Color(255, 82, 82));
                sug.getTxtResult().setText("Requested data is not found in records.");

            }

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "\nPlease type only numbers in Student No.", "WRONG TYPE", JOptionPane.ERROR_MESSAGE);

        } catch (Exception ex) {
            if (AlreadyCame == true) {
                java.awt.Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Data already retrived.", "WARNING", JOptionPane.INFORMATION_MESSAGE);
            }
        } finally {
            sqlConnection.CloseAllConnections();

        }

    }

    public void DbStudentAdd() {
        SqlConnection sqlConnection = new SqlConnection();
        StudentCanAdd = true;

        try {

            sag.getTxtResult().setForeground(Color.BLACK);
            DBStudentControlToAdd();
            if (!StudentCanAdd) {
                throw new Exception();
            }

            String SqlStudentAdd = "INSERT INTO `student` "
                    + "(`Id`,`No`,`Name`,`Surname`,`Email`,`Phone`,`Debt`) VALUES "
                    + "(NULL,"
                    + "'" + sag.getTxtNo().getText().trim() + "',"
                    + "'" + sag.getTxtName().getText().trim() + "',"
                    + "'" + sag.getTxtSurname().getText().trim().toUpperCase() + "',"
                    + "'" + sag.getTxtEmail().getText().trim() + "',"
                    + "'" + sag.getTxtPhoneNo().getText().trim() + "',"
                    + "'0.0' )";

            sqlConnection.Update(SqlStudentAdd);

            sag.getTxtResult().setBackground(Color.GREEN);
            sag.getTxtResult().setText("Student is registed.");
            Thread SendEmail = new Thread(new Runnable() {
                @Override
                public void run() {
                    new JavaMailUtil().MailStudentWhoRegister(sag.getTxtNo().getText().trim(), sag.getTxtName().getText().trim(), sag.getTxtSurname().getText().trim().toUpperCase(),
                            sag.getTxtEmail().getText().trim());
                }
            }
            );
            SendEmail.start();
            SuccessVoice();

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } catch (Exception ex) {
            java.awt.Toolkit.getDefaultToolkit().beep();
            sag.getTxtResult().setBackground(Color.ORANGE);
            sag.getTxtResult().setText("Student is already registered.");

        } finally {

            sqlConnection.CloseAllConnections();

        }

    }

    public void DBStudentControlToUpdate() {
        SqlConnection sqlConnection = new SqlConnection();

        StudentCanUpdate = true;
        Boolean newStudentNoFree = true;
        Boolean oldStudentNoFree = false;
        Boolean allSame = false;

        try {

            String SqlStudentControlQuery = "SELECT * FROM  `student` WHERE No LIKE '" + sug.getTxtno().getText().trim() + "'";

            sqlConnection.setResultSet(SqlStudentControlQuery);
            while (sqlConnection.getResultSet().next()) {
                oldStudentNoFree = true;
            }
            if (oldStudentNoFree == false) {

                throw new Exception();

            }
            SqlStudentControlQuery = "SELECT * FROM  `student` WHERE No LIKE '" + sug.getTxtNewNo().getText().trim() + "'";

            sqlConnection.setResultSet(SqlStudentControlQuery);

            while (sqlConnection.getResultSet().next()) {
                if (sug.getTxtno().getText().equals(sqlConnection.getResultSet().getString("No"))) {
                    if (sug.getTxtNewNo().getText().trim().equals(sqlConnection.getResultSet().getString("No"))
                            && sug.getTxtNewName().getText().trim().equals(sqlConnection.getResultSet().getString("Name"))
                            && sug.getTxtNewSurname().getText().trim().equals(sqlConnection.getResultSet().getString("Surname"))
                            && sug.getTxtNewEmail().getText().trim().equals(sqlConnection.getResultSet().getString("Email"))
                            && sug.getTxtPhoneNo().getText().trim().equals(sqlConnection.getResultSet().getString("Phone"))) {

                        allSame = true;
                        throw new Exception();
                    }
                } else {
                    newStudentNoFree = false;
                    throw new Exception();
                }
            }

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex, "CLASS BULUNAMADI", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(ActionStudent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            StudentCanUpdate = false;
            java.awt.Toolkit.getDefaultToolkit().beep();
            if (allSame == true) {
                JOptionPane.showMessageDialog(null, "DATA IS ALREADY UP TO DATE", "GÜNCELLEME HATASI", JOptionPane.ERROR_MESSAGE);
                sug.getTxtResult().setBackground(new Color(254, 202, 87));
                sug.getTxtResult().setText("Already updated.");
            } else if (newStudentNoFree == false) {
                JOptionPane.showMessageDialog(null, " New Student No is in use. Updation is Failed", "UPDATE ERROR", JOptionPane.ERROR_MESSAGE);
            } else if (oldStudentNoFree == false) {
                JOptionPane.showMessageDialog(null, " Student no is not found in records.\n"
                        + "Updation is Failed", "UPDATE ERROR", JOptionPane.ERROR_MESSAGE);
                sug.getTxtResult().setBackground(new Color(255, 82, 82));
                sug.getTxtResult().setText("UPDATION FAILED.");
                return;

            }

        } finally {

            sqlConnection.CloseAllConnections();
        }
    }

    public void DBStudentControlToAdd() {
        SqlConnection sqlConnection = new SqlConnection();
        StudentCanAdd = true;

        try {

            String SqlStudentControlQuery = "SELECT * FROM  `student` WHERE No LIKE '" + sag.getTxtNo().getText().trim() + "'";

            sqlConnection.setResultSet(SqlStudentControlQuery);
            while (sqlConnection.getResultSet().next()) {

                throw new Exception();
            }

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "BEKLENMEYEN HATA ", "Kayıt Hatası", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
        } catch (Exception ex) {
            StudentCanAdd = false;
        } finally {

            sqlConnection.CloseAllConnections();

        }

    }

    @Override
    public void focusGained(FocusEvent e) {
        if (sag != null) {
            if (sag.getTxtResult().getBackground() == Color.GREEN) {
                sag.getTxtResult().setBackground(new Color(206, 214, 224));
                sag.getTxtResult().setText("");
                sag.getTxtNo().setText("");
                sag.getTxtName().setText("");
                sag.getTxtSurname().setText("");
                sag.getTxtEmail().setText("");
                sag.getTxtPhoneNo().setText("");

            }
        }

        if (rsg != null) {
            if (e.getSource() == rsg.getTxtNo()) {
                rsg.getTxtNo().setForeground(Color.BLACK);
                rsg.getTxtNo().setFont(new Font("", Font.BOLD, (int) screenSizeWidth / 91));
                rsg.getTxtName().setForeground(rsgPlaceHolder);
                rsg.getTxtName().setFont(fontTxtPlaceHolder);
                rsg.getTxtName().setText("Type Name");
                rsg.getTxtSurname().setForeground(rsgPlaceHolder);
                rsg.getTxtSurname().setText("Type Surname");
                rsg.getTxtSurname().setFont(fontTxtPlaceHolder);
                if (rsg.getTxtNo().getText().trim().equals("Type No")) {
                    rsg.getTxtNo().setText("");
                }

            } else if (e.getSource() == rsg.getTxtName()) {
                rsg.getTxtName().setForeground(Color.BLACK);
                rsg.getTxtName().setFont(new Font("", Font.BOLD, (int) screenSizeWidth / 91));
                rsg.getTxtNo().setForeground(rsgPlaceHolder);
                rsg.getTxtNo().setText("Type No");
                rsg.getTxtNo().setFont(fontTxtPlaceHolder);
                rsg.getTxtSurname().setForeground(rsgPlaceHolder);
                rsg.getTxtSurname().setText("Type Surname");
                rsg.getTxtSurname().setFont(fontTxtPlaceHolder);
                if (rsg.getTxtName().getText().trim().equals("Type Name")) {
                    rsg.getTxtName().setText("");
                }
            } else if (e.getSource() == rsg.getTxtSurname()) {
                rsg.getTxtSurname().setForeground(Color.BLACK);
                rsg.getTxtSurname().setFont(new Font("", Font.BOLD, (int) screenSizeWidth / 91));
                rsg.getTxtName().setForeground(rsgPlaceHolder);
                rsg.getTxtName().setFont(fontTxtPlaceHolder);
                rsg.getTxtName().setText("Type Name");
                rsg.getTxtNo().setForeground(rsgPlaceHolder);
                rsg.getTxtNo().setText("Type No");
                rsg.getTxtNo().setFont(fontTxtPlaceHolder);
                if (rsg.getTxtSurname().getText().trim().equals("Type Surname")) {
                    rsg.getTxtSurname().setText("");
                }
            }
        }
    }

    public void clearAllTxtMainGui() {

        if (sag != null) {

            sag.getMg().gettxtStudentNo().setForeground(Color.GRAY);
            sag.getMg().gettxtStudentNo().setText("Type Student No");
            sag.getMg().getTxtBookBarcode().setForeground(Color.GRAY);
            sag.getMg().getTxtBookBarcode().setText("Type Book Barcode No");
            sag.getMg().getTxtBookName().setText("");
            sag.getMg().gettxtResultScreen().setText("");
            sag.getMg().gettxtResultScreen().setBackground(new Color(206, 214, 224));
        } else if (ssg != null) {

            ssg.getMg().gettxtStudentNo().setForeground(Color.GRAY);
            ssg.getMg().gettxtStudentNo().setText("Type Student No");
            ssg.getMg().getTxtBookBarcode().setForeground(Color.GRAY);
            ssg.getMg().getTxtBookBarcode().setText("Type Book Barcode No");
            ssg.getMg().getTxtBookName().setText("");
            ssg.getMg().gettxtResultScreen().setText("");
            ssg.getMg().gettxtResultScreen().setBackground(new Color(206, 214, 224));

        } else if (sug != null) {

            sug.getMg().gettxtStudentNo().setForeground(Color.GRAY);
            sug.getMg().gettxtStudentNo().setText("Type Student No");
            sug.getMg().getTxtBookBarcode().setForeground(Color.GRAY);
            sug.getMg().getTxtBookBarcode().setText("Type Book Barcode No");
            sug.getMg().getTxtBookName().setText("");
            sug.getMg().gettxtResultScreen().setText("");
            sug.getMg().gettxtResultScreen().setBackground(new Color(206, 214, 224));

        } else if (rsg != null) {

            rsg.getMg().gettxtStudentNo().setForeground(Color.GRAY);
            rsg.getMg().gettxtStudentNo().setText("Type Student No");
            rsg.getMg().getTxtBookBarcode().setForeground(Color.GRAY);
            rsg.getMg().getTxtBookBarcode().setText("Type Book Barcode No");
            rsg.getMg().getTxtBookName().setText("");
            rsg.getMg().gettxtResultScreen().setText("");
            rsg.getMg().gettxtResultScreen().setBackground(new Color(206, 214, 224));
        }
    }

    @Override
    public void focusLost(FocusEvent e) {

    }

    public void SuccessVoice() {
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File("tik.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();

        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(ActionsBook.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            Logger.getLogger(ActionsBook.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (LineUnavailableException ex) {
            Logger.getLogger(ActionsBook.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean ControlBeforeRemoveStudent() {
        SqlConnection sqlConnection = new SqlConnection();

        try {

            String BookExistQuery = "Select * FROM book WHERE StudentNo LIKE '" + sug.getTxtNewNo().getText().trim() + "'";
            int BookCounter = 0;
            sqlConnection.setResultSet(BookExistQuery);
            while (sqlConnection.getResultSet().next()) //    ;
            {
                BookCounter++;
            }

            if (BookCounter > 0) {

                java.awt.Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Student has " + BookCounter + " books. Student is not allowed to delete.");
                return false;
            } else {
                SuccessVoice();
                return true;

            }

        } catch (SQLException ex) {
            Logger.getLogger(ActionStudent.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {

            sqlConnection.CloseAllConnections();

        }

        return true;
    }

    public void BringStudentState() {
        SqlConnection sqlconnection = new SqlConnection();

        try {

            String StudentQuery = "SELECT * FROM  student RIGHT JOIN book ON book.StudentNo=student.No WHERE No LIKE '" + ssg.getTxtStudentNo().getText().trim() + "'";

            sqlconnection.setResultSet(StudentQuery);
            Date BorrowedDate1 = null;
            Date BorrowedDate2 = null;
            Date BorrowedDate3 = null;
            LocalDate localdate = LocalDate.now();
            SimpleDateFormat df2 = new SimpleDateFormat("EEE, dd MMM yyyy");

            if (sqlconnection.getResultSet().next()) {
                paintSsgDebt(sqlconnection.getResultSet().getDouble("Debt"));
                ssg.getTxtBookBarcodeNo1().setText(sqlconnection.getResultSet().getString("BarcodeNo"));
                ssg.getTxtBookName1().setText(sqlconnection.getResultSet().getString("Book.Name"));
                BorrowedDate1 = sqlconnection.getResultSet().getDate("borrowedDate");
                if (sqlconnection.getResultSet().next()) {
                    ssg.getTxtBookBarcodeNo2().setText(sqlconnection.getResultSet().getString("BarcodeNo"));
                    ssg.getTxtBookName2().setText(sqlconnection.getResultSet().getString("Book.Name"));

                    BorrowedDate2 = sqlconnection.getResultSet().getDate("borrowedDate");

                    if (sqlconnection.getResultSet().next()) {
                        ssg.getTxtBookBarcodeNo3().setText(sqlconnection.getResultSet().getString("BarcodeNo"));
                        ssg.getTxtBookName3().setText(sqlconnection.getResultSet().getString("Book.Name"));
                        BorrowedDate3 = sqlconnection.getResultSet().getDate("borrowedDate");

                    }
                }
                int delay = 0;
                String delayString = "";
                if (BorrowedDate1 != null) {

                    LocalDate borrowedDate = BorrowedDate1.toLocalDate();
                    LocalDate now = LocalDate.now();

                    Period diff = Period.between(borrowedDate, now);

                    if (diff.getYears() > 0) {
                        delay += 365 * diff.getYears();
                    }
                    if (diff.getMonths() > 0) {
                        delay += 30 * diff.getMonths();
                    }
                    if (diff.getDays() > 0) {
                        delay += diff.getDays();
                    }

                    if (delay < 30) {
                        delay = 30 - delay;

                        delayString = "+" + Integer.toString(delay);
                        ssg.getLblLendingDayNumber1().setForeground(Color.green);

                    } else {
                        delay++;
                        delay -= 30;

                        delayString = "-" + Integer.toString(delay);
                        ssg.getLblLendingDayNumber1().setForeground(Color.red);
                    }

                    ssg.getLblLendingDayNumber1().setText(delayString);

                    if (BorrowedDate2 != null) {
                        borrowedDate = BorrowedDate2.toLocalDate();
                        delay = 0;
                        diff = Period.between(borrowedDate, now);

                        if (diff.getYears() > 0) {
                            delay += 365 * diff.getYears();
                        }
                        if (diff.getMonths() > 0) {
                            delay += 30 * diff.getMonths();
                        }
                        if (diff.getDays() > 0) {
                            delay += diff.getDays();
                        }

                        if (delay < 30) {
                            delay = 30 - delay;
                            delayString = "+" + Integer.toString(delay);
                            ssg.getLblLendingDayNumber2().setForeground(Color.green);
                        } else {
                            delay -= 30;
                            delayString = "-" + Integer.toString(delay);
                            ssg.getLblLendingDayNumber2().setForeground(Color.red);
                        }

                        ssg.getLblLendingDayNumber2().setText(delayString);

                        if (BorrowedDate3 != null) {

                            delay = 0;
                            borrowedDate = BorrowedDate3.toLocalDate();
                            diff = Period.between(borrowedDate, now);

                            if (diff.getYears() > 0) {
                                delay += 365 * diff.getYears();
                            }
                            if (diff.getMonths() > 0) {
                                delay += 30 * diff.getMonths();
                            }
                            if (diff.getDays() > 0) {
                                delay += diff.getDays();
                            }

                            if (delay < 30) {
                                delay = 30 - delay;
                                delayString = "+" + Integer.toString(delay);
                                ssg.getLblLendingDayNumber3().setForeground(Color.green);
                            } else {
                                delay++;
                                delay -= 30;
                                delayString = "-" + Integer.toString(delay);
                                ssg.getLblLendingDayNumber3().setForeground(Color.red);
                            }
                            ssg.getLblLendingDayNumber3().setText(delayString);

                        }
                    }
                }

                SuccessVoice();
            } else {
                StudentQuery = "SELECT * FROM Student  WHERE No LIKE '" + ssg.getTxtStudentNo().getText().trim() + "' ";
                sqlconnection.setResultSet(StudentQuery);
                if (sqlconnection.getResultSet().next()) {
                    SuccessVoice();

                    paintSsgDebt(sqlconnection.getResultSet().getDouble("Debt"));
                } else {
                    java.awt.Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, " Student no "+ssg.getTxtStudentNo().getText().trim() +"is not found.", "STUDENT MISSMATCH", JOptionPane.ERROR_MESSAGE);

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActionStudent.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            sqlconnection.CloseAllConnections();

        }

    }

    public void paintSsgDebt(double debt) {
        if (debt > 0) {
            ssg.getTxtDept().setText(Double.toString(debt));

            ssg.getTxtDept().setBackground(new Color(255, 121, 121));
        } else if (debt < 0) {
            ssg.getTxtDept().setText(Double.toString(debt));

            ssg.getTxtDept().setBackground(Color.cyan);
        } else {

            ssg.getTxtDept().setText(Double.toString(debt));
            ssg.getTxtDept().setBackground(Color.ORANGE);

        }
    }

    public void ResetStudentState() {
        String EmptyTextForStudentState = "No Book";
        ssg.getTxtDept().setText("");
        ssg.getTxtDept().setBackground(new Color(206, 214, 224));
        ssg.getTxtBookName1().setText(EmptyTextForStudentState);
        ssg.getTxtBookName2().setText(EmptyTextForStudentState);
        ssg.getTxtBookName3().setText(EmptyTextForStudentState);
        ssg.getTxtBookBarcodeNo1().setText(EmptyTextForStudentState);
        ssg.getTxtBookBarcodeNo2().setText(EmptyTextForStudentState);
        ssg.getTxtBookBarcodeNo3().setText(EmptyTextForStudentState);
        ssg.getLblLendingDayNumber1().setText("---");
        ssg.getLblLendingDayNumber2().setText("---");
        ssg.getLblLendingDayNumber3().setText("---");
        ssg.getLblLendingDayNumber1().setForeground(Color.MAGENTA);
        ssg.getLblLendingDayNumber2().setForeground(Color.MAGENTA);
        ssg.getLblLendingDayNumber3().setForeground(Color.MAGENTA);

    }

    public void SearchRegisteredStudent(int search) {
        SqlConnection sqlConnection = new SqlConnection();

        final int bringAll = 0;
        final int searchName = 1;
        final int searchSurname = 2;
        final int searchNo = 3;
        boolean noVoice = false;
        StudentCanUpdate = true;
        String searchQuery = "";
        switch (search) {
            case searchName:
                searchQuery = "SELECT * FROM student WHERE name LIKE '%" + rsg.getTxtName().getText().trim() + "%'";
                break;
            case searchSurname:
                searchQuery = "SELECT * FROM student WHERE Surname LIKE '%" + rsg.getTxtSurname().getText().trim() + "%'";
                break;
            case searchNo:
                searchQuery = "SELECT * FROM student WHERE No LIKE '" + rsg.getTxtNo().getText().trim() + "%'";
                break;
            case bringAll:
                searchQuery = "SELECT * FROM student ";
                noVoice = true;
        }
        searchQuery += " ORDER BY No  ASC";
        try {

            sqlConnection.setResultSet(searchQuery);
            int totalStudentForQuery = 0;
            while (sqlConnection.getResultSet().next()) {
                totalStudentForQuery++;
            }

            sqlConnection.setResultSet(searchQuery);

            int counter = 0;
            rsg.DataOfTable = new String[totalStudentForQuery][6];

            while (sqlConnection.getResultSet().next()) {

                rsg.DataOfTable[counter][0] = Integer.toString(counter + 1);
                rsg.DataOfTable[counter][1] = sqlConnection.getResultSet().getString("No");
                rsg.DataOfTable[counter][2] = sqlConnection.getResultSet().getString("Name");
                rsg.DataOfTable[counter][3] = sqlConnection.getResultSet().getString("Surname");
                rsg.DataOfTable[counter][4] = sqlConnection.getResultSet().getString("Email");
                rsg.DataOfTable[counter][5] = sqlConnection.getResultSet().getString("Phone");

                counter++;
            }

            rsg.getJp().remove(rsg.getSp());
            rsg.setSp(new JTable(rsg.DataOfTable, rsg.HeadersOfTable));
            rsg.getJp().add(rsg.getSp());
            if (counter == 0) {
                noVoice = true;
                java.awt.Toolkit.getDefaultToolkit().beep();
            }
            if (noVoice == false) {
                SuccessVoice();

            }
        } catch (SQLException ex) {
            Logger.getLogger(ActionStudent.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            sqlConnection.CloseAllConnections();

        }

    }
}
