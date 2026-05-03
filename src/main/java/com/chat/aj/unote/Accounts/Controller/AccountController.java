package com.chat.aj.unote.Accounts.Controller;

import com.chat.aj.unote.Accounts.Dto.AccountsDto;
import com.chat.aj.unote.Accounts.Entity.Accounts;
import com.chat.aj.unote.Accounts.Exceptions.ResourceNotFoundException;
import com.chat.aj.unote.Accounts.Service.IAccountService;
import com.chat.aj.unote.Accounts.request.AccountUpdateRequest;
import com.chat.aj.unote.Accounts.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/accounts")
public class AccountController {
    private final IAccountService accountService;

    @GetMapping("/{accountId}/account")
    public ResponseEntity<ApiResponse> getAccountById(@PathVariable Long accountId) {
        try {
            Accounts acc = accountService.getAccountById(accountId);
            AccountsDto accDto = accountService.convertToDto(acc);
            return ResponseEntity.ok(new ApiResponse("Find account success!", accDto));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/{accountId}/update")
    public ResponseEntity<ApiResponse> updateAccount(@RequestBody AccountUpdateRequest request, @PathVariable Long accountId) {
        try {
            Accounts acc = accountService.updateAccount(request, accountId);
            AccountsDto accDto = accountService.convertToDto(acc);
            return ResponseEntity.ok(new ApiResponse("Update account success!", accDto));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{accountId}/delete")
    public ResponseEntity<ApiResponse> deleteAccount(@PathVariable Long accountId) {
        try {
            accountService.deleteAccount(accountId);
            return ResponseEntity.ok(new ApiResponse("Delete account success!", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}