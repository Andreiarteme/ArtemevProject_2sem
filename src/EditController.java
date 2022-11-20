import javax.security.auth.login.LoginException;
import javax.swing.*;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;


public class EditController {

    private DataBase dataBase;
    private User user;
//    private LoginModel loginModel;

    public EditController(/*LoginModel loginModel*/ DataBase dataBase, User user) {
//        this.loginModel = loginModel;
        this.dataBase = dataBase;
        this.user = user;
    }

    public boolean edition(String id, String quore, String teacher, String subject, String data, String userId, JLabel info) throws LoginException, NoSuchAlgorithmException, SQLException, ClassNotFoundException {
        if (!quore.equals("")){
            return editQuote(id, quore,teacher, subject, data, userId, info);

        }else{
            info.setText("Заполните поля!");
            return false;
        }


    }

    private boolean editQuote(String id, String quore, String teacher, String subject,String data,String userId, JLabel info) throws SQLException, ClassNotFoundException {
//        DataBase dataBase = new DataBase();
        System.out.println("editQuote from EdittController");
            Quote quote = new Quote(id,quore,  teacher,  subject,  data,  userId);//по умолчанию роль user
            dataBase.edit2Quote(quote);
            info.setText("Запись успешно изменена!");
            goBack();
            return true;
    }


    public void goBack() {
        System.out.println("EditController goBack");
//        DataBase dataBase = new DataBase();
        //добавить кейсы
        switch (Integer.parseInt(user.getRole())){
            case 1:
                AdminController adminController = new AdminController(dataBase, user);
                AdminView adminView = new AdminView(adminController);
                break;
            case 2:
                UserController userController = new UserController(dataBase, user);
                UserView userView = new UserView(userController);
                break;
            case 3:
                VerifierController verifierontroller = new VerifierController(dataBase, user);
                VerifierView verifierView = new VerifierView(verifierontroller);
                break;
        }
//        AdminController adminController = new AdminController(dataBase, user);
//        AdminView adminView = new AdminView(adminController);
    }

//    public Boolean edit2(String quore1, String teacher1, String subject1, String data1, String userId1, JLabel info) {
//
//
//    }
}