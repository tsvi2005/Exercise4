public class User {
    private String userName;
    private String password;
    private String phone;
    private boolean type;

    public String toString() {
        return "User:" +
                "username='" + userName + '\'' +
                // ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", type='" + type + '\'' ;
    }

    public User(String username, String password ,String phone,boolean type){
        this.userName=username;
        this.password=password;
        this.phone=phone;
        this.type=type;
    }

    public void setUsername(String userNameame) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public boolean getType() {
        return type;
    }

    public void setType(int num) {

        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
   //* public void setPassword(String userPassword){
   //     char[] digits = {'1','2','3','4','5','6','7','8','9','0'};
     //   char[] strongLetters = {'%','$','_'};
       // for (int i = 0; i < userPassword.length(); i++) {
         //   for (int j = 0; j < ; j++) {
                
          //  }
            //if (userPassword.charAt(i)==)
        //}
//    }
}
