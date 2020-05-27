/**
 * 
 */
package com.test.squareshift.service;

import org.springframework.stereotype.Service;

/**
 * @author madhan
 *
 */
@Service
public interface ISeatAllocationService {
    
    void initializePriorityQueue();
    
    void allocateSeats(int passengers);
}
