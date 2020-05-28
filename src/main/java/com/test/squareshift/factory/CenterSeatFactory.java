package com.test.squareshift.factory;

import com.test.squareshift.model.CenterSeat;
import com.test.squareshift.model.Seat;

import lombok.Getter;
import lombok.Setter;

public class CenterSeatFactory implements SeatFactory {

    private static final CenterSeatFactory INSTANCE = new CenterSeatFactory();

    @Getter
    @Setter
    private Seat headNode;

    @Override
    public synchronized Seat getSeat(int row, int col, int clusterIndex) {
        Seat centerSeat = CenterSeat.getInstance(row, col, (clusterIndex + 1));
        if(headNode == null) {
            headNode = centerSeat;
        }
        return centerSeat;
    }

    public static SeatFactory getInstance() {
        return INSTANCE;
    }

}
