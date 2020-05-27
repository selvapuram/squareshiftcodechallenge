package com.test.squareshift.service.impl;

import java.util.List;

import com.test.squareshift.dto.UserInput;
import com.test.squareshift.model.Cluster;
import com.test.squareshift.service.ISeatBuilderService;
import com.test.squareshift.service.ISeatService;

public class SeatService implements ISeatService {

    private ISeatBuilderService seatBuilderService;

    public SeatService(ISeatBuilderService seatBuilderService) {

        this.seatBuilderService = seatBuilderService;
    }

    @Override
    public List<Cluster> process(UserInput userInput) {
        return seatBuilderService.buildSeats(userInput.getSeats(), userInput.getNoOfPassengers());
    }

}
