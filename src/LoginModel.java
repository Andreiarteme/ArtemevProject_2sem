//import javax.security.auth.login.LoginException;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.logging.SocketHandler;
//
//public class LoginModel {
//    private String hash;
//    public void modelMethod() {
//        System.out.println("Model");
//    }
//
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
//
//
//    public Boolean checkLoginAndPassword(String loginText, String toHash) {
//        Boolean result = false;
//        if (loginText.equals("admin") && toHash.equals("21232f297a57a5a743894a0e4a801fc3")){
//            result = true;
//        }
//        return result;
//
//    }
//
//}
