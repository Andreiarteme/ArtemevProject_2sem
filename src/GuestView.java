import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuestView extends JFrame {
    private GuestController guestController;
    public static final int weidth = 800;
    public static final int height = 600;

    private JButton exit;


    public GuestView(GuestController guestController){
        this.guestController = guestController;
        start();
    }

    public void start(){
       this.setSize(weidth+10, height+10);
        this.setTitle("Цитаты преподавателей");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        //местоположение окна
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        this.setBounds(dimension.width/2-weidth/2,dimension.height/2-height/2,weidth,height);

        //добавление на панель

        GuestTableModel tableModel = new GuestTableModel(guestController.getDataBase().getQuotes().getArQuotes());
        JTable table = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setPreferredSize(new Dimension(800,600));
        add(tableScroll, new GridBagConstraints(0,0,3,1,1,1,
                GridBagConstraints.NORTH,GridBagConstraints.BOTH,
                new Insets(1,1,1,1),0,0));

        exit = new JButton("Назад");
        add(exit, new GridBagConstraints(0,1,1,1,1,1,
                GridBagConstraints.SOUTH,GridBagConstraints.HORIZONTAL,
                new Insets(1,1,1,1),0,0));

        //Акшены
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                guestController.toLoginWindow();
            }
        });



        this.setVisible(true);

    }






}
