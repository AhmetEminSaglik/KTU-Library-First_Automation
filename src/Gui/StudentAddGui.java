package Gui;

import Logic.ActionStudent;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ToolTipManager;

public class StudentAddGui {

    MainGui mg;
    JFrame jf;
    JPanel jp;
    JLabel lblNo;
    JLabel lblName;
    JLabel lblSurname;
    JLabel lblEmail;
    JLabel lblResult;
    JLabel lblPhoneNo;

    JTextField txtNo;
    JTextField txtName;
    JTextField txtSurname;
    JTextField txtEmail;
    JTextField txtResult;
    JButton btnComeBack;
    JTextField txtPhoneNo;
    JButton btnAdd;
    JButton btnClear;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final double screenSizeWidth = screenSize.getWidth();
    final double screenSizeHeight = screenSize.getHeight();

    final int lblWidth = (int) (screenSizeWidth / 4.553333333333334);
    final int lblheight = (int) (screenSizeHeight / 19.2);
    final int txtWidth = (int) (screenSizeWidth / 4.553333333333334);
    final int txtheight = (int) (screenSizeHeight / 25.6);
    final int topSpace = (int) (screenSizeHeight / 25.6);
    final int leftSpace = (int) (screenSizeWidth / 27.32);
    int lblPushCounter = 0;
    int txtPushCounter = 0;
    int lblpushUnder = lblheight + txtheight + (int) (screenSizeHeight / 25.6);

    Font lblFont = new Font("", Font.BOLD, (int) screenSizeWidth / 80);
    Font txtFont = new Font("", Font.BOLD, (int) screenSizeWidth / 91);
    ActionStudent action = new ActionStudent(this);

    public StudentAddGui(MainGui mg) {
        setJf(mg.getJf());
        getJf().setTitle("ÖĞRENCİ EKLE");
        setMg(mg);
        mg.getJp().setVisible(false);
        getJf().add(getJp());

        getJp().add(getLblNo());
        getJp().add(getLblResult());
        getJp().add(getLblName());
        getJp().add(getLblSurname());
        getJp().add(getLblEmail());
        getJp().add(getLblPhoneNo());
        getJp().add(getTxtNo());
        getJp().add(getTxtResult());
        getJp().add(getTxtName());
        getJp().add(getTxtSurname());
        getJp().add(getTxtEmail());
        getJp().add(getBtnComeBack());
        getJp().add(getBtnAdd());
        getJp().add(getBtnClear());
        getJp().add(getTxtPhoneNo());
        getBtnAdd().addActionListener(action);
        getBtnClear().addActionListener(action);
        getBtnComeBack().addActionListener(action);

        getTxtNo().addFocusListener(action);
        getTxtName().addFocusListener(action);
        getTxtSurname().addFocusListener(action);
        getTxtPhoneNo().addFocusListener(action);
        getTxtEmail().addFocusListener(action);

        getTxtNo().addActionListener(action);
        getTxtName().addActionListener(action);
        getTxtSurname().addActionListener(action);
        getTxtPhoneNo().addActionListener(action);
        getTxtEmail().addActionListener(action);
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

    public JPanel getJp() {
        if (jp == null) {
            jp = new JPanel();
            jp.setBounds(getJf().getBounds());
            jp.setBackground(new Color(246, 229, 141));
            jp.setLayout(null);
        }
        return jp;
    }

    public void setJp(JPanel jp) {
        this.jp = jp;
    }

    public JLabel getLblNo() {
        if (lblNo == null) {
            lblNo = new JLabel("Öğrenci No");
            lblNo.setBounds(leftSpace, topSpace + (lblpushUnder * lblPushCounter), lblWidth, lblheight);
            lblNo.setForeground(Color.BLACK);
            lblNo.setFont(lblFont);

        }

        return lblNo;
    }

    public void setLblNo(JLabel lblNo) {
        this.lblNo = lblNo;
    }

    public JLabel getLblResult() {
        if (lblResult == null) {
            lblResult = new JLabel("Sonuç ");
            lblResult.setBounds(leftSpace + txtWidth + (int) (screenSizeWidth / 17.075),
                    topSpace + (lblpushUnder * lblPushCounter), lblWidth, lblheight);

            lblResult.setForeground(Color.BLACK);
            lblResult.setFont(lblFont);
            lblPushCounter++;

        }
        return lblResult;
    }

    public void setLblResult(JLabel lblResult) {
        this.lblResult = lblResult;
    }

    public JLabel getLblName() {
        if (lblName == null) {
            lblName = new JLabel("Öğrenci Adı");
            lblName.setBounds(leftSpace, topSpace + (lblpushUnder * lblPushCounter), lblWidth, lblheight);
            lblName.setForeground(Color.BLACK);
            lblName.setFont(lblFont);

        }
        return lblName;
    }

    public void setLblName(JLabel lblName) {
        this.lblName = lblName;
    }

    public JLabel getLblSurname() {
        if (lblSurname == null) {

            lblSurname = new JLabel("Öğrenci Soyadı");
            lblSurname.setBounds(leftSpace + txtWidth + (int) (screenSizeWidth / 17.075),
                    topSpace + (lblpushUnder * lblPushCounter), lblWidth, lblheight);
            lblSurname.setForeground(Color.BLACK);
            lblSurname.setFont(lblFont);
            lblPushCounter++;

        }
        return lblSurname;
    }

    public void setLblSurname(JLabel lblSurname) {
        this.lblSurname = lblSurname;
    }

    public JLabel getLblEmail() {
        if (lblEmail == null) {

            lblEmail = new JLabel("Öğrenci Email");
            lblEmail.setBounds(leftSpace, topSpace + (lblpushUnder * lblPushCounter), lblWidth, lblheight);
            lblEmail.setForeground(Color.BLACK);
            lblEmail.setFont(lblFont);

        }
        return lblEmail;
    }

    public void setLblEmail(JLabel lblEmail) {
        this.lblEmail = lblEmail;
    }

    public JLabel getLblPhoneNo() {

        lblPhoneNo = new JLabel("Öğrenci Telefon No");

        lblPhoneNo.setBounds(leftSpace + txtWidth + (int) (screenSizeWidth / 17.075),
                topSpace + (lblpushUnder * lblPushCounter), lblWidth, lblheight);
        lblPhoneNo.setForeground(Color.BLACK);
        lblPhoneNo.setFont(lblFont);
        lblPushCounter++;
        return lblPhoneNo;
    }

    public void setLblPhoneNo(JLabel lblPhoneNo) {
        this.lblPhoneNo = lblPhoneNo;
    }

    public JTextField getTxtNo() {
        if (txtNo == null) {
            lblPushCounter = 0;
            txtNo = new JTextField();
            txtNo.setBounds(leftSpace, topSpace + lblheight + (lblpushUnder * lblPushCounter), txtWidth, txtheight);
            txtNo.setFont(txtFont);

        }
        return txtNo;
    }

    public void setTxtNo(JTextField txtNo) {
        this.txtNo = txtNo;
    }

    public JTextField getTxtResult() {
        if (txtResult == null) {
            txtResult = new JTextField("");
            txtResult.setBounds(leftSpace + txtWidth + (int) (screenSizeWidth / 17.075),
                    topSpace + lblheight + (lblpushUnder * lblPushCounter), txtWidth, txtheight);
            txtResult.setEditable(false);
            txtResult.setFocusable(false);
            txtResult.setFont(new Font("", Font.BOLD, (int) screenSizeWidth / 80));
            ToolTipManager ttm = ToolTipManager.sharedInstance();
            txtResult.setBackground(new Color(206, 214, 224));
            ttm.setInitialDelay(100);
            ttm.setDismissDelay(1000);
            txtResult.setToolTipText("Buraya Erişemezsiniz");
            lblPushCounter++;
        }
        return txtResult;
    }

    public void setTxtResult(JTextField txtResult) {
        this.txtResult = txtResult;
    }

    public JTextField getTxtName() {
        if (txtName == null) {
            txtName = new JTextField();
            txtName.setBounds(leftSpace, topSpace + lblheight + (lblpushUnder * lblPushCounter), txtWidth, txtheight);
            txtName.setFont(txtFont);
        }
        return txtName;
    }

    public void setTxtName(JTextField txtName) {
        this.txtName = txtName;
    }

    public JTextField getTxtSurname() {
        if (txtSurname == null) {
            txtSurname = new JTextField();
            txtSurname.setBounds(leftSpace + txtWidth + (int) (screenSizeWidth / 17.075),
                    topSpace + lblheight + (lblpushUnder * lblPushCounter), txtWidth, txtheight);
            txtSurname.setFont(txtFont);
            lblPushCounter++;
        }
        return txtSurname;
    }

    public void setTxtSurname(JTextField txtSurname) {
        this.txtSurname = txtSurname;
    }

    public JTextField getTxtEmail() {
        if (txtEmail == null) {
            txtEmail = new JTextField();
            txtEmail.setBounds(leftSpace, topSpace + lblheight + (lblpushUnder * lblPushCounter), txtWidth, txtheight);
            txtEmail.setFont(txtFont);
        }
        return txtEmail;
    }

    public void setTxtEmail(JTextField txtEmail) {
        this.txtEmail = txtEmail;
    }

    public JButton getBtnComeBack() {
        if (btnComeBack == null) {
            btnComeBack = new JButton("Geri Dön");
            btnComeBack.setFont(lblFont);
            btnComeBack.setBounds(leftSpace, (int) (screenSizeHeight / 1.7066666666666668),
                    (int) (screenSizeWidth / 9.106666666666667), (int) (screenSizeHeight / 25.6));
            btnComeBack.setCursor(new Cursor(12));

        }
        return btnComeBack;
    }

    public void setBtnComeBack(JButton btnComeBack) {
        this.btnComeBack = btnComeBack;
    }

    public JButton getBtnAdd() {
        if (btnAdd == null) {
            btnAdd = new JButton("Ekle ");
            btnAdd.setFont(lblFont);
            btnAdd.setBounds(leftSpace,
                    (int) (screenSizeHeight / 2.1942857142857144),
                    (int) (screenSizeWidth / 10.928),
                    (int) (screenSizeHeight / 25.6));
            btnAdd.setCursor(new Cursor(12));
        }
        return btnAdd;
    }

    public void setBtnAdd(JButton btnAdd) {
        this.btnAdd = btnAdd;
    }

    public JButton getBtnClear() {
        if (btnClear == null) {
            btnClear = new JButton(" Temizle ");
            btnClear.setFont(lblFont);
            btnClear.setBounds(leftSpace + (int) (screenSizeWidth / 7.805714285714286),
                    (int) (screenSizeHeight / 2.1942857142857144),
                    (int) (screenSizeWidth / 10.928),
                    (int) (screenSizeHeight / 25.6));

            btnClear.setCursor(new Cursor(12));
        }
        return btnClear;
    }

    public void setBtnClear(JButton btnClear) {
        this.btnClear = btnClear;
    }

    public Font getTxtFont() {
        return txtFont;
    }

    public void setTxtFont(Font txtFont) {
        this.txtFont = txtFont;
    }

    public JTextField getTxtPhoneNo() {
        if (txtPhoneNo == null) {
            txtPhoneNo = new JTextField();
            txtPhoneNo.setBounds(leftSpace + txtWidth + (int) (screenSizeWidth / 17.075),
                    topSpace + lblheight + (lblpushUnder * lblPushCounter), txtWidth, txtheight);
            txtPhoneNo.setFont(txtFont);
            lblPushCounter++;
        }
        return txtPhoneNo;
    }

    public void setTxtPhoneNo(JTextField txtPhoneNo) {
        this.txtPhoneNo = txtPhoneNo;
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
