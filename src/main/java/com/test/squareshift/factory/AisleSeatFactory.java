/**
 * 
 */
package com.test.squareshift.factory;

import com.test.squareshift.model.AisleSeat;
import com.test.squareshift.model.Seat;

import lombok.Getter;
import lombok.Setter;

/**
 * @author madhan
 *
 */
public class AisleSeatFactory implements SeatFactory {

    private static final AisleSeatFactory INSTANCE = new AisleSeatFactory();

    @Getter
    @Setter
    private Seat headNode;
    

    @Override
    public synchronized Seat getSeat(int row, int col, int clusterIndex) {
        Seat aisleSeat = AisleSeat.getInstance(row, col, (clusterIndex + 1));
        if(headNode == null) {
            headNode = aisleSeat;
        }
        return aisleSeat;
    }

    public static SeatFactory getInstance() {
        return INSTANCE;
    }

}
