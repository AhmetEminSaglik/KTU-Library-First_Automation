package Logic;

import Gui.BookAddGui;
import Gui.BookReturnGui;
import Gui.BookSearchListGui;
import Gui.BookUpdateRemoveGui;
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
import java.time.LocalDate;
import java.time.Period;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ActionsBook implements ActionListener, FocusListener {

    BookAddGui bag;
    BookReturnGui brg;
    BookSearchListGui bslg;
    BookUpdateRemoveGui burg;
    boolean BeepVoice = true;
    JButton clearBook_info_txt;
    JButton saveBook_info_txt;
    public final int DB_BARKODNO = 0;
    public final int DB_BOOKNAME = 1;
    public final int DB_AUTHORNAME = 2;
    public final int DB_CATEGORYNAME = 3;
    boolean firstValueChangedActionWillPerform = true;
    Boolean BookCanAdd;
    boolean BookBringCame;
    boolean BookCanUpdate;
    Color bslgPlaceHolder = Color.GRAY;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final double screenSizeWidth = screenSize.getWidth();
    final double screenSizeHeight = screenSize.getHeight();
    Font fontTxtPlaceHolder = new Font("", Font.ITALIC, (int) screenSizeWidth / 80);

    int[] dizi = new int[2];

    public ActionsBook(BookReturnGui brg) {
        this.brg = brg;

    }

    public ActionsBook(BookAddGui bag) {
        this.bag = bag;
    }

    public ActionsBook(BookSearchListGui bslg) {

        this.bslg = bslg;
        BeepVoice = false;
        SearcBookList(5);
    }

    public ActionsBook(BookUpdateRemoveGui burg) {
        this.burg = burg;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (bag != null) {
            if (e.getSource() == bag.getBtnComeBack()) {

                bag.getJp().setVisible(false);
                bag.getMg().getJp().setVisible(true);
                bag.getMg().getJf().setTitle("ANA SAYFA");
                clearAllTxtMainGui();

            } else if (e.getSource() == bag.getBtnAddBook()
                    || e.getSource() == bag.getTxtBookBarcodeNo()
                    || e.getSource() == bag.getTxtBookName()
                    || e.getSource() == bag.getTxtCategory()
                    || e.getSource() == bag.getTxtAuthorName()) {
                if (!bag.getTxtBookBarcodeNo().getText().trim().equals("")
                        && !bag.getTxtBookName().getText().trim().equals("")
                        && !bag.getTxtCategory().getText().trim().equals("")
                        && !bag.getTxtAuthorName().getText().trim().equals("")) {
                    DBbookAdd();
                }

            } else {
                java.awt.Toolkit.getDefaultToolkit().beep();
                bag.getTxtResult().setText("Lütfen Hepsini Eksiksiz doldurun");
                bag.getTxtResult().setForeground(Color.red);
                bag.getTxtResult().setBackground(Color.BLACK);

            }
        } else if (brg != null) {
            if (e.getSource() == brg.getBtnComeBack()) {
                brg.getJp().setVisible(false);
                brg.getMg().getJp().setVisible(true);
                brg.getMg().getJf().setTitle("ANA SAYFA");
                clearAllTxtMainGui();
            } else if (e.getSource() == brg.getTxtBarcodeNo() || e.getSource() == brg.getTxtStudentNo()) {
                java.awt.Toolkit.getDefaultToolkit().beep();
                if (brg.getTxtBarcodeNo().getText().trim().equals("")) {
                    brg.getTxtResult().setText("Kitap Barkod Numarasını Doldurun");
                    brg.getTxtResult().setBackground(Color.ORANGE);
                } else if (brg.getTxtStudentNo().getText().trim().equals("")) {
                    brg.getTxtResult().setText("Öğrenci Numarasını Doldurun");
                    brg.getTxtResult().setBackground(Color.YELLOW);

                } else {
                    if (StudentExist() == true && BookExist() == true) {
                        ReturnBookToLibrary();
                    }

                }
            }
        } else if (bslg
                != null) {

            if (e.getSource() == bslg.getBtnComeBack()) {
                bslg.getJp().setVisible(false);
                bslg.getMg().getJf().setTitle("ANA SAYFA");
                bslg.getMg().getJp().setVisible(true);
                clearAllTxtMainGui();
            } else if (e.getSource() == bslg.getTxtAuthorName()) {
                SearcBookList(0);
            } else if (e.getSource() == bslg.getTxtBookName()) {
                SearcBookList(1);
            } else if (e.getSource() == bslg.getTxtBarcodeNo()) {
                SearcBookList(2);
            } else if (e.getSource() == bslg.getTxtCategory()) {
                SearcBookList(3);
            }

        } else if (burg
                != null) {
            if (e.getSource() == burg.getBtnComeBack()) {
                burg.setVisible(false);
                burg.getMg().getJp().setVisible(true);
                burg.getJf().setTitle("ANA SAYFA");
                clearAllTxtMainGui();
            } else if (e.getSource() == burg.getBtnUpdate()
                    || e.getSource() == burg.getTxtNewBarcodeNo()
                    || e.getSource() == burg.getTxtNewBookName()
                    || e.getSource() == burg.getTxtNewAuthorName()
                    || e.getSource() == burg.getTxtNewCategory()) {
                DBBookUpdate();
            } else if (e.getSource() == burg.getBtnDelete()) {

                DBBookDelete();
            } else if (e.getSource() == burg.getTxtBarcodeNo()) {

                DBBookBringData();
            }
        }
    }

    public void clearAllTxtMainGui() {

        if (bag != null) {
            bag.getMg().action.NumbersOfBooks();
            bag.getMg().gettxtStudentNo().setForeground(Color.GRAY);
            bag.getMg().gettxtStudentNo().setText("Öğrenci No Girin");
            bag.getMg().getTxtBookBarcode().setForeground(Color.GRAY);
            bag.getMg().getTxtBookBarcode().setText("Kitap Barkod No girin");
            bag.getMg().getTxtBookName().setText("");
            bag.getMg().gettxtResultScreen().setText("");
            bag.getMg().gettxtResultScreen().setBackground(new Color(206, 214, 224));

        } else if (brg != null) {
            brg.getMg().action.NumbersOfBooks();
            brg.getMg().gettxtStudentNo().setForeground(Color.GRAY);
            brg.getMg().gettxtStudentNo().setText("Öğrenci No Girin");
            brg.getMg().getTxtBookBarcode().setForeground(Color.GRAY);
            brg.getMg().getTxtBookBarcode().setText("Kitap Barkod No girin");
            brg.getMg().getTxtBookName().setText("");
            brg.getMg().gettxtResultScreen().setText("");
            brg.getMg().gettxtResultScreen().setBackground(new Color(206, 214, 224));
        } else if (bslg != null) {
            bslg.getMg().gettxtStudentNo().setForeground(Color.GRAY);
            bslg.getMg().gettxtStudentNo().setText("Öğrenci No Girin");
            bslg.getMg().getTxtBookBarcode().setForeground(Color.GRAY);
            bslg.getMg().getTxtBookBarcode().setText("Kitap Barkod No girin");
            bslg.getMg().getTxtBookName().setText("");
            bslg.getMg().gettxtResultScreen().setText("");
            bslg.getMg().gettxtResultScreen().setBackground(new Color(206, 214, 224));

        } else if (burg != null) {
            burg.getMg().action.NumbersOfBooks();
            burg.getMg().gettxtStudentNo().setForeground(Color.GRAY);
            burg.getMg().gettxtStudentNo().setText("Öğrenci No Girin");
            burg.getMg().getTxtBookBarcode().setForeground(Color.GRAY);
            burg.getMg().getTxtBookBarcode().setText("Kitap Barkod No girin");
            burg.getMg().getTxtBookName().setText("");
            burg.getMg().gettxtResultScreen().setText("");
            burg.getMg().gettxtResultScreen().setBackground(new Color(206, 214, 224));
        }
    }

    public JButton getClearBook_info_txt() {
        return clearBook_info_txt;
    }

    public void setClearBook_info_txt(JButton clearBook_info_txt) {
        this.clearBook_info_txt = clearBook_info_txt;
    }

    public JButton getSaveBook_info_txt() {
        return saveBook_info_txt;
    }

    public void setSaveBook_info_txt(JButton saveBook_info_txt) {
        this.saveBook_info_txt = saveBook_info_txt;
    }

    @Override
    public void focusGained(FocusEvent e) {

        if (bag != null) {
            if (bag.getTxtResult().getBackground() == Color.GREEN) {
                bag.getTxtResult().setBackground(new Color(206, 214, 224));
                bag.getTxtResult().setText("");
                bag.getTxtAuthorName().setText("");
                bag.getTxtCategory().setText("");
                bag.getTxtBookName().setText("");
                bag.getTxtBookBarcodeNo().setText("");
            }

        } else if (bslg != null) {

            if (e.getSource() == bslg.getTxtAuthorName()) {

                bslg.setTxtAuthorName(bslgPlaceHolder(bslg.getTxtAuthorName()));
            } else if (e.getSource() == bslg.getTxtBarcodeNo()) {

                bslg.setTxtBarcodeNo(bslgPlaceHolder(bslg.getTxtBarcodeNo()));
            } else if (e.getSource() == bslg.getTxtBookName()) {
                bslg.setTxtBookName(bslgPlaceHolder(bslg.getTxtBookName()));
            } else if (e.getSource() == bslg.getTxtCategory()) {
                bslg.setTxtCategory(bslgPlaceHolder(bslg.getTxtCategory()));
            }
        }
    }

    public JTextField bslgPlaceHolder(JTextField jtxt) {
        if (jtxt != bslg.getTxtBarcodeNo()) {
            bslg.getTxtBarcodeNo().setFont(fontTxtPlaceHolder);
            bslg.getTxtBarcodeNo().setForeground(bslgPlaceHolder);
            bslg.getTxtBarcodeNo().setText("Barkod numarası giriniz");
        }
        if (jtxt != bslg.getTxtAuthorName()) {
            bslg.getTxtAuthorName().setFont(fontTxtPlaceHolder);
            bslg.getTxtAuthorName().setForeground(bslgPlaceHolder);
            bslg.getTxtAuthorName().setText("Yazar ismi giriniz");
        }
        if (jtxt != bslg.getTxtBookName()) {
            bslg.getTxtBookName().setFont(fontTxtPlaceHolder);
            bslg.getTxtBookName().setForeground(bslgPlaceHolder);
            bslg.getTxtBookName().setText("Kitap ismi giriniz");
        }
        if (jtxt != bslg.getTxtCategory()) {
            bslg.getTxtCategory().setFont(fontTxtPlaceHolder);
            bslg.getTxtCategory().setForeground(bslgPlaceHolder);
            bslg.getTxtCategory().setText("Kategori ismi giriniz");
        }
        if (jtxt.getText().trim().equals("Barkod numarası giriniz")
                || jtxt.getText().trim().equals("Yazar ismi giriniz")
                || jtxt.getText().trim().equals("Kitap ismi giriniz")
                || jtxt.getText().trim().equals("Kategori ismi giriniz")) {
            jtxt.setText("");
        }
        jtxt.setFont(new Font("", Font.BOLD, (int) screenSizeWidth / 91));
        jtxt.setForeground(Color.BLACK);
        return jtxt;
    }

    @Override
    public void focusLost(FocusEvent e) {
    }

    public void DBbookControl() {
        SqlConnection sqlConnection = new SqlConnection();

        try {

            String SqlBookControlQuery = "SELECT * FROM  `book` WHERE BarcodeNo LIKE '" + bag.getTxtBookBarcodeNo().getText().trim() + "'";
            sqlConnection.setResultSet(SqlBookControlQuery);
            while (sqlConnection.getResultSet().next()) {

                throw new Exception("Önceden Bu Barkod Numarası Alınmış");
            }

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "11111111111111111", "Kayıt Hatası", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
        } catch (Exception ex) {
            BookCanAdd = false;
        } finally {
            sqlConnection.CloseAllConnections();
        }

    }

    public void DBbookAdd() {
        SqlConnection sqlConnection = new SqlConnection();
        BookCanAdd = true;

        try {

            bag.getTxtResult().setForeground(Color.BLACK);
            DBbookControl();
            if (!BookCanAdd) {

                throw new Exception();
            }

            String SqlBookAdd = "INSERT INTO `book` "
                    + "(`Id`,`BarcodeNo`,`Name`,`AuthorName`,`CategoryName`) VALUES "
                    + "(NULL,"
                    + "'" + (bag.getTxtBookBarcodeNo().getText().trim()) + "',"
                    + "'" + bag.getTxtBookName().getText().trim() + "',"
                    + "'" + bag.getTxtAuthorName().getText().trim() + "',"
                    + "'" + bag.getTxtCategory().getText().trim() + "')";

            sqlConnection.Update(SqlBookAdd);

            bag.getTxtResult().setBackground(Color.GREEN);
            bag.getTxtResult().setText("Kitap Kayıt Başarılı");

            SuccessVoice();

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } catch (SQLException ex) {
            java.awt.Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "1-)Kitap Barcode Numarasına Sadece Sayı Girilebilir\n\n "
                    + "2-)Xampp hata meydana geldi  \n\n "
                    + "(yukarıdaki çözümlerden birisini deneyin)", "SQL HATASI", JOptionPane.ERROR_MESSAGE);

            bag.getTxtBookBarcodeNo().setForeground(new Color(255, 159, 26));

        } catch (Exception ex) {
            java.awt.Toolkit.getDefaultToolkit().beep();
            bag.getTxtResult().setBackground(Color.ORANGE);
            bag.getTxtResult().setText("Bu Barkod Numarası Zaten Kayıtlı");

        } finally {
            sqlConnection.CloseAllConnections();
        }

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

    public void DBBookDelete() {
        SqlConnection sqlConnection = new SqlConnection();

        BookCanUpdate = true;

        boolean AlreadyCame = true;
        boolean StudentTookBook = false;

        try {
            String SqlBookControlQuery = "SELECT * FROM `book` WHERE  BarcodeNo LIKE '" + burg.getTxtBarcodeNo().getText() + "'";
            sqlConnection.setResultSet(SqlBookControlQuery);

            if (sqlConnection.getResultSet().next()) {

                if (!burg.getTxtNewBarcodeNo().getText().trim().equals((sqlConnection.getResultSet().getString("BarcodeNo")))
                        || !burg.getTxtNewBookName().getText().trim().equals(sqlConnection.getResultSet().getString("Name"))
                        || !burg.getTxtNewAuthorName().getText().trim().equals(sqlConnection.getResultSet().getString("AuthorName"))
                        || !burg.getTxtNewCategory().getText().trim().equals(sqlConnection.getResultSet().getString("CategoryName"))) {

                    AlreadyCame = false;
                    throw new Exception();
                }

                if (sqlConnection.getResultSet().getString("StudentNo") != null) {
                    StudentTookBook = true;
                    throw new Exception();
                }
            } else {

                throw new Exception("Kayıtlı Kitap Bulunamadı");
            }

            String SqlBookdDeleteQuery = "DELETE FROM `book` WHERE BarcodeNo LIKE '" + burg.getTxtNewBarcodeNo().getText().trim() + "'";

            sqlConnection.setPrepareStatement(SqlBookdDeleteQuery);
            int answer = JOptionPane.showConfirmDialog(null, "Kitabı Silmek istediğinizden Emin misiniz ? ", "SİLME UYARISI",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (answer == JOptionPane.YES_OPTION) {

                SuccessVoice();

                burg.getTxtNewBarcodeNo().setText("");
                burg.getTxtNewBookName().setText("");
                burg.getTxtNewAuthorName().setText("");
                burg.getTxtNewCategory().setText("");
                burg.getTxtResult().setText("Kitap Silindi");
                burg.getTxtResult().setBackground(new Color(255, 121, 63));
                sqlConnection.PreparedStatementExecute();
            } else {

                burg.getTxtResult().setText("Kitap Silme İşlemi İptal Edildi");
                burg.getTxtResult().setBackground(Color.PINK);
                java.awt.Toolkit.getDefaultToolkit().beep();
            }

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "ClASS NOT FOUND");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "SQL HATASI", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            java.awt.Toolkit.getDefaultToolkit().beep();
            if (AlreadyCame == false) {

                int answer = JOptionPane.showConfirmDialog(null, "Kitabı Silmek İçin Önce Kitap Bilgilerini Getirmeniz gerekmektedir\n"
                        + "                                        Bilgiler Getirilsin mi ?", "EŞLEŞME HATASI", JOptionPane.YES_NO_OPTION);
                if (answer == JOptionPane.YES_OPTION) {
                    DBBookBringData();
                }
            } else if (StudentTookBook == true) {
                burg.getTxtResult().setText("Kitap Öğrenci Üzerine Kayıtlı, Silme Başarısız");
                burg.getTxtResult().setBackground(new Color(250, 177, 160));

            } else {
                JOptionPane.showMessageDialog(null, ex);
            }

        } finally {
            sqlConnection.CloseAllConnections();
        }

    }

    public void DBBookBringData() {
        SqlConnection sqlConnection = new SqlConnection();

        BookBringCame = false;
        Boolean AlreadyCame = false;
        boolean BarcodeNoEmpty = false;

        try {
            String SqlBookBrinQuery = "SELECT * FROM `book` WHERE  BarcodeNo LIKE '" + burg.getTxtBarcodeNo().getText().trim() + "'";

            sqlConnection.setResultSet(SqlBookBrinQuery);
            while (sqlConnection.getResultSet().next()) {

                BarcodeNoEmpty = true;
                if (burg.getTxtNewBarcodeNo().getText().trim().equals((sqlConnection.getResultSet().getString("BarcodeNo")))
                        && burg.getTxtNewBookName().getText().trim().equals(sqlConnection.getResultSet().getString("Name"))
                        && burg.getTxtNewAuthorName().getText().trim().equals(sqlConnection.getResultSet().getString("AuthorName"))
                        && burg.getTxtNewCategory().getText().trim().equals(sqlConnection.getResultSet().getString("CategoryName"))) {
                    AlreadyCame = true;

                    throw new Exception();
                }

                burg.getTxtNewBarcodeNo().setText(sqlConnection.getResultSet().getString("BarcodeNo"));
                burg.getTxtNewBookName().setText(sqlConnection.getResultSet().getString("Name"));
                burg.getTxtNewAuthorName().setText(sqlConnection.getResultSet().getString("AuthorName"));
                burg.getTxtNewCategory().setText(sqlConnection.getResultSet().getString("CategoryName"));
                BookBringCame = true;
                burg.getTxtResult().setBackground(new Color(24, 220, 255));
                burg.getTxtResult().setText("Bilgiler Getirildi");

                SuccessVoice();
            }
            if (!BookBringCame) {
                java.awt.Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, burg.getTxtBarcodeNo().getText().trim() + " Barkod Nolu Kitap Kaydı YOKTUR",
                        "EKSİK KAYIT HATASI", JOptionPane.ERROR_MESSAGE);
                burg.getTxtNewBarcodeNo().setText("");
                burg.getTxtNewBookName().setText("");
                burg.getTxtNewAuthorName().setText("");
                burg.getTxtNewCategory().setText("");

                burg.getTxtResult().setBackground(new Color(255, 82, 82));
                burg.getTxtResult().setText("İstenilen Bilgiler Kayıtta Bulunamadı");

            }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex + " ");
        } catch (SQLException ex) {
            if (BarcodeNoEmpty == false) {
                JOptionPane.showMessageDialog(null, "Barkod Numarasını Doldurup Aratmalısınız", "SQL HATASI", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception ex) {
            if (AlreadyCame == true) {
                java.awt.Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Aratılan Barkod Nolu Kitap  Zaten Getirildi", "ARAMA HATASI", JOptionPane.ERROR_MESSAGE);
            }
        } finally {
            sqlConnection.CloseAllConnections();
        }

    }

    public void DBBookUpdate() {
        SqlConnection sqlConnection = new SqlConnection();

        BookCanUpdate = true;
        boolean emptyArea = false;
        try {
            if (burg.getTxtNewBarcodeNo().getText().trim().equals("")
                    || burg.getTxtNewBookName().getText().equals("")
                    || burg.getTxtNewAuthorName().getText().equals("")
                    || burg.getTxtNewCategory().getText().equals("")) {
                emptyArea = true;
                throw new Exception();
            }
            String SqlBookUpdateQuery = "UPDATE `book` SET \n"
                    + "BarcodeNo= '" + burg.getTxtNewBarcodeNo().getText().trim() + "' ,\n"
                    + "Name='" + burg.getTxtNewBookName().getText().trim() + "',\n"
                    + "AuthorName='" + burg.getTxtNewAuthorName().getText().trim() + "', \n"
                    + "CategoryName= '" + burg.getTxtNewCategory().getText().trim() + "'\n"
                    + "WHERE  BarcodeNo LIKE '" + burg.getTxtBarcodeNo().getText().trim() + "' ";

            DBBookControlToUpdate();

            if (!BookCanUpdate) {

                throw new Exception();

            }
            int answer = JOptionPane.showConfirmDialog(null, "Güncellemek İstediğinize Emin misiniz?", "GÜNCELLEME ONAYI", JOptionPane.YES_NO_OPTION);
            if (answer != JOptionPane.YES_OPTION) {
                return;
            }

            sqlConnection.Update(SqlBookUpdateQuery);

            burg.getTxtResult().setBackground(Color.GREEN);
            burg.getTxtResult().setText("GÜNCELLENME BAŞARILI");
            SuccessVoice();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex + "aaaaaaaaaaaaaaaaa");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "\n\n\n1-) Bilgileri Güncelleyebilmeniz İçin Önce Öğrenci Numarasını\n"
                    + "Aratmalısınız\n"
                    + "2-) Yeni Öğrenci Numarasına Sayı girmelisiniz", "GÜNCELLEME HATASI", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            if (emptyArea == true) {
                java.awt.Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Lütfen Bilgileri Eksiksiz Bir Şekilde Doldurunuz", "EKSİK BİLGİ", JOptionPane.ERROR_MESSAGE);
                burg.getTxtResult().setText("Eksik Bilgi, Güncelleme Başarısız");
                burg.getTxtResult().setBackground(new Color(225, 112, 85));
            }
        } finally {
            sqlConnection.CloseAllConnections();
        }

    }

    public void DBBookControlToUpdate() {
        SqlConnection sqlConnection = new SqlConnection();

        boolean newBookNoFree = true;
        boolean oldBookNoFree = false;
        boolean allSame = false;
        BookCanUpdate = true;
        try {
            String SqlStudentControlQuery = "SELECT * FROM  `book` WHERE BarcodeNo LIKE '" + burg.getTxtBarcodeNo().getText() + "'";
            sqlConnection.setResultSet(SqlStudentControlQuery);

            while (sqlConnection.getResultSet().next()) {

                oldBookNoFree = true;
            }
            if (oldBookNoFree == false) {

                throw new Exception();

            }
            SqlStudentControlQuery = "SELECT * FROM  `book` WHERE BarcodeNo LIKE '" + burg.getTxtNewBarcodeNo().getText().trim() + "'";

            sqlConnection.setResultSet(SqlStudentControlQuery);

            while (sqlConnection.getResultSet().next()) {

                if (burg.getTxtBarcodeNo().getText().equals(sqlConnection.getResultSet().getString("BarcodeNo"))) {

                    if (burg.getTxtNewBarcodeNo().getText().trim().equals(sqlConnection.getResultSet().getString("BarcodeNo"))
                            && burg.getTxtNewBookName().getText().trim().equals(sqlConnection.getResultSet().getString("Name"))
                            && burg.getTxtNewAuthorName().getText().trim().equals(sqlConnection.getResultSet().getString("AuthorName"))
                            && burg.getTxtNewCategory().getText().trim().equals(sqlConnection.getResultSet().getString("CategoryName"))) {
                        allSame = true;

                        throw new Exception();
                    }
                } else {

                    newBookNoFree = false;

                    throw new Exception();
                }
            }

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Class Bulunamadı", "CLASS BULUNAMADI", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "SQL HATASI", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            BookCanUpdate = false;
            java.awt.Toolkit.getDefaultToolkit().beep();
            if (allSame == true) {
                JOptionPane.showMessageDialog(null, "Bilgiler zaten güncel", "GÜNCELLEME HATASI", JOptionPane.ERROR_MESSAGE);
            } else if (newBookNoFree == false) {
                JOptionPane.showMessageDialog(null, " YENİ Kitap Barkod Numarasında Başka Bir Kitap Kayıtlı", "GÜNCELLEME HATASI", JOptionPane.ERROR_MESSAGE);
            } else if (oldBookNoFree == false) {
                JOptionPane.showMessageDialog(null, " ESKİ Kitap Barkod Numarasında Kayıtlı Kitap Bulunmamaktadır\n"
                        + "Güncelleme Başarısız", "GÜNCELLEME HATASI", JOptionPane.ERROR_MESSAGE);
            }

            burg.getTxtResult().setBackground(Color.red);
            burg.getTxtResult().setBackground(new Color(255, 82, 82));
            burg.getTxtResult().setText("Güncelleme Başarısız");
        } finally {
            sqlConnection.CloseAllConnections();
        }

    }

    public boolean StudentExist() {
        SqlConnection sqlConnection = new SqlConnection();
        try {
            String StudentExistQuery = "Select * FROM student WHERE No LIKE '" + brg.getTxtStudentNo().getText().trim() + "'";
            sqlConnection.setResultSet(StudentExistQuery);
            if (sqlConnection.getResultSet().next()) {
                brg.getTxtStudentName().setText(sqlConnection.getResultSet().getString("Name"));
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "1 SQL HATASI");
        } finally {
            sqlConnection.CloseAllConnections();
        }
        java.awt.Toolkit.getDefaultToolkit().beep();
        brg.getTxtResult().setBackground(Color.red);
        brg.getTxtResult().setText("Kayıtlı Öğrenci Bulunamadı");
        return false;

    }

    public boolean BookExist() {
        SqlConnection sqlConnection = new SqlConnection();

        try {

            String BookExistQuery = "Select * FROM book WHERE BarcodeNo LIKE '" + brg.getTxtBarcodeNo().getText().trim() + "'";

            sqlConnection.setResultSet(BookExistQuery);
            if (!sqlConnection.getResultSet().next()) {
                java.awt.Toolkit.getDefaultToolkit().beep();
                brg.getTxtResult().setBackground(Color.red);
                brg.getTxtResult().setText("Kayıtlı Kitap Bulunamadı");
                return false;
            }
            brg.getTxtBookName().setText(sqlConnection.getResultSet().getString("Name"));
            brg.getTxtAuthorName().setText(sqlConnection.getResultSet().getString("AuthorName"));

        } catch (SQLException ex) {
            java.awt.Toolkit.getDefaultToolkit().beep();
            brg.getTxtResult().setText("EŞLEŞME BAŞARISIZ (Öğrenci Ödünç Alan kişi) / ( YADA ) / Kitap Şuan Kütüphanemizde bulunmaktadır");
            brg.getTxtResult().setBackground(new Color(250, 130, 49));
            return false;
        } finally {
            sqlConnection.CloseAllConnections();
        }

        return true;
    }

    public void sendEmailHasDebt(String nameSurname, String StudentEmail, double Debt) {
//        JavaMailUtil jmu = new JavaMailUtil();

        /*Thread sendEmailThread = new Thread(new Runnable() {
            @Override
            public void run() {
                jmu.sendEmail(nameSurname, StudentEmail, Debt);

            }

        });

        sendEmailThread.start();
*/
    }

    public void sendEmailHasNoDebt(String name, String surname, String bacodeNo, String bookName, String email) {
/*

        Thread sendEmailThread = new Thread(new Runnable() {
            @Override
            public void run() {
                new JavaMailUtil().MailStudentWhoDeliverBookBack(name, surname, bacodeNo, bookName, email);

            }

        });

        sendEmailThread.start();
*/

    }

    public void ReturnBookToLibrary() {
        SqlConnection sqlConnection = new SqlConnection();

        Double debt = 0.0;
        Double Fine = 0.0;

        final Double FineFee = 0.5;
        int delay = 0;
        Date borrowedDate = null;
        boolean FineAdded = false;

        try {

            String DeliverBookToLibraryQuery = "UPDATE book SET StudentNo = NULL , BorrowedDate = NULL ,"
                    + " book.Condition = NULL  WHERE StudentNo LIKE '" + brg.getTxtStudentNo().getText().trim() + "' and "
                    + "BarcodeNo LIKE '" + brg.getTxtBarcodeNo().getText().trim() + "' ";

            String addFine = "SELECT * FROM book INNER JOIN student ON book.studentNo=student.No "
                    + "where BarcodeNo LIKE'" + brg.getTxtBarcodeNo().getText().trim() + "' and "
                    + "studentNo LIKE '" + brg.getTxtStudentNo().getText().trim() + "'";
            sqlConnection.setResultSet(addFine);
            if (sqlConnection.getResultSet().next()) {
                String dayQuery = "SELECT DATEDIFF ('" + sqlConnection.getResultSet().getDate("borrowedDate") + "',NOW())";
                borrowedDate = sqlConnection.getResultSet().getDate("borrowedDate");
                debt = sqlConnection.getResultSet().getDouble("Debt");
                String StudentEmail = sqlConnection.getResultSet().getString("student.Email");
                sqlConnection.setResultSet(dayQuery);

                if (sqlConnection.getResultSet().next()) {

                    LocalDate borrowedDateForComparison = borrowedDate.toLocalDate();
                    LocalDate localdate = LocalDate.now();
                    Period diff = Period.between(borrowedDateForComparison, localdate);
                    if (diff.getYears() > 0) {
                        delay += 365 * diff.getYears();
                    }
                    if (diff.getMonths() > 0) {
                        delay += 30 * diff.getMonths();
                    }
                    if (diff.getDays() > 0) {
                        delay += diff.getDays();
                    }
                    if (delay > 30) {
                        delay = delay - 30;
                        Fine = FineFee * delay + debt;
                        FineAdded = true;
                        String Query = "SELECT * FROM student WHERE No LIKE '" + brg.getTxtStudentNo().getText().trim() + "'";
                        sqlConnection.setResultSet(Query);
                        if (sqlConnection.getResultSet().next()) {
                            String nameSurname = sqlConnection.getResultSet().getString("Name") + " " + sqlConnection.getResultSet().getString("Surname");
                            sendEmailHasDebt(nameSurname, StudentEmail, Fine);
                        }

                    } else {
                        Fine = debt;
                        sqlConnection.setResultSet("SELECT * FROM student WHERE no LIKE '" + brg.getTxtStudentNo().getText().trim()
                                + "'");
                        if (sqlConnection.getResultSet().next()) {
                            sendEmailHasNoDebt(sqlConnection.getResultSet().getString("Name"),
                                    sqlConnection.getResultSet().getString("Surname"),
                                    brg.getTxtBarcodeNo().getText().trim(),
                                    brg.getTxtBookName().getText().trim(),
                                    sqlConnection.getResultSet().getString("Email"));
                        }
                        //String name, String surname, String bacodeNo, String bookName, String email) {
                    }

                }
            }
            if (sqlConnection.Update(DeliverBookToLibraryQuery) == 1) {
                SuccessVoice();
                if (FineAdded == false) {

                    brg.getTxtResult().setBackground(Color.green);
                    brg.getTxtResult().setText("KİTAP İADE BAŞARILI");
                } else {

                    java.awt.Toolkit.getDefaultToolkit().beep();

                    brg.getTxtResult().setBackground(new Color(22, 160, 133));

                    brg.getTxtResult().setText("KİTAP İADE BAŞARILI  / ANCAK 30 GÜNÜ GEÇİRDİĞİ İÇİN CEZA YAPTIRIMI UYGUNLANMIŞTIR");

                }
                String Query = "UPDATE student Set debt=" + Fine + " WHERE  No LIKE '" + brg.getTxtStudentNo().getText().trim() + "'";
                sqlConnection.Update(Query);

            } else {
                DeliverBookToLibraryQuery = "SELECT * FROM book WHERE BarcodeNo LIKE '" + brg.getTxtBarcodeNo().getText().trim() + "'"
                        + " and StudentNo  IS NULL ";
                sqlConnection.setResultSet(DeliverBookToLibraryQuery);
                if (sqlConnection.getResultSet().next()) {

                    java.awt.Toolkit.getDefaultToolkit().beep();
                    brg.getTxtResult().setBackground(Color.ORANGE);
                    brg.getTxtResult().setText("Kitap Kütüphanemize Zaten İade Edilmiş.");
                } else {

                    java.awt.Toolkit.getDefaultToolkit().beep();
                    brg.getTxtResult().setBackground(Color.RED);
                    brg.getTxtResult().setText("Kitap - Öğrenci Uyuşmamaktadır. İade Alınmamıştır.");
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + " : 2 SQL HATASI");
        } finally {

            sqlConnection.CloseAllConnections();
        }

    }

    public void SearcBookList(int search) {
        SqlConnection sqlConnection = new SqlConnection();

        final int searchAuthor = 0;
        final int searchBookName = 1;
        final int searchBarcodeNo = 2;
        final int searchCategory = 3;
        final int searchAll = 5;

        boolean noVoice = false;

        String searchQuery = "";
        switch (search) {
            case searchAuthor:
                searchQuery = "SELECT * FROM book WHERE AuthorName LIKE '%" + bslg.getTxtAuthorName().getText().trim() + "%'";
                break;
            case searchBookName:
                searchQuery = "SELECT * FROM book WHERE Name LIKE '%" + bslg.getTxtBookName().getText().trim() + "%'";
                break;
            case searchBarcodeNo:
                searchQuery = "SELECT * FROM book WHERE BarcodeNo LIKE '" + bslg.getTxtBarcodeNo().getText().trim() + "'";
                break;
            case searchCategory:
                searchQuery = "SELECT * FROM book WHERE CategoryName LIKE '" + bslg.getTxtCategory().getText().trim() + "'";
                break;
            case searchAll:
                searchQuery = "SELECT * FROM book ";
                noVoice = true;
        }
        searchQuery += " ORDER BY Name ASC";
        try {

            sqlConnection.setResultSet(searchQuery);
            int totalBookForQuery = 0;
            while (sqlConnection.getResultSet().next()) {
                totalBookForQuery++;
            }

            sqlConnection.setResultSet(searchQuery);

            int counter = 0;
            bslg.DataOfTable = new String[totalBookForQuery][6];

            while (sqlConnection.getResultSet().next()) {

                bslg.DataOfTable[counter][0] = Integer.toString(counter + 1);
                bslg.DataOfTable[counter][1] = sqlConnection.getResultSet().getString("BarcodeNo");
                bslg.DataOfTable[counter][2] = sqlConnection.getResultSet().getString("Name");
                try {
                    if (sqlConnection.getResultSet().getString("StudentNo").equals(null)) {

                    }
                    bslg.DataOfTable[counter][3] = "Öğrencide";

                } catch (NullPointerException e) {
                    bslg.DataOfTable[counter][3] = "Rafta";
                }

                bslg.DataOfTable[counter][4] = sqlConnection.getResultSet().getString("CategoryName");
                bslg.DataOfTable[counter][5] = sqlConnection.getResultSet().getString("AuthorName");

                counter++;
            }

            if (counter == 0) {

                if (BeepVoice == false) {

                    BeepVoice = true;
                } else {
                    java.awt.Toolkit.getDefaultToolkit().beep();
                }
                noVoice = true;

            }

            bslg.getJp().remove(bslg.getSp());
            bslg.setSp(new JTable(bslg.DataOfTable, bslg.HeadersOfTable));
            bslg.getJp().add(bslg.getSp());
            if (noVoice == false) {
                SuccessVoice();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            sqlConnection.CloseAllConnections();

        }

    }

}
