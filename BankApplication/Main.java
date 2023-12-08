public class Main {
    public static void main(String[] args) {
        BankSystem bank = new BankSystem();

        // The loop stops when the user enters 5 in the InteractionBank class
        while (true) {
            try {
                InteractionUser.menu();
                InteractionBank.executingRequest(InteractionUser.inputAnswerUser(), bank);
                System.out.println();
            } catch (Exception ex) {
                System.out.println("An unexpected situation has occurred. Try again.");
            }
        }
    }
}
