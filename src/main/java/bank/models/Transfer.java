package bank.models;


public record Transfer(double amount, Account senderAccount, Account beneficiaryAccount) {
    public Transfer {
        if (amount <= 0){
            throw new IllegalArgumentException("transfer amount must be > 0");
        }
    }
}
