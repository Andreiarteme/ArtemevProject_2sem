import java.util.logging.SocketHandler;

public class LoginModel {
    private String hash;
    public void modelMethod() {
        System.out.println("Model");
    }


    public Boolean checkLoginAndPassword(String loginText, String toHash) {
        Boolean result = false;
        if (loginText.equals("admin") && toHash.equals("21232f297a57a5a743894a0e4a801fc3")){
            result = true;
        }
        return result;

    }
}
