import javax.security.auth.login.LoginException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginController {

    private DataBase dataBase;
//    private LoginModel loginModel;

    public LoginController(/*LoginModel loginModel*/DataBase dataBase) {
        this.dataBase = dataBase;
//        this.loginModel = loginModel;
    }

//    public String toHash (String str) throws LoginException, NoSuchAlgorithmException {
//        MessageDigest shall = MessageDigest.getInstance("SHA-1");
//        MessageDigest md5 = MessageDigest.getInstance("MD5");
//        byte[] bytes = md5.digest(str.getBytes());
//        StringBuilder builder = new StringBuilder();
//        for (byte b : bytes)
//            builder.append(String.format("%02X ", b));
//        String hash = builder.toString().replaceAll(" ","").toLowerCase();
//        return hash;
//    }
    public void registration(){
        RegistrationController  registrationController = new RegistrationController(/*loginModel*/dataBase);
        RegistrationView registrationView = new RegistrationView(registrationController);


    }

    public Boolean is_Empty(String loginText, String passwordText) {
        Boolean result = false;
        if (!loginText.trim().equals("") && !passwordText.trim().equals("")){
            result = true;
        }
            return result;

    }

        public String toHash (String str) throws LoginException, NoSuchAlgorithmException {
        MessageDigest shall = MessageDigest.getInstance("SHA-1");
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] bytes = md5.digest(str.getBytes());
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes)
            builder.append(String.format("%02X ", b));
        String hash = builder.toString().replaceAll(" ","").toLowerCase();
        return hash;
    }

    public boolean checkLoginAndPassword(String loginText, String passwordText) throws LoginException, NoSuchAlgorithmException {
       boolean str = dataBase.checkLoginAndPassword(loginText,toHash(passwordText));
        System.out.println(str + "logController");
        return str;
       // return dataBase.checkLoginAndPassword(loginText,toHash(passwordText));//сделать чтоб возвращал роль
    }
//    public String getUserId(String loginText) throws LoginException, NoSuchAlgorithmException {
//        String userId = dataBase.getUserId(loginText);
//        System.out.println(userId + "logController");
//        return userId;
//        // return dataBase.checkLoginAndPassword(loginText,toHash(passwordText));//сделать чтоб возвращал роль
//    }


    //вместо role добавить login
    public void goToView(String login) throws Exception {
        //направляет в соответсвии с ролью

        //добавить метод, чтобы давал user черех логин
        User user = dataBase.getUser(login);
        //user.getRole();

        if (user.getRole().equals("1")){
            //admin
            AdminController adminController = new AdminController(dataBase, user);
            AdminView adminView = new AdminView(adminController);
        } else if(user.getRole().equals("2")){
            //user
            UserController userController = new UserController(dataBase, user);
            UserView userView = new UserView(userController);
        } else if(user.getRole().equals("3")) {
            //verificator
            VerifierController verifierController = new VerifierController(dataBase, user);
            VerifierView verifierView = new VerifierView(verifierController);
        } else{
//            //guest
//            GuestController guestController = new GuestController(dataBase);
//            GuestView guestView = new GuestView(guestController);
            System.out.println("Error такого юзера нет");
        }
    }


    public void goToView() {
        //guest
            GuestController guestController = new GuestController(dataBase);
            GuestView guestView = new GuestView(guestController);
    }
}
