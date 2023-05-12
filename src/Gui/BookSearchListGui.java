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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

public class BookSearchListGui {

    MainGui mg;
    JFrame jf;
    JPanel jp;
    JLabel lblBookName;
    JLabel lblAuthorName;
    JLabel lblBarcodeNo;
    JLabel lblCategory;
    JTable table;
    JScrollPane sp;

    JTextField txtBookName;
    JTextField txtAuthorName;
    JTextField txtBarcodeNo;
    JTextField txtCategory;

    JButton btnComeBack;
    public String DataOfTable[][] = {};
    public String HeadersOfTable[] = {"", "Barcode No", "Book Name", "Book Status", "Book Category", "Author Name"};

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final double screenSizeWidth = screenSize.getWidth();
    final double screenSizeHeight = screenSize.getHeight();

    final int lblTopSpace = (int) (screenSizeHeight / 76.8);
    final int leftSpace = (int) (screenSizeWidth / 27.32);
    final int txtWidth = (int) (screenSizeWidth / 4.967272727272727);
    final int txtHeight = (int) (screenSizeHeight / 19.2);
    final int lblWidth = (int) (screenSizeWidth / 9.106666666666667);
    final int lblHeight = (int) (screenSizeHeight / 38.4);
    final int pushRightSpace = txtWidth + (int) (screenSizeWidth / 9.97080291970803);
    final int txtTopSpace = lblTopSpace + lblHeight + (int) (screenSizeHeight / 76.8);
    int pushRightCounter = 0;
    Color bslgPlaceHolder = Color.GRAY;
    Font fontTxtPlaceHolder = new Font("", Font.ITALIC, (int) screenSizeWidth / 80);
    Font font_lbl = new Font("monospaced", Font.BOLD, (int) screenSizeWidth / 80);
    Font font_txt = new Font("monospaced", Font.BOLD, (int) screenSizeWidth / 80);
    ActionsBook action = new ActionsBook(this);

    public BookSearchListGui(MainGui mg) {

        setMg(mg);
        setJf(mg.getJf());
        getJf().setTitle("BOOK LIST PAGE");

        getJf().add(getJp());
        getJp().add(getLblAuthorName());
        getJp().add(getLblBarcodeNo());
        getJp().add(getLblBookName());
        getJp().add(getLblCategory());
        getJp().add(getTxtAuthorName());
        getJp().add(getTxtBarcodeNo());
        getJp().add(getTxtBookName());
        getJp().add(getTxtCategory());
        getJp().add(getBtnComeBack());
        getTxtAuthorName().addActionListener(action);
        getTxtBarcodeNo().addActionListener(action);
        getTxtBookName().addActionListener(action);
        getTxtCategory().addActionListener(action);
        getBtnComeBack().addActionListener(action);

        getTxtAuthorName().addFocusListener(action);
        getTxtBarcodeNo().addFocusListener(action);
        getTxtBookName().addFocusListener(action);
        getTxtCategory().addFocusListener(action);
        getJp().add(getSp());

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
        return jf;
    }

    public void setJf(JFrame jf) {
        this.jf = jf;
    }

    public JPanel getJp() {
        if (jp == null) {
            jp = new JPanel();
            jp.setBounds(getJf().getBounds());
            jp.setBackground(new Color(100, 0, 100));
            jp.setLayout(null);
        }

        return jp;
    }

    public void setJp(JPanel jp) {
        this.jp = jp;
    }

    public JLabel getLblBookName() {
        if (lblBookName == null) {
            lblBookName = new JLabel("Book Name");
            lblBookName.setBounds(leftSpace, lblTopSpace + lblHeight + txtHeight + 15, lblWidth, lblHeight);
            lblBookName.setFont(font_lbl);
            lblBookName.setForeground(Color.WHITE);

        }
        return lblBookName;
    }

    public void setLblBookName(JLabel lblBookName) {
        this.lblBookName = lblBookName;
    }

    public JLabel getLblCategory() {

        if (lblCategory == null) {
            pushRightCounter = 1;
            lblCategory = new JLabel("Book Category");
            lblCategory.setBounds(leftSpace + (pushRightSpace * pushRightCounter),
                    lblTopSpace + lblHeight + txtHeight + (int) (screenSizeHeight / 51.2),
                    lblWidth, lblHeight);

            lblCategory.setFont(font_lbl);
            lblCategory.setForeground(Color.WHITE);

        }
        return lblCategory;
    }

    public void setLblCategory(JLabel lblCategory) {
        this.lblCategory = lblCategory;
    }

    public JLabel getLblAuthorName() {
        if (lblAuthorName == null) {
            lblAuthorName = new JLabel("Author Name");
            lblAuthorName.setBounds(leftSpace + (pushRightSpace * pushRightCounter), lblTopSpace, lblWidth, lblHeight);
            lblAuthorName.setFont(font_lbl);
            lblAuthorName.setForeground(Color.WHITE);
            pushRightCounter++;
        }
        return lblAuthorName;
    }

    public void setLblAuthorName(JLabel lblAuthorName) {
        this.lblAuthorName = lblAuthorName;
    }

    public JLabel getLblBarcodeNo() {
        if (lblBarcodeNo == null) {
            lblBarcodeNo = new JLabel("Barcode No");
            lblBarcodeNo.setBounds(leftSpace + (pushRightSpace * pushRightCounter), lblTopSpace, lblWidth, lblHeight);
            lblBarcodeNo.setFont(font_lbl);
            lblBarcodeNo.setForeground(Color.WHITE);
            pushRightCounter++;
        }
        return lblBarcodeNo;
    }

    public void setLblBarcodeNo(JLabel lblBarcodeNo) {
        this.lblBarcodeNo = lblBarcodeNo;
    }

    public void ExpandJframeAndJtable() {

        for (int i = getSp().getWidth(); i <= getTable().getPreferredSize().width; i++) {
            getJf().setBounds(getJf().getX() - 1, getJf().getY(), getJf().getWidth() + 2, getJf().getHeight());
            getSp().setBounds(leftSpace, txtTopSpace + lblHeight + txtHeight * 3,
                    (int) (screenSizeWidth / 1.9514285714285715) + 1, (int) (screenSizeHeight / 3.072));

        }

    }

    public JTable OrganizeTable(JTable table) {
        table = new JTable(DataOfTable, HeadersOfTable);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setRowHeight(15);
        table.getColumnModel().getColumn(0).setPreferredWidth((int) (screenSizeWidth / 19.514285714285716));
        table.getColumnModel().getColumn(1).setPreferredWidth((int) (screenSizeWidth / 9.106666666666667));
        table.getColumnModel().getColumn(2).setPreferredWidth((int) (screenSizeWidth / 3.0355555555555553));
        table.getColumnModel().getColumn(3).setPreferredWidth((int) (screenSizeWidth / 13.66));
        table.getColumnModel().getColumn(4).setPreferredWidth((int) (screenSizeWidth / 11.383333333333333));
        table.getColumnModel().getColumn(5).setPreferredWidth((int) (screenSizeWidth / 9.106666666666667));

        table.setDefaultEditor(Object.class, null);
        table.setFont(font_txt);
        table.setCursor(new Cursor(12));
        table.setRowHeight((int) (screenSizeHeight / 38.4));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.setSelectionBackground(Color.green);

        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        //  table.getSelectionModel().addListSelectionListener(action);
        table.setRowSelectionAllowed(true);
        return table;

    }

    public JTable getTable() {
        if (table == null) {
            table = new JTable(DataOfTable, HeadersOfTable);
            table = OrganizeTable(table);
        }

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

        sp.setBounds(leftSpace, txtTopSpace + lblHeight + txtHeight * 3, (int) (screenSizeWidth / 1.9514285714285715), (int) (screenSizeHeight / 3.072));

        return sp;
    }

    public void setSp(JTable table) {
        table = OrganizeTable(table);
        sp = new JScrollPane(table);
        this.sp = sp;

    }

    public JTextField getTxtBarcodeNo() {
        if (txtBarcodeNo == null) {
            txtBarcodeNo = new JTextField("");
            txtBarcodeNo.setBounds(leftSpace + (pushRightSpace * pushRightCounter), txtTopSpace, txtWidth, txtHeight);
            txtBarcodeNo.setFont(fontTxtPlaceHolder);
            txtBarcodeNo.setForeground(bslgPlaceHolder);
            txtBarcodeNo.setText("Type Barcode No");
            pushRightCounter++;
        }

        return txtBarcodeNo;
    }

    public void setTxtBarcodeNo(JTextField txtBarcodeNo) {
        this.txtBarcodeNo = txtBarcodeNo;
    }

    public JTextField getTxtBookName() {
        if (txtBookName == null) {
            pushRightCounter = 0;
            txtBookName = new JTextField("");
            txtBookName.setBounds(leftSpace, txtTopSpace + lblHeight + txtHeight + (int) (screenSizeHeight / 51.2), txtWidth, txtHeight);
            txtBookName.setFont(fontTxtPlaceHolder);
            txtBookName.setForeground(bslgPlaceHolder);
            txtBookName.setText("Type Book Name");
            pushRightCounter++;
        }

        return txtBookName;
    }

    public void setTxtBookName(JTextField txtBookName) {
        this.txtBookName = txtBookName;
    }

    public JTextField getTxtCategory() {
        if (txtCategory == null) {
            pushRightCounter = 1;
            txtCategory = new JTextField("");
            txtCategory.setBounds(leftSpace + (pushRightSpace * pushRightCounter), txtTopSpace + lblHeight + txtHeight + (int) (screenSizeHeight / 51.2), txtWidth, txtHeight);
            txtCategory.setFont(fontTxtPlaceHolder);
            txtCategory.setForeground(bslgPlaceHolder);
            txtCategory.setText("Type Category Name");

        }

        return txtCategory;
    }

    public void setTxtCategory(JTextField txtCategory) {
        this.txtCategory = txtCategory;
    }

    public JTextField getTxtAuthorName() {
        if (txtAuthorName == null) {

            pushRightCounter = 0;
            txtAuthorName = new JTextField("");
            txtAuthorName.setBounds(leftSpace + (pushRightSpace * pushRightCounter), txtTopSpace, txtWidth, txtHeight);
            txtAuthorName.setFont(fontTxtPlaceHolder);
            txtAuthorName.setForeground(bslgPlaceHolder);
            txtAuthorName.setText("Type Author Name");
            pushRightCounter++;
        }

        return txtAuthorName;
    }

    public void setTxtAuthorName(JTextField txtAuthorName) {
        this.txtAuthorName = txtAuthorName;
    }

    public JButton getBtnComeBack() {
        if (btnComeBack == null) {
            btnComeBack = new JButton("Back");
            btnComeBack.setFont(font_lbl);
            btnComeBack.setBounds(leftSpace, (int) (screenSizeHeight / 1.7066666666666668),
                    (int) (screenSizeWidth / 9.106666666666667), (int) (screenSizeHeight / 25.6));
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
