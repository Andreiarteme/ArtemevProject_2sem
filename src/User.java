public class User {
    private String login;
    private String hashPassword;
    private String role;

    public User(String login, String hashPassword, String role) {
        this.login = login;
        this.hashPassword = hashPassword;
        this.role = role;

    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getHashPassword() {
        return hashPassword;
    }
    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }
}
