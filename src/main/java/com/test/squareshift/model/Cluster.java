/**
 * 
 */
package com.test.squareshift.model;

import java.io.Serializable;
import java.util.HashMap;
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
public class Cluster implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = -5176484811574789143L;

    private int rowSize;
    
    private int colSize;
    
    private int id;
    
    private List<List<Seat>> seats;
    
    
    @JsonIgnore
    @Setter
    @Getter
    private static Map<SeatType, Map<Integer, Seat[]>> lastInsertNode = new HashMap<>();
}
