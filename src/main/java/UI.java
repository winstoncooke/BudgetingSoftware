import java.util.Scanner;

public class UI {
    private Scanner scanner;
    private ChartOfAccounts chartOfAccounts;
    private String user;

    public UI() {
        this.scanner = new Scanner(System.in);
        this.chartOfAccounts = new ChartOfAccounts();
        this.user = "Winston"; // Add user account support in a future release
    }

    public void start() {
        System.out.println("Welcome, " + user + ".");
        mainMenu();
    }

    public void mainMenu() {
        loop: while (true) {
//             Main menu options
            System.out.println("\n* Main Menu *");
            System.out.println("Select from the following numbered options:");
            System.out.println("1. Create a new account");
            System.out.println("2. View Chart of Accounts");
            System.out.println("3. Input a transaction");
            System.out.println("4. Delete an account");
            System.out.println("0. Log out");

//             Input user selection
            System.out.print("\n> ");
            int input = Integer.parseInt(scanner.nextLine());
            System.out.println();
            switch (input) {
                case 0 -> {
                    System.out.println("You have been logged out. Thank you for your patronage.");
                    break loop;
                }
                case 1 -> createAccount();
                case 2 -> displayChartOfAccounts();
                case 3 -> inputTransaction();
                case 4 -> deleteAccount();
                default -> System.out.println("ERROR: Invalid selection");
            }
        }
    }

    public void createAccount() {
        System.out.println("Enter the name for the new account: ");
        String name = scanner.nextLine();

//         Check that user chooses one of the three account types recognized by the system
        String type = "";
        boolean validType = false;
        while (!validType) {
            System.out.println("\nSelect a numbered option for your desired account type:");
            System.out.println("1. Asset");
            System.out.println("2. Liability");
            System.out.println("3. Equity");
            System.out.print("\n> ");
            int input = Integer.parseInt(scanner.nextLine());

            if (input == 1) {
                type = "Asset";
            } else if (input == 2) {
                type = "Liability";
            } else if (input == 3) {
                type = "Equity";
            } else {
                System.out.println("\nERROR: Please choose a valid account type\n");
            }

            if (type.equals("Asset") ||
                type.equals("Liability") ||
                type.equals("Equity")) {
                chartOfAccounts.add(name, type);
                validType = true;
            }
        }
    }

    public void displayChartOfAccounts() {
        if (chartOfAccounts.accountList().size() > 0) {
            chartOfAccounts.printChartOfAccounts();
        } else {
            System.out.println("No accounts have been added to the Chart of Accounts");
        }
    }

    public void inputTransaction() {
        if (chartOfAccounts.accountList().size() < 1) {
            System.out.println("ERROR: No accounts exist");
        } else if (chartOfAccounts.accountList().size() < 2) {
            System.out.println("ERROR: There must be at least two accounts to input a transaction.");
        } else if (chartOfAccounts.accountList().size() >= 2) {
            System.out.println("Enter an account number (Enter 0 to return).");
            int firstAccount = Integer.parseInt(scanner.nextLine());
            boolean validAccount = false;

            while (!validAccount) {
//                Break out of loop if 0 is entered
                if (firstAccount == 0) {
                    break;
                }

                if (firstAccount != chartOfAccounts.getAccountNumber(firstAccount)) {
                    System.out.println("\nERROR: Account not found");
                    break;
                }

                System.out.println("\nEnter a second account number (Enter 0 to return).");
                int secondAccount = Integer.parseInt(scanner.nextLine());

//                Break out of loop if 0 is entered
                if (secondAccount == 0) {
                    break;
                }

                if (secondAccount != chartOfAccounts.getAccountNumber(secondAccount)) {
                    System.out.println("\nERROR: Account not found");
                    break;
                }

//                Check for account number and then prompt user to update the balance if account number is valid
                if (firstAccount == chartOfAccounts.getAccountNumber(firstAccount) &&
                    secondAccount == chartOfAccounts.getAccountNumber(secondAccount)) {
                    System.out.println();
                    chartOfAccounts.printFormattedAccount(firstAccount);
                    chartOfAccounts.printFormattedAccount(secondAccount);
                    System.out.println("\nEnter an amount to add or subtract:");
                    System.out.print("\n< ");
                    double updateAmountInput = Double.parseDouble(scanner.nextLine());
                    chartOfAccounts.doubleEntry(firstAccount, secondAccount, updateAmountInput);
                    validAccount = true;
                } else {
                    System.out.println("\nERROR: Accounts not found");
                    break;
                }
            }
        } else {
            System.out.println("ERROR");
        }
    }

    public void deleteAccount() {
        if (chartOfAccounts.accountList().size() < 1) {
            System.out.println("ERROR: No accounts exist");
        } else {
            System.out.println("Enter an account number (Enter 0 to return).");
            int account = Integer.parseInt(scanner.nextLine());
            boolean validAccount = false;

            while (!validAccount) {
//                Break out of loop if 0 is entered
                if (account == 0) {
                    break;
                }

                if (account == chartOfAccounts.getAccountNumber(account)) {
                    chartOfAccounts.remove(account);
                    validAccount = true;
                } else {
                    System.out.println("\nERROR: Account not found");
                    break;
                }
            }
        }
    }
}
