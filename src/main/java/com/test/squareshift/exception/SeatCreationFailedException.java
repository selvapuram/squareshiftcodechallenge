/**
 * 
 */
package com.test.squareshift.exception;

import lombok.Getter;

/**
 * @author madhan
 *
 */
public class SeatCreationFailedException extends RuntimeException {
    
    @Getter
    private final String code = "1001";

    /**
     * 
     */
    private static final long serialVersionUID = 6703292092301558571L;

    /**
     * 
     */
    public SeatCreationFailedException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     */
    public SeatCreationFailedException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public SeatCreationFailedException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     */
    public SeatCreationFailedException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public SeatCreationFailedException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

}
