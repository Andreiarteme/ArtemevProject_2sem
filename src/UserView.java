import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class UserView extends JFrame {
    private UserController userController;
    public static final int weidth = 800;
    public static final int height = 600;



    private JButton insert;
    private JButton delete;
    private JButton edit;
    private JButton exit;
    private JLabel info;
    private JLabel info2;
    private JTable table;

    private JButton rename;


    public UserView(UserController userController) {
        this.userController = userController;
        start();
    }

    public void start() {
        this.setSize(weidth + 10, height + 10);
        this.setTitle("Цитаты преподавателей");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        //местоположение окна
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        this.setBounds(dimension.width / 2 - weidth / 2, dimension.height / 2 - height / 2, weidth, height);

        //содержимое окна

        //добавление на панель

        UserTableModel tableModel = new UserTableModel(userController.getDataBase().getQuotes().getArQuotes(), userController.getUser());
        table = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setPreferredSize(new Dimension(800, 600));

        add(tableScroll, new GridBagConstraints(0, 0, 5, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        exit = new JButton("Назад");
        add(exit, new GridBagConstraints(0, 2, 1, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));

        insert = new JButton("Добавить");
        add(insert, new GridBagConstraints(1, 2, 1, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));

        edit = new JButton("Редактировать");
        add(edit, new GridBagConstraints(2, 2, 1, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));

        delete = new JButton("Удалить");
        add(delete, new GridBagConstraints(3, 2, 1, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));


        info = new JLabel("Мои записи = " + tableModel.getCount());
        add(info, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));

        info2 = new JLabel("");
        add(info2, new GridBagConstraints(2, 1, 1, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));


        rename = new JButton("Редактировать свои данные");
        add(rename, new GridBagConstraints(4, 2, 1, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));


        //Акшены
        insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userController.insert();
                setVisible(false);

            }
        });

        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ind = table.getSelectedRow();
                if(userController.edit(ind)) {
                    setVisible(false);
                }else {
                    info2.setText("Это не ваша запись");
                }

            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ind = table.getSelectedRow();
                try {
                    if(userController.delete(ind)) {
                        setVisible(false);
                    }else {
                        info2.setText("Это не ваша запись");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });


        rename.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userController.rename();
                    setVisible(false);

            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                userController.toLoginWindow();
            }
        });


        this.setVisible(true);

    }
}