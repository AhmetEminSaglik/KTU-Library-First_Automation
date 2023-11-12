/*
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

    public static final String usernameEmail = "example@gmail.com";
    public static final String passwordEmail = "passwrods";

    Icon icon = new ImageIcon("GitHub-Mark-64px.png");

    static String WhoSendMail = "<br><br><br>"
            + "   <font color=\"gray\">  "
            + "<hr style=\"border: 1px dashed gray;\" />"
            + " <i>Karadeniz  Teknik Üniversitesi / Of Teknoloji Fakültesi Kütüphanesi<br>"
            + "Enerji Mühendisliği Bölümü Giriş Katı</i></font>"
            + "<br> <i><b> <i>"
            + "<a href=\"#\"i class=\"fa fa-github\"></i></a><a href=\"https://www.instagram.com/ahmeteminsaglik/?hl=tr\"> "
            + " Created by <font color=" + Color.red + " >Ahmet Emin SAĞLIK </font>" + "</a></i></b> "//<font size=\"4\"> </font>
            + "<br><b> <font size=\"4\" > <a href=\"https://github.com/AhmetEminSaglik\"> <img src=\"https://i.hizliresim.com/nyGGL1.png\">  AhmetEminSaglik</a> </font></b>";

    public void MailStudentWhoExtendTime(String name, String surname, String barcodeNo, String bookName, String email) {
        LocalDate localDate = LocalDate.now();

        String Text = "Değerli Öğrencimiz  " + name + " " + surname.toUpperCase() + ", <br><br>" + localDate + " tarihinde " + barcodeNo + " barkod numaralı, " + bookName + " isimli kitabın "
                + "süresini uzatmış bulunmaktasınız. Lütfen  en geç " + localDate.plusDays(29) + " tarihinde kitabı teslim edin ya da bu zaman aralığında kitap süresini tekrar uzattırın. "
                + "Anlayışınız için teşekkür eder iyi günler dileriz.";

        Text += WhoSendMail;
        String MessageSubject = "Kitap Süresi Uzatma İşlemi (" + barcodeNo + ")";
        sendEmail(Text, email, MessageSubject);

    }

    public void MaidStudentWhoPayDebt(String nameSurname, Double MoneyFromStudent, Double Debt, String email) {

        LocalDate localDate = LocalDate.now();
        String Text = "Değerli Öğrencimiz  " + nameSurname + ", <br><br>" + localDate + " tarihinde ";
        if (MoneyFromStudent > 0) {
            Text += (MoneyFromStudent + Debt) + " TL olan borcunuzun " + MoneyFromStudent + " TL' sini ödemiş bulunmaktasınız. ";
        } else {
            Text += (Debt + MoneyFromStudent) + " TL olan borcunuza " + (-MoneyFromStudent) + " TL daha eklemiş bulunmaktasınız.";
        }

        String TextAdd = "";
        if (Debt > 0.0) {
            TextAdd = " Toplamda " + Debt + " TL daha borcunuz bulunmaktadır. Bilginize... ";
        } else if (Debt < 0.0) {
            TextAdd = Debt + " TL kadar kütüphanemizde alacağınız vardır. Bilginize... ";
        } else {
            TextAdd = "Borcunuz bitmiş bulunmaktadır. Bilginize... ";
        }
        Text += TextAdd + WhoSendMail;
        String MessageSubject = "Borç Ödeme İşlemi (" + Debt + " TL)";

        sendEmail(Text, email, MessageSubject);

    }

    public void MailStudentWhoTakeBook(String name, String surname, String barcode, String bookName, String email) {
        LocalDate localDate = LocalDate.now();

        String Text = "Değerli Öğrencimiz  " + name + " " + surname.toUpperCase() + ", <br><br>" + localDate + " tarihinde " + barcode + " barkod numaralı, " + bookName + " isimli kitabı "
                + "almış bulunmaktasınız. Lütfen  en geç " + localDate.plusDays(29) + " tarihinde kitabı teslim ediniz. "
                + "Anlayışınız için teşekkür eder iyi günler dileriz.";

        Text += WhoSendMail;
        String MessageSubject = "Kitap Alma İşlemi (" + barcode + ") ";
        sendEmail(Text, email, MessageSubject);

    }

    public void MailStudentWhoDeleted(String StudentNo, String name, String surname, String email) {

        //String Text = StudentNo + " numaralı " + name + " " + surname.toUpperCase() + " isimli Değerli Öğrencimiz,<br><br>"
        String Text = "Değerli Öğrencimiz " + name + " " + surname.toUpperCase() + ",<br><br>"
                + "Of Teknoloji Fakültesi Kütüphanesinden kaydınız silinmiştir. Hayatınızda başarılar dileriz. ";
        String MessageSubject = "Kayıt Silme İşlemi ";

        Text += WhoSendMail;
        sendEmail(Text, email, MessageSubject);
    }

    public void MailStudentWhoDeliverBookBack(String name, String surname, String barcode, String bookName, String email) {
        LocalDate localDate = LocalDate.now();

        String Text = "Değerli Öğrencimiz  " + name + " " + surname.toUpperCase() + ", <br><br>" + localDate + " tarihinde " + barcode + " barkod numaralı, " + bookName + " isimli kitabı "
                + "Of Teknoloji Fakültesi Kütüphanesine iade etmiş bulunmaktasınız. <br>"
                + "Anlayışınız için teşekkür eder iyi günler dileriz.";
        String MessageSubject = "Kitap İade İşlemi (" + barcode + ")";

        Text += WhoSendMail;
        sendEmail(Text, email, MessageSubject);

    }

    public void MailStudentWhoRegister(String StudentNo, String name, String surname, String email) {
        LocalDate localDate = LocalDate.now();
        //?????******
        //String Text = StudentNo + " numaralı Sevgili " + name + " " + surname + " isimli değerli öğrencimiz,<br> <br>"
        String Text = "Değerli Öğrencimiz " + name + " " + surname + ", <br><br>"
                + localDate + " tarihinde Of Teknoloji Fakültesi Kütüphanesi ne kaydınız yapılmıştır. "
                + "Bundan sonra bu mail üzerinden bilgilendirileceksiniz. İyi günler dileriz. ";
        String MessageSubject = "Öğrenci Kayıt İşlemi ";

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
                    + LAST3DAYS*/
/* + " or 'Condition' IS NULL )"*//*
;
        } else if (Degree == 2) {

            Query = "SELECT * FROM book LEFT JOIN student ON book.StudentNo=student.No"
                    + " WHERE NOW() >  BorrowedDate + INTERVAL  30 DAY  AND book.Condition < "
                    + STARTEDFINE */
/*+ " or book.Condition IS  NULL )"*//*
;
        } else {
            if (CounterOfMail > 0) {
                if (MessageWillSend == true) {

                    JOptionPane.showMessageDialog(null, CounterOfMail + " Tane Mail atılmıştır\n"
                            + last7DayCounter + " tanesi son 7 güne girenler için\n"
                            + last3DayCounter + " tanesi son 3 güne girenler için\n"
                            + over30DayCounter + " tanesi 30 günü aşanlar için \n\n"
                            + "Artık programı kapatabilirsiniz");

                } else {

                    JOptionPane.showMessageDialog(null, "Toplamda " + CounterOfMail + " Farklı Kişiye Mail Atılacaktır\n"
                            + "Lütfen atılan maillerin ayrıntısını  görmeden programı kapatmayınız");

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
            JOptionPane.showMessageDialog(null, "HATA : \n\n\n" + ex);
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
            message.setSubject("Of Teknoloji Fakültesi Kitap İade Geciktirme Cezası");

            message.setContent("Değerli öğrencimiz, <br><br>Az önce iade ettiğiniz kitabı geciktirdiğiniz için  kütüphaneye " + Debt + " TL "
                    + "borcunuz vardır. Lütfen zamanında teslim etmeye özen gösteriniz. Anlayışınız için teşekkür eder iyi günler dileriz."
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
            String text = "Değerli Öğrencimiz " + nameSurname + ", <br><br>";
            if (sqlConnection2.getResultSet().next()) {

            }
            //Değerli Öğrencimiz

            switch (condition) {
                case LAST7DAYS:

                    message.setSubject("Of Teknoloji Fakültesi Kütüphanesi SON 7 GÜN Uyarısı ( BİLDİRİM  / " + sqlConnection2.getResultSet().getString("book.BarcodeNo") + " )");

                    message.setContent(text + sqlConnection2.getResultSet().getString("book.CategoryName") + " kategorisindeki, "
                            + sqlConnection2.getResultSet().getString("book.AuthorName") + " isimli yazara ait olan, "
                            + sqlConnection2.getResultSet().getString("book.BarcodeNo") + " barkod numaralı, "
                            + sqlConnection2.getResultSet().getString("book.Name") + "  adlı kitabınızın iadesi için son 7 gün kalmıştır.\n\n "
                            + " EN GEÇ " + lastDayOfBook(sqlConnection2.getResultSet().getString("book.BarcodeNo")) + " "
                            + " TARİHİNDE KÜTÜPHANEYE TESLİM EDİLMESİ GEREKMEKTEDİR. " + " Anlayışınız için teşekkür eder iyi günler dileriz." + WhoSendMail,
                            "text/html;charset=utf-8");

                    break;
                case LAST3DAYS:

                    message.setSubject("Of Teknoloji Fakültesi Kütüphanesi SON 3 GÜN Uyarısı ( KRİTİK / " + sqlConnection2.getResultSet().getString("book.BarcodeNo") + " )");
                    message.setContent(text + sqlConnection2.getResultSet().getString("book.CategoryName") + " kategorisindeki, "
                            + sqlConnection2.getResultSet().getString("book.AuthorName") + " isimli yazara ait olan, "
                            + sqlConnection2.getResultSet().getString("book.BarcodeNo") + " barkod numaralı, "
                            + sqlConnection2.getResultSet().getString("book.Name") + "  adlı kitabınızın iadesi için son 3 gün kalmıştır.\n"
                            + "EN GEÇ " + lastDayOfBook(sqlConnection2.getResultSet().getString("book.BarcodeNo")) + " TARİHİNDE KÜTÜPHANEYE TESLİM EDİLMESİ GEREKMEKTEDİR. "
                            + " Lütfen para cezası aşamasına geçilmeden önce kitabınızı iade edin ya da kitabınızın süresini uzattırın. \n Anlayışınız için teşekkür eder iyi günler dileriz." + WhoSendMail,
                            "text/html;charset=utf-8");
                    break;
                case STARTEDFINE:

                    message.setSubject("Of Teknoloji Fakültesi Kütüphanesi CEZA BAŞLANGIÇ ( CEZA / " + sqlConnection2.getResultSet().getString("book.BarcodeNo") + " )");
                    message.setContent(text + sqlConnection2.getResultSet().getString("book.CategoryName") + " kategorisindeki, "
                            + sqlConnection2.getResultSet().getString("book.AuthorName") + " isimli yazara ait olan, "
                            + sqlConnection2.getResultSet().getString("book.BarcodeNo") + " barkod numaralı, "
                            + sqlConnection2.getResultSet().getString("book.Name") + "   adlı kitabınızın teslim süresini geciktirdiğiniz için hakkınızda cezai işlem başlatılmıştır. "
                            + "Geç getirdiğiniz gün başına 0.5 TL para cezası uygulanmaktadır. Anlayışınız için teşekkür eder iyi günler dileriz." + WhoSendMail,
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
*/
