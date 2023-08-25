package bank.services;

public class TransferException extends RuntimeException{

    public TransferException(String msg) {
        super(msg);
    }
}
