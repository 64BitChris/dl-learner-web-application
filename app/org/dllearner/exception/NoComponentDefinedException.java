package org.dllearner.exception;

/**
 * Created with IntelliJ IDEA.
 * User: Chris
 * Date: 4/29/12
 * Time: 12:47 PM
 *
 * Exception to use when No Component is Defined.
 */
public class NoComponentDefinedException extends Exception {

    public NoComponentDefinedException() {
        super();
    }

    public NoComponentDefinedException(String message) {
        super(message);
    }

    public NoComponentDefinedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoComponentDefinedException(Throwable cause) {
        super(cause);
    }

    protected NoComponentDefinedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
