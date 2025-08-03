package wallet_service.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;
@Getter
@Setter
public class InsufficientFundsException extends RuntimeException {

    private final UUID walletId;
    private final BigDecimal currentBalance;
    private final BigDecimal requiredAmount;


    public InsufficientFundsException(UUID walletId, BigDecimal currentBalance, BigDecimal requiredAmount) {
        super(String.format("Insufficient funds in wallet %s. Current balance: %.2f, required amount: %.2f",
                walletId, currentBalance, requiredAmount));
        this.walletId = walletId;
        this.currentBalance = currentBalance;
        this.requiredAmount = requiredAmount;

    }


}
