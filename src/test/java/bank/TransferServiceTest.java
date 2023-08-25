package bank;

import bank.models.Account;
import bank.models.Transfer;
import bank.models.TransferResult;
import bank.services.LimitsService;
import bank.services.TransferService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TransferServiceTest {
    private TransferService transferService;
    private LimitsService limitsService;

    @BeforeEach
    void setUp() {
        this.limitsService = mock(LimitsService.class);
        when(limitsService.getBasicTransferLimit()).thenReturn(17_000.0);
        this.transferService = new TransferService(limitsService);
    }

    @AfterEach
    void tearDown() {
    }

    public Transfer balanceTransfer(final double senderBal, final double beneBal, final double transferAmt){
        Account sender = new Account("2089637191", senderBal);
        Account beneficiary = new Account("1902987162", beneBal);
        return new Transfer(transferAmt, sender, beneficiary);
    }

    @Test
    void transferWithCheckBalanceIsSufficient() {
        Transfer transfer = balanceTransfer(0.0,0.0, 2000);
        TransferResult result = this.transferService.transfer(transfer);

        assertFalse(result.status(), "insufficient balance transfer status should be false");
        assertEquals("Insufficient funds", result.message(), "insufficient balance transfer message incorrect");
    }

    @Test
    void transferWithCheckLimit() {
        Transfer transfer = balanceTransfer(100_000,0.0, 20_000);
        TransferResult result = this.transferService.transfer(transfer);
        assertFalse(result.status(), "limit exceeded. transfer status should be false");
        assertEquals("Transfer limit exceeded", result.message(), "limit check transfer message incorrect");

    }

    @Test
    void transferWithAllchecked() {

    }
}