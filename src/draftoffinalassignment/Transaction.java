

package draftoffinalassignment;

public abstract class Transaction {
    private double transactionAmount;
    private String timeOfPurchase;
    
public Transaction(double transactionAmount, String timeOfPurchase){
    this.timeOfPurchase = timeOfPurchase;
    this.transactionAmount = transactionAmount;
}

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTimeOfPurchase() {
        return timeOfPurchase;
    }

    public void setTimeOfPurchase(String timeOfPurchase) {
        this.timeOfPurchase = timeOfPurchase;
    }
}

