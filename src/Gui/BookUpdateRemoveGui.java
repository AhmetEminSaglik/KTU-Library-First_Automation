package Gui;

import Logic.ActionsBook;
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

public class BookUpdateRemoveGui extends JPanel {

    MainGui mg;
    JFrame jf;

    JLabel lblToBeChangedBarcodeNo;
    JLabel lblNewBarcodeNo;
    JLabel lblNewBookName;
    JLabel lblNewAuthorName;
    JLabel lblResult;
    JLabel lblNewCategory;

    JTextField txtBarcodeNo;
    JTextField txtNewBarcodeNo;
    JTextField txtNewBookName;
    JTextField txtNewAuthorName;
    JTextField txtResult;
    JTextField txtNewCategory;

    JButton btnComeBack;
    JButton btnUpdate;
    JButton btnDelete;

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

    final int lblPushSpaceRight = lblWidth + (int) (screenSizeWidth / 13.66);
    final int lblPushSpaceUnder = lblHeight + (int) (screenSizeHeight / 15.36);
    final int txtPushSpaceUnder = txtHeight + lblHeight + (int) (screenSizeHeight / 25.6);

    Font lblFont = new Font("monospaced", Font.BOLD, (int) screenSizeWidth / 80);
    Font txtFont = new Font("", Font.BOLD, (int) screenSizeWidth / 91);
    ActionsBook action = new ActionsBook(this);

    public BookUpdateRemoveGui(MainGui mg) {

        setMg(mg);
        this.setBounds(getJf().getBounds());
        this.setBackground(new Color(50, 150, 150));
        this.setLayout(null);
        getMg().getJf().setTitle("KİTAP GÜNCELLEME & SİLME");

        setJf(mg.getJf());
        mg.getJp().setVisible(false);
        getJf().add(this);

        this.add(getLblToBeChangedBarcodeNo());
        this.add(getLblNewBarcodeNo());
        this.add(getLblNewCategory());
        this.add(getLblNewBookName());
        this.add(getLblNewAuthorName());
        this.add(getLblResult());

        this.add(getTxtBarcodeNo());
        this.add(getTxtNewBarcodeNo());
        this.add(getTxtNewCategory());
        this.add(getTxtNewBookName());
        this.add(getTxtNewAuthorName());
        this.add(getTxtResult());
        this.add(getBtnComeBack());
        this.add(getBtnUpdate());
        this.add(getBtnDelete());
        getBtnComeBack().addActionListener(action);
        getBtnDelete().addActionListener(action);
        getBtnUpdate().addActionListener(action);
        getTxtBarcodeNo().addActionListener(action);
        getTxtNewBookName().addActionListener(action);
        getTxtNewAuthorName().addActionListener(action);
        getTxtNewBarcodeNo().addActionListener(action);
        getTxtNewCategory().addActionListener(action);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.orange);
        g.fillRect(0, heightpaintY, (int) (screenSizeWidth / 1.6070588235294119), (int) (screenSizeHeight / 76.8));

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

    public JLabel getLblToBeChangedBarcodeNo() {
        if (lblToBeChangedBarcodeNo == null) {
            lblToBeChangedBarcodeNo = new JLabel("Güncellenecek kitap Barkod Numarası");
            lblToBeChangedBarcodeNo.setBounds(leftSpace, (int) (screenSizeHeight / 51.2), lblWidth, lblHeight);
            lblToBeChangedBarcodeNo.setForeground(Color.white);
            lblToBeChangedBarcodeNo.setFont(lblFont);

        }
        return lblToBeChangedBarcodeNo;
    }

    public void setLblToBeChangedBarcodeNo(JLabel lblToBeChangedBarcodeNo) {
        this.lblToBeChangedBarcodeNo = lblToBeChangedBarcodeNo;
    }

    public JLabel getLblNewBarcodeNo() {

        if (lblNewBarcodeNo == null) {
            lblNewBarcodeNo = new JLabel("Yeni Barkod Numarası");
            lblNewBarcodeNo.setBounds(leftSpace,
                    lblTopSpace + (lblHeight + txtHeight) * lblPushUnderCounter, lblWidth, lblHeight);
            lblNewBarcodeNo.setForeground(Color.WHITE);
            lblNewBarcodeNo.setFont(lblFont);

        }

        return lblNewBarcodeNo;
    }

    public void setLblNewBarcodeNo(JLabel lblNewBarcodeNo) {
        this.lblNewBarcodeNo = lblNewBarcodeNo;
    }

    public JLabel getLblNewCategory() {
        if (lblNewCategory == null) {
            lblNewCategory = new JLabel("Yeni Kategori Adı");
            lblNewCategory.setBounds(leftSpace + txtWidth + (int) (screenSizeWidth / 27.32),
                    lblTopSpace + (lblHeight + txtHeight) * lblPushUnderCounter, lblWidth, lblHeight);
            lblNewCategory.setForeground(Color.WHITE);
            lblNewCategory.setFont(lblFont);
            lblPushUnderCounter++;
        }
        return lblNewCategory;
    }

    public void setLblNewCategory(JLabel lblNewCategory) {
        this.lblNewCategory = lblNewCategory;
    }

    public JLabel getLblNewBookName() {

        if (lblNewBookName == null) {
            lblNewBookName = new JLabel("Yeni Kitap İsmi ");
            lblNewBookName.setBounds(leftSpace,
                    lblTopSpace + (lblHeight + txtHeight + (int) (screenSizeHeight / 25.6)) * lblPushUnderCounter, lblWidth, lblHeight);
            lblNewBookName.setForeground(Color.WHITE);
            lblNewBookName.setFont(lblFont);
            lblPushUnderCounter++;
        }

        return lblNewBookName;
    }

    public void setLblNewBookName(JLabel lblNewBookName) {
        this.lblNewBookName = lblNewBookName;
    }

    public JLabel getLblNewAuthorName() {
        if (lblNewAuthorName == null) {
            lblNewAuthorName = new JLabel("Yeni Yazar İsmi");
            lblNewAuthorName.setBounds(leftSpace,
                    lblTopSpace + (lblHeight + txtHeight + (int) (screenSizeHeight / 25.6)) * lblPushUnderCounter, lblWidth, lblHeight);
            lblNewAuthorName.setForeground(Color.WHITE);
            lblNewAuthorName.setFont(lblFont);
            lblPushUnderCounter++;
        }
        return lblNewAuthorName;
    }

    public void setLblNewAuthorName(JLabel lblNewAuthorName) {
        this.lblNewAuthorName = lblNewAuthorName;
    }

    public JLabel getLblResult() {
        if (lblResult == null) {
            lblResult = new JLabel("SONUÇ");

            lblResult.setBounds(leftSpace + lblWidth + (int) (screenSizeWidth / 32.523809523809526),
                    (int) (screenSizeHeight / 51.2), lblWidth, lblHeight);
            lblResult.setForeground(Color.WHITE);
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
            txtBarcodeNo.setBounds(leftSpace, (int) (screenSizeHeight / 15.36), txtWidth, txtHeight);
            txtBarcodeNo.setFont(txtFont);

        }

        return txtBarcodeNo;
    }

    public void setTxtBarcodeNo(JTextField txtBarcodeNo) {
        this.txtBarcodeNo = txtBarcodeNo;
    }

    public JTextField getTxtNewBarcodeNo() {
        if (txtNewBarcodeNo == null) {
            txtNewBarcodeNo = new JTextField("");
            txtNewBarcodeNo.setBounds(leftSpace,
                    txtTopSpace + (txtPushSpaceUnder * txtPushUnderCounter), txtWidth, txtHeight);
            txtNewBarcodeNo.setFont(txtFont);

        }
        return txtNewBarcodeNo;
    }

    public void setTxtNewBarcodeNo(JTextField txtNewBarcodeNo) {
        this.txtNewBarcodeNo = txtNewBarcodeNo;
    }

    public JTextField getTxtNewCategory() {

        if (txtNewCategory == null) {
            txtNewCategory = new JTextField("");
            txtNewCategory.setBounds(leftSpace + txtWidth + (int) (screenSizeHeight / 15.36),
                    txtTopSpace + (txtPushSpaceUnder * txtPushUnderCounter), txtWidth, txtHeight);
            txtNewCategory.setFont(txtFont);
            txtPushUnderCounter++;
        }
        return txtNewCategory;
    }

    public void setTxtNewCategory(JTextField txtNewCategory) {
        this.txtNewCategory = txtNewCategory;
    }

    public JTextField getTxtNewBookName() {
        if (txtNewBookName == null) {
            txtNewBookName = new JTextField();
            txtNewBookName.setBounds(leftSpace,
                    txtTopSpace + (txtPushSpaceUnder * txtPushUnderCounter), txtWidth * 2 + (int) (screenSizeWidth / 27.32), txtHeight);
            txtNewBookName.setFont(txtFont);
            txtPushUnderCounter++;
        }
        return txtNewBookName;
    }

    public void setTxtNewBookName(JTextField txtNewBookName) {
        this.txtNewBookName = txtNewBookName;
    }

    public JTextField getTxtNewAuthorName() {
        if (txtNewAuthorName == null) {
            txtNewAuthorName = new JTextField();
            txtNewAuthorName.setBounds(leftSpace,
                    txtTopSpace + (txtPushSpaceUnder * txtPushUnderCounter), txtWidth, txtHeight);
            txtNewAuthorName.setFont(txtFont);

        }
        return txtNewAuthorName;
    }

    public void setTxtNewAuthorName(JTextField txtNewAuthorName) {
        this.txtNewAuthorName = txtNewAuthorName;
    }

    public JTextField getTxtResult() {
        if (txtResult == null) {
            txtResult = new JTextField();

            txtResult.setBounds(txtWidth + leftSpace + (int) (screenSizeWidth / 27.32),
                    (int) (screenSizeHeight / 15.36), txtWidth, txtHeight);
            txtResult.setFont(txtFont);
            txtResult.setEditable(false);
            txtResult.setBackground(new Color(206, 214, 224));
            ToolTipManager ttm = ToolTipManager.sharedInstance();
            ttm.setInitialDelay(100);
            ttm.setDismissDelay(1000);
            txtResult.setToolTipText("Buraya erişemezsiniz");

            txtPushUnderCounter++;
        }
        return txtResult;
    }

    public void setTxtResult(JTextField txtResult) {
        this.txtResult = txtResult;
    }

    public JButton getBtnComeBack() {
        if (btnComeBack == null) {
            btnComeBack = new JButton("Geri Dön");
            btnComeBack.setFont(lblFont);
            btnComeBack.setBounds(leftSpace,
                    txtTopSpace + (txtPushSpaceUnder * txtPushUnderCounter), txtWidth, txtHeight);
            btnComeBack.setCursor(new Cursor(12));
        }
        return btnComeBack;
    }

    public void setBtnComeBack(JButton btnComeBack) {
        this.btnComeBack = btnComeBack;
    }

    public JButton getBtnUpdate() {
        if (btnUpdate == null) {
            btnUpdate = new JButton("Güncelle");
            btnUpdate.setFont(lblFont);
            btnUpdate.setBounds(leftSpace + txtWidth + (int) (screenSizeWidth / 27.32),
                    txtTopSpace + (txtPushSpaceUnder * txtPushUnderCounter) - (int) (screenSizeHeight / 8.533333333333333),
                    txtWidth, txtHeight);

            txtPushUnderCounter++;
            btnUpdate.setCursor(new Cursor(12));
        }

        return btnUpdate;
    }

    public void setBtnUpdate(JButton btnUpdate) {
        this.btnUpdate = btnUpdate;
    }

    public JButton getBtnDelete() {
        if (btnDelete == null) {
            btnDelete = new JButton();
            btnDelete = new JButton("Sil");
            btnDelete.setFont(lblFont);
            btnDelete.setBounds(leftSpace + txtWidth + (int) (screenSizeWidth / 27.32),
                    txtTopSpace + (txtPushSpaceUnder * txtPushUnderCounter) - (int) (screenSizeHeight / 8.533333333333333),
                    txtWidth, txtHeight);
            btnDelete.setCursor(new Cursor(12));
        }
        return btnDelete;
    }

    public void setBtnDelete(JButton btnDelete) {
        this.btnDelete = btnDelete;
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
