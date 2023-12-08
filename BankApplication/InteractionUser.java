import java.util.Scanner;

/**
 * An auxiliary class for user interaction,
 * where methods for working with the user are provided
 */
public class InteractionUser extends InteractionBank {

    /**
     * Displaying an action menu for the user.
     */
    public static void menu() {
        System.out.println("Menu");
        System.out.println("1. View a list of accounts.");
        System.out.println("2. Open an account.");
        System.out.println("3. Top up your account.");
        System.out.println("4. Transfer money between accounts.");
        System.out.println("5. Exit.");
    }

    /**
     * Method for entering user action.
     * @return selected user action.
     */
    public static int inputAnswerUser() {
        System.out.print("Select one of the suggested items: ");
        Scanner sc = new Scanner(System.in);
        int ans = 0;
        boolean flag = true;

        while (ans < 1 || ans > 5) {
            if (!flag) {
                System.out.print("Sorry! Select from the list provided: ");
            }

            while (!sc.hasNextInt()) {
                System.out.print("Sorry! Select from the list provided: ");
                sc.next();
            }

            flag = false;
            ans = sc.nextInt();
        }

        return ans;
    }

    /**
     * Method for entering the account and replenishment amount (int values).
     * @param sc scanner for input
     * @return entered correct user value
     */
    static int inputInt(Scanner sc) {
        int ans = -1;
        boolean flag = true;

        while (ans < 0) {
            if (!flag) {
                System.out.print("Sorry! you entered an incorrect value. Repeat: ");
            }

            while (!sc.hasNextInt()) {
                System.out.print("Sorry! you entered an incorrect value. Repeat: ");
                sc.next();
            }

            flag = false;
            ans = sc.nextInt();
        }

        return ans;
    }
}
