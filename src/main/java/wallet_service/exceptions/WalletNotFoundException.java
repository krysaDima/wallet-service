package wallet_service.exceptions;

import java.util.UUID;

public class WalletNotFoundException extends RuntimeException{
    public WalletNotFoundException (UUID  walletId){
        super("Кошелек не найден по идентификатору:" + walletId);
    }
}
