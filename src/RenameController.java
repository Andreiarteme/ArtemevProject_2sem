import javax.security.auth.login.LoginException;
import javax.swing.*;
import javax.swing.text.StyledEditorKit;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;


public class RenameController {

    private DataBase dataBase;
    private User user;

    public RenameController(DataBase dataBase, User user) {
        this.dataBase = dataBase;
        this.user = user;
    }

    public boolean rename(String login, String password, String group, JLabel info){
        if (!(login.equals("")||password.equals("")||group.equals(""))){
            boolean b = dataBase.getUsers().checkLogins(user.getLogin(), login);
            if (!b){
                dataBase.rename(user.getId(),login,password,group);
                goBack();
                return true;
            }else{
                info.setText("Такой логин занят");
                return false;
            }

        }else{
            info.setText("Заполните все данные!");
            return false;
        }


    }

    private boolean editQuote(String id, String quore, String teacher, String subject,String data,String userId, JLabel info) throws SQLException, ClassNotFoundException {
        System.out.println("editQuote from EdittController");
        Quote quote = new Quote(id,quore,  teacher,  subject,  data,  userId);//по умолчанию роль user
        dataBase.edit2Quote(quote);
        info.setText("Запись успешно изменена!");
        goBack();
        return true;
    }


    public void goBack() {
        System.out.println("EditController goBack");
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

    }

}