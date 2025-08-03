package wallet_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import wallet_service.dto.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import wallet_service.dto.WalletOperationRequest;
import wallet_service.dto.WalletResponse;
import wallet_service.exceptions.InsufficientFundsException;
import wallet_service.exceptions.WalletNotFoundException;
import wallet_service.service.WalletService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService walletService;

    @PostMapping("/wallet")
    public ResponseEntity <WalletResponse> processOperation(@Valid @RequestBody WalletOperationRequest request){
        return ResponseEntity.ok(walletService.processOperation(request));
    }

    @GetMapping("/wallets/{walletId}")
    public ResponseEntity<WalletResponse> getBalance(@PathVariable UUID walletId) {
        return ResponseEntity.ok(walletService.getBalance(walletId));
    }

    @ExceptionHandler({WalletNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleNotFound(Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler({InsufficientFundsException.class})
    public ResponseEntity<ErrorResponse> handleInsufficientFunds(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(ex.getMessage()));
    }



}
