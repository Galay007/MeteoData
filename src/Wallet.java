import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Wallet {
    private User user;
    private Category categories;
    private List<Transaction> incomeTransactions;
    private List<Transaction> expenseTransactions;

    public Wallet(String username, String password, double beginAmount) {
        this.user = new User (username, password, beginAmount);
        this.categories = new Category();
        this.incomeTransactions = new ArrayList<>();
        this.expenseTransactions = new ArrayList<>();
    }

    public Category getCategories() {
        return categories;
    }

    public List<Transaction> getIncomeTransactions() {
        return incomeTransactions;
    }

    public List<Transaction> getExpenseTransactions() {
        return expenseTransactions;
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wallet wallet = (Wallet) o;
        return Objects.equals(user, wallet.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }

}
