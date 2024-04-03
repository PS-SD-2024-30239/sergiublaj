package ro.ps.chefmgmtbackend.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class NotFoundException extends RuntimeException {

    private final String message;
}
