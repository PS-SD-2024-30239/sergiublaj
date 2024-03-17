package ro.ps.chefmgmtbackend.exception;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ExceptionBody handleNotFoundException(NotFoundException exception) {
        return new ExceptionBody(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public final ExceptionBody handleInvalidArgument(MethodArgumentNotValidException exception) {
        return new ExceptionBody(Arrays.toString(exception.getDetailMessageArguments()));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public final ExceptionBody handleException(Exception exception) {
        return new ExceptionBody("EXCEPTION: " + exception.getMessage());
    }
}
