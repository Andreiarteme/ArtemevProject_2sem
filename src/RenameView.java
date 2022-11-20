import javax.security.auth.login.LoginException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;


public class RenameView extends JFrame {

    private RenameController renameController;
    public static final int weidth = 400;
    public static final int height = 200;

    private JPanel panel;

    private JTextField login;
    private JTextField password;
    private JTextField group;

    private JButton exit;
    private JButton save;
    private JLabel info;




    public RenameView(RenameController renameController) {
        this.renameController = renameController;
        start1();
    }

    private void start1() {
        this.setSize(weidth + 10, height + 10);
        this.setTitle("Регистрация");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //местоположение окна
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        this.setBounds(dimension.width / 2 - weidth / 2, dimension.height / 2 - height / 2, weidth, height);

        //содержимое окна
        GridBagLayout gridBagLayout = new GridBagLayout();
        panel = new JPanel(gridBagLayout);
        login = new JTextField(15);
        password = new JTextField(10);
        group = new JTextField(10);

        exit = new JButton("Назад");
        save = new JButton("Сохранить");
        info = new JLabel("info");

        //добавление на панель
        add(panel);
        GridBagConstraints a1 = new GridBagConstraints();
        a1.weightx = 0;
        a1.weighty = 0;
        a1.gridx = 0;
        a1.gridy = 0;
        a1.gridwidth = 4;
        a1.gridheight = 1;
        panel.add(login, a1);

        GridBagConstraints a2 = new GridBagConstraints();
        a2.weightx = 0;
        a2.weighty = 0;
        a2.gridx = 0;
        a2.gridy = 1;
        a2.gridwidth = 4;
        a2.gridheight = 1;
        panel.add(password, a2);

        GridBagConstraints a3 = new GridBagConstraints();
        a3.weightx = 0;
        a3.weighty = 0;
        a3.gridx = 0;
        a3.gridy = 2;
        a3.gridwidth = 4;
        a3.gridheight = 1;
        panel.add(group, a3);


        GridBagConstraints a6 = new GridBagConstraints();
        a6.weightx = 0;
        a6.weighty = 0;
        a6.gridx = 0;
        a6.gridy = 5;
        a6.gridwidth = 2;
        a6.gridheight = 1;
        panel.add(exit, a6);

        GridBagConstraints a7 = new GridBagConstraints();
        a7.weightx = 0;
        a7.weighty = 0;
        a7.gridx = 3;
        a7.gridy = 5;
        a7.gridwidth = 2;
        a7.gridheight = 1;
        panel.add(save, a7);

        GridBagConstraints a8 = new GridBagConstraints();
        a8.weightx = 0;
        a8.weighty = 0;
        a8.gridx = 0;
        a8.gridy = 6;
        a8.gridwidth = 5;
        a8.gridheight = 1;
        panel.add(info, a8);

        panel.revalidate();

        //Акшены

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login1 = login.getText().trim();
                String password1 = password.getText().trim();
                String group1 = group.getText().trim();



                Boolean bool = renameController.rename(login1,password1, group1, info);
                if (bool) {
                    setVisible(false);
                    info.setText("I here");
                }


            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                renameController.goBack();


            }
        });


        this.setVisible(true);
    }


}