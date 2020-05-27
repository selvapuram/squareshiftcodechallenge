/**
 * 
 */
package com.test.squareshift.exception;

import lombok.Getter;

/**
 * @author madhan
 *
 */
public class NoSuchStrategyException extends RuntimeException {
    
    @Getter
    private final String code = "1000";
    /**
     * 
     */
    private static final long serialVersionUID = 865853825868018745L;

    /**
     * 
     */
    public NoSuchStrategyException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     */
    public NoSuchStrategyException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public NoSuchStrategyException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     */
    public NoSuchStrategyException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public NoSuchStrategyException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

}
