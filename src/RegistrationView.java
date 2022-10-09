import javax.security.auth.login.LoginException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;


public class RegistrationView extends JFrame {
    private RegistrationController registrationController;
    public static final int weidth = 400;
    public static final int height = 200;

    private JPanel registrationPanel;
    private JTextField loginField;
    private JPasswordField passwordField;
    private JButton registration;
    private JButton authorization;
    private JLabel info;

    public RegistrationView(RegistrationController registrationController){
        this.registrationController = registrationController;
        start1();
    }

    private void start1(){
        this.setSize(weidth+10, height+10);
        this.setTitle("Регистрация");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //местоположение окна
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        this.setBounds(dimension.width/2-weidth/2,dimension.height/2-height/2,weidth,height);

        //содержимое окна
        GridBagLayout gridBagLayout = new GridBagLayout();
        registrationPanel = new JPanel(gridBagLayout);
        loginField = new JTextField(20);
        passwordField = new JPasswordField(20);
        registration = new JButton("Зарегестрироваться");
        authorization = new JButton("Авторизироваться");
        info = new JLabel("Данный логин уже зарегестрирован");

        //добавление на панель
        add(registrationPanel);
        GridBagConstraints a1 = new GridBagConstraints();
        a1.weightx = 0;
        a1.weighty = 0;
        a1.gridx = 2;
        a1.gridy = 1;
        a1.gridwidth = 4;
        a1.gridheight = 1;
        registrationPanel.add(loginField, a1);

        GridBagConstraints a2 = new GridBagConstraints();
        a2.weightx = 0;
        a2.weighty = 0;
        a2.gridx = 2;
        a2.gridy = 2;
        a2.gridwidth = 4;
        a2.gridheight = 1;
        registrationPanel.add(passwordField, a2);

        GridBagConstraints a3 = new GridBagConstraints();
        a3.weightx = 0;
        a3.weighty = 0;
        a3.gridx = 4;
        a3.gridy = 4;
        a3.gridwidth = 2;
        a3.gridheight = 1;
        registrationPanel.add(authorization, a3);

        GridBagConstraints a4 = new GridBagConstraints();
        a4.weightx = 0;
        a4.weighty = 0;
        a4.gridx = 4;
        a4.gridy = 3;
        a4.gridwidth = 2;
        a4.gridheight = 1;
        registrationPanel.add(registration, a4);

        GridBagConstraints a5 = new GridBagConstraints();
        a5.weightx = 0;
        a5.weighty = 0;
        a5.gridx = 2;
        a5.gridy = 0;
        a5.gridwidth = 5;
        a5.gridheight = 1;
        registrationPanel.add(info, a5);

        registrationPanel.revalidate();

        //Акшены

        registration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String regLogin = loginField.getText().trim();
                String regPassword = String.valueOf(passwordField.getPassword()).trim();
                try {
                    Boolean bool = registrationController.registration(regLogin,regPassword,info);
                    if (bool){
                        setVisible(false);
                    }
                } catch (LoginException ex) {
                    ex.printStackTrace();
                } catch (NoSuchAlgorithmException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }


            }
        });

        authorization.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                registrationController.authorization();



            }
        });


        this.setVisible(true); //он сам вызывается метод --> paint(g)
    }

//    @Override
//    public void paint(Graphics g){
//
//    }
}