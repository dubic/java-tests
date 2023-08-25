package bank.services;

public class LimitsService {

    private final double transferLimit = 18_000;

    public double getBasicTransferLimit() {
        throw new RuntimeException("This should have connected to the DB");
    }

}
