import java.util.*;

/**
 * The main banking system where accounts
 * and transactions with them are stored
 */
public class BankSystem {

    /**
     * List of accounts with data.
     */
    private Map<Integer, AccountPair> _accounts;

    /**
     * The last invoice score is stored.
     */
    private int _lastAccount;

    public BankSystem() {
        _lastAccount = 22002400;
        _accounts = new HashMap<>();
    }

    public BankSystem(BankSystem bankAccounts) {
        this();
        for (Integer key : bankAccounts.get_accounts()) {
            _accounts.put(key, bankAccounts.get_account_pair(key));
            _lastAccount = key;
        }
    }

    /**
     *
     * @return open bank accounts
     */
    public Set<Integer> get_accounts() {
        return _accounts.keySet();
    }

    /**
     *
     * @param key bank account
     * @return a pair of name and account balance
     */
    protected AccountPair get_account_pair(Integer key) {
        return _accounts.get(key);
    }

    /**
     * Method for opening a bank account with balance
     * @param name name account
     * @param balance balance account
     */
    public void add(String name, int balance) {
        _accounts.put(_lastAccount, new AccountPair(name, balance));
        ++_lastAccount;
    }

    /**
     * Method for opening a bank account
     * @param name name account
     */
    public void add(String name) {
        add(name, 0);
    }

    /**
     * Method for replenishing your account
     * @param account replenishment account
     * @param amount top up amount
     */
    public void replenishAccount(Integer account, int amount) {
        if (_accounts.containsKey(account)) {
            _accounts.get(account).add_balance(amount);
        } else {
            System.out.println("Unfortunately, we do not have such an account in our bank.");
        }
    }

    /**
     * Method for transferring from one account to another
     * @param accountUs the account from which the transfer is made
     * @param accountTrans where is the account transferred to
     * @param amount transfer amount
     */
    public void transferAmount(Integer accountUs, Integer accountTrans, int amount) {
        if (_accounts.containsKey(accountUs) && _accounts.get(accountUs)._balance >= amount && _accounts.containsKey(accountTrans)) {
            _accounts.get(accountUs).subtract_balance(amount);
            replenishAccount(accountTrans, amount);
        } else if (_accounts.containsKey(accountUs) && _accounts.get(accountUs)._balance < amount) {
            System.out.println("Unfortunately there are not enough funds in your account.");
        } else if (!_accounts.containsKey(accountUs)) {
            System.out.println("Unfortunately, we do not have such an account in our bank.");
        } else {
            System.out.println("Unfortunately, the account you want to transfer to does not exist in our bank.");
        }
    }

    /**
     * Helper class for creating a pair from name and account balance.
     */
    protected class AccountPair extends BankSystem {

        /**
         * Account name.
         */
        private String _name;

        /**
         * Account balance.
         */
        private int _balance;

        public AccountPair(String name) {
            _name = name;
            _balance = 0;
        }

        public AccountPair(String name, int balance) {
            _name = name;
            _balance = balance;
        }

        /**
         * @return Get account name.
         */
        public String get_name() {
            return _name;
        }

        /**
         * Set account name.
         * @param name account name
         */
        public void set_name(String name) {
            _name = name;
        }

        /**
         * @return Balance.
         */
        public int get_balance() {
            return _balance;
        }

        /**
         * Crediting money to balance.
         * @param money replenishment amount
         */
        public void add_balance(int money) {
            _balance += money;
        }

        /**
         * Withdrawing money from your balance.
         * @param money withdrawal amount
         */
        public void subtract_balance(int money) {
            _balance -= money;
        }

    }
}
