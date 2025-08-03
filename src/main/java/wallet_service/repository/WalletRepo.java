package wallet_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wallet_service.entity.Wallet;

import java.util.UUID;

@Repository
public interface WalletRepo extends JpaRepository<Wallet, UUID> {
}
