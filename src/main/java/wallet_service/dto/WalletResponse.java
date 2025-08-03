package wallet_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
public class WalletResponse {
    private UUID walletId;
    private BigDecimal balance;
}
