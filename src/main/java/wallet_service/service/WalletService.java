package wallet_service.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wallet_service.dto.WalletOperationRequest;
import wallet_service.dto.WalletResponse;
import wallet_service.entity.Wallet;
import wallet_service.exceptions.InsufficientFundsException;
import wallet_service.exceptions.WalletNotFoundException;
import wallet_service.repository.WalletRepo;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class WalletService {
    private final WalletRepo walletRepo;

    public WalletResponse processOperation  (WalletOperationRequest request){
        Wallet wallet = walletRepo.findById(request.getWalletId()).orElseThrow(() -> new WalletNotFoundException(request.getWalletId()));

        BigDecimal amount = request.getAmount();
        if ("WITHDRAW".equals(request.getOperationType())){
            if (wallet.getBalance().compareTo(amount)< 0 ){
                throw new InsufficientFundsException(wallet.getId(), wallet.getBalance(), amount);
            }
            wallet.setBalance(wallet.getBalance().subtract(amount));
        } else {
            wallet.setBalance(wallet.getBalance().add(amount));
        }
        wallet = walletRepo.save(wallet);
        return new WalletResponse(wallet.getId(), wallet.getBalance());
    }

    public WalletResponse getBalance(UUID walletId){
        Wallet wallet = walletRepo.findById(walletId).orElseThrow(()-> new WalletNotFoundException(walletId));
        return new WalletResponse(wallet.getId(), wallet.getBalance());

    }


    }





