import java.util.ArrayList;

public class UserContainer {
    private ArrayList<User> users;


    public UserContainer(){
        users = new ArrayList<User>();
    }

    public void add(User user){
        users.add(user);
    }

    public boolean userExists(String login){
        for ( User user: users) {
            if (user.getLogin().equals(login)){
                return false;
            }
        }
        return true;
    }
//    public boolean check(){
//
//
//
//        return true;
//    }

    public String check(String loginText, String toHash) {
        for (User user : users) {
            if (user.getLogin().equals(loginText) && user.getHashPassword().equals(toHash)) {
                System.out.println(user.getRole()+"cont");
                return user.getRole();
            }
        }
        return "bad";
    }
}
