package org.fruit.blueberry.exception;

/**
 * Created by AFei on 2014/7/26.
 */
public class UserExistException extends Exception {
    public UserExistException() {
        super();
    }

    public UserExistException(String message) {
        super(message);
    }

    public UserExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserExistException(Throwable cause) {
        super(cause);
    }
}
