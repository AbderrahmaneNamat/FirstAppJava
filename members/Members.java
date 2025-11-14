package members;

public  class Members  {
    private String userName;
    private String password;
    public Members(String userName,String password){
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
