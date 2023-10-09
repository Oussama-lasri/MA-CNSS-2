package ma.MaCNSS.Entities;

public class Societe {
    private int id ;
    private String label ;
    private String email ;
    private String password ;

    public Societe() {
    }

    public Societe(int id, String label, String email, String password) {
        this.id = id;
        this.label = label;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
