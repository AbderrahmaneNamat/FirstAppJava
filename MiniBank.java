import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.classfile.instruction.ThrowInstruction;
import java.lang.reflect.Array;
import java.lang.reflect.Member;
import java.time.chrono.IsoChronology;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import login.Login;
import members.Members;
import register.Register;

class Main {
        static Scanner scanner=new Scanner(System.in);
        static ArrayList<Members> members=new ArrayList<>();
        static ArrayList<Admin> admins=new ArrayList<>();
        static String FILE_NAME="members.dat";

    public static void main(String[] args) {
    //    loadMembers(members);
        int choice=0;
        while (choice!=4) {
            System.out.println("========MINI BANK SYSTEM=========");
            System.out.println("1-Enter As New  Member");
            System.out.println("2-Enter As Existing Member");
            System.out.println("3-Enter As Admin");
            System.out.println("4-Exit");
            System.out.println("=====================================");
            System.out.print("Enter  Your Choice:");
            try{
                choice=scanner.nextInt();
                scanner.nextLine();
            }catch(InputMismatchException e){
                System.out.println("Try to Enter Number");
                continue;
            }
            
            switch (choice) {
                case 1->Register.registerMember();
                case 2->Login.loginMember();
                case 3->loginAdmin();
                case 4->System.out.println("See You Later");
                default->System.out.println("Numer not found");
            } 
        }

    }
    //=======================SECTION REGISTER MEMBER=========================
 
        //===================================END REGISTER MEMBER SECTION====================================================================

        //===================================START LOGIN MEMBER SECTION====================================================================


        // Check Member if Exist in Members (RETURN TRUE|FALSE)
        

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
                        case 4-> System.out.println("Good Bye !!");
                        default->System.out.println("Number Out of Range");
                    }
                }
            }
        }
        private static void seeAllMembers(){
            ArrayList<Members> members=loadMembers();
            if (members == null || members.isEmpty()){
                System.out.println("Aucune Utilisateur est enregistrer");
            }
            int i=1;
            for(Members mem:members){
                System.out.printf("%d - UserName: %s ; Password: %s \n",i,mem.getMember(),mem.getMemberPass());
                i++;
            }
        }
        //======== PART OF MEMBER DETAILS ( DEPOSIT,WITHDRAW) ===========

        private static int checkIntegerInputValidation(){
            while(true){
                try{
                    int val=scanner.nextInt();
                    scanner.nextLine();
                    return val;
                }catch(InputMismatchException e){
                    System.out.println("Make Sure To Enter Integer Number");
                    scanner.nextLine();
                }
            }

        }
        private static void adminCheckMember(){
            members=loadMembers();
            System.out.println("Enter name:");
            String username=scanner.nextLine();
            int i=0;
            for(Members mem:members){
                i++;
                if(username.equals(mem.getMember())){
                    System.out.println("User Exist With the Following info");
                    System.out.printf("%d - UserName: %s ; Password: %s \n",i,mem.getMember(),mem.getMemberPass());
                }else{
                    System.out.println("User Not founded");
                }
            }    
        }
    private static void deleteuser() {
        members = loadMembers();

        System.out.print("Enter username to delete: ");
        String username = scanner.nextLine();

         boolean removed = members.removeIf(m -> m.getMember().equalsIgnoreCase(username));

         if (removed) {
             System.out.println("User Deleted Successfully.");
        } else {
            System.out.println("User Not Found.");
        }

        usersave(members);
    }
        private static void usersave(ArrayList<Members> members){
            try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(FILE_NAME));){
                oos.writeObject(members);
            }catch(IOException e){
                System.out.println("File Not Saved");
            }
               
        }
        @SuppressWarnings("unchecked")
        public static ArrayList<Members> loadMembers(){
            try (ObjectInputStream ois=new ObjectInputStream(new FileInputStream(FILE_NAME))) {
                return (ArrayList<Members>) ois.readObject();
            } catch (Exception e) {
                    System.out.println("Members Not  Loaded");
                    return new ArrayList<>();
            }
        }
        
}
