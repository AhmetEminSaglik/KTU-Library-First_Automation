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

public class StudentUpdateGui extends JPanel {

    JFrame jf;

    JLabel lblno;
    JLabel lblNewNo;
    JLabel lblNewName;
    JLabel lblNewSurname;
    JLabel lblNewEmail;
    JLabel lblResult;
    JLabel lblPhoneNo;

    JTextField txtno;
    JTextField txtNewNo;
    JTextField txtNewName;
    JTextField txtNewSurname;
    JTextField txtNewEmail;
    JTextField txtResult;
    JTextField txtPhoneNo;

    JButton btnComeBack;
    JButton btnUpdate;
    JButton btnDelete;

    MainGui mg;
    ActionStudent action = new ActionStudent(this);

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final double screenSizeWidth = screenSize.getWidth();
    final double screenSizeHeight = screenSize.getHeight();

    final int leftSpace = (int) (screenSizeWidth / 27.32);
    final int lblWidth = (int) (screenSizeWidth / 2.732);
    final int lblHeight = (int) (screenSizeHeight / 38.4);

    int lblPushUnderCounter = 0;
    final int heightpaintY = (int) (screenSizeHeight / 6.144);

    final int lblTopSpace = heightpaintY + (int) (screenSizeHeight / 25.6);
    final int txtTopSpace = heightpaintY + lblHeight + (int) (screenSizeHeight / 19.2);
    final int txtLeftSpace = (int) (screenSizeWidth / 27.32);
    final int txtWidth = (int) (screenSizeWidth / 3.902857142857143);
    final int txtHeight = (int) (screenSizeHeight / 19.2);

    int txtPushUnderCounter = 0;

    final int lblPushSpaceRight = lblWidth + (int) (screenSizeWidth / 10.928);
    final int lblPushSpaceUnder = lblHeight + (int) (screenSizeHeight / 15.36);
    final int txtPushSpaceUnder = txtHeight + lblHeight + (int) (screenSizeHeight / 25.6);

    Font lblFont = new Font("monospaced", Font.BOLD, (int) screenSizeWidth / 80);
    Font txtFont = new Font("", Font.BOLD, (int) screenSizeWidth / 91);

    public StudentUpdateGui(MainGui mg) {
        this.mg = mg;
        this.jf = mg.getJf();
        getJf().setTitle("UPDATE & DELETE STUDENT PAGE");
        this.setBounds(getJf().getBounds());
        this.setBackground(new Color(75, 153, 23));
        this.setLayout(null);

        setJf(mg.getJf());
        mg.getJp().setVisible(false);
        getJf().add(this);

        this.add(getLblno());
        this.add(getLblNewNo());
        this.add(getLblNewName());
        this.add(getLblNewSurname());
        this.add(getLblNewEmail());
        this.add(getLblPhoneNo());
        this.add(getLblResult());
        this.add(getTxtno());
        this.add(getTxtNewNo());
        this.add(getTxtNewName());
        this.add(getTxtNewSurname());
        this.add(getTxtNewEmail());
        this.add(getTxtResult());
        this.add(getTxtPhoneNo());
        this.add(getBtnComeBack());
        this.add(getBtnUpdate());
        this.add(getBtnDelete());
        getBtnComeBack().addActionListener(action);
        getTxtno().addActionListener(action);
        getTxtResult().addActionListener(action);
        getTxtNewSurname().addActionListener(action);
        getTxtNewNo().addActionListener(action);
        getTxtNewName().addActionListener(action);
        getTxtNewEmail().addActionListener(action);
        getTxtPhoneNo().addActionListener(action);
        getBtnUpdate().addActionListener(action);
        getBtnDelete().addActionListener(action);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.orange);
        g.fillRect(0, heightpaintY, (int) (screenSizeWidth / 1.6070588235294119), (int) (screenSizeHeight / 76.8));

    }

    public JFrame getJf() {
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

    public JLabel getLblno() {
        if (lblno == null) {
            lblno = new JLabel("Student No");
            lblno.setBounds(leftSpace, (int) (screenSizeHeight / 51.2), lblWidth, lblHeight);
            lblno.setForeground(Color.white);
            lblno.setFont(lblFont);
        }
        return lblno;
    }

    public void setLblno(JLabel lblno) {
        this.lblno = lblno;
    }

    public JLabel getLblNewNo() {
        if (lblNewNo == null) {
            lblNewNo = new JLabel("New Student No");
            lblNewNo.setBounds(leftSpace, lblTopSpace + (lblHeight + txtHeight) * lblPushUnderCounter, lblWidth, lblHeight);
            lblNewNo.setForeground(Color.WHITE);
            lblNewNo.setFont(lblFont);
            lblPushUnderCounter++;
        }
        return lblNewNo;
    }

    public void setLblNewNo(JLabel lblNewNo) {
        this.lblNewNo = lblNewNo;
    }

    public JLabel getLblNewName() {

        if (lblNewName == null) {
            lblNewName = new JLabel("New Student Name");
            lblNewName.setBounds(leftSpace, lblTopSpace + (lblHeight + txtHeight + (int) (screenSizeHeight / 25.6)) * lblPushUnderCounter, lblWidth, lblHeight);
            lblNewName.setForeground(Color.WHITE);
            lblNewName.setFont(lblFont);

        }
        return lblNewName;
    }

    public void setLblNewName(JLabel lblNewName) {
        this.lblNewName = lblNewName;
    }

    public JLabel getLblNewSurname() {
        if (lblNewSurname == null) {
            lblNewSurname = new JLabel("New Student Surname");
            lblNewSurname.setBounds(leftSpace + txtWidth + (int) (screenSizeHeight / 12), lblTopSpace + (lblHeight + txtHeight + (int) (screenSizeHeight / 25.6)) * lblPushUnderCounter, lblWidth, lblHeight);
            lblNewSurname.setForeground(Color.WHITE);
            lblNewSurname.setFont(lblFont);
            lblPushUnderCounter++;
        }
        return lblNewSurname;
    }

    public void setLblNewSurname(JLabel lblNewSurname) {
        this.lblNewSurname = lblNewSurname;
    }

    public JLabel getLblNewEmail() {
        if (lblNewEmail == null) {
            lblNewEmail = new JLabel("New Student Email");
            lblNewEmail.setBounds(leftSpace, lblTopSpace + (lblHeight + txtHeight + (int) (screenSizeHeight / 25.6)) * lblPushUnderCounter, lblWidth, lblHeight);
            lblNewEmail.setForeground(Color.WHITE);
            lblNewEmail.setFont(lblFont);

        }

        return lblNewEmail;
    }

    public void setLblNewEmail(JLabel lblNewEmail) {
        this.lblNewEmail = lblNewEmail;
    }

    public JLabel getLblPhoneNo() {
        if (lblPhoneNo == null) {
            lblPhoneNo = new JLabel("New Student Phone No");
            lblPhoneNo.setBounds(leftSpace + txtWidth + (int) (screenSizeHeight / 12),
                    lblTopSpace + (lblHeight + txtHeight + (int) (screenSizeHeight / 25.6)) * lblPushUnderCounter,
                    lblWidth, lblHeight);

            lblPhoneNo.setForeground(Color.WHITE);
            lblPhoneNo.setFont(lblFont);
            lblPushUnderCounter++;
        }
        return lblPhoneNo;
    }

    public void setLblPhoneNo(JLabel lblPhoneNo) {
        this.lblPhoneNo = lblPhoneNo;
    }

    public JLabel getLblResult() {
        if (lblResult == null) {
            lblResult = new JLabel("RESULT");
            lblResult.setBounds(leftSpace/2 + txtWidth + (int) (screenSizeWidth / 17),
                    (int) (screenSizeHeight / 51.2), lblWidth, lblHeight);

            lblResult.setForeground(Color.WHITE);
            lblResult.setFont(lblFont);
        }
        return lblResult;
    }

    public void setLblResult(JLabel lblResult) {
        this.lblResult = lblResult;
    }

    public JTextField getTxtno() {
        if (txtno == null) {
            txtno = new JTextField();
            txtno.setBounds(leftSpace, (int) (screenSizeHeight / 15.36), txtWidth, txtHeight);
            txtno.setFont(txtFont);

        }

        return txtno;
    }

    public void setTxtno(JTextField txtno) {
        this.txtno = txtno;
    }

    public JTextField getTxtNewNo() {
        if (txtNewNo == null) {
            txtNewNo = new JTextField("");
            txtNewNo.setBounds(leftSpace, txtTopSpace + (txtPushSpaceUnder * txtPushUnderCounter), txtWidth, txtHeight);
            txtNewNo.setFont(txtFont);
            txtPushUnderCounter++;
        }
        return txtNewNo;
    }

    public void setTxtNewNo(JTextField txtNewNo) {
        this.txtNewNo = txtNewNo;
    }

    public JTextField getTxtNewName() {
        if (txtNewName == null) {
            txtNewName = new JTextField("");
            txtNewName.setBounds(leftSpace, txtTopSpace + (txtPushSpaceUnder * txtPushUnderCounter), txtWidth, txtHeight);
            txtNewName.setFont(txtFont);

        }
        return txtNewName;
    }

    public void setTxtNewName(JTextField txtNewName) {
        this.txtNewName = txtNewName;
    }

    public JTextField getTxtNewSurname() {

        if (txtNewSurname == null) {
            txtNewSurname = new JTextField("");
            txtNewSurname.setBounds(leftSpace + txtWidth + (int) (screenSizeWidth / 27.32), txtTopSpace + txtPushSpaceUnder * txtPushUnderCounter, txtWidth, txtHeight);
            txtNewSurname.setForeground(Color.BLACK);
            txtNewSurname.setFont(lblFont);
            txtPushUnderCounter++;
        }
        return txtNewSurname;
    }

    public void setTxtNewSurname(JTextField txtNewSurname) {
        this.txtNewSurname = txtNewSurname;
    }

    public JTextField getTxtNewEmail() {
        if (txtNewEmail == null) {
            txtNewEmail = new JTextField("");
            txtNewEmail.setBounds(leftSpace, txtTopSpace + txtPushSpaceUnder * txtPushUnderCounter, txtWidth, txtHeight);
            txtNewEmail.setFont(txtFont);

        }
        return txtNewEmail;
    }

    public void setTxtNewEmail(JTextField txtNewEmail) {
        this.txtNewEmail = txtNewEmail;
    }

    public JTextField getTxtPhoneNo() {
        if (txtPhoneNo == null) {
            txtPhoneNo = new JTextField("");
            txtPhoneNo.setBounds(leftSpace + txtWidth + (int) (screenSizeWidth / 27.32), txtTopSpace + txtPushSpaceUnder * txtPushUnderCounter, txtWidth, txtHeight);
            txtPhoneNo.setFont(txtFont);

        }
        return txtPhoneNo;
    }

    public void setTxtPhoneNo(JTextField txtPhoneNo) {
        this.txtPhoneNo = txtPhoneNo;
    }

    public JTextField getTxtResult() {
        if (txtResult == null) {
            txtResult = new JTextField("");

            txtResult.setBounds(txtWidth + leftSpace + (int) (screenSizeWidth / 27.32), (int) (screenSizeHeight / 15.36), txtWidth, txtHeight);
            txtResult.setFont(txtFont);
            txtResult.setEditable(false);
            txtResult.setBackground(new Color(206, 214, 224));
            ToolTipManager ttm = ToolTipManager.sharedInstance();
            ttm.setInitialDelay(100);
            ttm.setDismissDelay(1000);
            txtResult.setToolTipText("Not allowed to acces here");

        }
        return txtResult;
    }

    public void setTxtResult(JTextField txtResult) {
        this.txtResult = txtResult;
    }

    public JButton getBtnComeBack() {
        if (btnComeBack == null) {
            btnComeBack = new JButton("Back");
            btnComeBack.setFont(lblFont);
            btnComeBack.setBounds(leftSpace, (int) (screenSizeHeight / 1.7066666666666668), (int) (screenSizeWidth / 6.83), (int) (screenSizeHeight / 25.6));
            btnComeBack.setCursor(new Cursor(12));

        }
        return btnComeBack;
    }

    public void setBtnComeBack(JButton btnComeBack) {
        this.btnComeBack = btnComeBack;
    }

    public JButton getBtnUpdate() {
        if (btnUpdate == null) {
            btnUpdate = new JButton("Update Student");
            btnUpdate.setFont(lblFont);
            btnUpdate.setBounds(leftSpace + (int) (screenSizeWidth / 2.4836363636363634),
                    (int) (screenSizeHeight / 1.7066666666666668), (int) (screenSizeWidth / 6.83), (int) (screenSizeHeight / 25.6));
            btnUpdate.setCursor(new Cursor(12));
        }
        return btnUpdate;
    }

    public void setBtnUpdate(JButton btnUpdate) {
        this.btnUpdate = btnUpdate;
    }

    public JButton getBtnDelete() {
        if (btnDelete == null) {
            btnDelete = new JButton("Delete Student");
            btnDelete.setFont(lblFont);
            btnDelete.setBounds(leftSpace + (int) (screenSizeWidth / 4.967272727272727), (int) (screenSizeHeight / 1.7066666666666668),
                    (int) (screenSizeWidth / 6.83), (int) (screenSizeHeight / 25.6));

            btnDelete.setCursor(new Cursor(12));
        }
        return btnDelete;
    }

    public void setBtnDelete(JButton btnDelete) {
        this.btnDelete = btnDelete;
    }

    public int getLblPushUnderCounter() {
        return lblPushUnderCounter;
    }

    public void setLblPushUnderCounter(int lblPushUnderCounter) {
        this.lblPushUnderCounter = lblPushUnderCounter;
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
