/**
 * 
 */
package com.test.squareshift.factory;

import com.test.squareshift.model.Seat;
import com.test.squareshift.model.WindowSeat;

import lombok.Getter;

/**
 * @author madhan
 *
 */
public class WindowSeatFactory implements SeatFactory {

    private static final WindowSeatFactory INSTANCE = new WindowSeatFactory();

    @Getter
    private Seat headNode;
    
 
    @Override
    public synchronized Seat getSeat(int row, int col, int clusterIndex) {
        Seat windowSeat = WindowSeat.getInstance(row, col, (clusterIndex + 1));
        if(headNode == null) {
            headNode  = windowSeat;
        } 
        return windowSeat;
    }

    public static SeatFactory getInstance() {
        return INSTANCE;
    }

}
