/**
 * 
 */
package com.test.squareshift.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author madhan
 *
 */
@Builder
@Getter
public class Cluster {
    
    private int rowSize;
    
    private int colSize;
    
    private int id;
    
    private List<List<Seat>> seats;
    
    @JsonIgnore
    @Setter
    private Map<Integer, Map<SeatType, Seat>> lastInsertNode;
}
