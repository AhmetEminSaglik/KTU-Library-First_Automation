package Logic;

import Gui.AboutUs;
import Gui.BookAddGui;
import Gui.BookReturnGui;
import Gui.BookSearchListGui;
import Gui.BookUpdateRemoveGui;
import Gui.FineDebtPayment;
import Gui.Login;
import Gui.MainGui;
import Gui.RegisteredStudentGui;
import Gui.StudentAddGui;
import Gui.StudentStateGui;
import Gui.StudentUpdateGui;
import Gui.TimeControlExtraTimeGui;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;

public class ActionsMainGui implements ActionListener, MouseListener, FocusListener {

    MainGui mg = null;

    boolean studentExist = true;
    boolean bookExist = true;
    boolean StudentCanTakeBook = true;
    boolean BookDeliveredSomeone = false;
    boolean bookFree = true;
    String StudentPlaceHolder = "Type Student No";
    String BarcodeNoPlaceHolder = "Type Book Barcode No";

    public ActionsMainGui(MainGui mg) {

        this.mg = mg;
    }

    public MainGui getMg() {
        if (mg == null) {
            mg = new MainGui();
        }
        return mg;
    }

    public void setMg(MainGui mg) {
        this.mg = mg;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == getMg().gettxtStudentNo() || e.getSource() == getMg().getTxtBookBarcode()) {
            if (getMg().gettxtStudentNo().getText().trim().equals("") || getMg().gettxtStudentNo().getText().equals(StudentPlaceHolder)) {
                java.awt.Toolkit.getDefaultToolkit().beep();
                getMg().gettxtResultScreen().setText("Fill Student No");
                getMg().gettxtResultScreen().setBackground(Color.YELLOW);
                //JOptionPane.showMessageDialog(null, "Öğrenci numarası boş bırakılamaz", "EKSİK BİLGİ", JOptionPane.ERROR_MESSAGE);
            } else if (getMg().getTxtBookBarcode().getText().trim().equals("")
                    || getMg().getTxtBookBarcode().getText().equals(BarcodeNoPlaceHolder)) {
                java.awt.Toolkit.getDefaultToolkit().beep();
                getMg().gettxtResultScreen().setText("Type Book Barcode No");
                getMg().gettxtResultScreen().setBackground(Color.ORANGE);
                //JOptionPane.showMessageDialog(null, "Kitap Barkod Numarası boş bırakılamaz", "EKSİK BİLGİ", JOptionPane.ERROR_MESSAGE);
            } else {
                getMg().getTxtBookName().setText("");

                DeliverBookToStudent();
                NumbersOfBooks();
            }

        }
        if (e.getSource() == getMg().getTxtBookBarcode()) {

        }
        if (e.getSource() == getMg().getBookAdd()) {

            getMg().getJp().setVisible(false);

            new BookAddGui(getMg());

        }
        if (e.getSource() == getMg().getBookReturn()) {

            getMg().getJp().setVisible(false);
            new BookReturnGui(getMg());

        }
        if (e.getSource() == getMg().getBookSearchList()) {
            mg.getJp().setVisible(false);
            new BookSearchListGui(getMg());
        }
        if (e.getSource() == getMg().getBookUpdateRemove()) {
            getMg().getJp().setVisible(false);
            new BookUpdateRemoveGui(getMg());
        }
        if (e.getSource() == getMg().getStudentAdd()) {
            StudentAddGui sag = new StudentAddGui(getMg());
        }
        if (e.getSource() == getMg().getStudentUpdate()) {
            getMg().getJp().setVisible(false);
            new StudentUpdateGui(getMg());

        }
        if (e.getSource() == getMg().getStudentState()) {

            new StudentStateGui(getMg());

        }
        if (e.getSource() == getMg().getRegisteredStudent()) {
            getMg().getJp().setVisible(false);
            new RegisteredStudentGui(getMg());

        }
        if (e.getSource() == getMg().getTimeControlExtraTime()) {
            getMg().getJp().setVisible(false);
            TimeControlExtraTimeGui tc_etg = new TimeControlExtraTimeGui(getMg());
            tc_etg.action.noVoice = true;
            tc_etg.action.SearchStudentBarkodNo(0);
        }
        if (e.getSource() == getMg().getAboutUs()) {
            getMg().getJp().setVisible(false);

            new AboutUs(getMg());
        }
        if (e.getSource() == getMg().getFineDebtPayment()) {
            getMg().getJp().setVisible(false);
            new FineDebtPayment(getMg());
        }
        if (e.getSource() == getMg().getExit()) {
            Object[] options = {"Exit", "Sign out ", "Cancel"};
            int answer = JOptionPane.showOptionDialog(null, "Please choose one", "EXIT OPTIONS", JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, options, null);

            if (answer == 0) {
                System.exit(0);
            } else if (answer == 1) {
                getMg().getJf().dispose();
                new Login();
            }
        }

    }

    public void insertUpdate(DocumentEvent e) {
        if (e.getDocument().getProperty("StudentNoBookBarcodetxt").equals(1)
                || e.getDocument().getProperty("StudentNoBookBarcodetxt").equals(2)) {

            try {

                Integer.parseInt(getMg().gettxtStudentNo().getText());

            } catch (NumberFormatException nfe) {

                int i = getMg().gettxtStudentNo().getText().length();
                while (i > 0) {
                    i--;
                    try {

                        if (getMg().gettxtStudentNo().getText().charAt(i) <= 9 || getMg().gettxtStudentNo().getText().charAt(i) >= 0) {
                        }
                    } catch (NumberFormatException nfe2) {
                        String StudentText = "";
                        for (int j = 0; j < i; j++) {
                            StudentText = "" + getMg().gettxtStudentNo().getText().charAt(j);

                        }
                        getMg().gettxtStudentNo().setText(StudentText);
                    }
                }
                {

                }
            }
        }
        if (e.getDocument().getProperty("StudentNoBookBarcodetxt").equals(1)) {

        }

        if (e.getDocument().getProperty("StudentNoBookBarcodetxt").equals(2)) {

        }

    }

    //  @Override
    public void removeUpdate(DocumentEvent e) {
        if (e.getDocument().getProperty("StudentNoBookBarcodetxt").equals(1)) {

        }
        if (e.getDocument().getProperty("StudentNoBookBarcodetxt").equals(2)) {

        }
        if (e.getDocument().getProperty("StudentNoBookBarcodetxt").equals(1)
                || e.getDocument().getProperty("StudentNoBookBarcodetxt").equals(2)) {
            boolean allInteger = false;

            try {

                Integer.parseInt(getMg().gettxtStudentNo().getText());

            } catch (NumberFormatException nfe) {

            }

        }

    }

    public void changedUpdate(DocumentEvent e) {

    }

    public void findButtonsSourceWithUsingMouse(MouseEvent e, Color background_color, Color foreground_color) {
        if (e.getSource() == getMg().getBookAdd()) {
            getMg().getBookAdd().setBackground(background_color);
            getMg().getBookAdd().setForeground(foreground_color);
        }
        if (e.getSource() == getMg().getBookReturn()) {
            getMg().getBookReturn().setBackground(background_color);
            getMg().getBookReturn().setForeground(foreground_color);
        }
        if (e.getSource() == getMg().getBookSearchList()) {
            getMg().getBookSearchList().setBackground(background_color);
            getMg().getBookSearchList().setForeground(foreground_color);
        }
        if (e.getSource() == getMg().getBookUpdateRemove()) {
            getMg().getBookUpdateRemove().setBackground(background_color);
            getMg().getBookUpdateRemove().setForeground(foreground_color);
        }
        if (e.getSource() == getMg().getStudentAdd()) {
            getMg().getStudentAdd().setBackground(background_color);
            getMg().getStudentAdd().setForeground(foreground_color);
        }
        if (e.getSource() == getMg().getStudentUpdate()) {
            getMg().getStudentUpdate().setBackground(background_color);
            getMg().getStudentUpdate().setForeground(foreground_color);
        }
        if (e.getSource() == getMg().getStudentState()) {
            getMg().getStudentState().setBackground(background_color);
            getMg().getStudentState().setForeground(foreground_color);
        }
        if (e.getSource() == getMg().getRegisteredStudent()) {
            getMg().getRegisteredStudent().setBackground(background_color);
            getMg().getRegisteredStudent().setForeground(foreground_color);
        }
        if (e.getSource() == getMg().getTimeControlExtraTime()) {
            getMg().getTimeControlExtraTime().setBackground(background_color);
            getMg().getTimeControlExtraTime().setForeground(foreground_color);
        }

        if (e.getSource() == getMg().getAboutUs()) {
            getMg().getAboutUs().setBackground(background_color);
            getMg().getAboutUs().setForeground(foreground_color);
        }
        if (e.getSource() == getMg().getExit()) {
            getMg().getExit().setBackground(background_color);
            getMg().getExit().setForeground(foreground_color);
        }
        if (e.getSource() == getMg().getFineDebtPayment()) {
            getMg().getFineDebtPayment().setBackground(background_color);
            getMg().getFineDebtPayment().setForeground(foreground_color);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        findButtonsSourceWithUsingMouse(e, Color.GRAY, Color.WHITE);

    }

    @Override
    public void mouseExited(MouseEvent e) {
        findButtonsSourceWithUsingMouse(e, Color.WHITE, Color.BLACK);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == getMg().gettxtStudentNo()) {
            if (getMg().gettxtStudentNo().getText().trim().equals(StudentPlaceHolder)) {
                getMg().gettxtStudentNo().setText("");
                getMg().gettxtStudentNo().setForeground(Color.BLACK);
            }
        } else if (e.getSource() == getMg().getTxtBookBarcode()) {
            if (getMg().getTxtBookBarcode().getText().trim().equals(BarcodeNoPlaceHolder)) {
                getMg().getTxtBookBarcode().setText("");
                getMg().getTxtBookBarcode().setForeground(Color.BLACK);

            }

        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == getMg().gettxtStudentNo()) {
            if (getMg().gettxtStudentNo().getText().trim().equals("")) {
                getMg().gettxtStudentNo().setForeground(Color.GRAY);
                getMg().gettxtStudentNo().setText(StudentPlaceHolder);
            }
        } else if (e.getSource() == getMg().getTxtBookBarcode()) {
            if (getMg().getTxtBookBarcode().getText().trim().equals("")) {
                getMg().getTxtBookBarcode().setForeground(Color.GRAY);
                getMg().getTxtBookBarcode().setText(BarcodeNoPlaceHolder);

            }
        }
    }

    public void StudentCanTakeBook() {
        SqlConnection sqlConnection = new SqlConnection();

        try {

            String StudentExistQuery = "SELECT * FROM student WHERE NO LIKE '" + getMg().gettxtStudentNo().getText().trim() + "'";
            sqlConnection.setResultSet(StudentExistQuery);
            if (!sqlConnection.getResultSet().next()) {

                StudentCanTakeBook = false;
                throw new Exception();
            } else {

            }
            String CanStudentTakeBookQuery = "SELECT * FROM book  RIGHT JOIN student ON  book.StudentNo =student.No  WHERE book.StudentNo LIKE'"
                    + getMg().gettxtStudentNo().getText() + "'";

            sqlConnection.setResultSet(CanStudentTakeBookQuery);
            int TookBookCounter = 0;
            while (sqlConnection.getResultSet().next()) {
                TookBookCounter++;
            }
            if (TookBookCounter == 3) {
                java.awt.Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Student has 3 books. Not allowed to give more books.", "BOOK BORROWED ERROR", JOptionPane.ERROR_MESSAGE);
                getMg().gettxtResultScreen().setText("NOT GIVEN. OVER REQUEST");
                getMg().gettxtResultScreen().setBackground(new Color(165, 94, 234));
                StudentCanTakeBook = false;
            }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "CLASS NOT FOUND");
        } catch (SQLException ex) {
        } catch (Exception ex) {
            if (StudentCanTakeBook == false) {
                java.awt.Toolkit.getDefaultToolkit().beep();

                getMg().gettxtResultScreen().setBackground(Color.red);
                getMg().gettxtResultScreen().setText("STUDENT IS NOT FOUND");
            }
        } finally {
            sqlConnection.CloseAllConnections();
        }

    }

    public void BookCanBeTake() {
        SqlConnection sqlConnection = new SqlConnection();

        try {

            String BookExistQuery = "Select * FROM book WHERE BarcodeNo LIKE '" + getMg().getTxtBookBarcode().getText().trim() + "'";
            sqlConnection.setResultSet(BookExistQuery);
            if (!sqlConnection.getResultSet().next()) {

                bookExist = false;

                throw new Exception();
            }
            String TakeBookName = "SELECT * FROM book WHERE BarcodeNo like '" + getMg().getTxtBookBarcode().getText().trim() + "'";

            sqlConnection.setResultSet(TakeBookName);
            if (sqlConnection.getResultSet().next()) {
                getMg().getTxtBookName().setText(sqlConnection.getResultSet().getString("Name"));
            }

            String BookCanBeDelivered = "SELECT * FROM book  LEFT JOIN student ON  book.StudentNo =student.No  "
                    + "WHERE  book.BarcodeNo LIKE  '" + getMg().getTxtBookBarcode().getText() + "' and book.StudentNo is null";
            sqlConnection.setResultSet(BookCanBeDelivered);// rs = stmt.executeQuery(BookCanBeDelivered);
            if (!sqlConnection.getResultSet().next()) {

                BookDeliveredSomeone = true;
                throw new Exception();
            }

        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {

        } catch (Exception ex) {
            bookFree = false;
            if (bookExist == false) {
                java.awt.Toolkit.getDefaultToolkit().beep();
                getMg().gettxtResultScreen().setText("BOOK IS NOT FOUND");
                getMg().gettxtResultScreen().setBackground(new Color(235, 59, 90));
            } else if (BookDeliveredSomeone == true) {
                java.awt.Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Book is given already.",
                        "MATCH ERROR: BOOK IS ALREADY GIVEN TO STUDENT", JOptionPane.ERROR_MESSAGE);
                getMg().gettxtResultScreen().setText("BOOK IS GIVEN ALREADY");
                getMg().gettxtResultScreen().setBackground(Color.ORANGE);
            }
        } finally {
            sqlConnection.CloseAllConnections();
        }

    }

    public void DeliverBookToStudent() {
        SqlConnection sqlConnection = new SqlConnection();

        StudentCanTakeBook = true;
        BookDeliveredSomeone = false;
        bookFree = true;
        studentExist = true;
        bookExist = true;

        try {
            StudentCanTakeBook();
            if (StudentCanTakeBook == false) {

                return;
            }
            BookCanBeTake();
            if (bookFree == false) {

                return;
            }

            String SqlDeliverBookStudent = "UPDATE  book SET StudentNo = '" + getMg().gettxtStudentNo().getText()
                    + "' , BorrowedDate = NOW()  "
                    + " \n where  BarcodeNo LIKE '" + getMg().getTxtBookBarcode().getText() + "'";
            sqlConnection.Update(SqlDeliverBookStudent);//  stmt.executeUpdate(SqlDeliverBookStudent);
            sqlConnection.setResultSet("SELECT * FROM student WHERE No LIKE '" + getMg().gettxtStudentNo().getText().trim() + "' ");
            if (sqlConnection.getResultSet().next()) {
                sendEmail(sqlConnection.getResultSet().getString("Name"),
                        sqlConnection.getResultSet().getString("Surname"),
                        getMg().getTxtBookBarcode().getText().trim(),
                        getMg().getTxtBookName().getText().trim(),
                        sqlConnection.getResultSet().getString("Email"));
            }
            getMg().gettxtResultScreen().setBackground(Color.GREEN);
            getMg().gettxtResultScreen().setText("MATCH SUCCESSFULLY");
            SuccessVoice();
        } catch (SQLException ex) {
            Logger.getLogger(ActionsMainGui.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            sqlConnection.CloseAllConnections();

        }

    }

    public void sendEmail(String name, String surname, String bacodeNo, String bookName, String email) {

        Thread sendEmailThread = new Thread(new Runnable() {
            @Override
            public void run() {
                new JavaMailUtil().MailStudentWhoTakeBook(name, surname, bacodeNo, bookName, email);
            }

        });

        sendEmailThread.start();

    }

    public void SuccessVoice() {
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File("tik.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();

        } catch (UnsupportedAudioFileException ex) {
        } catch (IOException ex) {
        } catch (LineUnavailableException ex) {
        }

    }

    public void NumbersOfBooks() {
        SqlConnection sqlConnection = new SqlConnection();

        try {

            String bookTotalNumberQuery = "SELECT COUNT(*) FROM book ";
            sqlConnection.setResultSet(bookTotalNumberQuery);
            if (sqlConnection.getResultSet().next()) {
                getMg().getTxtTotalBook().setText(Integer.toString(sqlConnection.getResultSet().getInt("COUNT(*)")));

            }
            String bookRemainNumberQuery = "SELECT COUNT(*) FROM book WHERE StudentNo IS NULL";
            sqlConnection.setResultSet(bookRemainNumberQuery);
            if (sqlConnection.getResultSet().next()) {
                getMg().getTxtRemainBook().setText(Integer.toString(sqlConnection.getResultSet().getInt("COUNT(*)")));

            }
            String bookGivenNumberQuery = "SELECT COUNT(*) FROM book  WHERE StudentNo IS NOT NULL";
            sqlConnection.setResultSet(bookGivenNumberQuery);
            if (sqlConnection.getResultSet().next()) {
                getMg().getTxtGivenBook().setText(Integer.toString(sqlConnection.getResultSet().getInt("COUNT(*)")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ActionsMainGui.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            sqlConnection.CloseAllConnections();

        }

    }

}
