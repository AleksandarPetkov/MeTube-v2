package domain.models.binding;

public class UserBindingModel {

    private String username;

    private String password;

    private String confirmpawword;

    private String email;

    public UserBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmpawword() {
        return confirmpawword;
    }

    public void setConfirmpawword(String confirmpawword) {
        this.confirmpawword = confirmpawword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
