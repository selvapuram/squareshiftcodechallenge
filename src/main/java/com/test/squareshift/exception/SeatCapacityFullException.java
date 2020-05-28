/**
 * 
 */
package com.test.squareshift.exception;

import lombok.Getter;

/**
 * @author madhan
 *
 */
public class SeatCapacityFullException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 6342282228876057484L;
    
    @Getter
    private final String code = "1005";

    /**
     * 
     */
    public SeatCapacityFullException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     */
    public SeatCapacityFullException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public SeatCapacityFullException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     */
    public SeatCapacityFullException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public SeatCapacityFullException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

}
