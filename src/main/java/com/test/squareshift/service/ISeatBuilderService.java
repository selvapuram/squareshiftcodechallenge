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
    
    int INITIAL_INDEX = 0;
    
    int getClusterCount();
    
    List<Cluster> buildSeats(List<List<Integer>> list, int passengers);
    
    
    
    default boolean isNotBoundary(int clusterIndex) {
        return (clusterIndex > INITIAL_INDEX && clusterIndex < (getClusterCount() - 1));
    }
    
    default boolean isStartBoundary(int clusterIndex) {
        return clusterIndex == INITIAL_INDEX;
    }
    
    default boolean isEndBoundary(int clusterIndex) {
        return clusterIndex == (getClusterCount() - 1);
    }

}
