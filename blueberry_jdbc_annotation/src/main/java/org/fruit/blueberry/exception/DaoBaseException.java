package org.fruit.blueberry.exception;

/**
 * Created by AFei on 2014/7/27.
 */
public class DaoBaseException extends RuntimeException {
    public DaoBaseException() {
        super();
    }

    public DaoBaseException(String message) {
        super(message);
    }

    public DaoBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoBaseException(Throwable cause) {
        super(cause);
    }
}
