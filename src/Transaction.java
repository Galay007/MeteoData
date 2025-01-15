public class Transaction {
    private Operation operation;
    private String category;
    private double amount;
    private String comment;

    public Transaction(){}

    public Transaction(Operation operation, String category, double amount) {
        this.operation = operation;
        this.category = category;
        this.amount = amount;
    }

    public Transaction(Operation operation, String category, double amount, String comment) {
        this.operation = operation;
        this.category = category;
        this.amount = amount;
        this.comment = comment;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        if (comment == null) {
            return category + ": " + amount;
        } else {
            return category + ": " + amount + " " + comment;
        }
    }
}
