package ro.ps.chefmgmtbackend.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {
    ERR001_CHEF_NOT_FOUND("Chef with ID %s not found"),
    ERR002_EMAIL_NOT_FOUND("Email %s not found"),
    ERR099_INVALID_CREDENTIALS("Invalid credentials.");

    private final String message;
}
