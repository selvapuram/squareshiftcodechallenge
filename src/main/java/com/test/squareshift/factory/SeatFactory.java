package com.test.squareshift.factory;

import com.test.squareshift.model.Seat;

public interface SeatFactory {
    
    Seat getSeat(int row, int col, int clusterIndex);
    
    Seat getHeadNode();
    
}
