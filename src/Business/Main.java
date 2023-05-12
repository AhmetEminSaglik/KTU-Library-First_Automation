package Business;

import Gui.Login;
import Logic.JavaMailUtil;
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
        if (JavaMailUtil.usernameEmail.equals("example@gmail.com") || JavaMailUtil.passwordEmail.equals("passwrods")) {
            JOptionPane.showMessageDialog(null, "Please change usernameEmail and  passwordEmail in JavaMailUtil Class\n" +
                     "Otherwise mail will not be send.");
        }
        new Login();

    }
}
