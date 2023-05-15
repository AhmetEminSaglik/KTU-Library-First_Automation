package Gui;

import Logic.ActionsMainGui;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ToolTipManager;

public class MainGui {

    JFrame jf = null;
    JPanel jp = null;

    JLabel lblStudentNo;
    JLabel lblBookBarcode;
    JLabel lblResultScreen;
    JLabel lblBookName;

    JLabel lblTotalBook;
    JLabel lblRemainBook;
    JLabel lblGivenBook;

    JTextField txtStudentNo;
    JTextField txtBookBarcode;
    JTextField txtResultScreen;
    JTextField txtBookName;

    JTextField txtTotalBook;
    JTextField txtGivenBook;
    JTextField txtRemainBook;

    JButton bookAdd;
    JButton bookReturn;
    JButton bookSearchList;
    JButton bookUpdateRemove;
    JButton studentAdd;
    JButton studentUpdate;
    JButton studentState;
    JButton registeredStudent;
    JButton TimeControl_ExtraTime;
    JButton FineDebtPayment;
    JButton aboutUs;
    JButton exit;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final double screenSizeWidth = screenSize.getWidth();
    final double screenSizeHeight = screenSize.getHeight();

    final int holding_area_scale = (int) (screenSizeWidth / 5.059259259259259);
    final int push_scale = (int) (screenSizeWidth / 170.75);
    final int txt_height = (int) (screenSizeHeight / 19.2);
    int push_right_lbl_counter = 0;
    int push_right_txt_counter = 0;

    //  final int push_total_remain_given_books = 30;
    int total_remain_given_books_x = (int) (screenSizeWidth / 91.06666666666666);
    int total_remain_given_books_y = (int) (screenSizeHeight / 9.6);
    int total_remain_given_books_counter = 0;

    final int button_width = (int) (screenSizeWidth / 6.209090909090909);
    final int button_height = (int) (screenSizeHeight / 15.36);
    final int passing_floor_X = button_width + (int) (screenSizeWidth / 34.15);
    final int FloorOfBook_X = (int) (screenSizeWidth / 30.355555555555554);
    final int FloorOfStudent_X = FloorOfBook_X + passing_floor_X;
    final int FloorOf_Time_Exit_X = FloorOfStudent_X + passing_floor_X;
    final int passing_floor_Y = (int) (screenSizeHeight / 12.8);
    final int FirstButtons_Y = (int) (screenSizeHeight / 2.953846153846154);
    final int SecondButtons_Y = FirstButtons_Y + passing_floor_Y;
    final int ThirdButtons_Y = SecondButtons_Y + passing_floor_Y;
    final int FourthButtons_Y = ThirdButtons_Y + passing_floor_Y;
    final ToolTipManager ttm = ToolTipManager.sharedInstance();
    final int oldDelay = ttm.getInitialDelay();

    public ActionsMainGui action = new ActionsMainGui(this);

    public MainGui() {
    }

    public MainGui(Login login) {
        //getJp(). = login.getJf();
        setJf(login.getJf());
        getJf().setTitle("MAIN PAGE");
        getJp().setBounds(0, 0, getJf().getWidth(), getJf().getHeight());
        getJf().add(getJp());
        getJp().setVisible(true);
        addAllThingsONPanel();

        getJp().setBackground(new Color(47, 53, 66));

    }

    public void addAllThingsONPanel() {

        getJp().setVisible(true);
        getJp().add(getLblStudentNo());
        getJp().add(getLblBookBarcode());
        getJp().add(getLblResultScreen());
        getJp().add(getLblBookName());
        getJp().add(getLblRemainBook());
        getJp().add(getLblGivenBook());
        getJp().add(getLblTotalBook());
        getJp().add(getTxtGivenBook());
        getJp().add(getTxtRemainBook());
        getJp().add(getTxtTotalBook());
        getJp().add(gettxtStudentNo());
        getJp().add(getTxtBookBarcode());
        getJp().add(gettxtResultScreen());
        getJp().add(getTxtBookName());
        getJp().add(getBookAdd());
        getJp().add(getBookReturn());
        getJp().add(getBookSearchList());
        getJp().add(getBookUpdateRemove());
        getJp().add(getStudentAdd());
        getJp().add(getStudentState());
        getJp().add(getStudentUpdate());
        getJp().add(getRegisteredStudent());
        getJp().add(getTimeControlExtraTime());
        getJp().add(getFineDebtPayment());
        getJp().add(getAboutUs());
        getJp().add(getExit());

    }

    public JLabel build_JlabelForNulls(JLabel jlabel) {
        jlabel = new JLabel("BURAYA YAZI YAZILACAK");
        jlabel.setBounds((int) (screenSizeWidth / 136.6) + (holding_area_scale + push_scale) * push_right_lbl_counter,
                (int) (screenSizeWidth / 136.6), holding_area_scale, (int) (screenSizeWidth / 45.53333333333333));

        jlabel.setFont(new Font("monospaced", Font.BOLD, (int) ((int) screenSizeWidth / 54.64)));
        jlabel.setForeground(Color.white);
        push_right_lbl_counter++;

        return jlabel;

    }

    public JTextField build_JTextfiledForNulls(JTextField jtxt) {
        jtxt = new JTextField("");
        jtxt.setBackground(Color.WHITE);
        jtxt.setBounds((int) (screenSizeWidth / 455.3333333333333) + (holding_area_scale + push_scale) * push_right_txt_counter,
                (int) (screenSizeHeight / 15.36), holding_area_scale, txt_height);

        jtxt.setFont(new Font("monospaced", Font.BOLD, (int) screenSizeWidth / 80));
        push_right_txt_counter++;
        jtxt.setLayout(null);

        return jtxt;
    }

    public JButton build_JbuttonForNulls(JButton jbtn, int whichFloor_X, int sectorButtons_Y) {
        jbtn = new JButton();
        jbtn.setCursor(new Cursor(12));
        jbtn.setBounds(whichFloor_X, sectorButtons_Y, button_width, button_height);
        jbtn.setFont(new Font("", Font.BOLD, (int) screenSizeWidth / 80));
        jbtn.setForeground(Color.BLACK);

        jbtn.setBackground(Color.WHITE);
        jbtn.addActionListener(action);
        jbtn.addMouseListener(action);
        jbtn.setBorderPainted(false);
        return jbtn;
    }

    public JFrame getJf() {
        if (jf == null) {
            jf = new JFrame();
            double jframeWidth = screenSize.getWidth() / 1.6070588235294119;
            double jframeHeight = screenSize.getHeight() / 1.3963636363636365;
            int jframeX = (int) ((screenSize.getWidth() - jframeWidth) / 2);
            int jframeY = (int) ((screenSize.getHeight() - jframeHeight) / 2);

            jf.setBounds(jframeX, jframeY, (int) jframeWidth, (int) jframeHeight);

        }

        double jframeWidth = screenSize.getWidth() / 1.6070588235294119;
        double jframeHeight = screenSize.getHeight() / 1.3963636363636365;
        int jframeX = (int) ((screenSize.getWidth() - jframeWidth) / 2);
        int jframeY = (int) ((screenSize.getHeight() - jframeHeight) / 2);
        jf.setBounds(jframeX, jframeY, (int) jframeWidth, (int) jframeHeight);
        return jf;
    }

    public JPanel getJp() {
        if (jp == null) {
            jp = new JPanel();
            jp.setBounds(getJf().getBounds());
            jp.setLayout(null);
        }
        return jp;
    }

    public void setJp(JPanel jp) {
        this.jp = jp;
    }

    public void setJf(JFrame Jf) {
        this.jf = Jf;
    }

    public JLabel getLblStudentNo() {
        if (lblStudentNo == null) {
            lblStudentNo = build_JlabelForNulls(lblStudentNo);
            lblStudentNo.setText("Student No ");

        }
        return lblStudentNo;
    }

    public void setLblStudentNo(JLabel lblStudentNo) {
        this.lblStudentNo = lblStudentNo;
    }

    public JLabel getLblBookBarcode() {
        if (lblBookBarcode == null) {
            lblBookBarcode = build_JlabelForNulls(lblBookBarcode);
            lblBookBarcode.setText("Book Barcode No");
        }
        return lblBookBarcode;
    }

    public void setLblBookBarcode(JLabel lblBookBarcode) {
        this.lblBookBarcode = lblBookBarcode;
    }

    public JLabel getLblResultScreen() {
        if (lblResultScreen == null) {
            lblResultScreen = build_JlabelForNulls(lblResultScreen);
            lblResultScreen.setText("Result");

        }
        return lblResultScreen;
    }

    public void setLblResultScreen(JLabel lblResultScreen) {
        this.lblResultScreen = lblResultScreen;
    }

    public JLabel getLblBookName() {
        if (lblBookName == null) {
            lblBookName = new JLabel("Book Name");
            lblBookName.setBounds((int) (screenSizeWidth / 136.6), (int) (screenSizeHeight / 5.688888888888889),
                    (int) (screenSizeWidth / 4.553333333333334), (int) (screenSizeHeight / 10.971428571428572));
            lblBookName.setForeground(Color.WHITE);
            lblBookName.setFont(new Font("monospaced", Font.BOLD, (int) ((int) screenSizeWidth / 54.64)));
        }
        return lblBookName;
    }

    public void setLblBookName(JLabel lblBookName) {
        this.lblBookName = lblBookName;
    }

    public JLabel getLblTotalBook() {
        if (lblTotalBook == null) {
            lblTotalBook = new JLabel("Total Books Number");
            lblTotalBook.setBounds((total_remain_given_books_x + holding_area_scale) * total_remain_given_books_counter,
                    total_remain_given_books_y, holding_area_scale,
                    (int) (screenSizeHeight / 15.36));
            lblTotalBook.setFont(new Font("monospaced", Font.BOLD, (int) ((int) screenSizeWidth / 62.09)));
            lblTotalBook.setForeground(new Color(116, 185, 255));
            total_remain_given_books_counter = 0;
        }

        return lblTotalBook;
    }

    public void setLblTotalBook(JLabel lblTotalBook) {
        this.lblTotalBook = lblTotalBook;
    }

    public JLabel getLblRemainBook() {
        if (lblRemainBook == null) {
            lblRemainBook = new JLabel("Borrowed Books");
            lblRemainBook.setBounds((int) (screenSizeWidth / 45) + (total_remain_given_books_x + holding_area_scale) * total_remain_given_books_counter,
                    total_remain_given_books_y, holding_area_scale,
                    (int) (screenSizeHeight / 15.36));
            lblRemainBook.setFont(new Font("monospaced", Font.BOLD, (int) ((int) screenSizeWidth / 62.09)));
            lblRemainBook.setForeground(Color.red);
            total_remain_given_books_counter++;
        }
        return lblRemainBook;
    }

    public void setLblRemainBook(JLabel lblRemainBook) {
        this.lblRemainBook = lblRemainBook;
    }

    public JLabel getLblGivenBook() {
        if (lblGivenBook == null) {
            lblGivenBook = new JLabel("Books In Shelf");
            lblGivenBook.setBounds((int) (screenSizeWidth / 45) + (total_remain_given_books_x + holding_area_scale) * total_remain_given_books_counter,
                    1 + total_remain_given_books_y, holding_area_scale,
                    (int) (screenSizeHeight / 15.36));
            lblGivenBook.setFont(new Font("monospaced", Font.BOLD, (int) ((int) screenSizeWidth / 62.09)));
            lblGivenBook.setForeground(Color.GREEN);
            total_remain_given_books_counter++;
        }
        return lblGivenBook;
    }

    public void setLblGivenBook(JLabel lblGivenBook) {
        this.lblGivenBook = lblGivenBook;
    }

    public JTextField gettxtStudentNo() {
        if (txtStudentNo == null) {
            txtStudentNo = build_JTextfiledForNulls(txtStudentNo);

            txtStudentNo.addActionListener(action);
            txtStudentNo.addFocusListener(action);
            txtStudentNo.setForeground(Color.GRAY);
            txtStudentNo.setText("Type Student No");

        }
        return txtStudentNo;
    }

    public void settxtStudentNo(JTextField txtStudentNo) {
        this.txtStudentNo = txtStudentNo;
    }

    public JTextField getTxtBookBarcode() {
        if (txtBookBarcode == null) {
            txtBookBarcode = build_JTextfiledForNulls(txtBookBarcode);
            txtBookBarcode.addActionListener(action);
            txtBookBarcode.addFocusListener(action);
            txtBookBarcode.setForeground(Color.GRAY);
            txtBookBarcode.setText("Type Book Barcode No");

        }
        return txtBookBarcode;
    }

    public void setTxtBookBarcode(JTextField txtBookBarcode) {
        this.txtBookBarcode = txtBookBarcode;
    }

    public JTextField gettxtResultScreen() {
        if (txtResultScreen == null) {
            txtResultScreen = build_JTextfiledForNulls(txtResultScreen);
            txtResultScreen.setEditable(false);
            txtResultScreen.setToolTipText("Not allowed to acces here");
            ttm.setInitialDelay(100);
            ttm.setDismissDelay(1000);
            txtResultScreen.setBackground(new Color(206, 214, 224));
            txtResultScreen.setForeground(Color.BLACK);
        }
        return txtResultScreen;
    }

    public void settxtResultScreen(JTextField txtResultScreen) {
        this.txtResultScreen = txtResultScreen;
    }

    public JTextField getTxtBookName() {
        if (txtBookName == null) {
            txtBookName = new JTextField("");
            txtBookName.setBounds((int) (screenSizeWidth / 455.3333333333333), (int) (screenSizeHeight / 4.042105263157895),
                    (int) (screenSizeWidth / 1.6557575757575758), (int) (screenSizeHeight / 13.963636363636363));
            txtBookName.setFont(new Font("monospaced", Font.BOLD, (int) ((int) screenSizeWidth / 71)));
            txtBookName.setEditable(false);
            txtBookName.setFocusable(false);

            txtBookName.setBackground(new Color(206, 214, 224));

            ttm.setInitialDelay(100);
            ttm.setDismissDelay(1000);
            txtBookName.setToolTipText("Not allowed to acces here");

        }
        return txtBookName;
    }

    public void setTxtBookName(JTextField txtBookName) {
        this.txtBookName = txtBookName;
    }

    public JTextField getTxtTotalBook() {
        if (txtTotalBook == null) {
            txtTotalBook = new JTextField("0");
            txtTotalBook.setBounds((int) (screenSizeWidth / 27.32) + (total_remain_given_books_x + holding_area_scale) * total_remain_given_books_counter,
                    total_remain_given_books_y + (int) (screenSizeHeight / 17.066666666666666), (int) (screenSizeWidth / 9.106666666666667), (int) (screenSizeHeight / 25.6));

            txtTotalBook.setFont(new Font("", Font.BOLD, 22));
            txtTotalBook.setEditable(false);
            txtTotalBook.setFocusable(false);
            txtTotalBook.setBackground(new Color(116, 185, 255));
            txtTotalBook.setBorder(BorderFactory.createLineBorder(Color.WHITE, 0));
            txtTotalBook.setBorder(BorderFactory.createCompoundBorder(txtGivenBook.getBorder(), BorderFactory.createEmptyBorder(0, 20, 0, 0)));

            txtTotalBook.addActionListener(action);
            total_remain_given_books_counter++;

        }
        return txtTotalBook;
    }

    public void setTxtTotalBook(JTextField txtTotalBook) {
        this.txtTotalBook = txtTotalBook;
    }

    public JTextField getTxtGivenBook() {
        if (txtGivenBook == null) {
            txtGivenBook = new JTextField("0");
            txtGivenBook.setBounds((int) (screenSizeWidth / 27.32) + (total_remain_given_books_x + holding_area_scale) * total_remain_given_books_counter,
                    total_remain_given_books_y + (int) (screenSizeHeight / 17.066666666666666), (int) (screenSizeWidth / 9.106666666666667), (int) (screenSizeHeight / 25.6));

            txtGivenBook.setFont(new Font("", Font.BOLD, 22));
            txtGivenBook.setEditable(false);
            txtGivenBook.setFocusable(false);
            txtGivenBook.setBackground(Color.red);
            txtGivenBook.setForeground(Color.BLACK);
            total_remain_given_books_counter++;
            txtGivenBook.setBorder(BorderFactory.createLineBorder(Color.WHITE, 0));
            txtGivenBook.setBorder(BorderFactory.createCompoundBorder(txtGivenBook.getBorder(), BorderFactory.createEmptyBorder(0, (int) (screenSizeWidth / 21), 0, 0)));

            txtGivenBook.addActionListener(action);
        }
        return txtGivenBook;
    }

    public void setTxtGivenBook(JTextField txtGivenBook) {

        this.txtGivenBook = txtGivenBook;
    }

    public JTextField getTxtRemainBook() {
        if (txtRemainBook == null) {
            txtRemainBook = new JTextField("0");
            txtRemainBook.setBounds((int) (screenSizeWidth / 27.32) + (total_remain_given_books_x + holding_area_scale) * total_remain_given_books_counter,
                    total_remain_given_books_y + (int) (screenSizeHeight / 17.066666666666666), (int) (screenSizeWidth / 9.106666666666667), (int) (screenSizeHeight / 25.6));
            txtRemainBook.setBackground(Color.GREEN);
            txtRemainBook.setForeground(Color.BLACK);
            txtRemainBook.setFont(new Font("", Font.BOLD, 22));
            txtRemainBook.setEditable(false);
            txtRemainBook.setFocusable(false);
            txtRemainBook.setBorder(BorderFactory.createLineBorder(Color.WHITE, 0));
            txtRemainBook.setBorder(BorderFactory.createCompoundBorder(txtGivenBook.getBorder(), BorderFactory.createEmptyBorder(0, 10, 0, 0)));
            txtRemainBook.addActionListener(action);
            total_remain_given_books_counter++;
        }
        return txtRemainBook;
    }

    public void setTxtRemainBook(JTextField txtRemainBook) {
        this.txtRemainBook = txtRemainBook;
    }

    public JButton getBookAdd() {
        if (bookAdd == null) {
            bookAdd = build_JbuttonForNulls(bookAdd, FloorOfBook_X, FirstButtons_Y);
            bookAdd.setText("Add Book");
        }
        return bookAdd;
    }

    public void setBookAdd(JButton bookAdd) {
        this.bookAdd = bookAdd;
    }

    public JButton getBookReturn() {
        if (bookReturn == null) {
            bookReturn = build_JbuttonForNulls(bookReturn, FloorOfBook_X, SecondButtons_Y);
            bookReturn.setText("Return Book");
        }
        return bookReturn;
    }

    public void setBookReturn(JButton bookReturn) {
        this.bookReturn = bookReturn;
    }

    public JButton getBookSearchList() {
        if (bookSearchList == null) {
            bookSearchList = build_JbuttonForNulls(bookSearchList, FloorOfBook_X, FourthButtons_Y);
            bookSearchList.setText("Book List");

        }
        return bookSearchList;
    }

    public void setBookSearchList(JButton bookSearchList) {
        this.bookSearchList = bookSearchList;
    }

    public JButton getBookUpdateRemove() {
        if (bookUpdateRemove == null) {
            bookUpdateRemove = build_JbuttonForNulls(bookUpdateRemove, FloorOfBook_X, ThirdButtons_Y);
            bookUpdateRemove.setText("Update-Delete Book");
        }

        return bookUpdateRemove;
    }

    public void setBookUpdateRemove(JButton bookUpdateRemove) {
        this.bookUpdateRemove = bookUpdateRemove;
    }

    public JButton getStudentAdd() {
        if (studentAdd == null) {
            studentAdd = build_JbuttonForNulls(studentAdd, FloorOfStudent_X, FirstButtons_Y);
            studentAdd.setText("Add Student");
        }

        return studentAdd;
    }

    public void setStudentAdd(JButton studentAdd) {
        this.studentAdd = studentAdd;
    }

    public JButton getStudentUpdate() {
        if (studentUpdate == null) {
            studentUpdate = build_JbuttonForNulls(studentUpdate, FloorOfStudent_X, ThirdButtons_Y);
            studentUpdate.setText("Update-Delete Student");
        }
        return studentUpdate;
    }

    public void setStudentUpdate(JButton studentUpdate) {
        this.studentUpdate = studentUpdate;
    }

    public JButton getStudentState() {
        if (studentState == null) {
            studentState = build_JbuttonForNulls(studentState, FloorOfStudent_X, SecondButtons_Y);
            studentState.setText("Student Status");
        }

        return studentState;
    }

    public void setStudentState(JButton studentState) {
        this.studentState = studentState;
    }

    public JButton getRegisteredStudent() {
        if (registeredStudent == null) {
            registeredStudent = build_JbuttonForNulls(registeredStudent, FloorOfStudent_X, FourthButtons_Y);
            registeredStudent.setText("Registered Student List");

        }
        return registeredStudent;
    }

    public void setRegisteredStudent(JButton registeredStudent) {
        this.registeredStudent = registeredStudent;
    }

    public JButton getTimeControlExtraTime() {
        if (TimeControl_ExtraTime == null) {
            TimeControl_ExtraTime = build_JbuttonForNulls(TimeControl_ExtraTime, FloorOf_Time_Exit_X, FirstButtons_Y);
            TimeControl_ExtraTime.setText("Time Check & Extension");

        }
        return TimeControl_ExtraTime;
    }

    public void setTimeControl_ExtraTime(JButton TimeControl_ExtraTime) {
        this.TimeControl_ExtraTime = TimeControl_ExtraTime;
    }

    public JButton getFineDebtPayment() {
        if (FineDebtPayment == null) {
            FineDebtPayment = build_JbuttonForNulls(FineDebtPayment, FloorOf_Time_Exit_X, SecondButtons_Y);
            FineDebtPayment.setText("Fines & Payment");

        }
        return FineDebtPayment;
    }

    public void setFineDebtPayment(JButton FineDebtPayment) {
        this.FineDebtPayment = FineDebtPayment;
    }

    public JButton getAboutUs() {
        if (aboutUs == null) {
            aboutUs = build_JbuttonForNulls(aboutUs, FloorOf_Time_Exit_X, ThirdButtons_Y);
            aboutUs.setText("About & Password");

        }
        return aboutUs;
    }

    public void setAboutUs(JButton aboutUs) {
        this.aboutUs = aboutUs;
    }

    public JButton getExit() {
        if (exit == null) {
            exit = build_JbuttonForNulls(exit, FloorOf_Time_Exit_X, FourthButtons_Y);
            exit.setText("Sign Out & Login Page");
        }
        return exit;
    }

    public void setExit(JButton exit) {
        this.exit = exit;
    }

}
