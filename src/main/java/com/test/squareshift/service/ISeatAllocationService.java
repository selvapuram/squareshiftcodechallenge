/**
 * 
 */
package com.test.squareshift.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.squareshift.exception.SeatCapacityFullException;
import com.test.squareshift.exception.SeatCreationFailedException;
import com.test.squareshift.model.Seat;

/**
 * @author madhan
 *
 */
@Service
public interface ISeatAllocationService {
    int INITIAL_INDEX = 0;
    
    int getClusterCount();
    
    
    void initializeSettings();
    
    void allocateSeats(int passengers);

    void checkSeatCapacity(List<List<Integer>> seats, int passengerCount) throws SeatCapacityFullException;
    
    Seat getCreatedSeat();
    
    ISeatAllocationService allocationMiddleClusters(int rowIdx, int colIdx, int row, int col, int clusterIndex);
    ISeatAllocationService allocationLastCluster(int rowIdx, int colIdx, int row, int col, int clusterIndex);
    ISeatAllocationService allocationFirstCluster(int rowIdx, int colIdx, int row, int col, int clusterIndex);
    ISeatAllocationService allocationSingleCluster(int rowIdx, int colIdx, int row, int col, int clusterIndex);
    
    default boolean isNotBoundary(int clusterIndex) {
        return (clusterIndex > INITIAL_INDEX && clusterIndex < (getClusterCount() - 1));
    }
    
    default boolean isStartBoundary(int clusterIndex) {
        return clusterIndex == INITIAL_INDEX;
    }
    
    default boolean isEndBoundary(int clusterIndex) {
        return clusterIndex == (getClusterCount() - 1);
    }
    
    default Seat orElse() {
        if (this.getCreatedSeat() == null) {
            throw new SeatCreationFailedException("Seat Creation Failed");
        }
        return this.getCreatedSeat();
    }
    
}
