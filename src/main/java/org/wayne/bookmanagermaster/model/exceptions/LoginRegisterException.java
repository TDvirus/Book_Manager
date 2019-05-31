package org.wayne.bookmanagermaster.model.exceptions;

public class LoginRegisterException extends RuntimeException {

    public LoginRegisterException() {
        super();
    }

    public LoginRegisterException(String message) {
        super(message);
    }

    public LoginRegisterException(Throwable cause) {
        super(cause);
    }

    public LoginRegisterException(String message, Throwable cause) {
        super(message, cause);
    }
}
