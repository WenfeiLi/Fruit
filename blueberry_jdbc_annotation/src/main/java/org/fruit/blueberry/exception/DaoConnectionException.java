package org.fruit.blueberry.exception;

/**
 * Created by AFei on 2014/7/27.
 */
public class DaoConnectionException extends RuntimeException {
    public DaoConnectionException() {
        super();
    }

    public DaoConnectionException(String message) {
        super(message);
    }

    public DaoConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoConnectionException(Throwable cause) {
        super(cause);
    }
}
