/**
 * 
 */
package com.test.squareshift.service.impl;

import java.util.PriorityQueue;

import com.test.squareshift.model.Seat;
import com.test.squareshift.model.SeatType;
import com.test.squareshift.service.ISeatAllocationService;

/**
 * @author madhan
 *
 */
public class SeatAllocationService implements ISeatAllocationService {
    
    private PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

    @Override
    public void allocateSeats(int passengers) {
        int poll = 1;
        while(!priorityQueue.isEmpty()) {
            SeatType seatType = SeatType.findByPriority(priorityQueue.poll());
            Seat node = seatType.getFactory().getHeadNode();
            
            while(node != null && poll <= passengers) {
                node.setPassengerId(poll);
                poll++;
                node = node.getNext();
            }
        }
    }

    @Override
    public void initializePriorityQueue() {
        for(SeatType seatType : SeatType.values()) {
            priorityQueue.offer(seatType.getPriority());
        }
        
    }
    

    
}
