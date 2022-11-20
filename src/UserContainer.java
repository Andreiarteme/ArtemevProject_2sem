import java.util.ArrayList;

public class UserContainer {
    private ArrayList<User> users;


    public UserContainer(){
        users = new ArrayList<User>();
    }

    public void add(User user){
        users.add(user);
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public boolean userExists(String login){
        for ( User user: users) {
            if (user.getLogin().equals(login)){
                return false;
            }
        }
        return true;
    }


    public boolean check(String loginText, String toHash) {
        for (User user : users) {
            if (user.getLogin().equals(loginText) && user.getHashPassword().equals(toHash)) {
                System.out.println(user.getRole()+"cont");
                return true;
            }
        }
        return false;
    }

    public User getUser(String login) throws Exception {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                System.out.println(user.getRole()+"cont");
                return user;
            }
        }
        throw new Exception();

    }

    public boolean checkLogins(String login, String login1) {
        for (User user : users) {
            if (user.getLogin().equals(login1) && !login.equals(login1)) {
                System.out.println(user.getRole()+"cont");
                return true;
            }
        }
        return false;


    }
}
