import java.util.Scanner;

public class UI {
    private Scanner scanner;
    private ChartOfAccounts chartOfAccounts;

    public UI() {
        this.scanner = new Scanner(System.in);
        this.chartOfAccounts = new ChartOfAccounts();
    }

    public void start() {
        String user = "Winston"; // Add user accounts in future update
        System.out.println("Welcome, " + user + ".");

        loop: while (true) {
//             Main menu options
            System.out.println("\nPlease select from the following options:");
            System.out.println("1. Create a new account");
            System.out.println("2. View Chart of Accounts");
            System.out.println("3. Input a transaction");
            System.out.println("0. Log out");

//             Input user selection
            System.out.print("\nSelection: ");
            int input = Integer.parseInt(scanner.nextLine());
            System.out.println();
            switch (input) {
                case 0 -> {
                    System.out.println("You have been logged out. Thank you for your patronage.");
                    break loop;
                }
                case 1 -> newAccount();
                case 2 -> displayChartOfAccounts();
//                case 3 -> inputTransaction();
                default -> System.out.println("ERROR: Invalid selection");
            }
        }
    }

    public void newAccount() {
        System.out.println("Enter the name for the new account: ");
        String name = scanner.nextLine();

//         Check that user chooses one of the three account types recognized by the system
        String type = "";
        boolean validType = false;
        while (!validType) {
            System.out.println("Enter the account type (Asset, Liability, Equity): ");
            type = scanner.nextLine();

            if (type.equals("Asset") ||
                type.equals("Liability") ||
                type.equals("Equity")) {
                chartOfAccounts.add(name, type);
                validType = true;
            } else {
                System.out.println("\nERROR: Please enter a valid account type\n");
            }
        }
        System.out.println("Account created: " + name + " (" + type + ")");
    }

    public void displayChartOfAccounts() {
        if (chartOfAccounts.accountList().size() > 0) {
            chartOfAccounts.printChartOfAccounts();
        } else {
            System.out.println("No accounts have been added to the Chart of Accounts");
        }
    }
}
