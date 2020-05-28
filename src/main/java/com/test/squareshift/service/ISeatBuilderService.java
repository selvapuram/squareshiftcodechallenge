/**
 * 
 */
package com.test.squareshift.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.squareshift.model.Cluster;

/**
 * @author madhan
 *
 */
@Service
public interface ISeatBuilderService {
    
    List<Cluster> buildSeats(List<List<Integer>> list, int passengers);
}
