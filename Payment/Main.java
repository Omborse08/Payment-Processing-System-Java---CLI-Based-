package Payment;
import java.util.*;
class Main_Cli{
    static void showMenu() {
        System.out.println("\n1.CreateUser\n2.SendMoney\n3.CheckBalance\n4.History\n5.Recover\n6.Exit");
    }
    static void main(String[] args) {
        ledgerService ledgerService = new ledgerService();
        paymentRepository p = new paymentRepository();
        LockAccount lockAccount = new LockAccount();

        paymentService paymentService = new paymentService(p,lockAccount,ledgerService);
        RecoverClass recoverClass = new RecoverClass(ledgerService,p);

        user_repo user_repo = new user_repo();
        int PAY_ID = 201;
        int USER_ID = 101;
        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning) {
            showMenu();
            System.out.print("Choose Option: ");
            int choose;
            try {
                choose = sc.nextInt();
            }
            catch (Exception e) {
                System.out.println("Wrong Input!");
                return;
            }
            sc.nextLine();
            switch (choose) {
                case 1:
                    int amount;
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Initial Balance: ");
                    try {
                        amount = sc.nextInt();
                    } catch (Exception e) {
                        System.out.println("Wrong input!");
                        return;
                    }
                    user user = new user(USER_ID,name);
                    user_repo.addUser(user);
                    System.out.println("User Created Successfully!");
                    System.out.println("User ID: "+USER_ID);
                    ledgeEntry ledgeEntry = new ledgeEntry(USER_ID,entryType.CREDIT,amount);
                    ledgerService.addEntry(ledgeEntry);
                    USER_ID++;
                    break;

                case 2:
                    System.out.print("Enter Senders ID: ");
                    int sendID = sc.nextInt();
                    System.out.print("Enter Receivers ID: ");
                    int receiversID = sc.nextInt();
                    System.out.print("Enter Amount: ");
                    int amounta = sc.nextInt();
                    if (paymentService.sendMoney(sendID,receiversID,amounta,PAY_ID)) {
                        System.out.println("Transaction Has Been Confiermed!");
                    }
                    else {
                        System.out.println("Something is Wrong!, Try Again Later!");
                    }
                    PAY_ID++;
                    break;

                case 3:
                    int id;
                    System.out.print("Enter User ID: ");
                    try {
                        id = sc.nextInt();
                    } catch (Exception e) {
                        System.out.println("Wrong Input!");
                        return;
                    }
                    System.out.print("Total Balance: ");
                    System.out.println(ledgerService.getBalance(id));
                    break;

                case 4:
                    System.out.print("Enter User ID: ");
                    int ids = sc.nextInt();
                    ledgerService.history(ids);
                    break;


                case 5:
                    System.out.println("Recovering All Failed Transaction!");
                    recoverClass.recoverTransactions();
                    break;

                case 6:
                    System.out.println("Thank You Bye!");
                    isRunning = false;
                    break;
            }
        }
    }
}
