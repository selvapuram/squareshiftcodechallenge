/**
 * 
 */
package com.test.squareshift.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.squareshift.dto.UserInput;
import com.test.squareshift.exception.NoSuchStrategyException;
import com.test.squareshift.model.Cluster;
import com.test.squareshift.service.impl.SeatAllocationService;
import com.test.squareshift.service.impl.SeatBuilderService;
import com.test.squareshift.service.impl.SeatService;

/**
 * @author madhan
 *
 */
@Service
public interface ISeatService {
    
    String DEFAULT_STRATEGY = "default";
    
    List<Cluster> process(UserInput userInput);
    
    static ISeatService getDefaultSeatService(String strategy) {
        if(DEFAULT_STRATEGY.equalsIgnoreCase(strategy)) {
            ISeatAllocationService seatAllocationService = new SeatAllocationService();
            ISeatBuilderService seatBuilderService = new SeatBuilderService(seatAllocationService);
            return new SeatService(seatBuilderService);
        }
        throw new NoSuchStrategyException("No implementation found");
    }

}
