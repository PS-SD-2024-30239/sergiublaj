package ro.ps.chefmgmtbackend.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {
    ERR001_CHEF_NOT_FOUND("Chef with ID %s not found");

    private final String message;
}
