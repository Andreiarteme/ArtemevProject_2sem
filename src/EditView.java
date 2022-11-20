import javax.security.auth.login.LoginException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;


public class EditView extends JFrame {

    private EditController editController;
    public static final int weidth = 400;
    public static final int height = 200;

    private JPanel panel;
    private JTextField quore;
    private JTextField teacher;
    private JTextField subject;
    private JTextField data;
    private JTextField userId;
    private JButton exit;
    private JButton save;
    private JLabel info;
    private Quote quo;



    public EditView(EditController editController, Quote quo) {
        this.editController = editController;
        this.quo = quo;
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
        quore = new JTextField(quo.getQuote(),20);
        teacher = new JTextField(quo.getTeacher(),15);
        subject = new JTextField(quo.getSubject(),10);
        data = new JTextField(quo.getData(),10);
        userId = new JTextField(quo.getUserId(),5);
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
        panel.add(quore, a1);

        GridBagConstraints a2 = new GridBagConstraints();
        a2.weightx = 0;
        a2.weighty = 0;
        a2.gridx = 0;
        a2.gridy = 1;
        a2.gridwidth = 4;
        a2.gridheight = 1;
        panel.add(teacher, a2);

        GridBagConstraints a3 = new GridBagConstraints();
        a3.weightx = 0;
        a3.weighty = 0;
        a3.gridx = 0;
        a3.gridy = 2;
        a3.gridwidth = 4;
        a3.gridheight = 1;
        panel.add(subject, a3);

        GridBagConstraints a4 = new GridBagConstraints();
        a4.weightx = 0;
        a4.weighty = 0;
        a4.gridx = 0;
        a4.gridy = 3;
        a4.gridwidth = 4;
        a4.gridheight = 1;
        panel.add(data, a4);

        GridBagConstraints a5 = new GridBagConstraints();
        a5.weightx = 0;
        a5.weighty = 0;
        a5.gridx = 0;
        a5.gridy = 4;
        a5.gridwidth = 4;
        a5.gridheight = 1;
        panel.add(userId, a5);


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
                String quore1 = quore.getText().trim();
                String teacher1 = teacher.getText().trim();
                String subject1 = subject.getText().trim();
                String data1 = data.getText().trim();
                String userId1 = userId.getText().trim();

                try {
                    Boolean bool = editController.edition(quo.getId(),quore1, teacher1, subject1, data1, userId1, info);
                    if (bool) {
                        setVisible(false);
                        info.setText("I here");
                    }
                    info.setText("Не получилось");
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

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                editController.goBack();


            }
        });


        this.setVisible(true);
    }
}
