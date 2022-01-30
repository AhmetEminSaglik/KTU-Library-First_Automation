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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

public class RegisteredStudentGui {

    JFrame jf;
    JPanel jp;
    JLabel lblName;
    JLabel lblSurname;
    JLabel lblNo;
    JTable table;
    JScrollPane sp;
    JTextField txtName;
    JTextField txtSurname;
    JTextField txtNo;
    JButton btnComeBack;
    public String DataOfTable[][] = {};
    public String HeadersOfTable[] = {"", "Öğrenci No", "Öğrenci Adı", "Öğrenci Soyadı", "Email", "Telefon"};

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final double screenSizeWidth = screenSize.getWidth();
    final double screenSizeHeight = screenSize.getHeight();

    final int lblTopSpace = (int) (screenSizeHeight / 76.8);
    final int leftSpace = (int) (screenSizeWidth / 45.53333333333333);
    final int txtWidth = (int) (screenSizeWidth / 6.209090909090909);
    final int txtHeight = (int) (screenSizeHeight / 19.2);
    final int lblWidth = txtWidth;
    final int lblHeight = (int) (screenSizeHeight / 30.72);
    final int pushRightSpace = (int) (screenSizeWidth / 27.32);
    final int txtTopSpace = lblTopSpace + lblHeight + (int) (screenSizeHeight / 76.8);
    final int topSpace = (int) (screenSizeHeight / 51.2);
    int pushRightCounter = 0;
    MainGui mg;
    public ActionStudent action = new ActionStudent(this);

    Font font_lbl = new Font("", Font.BOLD, (int) screenSizeWidth / 80);
    Font font_txt = new Font("", Font.BOLD, (int) (screenSizeWidth / 105.07));
    Font fontTxtPlaceHolder = new Font("", Font.ITALIC, (int) screenSizeWidth / 91);

    public RegisteredStudentGui(MainGui mg) {

        action.SearchRegisteredStudent(0);
        setMg(mg);
        setJf(mg.getJf());
        getJf().setTitle("KAYITLI ÖĞRENCİ SAYFASI");
        getJp().add(getLblName());
        getJp().add(getLblSurname());
        getJp().add(getLblNo());
        getJp().add(getTxtName());
        getJp().add(getTxtSurname());
        getJp().add(getTxtNo());
        getJp().add(getSp());
        getJp().add(getBtnComeBack());
        getTxtNo().addActionListener(action);
        getTxtName().addActionListener(action);
        getTxtSurname().addActionListener(action);
        getBtnComeBack().addActionListener(action);

        getTxtNo().addFocusListener(action);
        getTxtName().addFocusListener(action);
        getTxtSurname().addFocusListener(action);

        getJf().add(getJp());

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
            jp.setLayout(null);
            jp.setBackground(new Color(179, 0, 156));
        }
        return jp;
    }

    public void setJp(JPanel jp) {
        this.jp = jp;
    }

    public JLabel getLblName() {
        if (lblName == null) {
            lblName = new JLabel("Öğrenci Adı");
            lblName.setBounds(leftSpace, topSpace, lblWidth + (pushRightCounter * (lblWidth + pushRightSpace)), lblHeight);
            lblName.setForeground(Color.WHITE);
            lblName.setFont(font_lbl);

            pushRightCounter++;

        }
        return lblName;
    }

    public void setLblName(JLabel lblName) {
        this.lblName = lblName;
    }

    public JLabel getLblSurname() {
        if (lblSurname == null) {
            lblSurname = new JLabel("Öğrenci Soyadı");
            lblSurname.setBounds(leftSpace + (pushRightCounter * (lblWidth + pushRightSpace)), topSpace, lblWidth, lblHeight);
            lblSurname.setForeground(Color.WHITE);
            lblSurname.setFont(font_lbl);

            pushRightCounter++;

        }
        return lblSurname;
    }

    public void setLblSurname(JLabel lblSurname) {
        this.lblSurname = lblSurname;
    }

    public JLabel getLblNo() {
        if (lblNo == null) {
            lblNo = new JLabel("Öğrenci NO");
            lblNo.setBounds(leftSpace + (pushRightCounter * (lblWidth + pushRightSpace)), topSpace, lblWidth, lblHeight);
            lblNo.setForeground(Color.WHITE);
            lblNo.setFont(font_lbl);
            pushRightCounter++;
        }
        return lblNo;
    }

    public void setLblNo(JLabel lblNo) {
        this.lblNo = lblNo;
    }

    public JTable getTable() {
        if (table == null) {
            table = new JTable(DataOfTable, HeadersOfTable);
            table = OrganizeTable(table);
        }

        return table;
    }

    public JTable OrganizeTable(JTable table) {
        table.setSelectionBackground(Color.GREEN);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setRowHeight(15);
        table.getColumnModel().getColumn(0).setPreferredWidth((int) (screenSizeWidth / 27.32));
        table.getColumnModel().getColumn(1).setPreferredWidth((int) (screenSizeWidth / 13.66));
        table.getColumnModel().getColumn(2).setPreferredWidth((int) (screenSizeWidth / 10.507692307692308));
        table.getColumnModel().getColumn(3).setPreferredWidth((int) (screenSizeWidth / 10.507692307692308));
        table.getColumnModel().getColumn(4).setPreferredWidth((int) (screenSizeWidth / 5.464));
        table.getColumnModel().getColumn(5).setPreferredWidth((int) (screenSizeWidth / 9.106666666666667));

        table.setDefaultEditor(Object.class, null);
        table.setFont(font_txt);
        table.setCursor(new Cursor(12));
        table.setRowHeight((int) (screenSizeHeight / 38.4));
        table.setFont(new Font("", Font.BOLD, (int) screenSizeWidth / 91));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        table.setCellSelectionEnabled(true);
        // table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // table.getSelectionModel().addListSelectionListener(action);
        table.setRowSelectionAllowed(true);

        return table;

    }

    public void setTable(JTable table) {
        table = OrganizeTable(table);
        this.table = table;
    }

    public JScrollPane getSp() {
        if (sp == null) {
            sp = new JScrollPane(getTable());

        }
        sp.setBounds(leftSpace, (int) (screenSizeHeight / 6.144), (pushRightCounter * (lblWidth + pushRightSpace)) + txtWidth, (int) (screenSizeHeight / 2.56));

        return sp;
    }

    public void setSp(JTable table) {
        table = OrganizeTable(table);
        sp = new JScrollPane(table);
        this.sp = sp;
    }

    public JTextField getTxtName() {
        if (txtName == null) {
            pushRightCounter = 0;
            txtName = new JTextField("İsim Giriniz");
            txtName.setBounds(leftSpace + (pushRightCounter * (lblWidth + pushRightSpace)), topSpace + lblHeight + (int) (screenSizeHeight / 76.8), txtWidth, txtHeight);
            txtName.setForeground(Color.GRAY);
            txtName.setFont(fontTxtPlaceHolder);
            pushRightCounter++;

        }
        return txtName;
    }

    public void setTxtName(JTextField txtName) {
        this.txtName = txtName;
    }

    public JTextField getTxtSurname() {
        if (txtSurname == null) {
            txtSurname = new JTextField("Soyisim Giriniz");
            txtSurname.setBounds(leftSpace + (pushRightCounter * (lblWidth + pushRightSpace)), topSpace + lblHeight + (int) (screenSizeHeight / 76.8), txtWidth, txtHeight);
            txtSurname.setForeground(Color.GRAY);
            txtSurname.setFont(fontTxtPlaceHolder);

            pushRightCounter++;

        }
        return txtSurname;
    }

    public void setTxtSurname(JTextField txtSurname) {
        this.txtSurname = txtSurname;
    }

    public JTextField getTxtNo() {
        if (txtNo == null) {
            txtNo = new JTextField("Numara Giriniz");
            txtNo.setBounds(leftSpace + (pushRightCounter * (lblWidth + pushRightSpace)), topSpace + lblHeight + (int) (screenSizeHeight / 76.8), txtWidth, txtHeight);
            txtNo.setForeground(Color.GRAY);
            txtNo.setFont(fontTxtPlaceHolder);

        }
        return txtNo;
    }

    public void setTxtNo(JTextField txtNo) {
        this.txtNo = txtNo;
    }

    public JButton getBtnComeBack() {
        if (btnComeBack == null) {
            btnComeBack = new JButton("Geri dön");
            btnComeBack.setFont(font_lbl);
            btnComeBack.setBounds(leftSpace, (int) (screenSizeHeight / 1.7066666666666668), (int) (screenSizeWidth / 9.106666666666667), (int) (screenSizeHeight / 25.6));
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
