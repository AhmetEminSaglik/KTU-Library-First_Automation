package Gui;

import Logic.ActionsBook;
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

public class BookReturnGui {

    MainGui mg;
    JFrame jf;
    JPanel jp;
    JLabel lblBarcodeNo;
    JLabel lblStudentNo;
    JLabel lblBookName;
    JLabel lblAuthorName;
    JLabel lblStudentName;
    JLabel lblResult;

    JTextField txtBarcodeNo;
    JTextField txtStudentNo;
    JTextField txtBookName;
    JTextField txtAuthorName;
    JTextField txtStudentName;
    JTextField txtResult;
    JButton btnComeBack;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final double screenSizeWidth = screenSize.getWidth();
    final double screenSizeHeight = screenSize.getHeight();

    final int lblTopSpace = (int) (screenSizeHeight / 76.8);
    final int lblLeftSpace = (int) (screenSizeWidth / 27.32);
    final int lblWidth = (int) (screenSizeWidth / 5.464);
    final int lblHeight = (int) (screenSizeHeight / 12.8);
    int lblPushRightCounter = 0;
    int lblPushUnderCounter = 0;

    final int txtTopSpace = lblTopSpace + lblHeight - (int) (screenSizeHeight / 76.8);
    final int txtLeftSpace = (int) (screenSizeWidth / 27.32);
    final int txtWidth = (int) (screenSizeWidth / 4.553333333333334);
    final int txtHeight = (int) (screenSizeHeight / 19.2);
    int txtPushRightCounter = 0;
    int txtPushUnderCounter = 0;

    final int lblPushSpaceRight = lblLeftSpace + lblWidth + (int) (screenSizeWidth / 13.66);
    final int lblPushSpaceUnder = lblHeight + (int) (screenSizeHeight / 15.36);
    final int txtPushSpaceUnder = txtHeight + lblHeight + (int) (screenSizeHeight / 76.8);
    final int txtPushSpaceRight = txtLeftSpace + txtWidth + (int) (screenSizeWidth / 27.32);

    ActionsBook action = new ActionsBook(this);
    Font lblFont = new Font("monospaced", Font.BOLD, (int) screenSizeWidth / 80);
    Font txtFont = new Font("", Font.BOLD, (int) screenSizeWidth / 91);

    public BookReturnGui(MainGui mg) {

        setMg(mg);
        setJf(mg.getJf());
        getMg().getJf().setTitle("BOOK RETURN PAGE");
        getJf().add(getJp());
        getJp().add(getLblBarcodeNo());
        getJp().add(getLblStudentNo());
        getJp().add(getLblBookName());
        getJp().add(getLblAuthorName());
        getJp().add(getLblStudentName());
        getJp().add(getLblResult());
        getJp().add(getTxtBarcodeNo());
        getJp().add(getTxtStudentNo());
        getJp().add(getTxtBookName());
        getJp().add(getTxtAuthorName());
        getJp().add(getTxtStudentName());
        getJp().add(getTxtResult());
        getJp().add(getBtnComeBack());

        btnComeBack.addActionListener(action);
        getTxtBarcodeNo().addActionListener(action);
        getTxtStudentNo().addActionListener(action);
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
            jp.setBackground(new Color(50, 0, 50));
            jp.setBounds(getJf().getBounds());
            jp.setLayout(null);
        }
        return jp;
    }

    public void setJp(JPanel jp) {
        this.jp = jp;
    }

    public JLabel getLblBarcodeNo() {
        if (lblBarcodeNo == null) {
            lblBarcodeNo = new JLabel("Book Barcode No");
            lblBarcodeNo.setBounds(lblLeftSpace + (lblPushSpaceRight * lblPushRightCounter),
                    lblTopSpace + lblPushUnderCounter * lblPushSpaceUnder, lblWidth, lblHeight);
            lblBarcodeNo.setForeground(Color.white);
            lblBarcodeNo.setFont(lblFont);
            lblPushRightCounter++;

        }
        return lblBarcodeNo;
    }

    public void setLblBarcodeNo(JLabel lblBarcodeNo) {
        this.lblBarcodeNo = lblBarcodeNo;
    }

    public JLabel getLblStudentNo() {
        if (lblStudentNo == null) {
            lblStudentNo = new JLabel("Student No");
            lblStudentNo.setBounds(lblLeftSpace + (lblPushSpaceRight * lblPushRightCounter),
                    lblTopSpace + lblPushSpaceUnder * lblPushUnderCounter, lblWidth, lblHeight);
            lblStudentNo.setForeground(Color.white);
            lblStudentNo.setFont(lblFont);
            lblPushUnderCounter++;
            if (lblPushRightCounter == 1) {
                lblPushRightCounter = 0;
            } else {
                lblPushRightCounter++;
            }

        }
        return lblStudentNo;
    }

    public void setLblStudentNo(JLabel lblStudentNo) {
        this.lblStudentNo = lblStudentNo;
    }

    public JLabel getLblBookName() {
        if (lblBookName == null) {

            lblBookName = new JLabel("Book Name");
            lblBookName.setBounds(lblLeftSpace + (lblPushSpaceRight * lblPushRightCounter),
                    lblTopSpace + lblPushSpaceUnder * lblPushUnderCounter, lblWidth, lblHeight);
            lblBookName.setForeground(Color.white);
            lblBookName.setFont(lblFont);

            lblPushUnderCounter++;

        }
        return lblBookName;
    }

    public void setLblBookName(JLabel lblBookName) {
        this.lblBookName = lblBookName;
    }

    public JLabel getLblAuthorName() {
        if (lblAuthorName == null) {

            lblAuthorName = new JLabel("Book's Author Name");
            lblAuthorName.setBounds(lblLeftSpace + (lblPushSpaceRight * lblPushRightCounter),
                    lblTopSpace + lblPushSpaceUnder * lblPushUnderCounter, lblWidth, lblHeight);
            lblAuthorName.setForeground(Color.white);
            lblAuthorName.setFont(lblFont);

            if (lblPushRightCounter == 1) {
                lblPushRightCounter = 0;
            } else {
                lblPushRightCounter++;
            }

        }
        return lblAuthorName;
    }

    public void setLblAuthorName(JLabel lblAuthorName) {
        this.lblAuthorName = lblAuthorName;
    }

    public JLabel getLblStudentName() {
        if (lblStudentName == null) {

            lblStudentName = new JLabel("Student Name");
            lblStudentName.setBounds(lblLeftSpace + (lblPushSpaceRight * lblPushRightCounter),
                    lblTopSpace + lblPushSpaceUnder * lblPushUnderCounter, lblWidth, lblHeight);
            lblStudentName.setForeground(Color.white);
            lblStudentName.setFont(lblFont);
            lblPushUnderCounter++;

            if (lblPushRightCounter == 1) {
                lblPushRightCounter = 0;
            } else {
                lblPushRightCounter++;
            }
        }
        return lblStudentName;
    }

    public void setLblStudentName(JLabel lblStudentName) {

        this.lblStudentName = lblStudentName;
    }

    public JLabel getLblResult() {
        if (lblResult == null) {

            lblResult = new JLabel("RESULT");
            lblResult.setBounds(lblLeftSpace + (lblPushSpaceRight * lblPushRightCounter),
                    lblTopSpace + lblPushSpaceUnder * lblPushUnderCounter, lblWidth, lblHeight);
            lblResult.setForeground(Color.white);
            lblResult.setFont(lblFont);

            lblPushUnderCounter++;

        }
        return lblResult;
    }

    public void setLblResult(JLabel lblResult) {
        this.lblResult = lblResult;
    }

    public JTextField getTxtBarcodeNo() {

        if (txtBarcodeNo == null) {
            txtBarcodeNo = new JTextField();
            txtBarcodeNo.setBounds(txtLeftSpace + (txtPushSpaceRight * txtPushRightCounter),
                    txtTopSpace + (txtPushUnderCounter * txtPushSpaceUnder), txtWidth, txtHeight);
            txtBarcodeNo.setForeground(Color.BLACK);
            txtBarcodeNo.setFont(txtFont);

            if (txtPushRightCounter == 1) {
                txtPushRightCounter = 0;
            } else {
                txtPushRightCounter++;
            }

        }
        return txtBarcodeNo;
    }

    public void setTxtBarcodeNo(JTextField txtBarcodeNo) {
        this.txtBarcodeNo = txtBarcodeNo;
    }

    public JTextField getTxtStudentNo() {

        if (txtStudentNo == null) {

            txtStudentNo = new JTextField();
            txtStudentNo.setBounds(txtLeftSpace + (txtPushSpaceRight * txtPushRightCounter),
                    txtTopSpace + txtPushUnderCounter * txtPushSpaceUnder, txtWidth, txtHeight);
            txtStudentNo.setForeground(Color.BLACK);
            txtStudentNo.setFont(txtFont);
            txtPushUnderCounter++;
            if (txtPushRightCounter == 1) {
                txtPushRightCounter = 0;
            } else {
                txtPushRightCounter++;
            }

        }
        return txtStudentNo;
    }

    public void setTxtStudentNo(JTextField txtStudentNo) {
        this.txtStudentNo = txtStudentNo;
    }

    public JTextField getTxtBookName() {
        if (txtBookName == null) {

            txtBookName = new JTextField();
            txtBookName.setBounds(txtLeftSpace + (txtPushSpaceRight * txtPushRightCounter),
                    txtTopSpace + txtPushUnderCounter * txtPushSpaceUnder, txtWidth * 5 / 2, txtHeight);
            txtBookName.setForeground(Color.BLACK);
            txtBookName.setFont(txtFont);
            txtBookName.setEditable(false);
            txtBookName.setBackground(new Color(206, 214, 224));
            ToolTipManager ttm = ToolTipManager.sharedInstance();
            ttm.setInitialDelay(100);
            ttm.setDismissDelay(1000);
            txtBookName.setToolTipText("Buraya Erişemezsiniz");
            txtPushUnderCounter++;

        }
        return txtBookName;
    }

    public void setTxtBookName(JTextField txtBookName) {
        this.txtBookName = txtBookName;
    }

    public JTextField getTxtAuthorName() {
        if (txtAuthorName == null) {

            txtAuthorName = new JTextField();
            txtAuthorName.setBounds(txtLeftSpace + (txtPushSpaceRight * txtPushRightCounter),
                    txtTopSpace + txtPushUnderCounter * txtPushSpaceUnder, txtWidth, txtHeight);
            txtAuthorName.setForeground(Color.BLACK);
            txtAuthorName.setFont(txtFont);
            txtAuthorName.setEditable(false);
            txtAuthorName.setBackground(new Color(206, 214, 224));
            ToolTipManager ttm = ToolTipManager.sharedInstance();
            ttm.setInitialDelay(100);
            ttm.setDismissDelay(1000);
            txtAuthorName.setToolTipText("Buraya Erişemezsiniz");
            if (txtPushRightCounter == 1) {
                txtPushRightCounter = 0;
            } else {
                txtPushRightCounter++;
            }

        }
        return txtAuthorName;
    }

    public void setTxtAuthorName(JTextField txtAuthorName) {
        this.txtAuthorName = txtAuthorName;
    }

    public JTextField getTxtStudentName() {
        if (txtStudentName == null) {

            txtStudentName = new JTextField();
            txtStudentName.setBounds(txtLeftSpace + (txtPushSpaceRight * txtPushRightCounter),
                    txtTopSpace + txtPushUnderCounter * txtPushSpaceUnder, txtWidth, txtHeight);
            txtStudentName.setForeground(Color.BLACK);
            txtStudentName.setFont(txtFont);
            txtStudentName.setEditable(false);
            ToolTipManager ttm = ToolTipManager.sharedInstance();
            ttm.setInitialDelay(100);
            ttm.setDismissDelay(1000);
            txtStudentName.setBackground(new Color(206, 214, 224));
            txtStudentName.setToolTipText("Buraya Erişemezsiniz");
            txtPushUnderCounter++;
            if (txtPushRightCounter == 1) {
                txtPushRightCounter = 0;
            } else {
                txtPushRightCounter++;
            }

        }
        return txtStudentName;
    }

    public void setTxtStudentName(JTextField txtStudentName) {
        this.txtStudentName = txtStudentName;
    }

    public JTextField getTxtResult() {
        if (txtResult == null) {

            txtResult = new JTextField();
            txtResult.setBounds(txtLeftSpace + (txtPushSpaceRight * txtPushRightCounter),
                    txtTopSpace + txtPushUnderCounter * txtPushSpaceUnder, txtWidth * 5 / 2, txtHeight);

            txtResult.setForeground(Color.BLACK);
            txtResult.setFont(txtFont);
            txtResult.setEditable(false);
            ToolTipManager ttm = ToolTipManager.sharedInstance();
            ttm.setInitialDelay(100);
            ttm.setDismissDelay(1000);
            txtResult.setToolTipText("Buraya Erişemezsiniz");
            txtResult.setBackground(new Color(206, 214, 224));
            txtPushUnderCounter++;

            if (txtPushRightCounter == 1) {
                txtPushRightCounter = 0;
            } else {
                txtPushRightCounter++;
            }

        }
        return txtResult;
    }

    public void setTxtResult(JTextField txtResult) {
        this.txtResult = txtResult;
    }

    public JButton getBtnComeBack() {
        if (btnComeBack == null) {
            btnComeBack = new JButton("Back");
            btnComeBack.setBounds((int) (screenSizeWidth / 27.32), (int) (screenSizeHeight / 1.7066666666666668),
                    (int) (screenSizeWidth / 9.106666666666667), (int) (screenSizeHeight / 15.36));

            btnComeBack.setBackground(Color.white);
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
