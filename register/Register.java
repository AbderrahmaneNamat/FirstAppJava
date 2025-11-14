package register;
import login.Login;
import members.Members;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Register implements Serializable {
    static ArrayList<Members> members=new ArrayList<>();
    static String FILE_NAME="members.dat";

    static Scanner scanner = new Scanner(System.in);
    public static void registerMember(){
            String test;
            String userName;
            String password="";
            String confirmedpassword="";
            // Saisie d'un unique  Utilisateur

            do{
            System.out.println("Please Enter Unique Username");
            userName=scanner.nextLine();
                if(checkMember(userName)){
                System.out.println("User Already Exist");
            }
            }
            while(checkMember(userName));


            // Saisie d'un mode pass
            do{
                System.out.println("Please Enter Your Password");
                password=scanner.nextLine();
                System.out.println("Please Enter Your Password again");
                confirmedpassword=scanner.nextLine();
                if(!password.equals(confirmedpassword)){
                    System.out.println("Password not matched try again");
                }
            }while (!password.equals(confirmedpassword));

            // add member
            members.add(new Members(userName,password));
            try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))){
                oos.writeObject(members);
            }catch(IOException e){
                System.out.println("Something went wrong!");
            }
            System.out.println("User created Successfully");
            System.out.println("----------------");
            System.out.println("Do You Enter as exist Member click Y or Quit Click Q");
            test=scanner.nextLine();
            if(test.equalsIgnoreCase("Y")){
                Login.loginMember();
            }

        }
                // Check Member if Exist in Members (RETURN TRUE|FALSE)
        private static boolean checkMember(String username){
            for(Members mem:members){
                if(mem.getMember().equals(username)){
                    return true;
                }
            }
            return false;
        }
}
