/**
 * 
 */
package com.test.squareshift.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.test.squareshift.model.Cluster;
import com.test.squareshift.model.Seat;
import com.test.squareshift.service.ISeatAllocationService;
import com.test.squareshift.service.ISeatBuilderService;

/**
 * @author madhan
 *
 */
public class SeatBuilderService implements ISeatBuilderService {

    private ISeatAllocationService seatAllocationService;


    public SeatBuilderService(ISeatAllocationService seatAllocationService) {
        this.seatAllocationService = seatAllocationService;
    }


    @Override
    public List<Cluster> buildSeats(List<List<Integer>> seatsRequest, int passengers) {
        initialize();
        seatAllocationService.checkSeatCapacity(seatsRequest, passengers);
        int clusterIndex = 0;
        List<Cluster> clusters = new ArrayList<>();
        for (List<Integer> block : seatsRequest) {

            int row = block.get(0);
            int col = block.get(1);
            
            List<List<Seat>> seatsArr = new ArrayList<>(row);

            Cluster cluster = Cluster.builder().rowSize(row).colSize(col).id(clusterIndex + 1).seats(seatsArr).build();
            for (int i = 0; i < row; i++) {
                List<Seat> seats = new ArrayList<>(col);
                for (int j = 0; j < col; j++) {
                    Seat createdSeat = buildSeat(i, j, row, col, clusterIndex);
                    Cluster.setLastInsertNode(createdSeat.assignedLatestNode(j, this.seatAllocationService.isEndBoundary(clusterIndex),
                            Cluster.getLastInsertNode()));
                    seats.add(createdSeat);
                }
                seatsArr = cluster.getSeats();
                seatsArr.add(seats);
            }
            clusters.add(cluster);
            clusterIndex++;
        }
        this.allocateSeats(passengers);
        return clusters;
    }

    public Seat buildSeat(int rowIdx, int colIdx, int row, int col, int clusterIndex) {
        return this.seatAllocationService.allocationMiddleClusters(rowIdx, colIdx, row, col, clusterIndex)
                        .allocationSingleCluster(rowIdx, colIdx, row, col, clusterIndex)
                        .allocationFirstCluster(rowIdx, colIdx, row, col, clusterIndex)
                        .allocationLastCluster(rowIdx, colIdx, row, col, clusterIndex)
                        .orElse();
    }

    private void initialize() {
        this.seatAllocationService.initializeSettings();
    }
    private void allocateSeats(int passengers) {
        this.seatAllocationService.allocateSeats(passengers);
    }
}
