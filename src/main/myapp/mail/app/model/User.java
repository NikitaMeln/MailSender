package mail.app.model;

public class User {
    private String loginMail;
    private String password;

    public User() {
    }

    public User(String loginMail, String password) {
        this.loginMail = loginMail;
        this.password = password;
    }

    public String getLoginMail() {
        return loginMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLoginMail(String loginMail) {
        this.loginMail = loginMail;
    }
}
