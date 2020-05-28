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
public class CenterSeat implements Seat {

    /**
     * 
     */
    private static final long serialVersionUID = -4737055233451896287L;
    
    @Getter
    private final SeatType seatType = SeatType.CENTER;
    
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
    
    public static CenterSeat getInstance(int rowIdx, int colIdx, int clusterIndex) {
        return CenterSeat.builder().rowIndex(rowIdx).columnIndex(colIdx).clusterId(clusterIndex).build();
    }

    @Override
    @JsonIgnore
    public boolean isAisle() {
        return false;
    }

    @Override
    @JsonIgnore
    public boolean isWindow() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    @JsonIgnore
    public boolean isCenter() {
        return true;
    }

}
