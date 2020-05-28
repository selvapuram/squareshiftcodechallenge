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
    int headIndex = 0;
    int currentIndex = 1;
    int nodeSize = 2;
    
    SeatType getSeatType();

    boolean isAisle();

    boolean isWindow();

    boolean isCenter();
    
    void setNext(Seat node);
        
    Seat getNext();
    
    void setPassengerId(int id);
    
    int getPassengerId();
    
    default Map<SeatType, Map<Integer, Seat[]>> assignedLatestNode(int colIndex, boolean isLastCluster, Map<SeatType, Map<Integer, Seat[]>> latestNode) {
        Map<Integer, Seat[]> currentNode = latestNode.getOrDefault(this.getSeatType(), null);
        Seat[] nodes = null; 
        if(currentNode == null) {
            currentNode = new HashMap<>();
            nodes = createNodes();
        } else {
            nodes = currentNode.getOrDefault(colIndex, null);
            boolean currentNodeExists = isNotEmpty(nodes);
            if(currentNodeExists) {
                nodes[currentIndex].setNext(this);
                nodes[currentIndex] = this;
            } else {
                nodes = createNodes(); 
            }
            if(isLastCluster) {
                Seat[] nextNodes = currentNode.getOrDefault(colIndex + 1, null);
                boolean isNextNodeExists = isNotEmpty(nextNodes);
                if(isNextNodeExists) {
                    this.setNext(nextNodes[headIndex]);
                } else {
                    Seat[] prevNodes = currentNode.getOrDefault(colIndex - 1, null);
                    boolean isPrevNodeExists = isNotEmpty(prevNodes);
                    if(isPrevNodeExists && prevNodes[currentIndex].getNext() == null) {
                        prevNodes[currentIndex].setNext(this);
                    }
                }
            }
        }
        currentNode.put(colIndex, nodes);
        latestNode.put(this.getSeatType(),  currentNode);
        return latestNode;
    }

    /**
     * @param nodes
     * @return
     */
    default boolean isNotEmpty(Seat[] nodes) {
        return nodes != null && nodes.length > 0;
    }

    /**
     * @return Seats
     */
    default Seat[] createNodes() {
        Seat[] nodes = new Seat[nodeSize];
        nodes[headIndex] = this;
        nodes[currentIndex] = this;
        return nodes;
    }
    
    
}
