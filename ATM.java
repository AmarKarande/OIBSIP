import java.util.*;
public class ATM {


    static Scanner sc = new Scanner(System.in);

    static HashMap<String, Integer> userBalanceMap = new HashMap<>();

    static HashMap<String, Integer> userPasswordMap = new HashMap<>();

    static ArrayList<String> historyArray = new ArrayList<>();

    public static void enterDefaultsValues() {
        userBalanceMap.put("om", 5000);
        userBalanceMap.put("aniket", 3000);
        userBalanceMap.put("nilesh", 1000);

        userPasswordMap.put("om", 123);
        userPasswordMap.put("aniket", 321);
        userPasswordMap.put("nilesh", 456);
    }


    public static void withdraw(String userName) {
        System.out.print("Enter amount you want to withdraw: ");
        int amount = sc.nextInt();

        if (userBalanceMap.get(userName) > amount) {
            int newBalance = userBalanceMap.get(userName) - amount;
            userBalanceMap.replace(userName, newBalance);
            System.out.println("₹ " + amount + " has been withdrawed from your account.\n");
            System.out.println("**********************************************************\n");
            historyArray.add("₹ "+ amount +" withdraw from your account.");
        } else {
            System.out.println("You don't have sufficient balance for withdraw " + amount + " from your account.\n");
            System.out.println("**********************************************************\n");
        }
    }

    public static void deposit(String userName) {
        System.out.print("Enter amount you want to deposit : ");
        int amount = sc.nextInt();

        int newBalance = userBalanceMap.get(userName) + amount;
        userBalanceMap.replace(userName, newBalance);
        System.out.println("₹ " + amount + " has been deposited to your account.\n");
        System.out.println("**********************************************************\n");
        historyArray.add("₹ "+ amount +" deposited in your account.");

    }


    // function for checking balance
    public static void checkBalance(String userName) {
        System.out.println("Your account balance is " + userBalanceMap.get(userName));

        System.out.println("\n**********************************************************\n");
    }
    public static void transfer(String userName) {
        System.out.print("To whoms account you want to transfer money. : ");
        String beneficiary = sc.next().toLowerCase();

        if (userBalanceMap.containsKey(beneficiary)) {
            System.out.print("Enter amount you want to tansfer to " + beneficiary+" : ");
            int transferAmount = sc.nextInt();
            System.out.println();

            if (userBalanceMap.get(userName) >= transferAmount) {
                int userNewBalance = userBalanceMap.get(userName) - transferAmount;
                int beneficiaryNewBalance = userBalanceMap.get(beneficiary) + transferAmount;

                userBalanceMap.replace(beneficiary, beneficiaryNewBalance);
                userBalanceMap.replace(userName, userNewBalance);
                System.out.println("Money transfer has been done successfully.");
                historyArray.add("₹ "+ transferAmount +" tranfered to "+ beneficiary);
                System.out.println("\n**********************************************************\n");
            } else {
                System.out.println("You don't have enough balance to tansfer money.\n");
                System.out.println("**********************************************************\n");
            }
        } else {
            System.out.println(beneficiary + "don't have account in our bank.\n");
            System.out.println("**********************************************************\n");
        }
    }
    public static void showHistory(){
        for(int i=0; i<historyArray.size() ; i++){
            System.out.println(historyArray.get(i));
        }
    }

    public static void main(String[] args) {
        enterDefaultsValues();

        int passwordAttempt = 0;
        boolean isLogedin = false;
        boolean flag = true;

        System.out.print("Enter your username : ");
        String userName = sc.next().toLowerCase();

        if (userPasswordMap.containsKey(userName)) {

            // while loop for password if password is wrong for 3 time then atm will be blocked
            while (passwordAttempt < 3) {
                System.out.print("Please enter your ATM pin : ");
                int atmPin = sc.nextInt();
                System.out.println("\n**********************************************************\n");

                if (userPasswordMap.get(userName) == atmPin) {
                    isLogedin = true;
                    break;
                } else {
                    System.out.println("Wrong password");
                    passwordAttempt += 1;
                }
                if (passwordAttempt == 3) {
                    System.out.println("Your ATM has been Blocked for one day.");
                }
            }

        } else {
            System.out.println("You do not have back account in our bank");
        }

        if (isLogedin) {

            while (flag) {
                System.out.println("Press 1. for Transactions History");
                System.out.println("Press 2. for Withdraw");
                System.out.println("Press 3. for Deposit");
                System.out.println("Press 4. for Transfer");
                System.out.println("Press 5. to Check Your Bank Balance");
                System.out.println("Press 6. for Quit");
                System.out.println("\n**********************************************************\n");
                System.out.print("Enter your choice: ");

                int choice = sc.nextInt();
                System.out.println("\n**********************************************************\n");

                switch (choice) {
                    case 1:
                        showHistory();
                        break;
                    case 2:
                        withdraw(userName);
                        break;
                    case 3:
                        deposit(userName);
                        break;
                    case 4:
                        transfer(userName);
                        break;
                    case 5:
                        checkBalance(userName);
                        break;
                    case 6:
                        flag = false;
                        break;
                    default:
                        System.out.println("Please enter valid option.");
                        break;
                }
            }
        }
}
}
