import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.classfile.instruction.ThrowInstruction;
import java.time.chrono.IsoChronology;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class Main {
        static Scanner scanner=new Scanner(System.in);
        static ArrayList<Members> members=new ArrayList<>();
        static ArrayList<Admin> admins=new ArrayList<>();
        static String FILE_NAME="members.dat";

    public static void main(String[] args) {
        int choice=0;
        while (choice!=4) {
            System.out.println("========MINI BANK SYSTEM=========");
            System.out.println("1-Enter As New  Member");  //register
            System.out.println("2-Enter As Existing Member"); //login
            System.out.println("3-Enter As Admin");//admin
            System.out.println("4-Exit");
            System.out.println("==================================");
            System.out.print("Enter  Your Choice:");
            try{
                choice=scanner.nextInt();
                scanner.nextLine();
            }catch(InputMismatchException e){
                System.out.println("Try to Enter Number");
                continue;
            }
            
            switch (choice) {
                case 1->registerMember();
                case 2->loginMember();
                case 3->loginAdmin();
                case 4->System.out.println("See You Later");
                default->System.out.println("Numer not found");
            } 
        }

    }
    //=======================SECTION REGISTER MEMBER=========================
    private static void registerMember(){
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
                detailsMember();
            }

        }
        //===================================END REGISTER MEMBER SECTION====================================================================

        //===================================START LOGIN MEMBER SECTION====================================================================

        // Check Member if Exist in Members (RETURN TRUE|FALSE)
        private static boolean checkMember(String username){
            for(Members mem:members){
                if(mem.getMember().equals(username)){
                    return true;
                }
            }
            return false;
        }
        // Check Member if Exist in Members (RETURN TRUE|FALSE)
        private static void loginMember(){
                String username="";
                String password="";
                boolean login =false;
                String tryLogin="no";
                System.out.println("Enter Your username");
                username=scanner.nextLine();
                System.out.println("Enter Your password");
                password=scanner.nextLine();
                while (!login) {
                    System.out.println(login);
                    for(Members mem:members){
                        if(mem.getMember().equals(username) && mem.getMemberPass().equals(password)){
                            login = true;
                            detailsMember();
                        }
                            System.out.println("User or password is Wrong");
                            System.out.println("You want try again or Exit:(yes/no)");
                            tryLogin=scanner.nextLine();
                            if(tryLogin=="yes"){
                                loginMember();
                            }
                            else{
                                break;
                            }
                    
                    }
                }

                
        }
        // AVAILABLE OPTIONS FOR MEMBER (DEPOSIT|WITHDRAW)
        private static void detailsMember(){
            
            int balance =10;
            int ChoiceMember=0;
            while(ChoiceMember!=4){
                System.out.println("Enter Your Choice:");
                ChoiceMember=scanner.nextInt();
                scanner.nextLine();
                System.out.printf("Your Balance is:$ %d \n",balance);
                System.out.println("1-Deposit ");
                System.out.println("2-Withdraw ");
                System.out.println("4-Exit ");
                switch (ChoiceMember) {
                    case 1->deposit(balance);
                    case 2 ->withdraw(balance);
                    case 4 -> System.out.println("See You Next Session");
                
                    default->System.out.println("something went Wrong!!");
                }
            }



        }
        //Deposit Function
        private static void deposit(int balance){
            int amount=0;
            System.out.println("Enter the amount You Want Deposit: ");
            amount=scanner.nextInt();
            scanner.nextLine();
            balance+=amount;
            System.out.println("Your Balance Now Is: $" + balance);
            
        }
        // Withdraw Function
        private static void withdraw(int balance){
            int withdraw=0;
            System.out.println("Enter The amount You want to withdraw");
            withdraw=scanner.nextInt();
            scanner.nextLine();
            if(balance<=0 || balance < withdraw){
                System.out.println("You dont have enough money to withdraw");
            }
            else{
            balance-=withdraw;
            System.out.println("Successfully withdrawn $" + withdraw + ".");
            System.out.println("Your Account have Now $" + balance + ".");
            }
        }
        //===================================END EXIST MEMBER SECTION====================================================================
        // LOGIN & AVAILABLE OPTIONS FOR ADMIN (ADD |DELETE MEMBER)
        private static void loginAdmin(){
            int choiceAdmin=0; 
            String inputAdminPass="";
            String inputAdmin="";
            System.out.println("Enter Username:");
            inputAdmin=scanner.nextLine();
            System.out.println("Enter Password:");
             inputAdminPass=scanner.nextLine();
            if(inputAdmin.equals("admin")&& inputAdminPass.equals("admin")){
                while(choiceAdmin!=4) {
                    System.out.println("1-See all Members");
                    System.out.println("2-check Member ");
                    System.out.println("3-delete Members");
                    System.out.println("4-Quit");
                    System.out.println("======================");
                    System.out.println("Enter Your choice:");
                    choiceAdmin=scanner.nextInt();
                    scanner.nextLine();
                    switch (choiceAdmin) {
                        case 1-> seeAllMembers();
                        case 2-> adminCheckMember();
                    }
                }
            }
        }
        private static void seeAllMembers(){
            // for(Members mem:members){
            //     System.out.println(mem.getMembers());
            // }
            int i=1;
            for(Members mem:members){
                System.out.printf("%d - UserName: %s: \n",i,mem.getMember());
                i++;
            }
        }
        //======== PART OF MEMBER DETAILS ( DEPOSIT,WITHDRAW)=========

        private static void checkIntegerInputValidation(int val){
            try{
                val=scanner.nextInt();
                scanner.nextLine();
            }catch(InputMismatchException e){
                System.out.println("Make Sure To Enter Integer Number");
            }
        }
        private static void adminCheckMember(){
            System.out.println("lol");
        }
        @SuppressWarnings("unchecked")
        static void loadMembers(){
            try (ObjectInputStream ois=new ObjectInputStream(new FileInputStream(FILE_NAME))) {
                    members= (ArrayList<Members>) ois.readObject();
            } catch (Exception e) {
                System.out.println("Data Not loaded ");
            }
        }
        
}
