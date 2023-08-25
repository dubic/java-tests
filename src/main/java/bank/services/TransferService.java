package bank.services;

import bank.models.Transfer;
import bank.models.TransferResult;

public class TransferService {
    private final LimitsService limitsService;

    public TransferService(LimitsService limitsService) {
        this.limitsService = limitsService;
    }

    public TransferResult transfer(final Transfer transfer) {
        try {
            checkBalanceIsSufficient(transfer);
            checkTransferLimit(transfer);
            return doTransfer(transfer);
        } catch (TransferException e) {
            return new TransferResult(false, e.getMessage());
        }
    }

    private TransferResult doTransfer(Transfer transfer) {
        return new TransferResult(true, "transfer successful");
    }

    public void checkTransferLimit(Transfer transfer) {
        double transferLimit = this.limitsService.getBasicTransferLimit();
        System.out.println("Limit is :"+transferLimit);
        if (transfer.amount() > transferLimit){
            throw new TransferException("Transfer limit exceeded");
        }
    }

    public void checkBalanceIsSufficient(Transfer transfer) {
        if (transfer.amount() > transfer.senderAccount().balance()) {
            throw new TransferException("Insufficient funds");
        }
    }
}
