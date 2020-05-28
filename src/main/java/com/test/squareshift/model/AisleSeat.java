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
public class AisleSeat implements Seat {

    /**
     * 
     */
    private static final long serialVersionUID = 2794407942077150635L;
    
    @Getter
    private final SeatType seatType = SeatType.AISLE;

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
    
    
    public static AisleSeat getInstance(int rowIdx, int colIdx, int clusterIndex) {
        return AisleSeat.builder().rowIndex(rowIdx).columnIndex(colIdx).clusterId(clusterIndex).build();
    }


    @Override
    @JsonIgnore
    public boolean isAisle() {
        return true;
    }


    @Override
    @JsonIgnore
    public boolean isWindow() {
        return false;
    }


    @Override
    @JsonIgnore
    public boolean isCenter() {
        return false;
    }


 

}
