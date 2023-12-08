import java.util.Scanner;

/**
 * Class for interaction between user and bank
 */
public class InteractionBank extends BankSystem {

    /**
     * The main method for working with the user, where the value entered by him
     * is processed and translated into the required functionality.
     * @param answerUser user action
     * @param bank bank where accounts are kept
     */
    public static void executingRequest(int answerUser, BankSystem bank) {
        switch(answerUser) {
            case 1: {
                printAccounts(bank);
            } break;
            case 2: {
                System.out.print("Enter account name: ");
                Scanner sc = new Scanner(System.in);
                String name = sc.nextLine();
                bank.add(name);
            } break;
            case 3: {
                System.out.print("Please indicate your account number: ");
                Scanner sc = new Scanner(System.in);
                Integer account = InteractionUser.inputInt(sc);
                System.out.print("Indicate the replenishment amount: ");
                int amount = InteractionUser.inputInt(sc);
                bank.replenishAccount(account, amount);
            } break;
            case 4: {
                System.out.print("Please indicate your account number: ");
                Scanner sc = new Scanner(System.in);
                Integer accountUser = InteractionUser.inputInt(sc);
                System.out.print("Specify which account to transfer to: ");
                Integer accountTrans = InteractionUser.inputInt(sc);
                System.out.print("Indicate the transfer amount: ");
                int amount = InteractionUser.inputInt(sc);
                bank.transferAmount(accountUser, accountTrans, amount);
            } break;
            case 5: {
                System.exit(0);
            }
        }
    }

    /**
     * Method for displaying all bank accounts.
     * @param bank bank where accounts are kept
     */
    public static void printAccounts(BankSystem bank) {
        if (!bank.get_accounts().isEmpty()) {
            for (Integer it : bank.get_accounts()) {
                var data = bank.get_account_pair(it);
                System.out.print("Number account: ");
                System.out.print(it);
                System.out.print(" - Name account: ");
                System.out.print(data.get_name());
                System.out.print(" - Balance account: ");
                System.out.print(data.get_balance());
                System.out.println();
            }
        } else {
            System.out.println("Sorry, but the bank does not have open accounts yet.");
        }
    }
}
