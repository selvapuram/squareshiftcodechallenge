/**
 * 
 */
package com.test.squareshift.model;

import java.util.HashMap;
import java.util.Map;

import com.test.squareshift.factory.AisleSeatFactory;
import com.test.squareshift.factory.CenterSeatFactory;
import com.test.squareshift.factory.SeatFactory;
import com.test.squareshift.factory.WindowSeatFactory;

import lombok.Getter;

/**
 * @author madhan
 *
 */
public enum SeatType {
    AISLE(1) {

        @Override
        public SeatFactory getFactory() {
            return AisleSeatFactory.getInstance();
        }
        
    },
    WINDOW(2) {

        @Override
        public SeatFactory getFactory() {
            return WindowSeatFactory.getInstance();
        }
        
    },
    CENTER(3) {

        @Override
        public SeatFactory getFactory() {
            return CenterSeatFactory.getInstance();
        }
        
    };
    
    SeatType(int priority) {
        this.priority = priority;
    }
    
    abstract public SeatFactory getFactory();
    
    @Getter int priority;
    
    private static final Map<Integer,SeatType> map;
    static {
        map = new HashMap<Integer,SeatType>();
        for (SeatType v : SeatType.values()) {
            map.put(v.priority, v);
        }
    }
    
    public static SeatType findByPriority(int p) {
        return map.get(p);
    }
}
