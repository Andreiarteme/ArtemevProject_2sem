public class User {
    private String id;
    private String login;
    private String hashPassword;
    private String role;
    private String group;



    public User(String id, String login, String hashPassword, String role, String group) {
        this.id = id;
        this.login = login;
        this.hashPassword = hashPassword;
        this.role = role;
        this.group = group;

    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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

    public String getGroup() {
        return group;
    }
    public void setGroup(String group) {
        this.group = group;
    }
}
