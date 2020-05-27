/**
 * 
 */
package com.test.squareshift.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.test.squareshift.exception.SeatCreationFailedException;
import com.test.squareshift.model.Cluster;
import com.test.squareshift.model.Seat;
import com.test.squareshift.model.SeatType;
import com.test.squareshift.service.ISeatAllocationService;
import com.test.squareshift.service.ISeatBuilderService;

import lombok.Getter;
import lombok.Setter;

/**
 * @author madhan
 *
 */
public class SeatBuilderService implements ISeatBuilderService {
    
    private ISeatAllocationService seatAllocationService;
    
    @Getter
    @Setter
    private int capacity;
    
    public SeatBuilderService(ISeatAllocationService seatAllocationService) {
        this.seatAllocationService = seatAllocationService;
    }


    @Override
    public int getClusterCount() {
        return capacity;
    }

    @Override
    public List<Cluster> buildSeats(List<List<Integer>> seatsRequest, int passengers) {
        capacity = seatsRequest.size();
        
        int clusterIndex = 0;
        Cluster prevCluster = null;
        List<Cluster> clusters = new ArrayList<>();
        for(List<Integer> block : seatsRequest) {
            
            int row = block.get(0);
            int col = block.get(1);
            
            List<List<Seat>> seatsArr = new ArrayList<>(row);
            
            Cluster cluster = Cluster.builder()
                                .rowSize(row)
                                .colSize(col)
                                .id(clusterIndex + 1)
                                .seats(seatsArr).build();
            for(int  i = 0; i < row; i++) {
                List<Seat> seats = new ArrayList<>(col);
                for(int j = 0; j < col; j++) {
                    Seat createdSeat = buildSeat(i, j, row, col, clusterIndex);
                    cluster.setLastInsertNode(createdSeat.assignedLatestNode(j));
                    if(clusterIndex > 0) {                            
                        assignParentNode(createdSeat, j, prevCluster);
                    }
                    seats.add(createdSeat);
                }
                seatsArr = cluster.getSeats();
                seatsArr.add(seats);
            }
            clusters.add(cluster);
            prevCluster = cluster;
            clusterIndex++;
            
        }
        this.allocateSeats(passengers);
        return clusters;
    }
    
    

    private void assignParentNode(Seat createdSeat, int colIndex, Cluster prevCluster) {
        if(prevCluster.getLastInsertNode().containsKey(colIndex)) {
            Map<SeatType, Seat> lastSeatNode = prevCluster.getLastInsertNode().get(colIndex);
            Seat lastNode = lastSeatNode.getOrDefault(createdSeat.getSeatType(), null);
            if(lastNode != null) {
                lastNode.setNext(createdSeat);
            }
        }
    }

    public Seat buildSeat(int rowIdx, int colIdx, int row, int col, int clusterIndex) {
        boolean isMiddleExists = (row > 2);
        boolean isFirstSeat = (rowIdx == INITIAL_INDEX);
        boolean isLastSeat = (rowIdx == (row - 1));
        if (isNotBoundary(clusterIndex)) {
            if (isMiddleExists && !isFirstSeat && !isLastSeat) {
                return SeatType.CENTER.getFactory().getSeat(rowIdx, colIdx, clusterIndex);
            } else {
                return SeatType.AISLE.getFactory().getSeat(rowIdx, colIdx, clusterIndex);
            }
        } else if (isStartBoundary(clusterIndex)) {
            if(isFirstSeat) {
                return SeatType.WINDOW.getFactory().getSeat(rowIdx, colIdx, clusterIndex);
            } else if(isMiddleExists && !isLastSeat) {
                return SeatType.CENTER.getFactory().getSeat(rowIdx, colIdx, clusterIndex);
            } else {
                return SeatType.AISLE.getFactory().getSeat(rowIdx, colIdx, clusterIndex);
            }
        } else if (isEndBoundary(clusterIndex)) {
            if(rowIdx == (row - 1)) {
                return SeatType.WINDOW.getFactory().getSeat(rowIdx, colIdx, clusterIndex);
            } else if(isMiddleExists && !isFirstSeat) {
                return SeatType.CENTER.getFactory().getSeat(rowIdx, colIdx, clusterIndex);
            } else {
                return SeatType.AISLE.getFactory().getSeat(rowIdx, colIdx, clusterIndex);
            }
        }
        throw new SeatCreationFailedException("Seat Creation Failed");
    }


    private void allocateSeats(int passengers) {
        this.seatAllocationService.allocateSeats(passengers);
    }
}
