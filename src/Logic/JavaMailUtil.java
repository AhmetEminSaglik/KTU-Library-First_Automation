//      ahmeteminsaglik@gmail.com
package Logic;

import java.awt.Color;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class JavaMailUtil {

    final static int LAST7DAYS = 0;
    final static int LAST3DAYS = 1;
    final static int STARTEDFINE = 2;
    static int last7DayCounter = 0;
    static int last3DayCounter = 0;
    static int over30DayCounter = 0;

    static int CounterOfMail = 0;
    static boolean saveConditionOnMysql = true;
    static Message message;

    public static final String usernameEmail = "ahmeteminsaglik@gmail.com";
    public static final String passwordEmail = "pgdbtwqhklhfugoo";

    Icon icon = new ImageIcon("GitHub-Mark-64px.png");

    static String WhoSendMail = "<br><br><br>"
            + "   <font color=\"gray\">  "
            + "<hr style=\"border: 1px dashed gray;\" />"
            + " <i>Karadeniz Technical University / Of Faculty of Technology Library<br>"
            + "</i></font>"
            + "<br> <i><b> <i>"
            + "<a href=\"#\"i class=\"fa fa-github\"></i></a><a href=\"https://www.instagram.com/ahmeteminsaglik/?hl=tr\"> "
            + " Created by <font color=" + Color.red + " >Ahmet Emin SAĞLIK </font>" + "</a></i></b> "//<font size=\"4\"> </font>
            + "<br><b> <font size=\"4\" > <a href=\"https://github.com/AhmetEminSaglik\"> <img src=\"https://i.hizliresim.com/nyGGL1.png\">  AhmetEminSaglik</a> </font></b>";

    public void MailStudentWhoExtendTime(String name, String surname, String barcodeNo, String bookName, String email) {
        LocalDate localDate = LocalDate.now();

        String Text = "Dear Student " + name + " " + surname.toUpperCase() + ", <br><br>"
                + "You have renewed the loan period of the book name \"" + bookName + "\" with barcode no " + barcodeNo + " untill " + localDate + ". "
                + "Please return the book before " + localDate.plusDays(29) + ".<br>"
                + "Thank you for your understanding."
                +"<br><br>Wish you have a nice day.";

        Text += WhoSendMail;
        String MessageSubject = "Extension Book Loan Period (" + barcodeNo + ")";
        sendEmail(Text, email, MessageSubject);

    }

    public void MaidStudentWhoPayDebt(String nameSurname, Double MoneyFromStudent, Double Debt, String email) {

        LocalDate localDate = LocalDate.now();
        String Text = "Dear Student  " + nameSurname + ", <br><br>You have ";
        if (MoneyFromStudent > 0) {
            Text += "paid " + MoneyFromStudent + " TL of " + (MoneyFromStudent + Debt) + " debt ";
        } else {
            Text += "added more " + (-MoneyFromStudent) + " TL to your debt " + (Debt + MoneyFromStudent);
        }
        Text += "on " + localDate + ".";
        String TextAdd = "";
        if (Debt > 0.0) {
            TextAdd = " You have total " + Debt + " TL debt. For your information...";
        } else if (Debt < 0.0) {
            TextAdd = "You will get " + Debt + " TL from library. For your information...";
        } else {
            TextAdd = "Your debt is over. For your information...";

        }
        TextAdd += "<br><br>Wish you have a nice day.";
        Text += TextAdd + WhoSendMail;
        String MessageSubject = "Debt Payment Process(" + Debt + " TL)";

        sendEmail(Text, email, MessageSubject);

    }

    public void MailStudentWhoTakeBook(String name, String surname, String barcode, String bookName, String email) {
        LocalDate localDate = LocalDate.now();

        String Text = "Dear Student  " + name + " " + surname.toUpperCase() + ", <br><br>" +
                "You borrowed the \"" + bookName + "\" book with barcode no " + barcode + " on " + localDate + ". "
                + "Please return the book before " + localDate.plusDays(29) + "."
                + "Thank you for your understanding.<br>"
                + "Wish have a nice day.";

        Text += WhoSendMail;
        String MessageSubject = "Borrowed Book Process (" + barcode + ") ";
        sendEmail(Text, email, MessageSubject);

    }

    public void MailStudentWhoDeleted(String StudentNo, String name, String surname, String email) {

        //String Text = StudentNo + " numaralı " + name + " " + surname.toUpperCase() + " isimli Dear Student,<br><br>"
        String Text = "Dear Student " + name + " " + surname.toUpperCase() + ",<br><br>"
                + "Your record is deleted from Of Technical Faculty Library. Wish you success in your life.";
        String MessageSubject = "Record Deletion Process ";

        Text += WhoSendMail;
        sendEmail(Text, email, MessageSubject);
    }

    public void MailStudentWhoDeliverBookBack(String name, String surname, String barcode, String bookName, String email) {
        LocalDate localDate = LocalDate.now();

        String Text = "Dear Student  " + name + " " + surname.toUpperCase() + ", <br><br>" +
                "You returned the \"" + bookName + "\" with barcode no " + barcode + " on " + localDate + ".<br>"
                + "Thank you for your understanding. Have a good day.";
        String MessageSubject = "Book Return Process (" + barcode + ")";

        Text += WhoSendMail;
        sendEmail(Text, email, MessageSubject);

    }

    public void MailStudentWhoRegister(String StudentNo, String name, String surname, String email) {
        LocalDate localDate = LocalDate.now();
        //?????******
        //String Text = StudentNo + " numaralı Sevgili " + name + " " + surname + " isimli Dear Student,<br> <br>"
        String Text = "Dear Student " + name + " " + surname + ", <br><br>" +
                "You are registered to Of Technical Faculty Library." +
                "From now on, you will be informed through this email. Have a good day.";

        String MessageSubject = "Register Student Process";

        Text += WhoSendMail;
        sendEmail(Text, email, MessageSubject);

    }

    public void FindStudentAndMailThem(int Degree, boolean MessageWillSend) {
        SqlConnection sqlConnection = new SqlConnection();
        String Query = "";

        if (Degree == 0) {

            Query = "SELECT * FROM book LEFT JOIN student ON book.StudentNo=student.No "
                    + "WHERE NOW() >  BorrowedDate + INTERVAL  23 DAY  AND book.Condition IS NULL";
        } else if (Degree == 1) {

            Query = "SELECT * FROM book LEFT JOIN student ON book.StudentNo=student.No"
                    + " WHERE NOW() >  BorrowedDate + INTERVAL  27 DAY  AND book.Condition < "
                    + LAST3DAYS/* + " or 'Condition' IS NULL )"*/;
        } else if (Degree == 2) {

            Query = "SELECT * FROM book LEFT JOIN student ON book.StudentNo=student.No"
                    + " WHERE NOW() >  BorrowedDate + INTERVAL  30 DAY  AND book.Condition < "
                    + STARTEDFINE /*+ " or book.Condition IS  NULL )"*/;
        } else {
            if (CounterOfMail > 0) {
                if (MessageWillSend == true) {

                    JOptionPane.showMessageDialog(null, CounterOfMail + " Emails have been sent.\n"
                            + last7DayCounter + " of them for students have less then 7 days.\n"
                            + last3DayCounter + " of them for students have less then 3 days.\n"
                            + over30DayCounter + " of them for students have exceed the time limit.\n\n"
                            + "You may close the application.");

                } else {

                    JOptionPane.showMessageDialog(null, "Send emails " + CounterOfMail + "  different students.\n"
                            + "Please run the program until see the email details.");

                    CounterOfMail = 0;
                    last7DayCounter = 0;
                    last3DayCounter = 0;
                    over30DayCounter = 0;

                    FindStudentAndMailThem(0, true);

                }
                sqlConnection.CloseAllConnections();
            }
            return;
        }

        sqlConnection.setResultSet(Query);
        try {

            while (sqlConnection.getResultSet().next()) {
                if (Degree == 0) {
                    last7DayCounter++;

                }
                if (Degree == 1) {
                    last3DayCounter++;

                }
                if (Degree == 2) {
                    over30DayCounter++;

                }

                if (MessageWillSend == true) {
                    saveConditionOnMysql = false;
                    String NameSurname = sqlConnection.getResultSet().getString("Student.Name") + " " + sqlConnection.getResultSet().getString("Student.SurName").toUpperCase();

                    sendEmail(NameSurname, sqlConnection.getResultSet().getString("Student.Email"),
                            Degree, sqlConnection.getResultSet().getString("book.BarcodeNo"));

                    if (saveConditionOnMysql == true) {

                        String updateQuery = "UPDATE book set book.Condition = " + Degree
                                + " WHERE  BarcodeNo LIKE '" + sqlConnection.getResultSet().getString("BarcodeNo") + "'";

                        sqlConnection.Update(updateQuery);

                    }
                } else {
                    CounterOfMail++;
                }

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error : \n\n\n" + ex);
        }
        //   sqlConnection.CloseAllConnections();

        FindStudentAndMailThem(++Degree, MessageWillSend);
    }

    public static void sendEmail(String Text, String email, String MessageSubject) {
        //MailStudentWhoRegister
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(usernameEmail, passwordEmail);
            }

        });

        prepareMessage(session, usernameEmail, email, Text, MessageSubject);

        try {

            Transport.send(message);

        } catch (MessagingException ex) {
            JOptionPane.showMessageDialog(null, ex + "", "BEKLENMEYEN HATA", JOptionPane.ERROR_MESSAGE);

        }

    }

    public static void prepareMessage(Session session, String username, String recepient, String Text, String MessageSubject) {
        try {
            message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject(MessageSubject);

            //  message.setHeader("Content-Encoding", "ISO-8859-9");
            message.setContent(Text, "text/html;charset=utf-8");

            //              ahmeteminsaglik@gmail.com
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex + "", "BEKLENMEYEN HATA", JOptionPane.ERROR_MESSAGE);

        }

    }

    public static void sendEmail(String nameSurname, String recepient, Double Debt) {
        saveConditionOnMysql = true;

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(usernameEmail, passwordEmail);
            }

        });
        prepareMessage(session, usernameEmail, recepient, Debt);

        try {

            Transport.send(message);

        } catch (MessagingException ex) {
            JOptionPane.showMessageDialog(null, ex + "", "BEKLENMEYEN HATA", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void prepareMessage(Session session, String username, String recepient, Double Debt) {
        try {
            message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Late Return Book Fine for the Of Technical Faculty.");

            message.setContent("Dear Student, <br><br>You have returned the book but it's time was exceed. So you have to pay " + Debt + "." +
                    "Please return the book in given time.<br>"
                    + "Thank you for your understanding. Have a good day."
                    + WhoSendMail, "text/html;charset=utf-8");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex + "", "BEKLENMEYEN HATA", JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void sendEmail(String nameSurname, String recepient, int condition, String BarcodeNo) {

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(usernameEmail, passwordEmail);
            }
        });

        Message message = PrepareMessage(nameSurname, session, usernameEmail, recepient, condition, BarcodeNo);

        saveConditionOnMysql = true;

        try {
            Transport.send(message);
            CounterOfMail++;
            saveConditionOnMysql = true;

        } catch (MessagingException ex) {
            JOptionPane.showMessageDialog(null, "HATA : \n\n\n" + ex);

        }
    }

    public static Message PrepareMessage(String nameSurname, Session session, String username, String recepient, int condition, String BookBarcodeNo) {
        SqlConnection sqlConnection = new SqlConnection();
        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(username));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            String query = "SELECT * FROM student RIGHT JOIN book ON book.StudentNo= student.No  WHERE book.BarcodeNo LIKE'" + BookBarcodeNo + "'";
            SqlConnection sqlConnection2 = new SqlConnection();
            sqlConnection2.setResultSet(query);
            sqlConnection2.getResultSet();
            String text = "Dear Student " + nameSurname + ", <br><br>";
            if (sqlConnection2.getResultSet().next()) {

            }
            //Dear Student

            switch (condition) {
                case LAST7DAYS:

                    message.setSubject("Of Technical Faculty Library Last 7 DAYS Warning ( Notification  / " + sqlConnection2.getResultSet().getString("book.BarcodeNo") + " )");

                    message.setContent(text +
                                    "You are approaching the due date for returning the borrowed book " + sqlConnection2.getResultSet().getString("book.Name") + ". "
                                    + "You have last 7 days to return the book. "
                                    + "Please return the book before " + lastDayOfBook(sqlConnection2.getResultSet().getString("book.BarcodeNo")) + ".<br>"
                                    + "Thank you for your understanding."
                                    + "<br><br>Wish you have a nice day." + WhoSendMail,
                            "text/html;charset=utf-8");

                    break;
                case LAST3DAYS:

                    message.setSubject("Of Technical Faculty Library Last 7 DAYS Warning ( Critical / " + sqlConnection2.getResultSet().getString("book.BarcodeNo") + " )");
                    message.setContent(text +
                                    "You are approaching the due date for returning the borrowed book " + sqlConnection2.getResultSet().getString("book.Name") + ". "
                                    + "You have last 3 days to return the book. "
                                    + "Please return the book before " + lastDayOfBook(sqlConnection2.getResultSet().getString("book.BarcodeNo")) + ".<br>"
                                    + "Thank you for your understanding. "
                                    + "<b>Wish you have a nice day." + WhoSendMail,
                            "text/html;charset=utf-8");
                    break;
                case STARTEDFINE:

                    message.setSubject("Of Technical Faculty Penalty Begin Date ( Penalty / " + sqlConnection2.getResultSet().getString("book.BarcodeNo") + " )");
                    message.setContent(text + "You have exceed the deadline the " + sqlConnection2.getResultSet().getString("book.Name") + " book with "
                                    + sqlConnection2.getResultSet().getString("book.BarcodeNo") + " barcode no. "
                                    + "The punitive action has been initiated against you. "
                                    + "Each day the book is returned late, a fine of 0.5 TL will be applied. "
                                    + "Please return the book as soon as possible.<br>"
                                    + "Thank you for your understanding."
                                    + "<br><br> Wish you have a nice day." + WhoSendMail,
                            "text/html;charset=utf-8");
                    break;

            }

            sqlConnection.CloseAllConnections();
            sqlConnection2.CloseAllConnections();
            return message;

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null, ex + "\n\nEmail Gönderilirken Hata Oluştu", "EMAİL GÖNDERME HATASI", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public static LocalDate lastDayOfBook(String BarcodeNo) {
        SqlConnection sqlConection3 = new SqlConnection();
        String lasDateQuery = "SELECT * FROM book WHERE BarcodeNo LIKE '" + BarcodeNo + "' ";
        sqlConection3.setResultSet(lasDateQuery);
        LocalDate borrowedDateOfBook = LocalDate.now();
        try {

            if (sqlConection3.getResultSet().next()) {
                Date LastDate = sqlConection3.getResultSet().getDate("BorrowedDate");

                borrowedDateOfBook = LastDate.toLocalDate();

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Kitap İade için Son Gün Belirlenirken Hata Meydana Geldi");
        }

        return borrowedDateOfBook.plusDays(29);
    }
}
