package bank;

import bank.models.Account;
import bank.models.Transfer;
import bank.services.LimitsService;
import bank.services.TransferService;

public class App {
    public static void main(String[] args) {
        System.out.println("Bank service running...");
        Account sender = new Account("2089637191", 0.0);
        Account beneficiary = new Account("2089637191", 0.0);

        Transfer transfer = new Transfer(2000, sender, beneficiary);

        LimitsService limitsService = new LimitsService();
        TransferService transferService = new TransferService(limitsService);
        transferService.transfer(transfer);
    }
}
