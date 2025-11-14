import java.io.Serializable;

class Members implements Serializable {
    private String userName;
    private String password;
    Members(String userName,String password){
        this.userName=userName;
        this.password=password;
    }
    public String  getMember(){
        return this.userName;
    }
    public String  getMemberPass(){
        return this.password;
    }
}
