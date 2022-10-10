import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuestView extends JFrame {
    private GuestController guestController;
    public static final int weidth = 800;
    public static final int height = 600;

    private JPanel quotePanel;
    private JLabel quote;
    private JLabel teacher;
    private JLabel subject;
    private JLabel data;
    private JButton exit;
//    private JTable table;

    public GuestView(GuestController guestController){
        this.guestController = guestController;
        start();
    }

    public void start(){
       this.setSize(weidth+10, height+10);
//        this.setSize(new Dimension());
        this.setTitle("Цитаты преподавателей");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setLocationRelativeTo(null);
        this.setLayout(new GridBagLayout());
        //местоположение окна
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        this.setBounds(dimension.width/2-weidth/2,dimension.height/2-height/2,weidth,height);

        //содержимое окна
//        GridBagLayout gridBagLayout = new GridBagLayout();
//        quotePanel = new JPanel(gridBagLayout);
//        quote = new JLabel("");
//        teacher = new JLabel("");
//        subject = new JLabel("");
//        data = new JLabel("");
//        exit = new JButton("Выйти обратно");

        //добавление на панель

        GuestTableModel tableModel = new GuestTableModel(guestController.getDataBase().getQuotes().getArQuotes());
        JTable table = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setPreferredSize(new Dimension(800,600));
//        for (Quote quote: guestController.getDataBase().getQuotes().getArQuotes()){
//            tableModel.addInTable(quote);
//        }

        //tableModel.addInTable(guestController.getDataBase().getQuotes().getArQuotes());

        add(tableScroll, new GridBagConstraints(0,0,3,1,1,1,
                GridBagConstraints.NORTH,GridBagConstraints.BOTH,
                new Insets(1,1,1,1),0,0));

        exit = new JButton("Назад");
        add(exit, new GridBagConstraints(0,1,1,1,1,1,
                GridBagConstraints.SOUTH,GridBagConstraints.HORIZONTAL,
                new Insets(1,1,1,1),0,0));


//        add(loginPanel);
//        GridBagConstraints a1 = new GridBagConstraints();
//        a1.weightx = 0;
//        a1.weighty = 0;
//        a1.gridx = 2;
//        a1.gridy = 1;
//        a1.gridwidth = 4;
//        a1.gridheight = 1;
//        loginPanel.add(loginField, a1);
//
//        GridBagConstraints a2 = new GridBagConstraints();
//        a2.weightx = 0;
//        a2.weighty = 0;
//        a2.gridx = 2;
//        a2.gridy = 2;
//        a2.gridwidth = 4;
//        a2.gridheight = 1;
//        loginPanel.add(passwordField, a2);
//
//        GridBagConstraints a3 = new GridBagConstraints();
//        a3.weightx = 0;
//        a3.weighty = 0;
//        a3.gridx = 7;
//        a3.gridy = 2;
//        a3.gridwidth = 2;
//        a3.gridheight = 1;
//        loginPanel.add(forgotPassword, a3);
//
//        GridBagConstraints a4 = new GridBagConstraints();
//        a4.weightx = 0;
//        a4.weighty = 0;
//        a4.gridx = 4;
//        a4.gridy = 3;
//        a4.gridwidth = 2;
//        a4.gridheight = 1;
//        loginPanel.add(login, a4);
//
//        GridBagConstraints a5 = new GridBagConstraints();
//        a5.weightx = 0;
//        a5.weighty = 0;
//        a5.gridx = 4;
//        a5.gridy = 5;
//        a5.gridwidth = 2;
//        a5.gridheight = 1;
//        loginPanel.add(newUser, a5);
//
//        GridBagConstraints a6 = new GridBagConstraints();
//        a6.weightx = 0;
//        a6.weighty = 0;
//        a6.gridx = 7;
//        a6.gridy = 0;
//        a6.gridwidth = 1;
//        a6.gridheight = 1;
//        loginPanel.add(guest, a6);
//
//        GridBagConstraints a7 = new GridBagConstraints();
//        a7.weightx = 0;
//        a7.weighty = 0;
//        a7.gridx = 2;
//        a7.gridy = 0;
//        a7.gridwidth = 5;
//        a7.gridheight = 1;
//        loginPanel.add(info, a7);
//
//        loginPanel.revalidate();


        //Акшены

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                guestController.toLoginWindow();
            }
        });



        this.setVisible(true); //он сам вызывается метод --> paint(g)
//        this.pack();
    }






}
