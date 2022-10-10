import javax.security.auth.login.LoginException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;


public class LoginView extends JFrame {
    private LoginController loginController;
    public static final int weidth = 400;
    public static final int height = 200;

    private JPanel loginPanel;
    private JTextField loginField;
    private JPasswordField passwordField;
    private JButton login;
    private JButton newUser;
    private JButton guest;
    private JButton forgotPassword;
    private JLabel info;

    public LoginView(LoginController loginController){
        this.loginController = loginController;
        start();
    }

    public void start(){
        this.setSize(weidth+10, height+10);
        this.setTitle("Авторизация");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //местоположение окна
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        this.setBounds(dimension.width/2-weidth/2,dimension.height/2-height/2,weidth,height);

        //содержимое окна
        GridBagLayout gridBagLayout = new GridBagLayout();
        loginPanel = new JPanel(gridBagLayout);
        loginField = new JTextField(20);
        passwordField = new JPasswordField(20);
        login = new JButton("Войти");
        newUser = new JButton("Зарегестрироваться");
        guest = new JButton("Гостевой режим");
        forgotPassword = new JButton("Забыл пароль");
        info = new JLabel("");

        //добавление на панель
        add(loginPanel);
        GridBagConstraints a1 = new GridBagConstraints();
        a1.weightx = 0;
        a1.weighty = 0;
        a1.gridx = 2;
        a1.gridy = 1;
        a1.gridwidth = 4;
        a1.gridheight = 1;
        loginPanel.add(loginField, a1);

        GridBagConstraints a2 = new GridBagConstraints();
        a2.weightx = 0;
        a2.weighty = 0;
        a2.gridx = 2;
        a2.gridy = 2;
        a2.gridwidth = 4;
        a2.gridheight = 1;
        loginPanel.add(passwordField, a2);

        GridBagConstraints a3 = new GridBagConstraints();
        a3.weightx = 0;
        a3.weighty = 0;
        a3.gridx = 7;
        a3.gridy = 2;
        a3.gridwidth = 2;
        a3.gridheight = 1;
        loginPanel.add(forgotPassword, a3);

        GridBagConstraints a4 = new GridBagConstraints();
        a4.weightx = 0;
        a4.weighty = 0;
        a4.gridx = 4;
        a4.gridy = 3;
        a4.gridwidth = 2;
        a4.gridheight = 1;
        loginPanel.add(login, a4);

        GridBagConstraints a5 = new GridBagConstraints();
        a5.weightx = 0;
        a5.weighty = 0;
        a5.gridx = 4;
        a5.gridy = 5;
        a5.gridwidth = 2;
        a5.gridheight = 1;
        loginPanel.add(newUser, a5);

        GridBagConstraints a6 = new GridBagConstraints();
        a6.weightx = 0;
        a6.weighty = 0;
        a6.gridx = 7;
        a6.gridy = 0;
        a6.gridwidth = 1;
        a6.gridheight = 1;
        loginPanel.add(guest, a6);

        GridBagConstraints a7 = new GridBagConstraints();
        a7.weightx = 0;
        a7.weighty = 0;
        a7.gridx = 2;
        a7.gridy = 0;
        a7.gridwidth = 5;
        a7.gridheight = 1;
        loginPanel.add(info, a7);

        loginPanel.revalidate();
        //Акшены

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            String loginText = loginField.getText();
            String passwordText = String.valueOf(passwordField.getPassword());
            Boolean fields = loginController.is_Empty(loginText,passwordText);
            if (!fields){
                info.setText("Введите логин и/или пароль");
            }else{
                try {
                    String role = loginController.checkLoginAndPassword(loginText,passwordText);
                    System.out.println(role);
                    if (!role.equals("bad")){//сделать чтоб возвращал роль
                        info.setText("Добро пожаловать!");
                        setVisible(false);
                        loginController.goToView(role);
                    }else{
                        info.setText("Неверный логин или пароль");
                    }
                } catch (LoginException ex) {
                    ex.printStackTrace();
                } catch (NoSuchAlgorithmException ex) {
                    ex.printStackTrace();
                }
            }


            }
        });

        guest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                loginController.goToView("0");
            }
        });

        newUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                loginController.registration();
            }
        });

        forgotPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                //здесь еще надо сделать штуку для изменения пароля


            }
        });

        this.setVisible(true); //он сам вызывается метод --> paint(g)
    }

//    @Override
//    public void paint(Graphics g){
//
//    }
}
