package Gui;

import Logic.ActionStudent;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ToolTipManager;

public class StudentStateGui extends JPanel {

    JFrame jf;
    JLabel lblStudentNo;
    JLabel lblDept;
    JLabel lblLendingDayNumber1;
    JLabel lblLendingDayNumber2;
    JLabel lblLendingDayNumber3;

    JTextField txtStudentNo;
    JTextField txtDept;
    JTextField txtBookBarcodeNo1;
    JTextField txtBookBarcodeNo2;
    JTextField txtBookBarcodeNo3;
    JTextField txtBookName1;
    JTextField txtBookName2;
    JTextField txtBookName3;
    JButton btnComeBack;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final double screenSizeWidth = screenSize.getWidth();
    final double screenSizeHeight = screenSize.getHeight();

    final int lblWidth = (int) (screenSizeWidth / 9.106666666666667);
    final int lblHeight = (int) (screenSizeHeight / 25.6);

    final int txtBarcodeWidth = (int) (screenSizeWidth / 5.464);
    final int txtBookNameWidth = (int) (screenSizeWidth / 1.9514285714285715);
    final int txtHeight = (int) (screenSizeHeight / 19.2);

    final int txtPushUnder = txtHeight + (int) (screenSizeHeight / 76.8);

    final int lblPushUnder = txtHeight + (int) (screenSizeHeight / 76.8);
    final int LeftSpace = (int) (screenSizeWidth / 27.32);
    int pushUnderlblCounter = 0;
    int pushUndertxtCounter = 0;
    final int topSpace = (int) (screenSizeHeight / 5.907692307692308);
    final int topSpaceBook = topSpace + txtHeight + (int) (screenSizeHeight / 153.6);
    Font lblFont = new Font("monospaced", Font.BOLD, (int) screenSizeWidth / 80);
    Font txtFont = new Font("", Font.BOLD, (int) screenSizeWidth / 80);
    MainGui mg;
    ActionStudent action = new ActionStudent(this);
    ToolTipManager ttm = ToolTipManager.sharedInstance();

    public StudentStateGui(MainGui mg) {

        setMg(mg);
        getMg().getJf().setTitle("STUDENT STATUS PAGE");
        this.setBounds(getJf().getBounds());
        this.setBackground(new Color(44, 58, 71));
        this.setLayout(null);

        setJf(mg.getJf());
        mg.getJp().setVisible(false);
        getJf().add(this);
        this.add(getLblStudentNo());
        this.add(getLblDept());
        this.add(getLblLendingDayNumber1());
        this.add(getLblLendingDayNumber2());
        this.add(getLblLendingDayNumber3());
        this.add(getTxtStudentNo());
        this.add(getTxtDept());
        this.add(getTxtBookBarcodeNo1());
        this.add(getTxtBookBarcodeNo2());
        this.add(getTxtBookBarcodeNo3());
        this.add(getTxtBookName1());
        this.add(getTxtBookName2());
        this.add(getTxtBookName3());
        this.add(getBtnComeBack());
        getBtnComeBack().addActionListener(action);
        getTxtStudentNo().addActionListener(action);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.red);
        g.fillRect(0, topSpace - (int) (screenSizeHeight / 25.6),
                (int) (screenSizeHeight / 0.8533333333333334), (int) (screenSizeHeight / 76.8));

    }

    public JFrame getJf() {
        if (jf == null) {
            jf = new JFrame();
        }
        double jframeWidth = screenSize.getWidth() / 1.6070588235294119;
        double jframeHeight = screenSize.getHeight() / 1.3963636363636365;
        int jframeX = (int) ((screenSize.getWidth() - jframeWidth) / 2);
        int jframeY = (int) ((screenSize.getHeight() - jframeHeight) / 2);
        jf.setBounds(jframeX, jframeY, (int) jframeWidth, (int) jframeHeight);
        return jf;
    }

    public void setJf(JFrame jf) {
        this.jf = jf;
    }

    public JLabel getLblStudentNo() {
        if (lblStudentNo == null) {
            lblStudentNo = new JLabel("Student No");
            lblStudentNo.setBounds(LeftSpace, (int) (screenSizeHeight / 76.8), lblWidth, lblHeight);
            lblStudentNo.setForeground(Color.red);
            lblStudentNo.setFont(lblFont);
        }
        return lblStudentNo;
    }

    public void setLblStudentNo(JLabel lblStudentNo) {
        this.lblStudentNo = lblStudentNo;
    }

    public JLabel getLblDept() {
        if (lblDept == null) {
            lblDept = new JLabel("Debt");
            lblDept.setBounds(LeftSpace + (int) (screenSizeWidth / 3.415), (int) (screenSizeHeight / 76.8), lblWidth, lblHeight);
            lblDept.setForeground(Color.red);
            lblDept.setFont(lblFont);
        }
        return lblDept;
    }

    public void setLblDept(JLabel lblDept) {
        this.lblDept = lblDept;
    }

    public JLabel getLblLendingDayNumber1() {
        if (lblLendingDayNumber1 == null) {
            lblLendingDayNumber1 = new JLabel("---");
            lblLendingDayNumber1.setBounds(LeftSpace + txtBarcodeWidth + (int) (screenSizeWidth / 34.15),
                    topSpace + (txtHeight + (int) (screenSizeHeight / 10.971428571428572)) * pushUnderlblCounter, lblWidth, lblHeight);

            lblLendingDayNumber1.setForeground(Color.MAGENTA);
            lblLendingDayNumber1.setFont(lblFont);
            pushUnderlblCounter++;
        }
        return lblLendingDayNumber1;
    }

    public void setLblLendingDayNumber1(JLabel lblLendingDayNumber1) {
        this.lblLendingDayNumber1 = lblLendingDayNumber1;
    }

    public JLabel getLblLendingDayNumber2() {
        if (lblLendingDayNumber2 == null) {
            lblLendingDayNumber2 = new JLabel("---");
            lblLendingDayNumber2.setBounds(LeftSpace + txtBarcodeWidth + (int) (screenSizeWidth / 34.15),
                    topSpace + (txtHeight + (int) (screenSizeHeight / 10.971428571428572)) * pushUnderlblCounter, lblWidth, lblHeight);
            lblLendingDayNumber2.setForeground(Color.MAGENTA);
            lblLendingDayNumber2.setFont(lblFont);
            pushUnderlblCounter++;
        }
        return lblLendingDayNumber2;
    }

    public void setLblLendingDayNumber2(JLabel lblLendingDayNumber2) {
        this.lblLendingDayNumber2 = lblLendingDayNumber2;
    }

    public JLabel getLblLendingDayNumber3() {
        if (lblLendingDayNumber3 == null) {
            lblLendingDayNumber3 = new JLabel("---");
            lblLendingDayNumber3.setBounds(LeftSpace + txtBarcodeWidth + (int) (screenSizeWidth / 34.15),
                    topSpace + (txtHeight + (int) (screenSizeHeight / 10.971428571428572)) * pushUnderlblCounter, lblWidth, lblHeight);
            lblLendingDayNumber3.setForeground(Color.MAGENTA);
            lblLendingDayNumber3.setFont(lblFont);
            pushUnderlblCounter++;
        }
        return lblLendingDayNumber3;
    }

    public void setLblLendingDayNumber3(JLabel lblLendingDayNumber3) {
        this.lblLendingDayNumber3 = lblLendingDayNumber3;
    }

    public JTextField getTxtStudentNo() {
        if (txtStudentNo == null) {
            txtStudentNo = new JTextField("");
            txtStudentNo.setBounds(LeftSpace, lblHeight + (int) (screenSizeHeight / 76.8), txtBarcodeWidth, txtHeight);
            txtStudentNo.setFont(txtFont);
        }
        return txtStudentNo;
    }

    public void setTxtStudentNo(JTextField txtStudentNo) {
        this.txtStudentNo = txtStudentNo;
    }

    public JTextField getTxtDept() {
        if (txtDept == null) {
            txtDept = new JTextField("");
            txtDept.setBounds(LeftSpace + (int) (screenSizeWidth / 3.415), lblHeight + (int) (screenSizeHeight / 76.8), txtBarcodeWidth, txtHeight);
            txtDept.setFont(txtFont);
            txtDept.setEditable(false);
            txtDept.setFocusable(false);
            txtDept.setBackground(new Color(206, 214, 224));
            ttm.setInitialDelay(100);
            ttm.setDismissDelay(1000);
            txtDept.setToolTipText("Not allowed to acces here");
        }
        return txtDept;
    }

    public void setTxtDept(JTextField txtDept) {
        this.txtDept = txtDept;
    }

    public JTextField getTxtBookBarcodeNo1() {
        if (txtBookBarcodeNo1 == null) {
            txtBookBarcodeNo1 = new JTextField("NO BOOK");
            txtBookBarcodeNo1.setBounds(LeftSpace, topSpace + (txtHeight + (int) (screenSizeHeight / 10.971428571428572)) * pushUndertxtCounter, txtBarcodeWidth, txtHeight);
            txtBookBarcodeNo1.setFont(txtFont);
            txtBookBarcodeNo1.setEditable(false);
            txtBookBarcodeNo1.setFocusable(false);
            txtBookBarcodeNo1.setBackground(new Color(206, 214, 224));
            ttm.setInitialDelay(100);
            ttm.setDismissDelay(1000);
            txtBookBarcodeNo1.setToolTipText("Not allowed to acces here");
            pushUndertxtCounter++;

        }
        return txtBookBarcodeNo1;
    }

    public void setTxtBookBarcodeNo1(JTextField txtBookBarcodeNo1) {
        this.txtBookBarcodeNo1 = txtBookBarcodeNo1;
    }

    public JTextField getTxtBookBarcodeNo2() {
        if (txtBookBarcodeNo2 == null) {
            txtBookBarcodeNo2 = new JTextField("NO BOOK");
            txtBookBarcodeNo2.setBounds(LeftSpace, topSpace + (txtHeight + (int) (screenSizeHeight / 10.971428571428572)) * pushUndertxtCounter, txtBarcodeWidth, txtHeight);
            txtBookBarcodeNo2.setFont(txtFont);
            txtBookBarcodeNo2.setEditable(false);
            txtBookBarcodeNo2.setFocusable(false);
            txtBookBarcodeNo2.setBackground(new Color(206, 214, 224));
            ttm.setInitialDelay(100);
            ttm.setDismissDelay(1000);
            txtBookBarcodeNo2.setToolTipText("Not allowed to acces here");
            pushUndertxtCounter++;
        }
        return txtBookBarcodeNo2;
    }

    public void setTxtBookBarcodeNo2(JTextField txtBookBarcodeNo2) {
        this.txtBookBarcodeNo2 = txtBookBarcodeNo2;
    }

    public JTextField getTxtBookBarcodeNo3() {
        if (txtBookBarcodeNo3 == null) {
            txtBookBarcodeNo3 = new JTextField("NO BOOK");
            txtBookBarcodeNo3.setBounds(LeftSpace, topSpace + (txtHeight + (int) (screenSizeHeight / 10.971428571428572)) * pushUndertxtCounter, txtBarcodeWidth, txtHeight);
            txtBookBarcodeNo3.setFont(txtFont);
            txtBookBarcodeNo3.setEditable(false);
            txtBookBarcodeNo3.setFocusable(false);
            txtBookBarcodeNo3.setBackground(new Color(206, 214, 224));
            ttm.setInitialDelay(100);
            ttm.setDismissDelay(1000);
            txtBookBarcodeNo3.setToolTipText("Not allowed to acces here");
            pushUndertxtCounter++;
        }
        return txtBookBarcodeNo3;
    }

    public void setTxtBookBarcodeNo3(JTextField txtBookBarcodeNo3) {
        this.txtBookBarcodeNo3 = txtBookBarcodeNo3;
    }

    public JTextField getTxtBookName1() {
        if (txtBookName1 == null) {
            pushUndertxtCounter = 0;
            txtBookName1 = new JTextField("NO BOOK"
                    + "");
            txtBookName1.setBounds(LeftSpace, topSpaceBook + (txtHeight + (int) (screenSizeHeight / 10.971428571428572)) * pushUndertxtCounter, txtBookNameWidth, txtHeight);
            txtBookName1.setFont(txtFont);
            txtBookName1.setEditable(false);
            txtBookName1.setFocusable(false);
            txtBookName1.setBackground(new Color(206, 214, 224));
            ttm.setInitialDelay(100);
            ttm.setDismissDelay(1000);
            txtBookName1.setToolTipText("Not allowed to acces here");
            pushUndertxtCounter++;
        }

        return txtBookName1;
    }

    public void setTxtBookName1(JTextField txtBookName1) {
        this.txtBookName1 = txtBookName1;
    }

    public JTextField getTxtBookName2() {
        if (txtBookName2 == null) {
            txtBookName2 = new JTextField("NO BOOK");
            txtBookName2.setBounds(LeftSpace, topSpaceBook + (txtHeight + (int) (screenSizeHeight / 10.971428571428572)) * pushUndertxtCounter, txtBookNameWidth, txtHeight);
            txtBookName2.setFont(txtFont);
            txtBookName2.setEditable(false);
            txtBookName2.setFocusable(false);
            txtBookName2.setBackground(new Color(206, 214, 224));
            ttm.setInitialDelay(100);
            ttm.setDismissDelay(1000);
            txtBookName2.setToolTipText("Not allowed to acces here");
            pushUndertxtCounter++;
        }
        return txtBookName2;
    }

    public void setTxtBookName2(JTextField txtBookName2) {
        this.txtBookName2 = txtBookName2;
    }

    public JTextField getTxtBookName3() {
        if (txtBookName3 == null) {
            txtBookName3 = new JTextField("NO BOOK");
            txtBookName3.setBounds(LeftSpace, topSpaceBook + (txtHeight + (int) (screenSizeHeight / 10.971428571428572)) * pushUndertxtCounter, txtBookNameWidth, txtHeight);
            txtBookName3.setFont(txtFont);
            txtBookName3.setEditable(false);
            txtBookName3.setFocusable(false);
            txtBookName3.setBackground(new Color(206, 214, 224));
            ttm.setInitialDelay(100);
            ttm.setDismissDelay(1000);
            txtBookName3.setToolTipText("Not allowed to acces here");
            pushUndertxtCounter++;
        }
        return txtBookName3;
    }

    public void setTxtBookName3(JTextField txtBookName3) {
        this.txtBookName3 = txtBookName3;
    }

    public JButton getBtnComeBack() {
        if (btnComeBack == null) {
            btnComeBack = new JButton("Back");
            btnComeBack.setBounds(LeftSpace, (int) (screenSizeHeight / 1.7066666666666668),
                    (int) (screenSizeWidth / 9.106666666666667), (int) (screenSizeHeight / 21.942857142857143));
            btnComeBack.setFont(txtFont);
            btnComeBack.setCursor(new Cursor(12));

        }
        return btnComeBack;
    }

    public void setBtnComeBack(JButton btnComeBack) {
        this.btnComeBack = btnComeBack;
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

}
