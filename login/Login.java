package login;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.ObjectInputStream;
import members.Members;
public  class Login{
static ArrayList<Members>  members=new ArrayList<>();
static String FILE_NAME="members.dat";
       static Scanner scanner=new Scanner(System.in);
    public static  void loginMember(){
        String username="";
        String password="";
        boolean login =false;
        String tryLogin="no";
        do{
        System.out.println("===========Login Escpace============");
        System.out.print("Enter Username: ");
        username=scanner.nextLine();
        System.out.print("Enter Password: ");
        password=scanner.nextLine();
            for(Members mem:members){
                if(mem.getMember().equals(username) && mem.getMemberPass().equals(password)){
                login = true;
                detailsMember();
                }
            }
            if(!login){
                System.out.println("User or password is Wrong");
                System.out.println("You want try again or Exit:(yes/no)");
                tryLogin=scanner.nextLine();
            }
            }while(!login && tryLogin.equalsIgnoreCase("yes"));
        }
        // AVAILABLE OPTIONS FOR MEMBER (DEPOSIT|WITHDRAW)
        public static void detailsMember(){
            
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
     private static void deposit(int balance){
        int amount=0;
        System.out.println("Enter the amount You Want Deposit: ");
        amount=scanner.nextInt();
        scanner.nextLine();
        balance+=amount;
        System.out.println("Your Balance Now Is: $" + balance);
    }
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
 
}