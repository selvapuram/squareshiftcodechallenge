/**
 * 
 */
package com.test.squareshift.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author madhan
 *
 */
public interface Seat extends Serializable {
    SeatType getSeatType();

    boolean isAisle();

    boolean isWindow();

    boolean isCenter();
    
    default Map<Integer, Map<SeatType, Seat>> assignedLatestNode(int colIndex) {
        Map<Integer, Map<SeatType, Seat>> latestNode = new HashMap<>();
        Map<SeatType, Seat> currentNode = new HashMap<>();
        currentNode.put(this.getSeatType(), this);
        latestNode.put(colIndex,  currentNode);
        return latestNode;
    }
    
    void setNext(Seat node);
    
    Seat getNext();
    
    void setPassengerId(int id);
}
