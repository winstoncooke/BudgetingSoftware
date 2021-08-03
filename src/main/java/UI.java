import java.util.Scanner;

public class UI {
    private Scanner scanner;

    public UI() {
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome.");
        System.out.println();

        loop: while (true) {
            // Main menu options
            System.out.println("Please select from the following options:");
            System.out.println("1. Create a new account");
            System.out.println("2. View chart of accounts");
            System.out.println("3. Input a transaction");
            System.out.println("4. Log out");

            // Input user selection
            System.out.print("Selection: ");
            int input = Integer.parseInt(scanner.nextLine());
            switch (input) {
                case 1:
                    newAccount();
                    break;
                // case 2:
                    // chartOfAccounts();
                    // break;
                // case 3:
                    // inputTransaction();
                    // break;
                case 4:
                    System.out.println("You have been logged out. Thank you for your patronage.");
                    break loop;
                default:
                    System.out.println("Invalid selection");
                    break;
            }
        }
    }

    public void newAccount() {
        System.out.println("Enter the name for the new account: ");
        String name = scanner.nextLine();
        String type = "";

        // Check that user chooses one of the three account types recognized by the system
        boolean validType = false;
        while (!validType) {
            System.out.println("Enter the account type (Asset, Liability, Equity): ");
            type = scanner.nextLine();

            if (type.equals("Asset") ||
                type.equals("Liability") ||
                type.equals("Equity")) {
                validType = true;
            } else {
                System.out.println("Please enter a valid account type");
            }
        }
        Account account = new Account(name, type);
        System.out.println("Account" + name + " (" + type + ") " + "created");
    }
}
