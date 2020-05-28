/**
 * 
 */
package com.test.squareshift.service.impl;

import java.util.List;
import java.util.PriorityQueue;

import com.test.squareshift.exception.SeatCapacityFullException;
import com.test.squareshift.model.Cluster;
import com.test.squareshift.model.Seat;
import com.test.squareshift.model.SeatType;
import com.test.squareshift.service.ISeatAllocationService;

import lombok.Getter;
import lombok.Setter;

/**
 * @author madhan
 *
 */
public class SeatAllocationService implements ISeatAllocationService {

    private PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

    @Getter
    @Setter
    private int capacity;

    @Getter
    @Setter
    private Seat createdSeat;

    @Override
    public void allocateSeats(int passengers) {
        int poll = 1;
        while (!priorityQueue.isEmpty()) {
            SeatType seatType = SeatType.findByPriority(priorityQueue.poll());
            Seat node = seatType.getFactory().getHeadNode();

            while (node != null && poll <= passengers) {
                node.setPassengerId(poll);
                poll++;
                node = node.getNext();
            }
        }
    }

    @Override
    public void initializeSettings() {
        Cluster.getLastInsertNode().clear();
        for (SeatType seatType : SeatType.values()) {
            priorityQueue.offer(seatType.getPriority());
            seatType.getFactory().initializeHead();
        }

    }

    @Override
    public void checkSeatCapacity(List<List<Integer>> seats, int passengerCount) throws SeatCapacityFullException {
        int sum = 0;
        capacity = seats.size();
        for (List<Integer> s : seats) {
            sum += (s.get(0) * s.get(1));
        }
        if (passengerCount > sum) {
            throw new SeatCapacityFullException("Seat Capacity Full");
        }
    }

    private boolean isMiddleExists(int row) {
        return (row > 2);
    }

    private boolean isFirstSeat(int rowIdx) {
        return (rowIdx == INITIAL_INDEX);
    }

    private boolean isLastSeat(int rowIdx, int row) {
        return (rowIdx == (row - 1));
    }

    @Override
    public ISeatAllocationService allocationMiddleClusters(int rowIdx, int colIdx, int row, int col, int clusterIndex) {
        Seat createdSeat = null;
        if (isNotBoundary(clusterIndex)) {
            if (isMiddleExists(row) && !isFirstSeat(rowIdx) && !isLastSeat(rowIdx, row)) {
                createdSeat = SeatType.CENTER.getFactory().getSeat(rowIdx, colIdx, clusterIndex);
            } else {
                createdSeat = SeatType.AISLE.getFactory().getSeat(rowIdx, colIdx, clusterIndex);
            }
        }
        this.setCreatedSeat(createdSeat);
        return this;
    }

    @Override
    public ISeatAllocationService allocationSingleCluster(int rowIdx, int colIdx, int row, int col, int clusterIndex) {
        Seat createdSeat = this.getCreatedSeat();
        if (createdSeat == null && isStartBoundary(clusterIndex) && isEndBoundary(clusterIndex)) {
            if (isMiddleExists(row) && !isFirstSeat(rowIdx) && !isLastSeat(rowIdx, row)) {
                createdSeat = SeatType.CENTER.getFactory().getSeat(rowIdx, colIdx, clusterIndex);
            } else {
                createdSeat = SeatType.WINDOW.getFactory().getSeat(rowIdx, colIdx, clusterIndex);
            }
        }
        this.setCreatedSeat(createdSeat);
        return this;
    }

    @Override
    public ISeatAllocationService allocationFirstCluster(int rowIdx, int colIdx, int row, int col, int clusterIndex) {
        Seat createdSeat = this.getCreatedSeat();
        if (createdSeat == null && isStartBoundary(clusterIndex)) {
            if (isFirstSeat(rowIdx)) {
                createdSeat = SeatType.WINDOW.getFactory().getSeat(rowIdx, colIdx, clusterIndex);
            } else if (isMiddleExists(row) && !isLastSeat(rowIdx, row)) {
                createdSeat = SeatType.CENTER.getFactory().getSeat(rowIdx, colIdx, clusterIndex);
            } else {
                createdSeat = SeatType.AISLE.getFactory().getSeat(rowIdx, colIdx, clusterIndex);
            }
        }
        this.setCreatedSeat(createdSeat);
        return this;
    }
    
    @Override
    public ISeatAllocationService allocationLastCluster(int rowIdx, int colIdx, int row, int col, int clusterIndex) {
        Seat createdSeat = this.getCreatedSeat();
        if (createdSeat == null && isEndBoundary(clusterIndex)) {
            if (isLastSeat(rowIdx, row)) {
                createdSeat =  SeatType.WINDOW.getFactory().getSeat(rowIdx, colIdx, clusterIndex);
            } else if (isMiddleExists(row) && !isFirstSeat(rowIdx)) {
                createdSeat =  SeatType.CENTER.getFactory().getSeat(rowIdx, colIdx, clusterIndex);
            } else {
                createdSeat =  SeatType.AISLE.getFactory().getSeat(rowIdx, colIdx, clusterIndex);
            }
        }
        this.setCreatedSeat(createdSeat);
        return this;
    }
    

    @Override
    public int getClusterCount() {
        return capacity;
    }

}
