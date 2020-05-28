/**
 * 
 */
package com.test.squareshift.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author madhan
 *
 */
@Builder
public class WindowSeat implements Seat {

    /**
     * 
     */
    private static final long serialVersionUID = 396560816358797264L;
    
    @Getter
    private final SeatType seatType = SeatType.WINDOW;
    
    @Getter
    @Setter
    private int passengerId;
    
    @Getter
    @Setter
    private int rowIndex;
    
    @Getter
    @Setter
    private int columnIndex;
    
    @Getter
    @Setter
    private int clusterId;
    
    @Getter
    @Setter
    @JsonIgnore
    private Seat next;
    
    public static WindowSeat getInstance(int rowIdx, int colIdx, int clusterIndex) {
        return WindowSeat.builder().rowIndex(rowIdx).columnIndex(colIdx).clusterId(clusterIndex).build();
    }

    @Override
    @JsonIgnore
    public boolean isAisle() {
        return false;
    }

    @Override
    @JsonIgnore
    public boolean isWindow() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCenter() {
        return false;
    }
}
