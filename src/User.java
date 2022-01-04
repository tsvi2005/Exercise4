public class User {
    private String username;
    private String password;
    private String phone;
    private boolean regular;

    public String toString() {
        return username + " " + phone + "(" + type(regular) + ")";

    }
    public String type (boolean regular){
        String type = "Real estate broker";
        if (regular){
            type = "Regular user";
        }
        return  type;
    }

    public User(String username, String password ,String phone,boolean type){
        this.username=username;
        this.password=password;
        this.phone=phone;
        this.regular =type;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public boolean getRegular() {
        return regular;
    }

    public void setRegular(boolean regular) {
        this.regular = regular;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}