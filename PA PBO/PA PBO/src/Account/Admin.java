package Account;

public class Admin {
    private String IdAdmin;
    private String Password;

    public Admin(String IdAdmin, String Password) {
        this.IdAdmin = IdAdmin;
        this.Password = Password;
    }

    public String getIdAdmin() {
        return IdAdmin;
    }

    public void setIdAdmin(String idAdmin) {
        this.IdAdmin = idAdmin;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }
}

